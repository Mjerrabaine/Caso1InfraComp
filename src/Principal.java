
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Flow.Subscriber;

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
        System.out.println("Por favor ingrese el numero de productos a producir por proceso:");
        int numProductos= Integer.parseInt(scanner.nextLine());
        System.out.println("Por favor ingrese el tamaño de los buffers");
        int tamBuffers= Integer.parseInt(scanner.nextLine());
        System.out.println("Por favor ingrese el numero de procesos por etapa");
        int numProcesos= Integer.parseInt(scanner.nextLine());
        
        scanner.close();
        
        Buffer buffer1 = new Buffer(tamBuffers,1);
        Buffer buffer2 = new Buffer(tamBuffers,2);        
        Buffer bufferfinal = new Buffer(3);
        
        //numero de procesos por etapa
        int numProcesoA = numProcesos-1;
        int productosAProducir = numProductos;
        //lleva la cuenta de los ids de los procesos. Empieza en 1
        int contadorId = 1;
        
        //Etapa 1
        LinkedList<PAzul> lista1 = new LinkedList<PAzul>();
        var i = 0;
        while(i<numProcesoA){
            PAzul pAzul = new PAzul(contadorId, buffer1,productosAProducir,1);            
            String concat = "PAzul" + i;
            pAzul.cambiarNombre(concat);
            lista1.add(pAzul);
            contadorId++;
            pAzul.start();
            i++;
        }
        System.out.println("Procesos Azules en etapa 1 " + lista1);

        PNaranja pN1 = new PNaranja(1, buffer1,productosAProducir,1);
        pN1.start();

        System.out.println("Proceso Naranja en etapa 1 " + pN1);
            
        
        //Etapa 2
        LinkedList<PAzul> lista2 = new LinkedList<PAzul>();
        i = 0;
        while(i<numProcesoA){
            PAzul pAzul = new PAzul(contadorId, buffer1,buffer2, productosAProducir,2);
            String concat = "PAzul" + i;
            pAzul.cambiarNombre(concat);
            lista2.add(pAzul);
            contadorId++;
            pAzul.start();
            i++;
        }
        System.out.println("Procesos Azules en etapa 2" + lista2);

        PNaranja pN2 = new PNaranja(2, buffer1,buffer2,productosAProducir,2);
        pN2.start();
        System.out.println("Proceso Naranja en etapa 2" + pN2);
                
        
        //Etapa 3
        LinkedList<PAzul> lista3 = new LinkedList<PAzul>();
        i = 0;
        while(i<numProcesoA){
            PAzul pAzul = new PAzul(contadorId, buffer2,bufferfinal,productosAProducir,3);
            String concat = "PAzul" + i;
            pAzul.cambiarNombre(concat);
            lista3.add(pAzul);
            contadorId++;
            pAzul.start();
            i++;
        }
        System.out.println("Procesos Azules en etapa 3" + lista3);

        PNaranja pN3 = new PNaranja(3, buffer2,bufferfinal ,productosAProducir,3);
        pN3.start();
        System.out.println("Proceso Naranja en etapa 3" + pN3);
        
        
        //Etapa Final

        System.out.println("Numero de productos que deberian salir de produccion: " + productosAProducir*numProductos);

        //Numero de productos en el buffer final
        System.out.println("Numero de productos en el buffer final: " + bufferfinal.getBuffer().size());

       if (bufferfinal.getBuffer().size() == productosAProducir*numProcesos)
            System.out.println("La produccion fue exitosa");

        PFinal pFinal = new PFinal(1,bufferfinal, productosAProducir*numProcesos,numProcesos);          
        System.out.println("Proceso Final llama al start ");
        pFinal.start();
        System.out.println("Proceso Final esta vivo? " + pFinal.isAlive());
                
    }
    
}