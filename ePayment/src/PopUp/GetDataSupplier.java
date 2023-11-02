/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package PopUp;

import Main.Koneksi;
import static PopUp.GetDataProduk.TabelProduk;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class GetDataSupplier extends javax.swing.JDialog {

    public GetDataSupplier(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ShowTabel();
        HeaderColumn();
        TabelSupplier.requestFocus();
    }

    public void HeaderColumn() {
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();

        center.setHorizontalAlignment(JLabel.CENTER);
        TabelSupplier.getColumnModel().getColumn(0).setCellRenderer(center);
        TabelSupplier.getColumnModel().getColumn(1).setCellRenderer(center);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) TabelSupplier.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = TabelSupplier.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn column;
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(1);
        column.setPreferredWidth(128);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(2);
        column.setPreferredWidth(170);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(3);
        column.setPreferredWidth(180);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(4);
        column.setPreferredWidth(200);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

    }

    public void ShowTabel() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Kode Supplier");
        tbl.addColumn("Nama");
        tbl.addColumn("No Telepon");
        tbl.addColumn("Alamat");
        TabelSupplier.setModel(tbl);
        try {
            String sql = "SELECT * FROM supplier "+keyword;
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("kode_supplier"),
                    res.getString("nama_supplier"),
                    res.getString("no_telp"),
                    res.getString("alamat")
                });
                TabelSupplier.setModel(tbl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelSupplier = new javax.swing.JTable();
        txt_cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_pilih = new javax.swing.JButton();
        backk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabelSupplier.setBackground(new java.awt.Color(255, 255, 250));
        TabelSupplier.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TabelSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        TabelSupplier.setAutoscrolls(false);
        TabelSupplier.setGridColor(new java.awt.Color(205, 31, 31));
        TabelSupplier.setOpaque(false);
        TabelSupplier.setRowHeight(30);
        TabelSupplier.setSelectionBackground(new java.awt.Color(205, 31, 31));
        TabelSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelSupplierMouseClicked(evt);
            }
        });
        TabelSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TabelSupplierKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TabelSupplier);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 730, 200));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/popupSupplier.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_pilih.setOpaque(false);
        btn_pilih.setContentAreaFilled(false);
        btn_pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihActionPerformed(evt);
            }
        });
        getContentPane().add(btn_pilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 370, 110, 50));

        backk.setOpaque(false);
        backk.setContentAreaFilled(false);
        backk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backkActionPerformed(evt);
            }
        });
        getContentPane().add(backk, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 130, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariActionPerformed
private String keyword = "";
    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased

        String key = this.txt_cari.getText();
        this.keyword = "WHERE kode_supplier LIKE '%" + key + "%' OR nama_supplier LIKE '%" + key + "%' ";
        this.ShowTabel();
        HeaderColumn();
    }//GEN-LAST:event_txt_cariKeyReleased

    private void txt_cariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariKeyTyped

    private void btn_pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilihActionPerformed
        int p = TabelSupplier.getSelectedRow();
        
        String id = TabelSupplier.getValueAt(p, 1).toString();
        
        Karyawan.TransaksiBeli.txt_kodeS.setText(id);
        
        Karyawan.TransaksiBeli.txt_nama.requestFocus();
        this.dispose();
    }//GEN-LAST:event_btn_pilihActionPerformed

    private void backkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backkActionPerformed
        this.dispose();
    }//GEN-LAST:event_backkActionPerformed

    private void TabelSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelSupplierMouseClicked
        int p = TabelSupplier.getSelectedRow();
        String id = TabelSupplier.getValueAt(p, 1).toString();
        Karyawan.TransaksiBeli.txt_kodeS.setText(id);
        Karyawan.TransaksiBeli.txt_nama.requestFocus();
    }//GEN-LAST:event_TabelSupplierMouseClicked

    private void TabelSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabelSupplierKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            this.dispose();
//            Karyawan.TransaksiBeli.txt_kodeS.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int p = TabelSupplier.getSelectedRow();
            String id = TabelSupplier.getValueAt(p, 1).toString();
            Karyawan.TransaksiBeli.txt_kodeS.setText(id);
            Karyawan.TransaksiBeli.txt_nama.requestFocus();
            this.dispose();
        }
    }//GEN-LAST:event_TabelSupplierKeyPressed

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
            java.util.logging.Logger.getLogger(GetDataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetDataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetDataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetDataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GetDataSupplier dialog = new GetDataSupplier(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable TabelSupplier;
    private javax.swing.JButton backk;
    private javax.swing.JButton btn_pilih;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}
