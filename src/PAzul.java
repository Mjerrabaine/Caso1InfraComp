import java.security.Principal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class PAzul extends ProcesoP{

    private int id;
    private Buffer bufferIn;
    private Buffer bufferOut;

    public PAzul(int id, Buffer bufferIn,Buffer bufferOut) {
        this.bufferIn = bufferIn;
        this.bufferOut = bufferOut;
        this.id = id;
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
