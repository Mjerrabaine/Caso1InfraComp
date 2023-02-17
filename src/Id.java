/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class Id {
    private int id = 0;
    public synchronized int darId(){
        System.out.println("id: "+id);
        return id++;
    }        
         
}
