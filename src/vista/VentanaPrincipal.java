package vista;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JButton btnRegistrar;
	private JButton btnConsultarPersona;
	private JButton btnConsultarLista;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		iniciarComponentes();
	}

	private void iniciarComponentes() {
		// Fondo general
		contentPane.setBackground(new java.awt.Color(245, 245, 245));

		// Título
		JLabel lblSistemaGestionUsuarios = new JLabel("SISTEMA DE GESTIÓN DE USUARIOS");
		lblSistemaGestionUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaGestionUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSistemaGestionUsuarios.setBounds(20, 10, 410, 30);
		contentPane.add(lblSistemaGestionUsuarios);

		// Texto descriptivo (no editable)
		JTextArea txtrDescripcion = new JTextArea();
		txtrDescripcion.setText(
				"Esta aplicación implementa el patrón MVC (Modelo-Vista-Controlador),\n" +
						"separando responsabilidades en distintas clases y delegando tareas\n" +
						"mediante un coordinador.\n\n" +
						"Puede registrar nuevas personas, consultar datos individuales o ver\n" +
						"la lista completa de personas almacenadas."
		);
		txtrDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtrDescripcion.setEditable(false);
		txtrDescripcion.setOpaque(false);
		txtrDescripcion.setFocusable(false);
		txtrDescripcion.setWrapStyleWord(true);
		txtrDescripcion.setLineWrap(true);
		txtrDescripcion.setBounds(25, 50, 400, 110);
		contentPane.add(txtrDescripcion);

		// Botón registrar
		btnRegistrar = new JButton("➕ Registrar Persona");
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRegistrar.setBounds(25, 180, 400, 35);
		btnRegistrar.setBackground(new Color(72, 149, 239));
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);

		// Botón consultar persona
		btnConsultarPersona = new JButton("🔍 Consultar Persona");
		btnConsultarPersona.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnConsultarPersona.setBackground(new Color(72, 149, 239));
		btnConsultarPersona.setBounds(25, 220, 400, 35);
		btnConsultarPersona.addActionListener(this);
		contentPane.add(btnConsultarPersona);

		// Botón consultar lista
		btnConsultarLista = new JButton("📋 Consultar Lista de Personas");
		btnConsultarLista.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnConsultarLista.setBackground(new Color(72, 149, 239));
		btnConsultarLista.setBounds(25, 260, 400, 35);
		btnConsultarLista.addActionListener(this);
		contentPane.add(btnConsultarLista);
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			miCoordinador.mostrarVentanaRegistro();
		} else if (e.getSource() == btnConsultarPersona) {
			miCoordinador.mostrarVentanaConsultaIndividual();
		} else if (e.getSource() == btnConsultarLista) {
			miCoordinador.mostrarVentanaConsultarLista();
		}
	}
}
