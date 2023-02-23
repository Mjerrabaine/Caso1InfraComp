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
    int id;

    // lista de mensajes
    private List<Producto> buffer;
    // capacidad del buffer
    private int capacidad; // capacidad disponible
    private int tamanioBuffer;
    private int contA = 0;
    private int contN = 0;

    public synchronized List<Producto> getBuffer() {
        return buffer;
    }

    public Buffer(int size, int id) {
        this.tamanioBuffer = size;
        this.capacidad = size;
        this.buffer = new LinkedList<Producto>();
        this.id = id;
    }

    public Buffer(int id) {
        this.id = id;
        this.tamanioBuffer = 999999;
        this.buffer = new LinkedList<Producto>();
        this.capacidad = 999999;
    }

    public void insertarMensaje(ProcesoP proceso, Producto producto) {
        if (proceso.isColor() == true) {// true naranja

            while (this.getCapacidad() == 0) {
                proceso.yield();
            }
            synchronized (objNaranja) {
                this.getBuffer().add(producto);
                this.contN++;
                this.setMeCap(); // Syncronized objeto capacidad
                // this.getBuffer().add(producto);
/*                 producto.ModificarCadena(
                        "El producto:" + producto.getIdProducto() + " asociado al proceso: " + proceso.getPId() +
                                " de color: " + proceso.isColor() + " del proceso: " + proceso.getEtapa() +
                                " ha ingresado al buffer:"); */
                String colT="";                
                if(producto.isColor()){
                    colT="Naranja";
                }
                System.out
                        .print("El producto con id:" + producto.getIdProducto() + " asociado al proceso con id : " + proceso.getPId() +
                                " de color: " + colT + " de la etapa: " + proceso.getEtapa() +
                                " ha ingresado al buffer:" + this.id + "\n");
            }
        }

        else {// false azul
            synchronized (objAzul) {

                while (getCapacidad() == 0) {
                    try {
                        objAzul.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                this.getBuffer().add(producto);
                this.contA++;
                this.setMeCap();
                /*
                 * producto.ModificarCadena("El producto:"+producto.getIdProducto()
                 * +" asociado al proceso: "+proceso.getPId()+
                 * " de color: "+proceso.isColor()+" del proceso: "+proceso.getEtapa()+
                 * " ha ingresado al buffer:");
                 */
                String colT="";                
                if(!producto.isColor()){
                    colT="Azul";
                }
                System.out.println(
                        "El producto con id:" + producto.getIdProducto() + " asociado al proceso con id: " + proceso.getPId() +
                                " de color: " + colT + " de la etapa: " + proceso.getEtapa() +
                                " ha ingresado al buffer:" + this.id + "\n");

                objAzul.notifyAll();
            }
        }
    }

    public Producto obtenerMensaje(ProcesoP proceso) {
        Producto productoElegido = null;
        if (proceso.isColor() == true) {// true es naranja

            while (this.getCapacidad() == this.getTamanioBuffer() || this.getContN() == 0
                    || this.buffer.get(0) == null) {
                proceso.yield();
            }

            synchronized (objNaranja) {
                Boolean encontrado = false;
                int i = 0;

                while (i < this.getBuffer().size() && encontrado == false && this.getBuffer().get(i) != null
                        && this.getBuffer().get(i) != null) {
                    Producto producto = this.buffer.get(i);
                    if (producto.isColor() == true) {
                        encontrado = true;
                        productoElegido = producto;
                        try {
                            // Random number between 50 and 500
                            int random = (int) (Math.random() * 500) + 50;
                            System.out.println("El producto con id: " + productoElegido.getIdProducto()
                                    + "del proceso con id " + proceso.getPId() + " esta en transformacion y debe esperar "+ random + " segundos");
                            proceso.sleep(random);

                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        if(proceso.getEtapa()==2){
                            productoElegido.ModificarCadena("Producto en proceso");
                            System.out.println("El producto con id " + productoElegido.getIdProducto() + " cambio su estado a " + productoElegido.getCadena());
                        }
                        else if(proceso.getEtapa()==3){
                            productoElegido.ModificarCadena("Producto Procesado");
                            System.out.println("El producto con id " + productoElegido.getIdProducto() + " cambio su estado a " + productoElegido.getCadena());
                        }
                        this.getBuffer().remove(i);
                        this.setMaCap();
                        this.contN--;
/*                         productoElegido.ModificarCadena("El producto:" + productoElegido.getIdProducto()
                                + " asociado al proceso: " + proceso.getPId() +
                                " de color: " + proceso.isColor() + " del proceso: " + proceso.getEtapa() +
                                " ha ingresado al buffer:"); */
                                String colT="";                
                                if(producto.isColor()){
                                    colT="Naranja";
                                }
                        System.out.print("El producto con id:" + productoElegido.getIdProducto() + " asociado al proceso con id: "
                                + proceso.getPId() +
                                " de color: " + colT + " del proceso: " + proceso.getEtapa() +
                                " ha salio del buffer:" + this.id + "\n");
                    }
                }

            }
        } else {// false el color es azul
            synchronized (objAzul) {
                while (this.getCapacidad() == getTamanioBuffer() || contA == 0) {
                    try {
                        objAzul.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Boolean encontrado = false;
                while (!encontrado) {
                    int i = 0;
                    while (i < this.getBuffer().size() && encontrado == false) {
                        Producto producto = this.buffer.get(i);
                        if (producto.isColor() == false) {
                            encontrado = true;
                            productoElegido = producto;
                            this.getBuffer().remove(i);
                            this.setMaCap();
                            this.contA--;
                            /*
                             * productoElegido.ModificarCadena("El producto:"+productoElegido.getIdProducto(
                             * )+" asociado al proceso: "+proceso.getPId()+
                             * " de color: "+proceso.isColor()+" del proceso: "+proceso.getEtapa()+
                             * " ha ingresado al buffer:");
                             */
                            String colT="";                
                            if(!producto.isColor()){
                                colT="Azul";
                            }
                            System.out.println("El producto:" + productoElegido.getIdProducto()
                                    + " asociado al proceso: " + proceso.getPId() +
                                    " de color: " + colT + " del proceso: " + proceso.getEtapa() +
                                    " ha salio del buffer:" + this.id);
                            objAzul.notify();
                        }
                    }
                    if (!encontrado) {
                        try {
                            objAzul.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        return productoElegido;
    }

    public synchronized void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized int getCapacidad() {
        return capacidad;
    }

    public synchronized int getContN() {
        return contN;
    }

    public synchronized int setMeCap() {
        return --this.capacidad;
    }

    public synchronized int setMaCap() {
        return ++this.capacidad;
    }

    public synchronized int getTamanioBuffer() {
        return tamanioBuffer;
    }

}
