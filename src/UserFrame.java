/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UserFrame extends javax.swing.JFrame {

    Connect dbsetting;
    String driver, database, user, pass;
    Object table;

    /**
     * Creates new form UserFrame
     */
    public UserFrame() {
        initComponents();

        dbsetting = new Connect();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");

        loadMyGames();
    }

    public void loadMyGames() {
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel myGamesPanel = new JPanel();
        scrollPane.setViewportView(myGamesPanel);
        myGamesPanel.setLayout(new BorderLayout(0, 0));

        JPanel columnPanel = new JPanel();
        myGamesPanel.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);
            Statement stt = conn.createStatement();
            String SQL = "SELECT gameID, game_title, studio_name, publisher_name, release_date, genre, price FROM games LEFT JOIN studios ON games.studioID=studios.studioID LEFT JOIN publishers ON games.publisherID=publishers.publisherID";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                JPanel rowPanel = new JPanel();
                rowPanel.setPreferredSize(new Dimension(400, 200));
                rowPanel.setLayout(null);
                rowPanel.setBackground(new Color(61, 76, 93));

                ImageIcon icon = new ImageIcon("C:\\Users\\lenovo\\OneDrive\\Documents\\NetBeansProjects\\GameStore\\src\\images\\1.jpg");
                PictureBox image = new PictureBox();
                image.setBounds(0, 0, 400, 200);
                image.setImage(icon);
                rowPanel.add(image);

                JLabel title = new JLabel(res.getString(2));
                title.setBounds(420, 10, 500, 30);
                title.setFont(new Font("Arial", Font.BOLD, 24));
                title.setForeground(Color.WHITE);
                rowPanel.add(title);

                JLabel studio = new JLabel("Studio: " + res.getString(3));
                studio.setBounds(420, 40, 500, 30);
                studio.setFont(new Font("Arial", Font.PLAIN, 18));
                studio.setForeground(Color.GRAY);
                rowPanel.add(studio);

                JLabel publisher = new JLabel("Publisher: " + res.getString(4));
                publisher.setBounds(420, 60, 500, 30);
                publisher.setFont(new Font("Arial", Font.PLAIN, 18));
                publisher.setForeground(Color.GRAY);
                rowPanel.add(publisher);

                JLabel date = new JLabel("Release Date: " + res.getString(5));
                date.setBounds(420, 100, 500, 30);
                date.setFont(new Font("Arial", Font.PLAIN, 18));
                date.setForeground(Color.GRAY);
                rowPanel.add(date);

                ButtonRound genre = new ButtonRound();
                genre.setText(res.getString(6));
                genre.setBounds(420, 160, 100, 30);
                genre.setFont(new Font("Arial", Font.PLAIN, 18));
                genre.setForeground(Color.GRAY);
                genre.setColor(new Color(61, 76, 93));
                genre.setBorderColor(Color.GRAY);
                rowPanel.add(genre);

                TextFieldRound price = new TextFieldRound();
                price.setText("$" + res.getString(7));
                price.setBounds(850, 100, 80, 30);
                price.setFont(new Font("Arial", Font.PLAIN, 18));
                price.setForeground(Color.WHITE);
                price.setBackground(Color.BLACK);
                price.setHorizontalAlignment(SwingConstants.RIGHT);
                price.setRoundTopLeft(0);
                price.setRoundTopRight(0);
                price.setRoundBottomLeft(0);
                price.setRoundBottomRight(0);
                rowPanel.add(price);

                ButtonRound buy = new ButtonRound();
                buy.setText("BUY");
                buy.setBounds(930, 100, 100, 30);
                buy.setFont(new Font("Arial", Font.PLAIN, 18));
                buy.setForeground(Color.WHITE);
                buy.setBorderColor(new Color(111, 166, 32));
                buy.setRadius(0);
                rowPanel.add(buy);

                columnPanel.add(rowPanel);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu = new javax.swing.JPanel();
        buttonMyGames = new ButtonRound();
        buttonStore = new ButtonRound();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Store = new javax.swing.JPanel();
        labelStudios = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(22, 25, 32));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu.setBackground(new java.awt.Color(0, 0, 0));

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

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Username");

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addComponent(buttonMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonStore, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 582, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(100, 100, 100))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonStore, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        getContentPane().add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        Store.setBackground(new java.awt.Color(22, 25, 32));
        Store.setPreferredSize(new java.awt.Dimension(1280, 642));

        labelStudios.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelStudios.setForeground(new java.awt.Color(255, 255, 255));
        labelStudios.setText("GAMES");

        scrollPane.setBackground(new java.awt.Color(22, 25, 32));
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new java.awt.Dimension(100, 900));

        javax.swing.GroupLayout StoreLayout = new javax.swing.GroupLayout(Store);
        Store.setLayout(StoreLayout);
        StoreLayout.setHorizontalGroup(
            StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StoreLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStudios)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100))
        );
        StoreLayout.setVerticalGroup(
            StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StoreLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelStudios)
                .addGap(95, 95, 95)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
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
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Store;
    private ButtonRound buttonMyGames;
    private ButtonRound buttonStore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelStudios;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
