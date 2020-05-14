//imports de lo que usaremos
import java.util.ArrayList;
import java.io.Console;
import java.io.FileReader;
import java.io.BufferedReader;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Double;
import javax.sql.DataSource;
import java.util.Scanner;

public class tienda{
	  //Reset
	  public static final String ANSI_RESET = "\u001B[0m";
	  //Colores de letra
	  public static final String ANSI_RED = "\u001B[31m";
	  public static final String ANSI_GREEN = "\u001B[32m";
	  public static final String ANSI_CYAN = "\u001B[36m";
	  //Colores de fondo
	  public static final String ANSI_BLACK_BACKGROUND = "\u001B[30m";
	  public static String seccion = null;

	public static void main(String args[]) throws Exception {
		DAOCompra daocompra = new JDBCCompra();//creacion de DAO para lo relacionado con la base de datos
		System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Dime que base de datos vas a usar (sqlite o mysql) " + ANSI_RESET);
		Scanner sc = new Scanner(System.in);
		String respuesta1 = sc.nextLine();
		if (respuesta1.equalsIgnoreCase("sqlite")) {
			daocompra.crearSqlite();
		} else if (respuesta1.equalsIgnoreCase("mysql")) {
			daocompra.crearMysql();
		} else {
			System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "No seleccionaste ninguna base de datos existente o algo parecido" + ANSI_RESET);
			System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Esperamos que la próxima selecciones alguna disponible" + ANSI_RESET);
			return;
		}
		//a continuacion, el codigo para leer el fichero json, que corresponde al catalogo de productos.	

