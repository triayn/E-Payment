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
//import javafx.scene.chart.BarChart;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class LaporanPengeluaran extends javax.swing.JFrame {

    private String namaBulan = "???";

    public LaporanPengeluaran() {
        initComponents();      ImageIcon img = new ImageIcon("src/Image/LogoAplikasi.png");
            this.setIconImage(img.getImage());
        this.btn_harian.setEnabled(false);
        btn_mingguan.setEnabled(true);
        btn_bulanan.setEnabled(true);
        this.panelHarian.setVisible(true);
        this.panelMingguan.setVisible(false);
        this.panelBulanan.setVisible(false);
        this.exelHari.setVisible(true);
        HeaderColumnB();
        HeaderColumnH();
        HeaderColumnM();
    }

    //KEBUTUHAN PANEL HARIAN
    public void totalTransH() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(tgl_hari.getDate()));

        try {
            String sql = "SELECT COUNT(*) AS jumlah_baris FROM transaksi_beli WHERE DATE(tanggal) = '" + tgl + "'";
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
            String sql = "SELECT SUM(total_harga) as total FROM transaksi_beli WHERE DATE(tanggal) = '" + tgl + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("total") == null) {
                    this.lbl_pengeluaranH.setText(Main.Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_pengeluaranH.setText(Main.Rupiah.convertToRupiah(Integer.parseInt(rs.getString("total"))));
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
        tbl_hari.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        center.setHorizontalAlignment(JLabel.CENTER);
        tbl_hari.getColumnModel().getColumn(0).setCellRenderer(center);
        tbl_hari.getColumnModel().getColumn(2).setCellRenderer(center);
        tbl_hari.getColumnModel().getColumn(3).setCellRenderer(center);
//        tbl_hari.getColumnModel().getColumn(4).setCellRenderer(center);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) tbl_hari.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = tbl_hari.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn column;
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(0);
        column.setPreferredWidth(105);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(1);
        column.setPreferredWidth(185);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(2);
        column.setPreferredWidth(247);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(3);
        column.setPreferredWidth(247);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_hari.getColumnModel().getColumn(4);
        column.setPreferredWidth(314);
        tbl_hari.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
//        column = tbl_hari.getColumnModel().getColumn(5);
//        column.setPreferredWidth(227);
    }

    public void pencarianH() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = String.valueOf(fm.format(tgl_hari.getDate()));

        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tanggal");
        tbl.addColumn("Kode Transaksi ");
        tbl.addColumn("Kode Karyawan");
        tbl.addColumn("Total Pengeluaran");
        tbl_hari.setModel(tbl);
        try {
            String sql = "SELECT * FROM transaksi_beli WHERE DATE(tanggal) = '" + tgl + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("tanggal"),
                    res.getString("kode_tr_beli"),
                    res.getString("kode_karyawan"),
                    Main.Rupiah.convertToRupiah(Integer.parseInt(res.getString("total_harga")))
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
            String sql = "SELECT COUNT(*) AS jumlah_transaksi\n"
                    + "FROM transaksi_beli\n"
                    + "WHERE DATE(tanggal) BETWEEN '" + tgl1 + "' AND '" + tgl2 + "'";
            System.out.println("sql total Mingguan: " + sql);
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("jumlah_transaksi") == null) {
                    this.lbl_TotaltransaksiM.setText("0");
                } else {
                    this.lbl_TotaltransaksiM.setText(((rs.getString("jumlah_transaksi"))));
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
            String sql = "SELECT SUM(total_harga) as total FROM transaksi_beli WHERE DATE(tanggal) BETWEEN '" + tgl1 + "' AND '" + tgl2 + "'";
            System.out.println("sql total Mingguan: " + sql);
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("total") == null) {
                    this.lbl_pengeluaranM.setText(Main.Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_pengeluaranM.setText(Main.Rupiah.convertToRupiah(Integer.parseInt(rs.getString("total"))));
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
        tbl_mingguan.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        center.setHorizontalAlignment(JLabel.CENTER);
        tbl_mingguan.getColumnModel().getColumn(0).setCellRenderer(center);
        tbl_mingguan.getColumnModel().getColumn(2).setCellRenderer(center);
        tbl_mingguan.getColumnModel().getColumn(3).setCellRenderer(center);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        ((DefaultTableCellRenderer) tbl_mingguan.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader thead = tbl_mingguan.getTableHeader();
        thead.setBackground(new Color(205, 31, 31));
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        TableColumn column;
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(0);
        column.setPreferredWidth(105);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(1);
        column.setPreferredWidth(185);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(2);
        column.setPreferredWidth(247);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(3);
        column.setPreferredWidth(247);
        tbl_mingguan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_mingguan.getColumnModel().getColumn(4);
        column.setPreferredWidth(314);
    }

    public void pencarianM() {
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl1 = String.valueOf(fm.format(tgl_awal.getDate()));
        String tgl2 = String.valueOf(fm.format(tgl_akhir.getDate()));

        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tanggal");
        tbl.addColumn("Kode Transaksi Beli");
        tbl.addColumn("Kode Karyawan");
        tbl.addColumn("Total Pengeluaran");
        tbl_mingguan.setModel(tbl);
        try {
            String sql = "SELECT * FROM transaksi_beli WHERE DATE(tanggal)"
                    + " BETWEEN '" + tgl1 + "' AND '" + tgl2 + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("tanggal"),
                    res.getString("kode_tr_beli"),
                    res.getString("kode_karyawan"),
                    Main.Rupiah.convertToRupiah(Integer.parseInt(res.getString("total_harga")))
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
                    + "FROM transaksi_beli\n"
                    + "WHERE year(tanggal) ='" + Tahun.getYear() + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("jumlah_transaksi") == null) {
                    this.lbl_TotaltransaksiB.setText(Main.Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_TotaltransaksiB.setText(Main.Rupiah.convertToRupiah(
                            Integer.parseInt(rs.getString("jumlah_transaksi"))));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah" + e);
        }
    }

    public void totalB() {
        try {
            String sql = "SELECT SUM(total_harga) AS total FROM transaksi_beli WHERE year(tanggal) ='" + Tahun.getYear() + "'";
            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("total") == null) {
                    this.lbl_pengeluaranB.setText(Main.Rupiah.convertToRupiah(0));
                } else {
                    this.lbl_pengeluaranB.setText(Main.Rupiah.convertToRupiah(Integer.parseInt(rs.getString("total"))));
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
        column.setPreferredWidth(55);
        tbl_bulanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_bulanan.getColumnModel().getColumn(1);
        column.setPreferredWidth(133);
        tbl_bulanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_bulanan.getColumnModel().getColumn(2);
        column.setPreferredWidth(150);
        tbl_bulanan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tbl_bulanan.getColumnModel().getColumn(3);
        column.setPreferredWidth(260);
    }

    public void pencarianB() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tahun");
        tbl.addColumn("Bulan");
        tbl.addColumn("Total Perbulan");
        tbl_bulanan.setModel(tbl);
        try {
            String sql = "SELECT SUM(total_harga) AS totalPerbulan ,  MONTH(tanggal) AS Bulan_ke , YEAR(tanggal) AS Tahun FROM transaksi_beli "
                    + "WHERE MONTH(tanggal) >=1 AND YEAR(tanggal) = '" + Tahun.getYear() + "' GROUP BY MONTH(tanggal)";

            Connection conn = (Connection) Main.Koneksi.configDB();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            int no = 1;
            while (res.next()) {
                tbl.addRow(new Object[]{
                    no++,
                    res.getString("Tahun"),
                    this.getBulan(res.getInt("Bulan_ke")),
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

// FUNGSI UNTUK MEREFRESH HARIAN
    public void RefreshH() {
        this.lbl_TotaltransaksiB.setText("");
        this.lbl_TotaltransaksiM.setText("");
        this.lbl_TotaltransaksiH.setText("");
        this.lbl_pengeluaranB.setText("");
        this.lbl_pengeluaranM.setText("");
        this.lbl_pengeluaranH.setText("");
        this.tgl_akhir.setDate(null);
        this.tgl_awal.setDate(null);
        this.tgl_hari.setDate(null);

        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tanggal");
        tbl.addColumn("Kode Transaksi ");
        tbl.addColumn("Kode Karyawan");
        tbl.addColumn("Total Pengeluaran");
        tbl_hari.setModel(tbl);
        try {

            tbl.addRow(new Object[]{});

            tbl_hari.setModel(tbl);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e);
        }

    }

    // FUNGSI UNTUK MEREFRESH MINGGUAN
    public void RefreshM() {
        this.lbl_TotaltransaksiB.setText("");
        this.lbl_TotaltransaksiM.setText("");
        this.lbl_TotaltransaksiH.setText("");
        this.lbl_pengeluaranB.setText("");
        this.lbl_pengeluaranM.setText("");
        this.lbl_pengeluaranH.setText("");
        this.tgl_akhir.setDate(null);
        this.tgl_awal.setDate(null);
        this.tgl_hari.setDate(null);

        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tanggal");
        tbl.addColumn("Kode Transaksi Beli");
        tbl.addColumn("Kode Karyawan");
        tbl.addColumn("Total Pengeluaran");
        tbl_mingguan.setModel(tbl);
        try {

            tbl.addRow(new Object[]{});
            tbl_mingguan.setModel(tbl);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e.getMessage());
        }
    }

    // FUNGSI UNTUK MEREFRESH bulanan
    public void RefreshB() {
        this.lbl_TotaltransaksiB.setText("");
        this.lbl_TotaltransaksiM.setText("");
        this.lbl_TotaltransaksiH.setText("");
        this.lbl_pengeluaranB.setText("");
        this.lbl_pengeluaranM.setText("");
        this.lbl_pengeluaranH.setText("");
        this.tgl_akhir.setDate(null);
        this.tgl_awal.setDate(null);
        this.tgl_hari.setDate(null);
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No");
        tbl.addColumn("Tahun");
        tbl.addColumn("Bulan");
        tbl.addColumn("Total Perbulan");
        tbl_bulanan.setModel(tbl);
        try {

            tbl.addRow(new Object[]{});
            tbl_bulanan.setModel(tbl);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "salah " + e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ambilkodetransaksibeli = new javax.swing.JTextField();
        panelHarian = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_hari = new javax.swing.JTable();
        lbl_pengeluaranH = new javax.swing.JLabel();
        lbl_TotaltransaksiH = new javax.swing.JLabel();
        tgl_hari = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        backgroundHari = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        panelBulanan = new javax.swing.JPanel();
        panel_chart = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_bulanan = new javax.swing.JTable();
        lbl_pengeluaranB = new javax.swing.JLabel();
        lbl_TotaltransaksiB = new javax.swing.JLabel();
        Tahun = new com.toedter.calendar.JYearChooser();
        jLabel3 = new javax.swing.JLabel();
        backgroundBulan = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        exelBulan = new javax.swing.JButton();
        panelMingguan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_mingguan = new javax.swing.JTable();
        lbl_pengeluaranM = new javax.swing.JLabel();
        lbl_TotaltransaksiM = new javax.swing.JLabel();
        tgl_akhir = new com.toedter.calendar.JDateChooser();
        tgl_awal = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        backgroundMinggu = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        backgroundnya = new javax.swing.JLabel();
        btn_mingguan = new javax.swing.JButton();
        btn_bulanan = new javax.swing.JButton();
        btn_harian = new javax.swing.JButton();
        exelHari = new javax.swing.JButton();
        exelMinggu = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();

        ambilkodetransaksibeli.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHarian.setBackground(new java.awt.Color(255, 255, 255));
        panelHarian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_hari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbl_hari.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Kode Transaksi", "Kode Karyawan", "Total Pengeluaran"
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

        lbl_pengeluaranH.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelHarian.add(lbl_pengeluaranH, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 180, 30));

        lbl_TotaltransaksiH.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelHarian.add(lbl_TotaltransaksiH, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 120, 30));

        tgl_hari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tgl_hari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tgl_hariKeyReleased(evt);
            }
        });
        panelHarian.add(tgl_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Cari.png"))); // NOI18N
        panelHarian.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        backgroundHari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/laporanPengeluaranH.png"))); // NOI18N
        backgroundHari.setText("tinggi");
        panelHarian.add(backgroundHari, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -190, -1, -1));

        jButton3.setOpaque(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelHarian.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 40, 40));

        getContentPane().add(panelHarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 1170, 340));

        panelBulanan.setBackground(new java.awt.Color(255, 255, 255));
        panelBulanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_chart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panel_chart.setLayout(new java.awt.BorderLayout());
        panelBulanan.add(panel_chart, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 450, 280));

        tbl_bulanan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbl_bulanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Tahun", "Bulan", "Total Pengeluaran"
            }
        ));
        tbl_bulanan.setGridColor(new java.awt.Color(205, 31, 31));
        tbl_bulanan.setSelectionBackground(new java.awt.Color(205, 31, 31));
        tbl_bulanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bulananMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_bulanan);

        panelBulanan.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 600, 210));

        lbl_pengeluaranB.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelBulanan.add(lbl_pengeluaranB, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 190, 30));

        lbl_TotaltransaksiB.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelBulanan.add(lbl_TotaltransaksiB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 120, 30));
        panelBulanan.add(Tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Cari.png"))); // NOI18N
        panelBulanan.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        backgroundBulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/laporanPengeluaranB.png"))); // NOI18N
        panelBulanan.add(backgroundBulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -190, -1, -1));

        jButton2.setOpaque(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelBulanan.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 50, 50));

        exelBulan.setText("jButton4");
        exelBulan.setOpaque(false);
        exelBulan.setContentAreaFilled(false);
        exelBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exelBulanActionPerformed(evt);
            }
        });
        panelBulanan.add(exelBulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, 150, 50));

        getContentPane().add(panelBulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 1170, 400));

        panelMingguan.setBackground(new java.awt.Color(255, 255, 255));
        panelMingguan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_mingguan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbl_mingguan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "Kode Transaksi", "Kode Karyawan", "Total Pengeluaran"
            }
        ));
        tbl_mingguan.setGridColor(new java.awt.Color(205, 31, 31));
        tbl_mingguan.setSelectionBackground(new java.awt.Color(205, 31, 31));
        tbl_mingguan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_mingguanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_mingguan);

        panelMingguan.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 1100, 230));

        lbl_pengeluaranM.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelMingguan.add(lbl_pengeluaranM, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 190, 30));

        lbl_TotaltransaksiM.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        panelMingguan.add(lbl_TotaltransaksiM, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 120, 30));

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

        backgroundMinggu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/laporanPengeluaranM.png"))); // NOI18N
        panelMingguan.add(backgroundMinggu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -190, -1, -1));

        jButton1.setOpaque(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelMingguan.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 50, 40));

        getContentPane().add(panelMingguan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 1170, 340));

        backgroundnya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/laporanPengeluaranH.png"))); // NOI18N
        getContentPane().add(backgroundnya, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

        btn_harian.setText("\\");
            btn_harian.setOpaque(false);
            btn_harian.setContentAreaFilled(false);
            btn_harian.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn_harianActionPerformed(evt);
                }
            });
            getContentPane().add(btn_harian, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 70, 30));

            exelHari.setText("jButton4");
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
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(
                "Image/laporanPengeluaranH.png"));
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
        RefreshH();
        RefreshM();
        RefreshB();
        HeaderColumnH();
    }//GEN-LAST:event_btn_harianActionPerformed
    private void btn_mingguanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mingguanActionPerformed
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Image/laporanPengeluaranM.png"));
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
        RefreshH();
        RefreshM();
        RefreshB();

        HeaderColumnM();
    }//GEN-LAST:event_btn_mingguanActionPerformed
    private void btn_bulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bulananActionPerformed
        panel_chart.setVisible(false);
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Image/laporanPengeluaranB.png"));
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
        RefreshH();
        RefreshM();
        RefreshB();

        HeaderColumnB();
    }//GEN-LAST:event_btn_bulananActionPerformed
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (tgl_awal.getDate() == null || tgl_akhir.getDate() == null) {
            Informasi.PilihTanggal p = new Informasi.PilihTanggal(this, true);
            p.setVisible(true);
        } else {
            totalM();
            totalTransM();
            pencarianM();
            HeaderColumnM();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (Tahun == null) {
            Informasi.PilihTanggal p = new Informasi.PilihTanggal(this, true);
            p.setVisible(true);
        } else {
            totalB();
            totalTransB();
            pencarianB();

        }
    }//GEN-LAST:event_jButton2ActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (tgl_hari.getDate() == null) {
            Informasi.PilihTanggal p = new Informasi.PilihTanggal(this, true);
            p.setVisible(true);
        } else {
            totalH();
            totalTransH();
            pencarianH();
            HeaderColumnH();
        }
    }//GEN-LAST:event_jButton3ActionPerformed
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

    private void tgl_hariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_hariKeyReleased

    }//GEN-LAST:event_tgl_hariKeyReleased

    private void tgl_akhirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_akhirKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_akhirKeyReleased

    private void tgl_awalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_awalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_awalKeyReleased

    private void tbl_hariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hariMouseClicked
        String kode = this.tbl_hari.getValueAt(this.tbl_hari.getSelectedRow(), 2).toString();
        this.ambilkodetransaksibeli.setText(kode);
        new PopUp.DetailTransaksiBeli(this, rootPaneCheckingEnabled).setVisible(true);

