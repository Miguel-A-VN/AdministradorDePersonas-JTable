import controlador.Relaciones;
import modelo.conexion.Conexion;

public class Main {
	public static void main(String[] args) {
		new Relaciones();

		Conexion conexion = Conexion.getInstancia();
		System.out.println(conexion);
	}
}