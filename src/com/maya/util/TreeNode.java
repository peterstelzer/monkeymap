package com.maya.util;

import com.maya.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pete on 10/12/14.
 */
public class TreeNode<T> {

    /**
     * The list of child nodes
     */
    List<TreeNode<T>> children = new ArrayList<TreeNode<T>>();


    /**
     * The data associated with this node
     */
    T data;


    /**
     * The parent node of this node
     */
    TreeNode<T> parent;

    /**
     * Public constructor
     *
     * @param data
     */
    public TreeNode(T data) {

        this.data = data;
    }


    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }


    /**
     * addChildNode
     *
     * @param child
     * @return boolean indicating success. Failure results from adding a room that's already in this branch.
     */
    public boolean addChildNode(TreeNode<T> child) {
        // make sure the room has not already been added in this particular branch
        TreeNode<T> currentParent = parent;
        while (currentParent != null) {
            if (currentParent.data.equals(child.data)) {
                return false;
            } else {
                currentParent = currentParent.parent;
            }
        }
        children.add(child);
        child.setParent(this);
        return true;
    }


    public boolean hasChild(T child) {
        return (!children.contains(child));
    }


    /**
     * getDistanceToRoot returns the number of elements in the current branch (distance from leaf to root)
     *
     * @return the number of elements in the branch
     */
    public int getDistanceToRoot() {
        int index = 0;
        TreeNode<T> currentParent = this.parent;
        while (currentParent != null) {
            index++;
            currentParent = currentParent.parent;
        }
        return index;
    }


    public TreeNode<T> getRoot() {
        TreeNode<T> currentNode = this;
        while (currentNode.parent != null) {
            currentNode = currentNode.parent;
        }
        return currentNode;
    }


    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }


    @Override
    public String toString() {
        return getData().toString();
    }

    public int numberOfLeafNodes() {
        return numberOfLeafNodes(this, 0);
    }


    protected int numberOfLeafNodes(TreeNode<T> node, int count) {
        if (node.children.size() == 0) {
            return ++count;
        }
        for (TreeNode<T> currentNode : node.children) {
            count = numberOfLeafNodes(currentNode, count);
        }
        return count;
    }


    public List<TreeNode<T>> findPathsWithCertainEndpoint(T endPoint) {
        List<TreeNode<T>> results = new ArrayList<TreeNode<T>>();
        findPathsWithCertainEndpoint(results, endPoint, this);
        return results;
    }

    public void findPathsWithCertainEndpoint(List<TreeNode<T>> results, T endPoint, TreeNode<T> node) {
        if (node.children.size() == 0) {
            // we have an endpoint!  Is it our sought-after one?
            if (node.data.equals(endPoint)) {
                System.out.println("node.data.toString() = " + node.data.toString());
                results.add(node);
            }
        }
        else{
            for (TreeNode<T> currentNode : node.children) {
                findPathsWithCertainEndpoint(results, endPoint, currentNode);
            }
        }
    }
}
