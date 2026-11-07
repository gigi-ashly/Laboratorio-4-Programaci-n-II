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
import javax.swing.JPanel;
public class VentanaPrincipal extends JFrame {
    private CardLayout tarjetas;
    private JPanel contenedor;
    private AdministradorPalabras administrador;
    private GestorSonido sonido;
    private PanelMenu panelMenu;
    private PanelAdministracion panelAdministracion;
    
     public VentanaPrincipal(AdministradorPalabras administrador,GestorSonido sonido) {
     this.administrador = administrador;
     this.sonido = sonido;
     setTitle("Juego del Ahorcado");
        setSize(1000, 680);
        setMinimumSize(new Dimension(850, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tarjetas = new CardLayout();
        contenedor = new JPanel(tarjetas);

       
     }
     
}
