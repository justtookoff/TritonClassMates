package com.android.tritonclassmates;

import java.util.LinkedList;

/**
 * This is a class for saving friends.
 * Since AVLTree is faster than RBTree when finding friend, data structure AVL Tree was chosen.
 * AVL Tree was chosen because of its efficient time complexity.
 * Insert: log(n)
 * Find: log(n)
 * remove: log(n)
 * @Author: Dan Donghwee Kwon
 * @Date: 19 Jun 2017
 */

public class AVLTree<T extends Comparable<? super T>> {
    private AVLNode root;
    private int nelems;

    /**
     * This class is a AVL Node class for AVL Tree
     */
    protected class AVLNode {
        T key;
        /**
         * I am not sure how to save detailed info of friends
         * I think we need to make several arrays
         */
        //LinkedList<T> info;
        int balanceFactor;
        AVLNode left;
        AVLNode right;

        /**
         * Constructor for AVLNode
         * @param key
         */
        public AVLNode(T key) {
            this.key = key;
            this.balanceFactor = 0;
            this.left = null;
            this.right = null;
        }

        /**
         * Constructor for AVLNode
         * @param key
         * @param left
         * @param right
         */
        public AVLNode(T key, AVLNode left, AVLNode right) {
            this.key = key;
            this.balanceFactor = 0;
            this.left = left;
            this.right = right;
        }

        //Setters
        public void setKey(T key) {
            this.key = key;
        }

        public void setBalanceFactor(int balanceFactor) {
            this.balanceFactor = balanceFactor;
        }

        public void setLeft(AVLNode left) {
            this.left = left;
        }

        public void setRight(AVLNode right) {
            this.right = right;
        }

        //Getters
        public T getKey() {
            return this.key;
        }

        public int getBalanceFactor() {
            return this.balanceFactor;
        }

        public AVLNode getLeft() {
            return this.left;
        }

        public AVLNode getRight() {
            return this.right;
        }
    }

    /**
     * Constructor for AVLTree
     */
    public AVLTree(){
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Insert the key to the
     */
    public void insert(T key){

        //If the root is null
        if(this.root == null){
            this.root = new AVLNode(key);
            this.nelems++;
        }
        //If there is root node already
        else{
            //Find method
        }
    }

    public boolean find(T key) throws NullPointerException{
        boolean found = false;
        AVLNode curr = this.root;

        //NullpointerException check
        if(key == null) throw new NullPointerException();

        //Check the left part
        if(curr.getKey().compareTo(key) > 0){

        }
        //Check the right part
        else if(curr.getKey().compareTo(key) < 0){

        }
        else{
            found = true;
        }
        return found;
    }

    //getters
    public AVLNode getRoot(){
        return this.root;
    }

    public int getNelems(){
        return this.nelems;
    }
}
