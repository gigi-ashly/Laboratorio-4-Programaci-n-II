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
   panelMenu = new PanelMenu(
                () -> iniciarJuegoAzar(),
                () -> solicitarPalabra(),
                () -> mostrarAdministracion(),
                () -> System.exit(0)
        );

        panelAdministracion = new PanelAdministracion(
                administrador,
                sonido,
                () -> mostrarMenu()
        );

        contenedor.add(panelMenu, "MENU");
        contenedor.add(panelAdministracion, "ADMINISTRACION");

        add(contenedor);

        mostrarMenu();
    }

    private void iniciarJuegoAzar() {

        try {

            JuegoAhorcado juego = new JuegoAlAzar(administrador);

            mostrarJuego(juego);

        } catch (PalabraInvalidaException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void solicitarPalabra() {

        javax.swing.JPasswordField campo =
                new javax.swing.JPasswordField();

        Object[] contenido = {
            "Escribe la palabra que deberá adivinar el otro jugador:",
            campo
        };

        int opcion = JOptionPane.showConfirmDialog(this,contenido,"Palabra fija",JOptionPane.OK_CANCEL_OPTION
        );

        if (opcion == JOptionPane.OK_OPTION) {

            String palabra = new String(campo.getPassword());

            try {
                JuegoAhorcado juego = new JuegoPalabraFija(palabra);

                mostrarJuego(juego);

            } catch (PalabraInvalidaException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Palabra inválida",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    private void mostrarJuego(JuegoAhorcado juego) {

        PanelJuego panelJuego = new PanelJuego(
                juego,
                sonido,
                () -> mostrarMenu()
        );

        contenedor.add(panelJuego, "JUEGO");

        tarjetas.show(contenedor, "JUEGO");

        revalidate();
        repaint();
    }

    private void mostrarAdministracion() {

        panelAdministracion.actualizarLista();

        tarjetas.show(contenedor, "ADMINISTRACION");
    }

    private void mostrarMenu() {

        tarjetas.show(contenedor, "MENU");
    
       
     }
     
}
