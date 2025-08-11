package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import modelo.dto.PersonaDTO;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class VentanaConsultarLista extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Coordinador miCoordinador;
	private JTable tablaNombres;
	private JScrollPane barra;
	private JComboBox combo1; 
	private JLabel lblSeleccion;
	/**
	 * Create the frame.
	 * @param b 
	 * @param ventanaPrincipal 
	 */
	public VentanaConsultarLista(VentanaPrincipal ventanaPrincipal, boolean modal) {

		super(ventanaPrincipal, modal);
		setBounds(100, 100, 453, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(null);
		

		iniciarComponentes();

	}
	private void iniciarComponentes() {
		JLabel lblSistemaGestionUsuarios = new JLabel("CONSULTA LISTA PERSONAS");
		lblSistemaGestionUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaGestionUsuarios.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSistemaGestionUsuarios.setBounds(33, 6, 388, 30);
		contentPane.add(lblSistemaGestionUsuarios);
		barra = new JScrollPane();
		barra.setBounds(21, 48, 410, 210);
		barra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(barra);
		tablaNombres = new JTable();
		combo1 = new JComboBox();
		combo1.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
		combo1.setSelectedIndex(0);
		combo1.setBounds(21, 270, 224, 20);
		combo1.addActionListener(this);
		contentPane.add(combo1);
		
		lblSeleccion = new JLabel();
		lblSeleccion.setBounds(260, 270, 200, 20);
		contentPane.add(lblSeleccion);
		
		
	}


	public void consultarListaPersonas() {
		ArrayList<PersonaDTO> listaPersonas=miCoordinador.consultarListaPersonas();
		String msj="";
		if (listaPersonas.size()>0) {
			llenarTabla(listaPersonas);
			llenarCombo(listaPersonas);
			
		}else {
			lblSeleccion.setText("No hay personas registradas");
		}
		
		
	}


	private void llenarCombo(ArrayList<PersonaDTO> listaPersonas) {
		combo1.removeAllItems();
		combo1.addItem("seleccione");
		String item= "";
		for (int i = 0; i< listaPersonas.size(); i++) {
			item = listaPersonas.get(i).getDocumento() + " " + listaPersonas.get(i).getNombre();
			combo1.addItem(item);
			
		}
		
	}


	private void llenarTabla(ArrayList<PersonaDTO> listaPersonas) {
		String titulos[]= {"Documento","Nombre","Edad"};
		
		String info[][] = new String[listaPersonas.size()][3];
		
		for(int x = 0; x < info.length; x++) {
			info[x][0] = listaPersonas.get(x).getDocumento();
			info[x][1] = listaPersonas.get(x).getNombre();
			info[x][2] = String.valueOf(listaPersonas.get(x).getEdad());
			
		}
		tablaNombres = new JTable(info,titulos);
		int[] anchos = {20,100,100,10};
		for (int i = 0; i<tablaNombres.getColumnCount(); i++) {
			tablaNombres.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
			
		}
		barra.setViewportView(tablaNombres);
		
	}



	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador=miCoordinador;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==combo1) {
			if(combo1.getSelectedItem()!=null) {
				if(combo1.getSelectedIndex()!=0) {
					lblSeleccion.setText(combo1.getSelectedItem().toString());
				}else {
					lblSeleccion.setText("");
				}
			}
		}
	}

}