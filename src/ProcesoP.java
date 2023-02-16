/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */

public abstract class ProcesoP extends Thread {
    private String nombre;
    
    public void cambiarNombre(String s){
        this.nombre = s;
    }
}
