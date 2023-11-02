package Owner;

import Main.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MenuOwner extends javax.swing.JFrame {

    public MenuOwner() {
        initComponents();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
        jumlah_karyawan();
        jumlah_produk();
        jumlah_Member();
        jumlah_Supplier();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jumlah_supplier = new javax.swing.JLabel();
        jumlah_Member = new javax.swing.JLabel();
        jumlah_Produk = new javax.swing.JLabel();
        jumlah_karyawan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        DataKaryawan = new javax.swing.JButton();
        DataProduk = new javax.swing.JButton();
        DataMember = new javax.swing.JButton();
        DataSupplier = new javax.swing.JButton();
        LaporanPengeluaran = new javax.swing.JButton();
        LaporanPendapatan = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jumlah_supplier.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(jumlah_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 410, 60, 20));

        jumlah_Member.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(jumlah_Member, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, 60, 20));

        jumlah_Produk.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(jumlah_Produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 410, 60, 20));

        jumlah_karyawan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jumlah_karyawan.setText("50");
        getContentPane().add(jumlah_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 60, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/menuOwner.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        DataKaryawan.setText("jButton1");
        DataKaryawan.setOpaque(false);
        DataKaryawan.setContentAreaFilled(false);
        DataKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataKaryawanActionPerformed(evt);
            }
        });
        getContentPane().add(DataKaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 160, 210));

        DataProduk.setText("jButton1");
        DataProduk.setOpaque(false);
        DataProduk.setContentAreaFilled(false);
        DataProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataProdukActionPerformed(evt);
            }
        });
        getContentPane().add(DataProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 160, 210));

        DataMember.setText("jButton1");
        DataMember.setOpaque(false);
        DataMember.setContentAreaFilled(false);
        DataMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataMemberActionPerformed(evt);
            }
        });
        getContentPane().add(DataMember, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 160, 210));

        DataSupplier.setText("jButton1");
        DataSupplier.setOpaque(false);
        DataSupplier.setContentAreaFilled(false);
        DataSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataSupplierActionPerformed(evt);
            }
        });
        getContentPane().add(DataSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, 170, 210));

        LaporanPengeluaran.setText("jButton1");
        LaporanPengeluaran.setOpaque(false);
        LaporanPengeluaran.setContentAreaFilled(false);
        LaporanPengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanPengeluaranActionPerformed(evt);
            }
        });
        getContentPane().add(LaporanPengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 390, 140, -1));

        LaporanPendapatan.setText("jButton1");
        LaporanPendapatan.setOpaque(false);
        LaporanPendapatan.setContentAreaFilled(false);
        LaporanPendapatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanPendapatanActionPerformed(evt);
            }
        });
        getContentPane().add(LaporanPendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 420, 160, 20));

        btn_back.setOpaque(false);
        btn_back.setContentAreaFilled(false);
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 100, 80, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public void jumlah_karyawan() {
        try {
            String query = "SELECT COUNT(*) AS JumlahKaryawan FROM karyawan;";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.jumlah_karyawan.setText(r.getString("JumlahKaryawan"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }
public void jumlah_produk() {
        try {
            String query = "SELECT COUNT(*) AS jumlah FROM produk;";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.jumlah_Produk.setText(r.getString("jumlah"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }
public void jumlah_Member() {
        try {
            String query = "SELECT COUNT(*) AS jumlah FROM member;";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.jumlah_Member.setText(r.getString("jumlah"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }
public void jumlah_Supplier() {
        try {
            String query = "SELECT COUNT(*) AS jumlah FROM supplier;";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.jumlah_supplier.setText(r.getString("jumlah"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }
    private void DataKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataKaryawanActionPerformed
        new DataKaryawan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_DataKaryawanActionPerformed

    private void DataProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataProdukActionPerformed
        new DataProduk().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_DataProdukActionPerformed

    private void DataMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataMemberActionPerformed
        new DataMember().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_DataMemberActionPerformed

    private void DataSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataSupplierActionPerformed
        new DataSupplier().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_DataSupplierActionPerformed

    private void LaporanPengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanPengeluaranActionPerformed
  new LaporanPengeluaran().setVisible(true);
        this.dispose();         
    }//GEN-LAST:event_LaporanPengeluaranActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed

        new DashboardOwner().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void LaporanPendapatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanPendapatanActionPerformed
  new LaporanPendapatan().setVisible(true);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_LaporanPendapatanActionPerformed

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
            java.util.logging.Logger.getLogger(MenuOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuOwner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DataKaryawan;
    private javax.swing.JButton DataMember;
    private javax.swing.JButton DataProduk;
    private javax.swing.JButton DataSupplier;
    private javax.swing.JButton LaporanPendapatan;
    private javax.swing.JButton LaporanPengeluaran;
    private javax.swing.JButton btn_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jumlah_Member;
    private javax.swing.JLabel jumlah_Produk;
    private javax.swing.JLabel jumlah_karyawan;
    private javax.swing.JLabel jumlah_supplier;
    // End of variables declaration//GEN-END:variables
}
