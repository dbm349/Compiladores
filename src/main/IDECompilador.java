package main;

import main.ast.NodoPrograma;
import main.services.Assembler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;

public class IDECompilador extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private FileReader fr;
	private File archivo;
	private String path;
	private String timestamp;

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
		setBounds(100, 100, 650, 700);
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
				File workingDirectory = new File(System.getProperty("user.dir"));
				JFileChooser fc = new JFileChooser(workingDirectory);
				fc.showOpenDialog(null);
				archivo = fc.getSelectedFile();
				path = archivo.getAbsolutePath();
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
		
		JButton btnNewButton = new JButton("Analisis Sint\u00E1ctico");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fr == null) {
					JOptionPane.showMessageDialog(null,"No hay archivo cargado");
				}else {
					saveFile(txaArchivo,false);
					Lexico lexer = new Lexico(fr);
					parser sintactico = new parser(lexer);
					try {		
						sintactico.parse();
						resultadoAnalisis.setText(sintactico.s);
						fr = new FileReader(archivo);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,"Error");
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						if (!sintactico.Error.isEmpty()) {
							JOptionPane.showMessageDialog(null,sintactico.Error);
						}
						if (!lexer.Error.isEmpty()) {
							JOptionPane.showMessageDialog(null,lexer.Error);
						}
					}
					path = null;
				}	
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 5;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 7;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnRealizarAnalisis = new JButton("Analisis Lexicogr\u00E1fico");
		btnRealizarAnalisis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fr == null) {
					JOptionPane.showMessageDialog(null,"No hay archivo cargado");
				}else {
					//saveFile(txaArchivo,false);
					Lexico lexer = new Lexico(fr);
					parser sintactico = new parser(lexer);
					try {
						sintactico.parse();
						resultadoAnalisis.setText(lexer.s);
						fr = new FileReader(archivo);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,"Error");
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						if (!sintactico.Error.isEmpty()) {
							JOptionPane.showMessageDialog(null,sintactico.Error);
						}
						if (!lexer.Error.isEmpty()) {
							JOptionPane.showMessageDialog(null,lexer.Error);
						}
					}
					path = null;
				}	
			}
		});
		GridBagConstraints gbc_btnRealizarAnalisis = new GridBagConstraints();
		gbc_btnRealizarAnalisis.gridwidth = 3;
		gbc_btnRealizarAnalisis.insets = new Insets(0, 0, 5, 5);
		gbc_btnRealizarAnalisis.gridx = 0;
		gbc_btnRealizarAnalisis.gridy = 7;
		contentPane.add(btnRealizarAnalisis, gbc_btnRealizarAnalisis);
		
		
		JLabel lblResutadosDelAnlisis = new JLabel("Resultados del an\u00E1lisis");
		lblResutadosDelAnlisis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblResutadosDelAnlisis = new GridBagConstraints();
		gbc_lblResutadosDelAnlisis.anchor = GridBagConstraints.WEST;
		gbc_lblResutadosDelAnlisis.insets = new Insets(0, 0, 5, 5);
		gbc_lblResutadosDelAnlisis.gridx = 1;
		gbc_lblResutadosDelAnlisis.gridy = 8;
		contentPane.add(lblResutadosDelAnlisis, gbc_lblResutadosDelAnlisis);

		JButton btnNewButton_1 = new JButton("Mostrar tabla");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tabla t = new Tabla();
				t.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 11;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_3 = new JButton("Generar AST y ASM");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fr == null) {
					JOptionPane.showMessageDialog(null, "No hay archivo cargado");
				} else {
					Lexico lexer = new Lexico(fr);
					parser sintactico = new parser(lexer);
					try {
						NodoPrograma programa = (NodoPrograma) sintactico.parse().value;

						// Generacion arbol
						FileWriter archivo = new FileWriter("arbol.dot");
						PrintWriter pw = new PrintWriter(archivo);
						pw.println(programa.graficar());
						archivo.close();

						// Pasaje de .dot a .pgn
						try {
							timestamp = (new Date()).toString();
							timestamp = timestamp.replaceAll(" ", "");
							timestamp = timestamp.replaceAll(":", "");
							String cmd = "dot -Tpng arbol.dot -o \"" + timestamp + ".png\"";
							System.out.println("Comando: " + cmd);
							Runtime.getRuntime().exec(cmd);
							resultadoAnalisis.setText(lexer.s + "\n\n AST generado");
						} catch (Exception e) {
							System.out.println("No se pudo generar el pgn AST, revisar la instalaci??n de Graphviz");
						}

						// Generacion assembler
						String assembler = programa.generarAssembler();
						FileWriter fileWriter = new FileWriter("final.asm");
						fileWriter.write(assembler);
						fileWriter.close();

					} catch (Exception e) {
						//ignore
					}
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 5, 5, 0);
		gbc_btnNewButton_3.gridx = 13;
		gbc_btnNewButton_3.gridy = 9;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);

		JButton btnNewButton_6 = new JButton("Ejecutar");
		btnNewButton_6.setVisible(false);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fr == null) {
					JOptionPane.showMessageDialog(null, "No hay archivo cargado");
				} else {
					Lexico lexer = new Lexico(fr);
					parser sintactico = new parser(lexer);
					try {
						archivo=new File("ASM.conf");

						FileReader fileReader =new FileReader(archivo);
						BufferedReader bufferedReader = new BufferedReader(fileReader);
						String tasmPath = bufferedReader.readLine();
						String dosBoxPath = bufferedReader.readLine();

						NodoPrograma programa = (NodoPrograma) sintactico.parse().value;
						System.out.println("Generado AST");
						String assembler = programa.generarAssembler();
						FileWriter fileWriter = new FileWriter(tasmPath + "/prueba.asm");
						fileWriter.write(assembler);
						fileWriter.close();
						System.out.println("Generado ASM");
						String cmd = "cd "+ dosBoxPath + "\n DOSBox.exe\"";
						System.out.println("Ejecutando: " + cmd);
						Runtime.getRuntime().exec(cmd);
						System.out.println("Programa ejecutado");

					} catch (Exception e) {
						//ignore
					}
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 5, 5, 0);
		gbc_btnNewButton_6.gridx = 13;
		gbc_btnNewButton_6.gridy = 6;
		contentPane.add(btnNewButton_6, gbc_btnNewButton_6);

		JButton btnNewButton_2 = new JButton("Mostrar AST");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = System.getProperty("user.dir") + "\\" + timestamp + ".png";
				try {
					JLabel arbolImage = new JLabel(new ImageIcon(path));
					JFrame frame = new JFrame("Vista ??rbol");
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setBounds(100,100,1000,500);
					contentPane.setOpaque(true);
					JScrollPane scroll = new JScrollPane(arbolImage);
					scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					frame.add(scroll);
					frame.setVisible(true);
					frame.setResizable(true);
				} catch (Exception e) {
					//ignore
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 5, 5, 0);
		gbc_btnNewButton_2.gridx = 13;
		gbc_btnNewButton_2.gridy = 3;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);

		JButton btnNewButton_10 = new JButton("Mostrar ASM");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Assembler asm = new Assembler();
				asm.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton_10 = new GridBagConstraints();
		gbc_btnNewButton_10.insets = new Insets(0, 5, 5, 0);
		gbc_btnNewButton_10.gridx = 13;
		gbc_btnNewButton_10.gridy = 4;
		contentPane.add(btnNewButton_10, gbc_btnNewButton_10);

	}
	
	public void saveFile(TextArea txaArchivo, Boolean jopt) {
		String nombreArchivo = txtNombre.getText();
		if (archivo == null) {
			String carpeta = System.getProperty("user.dir");
			path = carpeta + "/" + nombreArchivo;
		}else {
			if (!archivo.getName().equals(nombreArchivo)) {
				String carpeta = System.getProperty("user.dir");
				path = carpeta + "/" + nombreArchivo;
			}else {
				path = archivo.getAbsolutePath();
			}
		}
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "No hay ningun archivo");
		}
		try { 
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(txaArchivo.getText());
			bw.close();
			fr = new FileReader(path);
			if(jopt) {
				JOptionPane.showMessageDialog(null, "Archivo guardado correctamente");
			}
		}catch (Exception ex){
			
		}
		path = null;
	}

}
