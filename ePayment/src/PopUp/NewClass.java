
package PopUp;

import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class NewClass {
      public void cetakStrukPenjualan(Connection conn, String idTransaksi) {
        try {
            // menyiapkan kode transaksi
            HashMap parameter = new HashMap();
            parameter.put("kode_tr_jual", idTransaksi);

            // meyiapkan jasper report
            InputStream file = getClass().getResourceAsStream("/Cetak/Notaaaaa.jrxml");
            JasperDesign desain = JRXmlLoader.load(file);
            JasperReport report = JasperCompileManager.compileReport(desain);
            JasperPrint print = JasperFillManager.fillReport(report, parameter, conn);
            
            // membuka jasper report
            JasperViewer jview = new JasperViewer(print, false);
            jview.setTitle("Cetak Struk " + idTransaksi);
            jview.setVisible(true);
            jview.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jview.setLocationRelativeTo(null);
            jview.setFitPageZoomRatio();

            // solved bug jasper report tiba-tiba minimaze
            jview.addWindowListener(new java.awt.event.WindowAdapter() {

                // menutup jasper report saat user menekan tombol close
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("JASPER CLOSING");
                    jview.dispose();
                }

                // menutup jasper report saat user menekan tombol close
                @Override
                public void windowClosed(WindowEvent e) {
                    System.out.println("JASPER CLOSED");
                    jview.dispose();
                }

                // memaksa jasper report untuk tetap aktif (tidak minimaze)
                @Override
                public void windowDeactivated(WindowEvent e) {
                    System.out.println("JASPER ACTIVATED");
                    jview.setVisible(true);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void main(String[] args) throws SQLException {
        NewClass n = new NewClass();
        java.sql.Connection con = (Connection) Main.Koneksi.configDB();
        n.cetakStrukPenjualan(con, "TRJ007");
    }
}
