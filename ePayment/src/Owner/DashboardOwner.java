/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Owner;

import Frame.*;
import Karyawan.TransaksiBeli;
import Karyawan.TransaksiJual;
import Main.*;
import PopUp.*;
import  Main.BarChartPendapatan;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Tria
 */
public class DashboardOwner extends javax.swing.JFrame {

    /**
     * Creates new form DashboardOwner
     */
    public DashboardOwner() {
        initComponents();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
        this.lblNama.setText("Haii " + Login.getNama());
        showData(Login.namaUser);
        PendapatanHarian();
        PendapatanBulanan();
        ProdukTerjual();
        Pembeli();
        BarChart();
//        new Main.ChartManager().showBarChart(panel_chart, "PENDAPATAN PERBULAN", 5, 2023);
    }

    public void BarChart() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        System.out.println(date);
        String tgl = date.toString();
        try {
//            String sql = "SELECT MONTH(tanggal) AS bulan, YEAR(tanggal) AS tahun FROM transaksi_jual "
//                    + "WHERE DATE(tanggal) = '" + tgl + "'";
            String sql = "SELECT DATE_FORMAT(NOW(), '%c') AS Bulan, DATE_FORMAT(NOW(), '%Y') AS Tahun;";
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                new Main.BarChartPendapatan().showBarChart(panel_chart, "PENDAPATAN BULAN INI", 
                        rs.getInt("bulan"), rs.getInt("tahun"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    public void PendapatanHarian() {
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        String tgl = date.toString();
        try {
            String sql = "SELECT SUM(bayar) AS total FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            Connection conn = (Connection) Koneksi.configDB();
                        System.out.println("SQl Pendapatan harian : "+sql);

            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (res.getString("total") == null) {
                    this.lbl_hari.setText(Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_hari.setText(Rupiah.convertToRupiah(Integer.parseInt(res.getString("total"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    public void PendapatanBulanan() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String tgl = date.toString();

        try {
            String sql = "SELECT SUM(bayar) AS total FROM transaksi_jual WHERE month(tanggal) = "
                    + "'" + tgl.substring(5, 7) + "' ";
                        System.out.println("SQl Pendapatan Bulanan: "+sql);

            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (res.getString("total") == null) {
                    this.lbl_bulan.setText(Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_bulan.setText(Rupiah.convertToRupiah(Integer.parseInt(res.getString("total"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    public void ProdukTerjual() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String tgl = date.toString();
        try {
            String sql = "SELECT SUM(total_item) AS total FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            System.out.println("SQl Produk terjual : "+sql);
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (res.getString("total") == null) {
                    this.lbl_produk.setText("0");
                } else {
                    this.lbl_produk.setText(String.format("%,d", res.getInt("total")).replaceAll(",", "."));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    public void Pembeli() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String tgl = date.toString();
        try {
            String sql = "SELECT COUNT(*) AS jumlah_baris FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            System.out.println("SQl Pembeli : "+sql);

            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (res.getString("jumlah_baris") == null) {
                    this.lbl_pembeli.setText("0");
                } else {
                    this.lbl_pembeli.setText(String.format("%,d", res.getInt("jumlah_baris")).replaceAll(",", "."));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    private void showData(String username) {
        try {
            String query = "SELECT * FROM karyawan WHERE username = '" + username + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.lblNamaO.setText(r.getString("nama_karyawan"));
                lbl_username.setText(Login.namaUser);
                this.lbl_kodeK.setText(r.getString("kode_karyawan"));
                this.lbl_noTelp.setText(r.getString("no_telp"));
                this.lbl_alamat.setText(r.getString("alamat"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_alamat = new javax.swing.JLabel();
        lbl_noTelp = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        lbl_kodeK = new javax.swing.JLabel();
        lblNamaO = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lbl_pembeli = new javax.swing.JLabel();
        lbl_produk = new javax.swing.JLabel();
        lbl_bulan = new javax.swing.JLabel();
        lbl_hari = new javax.swing.JLabel();
        panel_chart = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnProduk = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        menuowner = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_alamat.setBackground(new java.awt.Color(255, 255, 255));
        lbl_alamat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_alamat.setOpaque(true);
        getContentPane().add(lbl_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 540, 180, 30));

        lbl_noTelp.setBackground(new java.awt.Color(255, 255, 255));
        lbl_noTelp.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_noTelp.setOpaque(true);
        getContentPane().add(lbl_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 500, 180, 30));

        lbl_username.setBackground(new java.awt.Color(255, 255, 255));
        lbl_username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_username.setOpaque(true);
        getContentPane().add(lbl_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 470, 180, 30));

        lbl_kodeK.setBackground(new java.awt.Color(255, 255, 255));
        lbl_kodeK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_kodeK.setOpaque(true);
        getContentPane().add(lbl_kodeK, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 430, 180, 30));

        lblNamaO.setBackground(new java.awt.Color(255, 255, 255));
        lblNamaO.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblNamaO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaO.setOpaque(true);
        getContentPane().add(lblNamaO, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 570, 250, 40));

        lblNama.setBackground(new java.awt.Color(255, 255, 255));
        lblNama.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblNama.setOpaque(true);
        getContentPane().add(lblNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 820, 40));

        lbl_pembeli.setBackground(new java.awt.Color(205, 31, 31));
        lbl_pembeli.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        lbl_pembeli.setForeground(new java.awt.Color(255, 255, 255));
        lbl_pembeli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_pembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 210, 220, 80));

        lbl_produk.setBackground(new java.awt.Color(205, 31, 31));
        lbl_produk.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        lbl_produk.setForeground(new java.awt.Color(255, 255, 255));
        lbl_produk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, 220, 80));

        lbl_bulan.setBackground(new java.awt.Color(255, 255, 255));
        lbl_bulan.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        getContentPane().add(lbl_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 290, 280, 40));

        lbl_hari.setBackground(new java.awt.Color(255, 255, 255));
        lbl_hari.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        getContentPane().add(lbl_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 280, 40));

        panel_chart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panel_chart.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panel_chart, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 340, 270));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/01Dashboard.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnEdit.setOpaque(false);
        btnEdit.setContentAreaFilled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 600, 120, 40));

        btnProduk.setOpaque(false);
        btnProduk.setContentAreaFilled(false);
        btnProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdukActionPerformed(evt);
            }
        });
        getContentPane().add(btnProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 200, 50));

        btnTransaksi.setOpaque(false);
        btnTransaksi.setContentAreaFilled(false);
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 200, 50));

        btnSupplier.setOpaque(false);
        btnSupplier.setContentAreaFilled(false);
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });
        getContentPane().add(btnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 200, 40));

        menuowner.setText("menu owner");
        menuowner.setOpaque(false);
        menuowner.setContentAreaFilled(false);
        menuowner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuownerActionPerformed(evt);
            }
        });
        getContentPane().add(menuowner, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, 220, 50));

        btnKeluar.setOpaque(false);
        btnKeluar.setContentAreaFilled(false);
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        getContentPane().add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 600, 120, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuownerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuownerActionPerformed
        this.dispose();
        new MenuOwner().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_menuownerActionPerformed

    private void btnProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdukActionPerformed
        new Karyawan.StokProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnProdukActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        new Karyawan.MenghubungiSupplier().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed

        new Karyawan.MenuTransaksi().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed

        this.dispose();
        new Login().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        new PopUp.Profile(this, rootPaneCheckingEnabled).setVisible(true);

    }//GEN-LAST:event_btnEditActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardOwner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnProduk;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNamaO;
    private javax.swing.JLabel lbl_alamat;
    private javax.swing.JLabel lbl_bulan;
    private javax.swing.JLabel lbl_hari;
    private javax.swing.JLabel lbl_kodeK;
    private javax.swing.JLabel lbl_noTelp;
    private javax.swing.JLabel lbl_pembeli;
    private javax.swing.JLabel lbl_produk;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JButton menuowner;
    private javax.swing.JPanel panel_chart;
    // End of variables declaration//GEN-END:variables
}
