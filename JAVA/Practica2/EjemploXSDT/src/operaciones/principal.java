package operaciones;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import generated.DatosArtic;
import generated.Ventas;
import generated.VentasType;

public class principal {
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		menuPrincipal();

	}

	private static void menuPrincipal() {

		String opcion = "";
		do {
			System.out.println("-------MENU PRINCIPAL-------");
			System.out.println("Escoge una opción válida.");
			System.out.println("1) Visualizar XML");
			System.out.println("2) Insertar venta");
			System.out.println("3) Modificar venta");
			System.out.println("4) Eliminar venta");
			System.out.println("5) Añadir stock");
			System.out.println("6) Salir");
			System.out.println("------------------------------");
			opcion = sc.nextLine();

			switch (opcion) {
			case "1":
				visualizarxml();
				break;

			case "2":
				try {
					System.out.println("Inserta el número de venta (Digito)");
					int numVenta = sc.nextInt();
					sc.nextLine();
					System.out.println("Inserta el nombre del cliente");
					String nombreCli = sc.nextLine();
					System.out.println("Inserta las unidades (Dígito)");
					int unidadesVenta = sc.nextInt();
					sc.nextLine();
					System.out.println("Inserta la fecha de venta");
					String fechaVenta = sc.nextLine();
					insertarventa(numVenta, nombreCli, unidadesVenta, fechaVenta);
				} catch (InputMismatchException e) {
					System.out.println("Has introducido un caracter no válido");

				}

				break;
			case "3":
				try {
					System.out.println("Inserta el número de venta (Digito) quer quieras modificar");
					int numVenta = sc.nextInt();
					sc.nextLine();
					if (modificarVenta(numVenta)) {
						System.out.println("Se ha realizado la operación de modificación.");
					} else {
						System.err.println("Ha ocurrido un error, nos e ha realizado la modificación.");
					}

				} catch (InputMismatchException e) {
					System.out.println("Has introducido un caracter no válido");

				}

				break;
			case "4":
				try {
					System.out.println("Inserta el número de venta (Digito) que quieras eliminar");
					int numVenta = sc.nextInt();
					sc.nextLine();
					if (eliminarVenta(numVenta)) {
						System.out.println("Se ha realizado la operación de eliminacion.");
					} else {
						System.err.println("Ha ocurrido un error, nos e ha realizado la eliminacion.");
					}

				} catch (InputMismatchException e) {
					System.out.println("Has introducido un caracter no válido");

				}

				break;
			case "5":
				System.out.println("Inserta el número de unidades de stock que quieres añadir al artículo");
				try {
					int cantidad = sc.nextInt();
					sc.nextLine();
					if (añadirStock(cantidad)) {
						System.out.println("El stock se ha actualizado con éxito.");
					} else {
						System.err.println("El stock se ha actualizado con éxito.");
					}

				} catch (Exception e) {
					System.out.println(
							"Ha ocurrido un error, nos e ha añadido la cantidad deseada al stock del artículo.");
				}
				break;
			}

		} while (!opcion.equals("6"));
		System.out.println("Has escogido salir.\n" + LocalDateTime.now().getDayOfMonth() +"/"+LocalDateTime.now().getMonth()
				+"/"+LocalDateTime.now().getYear());
	}

	private static boolean añadirStock(int cantidad) {
		System.out.println("--------------------------------");
		System.out.println("--------- AÑADIR STOCK ---------");
		System.out.println("--------------------------------");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(generated.ObjectFactory.class);
			Unmarshaller u = jaxbContext.createUnmarshaller();
			JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));

			VentasType miventa = (VentasType) jaxbElement.getValue();

			// Crear una instancia para obtener todas las ventas
			Ventas vent = miventa.getVentas();
			DatosArtic articulo = miventa.getArticulo();
			int stockActualizado = articulo.getStock().intValue() + cantidad;
			articulo.setStock(BigInteger.valueOf(stockActualizado));

			System.out.println("Stock actualizado.");

			// Crear el Marshaller, y volcar la lista al fichero XML
			Marshaller m = jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private static boolean eliminarVenta(int numeventa) {
		boolean elimOk = false;
		System.out.println("--------------------------------");
		System.out.println("--------- ELIMINAR VENTA ---------");
		System.out.println("--------------------------------");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(generated.ObjectFactory.class);
			Unmarshaller u = jaxbContext.createUnmarshaller();
			JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));

			VentasType miventa = (VentasType) jaxbElement.getValue();

			// Crear una instancia para obtener todas las ventas
			Ventas vent = miventa.getVentas();

			// Guardar las ventas en la lista
			List<Ventas.Venta> listaVentas = new ArrayList<>();
			listaVentas = vent.getVenta();

			// Comprobar si existe el número de venta recorriendo el ArrayList

			Ventas.Venta ve = verificarVenta(numeventa, listaVentas);

			// Si el número de venta no existe, se añade la venta
			if (ve != null) {
				// Crear el objeto Ventas.Venta, y añadirle los datos

				listaVentas.remove(ve);

				// Crear el Marshaller, y volcar la lista al fichero XML
				Marshaller m = jaxbContext.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));
				System.out.println("Venta " + numeventa + " eliminado.");
				elimOk = true;

			} else {
				System.out.println("El número de venta " + numeventa + " no existe");
				elimOk = false;
			}

		} catch (JAXBException je) {
			System.out.println(je.getCause());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		return elimOk;

	}

	private static boolean modificarVenta(int numeventa) {
		boolean modifOk = false;
		System.out.println("--------------------------------");
		System.out.println("--------- MODIFICAR VENTA ---------");
		System.out.println("--------------------------------");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(generated.ObjectFactory.class);
			Unmarshaller u = jaxbContext.createUnmarshaller();
			JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));

			VentasType miventa = (VentasType) jaxbElement.getValue();

			// Crear una instancia para obtener todas las ventas
			Ventas vent = miventa.getVentas();

			// Guardar las ventas en la lista
			List<Ventas.Venta> listaVentas = new ArrayList<>();
			listaVentas = vent.getVenta();

			// Comprobar si existe el número de venta recorriendo el ArrayList

			Ventas.Venta ve = verificarVenta(numeventa, listaVentas);

			// Si el número de venta no existe, se añade la venta
			if (ve != null) {
				// Crear el objeto Ventas.Venta, y añadirle los datos

				System.out.println("Inserta las nuevas unidades (Dígito)");
				int unidadesVenta = sc.nextInt();
				sc.nextLine();

				System.out.println("Inserta la nueva fecha de venta");
				String fechaVenta = sc.nextLine();
				ve.setFecha(fechaVenta);
				ve.setUnidades(unidadesVenta);

				// Añadir la venta a la lista
				listaVentas.add(ve);

				// Crear el Marshaller, y volcar la lista al fichero XML
				Marshaller m = jaxbContext.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));
				System.out.println("Venta " + numeventa + " modificado.");
				modifOk = true;

			} else {
				System.out.println("El número de venta " + numeventa + " no existe");
				modifOk = false;
			}

		} catch (JAXBException je) {
			System.out.println(je.getCause());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		return modifOk;

	}

	public static void visualizarxml() {

		System.out.println("--------------------------------");
		System.out.println("-------- VISUALIZAR XML --------");
		System.out.println("--------------------------------");
		try {
			// Para crear el contexto JAXB se pueden usar ambas formas
			// JAXBContext jaxbContext = JAXBContext.newInstance("clasesdatos");
			JAXBContext jaxbContext = JAXBContext.newInstance(generated.ObjectFactory.class);

			// Crear un objeto de tipo Unmarshaller para convertir datos XML en un árbol de
			// objetos Java
			Unmarshaller u = jaxbContext.createUnmarshaller();

			// La clase JAXBElement representa a un elemento de un documento XML
			// En este caso a un elemento del documento ventasarticulos.xml
			JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));

			// Visualizar el documento
			Marshaller m = jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(jaxbElement, System.out);

			// Si se quiere operar con el documento hay que obtener los objetos del
			// JAXBElement
			// El método getValue() devuelve el modelo de contenido (ContentModel) y el
			// valor de los atributos del elemento
			VentasType miventa = (VentasType) jaxbElement.getValue();

			// Crear una instancia para obtener todas las ventas
			Ventas vent = miventa.getVentas();

			// Guardar las ventas en la lista
			List listaVentas = new ArrayList();
			listaVentas = vent.getVenta();

			System.out.println("--------------------------------");
			System.out.println("---- VISUALIZAR LOS OBJETOS ----");
			System.out.println("--------------------------------");

			// Cargar los datos del artículo
			DatosArtic miartic = (DatosArtic) miventa.getArticulo();
			System.out.println("Nombre del art�culo: " + miartic.getDenominacion());
			System.out.println("Codigo del art�culo: " + miartic.getCodigo());
			System.out.println("Stock del art�culo : " + miartic.getStock());
			System.out.println("Precio del art�culo: " + miartic.getPrecio());
			System.out.println("Ventas del art�culo: " + listaVentas.size());

			// Visualizar las ventas del artículo
			for (int i = 0; i < listaVentas.size(); i++) {
				Ventas.Venta ve = (Ventas.Venta) listaVentas.get(i);
				System.out.println("N�mero de venta: " + ve.getNumventa() + ". Nombre cliente: " + ve.getNombrecliente()
						+ ". Unidades: " + ve.getUnidades() + ". Fecha: " + ve.getFecha());
			}

		} catch (JAXBException je) {
			System.out.println(je.getCause());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

	}

	private static void insertarventa(int numeventa, String nomcli, int uni, String fecha) {

		System.out.println("--------------------------------");
		System.out.println("--------- AÑADIR VENTA ---------");
		System.out.println("--------------------------------");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(generated.ObjectFactory.class);
			Unmarshaller u = jaxbContext.createUnmarshaller();
			JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));

			VentasType miventa = (VentasType) jaxbElement.getValue();

			// Crear una instancia para obtener todas las ventas
			Ventas vent = miventa.getVentas();

			// Guardar las ventas en la lista
			List<Ventas.Venta> listaVentas = new ArrayList<>();
			listaVentas = vent.getVenta();

			// Comprobar si existe el número de venta recorriendo el ArrayList

			Ventas.Venta ve = verificarVenta(numeventa, listaVentas);

			// Si el número de venta no existe, se añade la venta
			if (ve == null) {
				// Crear el objeto Ventas.Venta, y añadirle los datos

				ve = new Ventas.Venta();
				ve.setNombrecliente(nomcli);
				ve.setFecha(fecha);
				ve.setUnidades(uni);
				BigInteger nume = BigInteger.valueOf(numeventa);
				ve.setNumventa(nume);

				// Añadir la venta a la lista
				listaVentas.add(ve);

				// Crear el Marshaller, y volcar la lista al fichero XML
				Marshaller m = jaxbContext.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));
				System.out.println("Venta " + numeventa + " añadida");
			} else
				System.out.println("El número de venta " + numeventa + " ya existe");
		} catch (JAXBException je) {
			System.out.println(je.getCause());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

	}

	private static Ventas.Venta verificarVenta(int numeventa, List<Ventas.Venta> listaVentas) {
		for (Ventas.Venta venta : listaVentas) {
			if (numeventa == venta.getNumventa().intValue()) {

				return venta;

			}
		}
		return null;
	}

}
