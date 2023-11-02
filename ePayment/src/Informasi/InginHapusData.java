/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Informasi;

/**
 *
 * @author Tria
 */
public class InginHapusData extends javax.swing.JDialog {

    /**
     * Creates new form InginHapusData
     */
    public InginHapusData(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }
    private boolean isClose = false;

    public boolean isClose() {
        return this.isClose;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_tidak = new javax.swing.JButton();
        btn_ya = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_tidak.setOpaque(false);
        btn_tidak.setContentAreaFilled(false);
        btn_tidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tidakActionPerformed(evt);
            }
        });
        btn_tidak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btn_tidakKeyTyped(evt);
            }
        });
        getContentPane().add(btn_tidak, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 80, 30));

        btn_ya.setOpaque(false);
        btn_ya.setContentAreaFilled(false);
        btn_ya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_yaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ya, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 80, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/tanyaHapus.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_yaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_yaActionPerformed

        this.isClose = true;
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_yaActionPerformed

    private void btn_tidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tidakActionPerformed
        this.isClose = false;
        this.dispose();       // TODO add your handling code here:
    }//GEN-LAST:event_btn_tidakActionPerformed

    private void btn_tidakKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_tidakKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tidakKeyTyped

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InginHapusData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InginHapusData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InginHapusData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InginHapusData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InginHapusData dialog = new InginHapusData(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_tidak;
    private javax.swing.JButton btn_ya;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
