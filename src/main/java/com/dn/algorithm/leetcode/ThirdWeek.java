package com.dn.algorithm.leetcode;

import java.io.IOException;

/**
 * @author dingning
 * @version 1.0
 * @description:
 * @date 2022/5/30 17:23
 */
public class ThirdWeek {
    public static void main(String[] args) {
//        System.out.println(test01("pwwkew"));
        try {
            //一分钟后电脑关机
            Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\shutdown.exe -s -f");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int test01(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        StringBuilder longest = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (!longest.toString().contains(s.substring(i, j))) {
                    longest.append(s.charAt(i));
                }
            }

        }
        System.out.println(longest);
        return longest.length();
    }

//    class Solution {
//    public int lengthOfLongestSubstring(String s) {
//        int n=s.length();
//        if(n==0)
//            return 0;
//        if(n==1)
//            return 1;
//         char[] ch=s.toCharArray();
//         int max=1;
//         for(int i=0;i<n-1;i++){
//             int cur=1;
//             Set<Character> isCircle = new HashSet<Character>();
//             for(int j=i+1;j<n;j++){
//                isCircle.add(ch[i]);
//                if(!isCircle.contains(ch[j])){
//                     cur++;
//                     isCircle.add(ch[j]);
//                }
//                 else
//                     break;
//             }
//             max=Math.max(cur,max);
//         }
//         return max;
//    }
//}
//
//作者：vvonderful-6oldbergkgj
//链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/solution/bao-by-vvonderful-6oldbergkgj-bbl4/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}
