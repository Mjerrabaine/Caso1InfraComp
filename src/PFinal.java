import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class PFinal extends Thread {

    private int id;
    private Buffer bufferFinal;
    private int productos;
    private int etapa;
    private int numProcesos;

    public PFinal(int id, Buffer bufferFinal, int productos, int numProcesos) {
        this.bufferFinal = bufferFinal;
        this.id = id;
        this.productos = productos;
        this.etapa = 4;
        this.numProcesos = numProcesos;

    }


    public void imprimirMensaje() {
        int productosImpresos = 0;
        int posicion=0;
        List<Producto> listafinal=bufferFinal.getBuffer();
        Collections.sort(listafinal, Comparator.comparing(Producto::getIdProducto));//para ordenar la lista
        System.out.println("la lista"+listafinal);
        while (productosImpresos < this.productos*this.numProcesos) {
            if (listafinal.size() > 0) {
                Producto producto = listafinal.get(posicion);
                System.out.println("El producto " + producto.getIdProducto() + " sale de produccion");
                listafinal.remove(posicion);
                productosImpresos++;
            }

        }
    }

    public void run() {
        this.imprimirMensaje();
    }
}
