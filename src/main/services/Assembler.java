package main.services;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Assembler extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    private final String ruta = System.getProperties().getProperty("user.dir");

    public Assembler(){

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
        contentPane.add(textArea, BorderLayout.CENTER);
        JList list = new JList();
        list.setFont(new Font("Tahoma", Font.PLAIN, 12));
        //contentPane.add(list, BorderLayout.CENTER);
        setBounds(300, 50, 675, 551);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVisible(true);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(scroll);


        File archivo = null;
        FileReader FileR=null;
        BufferedReader BufferedR=null;

        try {
            archivo=new File(ruta +"\\final.asm");       //windows
            //archivo=new File(ruta +"/final.asm");//linux
            FileR=new FileReader(archivo);
            BufferedR= new BufferedReader(FileR);
            String informacion;
            String asm = "";

            DefaultListModel lista = new DefaultListModel();

            while((informacion=BufferedR.readLine()) != null) {
                lista.addElement(informacion);
                asm += informacion + "\n";
            }

            list.setModel(lista);
            textArea.setText(asm);
            JButton btnCerrar = new JButton("Cerrar");
            btnCerrar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    dispose();

                }
            });
            btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 15));
            contentPane.add(btnCerrar, BorderLayout.SOUTH);
        } catch (Exception e) {} finally {
            try {
                if(null != FileR) {
                    FileR.close();}}
            catch (IOException e2){{}
            }


        }
    }

}
