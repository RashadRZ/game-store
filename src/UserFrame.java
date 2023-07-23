
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.NORTHWEST;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import swing.PictureBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lenovo
 */
public class UserFrame extends javax.swing.JFrame {

    /**
     * Creates new form UserFrame
     */
    public UserFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonMyGames = new ButtonRound();
        buttonStore = new ButtonRound();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Store = new javax.swing.JPanel();
        labelStudios = new javax.swing.JLabel();
        scrollPanel = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(22, 25, 32));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        buttonMyGames.setBackground(new java.awt.Color(0, 0, 0));
        buttonMyGames.setForeground(new java.awt.Color(158, 158, 158));
        buttonMyGames.setText("MY GAMES");
        buttonMyGames.setBorderColor(new java.awt.Color(0, 0, 0));
        buttonMyGames.setColor(new java.awt.Color(0, 0, 0));
        buttonMyGames.setColorClick(new java.awt.Color(0, 0, 0));
        buttonMyGames.setColorOver(new java.awt.Color(30, 30, 30));
        buttonMyGames.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buttonMyGames.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonMyGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMyGamesMouseClicked(evt);
            }
        });

        buttonStore.setBackground(new java.awt.Color(0, 0, 0));
        buttonStore.setForeground(new java.awt.Color(158, 158, 158));
        buttonStore.setText("STORE");
        buttonStore.setBorderColor(new java.awt.Color(0, 0, 0));
        buttonStore.setColor(new java.awt.Color(0, 0, 0));
        buttonStore.setColorClick(new java.awt.Color(0, 0, 0));
        buttonStore.setColorOver(new java.awt.Color(30, 30, 30));
        buttonStore.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buttonStore.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonStore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonStoreMouseClicked(evt);
            }
        });
        buttonStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(buttonMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonStore, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 764, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonStore, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        Store.setBackground(new java.awt.Color(22, 25, 32));
        Store.setPreferredSize(new java.awt.Dimension(1280, 642));

        labelStudios.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelStudios.setForeground(new java.awt.Color(255, 255, 255));
        labelStudios.setText("GAMES");

        scrollPanel.setBorder(null);
        scrollPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setPreferredSize(new java.awt.Dimension(100, 900));

        mainPanel.setLayout(new java.awt.BorderLayout());
        scrollPanel.setViewportView(mainPanel);

        javax.swing.GroupLayout StoreLayout = new javax.swing.GroupLayout(Store);
        Store.setLayout(StoreLayout);
        StoreLayout.setHorizontalGroup(
            StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StoreLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStudios)
                    .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117))
        );
        StoreLayout.setVerticalGroup(
            StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StoreLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelStudios)
                .addGap(61, 61, 61)
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", Store);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 50, 1290, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonMyGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMyGamesMouseClicked

    }//GEN-LAST:event_buttonMyGamesMouseClicked

    private void buttonStoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonStoreMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonStoreMouseClicked

    private void buttonStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonStoreActionPerformed

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
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Store;
    private ButtonRound buttonMyGames;
    private ButtonRound buttonStore;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelStudios;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables
}