		String line = new String("");
		String lin = null;
		String line1 = new String("");
		String lin1 = null;
		ArrayList<String> listaProds = new ArrayList<String>();
		ArrayList<String> listaProdsI = new ArrayList<String>();
		Console sec = null;
		sec = System.console();
		System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Bienvenidos a nuestra tienda!!!" + ANSI_RESET);
		System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Elige una seccion: Fruteria | Informática" + ANSI_RESET);
		seccion = sec.readLine();
		if (seccion.equalsIgnoreCase("Fruteria")){
			BufferedReader br = new BufferedReader(new FileReader("productos.json"));
			while((lin = br.readLine()) != null) {
	          	line = line + lin;
	        }
	        JSONArray jsonProductos = new JSONArray(line);
	        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Sección Frutería" + ANSI_RESET);
	        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "------------------" + ANSI_RESET);
	        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Producto " + " Precio" + ANSI_RESET);
	        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "               " + ANSI_RESET);
	        for (Object obj : jsonProductos){
	        		String nombre = ((JSONObject) obj).getString("nombre");
	        		String precio = ((JSONObject) obj).getString("precio");
	        		listaProds.add(nombre + ":" + precio);//los datos se pasan a una lista,para tenerlos guardados y poder usarlos
	        		System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + nombre + " : " + precio + " €" + ANSI_RESET);
	        }
	        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "-------------------" + ANSI_RESET);  		
		}
		else if (seccion.equalsIgnoreCase("Informatica")) {
			
			BufferedReader sr = new BufferedReader(new FileReader("productosI.json"));
	        while((lin1 = sr.readLine()) != null) {
	          	line1 = line1 + lin1;
	        }
	        JSONArray jsonProductosI = new JSONArray(line1);
	        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Sección Informática" + ANSI_RESET);
	        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "---------------------------" + ANSI_RESET);
	        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Producto " + " Precio" + ANSI_RESET);
	        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "                           " + ANSI_RESET);
	        for (Object obj1 : jsonProductosI){
	        		String nombre = ((JSONObject) obj1).getString("nombre");
	        		String precio = ((JSONObject) obj1).getString("precio");
	        		listaProdsI.add(nombre + ":" + precio);//los datos se pasan a una lista,para tenerlos guardados y poder usarlos
	        		System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + nombre + ":  " + precio + " €" + ANSI_RESET);
	        }
	        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "---------------------------" + ANSI_RESET);				
        }
		
        //una vez leidos y guardados, se empieza con la compra
		Compra c;
		ArrayList<Compra> lista1 = new ArrayList<Compra>();
		Console console = null;
		console = System.console();
		while(true){//bucle para crear compras, con sus respectivos datos (persona, articulos, cantidad y precio)
			c = new Compra();
			Person p = new Person();
			System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN +"Nombre persona: " + ANSI_RESET);//nombre de la persona
			String persona = console.readLine();
			if(persona.equals("")){
					break;
				}
			p.setName(persona);//setteo nombre de la persona
			System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "ID de compra: " + ANSI_RESET);
			String idcomp = console.readLine();
			int idcom=Integer.parseInt(idcomp);
			if (daocompra.idexistente(idcom)) {
				break;
			}
			
			while(true){//este while es para que una persona pueda comprar mas de un articulo en una compra
				c.setId(idcom);//setteo del id
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Articulos: " + ANSI_RESET);//articulo
				String articulo = console.readLine();
				if(articulo.equals("")){
					break;
				}

				for(int i = 0;i<listaProds.size();i++){//aqui esta la informacion del json
					String[] a = listaProds.get(i).split(":");
					if(articulo.equals(a[0])){
						c.getArt().setNombre(articulo);//si el articulo está en la lista de productos disponibles, setteo del articulo

						Double preciop=Double.parseDouble(a[1]);
						System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Cantidad: " + ANSI_RESET);//cantidad de articulos
						String canti = console.readLine();
						if(canti.equals("")){
							break;
						}
						int number = Integer.parseInt(canti);
						if (number < 0) {
							System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "No puedes llevarte menos de 0.0 productos, es incoherente" + ANSI_RESET);
							break;
						}
						Double cant = Double.parseDouble(canti);
						c.getArt().setPrecio(preciop * cant);//setteo de precio por cantidad (precio final por producto)
						c.setCant(cant);//setteo de cantidad
						c.getPer().setName(p.getName());//setteo de la persona dentro de la clase Compra
						Date fechaFactura = new Date();//Creación de fecha
						c.setFecha(fechaFactura);//setteo a la compra de fecha/hora
						lista1.add(c);//añadido de la compra a la lista
						daocompra.grabar(c);//se guarda en la base de datos
					}
				}
				for(int i = 0;i<listaProdsI.size();i++){//aqui esta la informacion del json
					String[] a = listaProdsI.get(i).split(":");
					
					if(articulo.equals(a[0])){
						c.getArt().setNombre(articulo);//si el articulo está en la lista de productos disponibles, setteo del articulo

						Double preciop=Double.parseDouble(a[1]);
						System.out.println(ANSI_BLACK_BACKGROUND + ANSI_CYAN + "Cantidad: " + ANSI_RESET);//cantidad de articulos
						String canti = console.readLine();
						if(canti.equals("")){
							break;
						}
						Double cant = Double.parseDouble(canti);
						c.getArt().setPrecio(preciop * cant);//setteo de precio por cantidad (precio final por producto)
						c.setCant(cant);//setteo de cantidad
						c.getPer().setName(p.getName());//setteo de la persona dentro de la clase Compra
						Date fechaFactura = new Date();//Creación de fecha
						c.setFecha(fechaFactura);//setteo a la compra de fecha/hora
						lista1.add(c);//añadido de la compra a la lista
						daocompra.grabar(c);//se guarda en la base de datos
					}
				}
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN +"Mas articulos? S|N " + ANSI_RESET);//Para agregar más articulos a la misma persona
				String masart = console.readLine();
				if(masart.equalsIgnoreCase("n")){
					c.setArticulos(lista1);
					break;
				}
				else if (masart.equalsIgnoreCase("s")){
					c = new Compra();//si queremos agregar más articulos crea una nueva compra y se le añaden articulos, al no haber creado una persona nueva
					//se le agrega la ultima persona creada, asi no crea conflictos de persona de compra = null
					//ni agregar articulos de otras personas.
				} else if (!masart.equalsIgnoreCase("n")||!masart.equalsIgnoreCase("s")){
					System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "No me has dado una respuesta convincente" + ANSI_RESET);
					break;
				}
			}
			System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + "Mas entradas? S|N " + ANSI_RESET);//Para agregar más entradas (personas y articulos)
			String masper = console.readLine();
			if(!masper.equalsIgnoreCase("s")){
				break;
			}
		}
		//Confirmación de registro en la base de datos, con la fecha de la factura.
		System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + "Los datos han quedado guardados en la base de datos. A fecha de " + new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(c.getFecha()) + ANSI_RESET);
		//Se le cambia el formato de la fecha de Compra para que la muestre como nosotros la visualizamos dia-mes-año y hora:min:seg

		while(true){//aqui es donde esta la parte de consultas.
			System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + "Quieres consultar datos? S|N" + ANSI_RESET);
			String respuesta = console.readLine();
			if (respuesta.equalsIgnoreCase("s")){
				daocompra.consultart();//te muestra todos los datos guardados
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + "Para consultar por persona: N | Para mostrar por ID: I" + ANSI_RESET);
				String respuesta2 = console.readLine();
				if(respuesta2.equalsIgnoreCase("n")){
					System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + "Introduce nombre" + ANSI_RESET);
					String r = console.readLine();
					daocompra.consultarn(r);//te muestra los datos que contienen el nombre introducido
				}
				
				else if(respuesta2.equalsIgnoreCase("i")){
					System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + "Introduce id" + ANSI_RESET);
					String r = console.readLine();
					int r2=Integer.parseInt(r);
					daocompra.consultari(r2);//te muestra la compra de una persona
				}
				else{//si la opcion que se introdujo no es n, i que salte este error y que pregunte si desea consultar o no.
					System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Lo sentimos! Esa opcion no esta disponible" + ANSI_RESET);
				}
			}
			else{//Mensaje despedida cuando el cliente se vaya
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + "Vuelva pronto." + ANSI_RESET);
				break;
			}
		}
	}
}
