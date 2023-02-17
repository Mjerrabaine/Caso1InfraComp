import java.util.LinkedList;
import java.util.List;


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
    private int capacidad; //capacidad disponible
    private int tamanioBuffer;
    private int contA;
    private int contN;

    public List<Producto> getBuffer() {
        return buffer;
    }
    
    public Buffer(int size) {
        this.tamanioBuffer = size;
        this.capacidad = size;
        this.buffer = new LinkedList<Producto>();
    }
    
    public Buffer() {
        this.tamanioBuffer = 999999;
        this.buffer = new LinkedList<Producto>();
    }
    
    public synchronized boolean hayMensajes() {
        return this.buffer.size() > 0;
    }
    
    
    public synchronized void insertarMensaje(ProcesoP proceso, Producto producto) {        
        if (proceso.getColor() == true) {//true naranja 
            synchronized (objNaranja) {                    
                while (capacidad == 0) {
                    try {
                        proceso.sleep(500);//duda preguntar .yield()
                        //objCapacidad.sleep(500);
                        
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                this.contN++;
                this.capacidad--; //Syncronized objeto capacidad
                this.buffer.add(producto);
                producto.ModificarCadena("El producto:"+producto.getIdProducto()+" asociado al proceso: "+proceso.getId()+
                " de color: "+proceso.getColor()+" del proceso: "+proceso.getEtapa()+
                " ha ingresado al buffer:");                
            }
        }
            else {//true azul
                synchronized (objAzul) {
                    while (capacidad == 0) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    this.contA++;
                    this.capacidad--; //Syncronized objeto capacidad
                    this.buffer.add(producto);
                    notify();
                    producto.ModificarCadena("El producto:"+producto.getIdProducto()+" asociado al proceso: "+proceso.getId()+
                    " de color: "+proceso.getColor()+" del proceso: "+proceso.getEtapa()+
                    " ha ingresado al buffer:");
                }
            }
        }
        
        public Producto obtenerMensaje(ProcesoP proceso) {
            Producto productoElegido = null;
            if (proceso.getColor() == true) {//es naranja
                synchronized (objNaranja) {
                    while (this.capacidad == this.tamanioBuffer || contN == 0) {
                        try {
                            proceso.sleep(500);//duda preguntar yield()
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    Boolean encontrado = false;
                    int i = 0;
                    while (i < this.buffer.size() && encontrado == false) {
                        Producto producto = this.buffer.get(i);
                        if (producto.isColor() == true) {
                            encontrado = true;
                            productoElegido = producto;
                            this.buffer.remove(i);
                            this.capacidad++; //Syncronized objeto capacidad
                        }
                    }
                    productoElegido.ModificarCadena("El producto:"+productoElegido.getIdProducto()+" asociado al proceso: "+proceso.getId()+
                    " de color: "+proceso.getColor()+" del proceso: "+proceso.getEtapa()+
                    " ha ingresado al buffer:");
                }
            } else {// el color es azul
                synchronized (objAzul) {
                    while (this.capacidad == this.tamanioBuffer || contA == 0) {
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
                        if (producto.isColor() == false) {
                            encontrado = true;
                            productoElegido = producto;
                            this.buffer.remove(i);
                            this.capacidad++; //Syncronized objeto capacidad
                        }
                    }
                    productoElegido.ModificarCadena("El producto:"+productoElegido.getIdProducto()+" asociado al proceso: "+proceso.getId()+
                    " de color: "+proceso.getColor()+" del proceso: "+proceso.getEtapa()+
                    " ha ingresado al buffer:");
                }
                
            }
            return productoElegido;
        }
        
    }
    