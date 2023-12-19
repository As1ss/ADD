package operaciones;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
			System.out.println("5) Salir");
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
			}

		} while (!opcion.equals("5"));
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
			List <Ventas.Venta>listaVentas = new ArrayList<>();
			listaVentas = vent.getVenta();

			// Comprobar si existe el número de venta recorriendo el ArrayList
		
			boolean existe = verificarVenta(numeventa, listaVentas);;
			

			// Si el número de venta no existe, se añade la venta
			if (!existe) {
				// Crear el objeto Ventas.Venta, y añadirle los datos
				Ventas.Venta ve = new Ventas.Venta();
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

	private static boolean verificarVenta(int numeventa, List<Ventas.Venta> listaVentas) {
		for (Ventas.Venta venta: listaVentas) {
			if (numeventa == venta.getNumventa().intValue()) {
				return true;
			}
		}
		return false;
	}

}
