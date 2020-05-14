import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//aqui esta el codigo para grabar y consultar
public class JDBCCompra implements DAOCompra{
	private Connection conectar() {//se hace un metodo para la conexión, para asi no tener que copiar el codigo siempre
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Connection conn = null;
		try {
			DataSource ds = (DataSource) context.getBean("ds");;
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("Tenemos un problema, no conecta");
			System.out.println(e.getMessage());
		}
		return conn;

	}


	public void crearSqlite() {
		File fichero = new File ("compra.db");
		try{
			if(fichero.createNewFile()) {
				System.out.println("compra.db se ha creado correctamente");
			}
			String sql = "CREATE TABLE IF NOT EXISTS compra (Cliente TEXT,Producto TEXT,Cantidad Double,Precio DOUBLE,ID INTEGER, Fecha TIMESTAMP);";
			Connection conn = this.conectar();
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void crearMysql() {
	     try {
		String bd = "CREATE DATABASE IF NOT EXISTS compra";
		String use = "USE compra;";
		String url = "jdbc:mysql://localhost:3306/";
		String sql = "CREATE TABLE IF NOT EXISTS compra (Cliente VARCHAR(30),Producto VARCHAR(30),Cantidad NUMERIC(4),Precio NUMERIC(4,2),ID NUMERIC(3), Fecha DATE);";
		Connection conn = DriverManager.getConnection(url, "root", "root");
		Statement st = conn.createStatement();
		st.executeUpdate(bd);
		st.executeUpdate(use);
		st.executeUpdate(sql);
	     } catch (Exception e) {
		System.err.println("OHH ALGO MAL");
		System.err.println(e.getMessage());
	     }
	}

	  public static final String ANSI_RESET = "\u001B[0m";
	  //Colores de letra
	  public static final String ANSI_RED = "\u001B[31m";
	  public static final String ANSI_GREEN = "\u001B[32m";
	  //Colores de fondo
	  public static final String ANSI_BLACK_BACKGROUND = "\u001B[30m";

	ArrayList<Integer> listaIDs= new ArrayList<Integer>();
	boolean valor;
	public boolean idexistente(int ids){
		try  {
			String sql1 = "SELECT ID FROM compra";
			Connection conn = this.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listaIDs.add(rs.getInt("ID"));
				if(listaIDs.contains(ids)){
					System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Esta ID ya existe" + ANSI_RESET);
					valor = true;
				}
				else {
					valor = false;
				}
			}	
		} catch (SQLException o) {
			System.out.println(o.getMessage());
		}
		return valor;
	}

	public void grabar(Compra c) {//codigo para grabar
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(c.getFecha().getTime());
		String sql = "INSERT INTO compra (Cliente,Producto,Cantidad,Precio,ID,Fecha) VALUES(?,?,?,?,?,?)";
		try (Connection conn = this.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, c.getPer().getName());
			pstmt.setString(2, c.getArt().getNombre());
			pstmt.setDouble(3, c.getCant());
			pstmt.setDouble(4, c.getArt().getPrecio());
			pstmt.setInt(5, c.getId());
			pstmt.setTimestamp(6, sqlDate);
			pstmt.executeUpdate();
		} catch (SQLException o) {
			System.out.println(o.getMessage());
		}
	}
	//a partir de aqui, el codigo es para consultar.
	public void consultart(){//aqui te muestra todo lo que hay almacenado en la base de datos
		try  {
			String sql1 = "SELECT DISTINCT(ID),Cliente FROM compra";
			Connection conn = this.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("CLIENTE  ID");
			while (rs.next()) {
				 System.out.println(rs.getString("Cliente")+" --> "+rs.getInt("ID"));

			 }	
			
		} catch (SQLException o) {
			System.out.println(o.getMessage());
		}
	}

	public void consultarn(String nombre){//aqui te muestra solo aquellos con el nombre introducido
		try{
			String sql2 = "SELECT * FROM compra WHERE CLIENTE ='"+nombre+"'";
			Connection conn = this.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {	
					 System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + "Cliente: "+rs.getString("Cliente") + ANSI_RESET);
			
					 System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + rs.getDouble("Cantidad")+" unidades de "+rs.getString("Producto")+" por "+rs.getDouble("Precio") + " €, con ID " + rs.getInt("ID") + " a fecha de " + rs.getTimestamp("Fecha") + ANSI_RESET);
			 }	
		}
		catch (SQLException o) {
			System.out.println(o.getMessage());
		}
	}


	public void consultari(int id){//aqui te muestra solo aquellos con la ID introducida.
		try{
			String sql2 = "SELECT * FROM compra WHERE ID ="+id;
			Connection conn = this.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {	
					 System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + "ID: " + rs.getInt("ID") + ANSI_RESET);
					 System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + "Esta ID le pertenece a: " + rs.getString("Cliente") + ANSI_RESET);
					 System.out.println(ANSI_BLACK_BACKGROUND + ANSI_GREEN + rs.getDouble("Cantidad")+" unidades de "+rs.getString("Producto")+" por "+rs.getDouble("Precio") + " €, a fecha de " + rs.getTimestamp("Fecha") + ANSI_RESET);

			 }	
		}
		catch (SQLException o) {
			System.out.println(o.getMessage());
		}
	}
}
