package PopUp;

import Karyawan.TransaksiJual;
import static Karyawan.TransaksiJual.txt_kodeP;
import Main.Koneksi;
import static PopUp.BerhasilTransaksiBeli.TabelTr;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class BerhasilTransaksiJual extends javax.swing.JDialog {

    public BerhasilTransaksiJual(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ShowTabel();
        lbl_kode.setText(Karyawan.TransaksiJual.lbl_kodeTJ.getText());
        lbl_kembali.setText(Karyawan.TransaksiJual.lbl_kembalian.getText());
        lbl_kodeK.setText(Frame.Login.getNama());
        lbl_tgl.setText(Karyawan.TransaksiJual.lbl_tgl.getText());
        lbl_tunai.setText("Rp." +Karyawan.TransaksiJual.txt_bayar.getText());
        lbl_diskon.setText("Rp." +Karyawan.TransaksiJual.lbl_hargadiskon.getText());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelTr = new javax.swing.JTable();
        lbl_kembali = new javax.swing.JLabel();
        lbl_diskon = new javax.swing.JLabel();
        lbl_tunai = new javax.swing.JLabel();
        lbl_kode = new javax.swing.JLabel();
        lbl_kodeK = new javax.swing.JLabel();
        lbl_tgl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabelTr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TabelTr.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelTr.setGridColor(new java.awt.Color(205, 31, 31));
        TabelTr.setSelectionBackground(new java.awt.Color(205, 31, 31));
        jScrollPane1.setViewportView(TabelTr);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 450, 170));

        lbl_kembali.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lbl_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 160, 30));

        lbl_diskon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lbl_diskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 160, 30));

        lbl_tunai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lbl_tunai, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 160, 30));

        lbl_kode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lbl_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 128, 250, 30));

        lbl_kodeK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(lbl_kodeK, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 190, 230, 30));

        lbl_tgl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_tgl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(lbl_tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 160, 250, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/BerhasilTransaksiJual.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, -1));

        btnKembali.setText("kembali");
        btnKembali.setOpaque(false);
        btnKembali.setContentAreaFilled(false);
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 100, 30));

        btnCetak.setOpaque(false);
        btnCetak.setContentAreaFilled(false);
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        getContentPane().add(btnCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, 100, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void ShowData() {
        try {
            String sql = "";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
        }
    }

    public void ShowTabel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Tr");
        model.addColumn("Nama Produk");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        TabelTr.setModel(model);
        try {
            
            String sql = "SELECT transaksi_jual.kode_tr_jual, detail_transaksi_jual.nama_produk, detail_transaksi_jual.jumlah, "
                    + "detail_transaksi_jual.total_harga, transaksi_jual.total_item, transaksi_jual.total_harga AS total "
                    + "FROM detail_transaksi_jual JOIN transaksi_jual ON detail_transaksi_jual.kode_tr_jual = "
                    + "transaksi_jual.kode_tr_jual WHERE detail_transaksi_jual.kode_tr_jual = '" + TransaksiJual.lbl_kodeTJ.getText() + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                model.addRow(new Object[]{
                    res.getString("kode_tr_jual"),
                    res.getString("nama_produk"),
                    res.getString("jumlah"),
                    Main.Rupiah.convertToRupiah(Integer.parseInt(res.getString("total_harga")))
                });
                if (res.isLast()) {
                    model.addRow(new Object[]{
                        "", "Total", res.getString("total_item"), Main.Rupiah.convertToRupiah(Integer.parseInt(res.getString("total"))),
                    });
                    System.out.println("TESTING");
                }
                TabelTr.setModel(model);
                lebar_tabelbarang();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    private void lebar_tabelbarang() {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        TabelTr.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        TabelTr.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        TabelTr.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) TabelTr.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = TabelTr.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn kolom;
        TabelTr.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        kolom = TabelTr.getColumnModel().getColumn(0);
        kolom.setPreferredWidth(40);
        kolom = TabelTr.getColumnModel().getColumn(1);
        kolom.setPreferredWidth(140);
        kolom = TabelTr.getColumnModel().getColumn(2);
        kolom.setPreferredWidth(30);
        kolom = TabelTr.getColumnModel().getColumn(3);
        kolom.setPreferredWidth(50);
    }


    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed
    private void cetakNota(Map parameter) throws SQLException {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src\\Cetak\\Nota.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jPrint = JasperFillManager.fillReport(jasperReport, parameter, Main.Koneksi.configDB());
            JasperViewer.viewReport(jPrint);
            
        } catch (JRException ex) {
        }
    }

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        Map parameters = new HashMap();
        parameters.put("kode", Karyawan.TransaksiJual.lbl_kodeTJ.getText());
        parameters.put("tunai", Karyawan.TransaksiJual.txt_bayar.getText());
        parameters.put("kembalian", Karyawan.TransaksiJual.lbl_kembalian.getText());
        try {
            this.dispose();
            this.cetakNota(parameters);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " ERROR");
        }

