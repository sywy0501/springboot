package com.cs.springboot.dataStructure;

import javax.xml.soap.Node;
import java.util.LinkedList;

/**
 * @author: ChuShi
 * @date: 2020/03/05 3:02 下午
 * @desc:
 */
public class TreeNode {

    private TreeNode left;
    private TreeNode right;

    private Object data;

    public TreeNode(TreeNode left,TreeNode right,Object data){
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public TreeNode(Object o){
        this.data = o;
    }

    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){

        TreeNode node = null;

        if (inputList==null||inputList.isEmpty()){
            return null;
        }

        Integer data = inputList.removeFirst();

        if (data!=null){
            node = new TreeNode(data);
            node.left = createBinaryTree(inputList);
            node.right = createBinaryTree(inputList);
        }
        return node;
    }
}
