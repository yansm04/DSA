/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;
import adt.DoublyLinkedList;
import adt.ListInterface;


/**
 *
 * @author Acer
 */
public class testAdt {
    
    public static void main(String[] args) {
        ListInterface<Integer> numbersList = new DoublyLinkedList<>();
        numbersList.clear();
        numbersList.addAtFront(40);
        numbersList.addAtFront(30);
        numbersList.addAtFront(20);
        
        numbersList.addAtBack(50);
        numbersList.addAtBack(60);
        numbersList.addAtBack(70);
        numbersList.viewAllForward();
        System.out.println("");
        numbersList.viewAllBackward();
        
        
    }
}
