
import java.util.ArrayList;

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

    //Constructor para los threads de la etapa 1, solo necesitan 1 buffer
    public PNaranja(int id, Buffer buffer, int num_productos, int etapa) {
        this.etapa = etapa;
        this.bufferInicial = buffer;
        this.id = id;
        this.num_productos = num_productos;
    }

    public PNaranja(int id, Buffer bufferIn, Buffer bufferOut, int num_productos, int etapa) {
        this.etapa = etapa;
        this.bufferIn = bufferIn;
        this.bufferOut = bufferOut;
        this.id = id;
        this.num_productos = num_productos;
    }

    public int getPId() {
        return id;
    }

    public void imprimirMensaje(String mensaje) {
        //mostrar el Mensaje que obtuvimos
        String prot = "El consumidor %d recupero el mensaje %s: ";// darle formato a las variables
        System.out.println(String.format(prot, this.id, mensaje));
    }

    @Override
    public boolean getColor() {
        return this.COLOR;
    }

    public void run() {

        if (this.etapa == 1) {
            System.out.println("PNaranja Etapa 1");
            ArrayList<Producto> arregloProductos = this.CrearProductos(this.num_productos, this.COLOR);
            System.out.println("Se crearon los productos naranjas + " + arregloProductos);

            for (int i = 0; i < arregloProductos.size(); i++) {            	
                Producto producto1 = arregloProductos.get(i);

                this.bufferInicial.insertarMensaje(this, producto1);
                System.out.println("Desde PNaranja run() El producto "+producto1.getIdProducto()+" de color: "+((producto1.isColor()) ? " Naranja ": " Azul ")+ "Se confirma que se inserto en el buffer de la etapa 1");            
            }
        } else {//buffer 2 y 3 la 3 es solo final
            System.out.println("PNaranja Etapa 2 o 3");
            for (int i = 0; i < num_productos; i++) {
                Producto producto = this.bufferIn.obtenerMensaje(this);   
                System.out.println("Desde PNaranja run() El producto "+producto.getIdProducto()+" de color: "+((producto.isColor()) ? " Naranja ": " Azul ")+ "Se obtiene desde el buffer " + bufferIn.getBufferId());
                                               
                this.bufferOut.insertarMensaje(this, producto);                
                System.out.println("Desde PNaranja run() El producto "+producto.getIdProducto()+" de color: "+((producto.isColor()) ? " Naranja ": " Azul ")+ "Se inserta en el buffer " + bufferIn.getBufferId());
            }

        }
    }

    @Override
    public int getEtapa() {
        return etapa;
    }
    
}
