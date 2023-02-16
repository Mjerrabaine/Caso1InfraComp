
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author usuario
 */
public class Buffer {

    Object objNaranja = new Object();
    Object objAzul = new Object();
    //lista de mensajes
    private List<Producto> buffer;
    //capacidad del buffer
    private int capacidad;
    private Id id;

    public Buffer(int size) {
        this.capacidad = size;
        this.buffer = new LinkedList<Producto>();
    }

    public Buffer() {
        this.buffer = new LinkedList<Producto>();
    }

    public synchronized boolean hayMensajes() {
        return this.buffer.size() > 0;
    }
    
    public synchronized ArrayList<Producto> CrearProductos(int numProduct, boolean color){
        Producto[] arregloProductos = new Producto[numProduct];
        
        for(int i = 0; i<numProduct; i++){
            Producto producto = new Producto(id.darId(), "Producto Sin Procesar", color);
            String concat = "producto" + i;
            producto.cambiarNombre(concat);
            arregloProductos[i]= producto;
        }
        
        
    }

    public synchronized void insertarMensaje(String mensaje) {
        while (this.buffer.size() == this.capacidad) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //uardar mensaje
        this.buffer.add(mensaje);

        //norificar a los consumidores
        notify();
    }

    public Producto obtenerMensaje(boolean color, ProcesoP proceso) {
        Producto productoElegido = null;
        if (color == true) {//es naranja
            synchronized (objNaranja) {
                while (this.buffer.size() == 0) {
                    try {
                        proceso.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Boolean encontrado = false;
                int i = 0;
                while (i < this.buffer.size() && encontrado == false) {
                    Producto producto = this.buffer.get(i);
                    if (producto.color == true) {
                        productoElegido = producto;
                        this.buffer.remove(i);
                    }
                }
            }
        } else {// el color es azul
            synchronized (objAzul) {
                while (this.buffer.size() == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Boolean encontrado = false;
                int i = 0;
                while (i < this.buffer.size() && encontrado == false) {
                    Producto producto = this.buffer.get(i);
                    if (producto.color == false) {
                        productoElegido = producto;
                        this.buffer.remove(i);
                    }
                }
            }

        }
        return productoElegido;
    }
    
    

}

