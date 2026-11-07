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
public class PanelMenu extends JPanel {
    
    private Color fondo = new Color(20, 27, 45);
    private Color acento = new Color(244, 183, 64);

    public PanelMenu(Runnable jugarAzar,Runnable palabraFija,Runnable administrar,Runnable salir) {
        setLayout(new GridBagLayout());
        setBackground(fondo);
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(new Color(31, 41, 64));
        tarjeta.setBorder(new EmptyBorder(38, 55, 38, 55));
        tarjeta.setLayout(
                new BoxLayout(tarjeta, BoxLayout.Y_AXIS)
        );
        JLabel titulo = new JLabel("EL AHORCADO");
        titulo.setFont( new Font("SansSerif", Font.BOLD, 38)
        );

        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel subtitulo = new JLabel("Pon a prueba tu ingenio"
        );
        subtitulo.setFont( new Font("SansSerif", Font.PLAIN, 16)
        );
        subtitulo.setForeground(new Color(190, 201, 220));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarjeta.add(titulo);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(subtitulo);
        tarjeta.add(Box.createVerticalStrut(34));
        tarjeta.add(crearBoton("JUGAR - PALABRA AL AZAR",jugarAzar,acento,fondo
        ));
        tarjeta.add(Box.createVerticalStrut(12));
        tarjeta.add(crearBoton("JUGAR - PALABRA FIJA",palabraFija, new Color(70, 145, 190),Color.WHITE
        ));
        tarjeta.add(Box.createVerticalStrut(12));
        tarjeta.add(crearBoton(
                "ADMINISTRAR PALABRAS",
                administrar,
                new Color(73, 91, 125),
                Color.WHITE
        ));

        tarjeta.add(Box.createVerticalStrut(12));
        tarjeta.add(crearBoton("SALIR",salir, new Color(165, 72, 77), Color.WHITE
        ));
        add(tarjeta);
    }

    private JButton crearBoton(String texto,Runnable accion,Color fondo,Color letra) {
        JButton boton = new JButton(texto);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(330, 48));
        boton.setPreferredSize(new Dimension(330, 48));
        boton.setFont(new Font("SansSerif", Font.BOLD, 14)
        );
        boton.setBackground(fondo);
        boton.setForeground(letra);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18)
        );

        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );
        boton.addActionListener(e -> accion.run());
        return boton;
    }

    
}
