
import com.sun.tools.javac.Main;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class PNaranja extends Thread{
    private int id;
    private Buffer bufferIn;
    private Buffer bufferOut;
    private int num_productos;

    public PNaranja(int id, Buffer bufferIn,Buffer bufferOut,int num_productos) {
        this.bufferIn = bufferIn;
        this.bufferOut = bufferOut;
        this.id = id;
        this.num_productos=num_productos;
    }
    
    public void imprimirMensaje(String mensaje){
        //mostrar el Mensaje que obtuvimos
        String prot = "El consumidor %d recupero el mensaje %s: ";// darle formato a las variables
        System.out.println(String.format(prot, this.id, mensaje));
    }
    public void run(){
    	
    	
    	
    	
        //se emplea una bandera para frenar el proceso
        
        while(!Main.isFinished()|| this.buffer.hayMensajes() ){//is finished?
            String mensaje = this.buffer.obtenerMensaje();
            if(mensaje == null){
                return;
            }
            //mostrar mensaje
            this.imprimirMensaje(mensaje);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
