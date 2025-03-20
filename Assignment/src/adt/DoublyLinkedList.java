/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Iterator;

/**
 *
 * @author Acer
 */
public class DoublyLinkedList<T extends Comparable<T>> implements ListInterface<T>, Iterable<T> {

    private int length;
    private Node headNode;
    private Node tailNode;

    public DoublyLinkedList() {
        this.headNode = null;
        this.tailNode = null;
        this.length = 0;
    }

    @Override
    public void addAtFront(T newData) {
        Node newNode = new Node(newData);
        if (isEmpty()) {
            headNode = newNode;
            tailNode = headNode;

        } else {

            headNode.prev = newNode;

            newNode.next = headNode;
            headNode = newNode;

        }
        length++;
    }

    @Override
    public void addAtBack(T newData) {
        Node newNode = new Node(newData);
        if (isEmpty()) {
            headNode = newNode;
            tailNode = headNode;

        } else {
            tailNode.next = newNode;
            newNode.prev = tailNode;
            tailNode = newNode;

        }
        length++;
    }

    @Override
    public void addAtIndex(T newData, int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }

        if (index == 0) {
            addAtFront(newData);
        } else if (index == length) {
            addAtBack(newData);
        } else {
            Node currentNode = getNode(index);
            Node newNode = new Node(newData);

            //move using one direction
//            for (int i = 0; i < index; i++) {
//                currentNode = currentNode.next;
//            }
            newNode.prev = currentNode.prev;
            newNode.next = currentNode;
            currentNode.prev.next = newNode;
            currentNode.prev = newNode;
            length++;
        }
    }

    // retrieve current node position (move using 2 directions)
    private Node getNode(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }

        Node current;

        if (index < length / 2) {
            // move forward from head
            current = headNode;
            for (int i = 0; i < index; i++) {
                current = current.next; // to right
            }
        } else {
            //move backward from tail
            current = tailNode;
            for (int i = length - 1; i > index; i--) {
                current = current.prev; // to left
            }
        }
        return current;
    }

    @Override
    public T removeFront() {

        if (isEmpty()) {
            return null;
        }
        T temp = headNode.data;
        if (headNode == tailNode) {

            headNode = null;
            tailNode = null;
        } else {

            headNode = headNode.next;
            headNode.prev = null;

        }
        length--;
        return temp;

    }

    @Override
    public T removeBack() {
        if (isEmpty()) {
            return null;
        }
        T temp = tailNode.data;
        if (headNode == tailNode) {

            headNode = null;
            tailNode = null;
        } else {

            tailNode = tailNode.prev;
            tailNode.next = null;

        }
        length--;
        return temp;

    }

    @Override
    public T removeByIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }
        if (isEmpty()) {
            return null;
        }
        if (index == 0) {
            return removeFront();
        } else if (index == length - 1) {
            return removeBack();
        }

        Node temp = headNode;
        for (int i = 0; i < index; i++) {
            temp = temp.next;

        }

        if (temp == null) {
            return null;
        }

        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }

        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }

        length--;
        return temp.data;
    }

    @Override
    public void viewAllForward() {
        Node temp = headNode;
        while (temp != null) {
            System.out.print(temp.data + "-->");
            temp = temp.next;
        }
    }

    @Override
    public void viewAllBackward() {
        Node temp = tailNode;
        while (temp != null) {
            System.out.print(temp.data + "-->");
            temp = temp.prev;
        }
    }

    @Override
    public T viewDataAtIndex(int index) {
        return getNode(index).data;
    }

    @Override
    public T updateFront(T newData) {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T replaced = headNode.data;
        headNode.data = newData;

//        Node temp = new Node(newData);
//        temp.next = headNode.next;
//        temp.prev = headNode.prev;
//        headNode = temp;
        return replaced;
    }

    @Override
    public T updateBack(T newData) {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T replaced = tailNode.data;
        tailNode.data = newData;

//        Node temp = new Node(newData);
//        temp.next = tailNode.next;
//        temp.prev = tailNode.prev;
//        tailNode = temp;
        return replaced;
    }

    @Override
    public boolean contains(T data) {
        if (isEmpty()) {
            return false;
        }
        Node left = headNode;
        Node right = tailNode;
        while (left != null && right != null && left.next != right && left != right) {
            if (Objects.equals(data, left.data)) {
                return true;
            }
            if (Objects.equals(data, right.data)) {
                return true;
            }
            left = left.next;
            right = right.prev;
        }
        if (left != null && left.data.equals(data)) {
            return true;
        }
        return false;
    }

    @Override
    public T updateNodeByIndex(int index, T newData) {
//         if (index < 0) {
//            System.out.println("Invalid index!");
//            return false;
//        }else{
//             
//         }
//
//        Node temp = headNode;
//        int count = 0;
//
//        while (temp != null) {
//            if (count == index) { 
//                temp.data = newData;
//                return true;
//            }
//            temp = temp.next;
//            count++;
//        }
//
//        System.out.println("Index out of range!");
//        return false; // Index out of range
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }

        Node node = getNode(index);
        T replaced = node.data;
        node.data = newData;

