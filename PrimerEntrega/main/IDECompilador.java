package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.TextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class IDECompilador extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private FileReader fr;
	private File archivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDECompilador frame = new IDECompilador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IDECompilador() {
		setResizable(false);
		setTitle("Compilador - Grupo 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {30, 245, 30, 113, 30, 0};
		gbl_contentPane.rowHeights = new int[]{40, 20, 23, 23, 16, 160, 23, 0, 16, 115, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblAnalizadorLxico = new JLabel("Compilador");
		lblAnalizadorLxico.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblAnalizadorLxico = new GridBagConstraints();
		gbc_lblAnalizadorLxico.gridwidth = 8;
		gbc_lblAnalizadorLxico.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnalizadorLxico.gridx = 0;
		gbc_lblAnalizadorLxico.gridy = 0;
		contentPane.add(lblAnalizadorLxico, gbc_lblAnalizadorLxico);
		
		JLabel lblNombreDelArchivo = new JLabel("Nombre del archivo");
		GridBagConstraints gbc_lblNombreDelArchivo = new GridBagConstraints();
		gbc_lblNombreDelArchivo.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNombreDelArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDelArchivo.gridx = 1;
		gbc_lblNombreDelArchivo.gridy = 2;
		contentPane.add(lblNombreDelArchivo, gbc_lblNombreDelArchivo);
		
		TextArea txaArchivo = new TextArea();
		GridBagConstraints gbc_txaArchivo = new GridBagConstraints();
		gbc_txaArchivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txaArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_txaArchivo.gridwidth = 6;
		gbc_txaArchivo.gridx = 1;
		gbc_txaArchivo.gridy = 5;
		contentPane.add(txaArchivo, gbc_txaArchivo);
		
		JButton btnGuardarArchivo = new JButton("Guardar");
		btnGuardarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile(txaArchivo,true);
			}
		});
		
		JButton btnAbrirArchivo = new JButton("Abrir archivo");
		btnAbrirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				archivo = fc.getSelectedFile();
				String nombre = archivo.getName();
				txtNombre.setText(nombre);
				
				try {
					fr = new FileReader(archivo);
					BufferedReader br = new BufferedReader(fr);
					String texto= "";
					String linea= "";
					while((linea=br.readLine()) != null) {
						texto += linea+"\n";					
					}
					txaArchivo.setText(texto);
					fr = new FileReader(archivo);
				}catch(Exception ex) {
					
				}
			}
		});
		GridBagConstraints gbc_btnAbrirArchivo = new GridBagConstraints();
		gbc_btnAbrirArchivo.gridwidth = 2;
		gbc_btnAbrirArchivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAbrirArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_btnAbrirArchivo.gridx = 4;
		gbc_btnAbrirArchivo.gridy = 2;
		contentPane.add(btnAbrirArchivo, gbc_btnAbrirArchivo);
		GridBagConstraints gbc_btnGuardarArchivo = new GridBagConstraints();
		gbc_btnGuardarArchivo.gridwidth = 2;
		gbc_btnGuardarArchivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardarArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardarArchivo.gridx = 4;
		gbc_btnGuardarArchivo.gridy = 3;
		contentPane.add(btnGuardarArchivo, gbc_btnGuardarArchivo);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 3;
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.gridx = 1;
		gbc_txtNombre.gridy = 3;
		contentPane.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCodigoDePrueba = new JLabel("Codigo a analizar");
		lblCodigoDePrueba.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCodigoDePrueba = new GridBagConstraints();
		gbc_lblCodigoDePrueba.anchor = GridBagConstraints.WEST;
		gbc_lblCodigoDePrueba.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigoDePrueba.gridx = 1;
		gbc_lblCodigoDePrueba.gridy = 4;
		contentPane.add(lblCodigoDePrueba, gbc_lblCodigoDePrueba);
		
		TextArea resultadoAnalisis = new TextArea();
		resultadoAnalisis.setEditable(false);
		GridBagConstraints gbc_resultadoAnalisis = new GridBagConstraints();
		gbc_resultadoAnalisis.insets = new Insets(0, 0, 0, 5);
		gbc_resultadoAnalisis.fill = GridBagConstraints.BOTH;
		gbc_resultadoAnalisis.gridwidth = 6;
		gbc_resultadoAnalisis.gridx = 1;
		gbc_resultadoAnalisis.gridy = 9;
		contentPane.add(resultadoAnalisis, gbc_resultadoAnalisis);
		
		JButton btnRealizarAnalisis = new JButton("Realizar An\u00E1lisis");
		btnRealizarAnalisis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fr == null) {
					JOptionPane.showMessageDialog(null,"No hay archivo cargado");
				}else {
					try {
						saveFile(txaArchivo,false);
						Lexico Lexer = new Lexico(fr);
						Lexer.next_token();
						resultadoAnalisis.setText(Lexer.s);
						fr = new FileReader(archivo);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,"Error");
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e2.toString().substring(21));
					}
				}	
			}
		});
		GridBagConstraints gbc_btnRealizarAnalisis = new GridBagConstraints();
		gbc_btnRealizarAnalisis.gridwidth = 8;
		gbc_btnRealizarAnalisis.insets = new Insets(0, 0, 5, 0);
		gbc_btnRealizarAnalisis.gridx = 0;
		gbc_btnRealizarAnalisis.gridy = 7;
		contentPane.add(btnRealizarAnalisis, gbc_btnRealizarAnalisis);
		
		
		JLabel lblResutadosDelAnlisis = new JLabel("Resultados del an\u00E1lisis l\u00E9xico");
		lblResutadosDelAnlisis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblResutadosDelAnlisis = new GridBagConstraints();
		gbc_lblResutadosDelAnlisis.anchor = GridBagConstraints.WEST;
		gbc_lblResutadosDelAnlisis.insets = new Insets(0, 0, 5, 5);
		gbc_lblResutadosDelAnlisis.gridx = 1;
		gbc_lblResutadosDelAnlisis.gridy = 8;
		contentPane.add(lblResutadosDelAnlisis, gbc_lblResutadosDelAnlisis);
		

		
	

	}
	
	public void saveFile(TextArea txaArchivo, Boolean jopt) {
		String nombreArchivo = txtNombre.getText();
		String carpeta = System.getProperty("user.dir");
		String rutaArchivo = carpeta + "/" + nombreArchivo;
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(rutaArchivo);
		}catch(IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
		}
		try { 
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(txaArchivo.getText());
			bw.close();
			fr = new FileReader(rutaArchivo);
			if(jopt) {
				JOptionPane.showMessageDialog(null, "Archivo guardado correctamente");
			}
		}catch (Exception ex){
			
		}
			
	}

}
