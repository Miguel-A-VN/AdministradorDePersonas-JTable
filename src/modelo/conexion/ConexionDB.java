
package modelo.conexion;

import java.util.HashMap;

import controlador.Coordinador;
import modelo.dto.PersonaDTO;

public class ConexionDB {

	public static HashMap<String, PersonaDTO> personasMap;
	private Coordinador miCoordinador;
	
	public ConexionDB() {
		personasMap=new HashMap<String, PersonaDTO>();
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador=miCoordinador;
	}

	
}



