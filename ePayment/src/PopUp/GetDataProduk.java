/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package PopUp;

import Karyawan.TransaksiJual;
import Main.Koneksi;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class GetDataProduk extends javax.swing.JDialog {

    public GetDataProduk(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ShowTabel();
        HeaderColumn();
        TabelProduk.requestFocus();
        
    }

    private void showData(String kodeProduk) {
        try {
            String query = "SELECT * FROM produk WHERE kode_produk = '" + kodeProduk + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
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
        tbl.addColumn("Ukuran");
        tbl.addColumn("Harga");
        tbl.addColumn("Stok");
        TabelProduk.setModel(tbl);
        String key = this.txt_cari.getText();

        try {
            
            String sql = "SELECT * FROM produk "+ keyword;
            System.out.println("SQL "+sql);
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("kode_produk"),
                    res.getString("nama_produk"),
                    res.getString("varian"),
                    res.getString("ukuran"),
                    res.getString("harga"),
                    res.getString("stok")
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
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        TabelProduk.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        TabelProduk.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) TabelProduk.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        TabelProduk.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        JTableHeader thead = TabelProduk.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 12));

        TableColumn column;
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(1);
        column.setPreferredWidth(198);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(2);
        column.setPreferredWidth(110);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(3);
        column.setPreferredWidth(135);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(4);
        column.setPreferredWidth(90);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(5);
        column.setPreferredWidth(90);
        TabelProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelProduk.getColumnModel().getColumn(6);
        column.setPreferredWidth(70);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelProduk = new javax.swing.JTable();
        txt_cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_pilih = new javax.swing.JButton();
        backk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        TabelProduk.setBackground(new java.awt.Color(255, 255, 250));
        TabelProduk.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TabelProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
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
        TabelProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TabelProdukKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TabelProdukKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TabelProduk);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 746, 217));

        txt_cari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cari.setBorder(null);
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
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 98, 190, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/popupProduk.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_pilih.setOpaque(false);
        btn_pilih.setContentAreaFilled(false);
        btn_pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihActionPerformed(evt);
            }
        });
        getContentPane().add(btn_pilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 380, 110, 50));

        backk.setOpaque(false);
        backk.setContentAreaFilled(false);
        backk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backkActionPerformed(evt);
            }
        });
        getContentPane().add(backk, new org.netbeans.lib.awtextra.AbsoluteConstraints(529, 380, 130, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariActionPerformed
private String keyword = "";
    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased

        String key = this.txt_cari.getText();
        this.keyword = "WHERE kode_produk LIKE '%" + key + "%' OR nama_produk LIKE '%" + key + "%' OR varian LIKE '%" + key + "%'";
        this.ShowTabel();
        HeaderColumn();
    }//GEN-LAST:event_txt_cariKeyReleased

    private void txt_cariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyTyped
ShowTabel();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariKeyTyped

    private void TabelProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelProdukMouseClicked

    }//GEN-LAST:event_TabelProdukMouseClicked

    private void TabelProdukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabelProdukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            this.dispose();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int p = TabelProduk.getSelectedRow();

            String id = TabelProduk.getValueAt(p, 1).toString();
            String nama = TabelProduk.getValueAt(p, 2).toString();
            String varian = TabelProduk.getValueAt(p, 3).toString();
            String ukuran = TabelProduk.getValueAt(p, 4).toString();
            String harga = TabelProduk.getValueAt(p, 5).toString();
            String stok = TabelProduk.getValueAt(p, 6).toString();

            Karyawan.TransaksiJual.txt_kodeP.setText(id);
            Karyawan.TransaksiJual.lbl_varian.setText(varian);
            Karyawan.TransaksiJual.lbl_ukuran.setText(ukuran);

            Karyawan.TransaksiJual.lbl_namaP.setText(nama + " " + varian + " " + ukuran);
            Karyawan.TransaksiJual.lbl_harga.setText(harga);
            Karyawan.TransaksiJual.lbl_stok.setText(stok);
            Karyawan.TransaksiJual.txt_jumlah.requestFocus();
            this.dispose();
        }

    }//GEN-LAST:event_TabelProdukKeyPressed

    private void TabelProdukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabelProdukKeyReleased

    }//GEN-LAST:event_TabelProdukKeyReleased

    private void btn_pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilihActionPerformed

        int p = TabelProduk.getSelectedRow();

        String id = TabelProduk.getValueAt(p, 1).toString();
        String nama = TabelProduk.getValueAt(p, 2).toString();
        String varian = TabelProduk.getValueAt(p, 3).toString();
        String ukuran = TabelProduk.getValueAt(p, 4).toString();
        String harga = TabelProduk.getValueAt(p, 5).toString();
        String stok = TabelProduk.getValueAt(p, 6).toString();

        Karyawan.TransaksiJual.txt_kodeP.setText(id);
        Karyawan.TransaksiJual.lbl_varian.setText(varian);
        Karyawan.TransaksiJual.lbl_ukuran.setText(ukuran);

        Karyawan.TransaksiJual.lbl_namaP.setText(nama + " " + varian + " " + ukuran);
        Karyawan.TransaksiJual.lbl_harga.setText(harga);
        Karyawan.TransaksiJual.lbl_stok.setText(stok);
        Karyawan.TransaksiJual.txt_jumlah.requestFocus();
        this.dispose();
    }//GEN-LAST:event_btn_pilihActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        this.showData(this.TabelProduk.getValueAt(this.TabelProduk.getSelectedRow(), 1).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void backkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backkActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_backkActionPerformed

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
            java.util.logging.Logger.getLogger(GetDataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetDataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetDataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetDataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GetDataProduk dialog = new GetDataProduk(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTable TabelProduk;
    private javax.swing.JButton backk;
    private javax.swing.JButton btn_pilih;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}
