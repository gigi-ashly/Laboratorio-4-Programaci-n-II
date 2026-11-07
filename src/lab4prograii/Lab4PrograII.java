/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4prograii;

/**
 *
 * @author gigiw
 */
import javax.swing.SwingUtilities;
public class Lab4PrograII {

    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> {

            AdministradorPalabras administrador =
                    new AdministradorPalabras();

            GestorSonido sonido = new GestorSonido();

            VentanaPrincipal ventana =
                    new VentanaPrincipal(administrador, sonido);

            ventana.setVisible(true);
        });
    // TODO code application logic here
    }
    
}
