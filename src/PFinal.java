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
    private int procesos;

    public PFinal(int id, Buffer bufferFinal, int productos, int procesos) {
        this.bufferFinal = bufferFinal;
        this.id = id;
        this.productos = productos;
        this.etapa = 4;
        this.procesos= procesos;

    }

    public void imprimirMensaje() {
        int productosImpresos = 0;
        System.out.println("num prod buff 3" +this.productos);
        for(int i = 0; i<bufferFinal.getBuffer().size();i++) {
        	System.out.println("producto en final" + bufferFinal.getBuffer().get(i));
        }
        while (productosImpresos < this.productos*this.procesos) {
            if (bufferFinal.getBuffer().size() > 0) {
                Producto producto = bufferFinal.getBuffer().get(bufferFinal.getBuffer().size() - 1);
                System.out.println("El producto " + producto.getIdProducto() + " sale de produccion");
                bufferFinal.getBuffer().remove(bufferFinal.getBuffer().size() - 1);
                productosImpresos++;
            }

        }
    }

    public void run() {
        this.imprimirMensaje();
    }
}
