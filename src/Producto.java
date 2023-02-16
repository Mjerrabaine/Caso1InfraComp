/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class Producto {

    private String cadena;
    private boolean color;
    private static int idProducto;
    private String nombre;

    public Producto(int idProducto, String cadena, boolean color) {
        this.cadena = cadena;
        this.color = color;
        this.idProducto = idProducto;
    }

    public void cambiarNombre(String s) {
        this.nombre = s;
    }
}
