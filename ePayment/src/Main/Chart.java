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
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author Tria
 */
public class Chart {
    public static final int PENDAPATAN = 0, PENGELUARAN = 1;
    
    private final Font F_MENU = new Font("Ebrima", 1, 20);
    
    public void showBarChart(JPanel panel, int type, String title, int bulan, int tahun){
        String valTitle = "";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // menambahkan value ke dalam chart
        switch(type){
            // mendapatkan data pendapatan
            case PENDAPATAN :
                valTitle = "Jumlah Pendapatan";
                // mendapatkan data mingguan
                Object[] mingguJual = new Waktu().getMinggu(bulan, tahun);
                String minggu1Jual, minggu2Jual;
                int weekJual = 1;
                // menambahkan data pendapatan ke dataset
                for(Object m : mingguJual){
                    minggu1Jual = m.toString().substring(0, m.toString().indexOf("="));
                    minggu2Jual = m.toString().substring(m.toString().indexOf("=")+1);
                    dataset.setValue(this.getBarDataPenjualan(minggu1Jual, minggu2Jual), "Amount", "Minggu " + weekJual);
                    weekJual++;
                }
                break;
            // mendapatkan data pengeluaran
            case PENGELUARAN :
                valTitle = "Jumlah Pengeluaran";
                // mendapatkan data mingguan
                Object[] mingguBeli = new Waktu().getMinggu(bulan, tahun);
                String minggu1Beli, minggu2Beli;
                int weekBeli = 1;
                // menambahkan data pendapatan ke dataset
                for(Object m : mingguBeli){
                    minggu1Beli = m.toString().substring(0, m.toString().indexOf("="));
                    minggu2Beli = m.toString().substring(m.toString().indexOf("=")+1);
                    dataset.setValue(this.getBarDataPembelian(minggu1Beli, minggu2Beli), "Amount", "Minggu " + weekBeli);
                    weekBeli++;
                }      
                break;
        }
        
        // membuat bar 
        JFreeChart barchart = ChartFactory.createBarChart(title,"Minggu",valTitle, 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        barchart.setTitle(new TextTitle(title, this.F_MENU));
        
        // mengatur warna pada bar dan background chart
        CategoryPlot categoryPlot = barchart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(237,40,40);
        renderer.setSeriesPaint(0, clr3);
        
        // menampilkan chart ke panel
        ChartPanel barpChartPanel = new ChartPanel(barchart);
        panel.removeAll();
        panel.add(barpChartPanel, BorderLayout.CENTER);
        panel.validate();
    }
    
    private int getBarDataPenjualan(String minggu1, String minggu2){
        try{
            // mendapatkan query untuk mendapatkan total pendapatan mingguan
            String sql = "SELECT SUM(total_harga) AS total "
                       + "FROM transaksi_jual "
                       + "WHERE DATE(tanggal) BETWEEN '"+minggu1+"' AND '"+minggu2+"'";
            System.out.println(sql);
            Connection conn = (Connection) Koneksi.configDB();
            PreparedStatement prp = conn.prepareStatement(sql);
            ResultSet res = prp.executeQuery(sql);
//            super.res = super.stat.executeQuery(sql);
            
            // mendapatkan data total pendapatan mingguan
            if(res.next()){
                int total = res.getInt("total");
                return total;
            }
        }catch(SQLException ex){
//            Message.showException(null, ex);
        }
        return 0;
    }
   
    private int getBarDataPembelian(String minggu1, String minggu2){
        try{
            // mendapatkan query untuk mendapatkan total pendapatan mingguan
            String sql = "SELECT SUM(total_harga) AS total "
                       + "FROM transaksi_beli "
                       + "WHERE DATE(tanggal) BETWEEN '"+minggu1+"' AND '"+minggu2+"'";
            System.out.println(sql);
            Connection conn = (Connection) Koneksi.configDB();
            PreparedStatement prp = conn.prepareStatement(sql);
            ResultSet res = prp.executeQuery(sql);
            
            // mendapatkan data total pendapatan mingguan
            if(res.next()){
                int total = res.getInt("total");
                return total;
            }
        }catch(SQLException ex){
//            Message.showException(null, ex);
        }
        return 0;
    }
    
    @Deprecated
    public void showHistogram(JPanel panel, int type, String title){
        
         double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
                            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
                            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
                            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
                            77, 44, 58, 91, 10, 67, 57, 19, 88, 84                                
                          };
 
 
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);
        
         JFreeChart chart = ChartFactory.createHistogram("JFreeChart Histogram","Data", "Frequency", dataset,PlotOrientation.VERTICAL, false,true,false);
            XYPlot plot= chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);

        
        
        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        panel.removeAll();
        panel.add(barpChartPanel2, BorderLayout.CENTER);
        panel.validate();
    }
}
