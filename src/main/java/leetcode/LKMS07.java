package main.java.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LKMS07
 * @Description TODO 力扣剑指offer面试题 07
 * @Author HeXiaoyuan
 * @Date 2020-06-22 9:21
 */
public class LKMS07 {

    class Solution {
        /**
         * @Description: 先在前序遍历序列中定位根节点值，然后在中序遍历序列中定位左子树和右子树
         * @Author: HeXiaoyuan
         * @Date: 2020-06-22 9:23
         * @param preorder: 前序遍历序列
         * @param inorder: 中序遍历序列
         * @return: {@link TreeNode}
         **/
        public TreeNode buildTree(int[] preorder, int[] inorder) {

            if(preorder==null||preorder.length==0){
                return null;
            }
            int length = preorder.length;
            Map<Integer , Integer> indexMap = new HashMap<>();//记录中序中所有值的索引，省的遍历查询了
            for (int i = 0 ; i < length ; i++){
                indexMap.put(inorder[i],i);
            }
            TreeNode tree = buildTree(preorder,0,length-1,inorder,0,length-1,indexMap);

            return tree;
        }

        public TreeNode buildTree (int[] preorder , int preStart , int preEnd , int[] inorder ,int inStart , int inEnd,Map<Integer,Integer> indexMap){
            if(preStart > preEnd){
                return null;
            }
            int rootVal = preorder[preStart];
            TreeNode rootNode = new TreeNode(rootVal);

            if(preStart == preEnd){
                return rootNode;
            }else{
                //找出中序遍历中的根节点位置
                int rootOrderIndex = indexMap.get(rootVal);
                int leftNodeLength = rootOrderIndex - inStart;
//                int rightNodeLength = inEnd - rootOrderIndex;
                TreeNode leftTree = buildTree(preorder,preStart+1,preStart+leftNodeLength,inorder,inStart,rootOrderIndex-1,indexMap);
                TreeNode rightTree = buildTree(preorder,preStart+leftNodeLength+1,preEnd,inorder,rootOrderIndex+1,inEnd,indexMap);
                rootNode.left = leftTree;
                rootNode.right = rightTree;
            }

            return rootNode;
        }
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class FastSolution {
        int preindex = 0;
        int inindex = 0;

        public TreeNode buildTree(int[] preorder, int[] inorder) {

            return buildtree(preorder,inorder,null);

        }

        public TreeNode buildtree(int[] preorder,int[] inorder, TreeNode finish){

            if(preindex == preorder.length ||(finish != null && inorder[inindex] == finish.val)){
                return null;
            }

            TreeNode root = new TreeNode(preorder[preindex ++]); //第一次创建的时候就是以第一个根节点创建的
            //左子树
            root.left = buildtree(preorder, inorder ,root);
            inindex ++;
            //右子树
            root.right = buildtree(preorder,inorder,finish);

            return root;


        }
    }
}
