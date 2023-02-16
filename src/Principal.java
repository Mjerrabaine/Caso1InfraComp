
import java.util.LinkedList;
import java.util.Scanner;

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
        System.out.println("Bienvenido a la planta de produccion");
        var scanner  = new Scanner(System.in);
        System.out.println("Por favor ingrese el numero de productos  a producir:");
        int numProductos= Integer.parseInt(scanner.nextLine());
        System.out.println("Por favor ingrese el tamaño de los buffers");
        int tamBuffers= Integer.parseInt(scanner.nextLine());
        System.out.println("Por favor ingrese el numero de procesos por etapa");
        int numProcesos= Integer.parseInt(scanner.nextLine());

        Buffer buffer1 = new Buffer(tamBuffers);
        Buffer buffer2 = new Buffer(tamBuffers);
        Buffer buffer3 = new Buffer(tamBuffers);
        Buffer bufferfinal = new Buffer();
        
        //numero de procesos por etapa
        int numProcesoA = numProcesos-1;
        int productosAProducir = numProductos%numProcesos;
        //lleva la cuenta de los ids
        int contadorId = 0;
        
        //Etapa 1
        LinkedList lista1 = new LinkedList();
        var i = 0;
        while(i<numProcesoA){
            PAzul pAzul = new PAzul(contadorId, buffer1,productosAProducir);
            String concat = "PAzul" + i;
            pAzul.cambiarNombre(concat);
            lista1.add(pAzul);
            contadorId++;
            pAzul.start();
        }
        
        PNaranja pN1 = new PNaranja(1, buffer1,1);
        pN1.start();
        
        //Etapa 2
        LinkedList lista2 = new LinkedList();
        i = 0;
        while(i<numProcesoA){
            PAzul pAzul = new PAzul(contadorId, buffer1,productosAProducir);
            String concat = "PAzul" + i;
            pAzul.cambiarNombre(concat);
            lista2.add(pAzul);
            contadorId++;
            pAzul.start();
        }
        
        PNaranja pN2 = new PNaranja(2, buffer1,1);
        pN2.start();

        //Etapa 3
        LinkedList lista3 = new LinkedList();
        i = 0;
        while(i<numProcesoA){
            PAzul pAzul = new PAzul(contadorId, buffer1,productosAProducir);
            String concat = "PAzul" + i;
            pAzul.cambiarNombre(concat);
            lista2.add(pAzul);
            contadorId++;
            pAzul.start();
        }
        
        PNaranja pN3 = new PNaranja(3, buffer1,1);
        pN3.start();
        
        
        PFinal pFinal = new PFinal(1,buffer3,bufferfinal, productosAProducir);       

    }

}
