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
    }
}
