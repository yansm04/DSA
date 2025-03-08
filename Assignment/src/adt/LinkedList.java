/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author Acer
 */
public class LinkedList<T> implements ListInterface<T> {

    private int length;
    private Node headNode;
    private Node tailNode;

    public LinkedList() {
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
    public void addAtBack(T newData){
        Node newNode = new Node(newData);
        if(isEmpty()){
            headNode = newNode;
            tailNode = headNode;
            
        }else{
            tailNode.next = newNode;
            newNode.prev = tailNode;
            tailNode = newNode;
            
        }
        length++;
    }

    @Override
    public T getData(int position) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T viewData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public void viewAllBackward(){
        Node temp = tailNode;
        while (temp != null) {
            System.out.print(temp.data + "-->");
            temp = temp.prev;
        }
    }

    @Override
    public boolean update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
