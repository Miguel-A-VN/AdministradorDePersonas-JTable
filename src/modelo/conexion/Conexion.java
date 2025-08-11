package modelo.conexion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

	public Properties propiedades = new Properties();
	private String nombreBd = "";
	private String usuario = "";
	private String password = "";
	private String url = "";

	Connection conn = null;
	private static Conexion instancia;

	private Conexion() {
		conectar();
	}

	private void cargarCredenciales() {
		try {
		propiedades.load(

		new FileInputStream("C:/Users/mvarg/Documents/programming/ADSO/4 trimestre/JAVA/Ejercicios/AdministradorDePersonas-JTable/src/properties/archivo.properties"));

		nombreBd=propiedades.getProperty("db.nombre_bd");
		usuario=propiedades.getProperty("db.usuario");
		password=propiedades.getProperty("db.password");
		url="jdbc:mysql://localhost:3306/"+nombreBd+"?useUnicode=true&use"
		+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
		+ "serverTimezone=UTC";

		} catch (FileNotFoundException e) {
		System.out.println("⚠️ Error, El archivo no exite");
		} catch (IOException e) {
			System.out.println("⚠️ Error, No se puede leer el archivo");
			e.printStackTrace();
			}
			}

	public void conectar() {
		try {
			cargarCredenciales();
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(url, usuario, password);
			if (conn != null) {
				System.out.println("✅ Conexion Exitosa  a la BD: " + nombreBd);
			} else {
				System.out.println("🐞 NO SE PUDO CONECTAR " + nombreBd);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("⚠️ ocurre una ClassNotFoundException : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("⚠️ ocurre una SQLException: " + e.getMessage());
			System.out.println("⚠️ Verifique que Mysql esté encendido...");
		}
	}

	public static Conexion getInstancia() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				conectar();
			}
		} catch (SQLException e) {
			System.out.println("⚠️ Error al verificar el estado de la conexión: " + e.getMessage());
		}
		return conn;
	}

	public void desconectar() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("✅ Conexión cerrada.");
			} catch (SQLException e) {
				System.out.println("⚠️ Error al cerrar la conexión: " + e.getMessage());
			} finally {
				conn = null;
			}
		}
	}

}