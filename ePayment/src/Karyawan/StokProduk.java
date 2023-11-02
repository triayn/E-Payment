/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Karyawan;

import Frame.Login;
import static Frame.Login.namaUser;
import Informasi.*;
import Informasi.PembayaranKurang;
import Main.Koneksi;
import PopUp.UpdateProduk;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Tria
 */
public class StokProduk extends javax.swing.JFrame {

    private String keyword = "";

    public StokProduk() {
        initComponents();
        HeaderColumn();
        ShowTabel();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
        txt_cari.setText(null);
    }

    private void showData(String kodeProduk) {
        try {
            String query = "SELECT * FROM produk WHERE kode_produk = '" + kodeProduk + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.lbl_kode.setText(r.getString("kode_produk"));
                this.lbl_nama.setText(r.getString("nama_produk"));
                this.lbl_varian.setText(r.getString("varian"));
                this.lbl_ukuran.setText(r.getString("ukuran"));
                this.lbl_harga.setText(r.getString("harga"));
                this.txt_stok.setText(r.getString("stok"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void ShowTabel() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Kode Produk");
        tbl.addColumn("Nama");
        tbl.addColumn("Varian");
        TabelProduk.setModel(tbl);
        try {
            String sql = "SELECT * FROM produk " + keyword;
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("kode_produk"),
                    res.getString("nama_produk"),
                    res.getString("varian")
                });
                TabelProduk.setModel(tbl);
                HeaderColumn();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void HeaderColumn() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) TabelProduk.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TabelProduk.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        JTableHeader thead = TabelProduk.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn column;
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(0);
        column.setPreferredWidth(70);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(1);
        column.setPreferredWidth(300);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(2);
        column.setPreferredWidth(310);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(3);
        column.setPreferredWidth(308);
    }

    private void refresh() {
        lbl_harga.setText(null);
        lbl_kode.setText(null);
        lbl_nama.setText(null);
        txt_stok.setText(null);
        lbl_ukuran.setText(null);
        lbl_varian.setText(null);
        txt_cari.setText(null);
        ShowTabel();
         
        HeaderColumn();
    }

    public static String username = "", kode_karyawan = "", nama = "";
    static boolean admin = false;

    public void pilihDashboard() {
        String Username = Login.getUsernamee();
        String Password = Login.getPassword();
        System.out.println("INI USERNAME ...." + Username);
        System.out.println(Password);
        try {
            String sql = "SELECT * FROM karyawan WHERE username=" + "'" + Username + "'" + " AND password='" + Password + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            System.out.println("Koneksi Berhasil!!");

            if (r.next()) {
                namaUser = r.getString("username");
                if (r.getString("level_user").equalsIgnoreCase("owner")) {
                    System.out.println("user admin");
                    new Owner.DashboardOwner().setVisible(true);
                    this.dispose();
                    admin = true;
                } else {
                    System.out.println("user karyawan");
                    new DashboardKaryawan().setVisible(true);
                    this.dispose();
                    admin = false;
                }

            } else {
                System.out.println("GAGAL KE DASHBOARD");
            }
        } catch (Exception e) {
            System.out.println("GAGAL KE DASHBOARD");

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

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelProduk = new javax.swing.JTable();
        lbl_harga = new javax.swing.JLabel();
        lbl_ukuran = new javax.swing.JLabel();
        lbl_varian = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        lbl_kode = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        txt_stok = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_editstok = new javax.swing.JButton();
        btn_clean = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabelProduk.setBackground(new java.awt.Color(255, 255, 250));
        TabelProduk.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TabelProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelProduk.setAutoscrolls(false);
        TabelProduk.setGridColor(new java.awt.Color(255, 255, 255));
        TabelProduk.setOpaque(false);
        TabelProduk.setRowHeight(30);
        TabelProduk.setSelectionBackground(new java.awt.Color(205, 31, 31));
        TabelProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelProdukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelProduk);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 990, 270));

