/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class Principal {

    public static void main(String[] args) {
        
        
        Buffer buffer1 = new Buffer(6);
        Buffer buffer2 = new Buffer(6);
        Buffer buffer3 = new Buffer(6);
        Buffer bufferfinal = new Buffer();
        

        PAzul pA1 = new PAzul(1, buffer1, 2);
        PAzul pA2 = new PAzul(2, buffer1, 4);
        PNaranja pN1 = new PNaranja(3, buffer1);
        
        PAzul pA3 = new PAzul(1, buffer1, 2);
        PAzul pA4 = new PAzul(2, buffer1, 4);
        PNaranja pN2 = new PNaranja(3, buffer1);

        PAzul pA5 = new PAzul(1, buffer1, 2);
        PAzul pA6 = new PAzul(2, buffer1, 4);
        PNaranja pN3 = new PNaranja(3, buffer1);
        
        PFinal f1 = new PFinal(1);


        pA1.start();
        pA2.start();
        pA3.start();
        pA4.start();
        pA5.start(); 
        pA6.start();

        pN1.start();
        pN2.start();
        pN3.start();        

    }

}