//        Node temp = new Node(newData);
//        temp.next = node.next;
//        temp.prev = node.prev;
//        node = temp;
        return replaced;
    }

    @Override
    public int getSize() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0; // head == null
    }

    @Override
    public void clear() {
        headNode = null;
        tailNode = null;
        length = 0;
    }

    @Override
    public int indexOf(T data) {
        Node currentNode = headNode;
        int index = 0;
        while (currentNode != null) {
            // object equal will prevent null pointer exception (return false instead)
            if (Objects.equals(data, currentNode.data)) {
                return index;
            }
            currentNode = currentNode.next;
            index++;
        }
        return -1;
    }

    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = headNode;

        while (currentNode != null) {
            outputStr += currentNode.data + "\n";
            currentNode = currentNode.next;
        }

        return outputStr;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements Iterator<T> {

        private Node current = headNode;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list.");
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    @Override
    public void mergeSort() {
        // if list is empty, no need to sort
        if (isEmpty()) {
            return;
        }

        headNode = mergeSortHelper(headNode); // merge sort starting from head node
        tailNode = headNode;  

        // traverse to end of the list to find tail node
        if (tailNode != null) {
            while (tailNode.next != null) {
                tailNode = tailNode.next;
            }
        }
    }

    // perform recursive merge sort on dll
    private Node mergeSortHelper(Node headNode) {
        // if list is empty or has one node only, already sorted
        if (headNode == null || headNode.next == null) {
            return headNode;
        }

        Node mid = getMiddle(headNode);  // find midpoint
        Node nxtToMid = mid.next;  // split into half
        mid.next = null;  // disconnec the 2 half

        if (nxtToMid != null) {
            nxtToMid.prev = null;  // disconnect the second half prev pointer
        }

        // recursive sorting for 2 half
        Node left = mergeSortHelper(headNode);
        Node right = mergeSortHelper(nxtToMid);

        // merge 2 sorted half 
        return merge(left, right);
    }

    // find the middle node of the list
    private Node getMiddle(Node headNode) {
        if (headNode == null) {
            return null;
        }

        Node slow = headNode;   // move one step at a time
        Node fast = headNode.next;  // move 2 steps at a time

        // both pointers will move until fast reaches the end
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; // this is the mid point
    }

    // merge 2 sorted lists
    private Node merge(Node left, Node right) {
        // use dummy node to help merging
        Node dummy = new Node(null);
        Node current = dummy;

        // merge 2 lists while both have remaining nodes
        while (left != null && right != null) {
            // compare data of left and right nodes to choose which node to append next
            if (left.data.compareTo(right.data) <= 0) {
                current.next = left;
                left.prev = current;
                left = left.next;
            } else {
                current.next = right;
                right.prev = current;
                right = right.next;
            }

            current = current.next;
        }

        // append if still have remaining nodes in either list
        if (left != null) {
            current.next = left;
            left.prev = current;
        } else {
            current.next = right;

            if (right != null) {
                right.prev = current;
            }
        }

        // merged list starts at dummy next (skip dummy node)
        Node mergeHead = dummy.next;

        // set the backward link of the head node to null
        if (mergeHead != null) {
            mergeHead.prev = null;
        }

        return mergeHead;
    }

    private class Node {

        private T data;
        private Node next;
        private Node prev;

        private Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
}
