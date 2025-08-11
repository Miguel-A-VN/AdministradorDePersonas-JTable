package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

import controlador.Coordinador;
import modelo.dto.PersonaDTO;
import java.awt.Font;

public class VentanaRegistro extends JDialog implements ActionListener {

	private Coordinador coordinador;
	private JButton btnRegistrar;
	private JTextField txtNombre;
	private JLabel lblResultado;
	private JButton btnCalculos;
	private JLabel lblDocumento;
	private JTextField txtDocumento;
	private JLabel lblEdad;
	private JTextField txtEdad;
	private JButton btnConsultar;
	private JLabel lblRegistrarUsuarios;

	public VentanaRegistro(VentanaPrincipal ventanaPrincipal, boolean modal) {
		super(ventanaPrincipal, modal);
		setTitle("Registro de Persona");
		setSize(420, 320);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		// Fondo moderno
		getContentPane().setBackground(new Color(245, 245, 245));

		iniciarComponentes();
	}

	private void iniciarComponentes() {
		// Título
		lblRegistrarUsuarios = new JLabel("REGISTRAR USUARIOS");
		lblRegistrarUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblRegistrarUsuarios.setBounds(0, 10, 420, 30);
		getContentPane().add(lblRegistrarUsuarios);

		// Nombre
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombre.setBounds(30, 60, 100, 25);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNombre.setBounds(120, 60, 180, 28);
		getContentPane().add(txtNombre);

		btnRegistrar = new JButton("➕ Registrar Persona");
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnRegistrar.setBounds(30, 245, 370, 35); // ancho total de la ventana
		btnRegistrar.setBackground(new Color(72, 149, 239)); // azul moderno
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.addActionListener(this);
		getContentPane().add(btnRegistrar);

		// Documento
		lblDocumento = new JLabel("Documento:");
		lblDocumento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDocumento.setBounds(30, 100, 100, 25);
		getContentPane().add(lblDocumento);

		txtDocumento = new JTextField();
		txtDocumento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtDocumento.setBounds(120, 100, 140, 28);
		getContentPane().add(txtDocumento);

		// Edad
		lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEdad.setBounds(270, 100, 50, 25);
		getContentPane().add(lblEdad);

		txtEdad = new JTextField();
		txtEdad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtEdad.setBounds(310, 100, 50, 28);
		getContentPane().add(txtEdad);

		// Resultado
		lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		lblResultado.setBounds(30, 150, 360, 25);
		getContentPane().add(lblResultado);

		// Botón cálculos
		btnCalculos = new JButton("⚙ Hacer Cálculos");
		btnCalculos.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCalculos.setBounds(30, 200, 160, 30);
		btnCalculos.addActionListener(this);
		btnCalculos.setVisible(false);
		getContentPane().add(btnCalculos);

		// Botón consultar lista
		btnConsultar = new JButton("📋 Consultar Registros");
		btnConsultar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnConsultar.setBounds(210, 200, 190, 30);
		btnConsultar.setVisible(false);
		btnConsultar.addActionListener(this);
		getContentPane().add(btnConsultar);
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnRegistrar) {

			try {
				validaRegistro();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}else if(e.getSource()==btnCalculos) {
			coordinador.mostrarVentanaOperaciones();
		}else if(e.getSource()==btnConsultar) {
			coordinador.mostrarVentanaConsultarLista();;
		}
	}

	private void validaRegistro() throws SQLException {
		boolean validaNombre=coordinador.validarDatoTexto(txtNombre.getText());
		boolean validaDocumento=coordinador.validarDatoTexto(txtNombre.getText());
		boolean validaEdad=coordinador.validarNumero(txtEdad.getText());

		verificaCampo(validaNombre, txtNombre);
		verificaCampo(validaDocumento, txtDocumento);
		verificaCampo(validaEdad, txtEdad);

		if(validaNombre==true && validaDocumento==true && validaEdad==true) {

			String res=coordinador.consultarDatos(txtNombre.getText());

			if(registrarPersona()==true) {

				lblResultado.setText("Resultado: "+res+", Registro Ok");
			}else {
				lblResultado.setText("Resultado: "+res+", No Registra");
			}


			lblResultado.setForeground(Color.black);
			btnCalculos.setVisible(true);
			btnConsultar.setVisible(true);
		}else {
			lblResultado.setText("Valide los tipos de datos ingresados");
			lblResultado.setForeground(Color.red);
			btnCalculos.setVisible(false);
			btnConsultar.setVisible(false);
		}
	}

	private boolean registrarPersona() throws SQLException {
		PersonaDTO miPersonaDTO=new PersonaDTO();
		miPersonaDTO.setDocumento(txtDocumento.getText());
		miPersonaDTO.setNombre(txtNombre.getText());
		miPersonaDTO.setEdad(Integer.parseInt(txtEdad.getText()));

		String resp=coordinador.registrarPersona(miPersonaDTO);

		if (resp.equals("si")) {
			return true;
		}else {
			return false;
		}
	}

	public void limpiarFormulario() {
		txtNombre.setText("");
		txtDocumento.setText("");
		txtEdad.setText("");
		lblResultado.setText("");
		btnCalculos.setVisible(false);
		btnConsultar.setVisible(false);
	}

	private void verificaCampo(boolean validarCampo, JTextField campo) {

		if (validarCampo==true) {
			campo.setBackground(Color.white);
		}else {
			campo.setBackground(Color.red);
		}

	}
}

