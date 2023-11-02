/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package PopUp;

import com.onbarcode.barcode.Code128;
import com.onbarcode.barcode.EAN128;
import com.onbarcode.barcode.IBarcode;
import java.awt.Font;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Tria
 */
public class DaftarProduk extends javax.swing.JDialog {

    /**
     * Creates new form Produk
     */
    public DaftarProduk(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        refresh();
        kode_produk_otomatis();
    }

    private void generate(String kode) throws Exception {
        Code128 barcode = new Code128();
        
        barcode.setData(kode);
        barcode.setUom(IBarcode.UOM_PIXEL);
        
        barcode.setX(1f);
        barcode.setY(35f);
        
        barcode.setLeftMargin(0f);
        barcode.setRightMargin(0f);
        barcode.setTopMargin(0f);
        barcode.setBottomMargin(0f);
        
        barcode.setResolution(52);
        barcode.setShowText(true);
        barcode.setTextFont(new Font("Arial", 0, 20));
        barcode.setTextMargin(6);
        
        barcode.setRotate(IBarcode.ROTATE_0);
        barcode.drawBarcode("src" + "/" + "/Barcode/" + kode + ".png");
        System.out.println(kode);
    }

    public void kode_produk_otomatis() {
        try {
            String sql = "SELECT kode_produk FROM produk order by kode_produk desc";
            Connection conn = (Connection) Main.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String Idno = rs.getString("kode_produk").substring(14);
                String AN = "" + (Integer.parseInt(Idno) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "00";
                } else if (AN.length() == 4) {
                    Nol = "0";
                } else if (AN.length() == 5) {
                    Nol = "";
                }
                txt_kode.setText("1111-BASRENG-" + Nol + AN);
            } else {
                txt_kode.setText("1111-BASRENG-0001");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void refresh() {
        txt_nama.setText(null);
        txt_varian.setText(null);
        txt_ukuran.setText(null);
        txt_harga.setText(null);
        txt_stok.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_kode = new javax.swing.JTextField();
        txt_stok = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_varian = new javax.swing.JTextField();
        txt_ukuran = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_kode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_kode.setBorder(null);
        getContentPane().add(txt_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 190, 20));

        txt_stok.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_stok.setBorder(null);
        txt_stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stokActionPerformed(evt);
            }
        });
        txt_stok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stokKeyTyped(evt);
            }
        });
        getContentPane().add(txt_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 40, 20));

        txt_harga.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_harga.setBorder(null);
        txt_harga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_hargaKeyTyped(evt);
            }
        });
        getContentPane().add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 130, 20));

        txt_nama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_nama.setBorder(null);
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 140, 20));

        txt_varian.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_varian.setBorder(null);
        getContentPane().add(txt_varian, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 190, 20));

        txt_ukuran.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_ukuran.setBorder(null);
        getContentPane().add(txt_ukuran, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 90, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/popupDataProduk.png"))); // NOI18N
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
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String kode = txt_kode.getText();
        String nama = txt_nama.getText();
        String varian = txt_varian.getText();
        String ukuran = txt_ukuran.getText();
        String harga = txt_harga.getText();
        String stok = txt_stok.getText();
        if (kode.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (nama.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (varian.equals("")) {
           new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (ukuran.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (harga.equals("")) {
           new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (stok.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else {

            try {
                java.sql.Statement statement = Main.Koneksi.configDB().createStatement();
                statement.executeUpdate("insert into produk VALUES ('" + kode + "','" + nama + "','" 
                        + varian + "','" + ukuran + "','" + harga + "','" + stok + "');");
                statement.close();
               
                new Informasi.BerhasilTambahData(null, rootPaneCheckingEnabled).setVisible(true);
                new Owner.DataProduk().setVisible(true);
                this.dispose();
                refresh();
                generate(kode);
                kode_produk_otomatis();
            } catch (Exception e) {
                new Informasi.GagalTambahData(null, rootPaneCheckingEnabled).setVisible(true);
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stokActionPerformed

    private void txt_stokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stokKeyTyped

     
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();}               // TODO add your handling code here:
    }//GEN-LAST:event_txt_stokKeyTyped

    private void txt_hargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hargaKeyTyped

        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();}               // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargaKeyTyped

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
            java.util.logging.Logger.getLogger(DaftarProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DaftarProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DaftarProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DaftarProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DaftarProduk dialog = new DaftarProduk(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_stok;
    private javax.swing.JTextField txt_ukuran;
    private javax.swing.JTextField txt_varian;
    // End of variables declaration//GEN-END:variables
}
