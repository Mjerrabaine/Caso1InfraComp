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
	Object objAzulObtener = new Object();
	Object objCapacidad = new Object();
	int id;

	// lista de mensajes
	private List<Producto> buffer;
	// capacidad del buffer
	private int capacidad; // capacidad disponible
	private int tamanioBuffer;
	private int contA;
	private int contN;

	public List<Producto> getBuffer() {
		return buffer;
	}

	public Buffer(int size, int id) {
		this.tamanioBuffer = size;
		this.capacidad = size;
		this.buffer = new LinkedList<Producto>();
		this.id = id;
	}

	public Buffer(int id) {
		this.id = id;
		this.tamanioBuffer = 999999;
		this.buffer = new LinkedList<Producto>();
		this.capacidad = 999999;
	}

	public void insertarMensaje(ProcesoP proceso, Producto producto) {
		System.out.println("el producto :" + producto.getIdProducto() + "entro a insertar en el buffer" + this.id);

		if (proceso.getColor() == true) {// true naranja
			while (getCapacidad() == 0) {
//                	System.out.println("el producto naranja:"+producto.getIdProducto()+"entro a esperar para insertar");
				proceso.yield();
			}

			synchronized (objCapacidad) {
				this.buffer.add(producto);
				this.contN++;
				this.capacidad--; // Syncronized objeto capacidad
			}

			producto.ModificarCadena("El producto:" + producto.getIdProducto() + " asociado al proceso: "
					+ proceso.getPId() + " de color: " + proceso.getColor() + " del proceso: " + proceso.getEtapa()
					+ " ha ingresado al buffer:");
			System.out.println("El producto:" + producto.getIdProducto() + " asociado al proceso: " + proceso.getPId()
					+ " de color: " + proceso.getColor() + " de la etapa: " + proceso.getEtapa()
					+ " ha ingresado al buffer:" + this.id + "\n");
		}

		else {// true azul
			synchronized (objAzul) {
				while (getCapacidad() == 0) {
					try {
//                        	System.out.println("el producto azul:"+producto.getIdProducto()+"entro a esperar para insertar");
						objAzul.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				this.buffer.add(producto);
				this.contA++;
				this.capacidad--; // Syncronized objeto capacidad

				producto.ModificarCadena("El producto:" + producto.getIdProducto() + " asociado al proceso: "
						+ proceso.getPId() + " de color: " + proceso.getColor() + " del proceso: " + proceso.getEtapa()
						+ " ha ingresado al buffer:");
				System.out.println("El producto:" + producto.getIdProducto() + " asociado al proceso: "
						+ proceso.getPId() + " de color: " + proceso.getColor() + " de la etapa: " + proceso.getEtapa()
						+ " ha ingresado al buffer:" + this.id + "\n");
				objAzul.notifyAll();
			}
		}
	}

	public Producto obtenerMensaje(ProcesoP proceso) {
		Producto productoElegido = null;
		try {
			// Random number between 50 and 500
			int random = (int) (Math.random() * 500) + 50;
			proceso.sleep(random);

		} catch (Exception e) {
			// TODO: handle exception
		}

		if (proceso.getColor() == true) {// es naranja
				while (getCapacidad() == this.getTamanioBuffer()|| contN == 0) {
					proceso.yield();
			}
			Boolean encontrado = false;
			int i = 0;
			synchronized (objCapacidad) {
				while (i < this.buffer.size() && encontrado == false) {
					Producto producto = this.buffer.get(i);
					if (producto.isColor() == true) {
						encontrado = true;
						productoElegido = producto;
						this.buffer.remove(i);

						this.capacidad++; // Syncronized objeto capacidad
						this.contN--;
					}

				}
			}
			productoElegido.ModificarCadena("El producto:" + productoElegido.getIdProducto() + " asociado al proceso: "
					+ proceso.getPId() + " de color: " + proceso.getColor() + " del proceso: " + proceso.getEtapa()
					+ " ha ingresado al buffer:");
			System.out.print("El producto:" + productoElegido.getIdProducto() + " asociado al proceso: "
					+ proceso.getPId() + " de color: " + proceso.getColor() + " del proceso: " + proceso.getEtapa()
					+ " ha salio del buffer:" + this.id + "\n");
		} else {// el color es azul
			synchronized (objAzul) {
				// System.out.println("entre a obtener azul");
				while (getCapacidad() == this.tamanioBuffer || contA == 0) {
					try {
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
						this.buffer.remove(i);
							this.capacidad++; // Syncronized objeto capacidad
							this.contA--;

					}
				}

				productoElegido.ModificarCadena("El producto:" + productoElegido.getIdProducto()
						+ " asociado al proceso: " + proceso.getPId() + " de color: " + proceso.getColor()
						+ " del proceso: " + proceso.getEtapa() + " ha ingresado al buffer:");
				System.out.print("El producto:" + productoElegido.getIdProducto() + " asociado al proceso: "
						+ proceso.getPId() + " de color: " + proceso.getColor() + " del proceso: " + proceso.getEtapa()
						+ " ha salio del buffer:" + this.id);
				objAzul.notifyAll();
			}

		}
		return productoElegido;
	}

	public int getCapacidad() {
		synchronized (objCapacidad) {
			return this.capacidad;
		}
	}

	public void modificarCapacidad(boolean accion) {
		if (accion == true) {// sumar
			this.capacidad++;
		} else {// restar
			this.capacidad--;
		}
	}

	public synchronized int getTamanioBuffer() {
		return tamanioBuffer;
	}



}
