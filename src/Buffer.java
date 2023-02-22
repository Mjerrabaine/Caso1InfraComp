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
	
	//lista de mensajes
	private List<Producto> buffer;
	//capacidad del buffer
	private int capacidad; //capacidad disponible
	private int tamanioBuffer;
	private int contA;
	private int contN;
	
	public List<Producto> getBuffer() {
		return buffer;
	}
	
	public Buffer(int size,int id) {
		this.tamanioBuffer = size;
		this.capacidad = size;
		this.buffer = new LinkedList<Producto>();
		this.id=id;
	}
	
	public Buffer(int id) {        
		this.id=id;
		this.tamanioBuffer = 999999;
		this.buffer = new LinkedList<Producto>();
		this.capacidad=999999;
	}
	
	public synchronized boolean hayMensajes() {
		return this.buffer.size() > 0;
	}
	
	public int getBufferId() {
		return this.id;
	}
	
	
	public Producto obtenerMensaje(ProcesoP proceso) {
		System.out.println("Obtener mensaje");
		Producto productoElegido = null;
		// try {
			//     //Random number between 50 and 500
			//     int random = (int)(Math.random() * 500) + 50;
			//     proceso.sleep(random);
			
			// } catch (Exception e) {
				//     // TODO: handle exception
				// }
				
				if (proceso.getColor() == true) {//es naranja
					System.out.println("El producto es naranja ");
					while (this.capacidad == this.tamanioBuffer || contN == 0) {
						System.out.println("El buffer " + this.getBufferId()+ " esta vacio. Se hace yield");
						proceso.yield();
					}
					Boolean encontrado = false;
					int i = 0;
					while (i < this.buffer.size() && encontrado == false) {
						Producto producto = this.buffer.get(i);
						if (producto.isColor() == true) {
							encontrado = true;
							productoElegido = producto;
							System.out.println("Producto naranja con id: " + productoElegido.getIdProducto()+ "elegido para sacar del buffer: "+ this.id);
							this.buffer.remove(i);
							this.capacidad++; //Syncronized objeto capacidad
							this.contN--;
						}
					}
					
					productoElegido.ModificarCadena("El producto:"+productoElegido.getIdProducto()+" asociado al proceso: "+proceso.getPId()+
					" de color: "+((proceso.getColor()) ? " Naranja ": " Azul ") +" de la etapa: "+proceso.getEtapa()+
					" ha salido del buffer:"+this.id+"\n");    
					
					System.out.println("Se modifica cadena. El producto:"+productoElegido.getIdProducto()+" asociado al proceso: "+proceso.getPId()+
					" de color: "+((proceso.getColor()) ? " Naranja ": " Azul ") +" de la etapa: "+proceso.getEtapa()+
					" ha salido del buffer:"+this.id+"\n");
					
				} else {// el color es azul
					System.out.println("El producto es azul ");
					synchronized (objAzul) {
						//System.out.println("entre a obtener azul");
						while (this.capacidad == this.tamanioBuffer || contA == 0) {
							try {
								System.out.println("El buffer " + this.getBufferId()+ " esta vacio. Se hace wait");
								objAzul.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						Boolean encontrado = false;
						int i = 0;
						while (i < this.buffer.size() && encontrado == false) {
							Producto producto = this.buffer.get(i);
							if (producto.isColor() == false) {
								encontrado = true;
								productoElegido = producto;
								System.out.println("Producto Azul con id: " + productoElegido.getIdProducto()+ "elegido para sacar del buffer: "+ this.id);
								this.buffer.remove(i);
								this.capacidad++; //Syncronized objeto capacidad
								this.contA--;
							}
						}

						// System.out.println(" Se hace notify obtener mensaje");
						// objAzul.notify();
						
						productoElegido.ModificarCadena("El producto:"+productoElegido.getIdProducto()+" asociado al proceso: "+proceso.getPId()+
						" de color: "+((proceso.getColor()) ? " Naranja ": " Azul ") +" de la etapa: "+proceso.getEtapa()+
						" ha salido del buffer:"+this.id+"\n");    
						
						System.out.println("Se modifica cadena. El producto:"+productoElegido.getIdProducto()+" asociado al proceso: "+proceso.getPId()+
						" de color: "+((proceso.getColor()) ? " Naranja ": " Azul ") +" de la etapa: "+proceso.getEtapa()+
						" ha salido del buffer:"+this.id+"\n");
					}
					
				}
				return productoElegido;
			}
			
			
	public synchronized void insertarMensaje(ProcesoP proceso, Producto producto) {  
		System.out.println("Insertar mensaje");
		if (proceso.getColor() == true) {//true naranja 
			System.out.println("El producto es naranja ");
			while (capacidad == 0) {
				System.out.println("El buffer " + this.getBufferId()+ "esta lleno. Se hace yield");
				proceso.yield();
			}
					
			this.buffer.add(producto);
			//System.out.println("El producto:"+producto.getIdProducto()+" asociado al proceso: "+proceso.getPId()+" de color: "+((proceso.getColor()) ? " Naranja ": " Azul ") +" de la etapa: "+proceso.getEtapa()+" ha ingresado al buffer:"+this.id+"\n");
					
			this.contN++;
			this.capacidad--; //Syncronized objeto capacidad
					
			producto.ModificarCadena("El producto:"+producto.getIdProducto()+" asociado al proceso: "+proceso.getPId()+
			" de color: "+((proceso.getColor()) ? " Naranja ": " Azul ") +" de la etapa: "+proceso.getEtapa()+
			" ha ingresado al buffer:"+this.id+"\n");    
					
			System.out.println("Se modifica cadena. El producto:"+producto.getIdProducto()+" asociado al proceso: "+proceso.getPId()+
			" de color: "+((proceso.getColor()) ? " Naranja ": " Azul ") +" de la etapa: "+proceso.getEtapa()+
			" ha ingresado al buffer:"+this.id+"\n");
		}
				
		else {//true azul
			System.out.println("El producto es azul ");
			synchronized (objAzul) {
				while (capacidad == 0) {
					try {	
						System.out.println("El buffer " + this.getBufferId()+ " esta lleno. Se hace wait");
						objAzul.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				this.buffer.add(producto);
				//System.out.println("El producto:"+producto.getIdProducto()+" asociado al proceso: "+proceso.getPId()+" de color: "+((proceso.getColor()) ? " Naranja ": " Azul ") +" de la etapa: "+proceso.getEtapa()+" ha ingresado al buffer:"+this.id+"\n");
						
				this.contA++;
				this.capacidad--; //Syncronized objeto capacidad
						
				System.out.println(" Se hace notify insertar mensaje");
				objAzul.notify();
						
				producto.ModificarCadena("El producto:"+producto.getIdProducto()+" asociado al proceso: "+proceso.getPId()+
				" de color: "+((proceso.getColor()) ? " Naranja ": " Azul ") +" de la etapa: "+proceso.getEtapa()+
				" ha ingresado al buffer:"+this.id+"\n");    
						
				System.out.println("Se modifica cadena. El producto:"+producto.getIdProducto()+" asociado al proceso: "+proceso.getPId()+
				" de color: "+((proceso.getColor()) ? " Naranja ": " Azul ") +" de la etapa: "+proceso.getEtapa()+
				" ha ingresado al buffer:"+this.id+"\n");
			}
		}
	}
			
}
		