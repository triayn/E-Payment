/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author Tria
 */
public class BarChartPendapatan {
    public void showBarChart(JPanel panel, String title, int bulan, int tahun){
        String valTitle = "";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // menambahkan value ke dalam chart
        // mendapatkan data pendapatan
        valTitle = "Jumlah Pendapatan";
        // mendapatkan data mingguan
        Object[] mingguJual = getMinggu(bulan, tahun);
        String minggu1Jual, minggu2Jual;
        int weekJual = 1;
        // menambahkan data pendapatan ke dataset
        for (Object m : mingguJual) {
            minggu1Jual = m.toString().substring(0, m.toString().indexOf("="));
            minggu2Jual = m.toString().substring(m.toString().indexOf("=") + 1);
            dataset.setValue(this.getBarDataPenjualan(minggu1Jual, minggu2Jual), "Amount", " " + weekJual);
            weekJual++;
        }

        // membuat bar 
        JFreeChart barchart = ChartFactory.createBarChart(title,"Minggu",valTitle, 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        barchart.setTitle(new TextTitle(title, new Font("Tahoma", 1, 20)));
        
        // mengatur warna pada bar dan background chart
        CategoryPlot categoryPlot = barchart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(205,31,31);
        renderer.setSeriesPaint(0, clr3);
        
        // menampilkan chart ke panel
        ChartPanel barpChartPanel = new ChartPanel(barchart);
        panel.removeAll();
        panel.add(barpChartPanel, BorderLayout.CENTER);
        panel.validate();
    }
   
    private int getBarDataPenjualan(String minggu1, String minggu2){
        try{
            // query mu
            String sql = "SELECT SUM(bayar) AS total "
                       + "FROM transaksi_jual "
                       + "WHERE DATE(tanggal) BETWEEN '"+minggu1+"' AND '"+minggu2+"'";
            System.out.println(sql);
            
            // eksekusi query
            Connection conn = (Connection) Koneksi.configDB();
            PreparedStatement prp = conn.prepareStatement(sql);
            ResultSet res = prp.executeQuery(sql);
            
            // ambil data
            if(res.next()){
                int total = res.getInt("total");
                return total;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public Object[] getMinggu(int bulan, int tahun){
         // menampung data minggu
         SortedMap<String, String> data = new TreeMap<>();
         
         // inisialisasi waktu awal
         bulan--;
         int hari = 1, week = 1;
         String minggu, sabtu;
         Date d = new Date(tahun-1900, bulan, hari);
         Calendar c = Calendar.getInstance();
         c.setTime(d);
         
         // mendapatkan sisa hari pada awal bulan
         int tgl1 = c.get(Calendar.DAY_OF_WEEK),
             sisaHari = 7, totalHari = this.getTotalHari(bulan+1);
         
         // jika bulan tidak diawali dengan hari minggu
         if(tgl1 > 1){
             // mengurangi sisahari
             sisaHari = sisaHari - (7 - tgl1+1);
         }
         else{
             sisaHari = 0;
         }
         
         // mendapatkan hari minggu pertama
         hari = hari - sisaHari;
         d = new Date(tahun-1900, bulan, hari);
         c.setTime(d);
         minggu = String.format("%d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
         
         // mendapatkan hari sabtu pertama
         hari = 7 - sisaHari;
         d = new Date(tahun-1900, bulan, hari);
         c.setTime(d);
         sabtu = String.format("%d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
         
         // menyimpan data minggu
         data.put(minggu, sabtu);
         System.out.println(String.format("Minggu ke-%d : %s ---> %s", week, minggu, sabtu));
         
         // mendapatkan data minggu ke 2 sampai minggu terakhir
         while(hari <= totalHari){
//             System.out.println("HARI KE -> " + hari);
             // inisialisasi ulang waktu
             d = new Date(tahun-1900, bulan, hari);
             c.setTime(d);
             
             // mendapatkan data minggu pada bulan
             int fWeek = c.get(Calendar.DAY_OF_WEEK);
             if(fWeek == 1){
                 // mendapatkan awal minggu
                 minggu = String.format("%d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
                 hari+=6;
                 // mendapatkan akhir minggu
                 d = new Date(tahun-1900, bulan, hari);
                 c.setTime(d);
                 sabtu = String.format("%d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
                 
                 // menyimpan data minggu
                 week++;
                 data.put(minggu, sabtu);
                 System.out.println(String.format("Minggu ke-%d : %s ---> %s", week, minggu, sabtu));
             }
             hari++;
         }
         
         System.out.println("\nAKHIR METHOD\n\n");
         return data.entrySet().toArray();
     }
    
    public int getTotalHari(int bulan){
        switch(bulan){
            case 1: return 31;
            case 2: return 28;
            case 3: return 31;
            case 4: return 30;
            case 5: return 31;
            case 6: return 30;
            case 7: return 31;
            case 8: return 31;
            case 9: return 30;
            case 10: return 31;
            case 11: return 30;
            case 12: return 31;
            default: return -1;
        }
    }

}