//try {
//            
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = (Connection) Main.Koneksi.configDB();
//
//
//            
//            HashMap parameter = new HashMap () ;
//            parameter.put ("kode",Karyawan.TransaksiJual.lbl_kodeTJ.getText()) ;
//            
//            InputStream file = getClass().getResourceAsStream("/Cetak/Nota.jrxml");
//            JasperDesign dsn = JRXmlLoader.load(file);
//            JasperReport Jupe = JasperCompileManager.compileReport(dsn);
//            JasperPrint jp = JasperFillManager.fillReport(Jupe, parameter,con);
//            
//            JasperViewer.viewReport( jp, false);
//
//         
//            } catch (Exception e) {
//              JOptionPane.showMessageDialog(null, "Data Tidak Dapat Dicetak"
//            +"\n"+e.getMessage(), "Cetak Data", javax.swing.JOptionPane.ERROR_MESSAGE);
//            }
//        String kodeTransaksiJual = Karyawan.TransaksiJual.lbl_kodeTJ.getText();
//        java.sql.Connection conn;
//        System.out.println("Enter presed");
//try {
//     conn = (Connection)Main.Koneksi.configDB();
//            // menyiapkan kode transaksi
//            HashMap parameter = new HashMap();
//            parameter.put("kode_tr_jual", kodeTransaksiJual);
//
//            // meyiapkan jasper report
//            InputStream file = getClass().getResourceAsStream("/Cetak/Notaaaaa.jrxml");
//            JasperDesign desain = JRXmlLoader.load(file);
//            JasperReport report = JasperCompileManager.compileReport(desain);
//            JasperPrint print = JasperFillManager.fillReport(report, parameter, conn);
//
//            
//            // membuka jasper report
//            JasperViewer jview = new JasperViewer(print, false);
//            jview.setTitle("Cetak Struk " + kodeTransaksiJual);
//            jview.setVisible(true);
//            jview.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//            jview.setLocationRelativeTo(null);
//            jview.setFitPageZoomRatio();
//
//            // solved bug jasper report tiba-tiba minimaze
//            jview.addWindowListener(new java.awt.event.WindowAdapter() {
//
//                // menutup jasper report saat user menekan tombol close
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    System.out.println("JASPER CLOSING");
//                    jview.dispose();
//                }
//
//                // menutup jasper report saat user menekan tombol close
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    System.out.println("JASPER CLOSED");
//                    jview.dispose();
//                }
//
//                // memaksa jasper report untuk tetap aktif (tidak minimaze)
//                @Override
//                public void windowDeactivated(WindowEvent e) {
//                    System.out.println("JASPER ACTIVATED");
//                    jview.setVisible(true);
//                }
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
//        try {
//            JasperDesign jasdi = JRXmlLoader.load("src/Cetak/Notaaaa.jrxml");
//
//
//            
//            String Sql = "SELECT transaksi_jual.tanggal, karyawan.nama_karyawan, detail_transaksi_jual.nama_produk, produk.harga,detail_transaksi_jual.jumlah, detail_transaksi_jual.total_harga, transaksi_jual.total_item, transaksi_jual.total_harga AS total "
//                    + "FROM detail_transaksi_jual JOIN transaksi_jual ON detail_transaksi_jual.kode_tr_jual = transaksi_jual.kode_tr_jual "
//                    + "JOIN produk ON detail_transaksi_jual.kode_produk = produk.kode_produk "
//                    + "JOIN karyawan ON transaksi_jual.kode_karyawan = karyawan.kode_karyawan";
//            
//            JRDesignQuery newQuery = new JRDesignQuery();
//            newQuery.setText(sql);
//            jasdi.setQuery(newQuery);
//            JasperReport js = JasperCompileManager.compileReport(jasdi);
//            JasperPrint jp = JasperFillManager.fillReport(js,null,con);
//            JasperViewer.viewReport(jp);
//            
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(rootPane,"ERROR"+e.getMessage());
//            
//        }
//        } catch (SQLException ex) {
//            Logger.getLogger(BerhasilTransaksiJual.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnCetakActionPerformed

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
            java.util.logging.Logger.getLogger(BerhasilTransaksiJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BerhasilTransaksiJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BerhasilTransaksiJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BerhasilTransaksiJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BerhasilTransaksiJual dialog = new BerhasilTransaksiJual(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTable TabelTr;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnKembali;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lbl_diskon;
    public static javax.swing.JLabel lbl_kembali;
    public static javax.swing.JLabel lbl_kode;
    public static javax.swing.JLabel lbl_kodeK;
    public static javax.swing.JLabel lbl_tgl;
    public static javax.swing.JLabel lbl_tunai;
    // End of variables declaration//GEN-END:variables
}
