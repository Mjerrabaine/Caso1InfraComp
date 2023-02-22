
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

/**
*
* @author usuario
*/
public class Principal {
    private static CyclicBarrier barrera;
    

    
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
        
        
        barrera=new CyclicBarrier((numProcesos*3)+1);
        //numero de procesos por etapa
        int numProcesoA = numProcesos-1;
        int productosAProducir = numProductos;
        //lleva la cuenta de los ids
        int contadorId = 0;
        
        //Etapa 1
        LinkedList<PAzul> lista1 = new LinkedList<PAzul>();
        var i = 0;
        while(i<numProcesoA){
            PAzul pAzul = new PAzul(contadorId, buffer1,productosAProducir,1,barrera); 
            String concat = "PAzul" + i;
            pAzul.cambiarNombre(concat);
            lista1.add(pAzul);
            contadorId++;
            pAzul.start();
            i++;
        }
        PNaranja pN1 = new PNaranja(1, buffer1,productosAProducir,1,barrera);
        pN1.start();
        
        //barrera1 = new CyclicBarrier((numProcesos*3)+1);
        
        //Etapa 2
        LinkedList<PAzul> lista2 = new LinkedList<PAzul>();
        i = 0;
        while(i<numProcesoA){
            PAzul pAzul = new PAzul(contadorId, buffer1,buffer2, productosAProducir,2,barrera);
            String concat = "PAzul" + i;
            pAzul.cambiarNombre(concat);
            lista2.add(pAzul);
            contadorId++;
            pAzul.start();
            i++;
        }
        
        PNaranja pN2 = new PNaranja(contadorId, buffer1,buffer2,productosAProducir,2,barrera);
        pN2.start();
        
        //barrera2 = new CyclicBarrier((numProcesos*3)+1);
        
        //Etapa 3
        LinkedList<PAzul> lista3 = new LinkedList<PAzul>();
        i = 0;
        while(i<numProcesoA){
            PAzul pAzul = new PAzul(contadorId, buffer2,bufferfinal,productosAProducir,3,barrera);
            String concat = "PAzul" + i;
            pAzul.cambiarNombre(concat);
            lista3.add(pAzul);
            contadorId++;
            pAzul.start();
            i++;
        }
        
        PNaranja pN3 = new PNaranja(3, buffer2,bufferfinal ,productosAProducir,3,barrera);
        pN3.start();
        try {
			barrera.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("PASE LA BARRERA");
        PFinal pFinal = new PFinal(1,bufferfinal, productosAProducir,numProcesos);  //CAMBIAR A QUE SEA BUFFER FINAL IMPRIMIR   
        pFinal.start();
        
    }
    
}