package com.dn.bean;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
* @description: 
* @author dingning
* @date 2022/5/17 17:56
* @version 1.0
*/
public class FakeTree<T> implements Iterable<FakeTree<T>> {

    /**
     * 树节点
     */
    public T data;

    /**
     * 父节点，根没有父节点
     */
    public FakeTree<T> parent;

    /**
     * 子节点，叶子节点没有子节点
     */
    public List<FakeTree<T>> children;

    /**
     * 保存了当前节点及其所有子节点，方便查询
     */
    private List<FakeTree<T>> elementsIndex;

    /**
     * 构造函数
     *
     * @param data
     */
    public FakeTree(T data) {
        this.data = data;
        this.children = new LinkedList<FakeTree<T>>();
        this.elementsIndex = new LinkedList<FakeTree<T>>();
        this.elementsIndex.add(this);
    }

    /**
     * 判断是否为根：根没有父节点
     *
     * @return
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * 判断是否为叶子节点：子节点没有子节点
     *
     * @return
     */
    public boolean isLeaf() {
        return children.size() == 0;
    }

    /**
     * 添加一个子节点
     *
     * @param child
     * @return
     */
    public FakeTree<T> addChild(T child) {
        FakeTree<T> childNode = new FakeTree<T>(child);

        childNode.parent = this;

        this.children.add(childNode);

        this.registerChildForSearch(childNode);

        return childNode;
    }

    /**
     * 获取当前节点的层
     *
     * @return
     */
    public int getLevel() {
        if (this.isRoot()) {
            return 0;
        } else {
            return parent.getLevel() + 1;
        }
    }

    /**
     * 递归为当前节点以及当前节点的所有父节点增加新的节点
     *
     * @param node
     */
    private void registerChildForSearch(FakeTree<T> node) {
        elementsIndex.add(node);
        if (parent != null) {
            parent.registerChildForSearch(node);
        }
    }

    /**
     * 从当前节点及其所有子节点中搜索某节点
     *
     * @param cmp
     * @return
     */
    public FakeTree<T> findTreeNode(Comparable<T> cmp) {
        for (FakeTree<T> element : this.elementsIndex) {
            T elData = element.data;
            if (cmp.compareTo(elData) == 0)
                return element;
        }

        return null;
    }

    /**
     * 获取当前节点的迭代器
     *
     * @return
     */
    @Override
    public Iterator<FakeTree<T>> iterator() {
        FakeTreeIterator<T> iterator = new FakeTreeIterator<T>(this);
        return iterator;
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : "[tree data null]";
    }
}
