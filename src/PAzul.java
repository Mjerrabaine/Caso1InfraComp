
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
    private final boolean COLOR = false; //COLOR DE PROCESO AZUL ES FALSO SIEMPRE
    private int etapa;
    
    //Constructor para los threads de la etapa 1, solo necesitan 1 buffer
    public PAzul(int id, Buffer buffer, int num_productos, int etapa) {
        this.etapa = etapa;
        this.bufferInicial = buffer;
        this.id = id;
        this.num_productos = num_productos;
    }

    public int getPId() {
        return id;
    }
    
    public PAzul(int id, Buffer bufferIn, Buffer bufferOut, int num_productos, int etapa) {
        this.etapa = etapa;
        this.bufferIn = bufferIn;
        this.bufferOut = bufferOut;
        this.id = id;
        this.num_productos = num_productos;
    }
    
    //    private void enviarMensaje(int i) {
        //        this.buffer.insertarMensaje(
        //                "El  thread productor :" + this.id + "saluda con el mensaje"
        //                + i + "de" + this.times + "mensajes"
        //        );
        
        //    }
        
        @Override
        public boolean getColor() {
            return this.COLOR;
        }
        @Override
        public int getEtapa() {
            return etapa;
        }
        
        public void run() {
            //        for(int i=0; i<this.times; i++){
                //            this.enviarMensaje(i);
                //        }
                //threads etapa 1
                if (this.etapa == 1) {
                    System.out.println("PAzul Etapa 1");
                    ArrayList<Producto> arregloProductos = this.CrearProductos(this.num_productos, this.COLOR);
                    System.out.println("Se crearon los productos azules + " + arregloProductos);

                    for(int i = 0; i<arregloProductos.size();i++){
                        Producto producto1 = arregloProductos.get(i);                        
                        this.bufferInicial.insertarMensaje(this, producto1);
                        System.out.println("Desde PAzul run() El producto "+producto1.getIdProducto()+" de color: "+((producto1.isColor()) ? " Naranja ": " Azul ")+ "Se confirma que se inserto en el buffer de la etapa 1");
                    }
                }
                else{//buffer 2 y 3 la 3 es solo final
                    System.out.println("PAzul Etapa 2 o 3");
                    for(int i = 0; i<num_productos;i++){
                        Producto producto=this.bufferIn.obtenerMensaje(this);
                        System.out.println("Desde PAzul run() El producto "+producto.getIdProducto()+" de color: "+((producto.isColor()) ? " Naranja ": " Azul ")+ "Se obtiene desde el buffer " + bufferIn.getBufferId());

                        this.bufferOut.insertarMensaje(this, producto);
                        System.out.println("Desde PAzul run() El producto "+producto.getIdProducto()+" de color: "+((producto.isColor()) ? " Naranja ": " Azul ")+ "Se inserta en el buffer " + bufferIn.getBufferId());
                    }
                    
                }
                
                
                
            }
        }
        