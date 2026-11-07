/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4prograii;

/**
 *
 * @author ANGILI PALACIOS
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelAdministracion extends JPanel{
    private AdministradorPalabras administrador;
    private GestorSonido sonido;
    private Runnable volver;

    private JTextField campoPalabra;
    private DefaultListModel<String> modeloLista;
    private JList<String> lista;
    private JLabel cantidad;

    public PanelAdministracion( AdministradorPalabras administrador,GestorSonido sonido,Runnable volver) {
        this.administrador = administrador;
        this.sonido = sonido;
        this.volver = volver;
        
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(20, 27, 45));

        setBorder(new EmptyBorder(25, 35, 25, 35)
        ); 
         JLabel titulo = new JLabel(
                "ADMINISTRACIÓN DE PALABRAS",
                SwingConstants.CENTER
        );

        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24)
        );
        add(titulo, BorderLayout.NORTH);
        JPanel contenido = new JPanel( new BorderLayout(12, 12)
        );

        contenido.setBackground(Color.WHITE);

        contenido.setBorder(new EmptyBorder(22, 22, 22, 22)
        );

        JPanel arriba = new JPanel(new FlowLayout()
        );
        arriba.setBackground(Color.WHITE);
        arriba.add(new JLabel("Nueva palabra:"));
        campoPalabra = new JTextField(18);
        campoPalabra.setFont(new Font("SansSerif", Font.PLAIN, 16)
        );

        arriba.add(campoPalabra);
        JButton agregar = new JButton("AGREGAR");
        agregar.setBackground( new Color(244, 183, 64)
        );
        arriba.add(agregar);
        contenido.add(arriba, BorderLayout.NORTH);
        modeloLista = new DefaultListModel<>();
        lista = new JList<>(modeloLista);
        lista.setFont(new Font("SansSerif", Font.PLAIN, 16)
        );

        contenido.add( new JScrollPane(lista),BorderLayout.CENTER
        );

        JPanel abajo = new JPanel(new BorderLayout()
        );

        abajo.setBackground(Color.WHITE);
        cantidad = new JLabel();
        abajo.add(cantidad, BorderLayout.WEST);
        JButton regresar = new JButton("VOLVER AL MENU");
        abajo.add(regresar, BorderLayout.EAST);
        contenido.add(abajo, BorderLayout.SOUTH);
        add(contenido, BorderLayout.CENTER);
        agregar.addActionListener(e -> agregarPalabra());
        campoPalabra.addActionListener(e -> agregarPalabra());
        regresar.addActionListener(e -> volver.run());
        actualizarLista();
    }
    private void agregarPalabra() {
        try {
            String palabra = campoPalabra.getText();
            administrador.agregar(palabra);
            sonido.reproducir(EfectoSonido.ACIERTO);
            campoPalabra.setText("");
            actualizarLista();
            JOptionPane.showMessageDialog(this,"La palabra se agrego correctamente."
            );
        } catch (
                PalabraInvalidaException| PalabraDuplicadaException ex) {
            sonido.reproducir(EfectoSonido.ALERTA);
            JOptionPane.showMessageDialog(this,ex.getMessage(), "No se pudo agregar",OptionPane.WARNING_MESSAGE
            );
        }
    }

    public void actualizarLista() {
        modeloLista.clear();
        for (String palabra : administrador.getPalabras()) {
            modeloLista.addElement(palabra);
        }
        cantidad.setText("Total de palabras: " + administrador.cantidad()
        );
    }
    }

