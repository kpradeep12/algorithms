package org.algorithms.tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    static class Node{
        int data;
        Node left,right;
        Node(int data) {this.data = data;}
        Node(int data, Node left, Node right) {this.data = data; this.left=left; this.right=right; }
    }
    public static void preOrder(Node n) {
        if(n == null) return;
        preOrder(n.left);
        System.out.printf(" %d ",n.data);
        preOrder(n.right);
    }
    
    public static int maxDepth(Node node) {
        if(node == null) return 0;
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        int bigger = Math.max(leftDepth, rightDepth);
        return bigger+1;
    }

    public static boolean checkEqualTrees(Node n1, Node n2) {
        if(n1==null && n2==null) return true;
        if(n1.data != n2.data || n1==null || n2==null) return false;
       // boolean result = (checkEqualTrees(n1.left, n2.left) && checkEqualTrees(n1.right, n2.right));
        //if(result && n1.data == n2.data) return true; else return false;
       return  (checkEqualTrees(n1.left, n2.left) && checkEqualTrees(n1.right, n2.right));
    }

    public static void invertTree(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            Node n = queue.poll();
            if(n.left != null) queue.add(n.left);
            if(n.right != null) queue.add(n.right);
            Node temp = n.left;
            n.left = n.right;
            n.right = temp;            
        }
    }

    public static ArrayList<ArrayList<Integer>> levelOrder(Node node) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();
        Queue<Node> current = new LinkedList<>();
        Queue<Node> nextLevel = new LinkedList<>();
        current.add(node);
        while(!current.isEmpty()) {
            Node n = current.poll();
            if(n.left!=null) nextLevel.add(n.left);
            if(n.right!=null) nextLevel.add(n.right);
            values.add(n.data);
            if(current.isEmpty()){
                current = nextLevel;
                nextLevel = new LinkedList<>();
                result.add(values);
                values = new ArrayList<>();
            }
        }
        return result;
    }
    public static void main(String[] args) {
        //Node node = new Node(3, new Node(9), 
        //new Node(20, new Node(15), new Node(7)));
        //System.out.println(maxDepth(node));

        //Node n1 = new Node(1, new Node(2), new Node(1));
        //Node n2 = new Node(1, new Node(2), new Node(1));
        //System.out.println(checkEqualTrees(n1, n2));

        /*Node n = new Node(1, new Node(2), new Node(3));
        preOrder(n);
        invertTree(n);
        System.out.println();
        preOrder(n);*/

        Node n = new Node(3, new Node(9), new Node(20, new Node(15), new Node(7)));
        ArrayList<ArrayList<Integer>> result = levelOrder(n);
        for(ArrayList<Integer> list: result) {
            list.forEach(num -> System.out.printf("%d ", num));
            System.out.println();
        }

    }
}