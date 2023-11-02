package PopUp;

import Main.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class UpdateMember extends javax.swing.JDialog {

    public UpdateMember(java.awt.Frame parent, boolean modal,String kode) {
        super(parent, modal);
        initComponents();
        showData(kode);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_alamat = new javax.swing.JTextField();
        txt_kode = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_noTelp = new javax.swing.JTextField();
        txt_tanggal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_alamat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_alamat.setBorder(null);
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 430, 20));

        txt_kode.setEditable(false);
        txt_kode.setBackground(new java.awt.Color(255, 255, 255));
        txt_kode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_kode.setBorder(null);
        txt_kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeActionPerformed(evt);
            }
        });
        getContentPane().add(txt_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 90, 20));

        txt_nama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_nama.setBorder(null);
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 260, -1));

        txt_noTelp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_noTelp.setBorder(null);
        txt_noTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_noTelpKeyTyped(evt);
            }
        });
        getContentPane().add(txt_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 170, 20));

        txt_tanggal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 230, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/popupDataMember.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_back.setOpaque(false);
        btn_back.setContentAreaFilled(false);
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 140, 50));

        btn_simpan.setOpaque(false);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, 120, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
private void showData(String kode){
        try{
        Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery("SELECT * FROM member WHERE kode_member = '"+kode+"'");
            
            if(r.next()){
                this.txt_kode.setText(r.getString("kode_member"));
                this.txt_nama.setText(r.getString("nama_member"));
                this.txt_noTelp.setText(r.getString("no_telp"));
                this.txt_alamat.setText(r.getString("alamat"));
                this.txt_tanggal.setDate(r.getDate("tanggal_daftar"));
           }
            
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        } 
    }
    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(txt_tanggal.getDate()));
        String kode = txt_kode.getText();
        String nama = txt_nama.getText();
        String notelp = txt_noTelp.getText();
        String alamat = txt_alamat.getText();
        String TanggalMember = tgl;
        System.out.println(tgl);
        if (kode.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (nama.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (notelp.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (alamat.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (TanggalMember.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else {
            try {
                java.sql.Statement statement = Main.Koneksi.configDB().createStatement();
                statement.executeUpdate(String.format("UPDATE member"
                        + " SET nama_member= '%s',"
                        + " no_telp = '%s', "
                        + " alamat = '%s', "
                        + " tanggal_daftar = '%s' "
                        + "WHERE kode_member= '%s'",
                        nama, notelp, alamat, TanggalMember, kode));
                new Informasi.BerhasilTambahData(null, rootPaneCheckingEnabled).setVisible(true);
                this.dispose();
            } catch (Exception e) {
            new Informasi.GagalTambahData(null, rootPaneCheckingEnabled).setVisible(true);    
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodeActionPerformed

    private void txt_noTelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_noTelpKeyTyped

        if(txt_noTelp.getText().equals("")){
            txt_noTelp.setText("+");
        }
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();}          // TODO add your handling code here:
    }//GEN-LAST:event_txt_noTelpKeyTyped

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
            java.util.logging.Logger.getLogger(UpdateMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateMember dialog = new UpdateMember(new javax.swing.JFrame(), true,null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_noTelp;
    private com.toedter.calendar.JDateChooser txt_tanggal;
    // End of variables declaration//GEN-END:variables
}
