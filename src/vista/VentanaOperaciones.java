package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controlador.Coordinador;

import java.awt.Font;

public class VentanaOperaciones extends JDialog implements ActionListener {
	private JButton btnCalcular;
	private JTextArea datosArea;
	private JTextField txtNum1;
	private JTextField txtNum2;
	private JRadioButton rbtnResta;
	private JRadioButton rbtnDivision;
	private JRadioButton rbtnSuma;
	private JRadioButton rbtnMultiplicacion;
	private JLabel lblNumero1;
	private JLabel lblNumero2;
	private JLabel lblTitulo;
	private Coordinador miCoordinador;


    public VentanaOperaciones(VentanaRegistro ventanaOperaciones, boolean modal) {

    	super(ventanaOperaciones, modal);
    	  
        setTitle("Ventana de Consulta");
        setSize(400, 272);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(250, 79, 100, 30);
        getContentPane().add(btnCalcular);
        btnCalcular.addActionListener(this);

        datosArea = new JTextArea();
        datosArea.setBounds(50, 164, 300, 60);
        datosArea.setEditable(false);
        getContentPane().add(datosArea);
        
        txtNum1 = new JTextField();
        txtNum1.setBounds(50, 80, 86, 26);
        getContentPane().add(txtNum1);
        txtNum1.setColumns(10);
        
        txtNum2 = new JTextField();
        txtNum2.setColumns(10);
        txtNum2.setBounds(152, 80, 86, 26);
        getContentPane().add(txtNum2);
        
   
        rbtnSuma = new JRadioButton("+");
        rbtnSuma.setBounds(47, 115, 56, 23);
        getContentPane().add(rbtnSuma);

        rbtnResta = new JRadioButton("-");
        rbtnResta.setBounds(133, 115, 56, 23);
        getContentPane().add(rbtnResta);

        rbtnMultiplicacion = new JRadioButton("*");
        rbtnMultiplicacion.setBounds(214, 115, 56, 23);
        getContentPane().add(rbtnMultiplicacion);

        rbtnDivision = new JRadioButton("/");
        rbtnDivision.setBounds(294, 115, 56, 23);
        getContentPane().add(rbtnDivision);

 
        ButtonGroup grupoOperaciones = new ButtonGroup();
        grupoOperaciones.add(rbtnSuma);
        grupoOperaciones.add(rbtnResta);
        grupoOperaciones.add(rbtnMultiplicacion);
        grupoOperaciones.add(rbtnDivision);
        
        lblNumero1 = new JLabel("Numero 1");
        lblNumero1.setBounds(50, 62, 86, 16);
        getContentPane().add(lblNumero1);
        
        lblNumero2 = new JLabel("Numero 2");
        lblNumero2.setBounds(152, 62, 86, 16);
        getContentPane().add(lblNumero2);
        
        lblTitulo = new JLabel("OPERACIONES MATEMATICAS");
        lblTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(6, 6, 388, 30);
        getContentPane().add(lblTitulo);

	}

    private String validarSeleccion() {
    	String operacion="";
    	if (rbtnSuma.isSelected()) {
    		operacion="suma";
	    } else if (rbtnResta.isSelected()) {
	    	operacion="resta";
	    } else if (rbtnMultiplicacion.isSelected()) {
	    	operacion="multiplicacion";
	    } else if (rbtnDivision.isSelected()) {
	    	operacion="division";
	    } else {
	    	operacion="";
	    }
    	
    	return operacion;
    }

	
	private void verificaCampo(boolean validarNumero, JTextField campo) {
		
		if (validarNumero==true) {
			campo.setBackground(Color.white);
		}else {
			campo.setBackground(Color.red);
		}
		
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCalcular) {
	        String num1 = txtNum1.getText();
	        String num2 = txtNum2.getText();

	        boolean valido1 = miCoordinador.validarNumero(num1);
	        boolean valido2 = miCoordinador.validarNumero(num2);

	        verificaCampo(valido1, txtNum1);
	        verificaCampo(valido2, txtNum2);

	        if (valido1 && valido2) {
	            String operacion = validarSeleccion();
	            if (!operacion.isEmpty()) {
	                String resultado = miCoordinador.calcularOperacion(operacion, num1, num2);
	                datosArea.setText(resultado);
	            } else {
	                datosArea.setText("Selecciona una operación.");
	            }
	        } else {
	            datosArea.setText("Corrige los datos en rojo.");
	        }
	    }
		
	}
}