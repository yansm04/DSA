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
    
    public void removeData();
    public T viewData();
    public void viewAllForward();
    public void viewAllBackward();
    public T viewDataAtIndex(int position);

    public boolean update();
    public int getSize();
    public boolean isEmpty();
    
    
    public void clear();
}
