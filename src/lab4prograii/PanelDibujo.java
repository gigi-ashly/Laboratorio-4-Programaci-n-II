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
public class PanelDibujo extends JPanel {
      private EtapaAhorcado etapa;

    public PanelDibujo() {
        etapa = EtapaAhorcado.values()[0];
        setBackground(new Color(245, 247, 251));
        setPreferredSize(new Dimension(300, 330));
    }
    public void setEtapa(EtapaAhorcado etapa) {
        this.etapa = etapa;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(new Color(48, 59, 79));
        g2.setStroke(new BasicStroke(5));

        int baseY = getHeight() - 35;
        int posteX = 65;

        g2.drawLine(25, baseY, 180, baseY);
        g2.drawLine(posteX, baseY, posteX, 30);
        g2.drawLine(posteX, 30, 205, 30);
        g2.drawLine(205, 30, 205, 65);

        int partes = etapa.ordinal();

        g2.setColor(new Color(190, 65, 70));
        g2.setStroke(new BasicStroke(4));

        if (partes >= 1) {
            g2.drawOval(181, 65, 48, 48);
        }

        if (partes >= 2) {
            g2.drawLine(205, 113, 205, 215);
        }

        if (partes >= 3) {
            g2.drawLine(205, 135, 160, 180);
        }

        if (partes >= 4) {
            g2.drawLine(205, 135, 250, 180);
        }

        if (partes >= 5) {
            g2.drawLine(205, 215, 165, 275);
        }

        if (partes >= 6) {
            g2.drawLine(205, 215, 245, 275);
        }

        g2.dispose();
    }

}
