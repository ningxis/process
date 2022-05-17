package com.dn.bean;

import java.util.Iterator;

/**
* @description: 
* @author dingning
* @date 2022/5/17 17:59
* @version 1.0
*/
public class FakeTreeIterator<T> implements Iterator<FakeTree<T>>{
    enum ProcessStages {
        ProcessParent, ProcessChildCurNode, ProcessChildSubNode
    }

    private ProcessStages doNext;

    private FakeTree<T> next;

    private Iterator<FakeTree<T>> childrenCurNodeIter;

    private Iterator<FakeTree<T>> childrenSubNodeIter;

    private FakeTree<T> FakeTree;

    public FakeTreeIterator(FakeTree<T> FakeTree) {
        this.FakeTree = FakeTree;
        this.doNext = ProcessStages.ProcessParent;
        this.childrenCurNodeIter = FakeTree.children.iterator();
    }

    @Override
    public boolean hasNext() {

        if (this.doNext == ProcessStages.ProcessParent) {
            this.next = this.FakeTree;
            this.doNext = ProcessStages.ProcessChildCurNode;
            return true;
        }

        if (this.doNext == ProcessStages.ProcessChildCurNode) {
            if (childrenCurNodeIter.hasNext()) {
                FakeTree<T> childDirect = childrenCurNodeIter.next();
                childrenSubNodeIter = childDirect.iterator();
                this.doNext = ProcessStages.ProcessChildSubNode;
                return hasNext();
            } else {
                this.doNext = null;
                return false;
            }
        }

        if (this.doNext == ProcessStages.ProcessChildSubNode) {
            if (childrenSubNodeIter.hasNext()) {
                this.next = childrenSubNodeIter.next();
                return true;
            } else {
                this.next = null;
                this.doNext = ProcessStages.ProcessChildCurNode;
                return hasNext();
            }
        }

        return false;
    }

    @Override
    public FakeTree<T> next() {
        return this.next;
    }

    /**
     * 目前不支持删除节点
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
}
