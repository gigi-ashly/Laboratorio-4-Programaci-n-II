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
public class PanelJuego extends JPanel{
    
    private JuegoAhorcado juego;
    private GestorSonido sonido;
    private Runnable volverMenu;

    private JLabel palabra;
    private JLabel intentos;
    private JLabel acertadas;
    private JLabel erradas;
    private JLabel mensaje;

    private JTextField campoLetra;
    private JButton probar;
    private JButton sonidoBoton;

    private PanelDibujo dibujo;
    private JPanel teclado;

    private JButton[] teclas = new JButton[27];

    public PanelJuego(JuegoAhorcado juego, GestorSonido sonido,Runnable volverMenu) {

        this.juego = juego;
        this.sonido = sonido;
        this.volverMenu = volverMenu;

        setLayout(new BorderLayout(18, 18));
        setBackground(new Color(20, 27, 45));
        setBorder(new EmptyBorder(22, 28, 22, 28));
        JLabel titulo = new JLabel(
                "PARTIDA EN CURSO",
                SwingConstants.CENTER
        );

        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 25)
        );

        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(1, 2, 18, 0)
        );
        centro.setOpaque(false);
        JPanel izquierda = new JPanel();

        izquierda.setBackground(Color.WHITE);
        izquierda.setBorder(new EmptyBorder(18, 18, 18, 18)
        );

        izquierda.setLayout( new BoxLayout(izquierda,BoxLayout.Y_AXIS
                )
        );
    }
}
