/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Owner;

import PopUp.*;
import Main.Koneksi;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Main.Rupiah;
import com.onbarcode.barcode.EAN13;
import com.onbarcode.barcode.*;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Tria
 */
public class DataProduk extends javax.swing.JFrame {

    private String keyword = "";

    public DataProduk() {
        initComponents();
        ShowTabel();
        HeaderColumn();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
    }

    public void refresh() {
        lbl_harga.setText(null);
        lbl_kode.setText(null);
        lbl_nama.setText(null);
        lbl_stok.setText(null);
        lbl_ukuran.setText(null);
        lbl_varian.setText(null);

        barcode.setIcon(null);
        txt_cari.setText("");
        ShowTabel();
        HeaderColumn();
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
                this.lbl_stok.setText(r.getString("stok"));
                ImageIcon img = new ImageIcon("src" + "/barcode/" + kodeProduk + ".png");
                barcode.setIcon(img);
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
        String key = this.txt_cari.getText();

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
                    res.getString("varian"),});
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
        column.setPreferredWidth(30);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(1);
        column.setPreferredWidth(210);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(2);
        column.setPreferredWidth(160);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(3);
        column.setPreferredWidth(208);
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
        lbl_kode = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        lbl_varian = new javax.swing.JLabel();
        lbl_stok = new javax.swing.JLabel();
        lbl_ukuran = new javax.swing.JLabel();
        lbl_harga = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        barcode = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn_daftar = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_clean = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabelProduk.setBackground(new java.awt.Color(255, 255, 250));
        TabelProduk.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TabelProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 610, 300));

        lbl_kode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lbl_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 170, 30));

        lbl_nama.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 120, 30));

        lbl_varian.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_varian, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, 190, 30));

        lbl_stok.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, 60, 30));

        lbl_ukuran.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_ukuran, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 200, 100, 30));

        lbl_harga.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 200, 100, 30));

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
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 210, 200, 20));
        getContentPane().add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 300, 210, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/dataProduk.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_daftar.setOpaque(false);
        btn_daftar.setContentAreaFilled(false);
        btn_daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 510, 140, 50));

        btn_edit.setOpaque(false);
        btn_edit.setContentAreaFilled(false);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 510, 140, 50));

        btn_clean.setOpaque(false);
        btn_clean.setContentAreaFilled(false);
        btn_clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cleanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 510, 60, 50));

        btn_hapus.setOpaque(false);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 510, 140, 50));

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

    private void btn_daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarActionPerformed

        new PopUp.DaftarProduk(this, rootPaneCheckingEnabled).setVisible(true);
     
    }//GEN-LAST:event_btn_daftarActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        if (this.lbl_kode.getText().equals("")) {
            Informasi.PilihData p = new Informasi.PilihData(this, true);
            p.setVisible(true);
        } else {
            UpdateProduk p = new UpdateProduk(this, true, this.TabelProduk.getValueAt(this.TabelProduk.getSelectedRow(), 1).toString());
            System.out.println("contoh " + this.TabelProduk.getValueAt(this.TabelProduk.getSelectedRow(), 1).toString());
            p.setVisible(true);
            this.ShowTabel();
            refresh();
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cleanActionPerformed
this.keyword = "";

        txt_cari.setText(null);
        ShowTabel();
         HeaderColumn();
        refresh();
    }//GEN-LAST:event_btn_cleanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        if (this.lbl_kode.getText().equals("")) {
            Informasi.PilihData p = new Informasi.PilihData(this, true);
            p.setVisible(true);
        } else {
            Informasi.InginHapusData p = new Informasi.InginHapusData(this, true);
            p.setVisible(true);

            if (p.isClose()) {
                String idP = this.lbl_kode.getText(),
                        sql = "DELETE FROM produk WHERE kode_produk = '" + idP + "'";
                try {
                    Connection conn = (Connection) Main.Koneksi.configDB();
                    Statement stat = conn.createStatement();
                    if (stat.executeUpdate(sql) > 0) {
                        new Informasi.BerhasilHapusData(this, rootPaneCheckingEnabled).setVisible(true);
                        refresh();
                        ShowTabel();
                        HeaderColumn();
                        this.ShowTabel();
                    } else {
                        Informasi.GagalHapusData q = new Informasi.GagalHapusData(this, true);
                        q.setVisible(true);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }

        }    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        new Owner.MenuOwner().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btn_backActionPerformed

    private void TabelProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelProdukMouseClicked
        this.showData(this.TabelProduk.getValueAt(this.TabelProduk.getSelectedRow(), 1).toString());
        int baris = TabelProduk.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_TabelProdukMouseClicked

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed

    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        String key = this.txt_cari.getText();
        this.keyword = "WHERE kode_produk LIKE '%" + key + "%' OR nama_produk LIKE '%" + key + "%' OR varian LIKE '%" + key + "%'";
        this.ShowTabel();
        HeaderColumn();
    }//GEN-LAST:event_txt_cariKeyReleased

    private void txt_cariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyTyped
    }//GEN-LAST:event_txt_cariKeyTyped

    private void txt_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_cariMouseClicked
  lbl_harga.setText(null);
        lbl_kode.setText(null);
        lbl_nama.setText(null);
        lbl_stok.setText(null);
        lbl_ukuran.setText(null);
        lbl_varian.setText(null);

        barcode.setIcon(null);
        txt_cari.setText("");
        ShowTabel();
        HeaderColumn();        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(DataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataProduk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelProduk;
    private javax.swing.JLabel barcode;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_clean;
    private javax.swing.JButton btn_daftar;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_harga;
    private javax.swing.JLabel lbl_kode;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JLabel lbl_stok;
    private javax.swing.JLabel lbl_ukuran;
    private javax.swing.JLabel lbl_varian;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}
