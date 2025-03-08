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
    public T getData();
    public void removeData();
    public T viewData();
    public boolean update();
    public int getSize();
    public boolean isEmpty();
    
    
    public void clear();
}
