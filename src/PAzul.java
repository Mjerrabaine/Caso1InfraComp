
import java.security.Principal;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author usuario
 */
public class PAzul extends ProcesoP {

    private int id;
    private Buffer bufferInicial;
    private Buffer bufferIn;
    private Buffer bufferOut;
    private int num_productos;
    private final boolean COLOR = false;
    private int etapa;

    //Constructor para los threads de la etapa 1, solo necesitan 1 buffer
    public PAzul(int id, Buffer buffer, int num_productos, int etapa) {
        this.etapa = etapa;
        this.bufferInicial = buffer;
        this.id = id;
        this.num_productos = num_productos;
    }

    public PAzul(int id, Buffer bufferIn, Buffer bufferOut, int num_productos, int etapa) {
        this.etapa = etapa;
        this.bufferIn = bufferIn;
        this.bufferOut = bufferOut;
        this.id = id;
        this.num_productos = num_productos;
    }

    private void enviarMensaje(int i) {
        this.buffer.insertarMensaje(
                "El  thread productor :" + this.id + "saluda con el mensaje"
                + i + "de" + this.times + "mensajes"
        );

    }

    @Override
    public boolean getColor() {
        return this.COLOR;
    }

    public void run() {
//        for(int i=0; i<this.times; i++){
//            this.enviarMensaje(i);
//        }
        //threads etapa 1
        if (this.etapa == 1) {

            ArrayList<Producto> arregloProductos = this.CrearProductos(this.num_productos, this.COLOR);
            for(int i = 0; i<arregloProductos.size();i++){
                Producto producto1 = arregloProductos.get(i);
                this.bufferInicial.insertarMensaje(this, producto1);
            }
        }
        else if (){//etapa final
            
        }
        else{
        
        }

    }

}
