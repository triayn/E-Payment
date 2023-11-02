/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.applet.Applet;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author laptophp
 */
public class TestEnter extends Applet {

    TextField t;

    public void init() {
        TextField t = new TextField("press ENTER");
        add(t);
        t.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    Toolkit.getDefaultToolkit().beep();
                    System.out.println("ENTER pressed");
                }
            }
        }
        );
    }
}
