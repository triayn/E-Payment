/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Owner;

import Main.Koneksi;
import PopUp.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
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
public class DataKaryawan extends javax.swing.JFrame {

    private String keyword = "";

    public DataKaryawan() {
        initComponents();
        ShowTabel();      
//        ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
//            this.setIconImage(img.getImage());
        HeaderColumn();
    }

    private void showData(String kode) {
        try {
            String kode_karyawan = TabelKaryawan.getValueAt(this.TabelKaryawan.getSelectedRow(), 1).toString();
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery("SELECT * FROM karyawan WHERE kode_karyawan = '" + kode + "'");

            if (r.next()) {
                this.lbl_kode.setText(r.getString("kode_karyawan"));
                this.lbl_nama.setText(r.getString("nama_karyawan"));
                this.lbl_noTelp.setText(r.getString("no_telp"));
                this.lbl_alamat.setText(r.getString("alamat"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void ShowTabel() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Kode Karyawan");
        tbl.addColumn("Nama");
        tbl.addColumn("Level User");
        TabelKaryawan.setModel(tbl);
        try {
            String sql = "SELECT * FROM karyawan " + keyword;
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("kode_karyawan"),
                    res.getString("nama_karyawan"),
                    res.getString("level_user")
                });
                TabelKaryawan.setModel(tbl);
                HeaderColumn();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void HeaderColumn() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TabelKaryawan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) TabelKaryawan.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TabelKaryawan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        JTableHeader thead = TabelKaryawan.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn column;
        TabelKaryawan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelKaryawan.getColumnModel().getColumn(0);
        column.setPreferredWidth(48);
        TabelKaryawan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelKaryawan.getColumnModel().getColumn(1);
        column.setPreferredWidth(320);
        TabelKaryawan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelKaryawan.getColumnModel().getColumn(2);
        column.setPreferredWidth(420);
        TabelKaryawan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelKaryawan.getColumnModel().getColumn(3);
        column.setPreferredWidth(320);
    }

    private boolean isOwner(String idKy) {
        try {
            String sql = "SELECT level_user FROM karyawan WHERE kode_karyawan = '" + idKy + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            System.out.println(sql);

            if (res.next()) {
                String level = res.getString("level_user");
                return level.equalsIgnoreCase("owner");
            }
        } catch (Exception ex) {
            Informasi.GagalHapusData p = new Informasi.GagalHapusData(this, true);
            p.setVisible(true);
        }
        return false;
    }

    public void refresh() {
        lbl_alamat.setText(null);
        lbl_noTelp.setText(null);
        lbl_nama.setText(null);
        lbl_kode.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelKaryawan = new javax.swing.JTable();
        txt_cari = new javax.swing.JTextField();
        lbl_kode = new javax.swing.JLabel();
        btn_daftar = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_clean = new javax.swing.JButton();
        lbl_noTelp = new javax.swing.JLabel();
        lbl_alamat = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabelKaryawan.setBackground(new java.awt.Color(255, 255, 250));
        TabelKaryawan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TabelKaryawan.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelKaryawan.setAutoscrolls(false);
        TabelKaryawan.setGridColor(new java.awt.Color(255, 255, 255));
        TabelKaryawan.setOpaque(false);
        TabelKaryawan.setRowHeight(30);
        TabelKaryawan.setSelectionBackground(new java.awt.Color(205, 31, 31));
        TabelKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelKaryawanMouseClicked(evt);
            }
        });
        TabelKaryawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TabelKaryawanKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TabelKaryawan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 1110, 250));

        txt_cari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cari.setBorder(null);
        txt_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_cariMouseClicked(evt);
            }
        });
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 210, 200, 20));

        lbl_kode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 120, 30));

        btn_daftar.setOpaque(false);
        btn_daftar.setContentAreaFilled(false);
        btn_daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 540, 140, 50));

        btn_edit.setOpaque(false);
        btn_edit.setContentAreaFilled(false);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 540, 140, 50));

        btn_hapus.setOpaque(false);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 540, 140, 50));

        btn_clean.setOpaque(false);
        btn_clean.setContentAreaFilled(false);
        btn_clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cleanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 540, 70, 50));

        lbl_noTelp.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 200, 30));

        lbl_alamat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, 200, 30));

        lbl_nama.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 200, 30));

        btn_back.setOpaque(false);
        btn_back.setContentAreaFilled(false);
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 100, 80, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/dataKaryawan.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarActionPerformed
        refresh();
        new PopUp.DaftarKaryawan(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btn_daftarActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        if (lbl_kode.getText().equals("")) {
            new Informasi.PilihData(this, rootPaneCheckingEnabled).setVisible(true);
        } else {
            UpdateKaryawan p = new UpdateKaryawan(this, true, this.TabelKaryawan.getValueAt(this.TabelKaryawan.getSelectedRow(), 1).toString());
            System.out.println("contoh " + this.TabelKaryawan.getValueAt(this.TabelKaryawan.getSelectedRow(), 1).toString());
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
        if (lbl_kode.getText().equals("")) {
            new Informasi.PilihData(this, rootPaneCheckingEnabled).setVisible(true);
        } else {
            Informasi.InginHapusData p = new Informasi.InginHapusData(this, true);
            p.setVisible(true);

            if (p.isClose()) {
                String idK = this.lbl_kode.getText(),
                        sql = "DELETE FROM karyawan WHERE kode_karyawan = '" + idK + "'";
                if (this.isOwner(idK)) {
                    System.out.println("LEVEL : " + this.isOwner(idK));
                    Informasi.HapusOwner q = new Informasi.HapusOwner(this, true);
                    q.setVisible(true);
                    this.keyword = "";
        txt_cari.setText(null);
        ShowTabel();
         HeaderColumn();
        refresh();
                    return;
                }
                try {
                    Connection conn = (Connection) Main.Koneksi.configDB();
                    Statement stat = conn.createStatement();
                    if (stat.executeUpdate(sql) > 0) {
                        new Informasi.BerhasilHapusData(this, rootPaneCheckingEnabled).setVisible(true);
                        refresh();
                        HeaderColumn();
                        this.ShowTabel();
                    } else {
                        new Informasi.GagalHapusData(this, rootPaneCheckingEnabled).setVisible(true);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }

                if (this.isOwner(idK)) {
                    System.out.println("LEVEL : " + this.isOwner(idK));
                    new Informasi.HapusOwner(this, rootPaneCheckingEnabled).setVisible(true);
                    return;
                }
            }
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed

        new MenuOwner().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void TabelKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelKaryawanMouseClicked
        this.showData(this.TabelKaryawan.getValueAt(this.TabelKaryawan.getSelectedRow(), 1).toString());
        System.out.println(this.TabelKaryawan.getValueAt(this.TabelKaryawan.getSelectedRow(), 1).toString());
    }//GEN-LAST:event_TabelKaryawanMouseClicked

    private void TabelKaryawanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabelKaryawanKeyPressed
//        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
//        if(evt.getKeyCode() == KeyEvent.VK_UP){
//            this.idSelected = this.TabelKaryawan.getValueAt(TabelKaryawan.getSelectedRow() - 1, 1).toString();
//            System.out.println("ID SELECTED : " + this.idSelected);
//            this.showData();
//        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
//            this.idSelected = this.TabelKaryawan.getValueAt(TabelKaryawan.getSelectedRow() + 1, 1).toString();
//            this.showData();
//        }
//        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_TabelKaryawanKeyPressed

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        String key = this.txt_cari.getText();
        this.keyword = "WHERE kode_karyawan LIKE '%" + key + "%' OR nama_karyawan LIKE '%" + key + "%'";
        this.ShowTabel();
        HeaderColumn();
    }//GEN-LAST:event_txt_cariKeyReleased

    private void txt_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_cariMouseClicked
refresh();
txt_cari.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_txt_cariMouseClicked

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
            java.util.logging.Logger.getLogger(DataKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataKaryawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelKaryawan;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_clean;
    private javax.swing.JButton btn_daftar;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_alamat;
    private javax.swing.JLabel lbl_kode;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JLabel lbl_noTelp;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}
