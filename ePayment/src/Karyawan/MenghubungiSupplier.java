/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Karyawan;

import Frame.Login;
import static Frame.Login.namaUser;
import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;
import Main.*;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.URISyntaxException;
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

public class MenghubungiSupplier extends javax.swing.JFrame {

    String nomerHp = "";

    public MenghubungiSupplier() {
        initComponents();
        HeaderColumn();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
        ShowTabel();
        simpannomor.setText(null);
    }

    private void showData(String kodeSupplier) {
        try {
            String query = "SELECT no_telp FROM supplier WHERE kode_supplier = '" + kodeSupplier + "'";
            Connection conn = (Connection) Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet r = stat.executeQuery(query);

            if (r.next()) {
                this.simpannomor.setText(r.getString("no_telp"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public void WhatsApp() {

        try {
            String pesan = "Assalamualaikum kak";
            String nomor = TabelSupplier.getValueAt(this.TabelSupplier.getSelectedRow(), 3).toString();
            System.out.println("Nomor : " + simpannomor.getText());
            String url = "https://web.whatsapp.com/send?phone=" + nomor + "&text=" + URLEncoder.encode(pesan, "UTF-8");
            Desktop.getDesktop().browse(URI.create(url));
            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
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
            String sql = "SELECT * FROM supplier WHERE nama_supplier LIKE '" + txt_cari.getText() + "%' order by kode_supplier asc";
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
                HeaderColumn();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void HeaderColumn() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) TabelSupplier.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        TabelSupplier.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        JTableHeader thead = TabelSupplier.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn column;
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(0);
        column.setPreferredWidth(70);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(1);
        column.setPreferredWidth(150);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(2);
        column.setPreferredWidth(250);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(3);
        column.setPreferredWidth(207);
        TabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = TabelSupplier.getColumnModel().getColumn(4);
        column.setPreferredWidth(300);
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
        TabelSupplier = new javax.swing.JTable();
        txt_cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_wa = new javax.swing.JButton();
        btn_clean = new javax.swing.JButton();
        btn_whatsapp = new javax.swing.JButton();
        simpannomor = new javax.swing.JTextField();
        btnProduk = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        TabelSupplier.setGridColor(new java.awt.Color(255, 255, 255));
        TabelSupplier.setOpaque(false);
        TabelSupplier.setRowHeight(30);
        TabelSupplier.setSelectionBackground(new java.awt.Color(205, 31, 31));
        TabelSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelSupplier);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 980, 320));

        txt_cari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cari.setBorder(null);
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 200, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/supplier.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_wa.setOpaque(false);
        btn_wa.setContentAreaFilled(false);
        btn_wa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_waActionPerformed(evt);
            }
        });
        getContentPane().add(btn_wa, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 590, 180, 50));

        btn_clean.setOpaque(false);
        btn_clean.setContentAreaFilled(false);
        btn_clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cleanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 590, 60, 50));

        btn_whatsapp.setSelected(false);
        btn_whatsapp.setContentAreaFilled(false);
        btn_whatsapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_whatsappActionPerformed(evt);
            }
        });
        getContentPane().add(btn_whatsapp, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 590, 190, 50));

        simpannomor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpannomorActionPerformed(evt);
            }
        });
        getContentPane().add(simpannomor, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 600, 130, 50));

        btnProduk.setOpaque(false);
        btnProduk.setContentAreaFilled(false);
        btnProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdukActionPerformed(evt);
            }
        });
        getContentPane().add(btnProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 200, 50));

        btnDashboard.setOpaque(false);
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        getContentPane().add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 200, 50));

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

    private void TabelSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelSupplierMouseClicked

    }//GEN-LAST:event_TabelSupplierMouseClicked

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        ShowTabel();
        HeaderColumn();
    }//GEN-LAST:event_txt_cariKeyReleased

    private void btn_waActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_waActionPerformed
        WhatsApp();
    }//GEN-LAST:event_btn_waActionPerformed

    private void btn_whatsappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_whatsappActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_whatsappActionPerformed

    private void btnProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdukActionPerformed
        new StokProduk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnProdukActionPerformed
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
    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        this.pilihDashboard();       // TODO add your handling code here:
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
        new MenuTransaksi().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void simpannomorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpannomorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_simpannomorActionPerformed

    private void btn_cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cleanActionPerformed

   this.txt_cari.setText(null);
        ShowTabel();
        HeaderColumn();
    }//GEN-LAST:event_btn_cleanActionPerformed

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
            java.util.logging.Logger.getLogger(MenghubungiSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenghubungiSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenghubungiSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenghubungiSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenghubungiSupplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelSupplier;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnProduk;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JButton btn_clean;
    private javax.swing.JButton btn_wa;
    private javax.swing.JButton btn_whatsapp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField simpannomor;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}
