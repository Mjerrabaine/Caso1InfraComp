
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author usuario
 */
public class PNaranja extends ProcesoP {

    private int id;
    private Buffer bufferInicial;
    private Buffer bufferIn;
    private Buffer bufferOut;
    private int num_productos;
    private final boolean COLOR = true;
    private int etapa;
    private CyclicBarrier barrera;

    //Constructor para los threads de la etapa 1, solo necesitan 1 buffer
    public PNaranja(int id, Buffer buffer, int num_productos, int etapa, CyclicBarrier barrera) {
        this.etapa = etapa;
        this.bufferInicial = buffer;
        this.id = id;
        this.num_productos = num_productos;
        this.barrera= barrera;
    }

    public PNaranja(int id, Buffer bufferIn, Buffer bufferOut, int num_productos, int etapa, CyclicBarrier barrera) {
        this.etapa = etapa;
        this.bufferIn = bufferIn;
        this.bufferOut = bufferOut;
        this.id = id;
        this.num_productos = num_productos;
        this.barrera= barrera;
    }

    public int getPId() {
        return id;
    }

/*     public void imprimirMensaje(String mensaje) {
        //mostrar el Mensaje que obtuvimos
        String prot = "El consumidor %d recupero el mensaje %s: ";// darle formato a las variables
        System.out.println(String.format(prot, this.id, mensaje));
    } */

    @Override
    public boolean isColor() {
        return this.COLOR;
    }

    public void run() {

        if (this.etapa == 1) {
            ArrayList<Producto> arregloProductos = this.CrearProductos(this.num_productos, this.COLOR);
/*             System.out.print("el numero de productos creados es:"+arregloProductos.size()); */
//            System.out.print("id producto 0 de la lista: "+ arregloProductos.get(0).getIdProducto()+"\n");
//            System.out.print("id producto 1 de la lista: "+ arregloProductos.get(1).getIdProducto()+"\n");
//            System.out.print("id producto 2 de la lista: "+ arregloProductos.get(2).getIdProducto()+"\n");
            for (int i = 0; i < arregloProductos.size(); i++) {
            	
                Producto producto1 = arregloProductos.get(i);
                System.out.print("id del producto desde el thread: "+producto1.getIdProducto()+"\n");
                this.bufferInicial.insertarMensaje(this, producto1);
            }
        } else {//buffer 2 y 3 la 3 es solo final
            for (int i = 0; i < num_productos; i++) {
                Producto producto = this.bufferIn.obtenerMensaje(this);
                this.bufferOut.insertarMensaje(this, producto);
            }
    
        }
        try {
            barrera.await();  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getEtapa() {
        return etapa;
    }

    
}
