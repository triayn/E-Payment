/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package PopUp;

import Main.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class UpdateSupplier extends javax.swing.JDialog {

    private boolean isClose = false;

    public UpdateSupplier(java.awt.Frame parent, boolean modal, String kode) {
        super(parent, modal);
        initComponents();
        refresh();
        kode_supplier_otomatis();
        showData(kode);
    }

    public boolean isClose() {
        return this.isClose;
    }

    private void showData(String kode) {
        try {
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery("SELECT * FROM supplier WHERE kode_supplier = '" + kode + "'");

            if (r.next()) {
                this.txt_kode.setText(r.getString("kode_supplier"));
                this.txt_nama.setText(r.getString("nama_supplier"));
                this.txt_noTelp.setText(r.getString("no_telp"));
                this.txt_alamat.setText(r.getString("alamat"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void kode_supplier_otomatis() {
        try {
            String sql = "SELECT kode_supplier FROM supplier order by kode_supplier desc";
            Connection conn = (Connection) Main.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String Idno = rs.getString("kode_supplier").substring(2);
                String AN = "" + (Integer.parseInt(Idno) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "0";
                } else if (AN.length() == 3) {
                    Nol = "";
                }
                txt_kode.setText("SP" + Nol + AN);
            } else {
                txt_kode.setText("SP01");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void refresh() {
        txt_kode.setText(null);
        txt_nama.setText(null);
        txt_noTelp.setText(null);
        txt_alamat.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_nama = new javax.swing.JTextField();
        txt_noTelp = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_kode = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_nama.setBorder(null);
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 183, 260, 20));

        txt_noTelp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_noTelp.setBorder(null);
        txt_noTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_noTelpKeyTyped(evt);
            }
        });
        getContentPane().add(txt_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 170, 20));

        txt_alamat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_alamat.setBorder(null);
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 430, 20));

        txt_kode.setEditable(false);
        txt_kode.setBackground(new java.awt.Color(255, 255, 255));
        txt_kode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_kode.setBorder(null);
        getContentPane().add(txt_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 100, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/popupDataSuppplier.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_back.setOpaque(false);
        btn_back.setContentAreaFilled(false);
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 130, 50));

        btn_simpan.setOpaque(false);
        btn_simpan.setContentAreaFilled(false);
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, 130, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String kode = txt_kode.getText();
        String nama = txt_nama.getText();
        String no_telp = txt_noTelp.getText();
        String alamat = txt_alamat.getText();
        if (kode.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (nama.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (no_telp.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (alamat.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else {
            try {
                Statement statement = Main.Koneksi.configDB().createStatement();
                statement.executeUpdate(String.format(
                "UPDATE supplier SET nama_supplier = '%s', no_telp= '%s', "
              + "alamat = '%s' WHERE kode_supplier = '%s'", nama, no_telp, alamat, kode));
                statement.close();
                new Informasi.BerhasilEditData(null, rootPaneCheckingEnabled).setVisible(true);
                this.dispose();
            } catch (Exception e) {
                new Informasi.GagalTambahData(null, rootPaneCheckingEnabled).setVisible(true);
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_noTelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_noTelpKeyTyped

        if(txt_noTelp.getText().equals("")){
            txt_noTelp.setText("+");
        }
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();}          // TODO add your handling code here:
    }//GEN-LAST:event_txt_noTelpKeyTyped

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdateSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateSupplier dialog = new UpdateSupplier(new javax.swing.JFrame(), true, null);
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
    // End of variables declaration//GEN-END:variables
}
