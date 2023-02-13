/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class PAzul extends Thread{

    private int id;
    private Buffer buffer;
    private int times;

    public PAzul(int id, Buffer buffer, int times) {
        this.buffer = buffer;
        this.id = id;
        this.times = times;
    }

    private void enviarMensaje(int i) {
        this.buffer.insertarMensaje(
                "El  thread productor :" + this.id + "saluda con el mensaje"
                + i + "de" + this.times + "mensajes"
        );
        
        
    }

    public void run() {
        for(int i=0; i<this.times; i++){
            this.enviarMensaje(i);
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
