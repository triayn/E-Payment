/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package PopUp;

import Frame.Login;
import Main.Koneksi;
import java.awt.Color;
import java.awt.Font;
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

/**
 *
 * @author Tria
 */
public class DetailTransaksiBeli extends javax.swing.JDialog {

    public DetailTransaksiBeli(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.TampilTabel();
        HeaderColumn();
    }

    public void TampilTabel() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Kode Transaksi");
        tbl.addColumn("Kode Supplier");
        tbl.addColumn("Nama Barang");
        tbl.addColumn("Jumlah Barang");
        tbl.addColumn("Total Harga");
        TabelTransaksi.setModel(tbl);
        try {
            String sql = "SELECT * FROM detail_transaksi_beli WHERE kode_tr_beli ="
           + " '" + Owner.LaporanPengeluaran.ambilkodetransaksibeli.getText() + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("kode_tr_beli"),
                    res.getString("kode_supplier"),
                    res.getString("nama_barang"),
                    res.getString("jumlah_barang"),
                    Main.Rupiah.convertToRupiah(Integer.parseInt(res.getString("total_harga")))
                });
                TabelTransaksi.setModel(tbl);
                HeaderColumn();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e);
        }
    }
    
    public void HeaderColumn() {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        TabelTransaksi.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        TabelTransaksi.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        TabelTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        center.setHorizontalAlignment(JLabel.CENTER);
        TabelTransaksi.getColumnModel().getColumn(0).setCellRenderer(center);
        TabelTransaksi.getColumnModel().getColumn(1).setCellRenderer(center);
//        TabelTransaksi.getColumnModel().getColumn(3).setCellRenderer(center);
//        TabelTransaksi.getColumnModel().getColumn(4).setCellRenderer(center);
        TabelTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) TabelTransaksi.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = TabelTransaksi.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn column;
        TabelTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelTransaksi.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
        TabelTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelTransaksi.getColumnModel().getColumn(1);
        column.setPreferredWidth(130);
        TabelTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelTransaksi.getColumnModel().getColumn(2);
        column.setPreferredWidth(128);
        TabelTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelTransaksi.getColumnModel().getColumn(3);
        column.setPreferredWidth(180);
        TabelTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelTransaksi.getColumnModel().getColumn(4);
        column.setPreferredWidth(130);
        TabelTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelTransaksi.getColumnModel().getColumn(5);
        column.setPreferredWidth(170);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelTransaksi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        kmbali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabelTransaksi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelTransaksi.setGridColor(new java.awt.Color(205, 31, 31));
        TabelTransaksi.setSelectionBackground(new java.awt.Color(205, 31, 31));
        jScrollPane1.setViewportView(TabelTransaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 97, 790, 300));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/popupLaporan.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        kmbali.setOpaque(false);
        kmbali.setContentAreaFilled(false);
        kmbali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmbaliActionPerformed(evt);
            }
        });
        getContentPane().add(kmbali, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 410, 160, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kmbaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmbaliActionPerformed
        this.dispose();
    }//GEN-LAST:event_kmbaliActionPerformed

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
            java.util.logging.Logger.getLogger(DetailTransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailTransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailTransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailTransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DetailTransaksiBeli dialog = new DetailTransaksiBeli(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable TabelTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kmbali;
    // End of variables declaration//GEN-END:variables
}
