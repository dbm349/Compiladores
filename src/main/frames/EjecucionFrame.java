package main.frames;

import main.ast.NodoPrograma;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EjecucionFrame extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    private final String ruta = System.getProperties().getProperty("user.dir");

    public EjecucionFrame(NodoPrograma nodoPrograma){
/*
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblCodigoDePrueba = new JLabel("Ruta de TASM");
        lblCodigoDePrueba.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblCodigoDePrueba = new GridBagConstraints();
        gbc_lblCodigoDePrueba.anchor = GridBagConstraints.WEST;
        gbc_lblCodigoDePrueba.insets = new Insets(0, 0, 5, 5);
        gbc_lblCodigoDePrueba.gridx = 1;
        gbc_lblCodigoDePrueba.gridy = 4;
        contentPane.add(lblCodigoDePrueba, gbc_lblCodigoDePrueba);


        //Input ASM
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 5, 5));
        setContentPane(contentPane);
        JTextField tasmPath = new JTextField();
        tasmPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
        contentPane.add(tasmPath, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();

            }
        });
        btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(btnCerrar, BorderLayout.SOUTH);
*/
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        JPanel paneInput = new JPanel();
        paneInput.setBounds(100, 100, 450, 150);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        contentPane.add(paneInput, BorderLayout.NORTH);

        JLabel lblCodigoDePrueba = new JLabel("Ruta de TASM");
        lblCodigoDePrueba.setFont(new Font("Tahoma", Font.PLAIN, 13));
        paneInput.add(lblCodigoDePrueba, BorderLayout.NORTH);
        JTextField tasmPath = new JTextField();
        tasmPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
        paneInput.add(tasmPath, BorderLayout.SOUTH);


            JButton btnCerrar = new JButton("Cerrar");
            btnCerrar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    dispose();

                }
            });
            btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 15));
            contentPane.add(btnCerrar, BorderLayout.SOUTH);


        }
    }
