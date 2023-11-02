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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import Main.Koneksi;
import PopUp.*;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class DataMember extends javax.swing.JFrame {

    private String keyword = "";

    public DataMember() {
        initComponents();
        refresh();
        ShowTabel();
        ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
        this.setIconImage(img.getImage());
        HeaderColumn();
    }

    private void showData(String kode) {
        try {
            kode = TabelMember.getValueAt(this.TabelMember.getSelectedRow(), 1).toString();
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery("SELECT * FROM member WHERE kode_member = '" + kode + "'");

            if (r.next()) {
                this.lbl_kode.setText(r.getString("kode_member"));
                this.lbl_nama.setText(r.getString("nama_member"));
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
        tbl.addColumn("Kode Member");
        tbl.addColumn("Nama Member");
        tbl.addColumn("Tanggal Daftar");
        TabelMember.setModel(tbl);
        try {
            String sql = "SELECT * FROM member " + keyword;
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("kode_Member"),
                    res.getString("nama_member"),
                    res.getString("tanggal_daftar")
                });
                TabelMember.setModel(tbl);
                HeaderColumn();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void HeaderColumn() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TabelMember.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) TabelMember.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TabelMember.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        JTableHeader thead = TabelMember.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn column;
        TabelMember.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelMember.getColumnModel().getColumn(0);
        column.setPreferredWidth(70);
        TabelMember.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelMember.getColumnModel().getColumn(1);
        column.setPreferredWidth(304);
        TabelMember.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelMember.getColumnModel().getColumn(2);
        column.setPreferredWidth(404);
        TabelMember.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelMember.getColumnModel().getColumn(3);
        column.setPreferredWidth(300);
    }

    public void refresh() {
        lbl_alamat.setText(null);
        lbl_noTelp.setText(null);
        lbl_nama.setText(null);
        lbl_kode.setText(null);
        txt_cari.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelMember = new javax.swing.JTable();
        lbl_kode = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        lbl_noTelp = new javax.swing.JLabel();
        lbl_alamat = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_daftar = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_clean = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabelMember.setBackground(new java.awt.Color(255, 255, 250));
        TabelMember.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TabelMember.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelMember.setAutoscrolls(false);
        TabelMember.setGridColor(new java.awt.Color(205, 31, 31));
        TabelMember.setOpaque(false);
        TabelMember.setRowHeight(30);
        TabelMember.setSelectionBackground(new java.awt.Color(205, 31, 31));
        TabelMember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMemberMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelMember);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 1080, 260));

        lbl_kode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 120, 30));

        lbl_nama.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 200, 30));

        lbl_noTelp.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_noTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 200, 30));

        lbl_alamat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, 200, 30));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/dataMember.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
        new PopUp.DaftarMember(this, rootPaneCheckingEnabled).setVisible(true);
        refresh();
    }//GEN-LAST:event_btn_daftarActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        if (this.lbl_kode.getText().equals("")) {
            Informasi.PilihData p = new Informasi.PilihData(this, true);
            p.setVisible(true);
        } else {
            UpdateMember p = new UpdateMember(this, true, this.TabelMember.getValueAt(this.
                                                TabelMember.getSelectedRow(), 1).toString());
            p.setVisible(true);
            this.ShowTabel();
            refresh();
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        if (this.lbl_kode.getText().equals("")) {
            Informasi.PilihData p = new Informasi.PilihData(this, true);
            p.setVisible(true);
        } else {
            Informasi.InginHapusData p = new Informasi.InginHapusData(this, true);
            p.setVisible(true);
            if (p.isClose()) {
                String idM = this.lbl_kode.getText(),
                        sql = "DELETE FROM member WHERE kode_member = '" + idM + "'";
                try {
                    Connection conn = (Connection) Main.Koneksi.configDB();
                    Statement stat = conn.createStatement();
                    if (stat.executeUpdate(sql) > 0) {
                        new Informasi.BerhasilHapusData(this, rootPaneCheckingEnabled).setVisible(true);
                        refresh();
                        HeaderColumn();
                        this.ShowTabel();
                    } else {
                        Informasi.GagalHapusData q = new Informasi.GagalHapusData(this, true);
                        q.setVisible(true);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }}}
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cleanActionPerformed
        this.keyword = "";

        txt_cari.setText(null);
        ShowTabel();
        HeaderColumn();
        refresh();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cleanActionPerformed

    private void TabelMemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMemberMouseClicked
        this.showData(this.TabelMember.getValueAt(this.TabelMember.getSelectedRow(), 1).toString());
    }//GEN-LAST:event_TabelMemberMouseClicked

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        String key = this.txt_cari.getText();
        this.keyword = "WHERE kode_member LIKE '%" + key + "%' OR nama_member LIKE '%" + key + "%'";
        this.ShowTabel();
        HeaderColumn();
    }//GEN-LAST:event_txt_cariKeyReleased

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        new Owner.MenuOwner().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btn_backActionPerformed

    private void txt_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_cariMouseClicked
        refresh();
        txt_cari.setText("");// TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(DataMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataMember().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelMember;
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
