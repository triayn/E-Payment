package Karyawan;

import Frame.Login;
import static Karyawan.TransaksiJual.lbl_kodeTJ;
import static Karyawan.TransaksiJual.txt_kodeP;
import java.awt.Dimension;
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
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;

public class TransaksiBeli extends javax.swing.JFrame {

    public TransaksiBeli() {
        initComponents();

        getId();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
        this.lbl_kodeK.setText(Login.getIdK());
        tanggalSaatIni();
        TabelFormat();
        this.txt_kodeS.requestFocus();
    }

    public void utama() {
        lbl_kodeTB.setText("");
        txt_kodeS.setText("");
        txt_nama.setText("");
        txt_totalhargaBeli.setText("");
        txt_jumlah.setText("");
        getId();
    }

    public void kosong() {
        DefaultTableModel model = (DefaultTableModel) TabelTrB.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    public void tambahTransaksi() {
        int harga, total;

        harga = Integer.valueOf(this.txt_totalhargaBeli.getText());
        total = harga;

        String idr1 = Main.Rupiah.convertToRupiah(Integer.parseInt(String.valueOf(total)));
        lbl_totalhargaBeli.setText(idr1);

        loadData();

        totalBiaya();

        clear2();
        this.txt_kodeS.requestFocus();
    }

    public void clear2() {
        txt_kodeS.setText("");
        txt_nama.setText("");
        txt_totalhargaBeli.setText("");
        txt_jumlah.setText("");
    }

    public void totalBiaya() {
        int jumlahBaris = TabelTrB.getRowCount();
        int totalBiaya = 0;
        int hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            hargaBarang = Integer.parseInt(TabelTrB.getValueAt(i, 4).toString());
            totalBiaya += hargaBarang;
        }
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);

        String idr1 = Main.Rupiah.convertToRupiah(totalBiaya);
        lbl_totalhargaBeli.setText(idr1);