        lbl_harga.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 550, 100, 30));

        lbl_ukuran.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_ukuran, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 550, 90, 30));

        lbl_varian.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_varian, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 550, 190, 30));

        lbl_nama.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, 120, 30));

        lbl_kode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lbl_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, 170, 30));

        txt_cari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cari.setBorder(null);
        txt_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_cariMouseClicked(evt);
            }
        });
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cariKeyTyped(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 200, 20));

        txt_stok.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_stok.setBorder(null);
        txt_stok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stokKeyTyped(evt);
            }
        });
        getContentPane().add(txt_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 550, 50, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/produk.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_editstok.setOpaque(false);
        btn_editstok.setContentAreaFilled(false);
        btn_editstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editstokActionPerformed(evt);
            }
        });
        getContentPane().add(btn_editstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 610, 180, 50));

        btn_clean.setOpaque(false);
        btn_clean.setContentAreaFilled(false);
        btn_clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cleanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 610, 60, 50));

        btnDashboard.setOpaque(false);
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        getContentPane().add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 200, 50));

        btnSupplier.setOpaque(false);
        btnSupplier.setContentAreaFilled(false);
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });
        getContentPane().add(btnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 200, 40));

        btnTransaksi.setOpaque(false);
        btnTransaksi.setContentAreaFilled(false);
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 200, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TabelProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelProdukMouseClicked
txt_stok.requestFocus();
        this.showData(this.TabelProduk.getValueAt(this.TabelProduk.getSelectedRow(), 1).toString());
    }//GEN-LAST:event_TabelProdukMouseClicked

    private void btn_cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cleanActionPerformed
  refresh();
  String key = this.txt_cari.getText();
        this.keyword = " WHERE kode_produk LIKE '%" + key + "%' OR nama_produk LIKE '%" + key + "%' OR varian LIKE '%" + key + "%'";
        this.ShowTabel();
        HeaderColumn();
      
    }//GEN-LAST:event_btn_cleanActionPerformed

    private void btn_editstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editstokActionPerformed
        String kode = lbl_kode.getText();
        String stok = txt_stok.getText();

        if (kode.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else if (stok.equals("")) {
            new Informasi.HarapDiisiSemua(null, rootPaneCheckingEnabled).setVisible(true);
        } else {

            try {
                Statement statement = Main.Koneksi.configDB().createStatement();
                statement.executeUpdate(String.format("UPDATE produk SET stok = '%s' WHERE kode_produk = '%s'", stok, kode));
                statement.close();
                new Informasi.BerhasilEditData(null, rootPaneCheckingEnabled).setVisible(true);
            } catch (Exception e) {
                GagalEditData p = new GagalEditData(this, true);
                p.setVisible(true);
//                JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan\n" + e.getMessage() + "\nCoba di teliti dulu code nya");
            }
        }

        this.ShowTabel();
        refresh();
    }//GEN-LAST:event_btn_editstokActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_cariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyTyped
        ShowTabel();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariKeyTyped

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        String key = this.txt_cari.getText();
        this.keyword = " WHERE kode_produk LIKE '%" + key + "%' OR nama_produk LIKE '%" + key + "%' OR varian LIKE '%" + key + "%'";
        this.ShowTabel();
        HeaderColumn();
    }//GEN-LAST:event_txt_cariKeyReleased

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        this.pilihDashboard();
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        new MenghubungiSupplier().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
        new MenuTransaksi().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void txt_stokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stokKeyTyped
char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();}
    }//GEN-LAST:event_txt_stokKeyTyped

    private void txt_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_cariMouseClicked
refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariMouseClicked

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
            java.util.logging.Logger.getLogger(StokProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StokProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StokProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StokProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StokProduk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelProduk;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JButton btn_clean;
    private javax.swing.JButton btn_editstok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_harga;
    private javax.swing.JLabel lbl_kode;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JLabel lbl_ukuran;
    private javax.swing.JLabel lbl_varian;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_stok;
    // End of variables declaration//GEN-END:variables
}
