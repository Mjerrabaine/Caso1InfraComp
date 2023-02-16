
import com.sun.tools.javac.Main;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class PNaranja extends ProcesoP{
    private int id;
   private Buffer bufferInicial;
    private Buffer bufferIn;
    private Buffer bufferOut;
    private int num_productos;
    private final boolean COLOR = true;
    private int etapa;
    
    //Constructor para los threads de la etapa 1, solo necesitan 1 buffer
    public PNaranja(int id, Buffer buffer,int num_productos, int etapa) {
        this.etapa= etapa;
        this.bufferInicial = buffer;
        this.id = id;
        this.num_productos=num_productos;
    }
    
    public PNaranja(int id, Buffer bufferIn,Buffer bufferOut,int num_productos, int etapa) {
        this.etapa= etapa;
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
    
    @Override
    public boolean getColor(){
        return this.COLOR;
    }
    
    public void run(){
        
        if(this.etapa==1){
            this.CrearProductos(this.num_productos, this.COLOR);
        }
    	
    	
    	
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