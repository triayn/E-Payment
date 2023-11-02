/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frame;

import static Frame.Login.admin;
import static Frame.Login.username;
import Informasi.BerhasilLogin;
import Informasi.GagalLogin;
import Main.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class LoginWithRFID extends javax.swing.JFrame {

    public LoginWithRFID() {
        initComponents();
        focus.requestFocus();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
    }
 public static String username = "", kode_karyawan = "", nama = "";
   
    public void Login(String key) {
       try {

            String sql = "SELECT * FROM karyawan WHERE kode_karyawan = '" + key + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                Login.namaUser = r.getString("username");
                String Username = r.getString("username");
                Login.username = Username;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        try {
            String sql = "SELECT * FROM karyawan ";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            System.out.println("KODE : " + key);
            if (rs.next()) {
                System.out.println("execute");
                if (key.toString().equals(rs.getString(1))) {
                    BerhasilLogin p = new BerhasilLogin(null, true);
                    p.setVisible(true);
                    System.out.println(key);
                    if (rs.getString("level_user").equalsIgnoreCase("owner")) {
                        System.out.println("user admin");
                        admin = true;
                    } else {
                        System.out.println("user karyawan");
                        admin = false;
                    }
                    new ProgressBar().setVisible(true);
                    this.dispose();
                } else {
                    GagalLogin q = new GagalLogin(this, true);
                    q.setVisible(true);
                    focus.setText("");
                }
            }
        } catch (Exception e) {
            GagalLogin r = new GagalLogin(null, true);
            r.setVisible(true);
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        focus = new javax.swing.JTextField();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/loginRFID.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        focus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                focusKeyReleased(evt);
            }
        });
        getContentPane().add(focus, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 120, -1));

        btn_back.setOpaque(false);
        btn_back.setContentAreaFilled(false);
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void focusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_focusKeyReleased
       String kode = focus.getText();
       Login.kode_karyawan = kode;
//       LoginWithRFID.kode_karyawan = this.kode_karyawan;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.Login(kode);
        }
    }//GEN-LAST:event_focusKeyReleased

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginWithRFID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginWithRFID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginWithRFID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginWithRFID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWithRFID().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JTextField focus;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
