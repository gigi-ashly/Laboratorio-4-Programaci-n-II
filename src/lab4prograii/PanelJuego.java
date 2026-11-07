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
        palabra = new JLabel("", SwingConstants.CENTER);

        palabra.setFont(new Font("Monospaced", Font.BOLD, 34)
        );

        palabra.setForeground(new Color(31, 54, 83));

        intentos = new JLabel("", SwingConstants.CENTER);

        intentos.setFont(new Font("SansSerif", Font.BOLD, 18)
        );
        acertadas = new JLabel("Acertadas: ");
        acertadas.setForeground(new Color(35, 135, 90));
        erradas = new JLabel("Erradas: ");
        erradas.setForeground(new Color(190, 65, 70));
        mensaje = new JLabel( "Escribe una letra para comenzar.",SwingConstants.CENTER
        );

        izquierda.add(palabra);
        izquierda.add(Box.createVerticalStrut(14));
        izquierda.add(intentos);
        izquierda.add(Box.createVerticalStrut(10));
        izquierda.add(acertadas);
        izquierda.add(Box.createVerticalStrut(7));
        izquierda.add(erradas);
        izquierda.add(Box.createVerticalStrut(15));

        izquierda.add(crearEntrada());

        izquierda.add(Box.createVerticalStrut(12));
        izquierda.add(mensaje);
        izquierda.add(Box.createVerticalStrut(15));

        teclado = new JPanel(new GridLayout(3, 10, 6, 6)
        );
        teclado.setBackground(Color.WHITE);
        crearTeclado();
        izquierda.add(teclado);
        JPanel derecha = new JPanel(new BorderLayout()
        );

        derecha.setBackground(Color.WHITE);
        derecha.setBorder(new EmptyBorder(12, 12, 12, 12)
        );

        dibujo = new PanelDibujo();
        derecha.add(dibujo, BorderLayout.CENTER);
        JPanel botonesAbajo = new JPanel();
        botonesAbajo.setBackground(Color.WHITE);
        JButton nuevo = new JButton("JUGAR DE NUEVO");
        JButton menu = new JButton("VOLVER AL MENÚ");
        sonidoBoton = new JButton("SONIDO: ACTIVADO");
        botonesAbajo.add(nuevo);
        botonesAbajo.add(menu);
        botonesAbajo.add(sonidoBoton);
        derecha.add(botonesAbajo, BorderLayout.SOUTH);

        nuevo.addActionListener(e -> reiniciarPartida());
        menu.addActionListener(e -> volverMenu.run());
        sonidoBoton.addActionListener(e -> alternarSonido());

        centro.add(izquierda);
        centro.add(derecha);

        add(centro, BorderLayout.CENTER);

        probar.addActionListener(e -> probarLetra());
        campoLetra.addActionListener(e -> probarLetra());

        actualizarPantalla();
    }

    private JPanel crearEntrada() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.WHITE);
        campoLetra = new JTextField(5);
        campoLetra.setFont(
                new Font("SansSerif", Font.BOLD, 18)
        );
        campoLetra.setHorizontalAlignment(
                JTextField.CENTER
        );
        probar = new JButton("PROBAR");
        panel.add(new JLabel("Letra:"));
        panel.add(campoLetra);
        panel.add(probar);

        return panel;
    }

    private void crearTeclado() {
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        for (int i = 0; i < letras.length(); i++) {
            String letra = String.valueOf(
                    letras.charAt(i)
            );
            JButton boton = new JButton(letra);
            boton.setFocusable(false);
            boton.addActionListener(e -> {
                campoLetra.setText(letra);
                probarLetra();
            });
            teclas[i] = boton;
            teclado.add(boton);
        }
    }

    private void probarLetra() {
        try {
            ResultadoLetra resultado = juego.ingresarLetra(
                    campoLetra.getText()
            );
            if (resultado == ResultadoLetra.ACIERTO) {
                sonido.reproducir(EfectoSonido.ACIERTO);
                mensaje.setText("Muy bien esa letra si esta."
                );

            } else {
                sonido.reproducir(EfectoSonido.ERROR);
                mensaje.setText("Esa letra no está. Intenta otra."
                );
            }
            marcarTecla(
                    campoLetra.getText().toUpperCase()
            );
            campoLetra.setText("");
            actualizarPantalla();
        } catch (
                LetraInvalidaException
                | LetraRepetidaException
                | PartidaTerminadaException ex) {

            sonido.reproducir(EfectoSonido.ALERTA);

            mensaje.setText(ex.getMessage());
        }
    }

    private void marcarTecla(String letra) {

        for (JButton boton : teclas) {

            if (boton != null && boton.getText().equals(letra)) {

                boton.setEnabled(false);

                boton.setBackground(
                        new Color(215, 220, 230)
                );
            }
        }
    }

    private void actualizarPantalla() {

        palabra.setText(
                formatearPalabra(juego.getPalabraMostrada())
        );

        intentos.setText("Intentos restantes: "+ juego.getIntentosRestantes()+ " / 6"
        );

        acertadas.setText("Acertadas: " + juego.getLetrasAcertadas()
        );

        erradas.setText("Erradas: " + juego.getLetrasErradas()
        );

        dibujo.setEtapa(juego.getEtapa());

        if (juego.getEstado() == EstadoPartida.GANADA) {

            mensaje.setText("¡Ganaste! Completaste la palabra."
            );

            terminarPartida();
            sonido.reproducir(EfectoSonido.VICTORIA);

        } else if (juego.getEstado() == EstadoPartida.PERDIDA) {
            mensaje.setText("Perdiste. La palabra era: "+ juego.getPalabraSecreta()
            );
            terminarPartida();
            sonido.reproducir(EfectoSonido.DERROTA);
        }
    }

    private String formatearPalabra(char[] mostrada) {
        StringBuilder texto = new StringBuilder();
        for (char letra : mostrada) {
            texto.append(letra).append(' ');
        }
        return texto.toString();
    }

    private void terminarPartida() {

        campoLetra.setEnabled(false);
        probar.setEnabled(false);
        for (JButton boton : teclas) {
            if (boton != null) {
                boton.setEnabled(false);
            }
        }
    }

    private void reiniciarPartida() {
        try {
            juego.reiniciar();
            campoLetra.setEnabled(true);
            probar.setEnabled(true);
            for (JButton boton : teclas) {
                if (boton != null) {
                    boton.setEnabled(true);
                    boton.setBackground(null);
                }
            }
            mensaje.setText(
                    "Nueva partida. ¡Buena suerte!"
            );
            actualizarPantalla();
        } catch (PalabraInvalidaException ex) {
            mensaje.setText(ex.getMessage());
        }
    }

    private void alternarSonido() {
        sonido.setSilenciado(
                !sonido.isSilenciado()
        );
        if (sonido.isSilenciado()) {
            sonidoBoton.setText("SONIDO: SILENCIADO");
        } else {
            sonidoBoton.setText("SONIDO: ACTIVADO");
        }
    }
    }

