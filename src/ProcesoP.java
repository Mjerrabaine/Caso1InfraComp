
import java.util.ArrayList;

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
    private static Id id = new Id();
    private ArrayList<Producto> productosVerificar = new ArrayList<Producto>();

    public void cambiarNombre(String s){
        this.nombre = s;
    }
    
    public ArrayList<Producto> CrearProductos(int numProduct, boolean color){
        ArrayList<Producto> arregloProductos = new ArrayList<Producto>(numProduct);
        for(int i = 0; i<numProduct; i++){
            Producto producto = new Producto(id.darId(), "Producto Sin Procesar", color);
            String concat = "producto" + i;
            producto.cambiarNombre(concat);
            arregloProductos.add(producto);
            productosVerificar.add(producto); //LINEA DE CODIGO TEMPORAL

            System.out.println("Se creo el producto: "+  "id: "+ producto.getIdProducto() + " y color: "+ producto.isColor()); 

        }
            //System.out.println("Arreglo de productos: "+ productosVerificar);

        //        System.out.print("id producto 0 de la lista: "+ arregloProductos.get(0).getIdProducto()+"\n");
        //        System.out.print("id producto 1 de la lista: "+ arregloProductos.get(1).getIdProducto()+"\n");
        //        System.out.print("id producto 2 de la lista: "+ arregloProductos.get(2).getIdProducto()+"\n");
        return arregloProductos;
        
    }
    public abstract  boolean getColor();
    public abstract int getEtapa();
    public abstract int getPId();
    
}