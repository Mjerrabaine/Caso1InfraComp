
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
    private Id id;

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
        }
        return arregloProductos;
        
    }
    public abstract  boolean getColor();
    public abstract int getEtapa();
    
}