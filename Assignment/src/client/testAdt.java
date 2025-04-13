/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;
import adt.SortedDoublyLinkedList;

import adt.SortedListInterface;


/**
 *
 * @author Acer
 */
public class testAdt {

    public static void main(String[] args) {
        SortedListInterface<Integer> numbersList = new SortedDoublyLinkedList<>();
        numbersList.clear();
        numbersList.addAtFront(40);
        numbersList.addAtFront(30);
        numbersList.addAtFront(20);

        numbersList.addAtBack(70);
        numbersList.addAtBack(50);
        numbersList.addAtBack(60);

        numbersList.contains(30);
        System.out.println(numbersList.contains(30));
       
        numbersList.viewAllForward();
        //numbersList.viewAllBackward();
        
        System.out.println("\n");
        numbersList.mergeSort();
        numbersList.viewAllForward();

    }
}