        System.out.println("TOTAL HARGAAAA = " + totalBiaya);
        this.totalhargadiambil = totalBiaya;
        this.totalhargabrg.setText("" + totalBiaya);
        this.totalhargabrg.setVisible(false);
    }
    private int totalhargadiambil;

    public void loadData() {

        for (int i = 0; i < model.getRowCount(); i++) {
            if (txt_kodeS.getText().equals(String.valueOf(TabelTrB.getValueAt(i, 1)))) {
            }
        }

        if (this.txt_jumlah.getText().equals("0") || this.txt_jumlah.getText().equals("") || this.txt_jumlah.getText().equals(" ")) {
            IsiJumlah p = new IsiJumlah(this, true);
            p.setVisible(true);

        } else {

            String nama = txt_nama.getText();
            boolean duplikasi = true;
            if (TabelTrB.getRowCount() >= 1) {

                for (int i = 0; i < model.getRowCount(); i++) {

                    String namanya = (String) model.getValueAt(i, 2);

                    if (namanya.equalsIgnoreCase(nama)) {
                        System.out.println("KODE SUPPLIER = " + namanya);
                        System.out.println("DUPLIKASI = " + duplikasi);

                        duplikasi = false;
                        // Modify the quantity of the last row

                        int newTotal = Integer.parseInt(model.getValueAt(i, 4).toString());
                        String ppp = txt_jumlah.getText();
                        model.setValueAt(ppp, i, 3);

                        String totalhargatext = this.txt_totalhargaBeli.getText();
                        String totalhargaclean = totalhargatext.replaceAll("\\D+", "");
                        int totalhargaint = Integer.parseInt(totalhargaclean);

                        String total = Integer.toString(newTotal + totalhargaint);
                        model.setValueAt(total, i, 4);
                    }

                }
            }//batas
            int hargafix;
            String totalhargatext = this.txt_totalhargaBeli.getText();
            String totalhargaclean = totalhargatext.replaceAll("\\D+", "");
            int totalhargaint = Integer.parseInt(totalhargaclean);

            hargafix = Integer.valueOf(totalhargaint);
            if (duplikasi) {
                model.addRow(new Object[]{
                    lbl_kodeTB.getText(),
                    txt_kodeS.getText(),
                    this.txt_nama.getText(),
                    this.txt_jumlah.getText(),
                    hargafix
                });
            }// Tambahkan row baru jika tidak ada duplikasi

            TabelTrB.setModel(model);
        }
    }

    public void getId() {
        try {
            String sql = "SELECT kode_tr_beli FROM detail_transaksi_beli order by kode_tr_beli desc";
            Connection conn = (Connection) Main.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String Idno = rs.getString("kode_tr_beli").substring(5);
                String AN = "" + (Integer.parseInt(Idno) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "00";
                } else if (AN.length() == 2) {
                    Nol = "0";
                } else if (AN.length() == 3) {
                    Nol = "";
                }
                lbl_kodeTB.setText("TRB" + Nol + AN);
            } else {
                lbl_kodeTB.setText("TRB001");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private DefaultTableModel model;

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

        model.addColumn("Nota");
        model.addColumn("Kode Supplier");
        model.addColumn("Nama Produk");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        TabelTrB.getTableHeader().setBackground(new java.awt.Color(102, 153, 255));
        TabelTrB.getTableHeader().setForeground(java.awt.Color.BLACK);
        TabelTrB.getTableHeader().setPreferredSize(new Dimension(20, 35));
        TabelTrB.setModel(model);
        this.lebar_tabelbarang();
        txt_kodeS.requestFocus();

    }

    public void kembaliannyaa() {
        int hargafix;

        String totalhargatext = lbl_totalhargaBeli.getText();
        String totalhargaclean = totalhargatext.replaceAll("\\D+", "");
        int totalhargaint = Integer.parseInt(totalhargaclean);
        hargafix = Integer.valueOf(totalhargaint);

        int kembaliannya = Integer.valueOf(txt_bayarB.getText()) - hargafix;
        String idr1 = String.valueOf(kembaliannya);
        lbl_kembalianB.setText(idr1);
        String kembaliantext = lbl_kembalianB.getText();
        if (kembaliantext.contains("-")) {
            lbl_kembalianB.setText("0");
        }
    }

    private void lebar_tabelbarang() {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        TabelTrB.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        TabelTrB.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        TabelTrB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) TabelTrB.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = TabelTrB.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn kolom;
        TabelTrB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        kolom = TabelTrB.getColumnModel().getColumn(0);
        kolom.setPreferredWidth(50);
        kolom = TabelTrB.getColumnModel().getColumn(1);
        kolom.setPreferredWidth(160);
        kolom = TabelTrB.getColumnModel().getColumn(2);
        kolom.setPreferredWidth(100);
        kolom = TabelTrB.getColumnModel().getColumn(3);
        kolom.setPreferredWidth(50);
        kolom = TabelTrB.getColumnModel().getColumn(4);
        kolom.setPreferredWidth(90);

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        totalbarangdiambil = new javax.swing.JLabel();
        totalhargabrg = new javax.swing.JLabel();
        totalbarang = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelTrB = new javax.swing.JTable();
        lbl_kembalianB = new javax.swing.JLabel();
        lbl_kodeTB = new javax.swing.JLabel();
        lbl_totalhargaBeli = new javax.swing.JLabel();
        txt_kodeS = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        txt_totalhargaBeli = new javax.swing.JTextField();
        lbl_kodeK = new javax.swing.JLabel();
        lbl_tgl = new javax.swing.JLabel();
        txt_bayarB = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_transaksi = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_cariS = new javax.swing.JButton();

        totalbarangdiambil.setText("jLabel2");

        totalhargabrg.setText("jLabel2");

        totalbarang.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabelTrB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TabelTrB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        TabelTrB.setGridColor(new java.awt.Color(205, 31, 31));
        TabelTrB.setSelectionBackground(new java.awt.Color(205, 31, 31));
        jScrollPane1.setViewportView(TabelTrB);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 317, 790, 200));

        lbl_kembalianB.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_kembalianB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 580, 190, 30));

        lbl_kodeTB.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_kodeTB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_kodeTB.setText("0");
        getContentPane().add(lbl_kodeTB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 300, 40));

        lbl_totalhargaBeli.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_totalhargaBeli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_totalhargaBeli.setText("Rp.0");
        getContentPane().add(lbl_totalhargaBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 790, 40));

        txt_kodeS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_kodeS.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_kodeS.setBorder(null);
        txt_kodeS.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_kodeSInputMethodTextChanged(evt);
            }
        });
        txt_kodeS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeSActionPerformed(evt);
            }
        });
        txt_kodeS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_kodeSKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_kodeSKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_kodeSKeyTyped(evt);
            }
        });
        getContentPane().add(txt_kodeS, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 225, 180, 20));

        txt_nama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_nama.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_nama.setBorder(null);
        txt_nama.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_namaInputMethodTextChanged(evt);
            }
        });
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        txt_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_namaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_namaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_namaKeyTyped(evt);
            }
        });
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 280, 30));

        txt_jumlah.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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
        getContentPane().add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 80, 20));

        txt_totalhargaBeli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_totalhargaBeli.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_totalhargaBeli.setBorder(null);
        txt_totalhargaBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalhargaBeliActionPerformed(evt);
            }
        });
        txt_totalhargaBeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_totalhargaBeliKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_totalhargaBeliKeyReleased(evt);
            }
        });
        getContentPane().add(txt_totalhargaBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 150, 30));

        lbl_kodeK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(lbl_kodeK, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 190, 30));

        lbl_tgl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_tgl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 310, 30));

        txt_bayarB.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_bayarB.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_bayarB.setBorder(null);
        txt_bayarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarBActionPerformed(evt);
            }
        });
        txt_bayarB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_bayarBKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarBKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_bayarBKeyTyped(evt);
            }
        });
        getContentPane().add(txt_bayarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 533, 190, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/transaksiBeliBaru.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btn_transaksi.setText("btn_Transaksi");
        btn_transaksi.setOpaque(false);
        btn_transaksi.setContentAreaFilled(false);
        btn_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transaksiActionPerformed(evt);
            }
        });
        getContentPane().add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 170, 40));

        btnKembali.setOpaque(false);
        btnKembali.setContentAreaFilled(false);
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, 150, 40));

        btn_tambah.setText("Tambah");
        btn_tambah.setOpaque(false);
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 130, 40));

        btn_hapus.setText("hapus");
        btn_hapus.setOpaque(false);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 130, 40));

        btn_cariS.setOpaque(false);
        btn_cariS.setContentAreaFilled(false);
        btn_cariS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cariSMouseClicked(evt);
            }
        });
        btn_cariS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariSActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cariS, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 50, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        if (this.txt_jumlah.getText().isEmpty()) {

            IsiJumlah p = new IsiJumlah(this, true);
            p.setVisible(true);
        } else {

            tambahTransaksi();
        }
        this.txt_kodeS.requestFocus();
    }//GEN-LAST:event_btn_tambahActionPerformed


    private void btn_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transaksiActionPerformed
        String kembaliannyaa = lbl_kembalianB.getText();
        String noTransaksi = lbl_kodeTB.getText();
        String kodeS = this.txt_kodeS.getText();
        String kodeK = this.lbl_kodeK.getText();
        String tanggal = lbl_tgl.getText().toString().substring(0, 10);
        String totalbrg = this.totalbarang.getText();
        String checkBayar = txt_bayarB.getText();
        int bayar = 0;
        int bayarpilih;

        String totalhargatext1 = lbl_totalhargaBeli.getText();
        String totalhargaclean1 = totalhargatext1.replaceAll("\\D+", "");
        int totalhargaint1 = Integer.parseInt(totalhargaclean1);

        bayarpilih = totalhargaint1;

        int hargafix1;

        String totalhargatext = lbl_totalhargaBeli.getText();
        String totalhargaclean = totalhargatext.replaceAll("\\D+", "");
        int totalhargaint = Integer.parseInt(totalhargaclean);
        hargafix1 = Integer.valueOf(totalhargaint);

        if (checkBayar.isEmpty()) {

            IsiTunai p = new IsiTunai(this, true);
            p.setVisible(true);
        } else {
            bayar = Integer.parseInt(txt_bayarB.getText().replace(".", "").trim());
        }

        if (bayar < hargafix1) {
            PembayaranKurang p = new PembayaranKurang(this, true);
            p.setVisible(true);
        } else if (checkBayar.isEmpty()) {

            txt_bayarB.setText("");
            IsiTunai p = new IsiTunai(this, true);
            p.setVisible(true);

        } else {

            try {
                String sql = "INSERT INTO `transaksi_beli`(`kode_tr_beli`,`kode_karyawan`,`total_harga`, `tanggal`) VALUES "
                        + "('" + noTransaksi + "','" + kodeK + "','" + totalhargadiambil + "','" + lbl_tgl.getText() + "')";
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
                int baris = TabelTrB.getRowCount();

                for (int i = 0; i < baris; i++) {
                    String sql = "INSERT INTO `detail_transaksi_beli` (`kode_tr_beli`, `kode_supplier`, `nama_barang`, `jumlah_barang`, `total_harga`) VALUES "
                            + "('" + TabelTrB.getValueAt(i, 0) + "','" + TabelTrB.getValueAt(i, 1) + "','" + TabelTrB.getValueAt(i, 2) + "','" + TabelTrB.getValueAt(i, 3) + "','" + TabelTrB.getValueAt(i, 4) + "')";
                    System.out.println("SQL DETAIL TRANS = " + sql);
                    PreparedStatement p = c.prepareStatement(sql);
                    p.executeUpdate();
                    p.close();
                }
            } catch (Exception e) {
                System.out.println("simpan penjualan rinci error" + e.getMessage());
            }
            new PopUp.BerhasilTransaksiBeli(this, rootPaneCheckingEnabled).setVisible(true);
            PopUp.BerhasilTransaksiBeli.lbl_kembali.setText(kembaliannyaa);
            PopUp.BerhasilTransaksiBeli.lbl_kode.setText(noTransaksi);

            PopUp.BerhasilTransaksiBeli.lbl_kodeK.setText(kodeK);
            PopUp.BerhasilTransaksiBeli.lbl_tgl.setText(tanggal);
            PopUp.BerhasilTransaksiBeli.lbl_tunai.setText(checkBayar);
            PopUp.BerhasilTransaksiBeli.lbl_kode.setText(noTransaksi);
            clear2();
            utama();

            kosong();

            lbl_kodeK.setText(Login.getIdK());
            tanggalSaatIni();
            TabelFormat();
            lbl_kembalianB.setText("0");
            lbl_totalhargaBeli.setText("Rp.0");
            lbl_kembalianB.setBackground(java.awt.Color.WHITE);
            txt_bayarB.setText(null);

            getId();
        }
    }//GEN-LAST:event_btn_transaksiActionPerformed


    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        DefaultTableModel model = (DefaultTableModel) TabelTrB.getModel();
        int row = TabelTrB.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_cariSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cariSMouseClicked

    }//GEN-LAST:event_btn_cariSMouseClicked

    private void btn_cariSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariSActionPerformed
        new PopUp.GetDataSupplier(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btn_cariSActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        new Karyawan.MenuTransaksi().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed

    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void txt_jumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_totalhargaBeli.requestFocus();
        }
    }//GEN-LAST:event_txt_jumlahKeyPressed

    private void txt_jumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahKeyReleased

    }//GEN-LAST:event_txt_jumlahKeyReleased

    private void txt_kodeSInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_kodeSInputMethodTextChanged

    }//GEN-LAST:event_txt_kodeSInputMethodTextChanged

    private void txt_kodeSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodeSActionPerformed

    }//GEN-LAST:event_txt_kodeSActionPerformed

    private void txt_kodeSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kodeSKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String query = "SELECT * FROM supplier WHERE kode_supplier = '" + txt_kodeS.getText() + "'";
                Connection conn = (Connection) Koneksi.configDB();
                Statement stat = conn.createStatement();
                ResultSet r = stat.executeQuery(query);

                if (r.next()) {
                    if (r.getString("kode_supplier") == null) {
                        JOptionPane.showMessageDialog(rootPane, "Bukan Kode Supplier");
                        txt_kodeS.setText(null);

                    } else {
                        txt_nama.requestFocus();
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }

        if (evt.getKeyCode() == (KeyEvent.VK_RIGHT)) {
            new PopUp.GetDataSupplier(this, rootPaneCheckingEnabled).setVisible(true);
        }
    }//GEN-LAST:event_txt_kodeSKeyPressed

    private void txt_kodeSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kodeSKeyReleased

    }//GEN-LAST:event_txt_kodeSKeyReleased

    private void txt_kodeSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kodeSKeyTyped

    }//GEN-LAST:event_txt_kodeSKeyTyped

    private void txt_totalhargaBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalhargaBeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalhargaBeliActionPerformed

    private void txt_totalhargaBeliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalhargaBeliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {        // TODO add your handling code here:
            if (this.txt_jumlah.getText().isEmpty()) {

                IsiJumlah p = new IsiJumlah(this, true);
                p.setVisible(true);
            } else {

                tambahTransaksi();
            }
            this.txt_kodeS.requestFocus();
        }
    }//GEN-LAST:event_txt_totalhargaBeliKeyPressed

    private void txt_totalhargaBeliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalhargaBeliKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalhargaBeliKeyReleased

    private void txt_namaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_namaInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaInputMethodTextChanged

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_namaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_jumlah.requestFocus();
        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_namaKeyPressed

    private void txt_namaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaKeyReleased

    private void txt_namaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaKeyTyped

    private void txt_bayarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bayarBActionPerformed

    private void txt_bayarBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String kembaliannyaa = lbl_kembalianB.getText();
            String noTransaksi = lbl_kodeTB.getText();
            String kodeS = this.txt_kodeS.getText();
            String kodeK = this.lbl_kodeK.getText();
            String tanggal = lbl_tgl.getText().toString().substring(0, 10);
            String totalbrg = this.totalbarang.getText();
            String checkBayar = txt_bayarB.getText();
            int bayar = 0;
            int bayarpilih;

            String totalhargatext1 = lbl_totalhargaBeli.getText();
            String totalhargaclean1 = totalhargatext1.replaceAll("\\D+", "");
            int totalhargaint1 = Integer.parseInt(totalhargaclean1);

            bayarpilih = totalhargaint1;

            int hargafix1;

            String totalhargatext = lbl_totalhargaBeli.getText();
            String totalhargaclean = totalhargatext.replaceAll("\\D+", "");
            int totalhargaint = Integer.parseInt(totalhargaclean);
            hargafix1 = Integer.valueOf(totalhargaint);

            if (checkBayar.isEmpty()) {

                IsiTunai p = new IsiTunai(this, true);
                p.setVisible(true);
            } else {
                bayar = Integer.parseInt(txt_bayarB.getText().replace(".", "").trim());
            }

            if (bayar < hargafix1) {
                PembayaranKurang p = new PembayaranKurang(this, true);
                p.setVisible(true);
            } else if (checkBayar.isEmpty()) {

                txt_bayarB.setText("");
                IsiTunai p = new IsiTunai(this, true);
                p.setVisible(true);

            } else {

                try {
                    String sql = "INSERT INTO `transaksi_beli`(`kode_tr_beli`,`kode_karyawan`,`total_harga`, `tanggal`) VALUES "
                            + "('" + noTransaksi + "','" + kodeK + "','" + totalhargadiambil + "','" + lbl_tgl.getText() + "')";
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
                    int baris = TabelTrB.getRowCount();

                    for (int i = 0; i < baris; i++) {
                        String sql = "INSERT INTO `detail_transaksi_beli` (`kode_tr_beli`, `kode_supplier`, `nama_barang`, `jumlah_barang`, `total_harga`) VALUES "
                                + "('" + TabelTrB.getValueAt(i, 0) + "','" + TabelTrB.getValueAt(i, 1) + "','" + TabelTrB.getValueAt(i, 2) + "','" + TabelTrB.getValueAt(i, 3) + "','" + TabelTrB.getValueAt(i, 4) + "')";
                        System.out.println("SQL DETAIL TRANS = " + sql);
                        PreparedStatement p = c.prepareStatement(sql);
                        p.executeUpdate();
                        p.close();
                    }
                } catch (Exception e) {
                    System.out.println("simpan penjualan rinci error" + e.getMessage());
                }
                new PopUp.BerhasilTransaksiBeli(this, rootPaneCheckingEnabled).setVisible(true);
                PopUp.BerhasilTransaksiBeli.lbl_kembali.setText(kembaliannyaa);
                PopUp.BerhasilTransaksiBeli.lbl_kode.setText(noTransaksi);

                PopUp.BerhasilTransaksiBeli.lbl_kodeK.setText(kodeK);
                PopUp.BerhasilTransaksiBeli.lbl_tgl.setText(tanggal);
                PopUp.BerhasilTransaksiBeli.lbl_tunai.setText(checkBayar);
                PopUp.BerhasilTransaksiBeli.lbl_kode.setText(noTransaksi);
                clear2();
                utama();

                kosong();

                lbl_kodeK.setText(Login.getIdK());
                tanggalSaatIni();
                TabelFormat();
                lbl_kembalianB.setText("0");
                lbl_totalhargaBeli.setText("Rp.0");
                lbl_kembalianB.setBackground(java.awt.Color.WHITE);
                txt_bayarB.setText(null);

                getId();
            }
        }
    }//GEN-LAST:event_txt_bayarBKeyPressed

    private void txt_bayarBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarBKeyReleased
        if (txt_bayarB.getText().equals("") || txt_bayarB.getText().equals("0")) {
            lbl_kembalianB.setText("0");
        } else {
            kembaliannyaa();
        }
    }//GEN-LAST:event_txt_bayarBKeyReleased


    private void txt_bayarBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarBKeyTyped

        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bayarBKeyTyped

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
            java.util.logging.Logger.getLogger(TransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiBeli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelTrB;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btn_cariS;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_transaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lbl_kembalianB;
    private javax.swing.JLabel lbl_kodeK;
    public static javax.swing.JLabel lbl_kodeTB;
    public static javax.swing.JLabel lbl_tgl;
    private javax.swing.JLabel lbl_totalhargaBeli;
    public static javax.swing.JLabel totalbarang;
    private javax.swing.JLabel totalbarangdiambil;
    private javax.swing.JLabel totalhargabrg;
    public static javax.swing.JTextField txt_bayarB;
    public static javax.swing.JTextField txt_jumlah;
    public static javax.swing.JTextField txt_kodeS;
    public static javax.swing.JTextField txt_nama;
    public static javax.swing.JTextField txt_totalhargaBeli;
    // End of variables declaration//GEN-END:variables
}
