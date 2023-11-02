/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Karyawan;

import Frame.Login;
import Main.Koneksi;
import PopUp.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import Informasi.*;

/**
 *
 * @author Tria
 */
public class TransaksiJual extends javax.swing.JFrame {

    public TransaksiJual(String kode) {
        initComponents();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
        txt_kodeP.setText(kode);
        getId();
        lbl_kodeK.setText(Login.getIdK());
        tanggalSaatIni();
        TabelFormat();
        txt_kodeP.requestFocus();
        this.lbl_hargadiskon.setText("0");
        this.txt_kodeM.setText(null);
        this.lbl_hargadiskon.setText("0");

    }

    public void TabelFormat() {

        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) {
                if (col == 3) { //columnIndex: the column you want to make it editable
                    return true;
                } else {
                    return false;
                }
            }
        };

        model.addColumn("Kode Tr");
        model.addColumn("Kode Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        TabelTr.getTableHeader().setBackground(new java.awt.Color(102, 153, 255));
        TabelTr.getTableHeader().setForeground(java.awt.Color.BLACK);
        TabelTr.getTableHeader().setPreferredSize(new Dimension(20, 35));
        TabelTr.setModel(model);
        lebar_tabelbarang();
        txt_kodeP.requestFocus();

    }

    public void totalBiaya() {
        int jumlahBaris = TabelTr.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(TabelTr.getValueAt(i, 4).toString());
            hargaBarang = Integer.parseInt(TabelTr.getValueAt(i, 3).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }

        String idr1 = Main.Rupiah.convertToRupiah(Integer.parseInt(String.valueOf(totalBiaya)));
        lbl_totalharga.setText(idr1);

        System.out.println("TOTAL HARGAAAA = " + totalBiaya);
        this.totalhargadiambil = totalBiaya;
        totalhargabrg.setText("" + totalBiaya);
        totalhargabrg.setVisible(false);
    }
    public int hargadiskonnya;
    public int totalhargadiambil;

    public void loadData() {

        int totalBrgDb = 0;
        int jmlBrg = 0;
        int jmlBrg2 = Integer.parseInt(txt_jumlah.getText());

        String sql = "SELECT * FROM produk WHERE kode_produk ='" + txt_kodeP.getText() + "'";

        for (int i = 0; i < model.getRowCount(); i++) {
            if (txt_kodeP.getText().equals(String.valueOf(TabelTr.getValueAt(i, 1)))) {
                jmlBrg += Integer.parseInt(String.valueOf(TabelTr.getValueAt(i, 4)));
            }
        }
        int jmlTotal = jmlBrg + jmlBrg2;

        try {
            java.sql.Connection conn = (Connection) Main.Koneksi.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            java.sql.ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                totalBrgDb = Integer.parseInt(rs.getString(6));
            }

        } catch (Exception e) {
            e.getMessage();
        }
        System.out.println(totalBrgDb);
        System.out.println(jmlTotal);
        if (totalBrgDb == 0) {
            ProdukHabis p = new ProdukHabis(this, true);
            p.setVisible(true);
            System.out.println("TOTAL BARANG DI TABEL" + totalBrgDb);

        } else if (txt_jumlah.getText().equals("0")) {
            IsiJumlah p = new IsiJumlah(this, true);
            p.setVisible(true);
        } else if (totalBrgDb < jmlTotal) {
            StokKurang p = new StokKurang(this, true);
            p.setVisible(true);

        } else {

            String kodeP = txt_kodeP.getText();
            boolean duplikasi = true;
            if (TabelTr.getRowCount() >= 1 ) {

                for (int i = 0; i < model.getRowCount(); i++) {

                    String kode = (String) model.getValueAt(i, 1);

                    if (kode.equalsIgnoreCase(kodeP)) {
                        System.out.println("KODE PRODUK = " + kode);
                        System.out.println("DUPLIKASI = " + duplikasi);

                        duplikasi = false;
                        // Modify the quantity of the last row
                        int oldQuantity = Integer.parseInt((String) model.getValueAt(i, 4));

                        int newQuantity = oldQuantity + Integer.parseInt(txt_jumlah.getText());
                        // int hargaBaru = harga + Integer.parseInt(hargaBarang);
                        model.setValueAt(Integer.toString(newQuantity), i, 4);
                        String total = Integer.toString(newQuantity * Integer.parseInt(lbl_harga.getText()));
                        model.setValueAt(total, i, 5);
                    }

                }
            }//batas
            String namadanvarian = lbl_namaP.getText() + " " + lbl_varian.getText() + " " + lbl_ukuran.getText();
            int hargafix;
            String totalhargatext = lbl_totalharga.getText();
            String totalhargaclean = totalhargatext.replaceAll("\\D+", "");
            int totalhargaint = Integer.parseInt(totalhargaclean);

            if (lbl_hargadiskon.getText().equals("0")) {
                hargafix = Integer.valueOf(totalhargaint);
            } else {
                hargafix = Integer.valueOf(lbl_hargadiskon.getText());
            }
            if (duplikasi) {
                System.out.println("HARGA FIX = " + hargafix);
                model.addRow(new Object[]{
                    lbl_kodeTJ.getText(),
                    txt_kodeP.getText(),
                    namadanvarian,
                    lbl_harga.getText(),
                    txt_jumlah.getText(),
                    hargafix//ada di codingan lain untuk cara resolve nya jika ada error

                });
            }// Tambahkan row baru jika tidak ada duplikasi

            TabelTr.setModel(model);
        }
        lbl_hargadiskon.setText("0");
    }

    public void tambahTransaksi() {
        int jumlah, harga, total;

        jumlah = Integer.valueOf(txt_jumlah.getText());
        harga = Integer.valueOf(lbl_harga.getText());
        total = jumlah * harga;

        lbl_totalharga.setText(String.valueOf(total));

        loadData();

        totalJumlah();
        totalBiaya();

        clear2();
        txt_kodeP.requestFocus();
    }

    private DefaultTableModel model;

    public void utama() {
        lbl_kodeTJ.setText("");
        txt_kodeP.setText("");
        lbl_namaP.setText("");
        lbl_harga.setText("");
        txt_jumlah.setText("");
        getId();
    }

    public void clear2() {
        txt_kodeP.setText("");
        lbl_namaP.setText("");
        lbl_harga.setText("");
        lbl_stok.setText("");
        txt_jumlah.setText("");
        txt_kodeM.setText("");
    }

    public void totalJumlah() {
        int jumlahBaris = TabelTr.getRowCount();
        int hargaBarang = 0;
        int totalJumlah = 0;
        for (int i = 0; i < jumlahBaris; i++) {
            hargaBarang = Integer.parseInt(TabelTr.getValueAt(i, 4).toString());
            totalJumlah = totalJumlah + hargaBarang;//dapatkan harga barang
        }
        System.out.println("Barang yang dibeli = " + totalJumlah);
        System.out.println("harga barang = " + hargaBarang);
        totalbarang.setText("" + totalJumlah);
        totalbarang.setVisible(false);
    }

    public void kosong() {
        DefaultTableModel model = (DefaultTableModel) TabelTr.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    public void tanggalSaatIni() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nol_jam = "", nol_menit = "", nol_detik = "";
                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();

                if (nilai_jam <= 9) {
                    nol_jam = "";
                }
                if (nilai_menit <= 9) {
                    nol_jam = "";
                }
                if (nilai_detik <= 9) {
                    nol_jam = "";
                }

                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);

                Date date = new Date();
                SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");

                lbl_tgl.setText(s.format(date) + " " + jam + ":" + menit + ":" + detik + "");
            }
        };
        new Timer(1000, taskPerformer).start();

    }

    private void lebar_tabelbarang() {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        TabelTr.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        TabelTr.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        TabelTr.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TabelTr.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        TabelTr.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        
        center.setHorizontalAlignment(JLabel.CENTER);
        TabelTr.getColumnModel().getColumn(1).setCellRenderer(center);
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
        kolom.setPreferredWidth(130);
        kolom = TabelTr.getColumnModel().getColumn(2);
        kolom.setPreferredWidth(200);
        kolom = TabelTr.getColumnModel().getColumn(3);
        kolom.setPreferredWidth(50);
        kolom = TabelTr.getColumnModel().getColumn(4);
        kolom.setPreferredWidth(50);
        kolom = TabelTr.getColumnModel().getColumn(5);
        kolom.setPreferredWidth(50);

    }

    public void getId() {
//        try {
//            String sql = "SELECT kode_tr_jual FROM transaksi_jual order by kode_tr_jual desc";
//            Connection conn = (Connection) Main.Koneksi.configDB();
//            java.sql.Statement stm = conn.createStatement();
//            java.sql.ResultSet rs = stm.executeQuery(sql);
//
//            if (rs.next()) {
//                String Idno = rs.getString("kode_tr_jual").substring(5);
//                String AN = "" + (Integer.parseInt(Idno) + 1);
//                String Nol = "";
//                if (AN.length() == 1) {
//                    Nol = "00";
//                } else if (AN.length() == 2) {
//                    Nol = "0";
//                } else if (AN.length() == 3) {
//                    Nol = "";
//                }
//                lbl_kodeTJ.setText("TRJ" + Nol + AN);
//            } else {
//                lbl_kodeTJ.setText("TRJ001");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
        try {
            String sql = "SELECT kode_tr_jual FROM transaksi_jual order by kode_tr_jual desc";
            Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String idTJ = rs.getString("kode_tr_jual").substring(3);
                String AN = "" + (Integer.parseInt(idTJ) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "00";
                } else if (AN.length() == 2) {
                    Nol = "0";
                } else if (AN.length() == 3) {
                    Nol = "";

                }

                lbl_kodeTJ.setText("TRJ" + Nol + AN);
            } else {
                lbl_kodeTJ.setText("TRJ001");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
        }
    }

    private void IsMember(String kode) {
        int jumlahBaris = TabelTr.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(TabelTr.getValueAt(i, 4).toString());
            hargaBarang = Integer.parseInt(TabelTr.getValueAt(i, 3).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        try {

            String sql = "SELECT * FROM member";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                if (txt_kodeM.getText().equals(res.getString(1))) {
                    txt_kodeM.getText();

                    if (totalBiaya > 50000) {

                        this.hargadiskonnya = (int) (totalBiaya * 0.02);
                        lbl_hargadiskon.setText(String.valueOf(totalBiaya - this.hargadiskonnya));
                    } else if (totalBiaya > 100000) {
                        this.hargadiskonnya = (int) (totalBiaya * 0.05);
                        lbl_hargadiskon.setText(String.valueOf(totalBiaya - this.hargadiskonnya));

                    } else {
                        lbl_hargadiskon.setText(String.valueOf(0));
                    }

                } else {
                    BukanMember p = new BukanMember(this, true);
                    p.setVisible(true);
                    this.txt_kodeM.setText("");
                }
            }
        } catch (Exception e) {

        }
    }

    public void kembaliannyaa() {
        int hargafix;

        String totalhargatext = lbl_totalharga.getText();
        String totalhargaclean = totalhargatext.replaceAll("\\D+", "");
        int totalhargaint = Integer.parseInt(totalhargaclean);
        System.out.println("ttl : "+totalhargaint);
        
        if (lbl_hargadiskon.getText().equals("0")) {
            hargafix = Integer.valueOf(totalhargaint);
        } else {
            hargafix = Integer.valueOf(lbl_hargadiskon.getText());
        }

        int kembaliannya = Integer.valueOf(txt_bayar.getText()) - hargafix;
        String idr1 = Main.Rupiah.convertToRupiah(Integer.parseInt(String.valueOf(kembaliannya)));
        lbl_kembalian.setText(idr1);
        String kembaliantext = lbl_kembalian.getText();
        if (kembaliantext.contains("-")) {
            lbl_kembalian.setText("Rp.0");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_varian = new javax.swing.JLabel();
        totalhargabrg = new javax.swing.JLabel();
        totalbarang = new javax.swing.JLabel();
        lbl_ukuran = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelTr = new javax.swing.JTable();
        txt_bayar = new javax.swing.JTextField();
        lbl_hargadiskon = new javax.swing.JLabel();
        txt_kodeM = new javax.swing.JTextField();
        lbl_tgl = new javax.swing.JLabel();
        lbl_kodeK = new javax.swing.JLabel();
        lbl_harga = new javax.swing.JLabel();
        lbl_stok = new javax.swing.JLabel();
        txt_jumlah = new javax.swing.JTextField();
        txt_kodeP = new javax.swing.JTextField();
        lbl_namaP = new javax.swing.JLabel();
        lbl_kodeTJ = new javax.swing.JLabel();
        lbl_totalharga = new javax.swing.JLabel();
        lbl_kembalian = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        btn_transaksi = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_cariP = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();

        lbl_varian.setText("jLabel2");

        totalhargabrg.setText("jLabel2");

        totalbarang.setText("jLabel2");

        lbl_ukuran.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 317, 790, 200));

        txt_bayar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_bayar.setBorder(null);
        txt_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarActionPerformed(evt);
            }
        });
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_bayarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_bayarKeyTyped(evt);
            }
        });
        getContentPane().add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 540, 190, 20));

        lbl_hargadiskon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_hargadiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 580, 190, 30));

        txt_kodeM.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_kodeM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_kodeM.setBorder(null);
        txt_kodeM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeMActionPerformed(evt);
            }
        });
        txt_kodeM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_kodeMKeyReleased(evt);
            }
        });
        getContentPane().add(txt_kodeM, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 190, 30));

        lbl_tgl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_tgl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 210, 310, 30));

        lbl_kodeK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_kodeK, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 190, 30));

        lbl_harga.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 100, 30));

        lbl_stok.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 70, 30));

        txt_jumlah.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_jumlah.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_jumlah.setBorder(null);
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });
        txt_jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_jumlahKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_jumlahKeyReleased(evt);
            }
        });
        getContentPane().add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, 80, 23));

        txt_kodeP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_kodeP.setBorder(null);
        txt_kodeP.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_kodePInputMethodTextChanged(evt);
            }
        });
        txt_kodeP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodePActionPerformed(evt);
            }
        });
        txt_kodeP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_kodePKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_kodePKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_kodePKeyTyped(evt);
            }
        });
        getContentPane().add(txt_kodeP, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 180, 20));

        lbl_namaP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_namaP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(lbl_namaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 270, 30));

        lbl_kodeTJ.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_kodeTJ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_kodeTJ.setText("0");
        getContentPane().add(lbl_kodeTJ, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 300, 40));

        lbl_totalharga.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_totalharga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_totalharga.setText("Rp.0");
        getContentPane().add(lbl_totalharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 340, 40));

        lbl_kembalian.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_kembalian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_kembalian.setText("Rp.0");
        getContentPane().add(lbl_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 90, 380, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/TransaksiJual.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_tambah.setText("Tambah");
        btn_tambah.setOpaque(false);
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 130, 40));

        btn_transaksi.setText("btn_Transaksi");
        btn_transaksi.setOpaque(false);
        btn_transaksi.setContentAreaFilled(false);
        btn_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 170, 40));

        btn_hapus.setText("hapus");
        btn_hapus.setOpaque(false);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 130, 40));

        btn_cariP.setOpaque(false);
        btn_cariP.setContentAreaFilled(false);
        btn_cariP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cariPMouseClicked(evt);
            }
        });
        btn_cariP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariPActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cariP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 50, 50));

        btnKembali.setText("jButton1");
        btnKembali.setOpaque(false);
        btnKembali.setContentAreaFilled(false);
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, 150, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_kodeMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodeMActionPerformed

    }//GEN-LAST:event_txt_kodeMActionPerformed

    private void txt_kodeMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kodeMKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.IsMember(this.txt_kodeM.getText());
        }
    }//GEN-LAST:event_txt_kodeMKeyReleased

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed

    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void txt_jumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahKeyReleased

    }//GEN-LAST:event_txt_jumlahKeyReleased

    private void txt_kodePKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kodePKeyReleased

    }//GEN-LAST:event_txt_kodePKeyReleased

    private void txt_kodePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodePActionPerformed
    }//GEN-LAST:event_txt_kodePActionPerformed

    private void btn_cariPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cariPMouseClicked

    }//GEN-LAST:event_btn_cariPMouseClicked

    private void txt_kodePKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kodePKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            new PopUp.GetDataProduk(this, rootPaneCheckingEnabled).setVisible(true);

        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String query = "SELECT * FROM produk WHERE kode_produk = '" + txt_kodeP.getText() + "'";
                Connection conn = (Connection) Koneksi.configDB();
                Statement stat = conn.createStatement();
                ResultSet r = stat.executeQuery(query);

                if (r.next()) {
                    lbl_varian.setText(r.getString("varian"));
                    lbl_ukuran.setText(r.getString("ukuran"));
                    this.lbl_namaP.setText(r.getString("nama_produk") + " " + lbl_varian.getText() + " " + lbl_ukuran.getText());
                    this.lbl_harga.setText(r.getString("harga"));
                    this.lbl_stok.setText(r.getString("stok"));

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
            txt_jumlah.requestFocus();
        }


    }//GEN-LAST:event_txt_kodePKeyPressed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        new Karyawan.MenuTransaksi().setVisible(true);
        this.dispose();   // TODO add your handling code here:
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btn_cariPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariPActionPerformed
        new PopUp.GetDataProduk(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btn_cariPActionPerformed

    private void txt_kodePKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kodePKeyTyped
    }//GEN-LAST:event_txt_kodePKeyTyped

    private void txt_kodePInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_kodePInputMethodTextChanged

    }//GEN-LAST:event_txt_kodePInputMethodTextChanged

    private void txt_jumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_jumlah.getText().isEmpty()) {

                IsiJumlah p = new IsiJumlah(this, true);
                p.setVisible(true);
            } else {

                tambahTransaksi();
            }
            txt_kodeP.requestFocus();
        }
    }//GEN-LAST:event_txt_jumlahKeyPressed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        if (txt_jumlah.getText().isEmpty()) {

            IsiJumlah p = new IsiJumlah(this, true);
            p.setVisible(true);
        } else {

            tambahTransaksi();
        }
        txt_kodeP.requestFocus();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        DefaultTableModel model = (DefaultTableModel) TabelTr.getModel();
        int row = TabelTr.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        totalJumlah();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transaksiActionPerformed
        String kembaliannyaa = lbl_kembalian.getText();
        String noTransaksi = lbl_kodeTJ.getText();
        txt_kodeM.getText();
        String kodeM = txt_kodeM.getText();
        String kodeK = lbl_kodeK.getText();
        String tanggal = lbl_tgl.getText().toString().substring(0, 10);
        String totalbrg = totalbarang.getText();
        String checkBayar = txt_bayar.getText();
        int bayar = 0;
        int bayarpilih;

        String totalhargatext1 = lbl_totalharga.getText();
        String totalhargaclean1 = totalhargatext1.replaceAll("\\D+", "");
        int totalhargaint1 = Integer.parseInt(totalhargaclean1);

        if (lbl_hargadiskon.getText().equals("") || lbl_hargadiskon.getText().equals("0")) {
            bayarpilih = totalhargaint1;
        } else {
            bayarpilih = Integer.parseInt(lbl_hargadiskon.getText());

        }
        int hargafix1;

        String totalhargatext = lbl_totalharga.getText();
        String totalhargaclean = totalhargatext.replaceAll("\\D+", "");
        int totalhargaint = Integer.parseInt(totalhargaclean);
        if (lbl_hargadiskon.getText().equals("0")) {
            hargafix1 = Integer.valueOf(totalhargaint);
        } else {
            hargafix1 = Integer.valueOf(lbl_hargadiskon.getText());
        }

        if (checkBayar.isEmpty()) {
            IsiTunai p = new IsiTunai(this, true);
            p.setVisible(true);
        } else {
            bayar = Integer.parseInt(txt_bayar.getText().replace(".", "").trim());
        }

        if (bayar < hargafix1) {
            PembayaranKurang p = new PembayaranKurang(this, true);
            p.setVisible(true);
        } else if (checkBayar.isEmpty()) {

            txt_bayar.setText("");
            IsiTunai p = new IsiTunai(this, true);
            p.setVisible(true);

        } else {

            try {
                String sql = "INSERT INTO `transaksi_jual`(`kode_tr_jual`, `kode_karyawan`, `kode_member`, `total_item` , `total_harga`, `harga_diskon`, `bayar`, `tanggal`) VALUES "
                        + "('" + noTransaksi + "','" + kodeK + "','" + kodeM + "','" + totalbrg + "','" + totalhargadiambil + "','" + lbl_hargadiskon.getText() + "','" + bayarpilih + "','" + lbl_tgl.getText() + "')";
                java.sql.Connection con = (Connection) Main.Koneksi.configDB();
                java.sql.PreparedStatement pst = con.prepareStatement(sql);
                System.out.println("QUERYYY TRANSAKSI = " + sql);
                pst.executeUpdate();
                pst.close();
            } catch (Exception e) {
                System.out.println("simpan penjualan error " + e.getMessage());
            }

            try {
                Connection c = (Connection) Main.Koneksi.configDB();
                int baris = TabelTr.getRowCount();

                for (int i = 0; i < baris; i++) {
                    String sql = "INSERT INTO `detail_transaksi_jual` (`kode_tr_jual`, `kode_produk`, `nama_produk`, `jumlah`, `total_harga`) VALUES "
                            + "('" + TabelTr.getValueAt(i, 0) + "','" + TabelTr.getValueAt(i, 1) + "','" + TabelTr.getValueAt(i, 2) + "','" + TabelTr.getValueAt(i, 4) + "','" + TabelTr.getValueAt(i, 5) + "')";
                    System.out.println("SQL DETAIL TRANS = " + sql);
                    PreparedStatement p = c.prepareStatement(sql);
                    p.executeUpdate();
                    p.close();
                }
            } catch (Exception e) {
                System.out.println("simpan penjualan rinci error" + e.getMessage());
            }
            new PopUp.BerhasilTransaksiJual(this, rootPaneCheckingEnabled).setVisible(true);
            PopUp.BerhasilTransaksiJual.lbl_kode.setText(noTransaksi);
            PopUp.BerhasilTransaksiJual.lbl_kembali.setText(kembaliannyaa);
            PopUp.BerhasilTransaksiJual.lbl_kodeK.setText(kodeK);
            PopUp.BerhasilTransaksiJual.lbl_tgl.setText(tanggal);
            PopUp.BerhasilTransaksiJual.lbl_tunai.setText(checkBayar);

            clear2();
            utama();
            lbl_kodeTJ.setText(null);
            getId();
            kosong();

            lbl_kodeK.setText(Login.getIdK());
            tanggalSaatIni();
            TabelFormat();
            lbl_hargadiskon.setText("0");
            lbl_kembalian.setText("Rp.0");
            lbl_totalharga.setText("Rp.0");
            lbl_kembalian.setBackground(java.awt.Color.WHITE);
            txt_kodeP.requestFocus();
            txt_bayar.setText(null);

            getId();
        }

    }//GEN-LAST:event_btn_transaksiActionPerformed

    private void txt_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bayarActionPerformed

    private void txt_bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String kembaliannyaa = lbl_kembalian.getText();
            String noTransaksi = lbl_kodeTJ.getText();
            txt_kodeM.getText();
            String kodeM = txt_kodeM.getText();
            String kodeK = lbl_kodeK.getText();
            String tanggal = lbl_tgl.getText().toString().substring(0, 10);
            System.out.println("TANGGAL AJA = " + tanggal);
            String totalbrg = totalbarang.getText();
            System.out.println("TOTAL BARANG = " + totalbarang.getText());
            String grandtotal = totalhargabrg.getText();
            String checkBayar = txt_bayar.getText();
            int bayar = 0;
            int bayartanparp = Integer.parseInt(txt_bayar.getText());
            int bayarpilih;

            String totalhargatext1 = lbl_totalharga.getText();
            String totalhargaclean1 = totalhargatext1.replaceAll("\\D+", "");
            int totalhargaint1 = Integer.parseInt(totalhargaclean1);
            System.out.println("tooooo : "+totalhargaint1);

            if (lbl_hargadiskon.getText().equals("") || lbl_hargadiskon.getText().equals("0")) {
                bayarpilih = totalhargaint1;
            } else {
                bayarpilih = Integer.parseInt(lbl_hargadiskon.getText());
            }
            int hargafix1;

            String totalhargatext = lbl_totalharga.getText();
            String totalhargaclean = totalhargatext.replaceAll("\\D+", "");
            int totalhargaint = Integer.parseInt(totalhargaclean);
            if (lbl_hargadiskon.getText().equals("0")) {
                hargafix1 = Integer.valueOf(totalhargaint);
            } else {
                hargafix1 = Integer.valueOf(lbl_hargadiskon.getText());
            }

            if (checkBayar.isEmpty()) {

                IsiTunai p = new IsiTunai(this, true);
                p.setVisible(true);
            } else {
                bayar = Integer.parseInt(txt_bayar.getText().replace(".", "").trim());
            }

            if (bayar < hargafix1) {
                PembayaranKurang p = new PembayaranKurang(this, true);
                p.setVisible(true);
            } else if (checkBayar.isEmpty()) {

                txt_bayar.setText("");
                IsiTunai p = new IsiTunai(this, true);
                p.setVisible(true);

            } else {

                try {
                    String sql = "INSERT INTO `transaksi_jual`(`kode_tr_jual`, `kode_karyawan`, `kode_member`, `total_item` , `total_harga`, `harga_diskon`, `bayar`, `tanggal`) VALUES "
                            + "('" + noTransaksi + "','" + kodeK + "','" + kodeM + "','" + totalbrg + "','" + totalhargadiambil + "','" + lbl_hargadiskon.getText() + "','" + bayarpilih + "','" + lbl_tgl.getText() + "')";
                    java.sql.Connection con = (Connection) Main.Koneksi.configDB();
                    java.sql.PreparedStatement pst = con.prepareStatement(sql);
                    System.out.println("QUERYYY TRANSAKSI = " + sql);
                    System.out.println("HARGA DISKON = " + lbl_hargadiskon.getText());
                    pst.executeUpdate();
                    pst.close();
                } catch (Exception e) {
                    System.out.println("simpan penjualan error " + e.getMessage());
                }

                try {
                    Connection c = (Connection) Main.Koneksi.configDB();
                    int baris = TabelTr.getRowCount();

                    for (int i = 0; i < baris; i++) {
                        String sql = "INSERT INTO `detail_transaksi_jual` (`kode_tr_jual`, `kode_produk`, `nama_produk`, `jumlah`, `total_harga`) VALUES "
                                + "('" + TabelTr.getValueAt(i, 0) + "','" + TabelTr.getValueAt(i, 1) + "','" + TabelTr.getValueAt(i, 2) + "','" + TabelTr.getValueAt(i, 4) + "','" + TabelTr.getValueAt(i, 5) + "')";
                        System.out.println("SQL DETAIL TRANS = " + sql);
                        PreparedStatement p = c.prepareStatement(sql);
                        p.executeUpdate();
                        p.close();
                    }
                } catch (Exception e) {
                    System.out.println("simpan penjualan rinci error" + e.getMessage());
                }
                new PopUp.BerhasilTransaksiJual(this, rootPaneCheckingEnabled).setVisible(true);
                PopUp.BerhasilTransaksiJual.lbl_kembali.setText(kembaliannyaa);
                            PopUp.BerhasilTransaksiJual.lbl_kode.setText(noTransaksi);

                PopUp.BerhasilTransaksiJual.lbl_kodeK.setText(kodeK);
                PopUp.BerhasilTransaksiJual.lbl_tgl.setText(tanggal);
                PopUp.BerhasilTransaksiJual.lbl_tunai.setText(checkBayar);

                clear2();
                utama();
                lbl_kodeTJ.setText(null);
                getId();
                kosong();

                getId();
                lbl_kodeK.setText(Login.getIdK());
                tanggalSaatIni();
                TabelFormat();
                lbl_hargadiskon.setText("0");
                lbl_kembalian.setText("Rp.0");
                lbl_totalharga.setText("Rp.0");
                lbl_kembalian.setBackground(java.awt.Color.WHITE);
                txt_kodeP.requestFocus();
                txt_bayar.setText(null);

            }
        }
    }//GEN-LAST:event_txt_bayarKeyPressed

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased

        if (txt_bayar.getText().equals("") || txt_bayar.getText().equals("0")) {
            lbl_kembalian.setText("Rp.0");
        } else {
            kembaliannyaa();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void txt_bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyTyped

    }//GEN-LAST:event_txt_bayarKeyTyped

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransaksiJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiJual(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelTr;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btn_cariP;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_transaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lbl_harga;
    public static javax.swing.JLabel lbl_hargadiskon;
    public static javax.swing.JLabel lbl_kembalian;
    private javax.swing.JLabel lbl_kodeK;
    public static javax.swing.JLabel lbl_kodeTJ;
    public static javax.swing.JLabel lbl_namaP;
    public static javax.swing.JLabel lbl_stok;
    public static javax.swing.JLabel lbl_tgl;
    private javax.swing.JLabel lbl_totalharga;
    public static javax.swing.JLabel lbl_ukuran;
    public static javax.swing.JLabel lbl_varian;
    public static javax.swing.JLabel totalbarang;
    private javax.swing.JLabel totalhargabrg;
    public static javax.swing.JTextField txt_bayar;
    public static javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kodeM;
    public static javax.swing.JTextField txt_kodeP;
    // End of variables declaration//GEN-END:variables
}
