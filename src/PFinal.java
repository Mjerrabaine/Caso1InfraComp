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

    public PFinal(int id, Buffer bufferFinal, int productos) {
        this.bufferFinal = bufferFinal;
        this.id = id;
        this.productos = productos;
        this.etapa = 4;

    }

    public void imprimirMensaje() {
        int productosImpresos = 0;
        while (productosImpresos <= this.productos) {
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
