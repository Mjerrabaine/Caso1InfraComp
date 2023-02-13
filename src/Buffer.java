
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class Buffer {
    //lista de mensajes
    private List<String> buffer;
   //capacidad del buffer
    private int size;
    
    public Buffer(int size){
    this.size = size;
    this.buffer = new LinkedList<String>();
    }
    
    public Buffer(){
    this.buffer = new LinkedList<String>();
    }
    
    public synchronized boolean hayMensajes(){
        return this.buffer.size() >0;
    }
    
    public synchronized void insertarMensaje(String mensaje){
        while(this.buffer.size() ==this.size){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //uardar mensaje
        this.buffer.add(mensaje);
        
        //norificar a los consumidores
        notify();
    }
    
    public synchronized String obtenerMensaje(){
        while(this.buffer.size() ==0){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //retirar el mensaje
        String mensaje= this.buffer.remove(0);
        notify();
        
        //Entregar
        return mensaje;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
