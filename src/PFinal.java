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
        System.out.println("num prod buff 3: " +this.bufferFinal.getBuffer().get(0));
        for(int i = 0; i<bufferFinal.getBuffer().size();i++) {
        	System.out.println("Producto en buffer final" + bufferFinal.getBuffer().get(i)+ "\n");
        }
        System.out.println("productos*procesos:"+this.procesos*this.productos);
        System.out.println("productos en el buffer:"+bufferFinal.getBuffer().size());
        while (productosImpresos < (this.productos*this.procesos)) {
        	//System.out.println("entre al while de imprimir mensaje");
        	
            if (bufferFinal.getBuffer().size() > 0) {
            	System.out.println("entre al if de imprimir mensaje");
                Producto producto = bufferFinal.getBuffer().get(bufferFinal.getBuffer().size() - 1);
                System.out.println("El producto " + producto.getIdProducto() + " sale de produccion\n");
                bufferFinal.getBuffer().remove(bufferFinal.getBuffer().size() - 1);
                productosImpresos++;
            }

        }
    }

    public void run() {
        this.imprimirMensaje();
    }
}
