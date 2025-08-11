package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controlador.Coordinador;
import modelo.conexion.Conexion;
import modelo.conexion.ConexionBD;
import modelo.dto.PersonaDTO;

public class PersonaDAO {
	Connection connection = null;
	Conexion conexion = Conexion.getInstancia();
	PreparedStatement preparedStatement = null;
	private Coordinador miCoordinador;

	public String registrarPersona(PersonaDTO persona) throws SQLException {
		connection = conexion.getConnection();
		try {
			if (ConexionBD.personasMap.containsKey(persona.getDocumento()) == false) {
				String consulta = "insert into empleado(documento,nombre,edad) values (?,?,?) ";

				preparedStatement = connection.prepareStatement(consulta);
				preparedStatement.setString(1, persona.getDocumento());
				preparedStatement.setString(2, persona.getNombre());
				preparedStatement.setInt(3, persona.getEdad());
				preparedStatement.execute();
				return "si";
			} else {
				return "ya existe";
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Se ha duplicado la clave primaria");
//			e.printStackTrace();
			return "no";
		} catch (Exception e) {
			System.out.println("No se pudo registrar el dato: " + e.getMessage());
			e.printStackTrace();
			return "no";
		} finally {
			preparedStatement.close();
			connection.close();
			conexion.desconectar();
		}

	}

	public PersonaDTO consultarPersonaPorDocumento(String documento) throws SQLException {
		PersonaDTO persona = null;
		// conectar();
//	if(connection==null) {
//		return persona;
		// }
		connection = conexion.getConnection();
		ResultSet resultSet = null;
		String consulta = "select documento,nombre,edad from empleado where documento = ? ";

		try {
			preparedStatement = connection.prepareStatement(consulta);

			preparedStatement.setString(1, documento);/* si tengo mas ??? seria 1 2 3 */
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				persona = new PersonaDTO();
				persona.setDocumento(resultSet.getString("documento"));
				persona.setNombre(resultSet.getString("nombre"));
				persona.setEdad(resultSet.getInt("edad"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("hay un error en la consulta" + e.getMessage());
		} finally {
			resultSet.close();
			preparedStatement.close();
			connection.close();
			conexion.desconectar();
		}

		return persona;

	}

	public ArrayList<PersonaDTO> consultarListaPersonas() {
		PreparedStatement statement = null;
		ResultSet result = null;
		connection = conexion.getConnection();
		ArrayList<PersonaDTO> listaPersonas = new ArrayList<PersonaDTO>();
		String consulta = "select * from empleado ";

		try {
			if (connection != null) {
				statement = connection.prepareStatement(consulta);
				result = statement.executeQuery();

				while (result.next()) {
					PersonaDTO miUsuario = new PersonaDTO();
					miUsuario.setDocumento(result.getString("documento"));
					miUsuario.setNombre(result.getString("nombre"));
					miUsuario.setEdad(result.getInt("edad"));

					listaPersonas.add(miUsuario);
				}

				conexion.desconectar();
			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta de usuarios: " + e.getMessage());
		}

		return listaPersonas;
	}

	public String actualizarPersona(PersonaDTO persona) throws SQLException {

		PreparedStatement statement = null;
		String resp = "";
		connection = conexion.getConnection();
		String consulta = "UPDATE empleado SET nombre = ?, edad = ? WHERE documento = ?";
		try {
			if (connection != null) {
				statement = connection.prepareStatement(consulta);

				statement.setString(1, persona.getNombre());
				statement.setInt(2, persona.getEdad());
				statement.setString(3, persona.getDocumento());
				statement.executeUpdate();
				resp = "ok";
				conexion.desconectar();
			}
		} catch (Exception e) {
			System.out.println(e);
			resp = "error";
		}

		return resp;

	}

	public String eliminarPersona(String documento) throws SQLException {

		connection = conexion.getConnection();
		try {
			String consulta = "delete from empleado where documento = 123";
			PreparedStatement statement = connection.prepareStatement(consulta);
			statement.executeUpdate();
			return "ok";

		} catch (Exception e) {
			System.out.println(e);
			return "error";
		} finally {
			preparedStatement.close();
			connection.close();
			conexion.desconectar();

		}

	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

}