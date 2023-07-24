/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class UserFrame extends javax.swing.JFrame {

    Connect dbsetting;
    String driver, database, user, pass;
    Object table;

    /**
     * Creates new form UserFrame
     */
    private int userID;

    public UserFrame(int userID) {
        initComponents();

        dbsetting = new Connect();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");

        this.userID = userID;

        buttonMyGames.setBold();

        buttonsSearchSetIcon();
        setUserName();
        loadMyGames();

        UIManager UI = new UIManager();
        UI.put("Panel.background", new ColorUIResource(40, 46, 57));
        UI.put("OptionPane.background", new ColorUIResource(40, 46, 57));
        UI.put("OptionPane.messageForeground", Color.white);
    }

    public void buttonsSearchSetIcon() {
        buttonSearchMyGames.setSearchIcon();
        buttonSearchStore.setSearchIcon();
    }

    public void setUserName() {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);

            Statement stt = conn.createStatement();
            String SQL = "SELECT name FROM users WHERE userID=" + this.userID;
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                labelUserName.setText(res.getString(1));
            }
            stt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void searchMyGames() {
        scrollPaneMyGames.getVerticalScrollBar().setUnitIncrement(16);

        JPanel myGamesPanel = new JPanel();
        scrollPaneMyGames.setViewportView(myGamesPanel);
        myGamesPanel.setLayout(new BorderLayout(0, 0));

        JPanel columnPanel = new JPanel();
        myGamesPanel.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);
            Statement stt = conn.createStatement();
            String SQL = "SELECT transactions.gameID, game_title, studio_name, publisher_name, release_date, genre FROM transactions LEFT JOIN games ON transactions.gameID = games.gameID LEFT JOIN studios ON games.studioID=studios.studioID LEFT JOIN publishers ON games.publisherID=publishers.publisherID WHERE userID="
                    + this.userID + " AND (game_title LIKE '%" + textSearchMyGames.getText() + "%' OR genre LIKE '%" + textSearchMyGames.getText() + "%' OR studio_name LIKE '%" + textSearchMyGames.getText() + "%' OR publisher_name LIKE '%" + textSearchMyGames.getText() + "%' OR release_date LIKE '%" + textSearchMyGames.getText()
                    + "%' OR price LIKE '%" + textSearchMyGames.getText() + "%')";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                JPanel rowPanel = new JPanel();
                rowPanel.setPreferredSize(new Dimension(400, 200));
                rowPanel.setLayout(null);
                rowPanel.setBackground(new Color(61, 76, 93));

                ImageIcon icon = new ImageIcon("src/images/" + res.getString(1) + ".jpg");
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
                studio.setForeground(new Color(103, 193, 245));
                rowPanel.add(studio);

                JLabel publisher = new JLabel("Publisher: " + res.getString(4));
                publisher.setBounds(420, 60, 500, 30);
                publisher.setFont(new Font("Arial", Font.PLAIN, 18));
                publisher.setForeground(new Color(103, 193, 245));
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
                genre.setForeground(new Color(103, 193, 245));
                genre.setColor(new Color(39, 59, 75));
                genre.setBorderColor(new Color(39, 59, 75));
                genre.setColorOver(new Color(40, 46, 57));
                genre.setColorClick(new Color(40, 46, 57));
                rowPanel.add(genre);

                columnPanel.add(rowPanel);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void loadMyGames() {
        scrollPaneMyGames.getVerticalScrollBar().setUnitIncrement(16);

        JPanel myGamesPanel = new JPanel();
        scrollPaneMyGames.setViewportView(myGamesPanel);
        myGamesPanel.setLayout(new BorderLayout(0, 0));

        JPanel columnPanel = new JPanel();
        myGamesPanel.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);
            Statement stt = conn.createStatement();
            String SQL = "SELECT transactions.gameID, game_title, studio_name, publisher_name, release_date, genre FROM transactions LEFT JOIN games ON transactions.gameID = games.gameID LEFT JOIN studios ON games.studioID=studios.studioID LEFT JOIN publishers ON games.publisherID=publishers.publisherID WHERE userID=" + this.userID + " ORDER BY game_title";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                JPanel rowPanel = new JPanel();
                rowPanel.setPreferredSize(new Dimension(400, 200));
                rowPanel.setLayout(null);
                rowPanel.setBackground(new Color(61, 76, 93));

                ImageIcon icon = new ImageIcon("src/images/" + res.getString(1) + ".jpg");
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
                studio.setForeground(new Color(103, 193, 245));
                rowPanel.add(studio);

                JLabel publisher = new JLabel("Publisher: " + res.getString(4));
                publisher.setBounds(420, 60, 500, 30);
                publisher.setFont(new Font("Arial", Font.PLAIN, 18));
                publisher.setForeground(new Color(103, 193, 245));
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
                genre.setForeground(new Color(103, 193, 245));
                genre.setColor(new Color(39, 59, 75));
                genre.setBorderColor(new Color(39, 59, 75));
                genre.setColorOver(new Color(40, 46, 57));
                genre.setColorClick(new Color(40, 46, 57));
                rowPanel.add(genre);

                columnPanel.add(rowPanel);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void searchStore() {
        scrollPaneStore.getVerticalScrollBar().setUnitIncrement(16);

        JPanel storePanel = new JPanel();
        scrollPaneStore.setViewportView(storePanel);
        storePanel.setLayout(new BorderLayout(0, 0));

        JPanel columnPanel = new JPanel();
        storePanel.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);
            Statement stt = conn.createStatement();
            String SQL = "SELECT gameID, game_title, studio_name, publisher_name, release_date, genre, price FROM games LEFT JOIN studios ON games.studioID=studios.studioID LEFT JOIN publishers ON games.publisherID=publishers.publisherID WHERE game_title LIKE '%"
                    + textSearchStore.getText() + "%' OR genre LIKE '%" + textSearchStore.getText() + "%' OR studio_name LIKE '%" + textSearchStore.getText() + "%' OR publisher_name LIKE '%" + textSearchStore.getText() + "%' OR release_date LIKE '%"
                    + textSearchStore.getText() + "%' OR price LIKE '%" + textSearchStore.getText() + "%'";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                String gameID = res.getString(1);
                String game_title = res.getString(2);
                
                JPanel rowPanel = new JPanel();
                rowPanel.setPreferredSize(new Dimension(400, 200));
                rowPanel.setLayout(null);
                rowPanel.setBackground(new Color(61, 76, 93));

                ImageIcon icon = new ImageIcon("src/images/" + res.getString(1) + ".jpg");
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
                studio.setForeground(new Color(103, 193, 245));
                rowPanel.add(studio);

                JLabel publisher = new JLabel("Publisher: " + res.getString(4));
                publisher.setBounds(420, 60, 500, 30);
                publisher.setFont(new Font("Arial", Font.PLAIN, 18));
                publisher.setForeground(new Color(103, 193, 245));
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
                genre.setForeground(new Color(103, 193, 245));
                genre.setColor(new Color(39, 59, 75));
                genre.setBorderColor(new Color(39, 59, 75));
                genre.setColorOver(new Color(40, 46, 57));
                genre.setColorClick(new Color(40, 46, 57));
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
                buy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == buy) {
                            int result = JOptionPane.showConfirmDialog(null, "Do You Want To Buy This Game? \n" + game_title, "Buy Confirmation", JOptionPane.YES_NO_OPTION);
                            switch (result) {
                                case JOptionPane.YES_OPTION:
                                    insertTransaction(gameID);
                                    break;
                                case JOptionPane.NO_OPTION:
                                    break;
                            }
                        }
                    }
                });

                columnPanel.add(rowPanel);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void loadStore() {
        scrollPaneStore.getVerticalScrollBar().setUnitIncrement(16);

        JPanel storePanel = new JPanel();
        scrollPaneStore.setViewportView(storePanel);
        storePanel.setLayout(new BorderLayout(0, 0));

        JPanel columnPanel = new JPanel();
        storePanel.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);
            Statement stt = conn.createStatement();
            String SQL = "SELECT gameID, game_title, studio_name, publisher_name, release_date, genre, price FROM games LEFT JOIN studios ON games.studioID=studios.studioID LEFT JOIN publishers ON games.publisherID=publishers.publisherID ORDER BY game_title";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                String gameID = res.getString(1);
                String game_title = res.getString(2);

                JPanel rowPanel = new JPanel();
                rowPanel.setPreferredSize(new Dimension(400, 200));
                rowPanel.setLayout(null);
                rowPanel.setBackground(new Color(61, 76, 93));

                ImageIcon icon = new ImageIcon("src/images/" + res.getString(1) + ".jpg");
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
                studio.setForeground(new Color(103, 193, 245));
                rowPanel.add(studio);

                JLabel publisher = new JLabel("Publisher: " + res.getString(4));
                publisher.setBounds(420, 60, 500, 30);
                publisher.setFont(new Font("Arial", Font.PLAIN, 18));
                publisher.setForeground(new Color(103, 193, 245));
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
                genre.setForeground(new Color(103, 193, 245));
                genre.setColor(new Color(39, 59, 75));
                genre.setBorderColor(new Color(39, 59, 75));
                genre.setColorOver(new Color(40, 46, 57));
                genre.setColorClick(new Color(40, 46, 57));
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
                buy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == buy) {
                            int result = JOptionPane.showConfirmDialog(null, "Do You Want To Buy This Game? \n" + game_title, "Buy Confirmation", JOptionPane.YES_NO_OPTION);
                            switch (result) {
                                case JOptionPane.YES_OPTION:
                                    insertTransaction(gameID);
                                    break;
                                case JOptionPane.NO_OPTION:
                                    break;
                            }
                        }
                    }
                });

                columnPanel.add(rowPanel);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void insertTransaction(String gameID) {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);
            Statement stt = conn.createStatement();
            String SQL = "INSERT INTO transactions (userID, gameID, transaction_date) VALUES "
                    + "("
                    + "'" + this.userID + "', "
                    + "'" + gameID + "', "
                    + "CURDATE()"
                    + ")";
            stt.executeUpdate(SQL);
            stt.close();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
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
        labelUserName = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        MyGames = new javax.swing.JPanel();
        labelMyGames = new javax.swing.JLabel();
        textSearchMyGames = new TextFieldRound();
        buttonSearchMyGames = new ButtonRound();
        scrollPaneMyGames = new javax.swing.JScrollPane();
        Store = new javax.swing.JPanel();
        labelStore = new javax.swing.JLabel();
        textSearchStore = new TextFieldRound();
        buttonSearchStore = new ButtonRound();
        scrollPaneStore = new javax.swing.JScrollPane();

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

        labelUserName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        labelUserName.setForeground(new java.awt.Color(255, 255, 255));
        labelUserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelUserName.setText("Username");

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addComponent(buttonMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonStore, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 582, Short.MAX_VALUE)
                .addComponent(labelUserName)
                .addGap(100, 100, 100))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonStore, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUserName))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        getContentPane().add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        MyGames.setBackground(new java.awt.Color(22, 25, 32));
        MyGames.setPreferredSize(new java.awt.Dimension(1280, 642));

        labelMyGames.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelMyGames.setForeground(new java.awt.Color(255, 255, 255));
        labelMyGames.setText("MY GAMES");

        textSearchMyGames.setBackground(new java.awt.Color(51, 51, 51));
        textSearchMyGames.setForeground(new java.awt.Color(255, 255, 255));
        textSearchMyGames.setText("Search...");
        textSearchMyGames.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textSearchMyGames.setPreferredSize(new java.awt.Dimension(300, 30));
        textSearchMyGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textSearchMyGamesMouseClicked(evt);
            }
        });

        buttonSearchMyGames.setBackground(new java.awt.Color(39, 59, 75));
        buttonSearchMyGames.setForeground(new java.awt.Color(103, 193, 245));
        buttonSearchMyGames.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonSearchMyGames.setColor(new java.awt.Color(39, 59, 75));
        buttonSearchMyGames.setColorClick(new java.awt.Color(19, 39, 55));
        buttonSearchMyGames.setColorOver(new java.awt.Color(79, 99, 115));
        buttonSearchMyGames.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonSearchMyGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSearchMyGamesMouseClicked(evt);
            }
        });

        scrollPaneMyGames.setBackground(new java.awt.Color(22, 25, 32));
        scrollPaneMyGames.setBorder(null);
        scrollPaneMyGames.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneMyGames.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneMyGames.setPreferredSize(new java.awt.Dimension(100, 900));

        javax.swing.GroupLayout MyGamesLayout = new javax.swing.GroupLayout(MyGames);
        MyGames.setLayout(MyGamesLayout);
        MyGamesLayout.setHorizontalGroup(
            MyGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MyGamesLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(MyGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMyGames)
                    .addComponent(scrollPaneMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MyGamesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textSearchMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonSearchMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        MyGamesLayout.setVerticalGroup(
            MyGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MyGamesLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelMyGames)
                .addGap(47, 47, 47)
                .addGroup(MyGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textSearchMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneMyGames, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab1", MyGames);

        Store.setBackground(new java.awt.Color(22, 25, 32));
        Store.setPreferredSize(new java.awt.Dimension(1280, 642));

        labelStore.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelStore.setForeground(new java.awt.Color(255, 255, 255));
        labelStore.setText("STORE");

        textSearchStore.setBackground(new java.awt.Color(51, 51, 51));
        textSearchStore.setForeground(new java.awt.Color(255, 255, 255));
        textSearchStore.setText("Search...");
        textSearchStore.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textSearchStore.setPreferredSize(new java.awt.Dimension(300, 30));
        textSearchStore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textSearchStoreMouseClicked(evt);
            }
        });

        buttonSearchStore.setBackground(new java.awt.Color(39, 59, 75));
        buttonSearchStore.setForeground(new java.awt.Color(103, 193, 245));
        buttonSearchStore.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonSearchStore.setColor(new java.awt.Color(39, 59, 75));
        buttonSearchStore.setColorClick(new java.awt.Color(19, 39, 55));
        buttonSearchStore.setColorOver(new java.awt.Color(79, 99, 115));
        buttonSearchStore.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonSearchStore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSearchStoreMouseClicked(evt);
            }
        });

        scrollPaneStore.setBackground(new java.awt.Color(22, 25, 32));
        scrollPaneStore.setBorder(null);
        scrollPaneStore.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneStore.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneStore.setPreferredSize(new java.awt.Dimension(100, 900));

        javax.swing.GroupLayout StoreLayout = new javax.swing.GroupLayout(Store);
        Store.setLayout(StoreLayout);
        StoreLayout.setHorizontalGroup(
            StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StoreLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(StoreLayout.createSequentialGroup()
                        .addComponent(textSearchStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSearchStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelStore)
                        .addComponent(scrollPaneStore, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        StoreLayout.setVerticalGroup(
            StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StoreLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelStore)
                .addGap(47, 47, 47)
                .addGroup(StoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textSearchStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneStore, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab1", Store);

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 50, 1290, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonMyGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMyGamesMouseClicked
        tabbedPane.setSelectedComponent(MyGames);
        buttonMyGames.setBold();
        buttonStore.removeBold();
        loadMyGames();
    }//GEN-LAST:event_buttonMyGamesMouseClicked

    private void buttonStoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonStoreMouseClicked
        tabbedPane.setSelectedComponent(Store);
        buttonStore.setBold();
        buttonMyGames.removeBold();
        loadStore();
    }//GEN-LAST:event_buttonStoreMouseClicked

    private void buttonSearchMyGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSearchMyGamesMouseClicked
        searchMyGames();
    }//GEN-LAST:event_buttonSearchMyGamesMouseClicked

    private void buttonSearchStoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSearchStoreMouseClicked
        searchStore();
    }//GEN-LAST:event_buttonSearchStoreMouseClicked

    private void textSearchMyGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textSearchMyGamesMouseClicked
        textSearchMyGames.setText("");
    }//GEN-LAST:event_textSearchMyGamesMouseClicked

    private void textSearchStoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textSearchStoreMouseClicked
        textSearchStore.setText("");
    }//GEN-LAST:event_textSearchStoreMouseClicked

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
            private int userID;

            public void run() {
                new UserFrame(this.userID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel MyGames;
    private javax.swing.JPanel Store;
    private ButtonRound buttonMyGames;
    private ButtonRound buttonSearchMyGames;
    private ButtonRound buttonSearchStore;
    private ButtonRound buttonStore;
    private javax.swing.JLabel labelMyGames;
    private javax.swing.JLabel labelStore;
    private javax.swing.JLabel labelUserName;
    private javax.swing.JScrollPane scrollPaneMyGames;
    private javax.swing.JScrollPane scrollPaneStore;
    private javax.swing.JTabbedPane tabbedPane;
    private TextFieldRound textSearchMyGames;
    private TextFieldRound textSearchStore;
    // End of variables declaration//GEN-END:variables
}