//KODE UNTUK MENAMPLKAN DETAIL TRANSAKSI PADA TABEL HARIAN

    }//GEN-LAST:event_tbl_hariMouseClicked

    private void tbl_bulananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bulananMouseClicked
        panel_chart.setVisible(true);

        this.namaBulan = this.tbl_bulanan.getValueAt(this.tbl_bulanan.getSelectedRow(), 2).toString();

        int bulanDipilih = this.getNilaiBulan(this.namaBulan);
        int tahunDipilih = Tahun.getYear();
        System.out.println(bulanDipilih);
        System.out.println(tahunDipilih);
        new Main.BarChartPendapatan().showBarChart(panel_chart, "PENGELUARAN PERBULAN", bulanDipilih, tahunDipilih);
    }//GEN-LAST:event_tbl_bulananMouseClicked

    private void tbl_mingguanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_mingguanMouseClicked
        String kode = this.tbl_mingguan.getValueAt(this.tbl_mingguan.getSelectedRow(), 2).toString();
        this.ambilkodetransaksibeli.setText(kode);
        new PopUp.DetailTransaksiBeli(this, rootPaneCheckingEnabled).setVisible(true);

//KODE UNTUK MENAMPLKAN DETAIL TRANSAKSI PADA TABEL MINGGUAN

    }//GEN-LAST:event_tbl_mingguanMouseClicked

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LaporanPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaporanPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaporanPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaporanPengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaporanPengeluaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser Tahun;
    public static javax.swing.JTextField ambilkodetransaksibeli;
    private javax.swing.JLabel backgroundBulan;
    private javax.swing.JLabel backgroundHari;
    private javax.swing.JLabel backgroundMinggu;
    private javax.swing.JLabel backgroundnya;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_bulanan;
    private javax.swing.JButton btn_harian;
    private javax.swing.JButton btn_mingguan;
    private javax.swing.JButton exelBulan;
    private javax.swing.JButton exelHari;
    private javax.swing.JButton exelMinggu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_TotaltransaksiB;
    private javax.swing.JLabel lbl_TotaltransaksiH;
    private javax.swing.JLabel lbl_TotaltransaksiM;
    private javax.swing.JLabel lbl_pengeluaranB;
    private javax.swing.JLabel lbl_pengeluaranH;
    private javax.swing.JLabel lbl_pengeluaranM;
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
