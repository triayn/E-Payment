/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frame;

import PopUp.*;
import Main.*;
import Frame.*;
import Informasi.*;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO; 
import java.sql.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;


public class Login extends javax.swing.JFrame {

    public static String username = "", kode_karyawan = "", nama = "";
    static boolean admin = false;
    
    public Login() {
        initComponents();
        this.ic_hidden.setVisible(false);
        ic_show.setVisible(true);
        showPassword();
        hiddenPassword();
         ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
    }
    
    public void showPassword() {
        this.ic_show.setVisible(false);
        this.ic_show.setEnabled(false);
        this.ic_hidden.setVisible(true);
        this.ic_hidden.setEnabled(true);
        this.txt_password.setEchoChar(((char)0));
    }

    public void hiddenPassword(){
       this.ic_hidden.setVisible(false);
       this.ic_hidden.setEnabled(false);
       this.ic_show.setVisible(true);
       this.ic_show.setEnabled(true);
       this.txt_password.setEchoChar('*');
    }
    
    public static String getNama(){
        try{
            Connection c = (Connection) Koneksi.configDB();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM karyawan WHERE username = '"+username+"'");
            
            if(r.next()){
                return r.getString("nama_karyawan");
            }
        }catch(SQLException ex){
            
        }
        return "null";
    }
   
   public static String getIdK() {
       try {
           Connection conn = (Connection) Koneksi.configDB();
           Statement st = conn.createStatement();
           ResultSet res = st.executeQuery("SELECT * FROM karyawan WHERE username = '"+username+"'");
           if (res.next()) {
               return res.getString("kode_karyawan");
           }
       } catch (SQLException ex){
           
       }
       return "null";
   }
   public static String getUsernamee() {
       try {
           Connection conn = (Connection) Koneksi.configDB();
           Statement st = conn.createStatement();
           ResultSet res = st.executeQuery("SELECT * FROM karyawan WHERE username = '"+username+"'");
           if (res.next()) {
               return res.getString("username");
           }
       } catch (SQLException ex){
           
       }
       return "null";
   }
   public static String getPassword() {
       try {
           Connection conn = (Connection) Koneksi.configDB();
           Statement st = conn.createStatement();
           ResultSet res = st.executeQuery("SELECT * FROM karyawan WHERE username = '"+username+"'");
           if (res.next()) {
               return res.getString("password");
           }
       } catch (SQLException ex){
           
       }
       return "null";
   }
   
   public static String namaUser;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ic_show = new javax.swing.JLabel();
        ic_hidden = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        txt_username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_masuk = new javax.swing.JButton();
        btn_lupaPass = new javax.swing.JButton();
        MasukDenganKartu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ic_show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Show.png"))); // NOI18N
        ic_show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_showMouseClicked(evt);
            }
        });
        getContentPane().add(ic_show, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 290, 40, 30));

        ic_hidden.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic_hidden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ic_Hidden.png"))); // NOI18N
        ic_hidden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ic_hiddenMouseClicked(evt);
            }
        });
        getContentPane().add(ic_hidden, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 290, 40, 30));

        txt_password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_password.setBorder(null);
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passwordKeyPressed(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 330, 30));

        txt_username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_username.setBorder(null);
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 330, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Login_1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_masuk.setOpaque(false);
        btn_masuk.setContentAreaFilled(false);
        btn_masuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_masukActionPerformed(evt);
            }
        });
        getContentPane().add(btn_masuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 200, 60));

        btn_lupaPass.setOpaque(false);
        btn_lupaPass.setContentAreaFilled(false);
        btn_lupaPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lupaPassActionPerformed(evt);
            }
        });
        getContentPane().add(btn_lupaPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 210, 40));

        MasukDenganKartu.setText("jButton1");
        MasukDenganKartu.setOpaque(false);
        MasukDenganKartu.setContentAreaFilled(false);
        MasukDenganKartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasukDenganKartuActionPerformed(evt);
            }
        });
        getContentPane().add(MasukDenganKartu, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 470, 270, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ic_showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_showMouseClicked
        this.showPassword();
    }//GEN-LAST:event_ic_showMouseClicked

    private void btn_masukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_masukActionPerformed
        String Username = txt_username.getText();
        String Password = txt_password.getText();
        username = Username;
        this.setVisible(true);

        try{
            String sql = "SELECT * FROM karyawan WHERE username="+"'"+Username+"'"+" AND password='"+Password+"'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            System.out.println("Koneksi Berhasil!!");

            if(r.next()){
                namaUser = r.getString("username");
                BerhasilLogin p = new BerhasilLogin(this, true);
                p.setVisible(true);
                    if(r.getString("level_user").equalsIgnoreCase("owner")) {
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
            }
        } catch(Exception e) {
            GagalLogin r = new GagalLogin(this, true);
            r.setVisible(true);
        }
    }//GEN-LAST:event_btn_masukActionPerformed

    private void ic_hiddenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_hiddenMouseClicked
        this.hiddenPassword();
    }//GEN-LAST:event_ic_hiddenMouseClicked

    private void btn_lupaPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lupaPassActionPerformed
        new Frame.LupaPassword(this, rootPaneCheckingEnabled).setVisible(true);

    }//GEN-LAST:event_btn_lupaPassActionPerformed

    private void MasukDenganKartuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasukDenganKartuActionPerformed
        new Frame.LoginWithRFID().setVisible(true);
this.dispose();
    }//GEN-LAST:event_MasukDenganKartuActionPerformed

    private void txt_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         String Username = txt_username.getText();
        String Password = txt_password.getText();
        username = Username;
        this.setVisible(true);

        try{
            String sql = "SELECT * FROM karyawan WHERE username="+"'"+Username+"'"+" AND password='"+Password+"'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            System.out.println("Koneksi Berhasil!!");

            if(r.next()){
                namaUser = r.getString("username");
                BerhasilLogin p = new BerhasilLogin(this, true);
                p.setVisible(true);
                    if(r.getString("level_user").equalsIgnoreCase("owner")) {
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
            }
        } catch(Exception e) {
            GagalLogin r = new GagalLogin(this, true);
            r.setVisible(true);
        }   
}// TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordKeyPressed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MasukDenganKartu;
    private javax.swing.JButton btn_lupaPass;
    private javax.swing.JButton btn_masuk;
    private javax.swing.JLabel ic_hidden;
    private javax.swing.JLabel ic_show;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
