package Owner;

import Main.Koneksi;
import com.mysql.cj.util.Util;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class LaporanPendapatan extends javax.swing.JFrame {

    private String namaBulan = "???";

    public LaporanPendapatan() {
        initComponents();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
        this.btn_harian.setEnabled(false);
        btn_mingguan.setEnabled(true);
        btn_bulanan.setEnabled(true);
        this.panelHarian.setVisible(true);
        this.panelMingguan.setVisible(false);
        this.panelBulanan.setVisible(false);
        this.exelHari.setVisible(true);
        HeaderColumnH();
        HeaderColumnH();
        HeaderColumnM();
        panel_chart.setVisible(false);
    }

// FUNGSI UNTUK MEREFRESH HARIAN
    public void RefreshH() {

        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tanggal");
        tbl.addColumn("Kode Transaksi");
        tbl.addColumn("Kode karyawan");
        tbl.addColumn("Total Item");
        tbl.addColumn("Total Bayar");
        tbl_hari.setModel(tbl);
        try {

            tbl.addRow(new Object[]{});
            tbl_hari.setModel(tbl);

            HeaderColumnH();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e);
        }
    }

    // FUNGSI UNTUK MEREFRESH MINGGUAN
    public void RefreshM() {

        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tanggal");
        tbl.addColumn("Kode Transaksi");
        tbl.addColumn("Kode karyawan");
        tbl.addColumn("Total Item");
        tbl.addColumn("Total Bayar");
        tbl_mingguan.setModel(tbl);
        try {

            tbl.addRow(new Object[]{});
            tbl_mingguan.setModel(tbl);

            HeaderColumnH();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e);
        }
    }

    // FUNGSI UNTUK MEREFRESH bulanan
    public void RefreshB() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tahun");
        tbl.addColumn("Bulan");
        tbl.addColumn("Produk Terjaul");
        tbl.addColumn("Total Pendapatan");
        tbl_bulanan.setModel(tbl);
        try {

            lbl_TotaltransaksiB.setText("");
            lbl_pendapatanB.setText("");

            tbl.addRow(new Object[]{});
            tbl_bulanan.setModel(tbl);
            HeaderColumnB();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e);
        }

    }

    //KEBUTUHAN PANEL HARIAN
    public void totalTransH() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(tgl_hari.getDate()));

        try {
            String sql = "SELECT COUNT(*) AS jumlah_baris FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("jumlah_baris") == null) {
                    this.lbl_TotaltransaksiH.setText("0");
                } else {
                    this.lbl_TotaltransaksiH.setText((rs.getString("jumlah_baris")));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void totalH() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(tgl_hari.getDate()));

        try {
            String sql = "SELECT SUM(total_harga) as total FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("total") == null) {
                    this.lbl_pendapatanH.setText(Main.Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_pendapatanH.setText(Main.Rupiah.convertToRupiah(Integer.parseInt(rs.getString("total"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void HeaderColumnH() {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tbl_hari.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tbl_hari.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        center.setHorizontalAlignment(JLabel.CENTER);
        tbl_hari.getColumnModel().getColumn(0).setCellRenderer(center);
        tbl_hari.getColumnModel().getColumn(2).setCellRenderer(center);
        tbl_hari.getColumnModel().getColumn(3).setCellRenderer(center);
        tbl_hari.getColumnModel().getColumn(4).setCellRenderer(center);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) tbl_hari.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = tbl_hari.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn column;
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(0);
        column.setPreferredWidth(85);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(1);
        column.setPreferredWidth(185);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(2);
        column.setPreferredWidth(197);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(3);
        column.setPreferredWidth(197);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(4);
        column.setPreferredWidth(207);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(5);
        column.setPreferredWidth(227);
    }

    public void pencarianH() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(tgl_hari.getDate()));
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tanggal");
        tbl.addColumn("Kode Transaksi");
        tbl.addColumn("Kode karyawan");
        tbl.addColumn("Total Item");
        tbl.addColumn("Total Bayar");
        tbl_hari.setModel(tbl);
        try {
            String sql = "SELECT * FROM transaksi_jual WHERE DATE(tanggal) = '" + tgl + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("tanggal"),
                    res.getString("kode_tr_jual"),
                    res.getString("kode_karyawan"),
                    res.getString("total_item"),
                    Main.Rupiah.convertToRupiah(Integer.parseInt(res.getString("bayar")))
                });
                tbl_hari.setModel(tbl);
                totalH();
                HeaderColumnH();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e);
        }
    }

    //KEBUTUHAN PANEL MINGGUAN
    public void totalTransM() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl1 = String.valueOf(fm.format(tgl_awal.getDate()));
        String tgl2 = String.valueOf(fm.format(tgl_akhir.getDate()));

        try {
            String sql = "SELECT COUNT(*) AS jumlah_baris FROM transaksi_jual WHERE DATE(tanggal) BETWEEN '" + tgl1 + "' AND '" + tgl2 + "'";
            System.out.println("sql total Mingguan: " + sql);
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("jumlah_baris") == null) {
                    this.lbl_TotaltransaksiM.setText("0");
                } else {
                    this.lbl_TotaltransaksiM.setText(((rs.getString("jumlah_baris"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void totalM() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl1 = String.valueOf(fm.format(tgl_awal.getDate()));
        String tgl2 = String.valueOf(fm.format(tgl_akhir.getDate()));

        try {
            String sql = "SELECT SUM(total_harga) as total FROM transaksi_jual WHERE DATE(tanggal) BETWEEN '" + tgl1 + "' AND '" + tgl2 + "'";
            System.out.println("sql total Mingguan: " + sql);
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("total") == null) {
                    this.lbl_pendapatanM.setText(Main.Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_pendapatanM.setText(Main.Rupiah.convertToRupiah(Integer.parseInt(rs.getString("total"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void HeaderColumnM() {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tbl_mingguan.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tbl_mingguan.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        center.setHorizontalAlignment(JLabel.CENTER);
        tbl_mingguan.getColumnModel().getColumn(0).setCellRenderer(center);
        tbl_mingguan.getColumnModel().getColumn(2).setCellRenderer(center);
        tbl_mingguan.getColumnModel().getColumn(3).setCellRenderer(center);
        tbl_mingguan.getColumnModel().getColumn(4).setCellRenderer(center);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) tbl_mingguan.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = tbl_mingguan.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Verdana", Font.BOLD, 12));

        TableColumn column;
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(0);
        column.setPreferredWidth(85);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(1);
        column.setPreferredWidth(185);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(2);
        column.setPreferredWidth(197);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(3);
        column.setPreferredWidth(197);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(4);
        column.setPreferredWidth(207);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(5);
        column.setPreferredWidth(227);
    }

    public void pencarianM() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl1 = String.valueOf(fm.format(tgl_awal.getDate()));
        String tgl2 = String.valueOf(fm.format(tgl_akhir.getDate()));
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tanggal");
        tbl.addColumn("Kode Transaksi");
        tbl.addColumn("Kode karyawan");
        tbl.addColumn("Total Item");
        tbl.addColumn("Total Bayar");
        tbl_mingguan.setModel(tbl);
        try {
            String sql = "SELECT * FROM transaksi_jual WHERE DATE(tanggal)"
                    + " BETWEEN '" + tgl1 + "' AND '" + tgl2 + "'";

            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("tanggal"),
                    res.getString("kode_tr_jual"),
                    res.getString("kode_karyawan"),
                    res.getString("total_item"),
                    Main.Rupiah.convertToRupiah(Integer.parseInt(res.getString("bayar")))
                });
                tbl_mingguan.setModel(tbl);
                totalM();
                HeaderColumnM();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e.getMessage());
        }
    }

    //KEBUTUHAN PANEL BULANAN
    public void totalTransB() {
        try {
            String sql = "SELECT COUNT(*) AS jumlah_transaksi\n"
                    + "FROM transaksi_jual\n"
                    + "WHERE year(tanggal) ='" + Tahun.getYear() + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("jumlah_transaksi") == null) {
                    this.lbl_TotaltransaksiB.setText(Main.Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_TotaltransaksiB.setText(rs.getString("jumlah_transaksi"));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void totalB() {
        try {
            String sql = "SELECT SUM(total_harga) AS total FROM transaksi_jual WHERE year(tanggal) ='" + Tahun.getYear() + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("total") == null) {
                    this.lbl_pendapatanB.setText(Main.Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_pendapatanB.setText(Main.Rupiah.convertToRupiah(Integer.parseInt(rs.getString("total"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void HeaderColumnB() {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tbl_bulanan.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tbl_bulanan.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        tbl_bulanan.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tbl_bulanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        center.setHorizontalAlignment(JLabel.CENTER);
        tbl_bulanan.getColumnModel().getColumn(0).setCellRenderer(center);
        tbl_bulanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) tbl_bulanan.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = tbl_bulanan.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Verdana", Font.BOLD, 12));

        TableColumn column;
        tbl_bulanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_bulanan.getColumnModel().getColumn(0);
        column.setPreferredWidth(33);
        tbl_bulanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_bulanan.getColumnModel().getColumn(1);
        column.setPreferredWidth(125);
        tbl_bulanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_bulanan.getColumnModel().getColumn(2);
        column.setPreferredWidth(127);
        tbl_bulanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_bulanan.getColumnModel().getColumn(3);
        column.setPreferredWidth(123);
        tbl_bulanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_bulanan.getColumnModel().getColumn(4);
        column.setPreferredWidth(200);
    }

    public void pencarianB() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tahun");
        tbl.addColumn("Bulan");
        tbl.addColumn("Produk Terjaul");
        tbl.addColumn("Total Pendapatan");
        tbl_bulanan.setModel(tbl);
        try {
            String sql = "SELECT SUM(total_harga) AS totalPerbulan ,  MONTH(tanggal)"
                    + " AS Bulan_ke , YEAR(tanggal) AS Tahun, "
                    + "SUM(total_item) AS produk, COUNT(*) AS jumlah_baris  FROM transaksi_jual "
                    + "WHERE MONTH(tanggal) >=1 AND YEAR(tanggal) = '" + Tahun.getYear() + 
                    "' GROUP BY MONTH(tanggal)";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("Tahun"),
                    this.getBulan(res.getInt("Bulan_ke")),
                    res.getString("produk"),
                    //                    res.getString("jumlah_baris"),
                    Main.Rupiah.convertToRupiah(Integer.parseInt(res.getString("totalPerbulan")))
                });
                tbl_bulanan.setModel(tbl);
                HeaderColumnB();
                totalB();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e);
        }

    }

    private String getBulan(int bulan) {
        switch (bulan - 1) {
            case Calendar.JANUARY:
                return "Januari";
            case Calendar.FEBRUARY:
                return "Februari";
            case Calendar.MARCH:
                return "Maret";
            case Calendar.APRIL:
                return "April";
            case Calendar.MAY:
                return "Mei";
            case Calendar.JUNE:
                return "Juni";
            case Calendar.JULY:
                return "Juli";
            case Calendar.AUGUST:
                return "Agustus";
            case Calendar.SEPTEMBER:
                return "September";
            case Calendar.OCTOBER:
                return "Oktober";
            case Calendar.NOVEMBER:
                return "November";
            case Calendar.DECEMBER:
                return "Desember";
            default:
                return "null";
        }
    }

    //KEBUTUHAN MENDAPATKAN DATA HARI DAN BULAN
    private Calendar kalender = Calendar.getInstance();

    public String getNamaHari(int dayOfWeek) {
        switch (kalender.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                return "Minggu";
            case Calendar.MONDAY:
                return "Senin";
            case Calendar.TUESDAY:
                return "Selasa";
            case Calendar.WEDNESDAY:
                return "Rabu";
            case Calendar.THURSDAY:
                return "Kamis";
            case Calendar.FRIDAY:
                return "Jumat";
            case Calendar.SATURDAY:
                return "Sabtu";
            default:
                return "null";
        }
    }

    public String getNamaBulan(int bulan) {
        switch (bulan) {
            case Calendar.JANUARY:
                return "Januari";
            case Calendar.FEBRUARY:
                return "Februari";
            case Calendar.MARCH:
                return "Maret";
            case Calendar.APRIL:
                return "April";
            case Calendar.MAY:
                return "Mei";
            case Calendar.JUNE:
                return "Juni";
            case Calendar.JULY:
                return "Juli";
            case Calendar.AUGUST:
                return "Agustus";
            case Calendar.SEPTEMBER:
                return "September";
            case Calendar.OCTOBER:
                return "Oktober";
            case Calendar.NOVEMBER:
                return "November";
            case Calendar.DECEMBER:
                return "Desember";
            default:
                return "null";
        }
    }

    public int getNilaiBulan(String bulan) {
        switch (bulan) {
            case "Januari":
                return 1;
            case "Februari":
                return 2;
            case "Maret":
                return 3;
            case "April":
                return 4;
            case "Mei":
                return 5;
            case "Juni":
                return 6;
            case "Juli":
                return 7;
            case "Agustus":
                return 8;
            case "September":
                return 9;
            case "Oktober":
                return 10;
            case "November":
                return 11;
            case "Desember":
                return 12;
            default:
                return -11;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ambilkodetransaksijual = new javax.swing.JTextField();
        panelBulanan = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_bulanan = new javax.swing.JTable();
        panel_chart = new javax.swing.JPanel();
        lbl_pendapatanB = new javax.swing.JLabel();
        lbl_TotaltransaksiB = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Tahun = new com.toedter.calendar.JYearChooser();
        backgroundBulan = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        exelBulan = new javax.swing.JButton();
        panelHarian = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_hari = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tgl_hari = new com.toedter.calendar.JDateChooser();
        lbl_pendapatanH = new javax.swing.JLabel();
        lbl_TotaltransaksiH = new javax.swing.JLabel();
        backgroundHari = new javax.swing.JLabel();
        btn_cari = new javax.swing.JButton();
        panelMingguan = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_mingguan = new javax.swing.JTable();
        tgl_akhir = new com.toedter.calendar.JDateChooser();
        tgl_awal = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_pendapatanM = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lbl_TotaltransaksiM = new javax.swing.JLabel();
        backgroundMinggu = new javax.swing.JLabel();
        btn_mingguan = new javax.swing.JButton();
        btn_bulanan = new javax.swing.JButton();
        btn_harian = new javax.swing.JButton();
        backgroundnya = new javax.swing.JLabel();
        exelHari = new javax.swing.JButton();
        exelMinggu = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();

        ambilkodetransaksijual.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBulanan.setBackground(new java.awt.Color(255, 255, 255));
        panelBulanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_bulanan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbl_bulanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Tahun", "Bulan", "Produk Terjual", "Total Pendapatan"
            }
        ));
        tbl_bulanan.setGridColor(new java.awt.Color(205, 31, 31));
        tbl_bulanan.setSelectionBackground(new java.awt.Color(205, 31, 31));
        tbl_bulanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bulananMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_bulanan);

        panelBulanan.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 610, 200));

        panel_chart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panel_chart.setLayout(new java.awt.BorderLayout());
        panelBulanan.add(panel_chart, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 450, 280));

        lbl_pendapatanB.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelBulanan.add(lbl_pendapatanB, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 200, 30));

        lbl_TotaltransaksiB.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelBulanan.add(lbl_TotaltransaksiB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 120, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Cari.png"))); // NOI18N
        panelBulanan.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));
        panelBulanan.add(Tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, 30));

        backgroundBulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/laporanPendapatanB.png"))); // NOI18N
        panelBulanan.add(backgroundBulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -190, -1, -1));

        jButton2.setOpaque(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelBulanan.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 40, 40));

        exelBulan.setText("jButton4");
        exelBulan.setOpaque(false);
        exelBulan.setContentAreaFilled(false);
        exelBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exelBulanActionPerformed(evt);
            }
        });
        panelBulanan.add(exelBulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, 150, 50));

        getContentPane().add(panelBulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 1170, 410));

        panelHarian.setBackground(new java.awt.Color(255, 255, 255));
        panelHarian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_hari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbl_hari.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Kode Transaksi", "Kode Karyawan", "Total Item", "Total Bayar"
            }
        ));
        tbl_hari.setGridColor(new java.awt.Color(205, 31, 31));
        tbl_hari.setSelectionBackground(new java.awt.Color(205, 31, 31));
        tbl_hari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hariMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_hari);

        panelHarian.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 1100, 230));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Cari.png"))); // NOI18N
        panelHarian.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        tgl_hari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tgl_hari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tgl_hariKeyReleased(evt);
            }
        });
        panelHarian.add(tgl_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, 30));

        lbl_pendapatanH.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelHarian.add(lbl_pendapatanH, new org.netbeans.lib.awtextra.AbsoluteConstraints(955, 50, 190, 30));

        lbl_TotaltransaksiH.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelHarian.add(lbl_TotaltransaksiH, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 120, 30));

        backgroundHari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/laporanPendapatanH.png"))); // NOI18N
        panelHarian.add(backgroundHari, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -190, -1, -1));

        btn_cari.setOpaque(false);
        btn_cari.setContentAreaFilled(false);
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        panelHarian.add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 40, 40));

        getContentPane().add(panelHarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 1170, 340));

        panelMingguan.setBackground(new java.awt.Color(255, 255, 255));
        panelMingguan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_mingguan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbl_mingguan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Kode Transaksi", "Kode Karyawan", "Total Item", "Total Bayar"
            }
        ));
        tbl_mingguan.setGridColor(new java.awt.Color(205, 31, 31));
        tbl_mingguan.setSelectionBackground(new java.awt.Color(205, 31, 31));
        tbl_mingguan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_mingguanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_mingguan);

        panelMingguan.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 1100, 230));

        tgl_akhir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tgl_akhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tgl_akhirKeyReleased(evt);
            }
        });
        panelMingguan.add(tgl_akhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 160, 30));

        tgl_awal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tgl_awal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tgl_awalKeyReleased(evt);
            }
        });
        panelMingguan.add(tgl_awal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setText("-");
        panelMingguan.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 24, -1, 80));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Cari.png"))); // NOI18N
        panelMingguan.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 50, -1));

        lbl_pendapatanM.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelMingguan.add(lbl_pendapatanM, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 160, 30));

        jButton1.setOpaque(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelMingguan.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 50, 40));

        lbl_TotaltransaksiM.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lbl_TotaltransaksiM.setText("0");
        panelMingguan.add(lbl_TotaltransaksiM, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 120, 30));

        backgroundMinggu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/laporanPendapatanH.png"))); // NOI18N
        panelMingguan.add(backgroundMinggu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -190, -1, -1));

        getContentPane().add(panelMingguan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 1170, 340));

        btn_mingguan.setOpaque(false);
        btn_mingguan.setContentAreaFilled(false);
        btn_mingguan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mingguanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_mingguan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 90, 30));

        btn_bulanan.setOpaque(false);
        btn_bulanan.setContentAreaFilled(false);
        btn_bulanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bulananActionPerformed(evt);
            }
        });
        getContentPane().add(btn_bulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 80, 30));

        btn_harian.setOpaque(false);
        btn_harian.setContentAreaFilled(false);
        btn_harian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_harianActionPerformed(evt);
            }
        });
        getContentPane().add(btn_harian, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 80, 30));

        backgroundnya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/laporanPendapatanH.png"))); // NOI18N
        getContentPane().add(backgroundnya, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        exelHari.setText("jButton4");
        exelHari.setOpaque(false);
        exelHari.setContentAreaFilled(false);
        exelHari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exelHariActionPerformed(evt);
            }
        });
        getContentPane().add(exelHari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 540, 150, 50));

        exelMinggu.setText("jButton4");
        exelMinggu.setOpaque(false);
        exelMinggu.setContentAreaFilled(false);
        exelMinggu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exelMingguActionPerformed(evt);
            }
        });
        getContentPane().add(exelMinggu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 540, 150, 50));

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


    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed

        new MenuOwner().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_harianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_harianActionPerformed
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Image/laporanPendapatanH.png"));
        backgroundnya.setIcon(icon);
        backgroundHari.setIcon(icon);
        btn_harian.setEnabled(false);
        btn_mingguan.setEnabled(true);
        btn_bulanan.setEnabled(true);

        this.panelHarian.setVisible(true);
        this.panelMingguan.setVisible(false);
        this.panelBulanan.setVisible(false);
        this.exelHari.setVisible(true);
        this.exelMinggu.setVisible(false);
        this.exelBulan.setVisible(false);
        tgl_akhir.setDate(null);
        tgl_awal.setDate(null);
        RefreshM();
        RefreshB();
        lbl_TotaltransaksiM.setText("");
        lbl_pendapatanM.setText("");
        HeaderColumnH();

    }//GEN-LAST:event_btn_harianActionPerformed

    private void btn_mingguanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mingguanActionPerformed
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Image/laporanPendapatanM.png"));
        backgroundnya.setIcon(icon);
        backgroundMinggu.setIcon(icon);
        btn_mingguan.setEnabled(false);
        btn_harian.setEnabled(true);
        btn_bulanan.setEnabled(true);
        this.panelHarian.setVisible(false);
        this.panelMingguan.setVisible(true);
        this.panelBulanan.setVisible(false);

        this.exelHari.setVisible(false);
        this.exelMinggu.setVisible(true);
        this.exelBulan.setVisible(false);
        tgl_hari.setDate(null);
        lbl_pendapatanH.setText("");
        lbl_TotaltransaksiH.setText("");
        RefreshH();
        RefreshB();
        HeaderColumnM();
    }//GEN-LAST:event_btn_mingguanActionPerformed

    private void btn_bulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bulananActionPerformed
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Image/laporanPendapatanB.png"));
        backgroundnya.setIcon(icon);
        backgroundBulan.setIcon(icon);
        btn_harian.setEnabled(true);
        btn_mingguan.setEnabled(true);
        btn_bulanan.setEnabled(false);
        this.panelHarian.setVisible(false);
        this.panelMingguan.setVisible(false);
        this.panelBulanan.setVisible(true);

        this.exelHari.setVisible(false);
        this.exelMinggu.setVisible(false);
        this.exelBulan.setVisible(true);
        tgl_akhir.setDate(null);
        tgl_awal.setDate(null);
        RefreshM();

        lbl_pendapatanM.setText("");
        lbl_TotaltransaksiM.setText("");
        RefreshH();
        tgl_hari.setDate(null);
        lbl_pendapatanH.setText("");
        lbl_TotaltransaksiH.setText("");
        RefreshH();
        HeaderColumnB();
    }//GEN-LAST:event_btn_bulananActionPerformed

    private void tgl_hariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_hariKeyReleased

    }//GEN-LAST:event_tgl_hariKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (tgl_awal.getDate() == null || tgl_akhir.getDate() == null) {
            Informasi.PilihTanggal p = new Informasi.PilihTanggal(this, true);
            p.setVisible(true);
        } else {
            pencarianM();
            totalM();
            totalTransM();
            HeaderColumnH();
            HeaderColumnB();
            HeaderColumnM();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (Tahun == null) {
            Informasi.PilihTanggal p = new Informasi.PilihTanggal(this, true);
            p.setVisible(true);
        } else {
            pencarianB();
            totalB();
            totalTransB();
            HeaderColumnH();
            HeaderColumnH();
            HeaderColumnM();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        if (tgl_hari.getDate() == null) {
            Informasi.PilihTanggal p = new Informasi.PilihTanggal(this, true);
            p.setVisible(true);
        } else {

            pencarianH();
            totalH();
            totalTransH();
            HeaderColumnH();
            HeaderColumnH();
            HeaderColumnM();

        }
    }//GEN-LAST:event_btn_cariActionPerformed
    public void RubahKeExcel(javax.swing.JTable table, File file) {
        try {
            DefaultTableModel Model_Data = (DefaultTableModel) table.getModel();
            FileWriter ObjWriter = new FileWriter(file);
            for (int i = 0; i < Model_Data.getColumnCount(); i++) {
                ObjWriter.write(Model_Data.getColumnName(i) + "\t");
            }

            ObjWriter.write("\n");

            for (int i = 0; i < Model_Data.getRowCount(); i++) {
                for (int j = 0; j < Model_Data.getColumnCount(); j++) {
                    ObjWriter.write(Model_Data.getValueAt(i, j).toString() + "\t");
                }
                ObjWriter.write("\n");
            }

            ObjWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void exelHariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exelHariActionPerformed
        if (tgl_hari.getDate() == null) {
            Informasi.TabelKosong p = new Informasi.TabelKosong(this, true);
            p.setVisible(true);
        } else {
            JFileChooser path = new JFileChooser();

            path.showOpenDialog(this);
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            try {
                File f = path.getSelectedFile();
                String location = f.getAbsolutePath();
                String filename = location + "_" + date + ".xls";
                File fp = new File(filename);

                RubahKeExcel(tbl_hari, fp);
                Informasi.BerhasilExport p = new Informasi.BerhasilExport(this, true);
                p.setVisible(true);
            } catch (Exception e) {
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_exelHariActionPerformed

    private void exelMingguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exelMingguActionPerformed
        if (tgl_awal.getDate() == null || tgl_akhir.getDate() == null) {
            Informasi.TabelKosong p = new Informasi.TabelKosong(this, true);
            p.setVisible(true);
        } else {
            JFileChooser path = new JFileChooser();

            path.showOpenDialog(this);
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            try {
                File f = path.getSelectedFile();
                String location = f.getAbsolutePath();
                String filename = location + "_" + date + ".xls";
                File fp = new File(filename);

                RubahKeExcel(tbl_mingguan, fp);
                Informasi.BerhasilExport p = new Informasi.BerhasilExport(this, true);
                p.setVisible(true);
            } catch (Exception e) {
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_exelMingguActionPerformed

    private void exelBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exelBulanActionPerformed
        if (Tahun == null) {
            Informasi.TabelKosong p = new Informasi.TabelKosong(this, true);
            p.setVisible(true);
        } else {
            JFileChooser path = new JFileChooser();

            path.showOpenDialog(this);
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            try {
                File f = path.getSelectedFile();
                String location = f.getAbsolutePath();
                String filename = location + "_" + date + ".xls";
                File fp = new File(filename);

                RubahKeExcel(tbl_bulanan, fp);
                Informasi.BerhasilExport p = new Informasi.BerhasilExport(this, true);
                p.setVisible(true);
            } catch (Exception e) {
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_exelBulanActionPerformed

    private void tgl_awalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_awalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_awalKeyReleased

    private void tgl_akhirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_akhirKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_akhirKeyReleased

    private void tbl_bulananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bulananMouseClicked
        this.panel_chart.setVisible(true);
        this.namaBulan = this.tbl_bulanan.getValueAt(this.tbl_bulanan.getSelectedRow(), 2).toString();

        int bulanDipilih = this.getNilaiBulan(this.namaBulan);
        int tahunDipilih = Tahun.getYear();
        System.out.println(bulanDipilih);
        System.out.println(tahunDipilih);
        new Main.BarChartPendapatan().showBarChart(panel_chart, "PENDAPATAN PERBULAN",
                bulanDipilih, tahunDipilih);
//        try {
//            String sql = "SELECT DATE_FORMAT(NOW(), '%c') AS Bulan, DATE_FORMAT(NOW(), '%Y') AS Tahun;";
//            Connection conn = (Connection) Koneksi.configDB();
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                int bulanDipilih = this.getNilaiBulan(this.namaBulan);
//                int tahunDipilih = Tahun.getYear();
//                System.out.println(bulanDipilih);
//                System.out.println(tahunDipilih);
//                new Main.ChartManager().showBarChart(panel_chart, "PENDAPATAN BULAN INI",
//                        bulanDipilih, tahunDipilih);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(rootPane, "SALAH " + e);
//        }
    }//GEN-LAST:event_tbl_bulananMouseClicked

    private void tbl_hariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hariMouseClicked
        String kode = this.tbl_hari.getValueAt(this.tbl_hari.getSelectedRow(), 2).toString();
        this.ambilkodetransaksijual.setText(kode);
        new PopUp.DetailTransaksiJual(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_tbl_hariMouseClicked

    private void tbl_mingguanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_mingguanMouseClicked
        String kode = this.tbl_mingguan.getValueAt(this.tbl_mingguan.getSelectedRow(), 2).toString();
        this.ambilkodetransaksijual.setText(kode);
        new PopUp.DetailTransaksiJual(this, rootPaneCheckingEnabled).setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_mingguanMouseClicked

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
            java.util.logging.Logger.getLogger(LaporanPendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaporanPendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaporanPendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaporanPendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaporanPendapatan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser Tahun;
    public static javax.swing.JTextField ambilkodetransaksijual;
    private javax.swing.JLabel backgroundBulan;
    private javax.swing.JLabel backgroundHari;
    private javax.swing.JLabel backgroundMinggu;
    private javax.swing.JLabel backgroundnya;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_bulanan;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_harian;
    private javax.swing.JButton btn_mingguan;
    private javax.swing.JButton exelBulan;
    private javax.swing.JButton exelHari;
    private javax.swing.JButton exelMinggu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbl_TotaltransaksiB;
    private javax.swing.JLabel lbl_TotaltransaksiH;
    private javax.swing.JLabel lbl_TotaltransaksiM;
    private javax.swing.JLabel lbl_pendapatanB;
    private javax.swing.JLabel lbl_pendapatanH;
    private javax.swing.JLabel lbl_pendapatanM;
    private javax.swing.JPanel panelBulanan;
    private javax.swing.JPanel panelHarian;
    private javax.swing.JPanel panelMingguan;
    private javax.swing.JPanel panel_chart;
    private javax.swing.JTable tbl_bulanan;
    private javax.swing.JTable tbl_hari;
    private javax.swing.JTable tbl_mingguan;
    private com.toedter.calendar.JDateChooser tgl_akhir;
    private com.toedter.calendar.JDateChooser tgl_awal;
    private com.toedter.calendar.JDateChooser tgl_hari;
    // End of variables declaration//GEN-END:variables
}
