/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import form.FrmLogIn;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Phenom
 */
public class Start {
    public static void main(String[] args) throws IOException {
        JFrame frmLogin = new FrmLogIn();
        frmLogin.setVisible(true);
    }
}
