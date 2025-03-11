/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author Acer
 */
public interface ListInterface <T>{
    
    public void addAtFront(T newData);
    public void addAtBack(T newData);
    public void addAtIndex(T newData, int index);
    
     public T removeFront();
    public T removeBack();
    public T removeByIndex(int index);
    public T viewData();
    public void viewAllForward();
    public void viewAllBackward();
    public T viewDataAtIndex(int position);
    public T updateFront(T data);
    public T updateBack(T data);
    public T contains(T data);
    public T updateNodeByIndex(int position, T data);
    
    public int getSize();
    public boolean isEmpty();
    
    
    public void clear();
}
