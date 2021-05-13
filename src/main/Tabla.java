package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Font;

public class Tabla extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tabla frame = new Tabla();
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
    private final String ruta = System.getProperties().getProperty("user.dir");
    public Tabla() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JList list = new JList();
        list.setFont(new Font("Tahoma", Font.PLAIN, 12));
        contentPane.add(list, BorderLayout.CENTER);
        setBounds(300, 50, 675, 551);
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setVisible(true);


        File archivo = null;
        FileReader FileR=null;
        BufferedReader BufferedR=null;

        try {
            archivo=new File(ruta +"\\ts.txt");
            FileR=new FileReader(archivo);
            BufferedR= new BufferedReader(FileR);
            String informacion;

            DefaultListModel lista = new DefaultListModel();

            while((informacion=BufferedR.readLine()) != null) {
                lista.addElement(informacion);
            }

            list.setModel(lista);

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
