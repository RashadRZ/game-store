
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class Dashboard extends javax.swing.JFrame {

    Connect dbsetting;
    String driver, database, user, pass;
    Object table;

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();

        dbsetting = new Connect();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");

        tableStudios.setModel(tableModelStudios);
        tablePublishers.setModel(tableModelPublishers);
        tableGames.setModel(tableModelGames);
        tableUsers.setModel(tableModelUsers);
        tableTransactions.setModel(tableModelTransactions);

        setTableLoad(1);
        setTableLoad(2);
        setTableLoad(3);
        setTableLoad(4);
        setTableLoad(5);

        buttonStudios.setBold();
        buttonsSearchSetIcon();
        buttonsEditSetIcon();
        buttonsDeleteSetIcon();
        tableHeaderSetColor();
    }

    private javax.swing.table.DefaultTableModel tableModelStudios = getDefaultTableModel(1);
    private javax.swing.table.DefaultTableModel tableModelPublishers = getDefaultTableModel(2);
    private javax.swing.table.DefaultTableModel tableModelGames = getDefaultTableModel(3);
    private javax.swing.table.DefaultTableModel tableModelUsers = getDefaultTableModel(4);
    private javax.swing.table.DefaultTableModel tableModelTransactions = getDefaultTableModel(5);

    private javax.swing.table.DefaultTableModel getDefaultTableModel(int i) {
        switch (i) {
            case 1:
                return new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Studio Name", "Director", "Studio Country"}) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
            case 2:
                return new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Publisher Name", "Publisher Country"}) {
                    boolean[] canEdit = new boolean[]{
                        false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
            case 3:
                return new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Game Title", "Genre", "Studio", "Publisher", "Release Date", "Price"}) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
            case 4:
                return new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Name", "Email", "Gender"}) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
            case 5:
                return new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"User Name", "Game", "Transaction Date"}) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
        }
        return null;
    }

    private void setTableLoad(int n) {
        String data[];
        String stat = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);
            Statement stt = conn.createStatement();
            String SQL = "";
            ResultSet res;
            switch (n) {
                case 1:
                    data = new String[3];
                    SQL += "SELECT studio_name, director, studio_country FROM studios";
                    res = stt.executeQuery(SQL);
                    while (res.next()) {
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        data[2] = res.getString(3);
                        tableModelStudios.addRow(data);
                    }
                    res.close();
                    stt.close();
                    conn.close();
                    break;
                case 2:
                    data = new String[3];
                    SQL += "SELECT publisher_name, publisher_country FROM publishers";
                    res = stt.executeQuery(SQL);
                    while (res.next()) {
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        tableModelPublishers.addRow(data);
                    }
                    res.close();
                    stt.close();
                    conn.close();
                    break;
                case 3:
                    data = new String[7];
                    SQL += "SELECT game_title, genre, studio_name, publisher_name, release_date, price FROM games LEFT JOIN studios ON games.studioID = studios.studioID LEFT JOIN publishers ON games.publisherID = publishers.publisherID";
                    res = stt.executeQuery(SQL);
                    while (res.next()) {
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        data[2] = res.getString(3);
                        data[3] = res.getString(4);
                        data[4] = res.getString(5);
                        data[5] = res.getString(6);
                        tableModelGames.addRow(data);
                    }
                    res.close();
                    stt.close();
                    conn.close();
                    break;
                case 4:
                    data = new String[4];
                    SQL += "SELECT name, email, gender FROM users";
                    res = stt.executeQuery(SQL);
                    while (res.next()) {
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        data[2] = res.getString(3);
                        tableModelUsers.addRow(data);
                    }
                    res.close();
                    stt.close();
                    conn.close();
                    break;
                case 5:
                    data = new String[4];
                    SQL += "SELECT name, game_title, transaction_date FROM transactions LEFT JOIN users ON transactions.userID = users.UserID LEFT JOIN games ON transactions.gameID = games.gameID";
                    res = stt.executeQuery(SQL);
                    while (res.next()) {
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        data[2] = res.getString(3);
                        tableModelTransactions.addRow(data);
                    }
                    res.close();
                    stt.close();
                    conn.close();
                    break;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    private void setTableSearch(int n) {
        try {
            String data[];
            String stat = "";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);
            Statement stt = conn.createStatement();
            String SQL = "";
            ResultSet res;
            switch (n) {
                case 1:
                    tableModelStudios.setRowCount(0);
                    data = new String[3];
                    SQL += "SELECT studio_name, director, studio_country FROM studios WHERE studio_name LIKE '%" + textSearchStudios.getText() + "%' OR director LIKE '%" + textSearchStudios.getText() + "%' OR studio_country LIKE '%" + textSearchStudios.getText() + "%'";
                    res = stt.executeQuery(SQL);
                    while (res.next()) {
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        data[2] = res.getString(3);
                        tableModelStudios.addRow(data);
                    }
                    res.close();
                    stt.close();
                    conn.close();
                    break;
                case 2:
                    tableModelPublishers.setRowCount(0);
                    data = new String[2];
                    SQL += "SELECT publisher_name, publisher_country FROM publishers WHERE publisher_name LIKE '%" + textSearchPublishers.getText() + "%' OR publisher_country LIKE '%" + textSearchPublishers.getText() + "%'";
                    res = stt.executeQuery(SQL);
                    while (res.next()) {
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        tableModelPublishers.addRow(data);
                    }
                    res.close();
                    stt.close();
                    conn.close();
                    break;
                case 3:
                    tableModelGames.setRowCount(0);
                    data = new String[6];
                    SQL += "SELECT game_title, genre, studio_name, publisher_name, release_date, price FROM games LEFT JOIN studios ON games.studioID = studios.studioID LEFT JOIN publishers ON games.publisherID = publishers.publisherID WHERE game_title LIKE '%" + textSearchGames.getText() + "%' OR genre LIKE '%" + textSearchGames.getText() + "%' OR studio_name LIKE '%" + textSearchGames.getText() + "%' OR publisher_name LIKE '%" + textSearchGames.getText() + "%' OR release_date LIKE '%" + textSearchGames.getText() + "%' OR price LIKE '%" + textSearchGames.getText() + "%'";
                    res = stt.executeQuery(SQL);
                    while (res.next()) {
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        data[2] = res.getString(3);
                        data[3] = res.getString(4);
                        data[4] = res.getString(5);
                        data[5] = res.getString(6);
                        tableModelGames.addRow(data);
                    }
                    res.close();
                    stt.close();
                    conn.close();
                    break;
                case 4:
                    tableModelUsers.setRowCount(0);
                    data = new String[3];
                    SQL += "SELECT name, email, gender FROM users WHERE name LIKE '%" + textSearchUsers.getText() + "%' OR email LIKE '%" + textSearchUsers.getText() + "%' OR gender LIKE '%" + textSearchUsers.getText() + "%'";
                    res = stt.executeQuery(SQL);
                    while (res.next()) {
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        data[2] = res.getString(3);
                        tableModelUsers.addRow(data);
                    }
                    res.close();
                    stt.close();
                    conn.close();
                    break;
                case 5:
                    tableModelTransactions.setRowCount(0);
                    data = new String[3];
                    SQL += "SELECT name, game_title, transaction_date FROM transactions LEFT JOIN users ON transactions.userID = users.UserID LEFT JOIN games ON transactions.gameID = games.gameID WHERE name LIKE '%" + textSearchTransactions.getText() + "%' OR game_title LIKE '%" + textSearchTransactions.getText() + "%' OR transaction_date LIKE '%" + textSearchTransactions.getText() + "%'";
                    res = stt.executeQuery(SQL);
                    while (res.next()) {
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        data[2] = res.getString(3);
                        tableModelTransactions.addRow(data);
                    }
                    res.close();
                    stt.close();
                    conn.close();
                    break;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void tableHeaderSetColor() {
        tableStudios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableStudios.getTableHeader().setBackground(new Color(40, 46, 57));
        tableStudios.getTableHeader().setForeground(new Color(255, 255, 255));
        tableStudios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableStudios.getTableHeader().setBorder(new LineBorder(new Color(40, 46, 57)));
        scrollPaneStudios.getViewport().setBackground(new Color(31, 39, 51));

        tablePublishers.getTableHeader().setOpaque(false);
        tablePublishers.getTableHeader().setBackground(new Color(40, 46, 57));
        tablePublishers.getTableHeader().setForeground(new Color(255, 255, 255));
        tablePublishers.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablePublishers.getTableHeader().setBorder(new LineBorder(new Color(40, 46, 57)));
        scrollPanePublishers.getViewport().setBackground(new Color(31, 39, 51));

        tableGames.getTableHeader().setOpaque(false);
        tableGames.getTableHeader().setBackground(new Color(40, 46, 57));
        tableGames.getTableHeader().setForeground(new Color(255, 255, 255));
        tableGames.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableGames.getTableHeader().setBorder(new LineBorder(new Color(40, 46, 57)));
        scrollPaneGames.getViewport().setBackground(new Color(31, 39, 51));

        tableUsers.getTableHeader().setOpaque(false);
        tableUsers.getTableHeader().setBackground(new Color(40, 46, 57));
        tableUsers.getTableHeader().setForeground(new Color(255, 255, 255));
        tableUsers.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableUsers.getTableHeader().setBorder(new LineBorder(new Color(40, 46, 57)));
        scrollPaneUsers.getViewport().setBackground(new Color(31, 39, 51));

        tableTransactions.getTableHeader().setOpaque(false);
        tableTransactions.getTableHeader().setBackground(new Color(40, 46, 57));
        tableTransactions.getTableHeader().setForeground(new Color(255, 255, 255));
        tableTransactions.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableTransactions.getTableHeader().setBorder(new LineBorder(new Color(40, 46, 57)));
        scrollPaneTransactions.getViewport().setBackground(new Color(31, 39, 51));
    }

    public void buttonMenuRemoveBold() {
        buttonStudios.removeBold();
        buttonPublishers.removeBold();
        buttonGames.removeBold();
        buttonUsers.removeBold();
        buttonTransactions.removeBold();
    }

    public void buttonsSearchSetIcon() {
        buttonSearchStudios.setSearchIcon();
        buttonSearchPublishers.setSearchIcon();
        buttonSearchGames.setSearchIcon();
        buttonSearchUsers.setSearchIcon();
        buttonSearchTransactions.setSearchIcon();
    }

    public void buttonsEditSetIcon() {
        buttonEditStudios.setEditIcon();
        buttonEditPublishers.setEditIcon();
        buttonEditGames.setEditIcon();
    }

    public void buttonsDeleteSetIcon() {
        buttonDeleteStudios.setDeleteIcon();
        buttonDeletePublishers.setDeleteIcon();
        buttonDeleteGames.setDeleteIcon();
        buttonDeleteUsers.setDeleteIcon();
        buttonDeleteTransactions.setDeleteIcon();
    }

    public void insertIntoTable(int n) {
        String data[];
        switch (n) {
            case 1:
                data = new String[3];
                if ((textStudioName.getText().isEmpty()) || (textDirector.getText().isEmpty()) || (textStudioCountry.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Data can't be empty");
                } else {
                    try {
                        Class.forName(driver);
                        Connection conn = DriverManager.getConnection(database, user, pass);
                        Statement stt = conn.createStatement();
                        String SQL = "INSERT INTO studios (studio_name, director, studio_country) VALUES"
                                + "("
                                + "'" + textStudioName.getText() + "',"
                                + "'" + textDirector.getText() + "',"
                                + "'" + textStudioCountry.getText() + "'"
                                + ")";
                        stt.executeUpdate(SQL);
                        data[0] = textStudioName.getText();
                        data[1] = textDirector.getText();
                        data[2] = textStudioCountry.getText();
                        tableModelStudios.insertRow(0, data);
                        stt.close();
                        conn.close();
                        resetForm(1);
                        tabbedPane.setSelectedComponent(Studios);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                break;
            case 2:
                data = new String[2];
                if ((textPublisherName.getText().isEmpty()) || (textPublisherCountry.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Data can't be empty");
                } else {
                    try {
                        Class.forName(driver);
                        Connection conn = DriverManager.getConnection(database, user, pass);
                        Statement stt = conn.createStatement();
                        String SQL = "INSERT INTO publishers (publisher_name, publisher_country) VALUES"
                                + "("
                                + "'" + textPublisherName.getText() + "',"
                                + "'" + textPublisherCountry.getText() + "'"
                                + ")";
                        stt.executeUpdate(SQL);
                        data[0] = textPublisherName.getText();
                        data[1] = textPublisherCountry.getText();
                        tableModelPublishers.insertRow(0, data);
                        stt.close();
                        conn.close();
                        resetForm(2);
                        tabbedPane.setSelectedComponent(Publishers);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                break;
            case 3:
                data = new String[6];
                if ((textGameTitle.getText().isEmpty()) || (textGenre.getText().isEmpty()) || (comboBoxStudio.getSelectedIndex() == -1) || (comboBoxPublisher.getSelectedIndex() == -1) || (textReleaseDate.getText().isEmpty()) || (textPrice.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Data can't be empty");
                } else {
                    try {
                        Class.forName(driver);
                        Connection conn = DriverManager.getConnection(database, user, pass);
                        Statement stt = conn.createStatement();
                        StudioItem si = (StudioItem) comboBoxStudio.getSelectedItem();
                        PublisherItem pi = (PublisherItem) comboBoxPublisher.getSelectedItem();
                        String SQL = "INSERT INTO games (game_title, genre, studioID, publisherID, release_date, price) VALUES"
                                + "("
                                + "'" + textGameTitle.getText() + "',"
                                + "'" + textGenre.getText() + "',"
                                + si.studioID + ", "
                                + pi.publisherID + ", "
                                + "'" + textReleaseDate.getText() + "',"
                                + Double.parseDouble(textPrice.getText())
                                + ")";
                        stt.executeUpdate(SQL);
                        data[0] = textGameTitle.getText();
                        data[1] = textGenre.getText();
                        data[2] = si.studioName;
                        data[3] = pi.publisherName;
                        data[4] = textReleaseDate.getText();
                        data[5] = textPrice.getText();
                        tableModelGames.insertRow(0, data);
                        stt.close();
                        conn.close();
                        resetForm(3);
                        tabbedPane.setSelectedComponent(Games);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                break;
        }
    }

    public void resetForm(int n) {
        switch (n) {
            case 1:
                textStudioName.setText("");
                textDirector.setText("");
                textStudioCountry.setText("");
                break;
            case 2:
                textPublisherName.setText("");
                textPublisherCountry.setText("");
                break;
            case 3:
                textGameTitle.setText("");
                comboBoxStudio.setSelectedIndex(-1);
                comboBoxPublisher.setSelectedIndex(-1);
                textGenre.setText("");
                textReleaseDate.setText("");
                textPrice.setText("");
                break;
        }
    }

    // Add item to Combo Box with name but insert with ID
    public class StudioItem {

        int studioID;
        String studioName;

        StudioItem(int studioID, String studioName) {
            this.studioID = studioID;
            this.studioName = studioName;
        }

        public String toString() {
            return studioName;
        }

    }

    public void loadStudio() {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);
            Statement stt = conn.createStatement();
            String SQL = "";
            ResultSet res;
            String data[] = new String[2];
            SQL += "SELECT studioID, studio_name FROM studios ORDER BY studio_name";
            res = stt.executeQuery(SQL);
            comboBoxStudio.removeAllItems();
            while (res.next()) {
                StudioItem si;
                comboBoxStudio.addItem(si = new StudioItem(res.getInt(1), res.getString(2)));
            }
            res.close();
            stt.close();
            conn.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public class PublisherItem {

        int publisherID;
        String publisherName;

        PublisherItem(int publisherID, String publisherName) {
            this.publisherID = publisherID;
            this.publisherName = publisherName;
        }

        public String toString() {
            return publisherName;
        }

    }

    public void loadPublisher() {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(database, user, pass);
            Statement stt = conn.createStatement();
            String SQL = "";
            ResultSet res;
            String data[] = new String[2];
            SQL += "SELECT publisherID, publisher_name FROM publishers ORDER by publisher_name";
            res = stt.executeQuery(SQL);
            comboBoxPublisher.removeAllItems();
            while (res.next()) {
                PublisherItem pi;
                comboBoxPublisher.addItem(pi = new PublisherItem(res.getInt(1), res.getString(2)));
            }
            res.close();
            stt.close();
            conn.close();
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

        labelHeader = new javax.swing.JLabel();
        buttonReset = new ButtonRound();
        buttonSubmit = new ButtonRound();
        buttonDelete = new ButtonRound();
        buttonUpdate = new ButtonRound();
        textSearch = new TextFieldRound();
        label = new javax.swing.JLabel();
        textField = new TextFieldRound();
        dateChooser = new com.raven.datechooser.DateChooser();
        Menu = new javax.swing.JPanel();
        buttonStudios = new ButtonRound();
        buttonPublishers = new ButtonRound();
        buttonGames = new ButtonRound();
        buttonUsers = new ButtonRound();
        buttonTransactions = new ButtonRound();
        tabbedPane = new javax.swing.JTabbedPane();
        Studios = new javax.swing.JPanel();
        labelStudios = new javax.swing.JLabel();
        textSearchStudios = new TextFieldRound();
        buttonAddStudios = new ButtonRound();
        scrollPaneStudios = new javax.swing.JScrollPane();
        tableStudios = new javax.swing.JTable();
        buttonSearchStudios = new ButtonRound();
        buttonEditStudios = new ButtonRound();
        buttonDeleteStudios = new ButtonRound();
        Publishers = new javax.swing.JPanel();
        labelPublishers = new javax.swing.JLabel();
        textSearchPublishers = new TextFieldRound();
        buttonAddPublishers = new ButtonRound();
        scrollPanePublishers = new javax.swing.JScrollPane();
        tablePublishers = new javax.swing.JTable();
        buttonSearchPublishers = new ButtonRound();
        buttonEditPublishers = new ButtonRound();
        buttonDeletePublishers = new ButtonRound();
        Games = new javax.swing.JPanel();
        labelGames = new javax.swing.JLabel();
        textSearchGames = new TextFieldRound();
        buttonAddGames = new ButtonRound();
        scrollPaneGames = new javax.swing.JScrollPane();
        tableGames = new javax.swing.JTable();
        buttonSearchGames = new ButtonRound();
        buttonEditGames = new ButtonRound();
        buttonDeleteGames = new ButtonRound();
        Users = new javax.swing.JPanel();
        labelUsers = new javax.swing.JLabel();
        textSearchUsers = new TextFieldRound();
        scrollPaneUsers = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        buttonSearchUsers = new ButtonRound();
        buttonDeleteUsers = new ButtonRound();
        Transactions = new javax.swing.JPanel();
        labelTransactions = new javax.swing.JLabel();
        textSearchTransactions = new TextFieldRound();
        scrollPaneTransactions = new javax.swing.JScrollPane();
        tableTransactions = new javax.swing.JTable();
        buttonSearchTransactions = new ButtonRound();
        buttonDeleteTransactions = new ButtonRound();
        CreateStudios = new javax.swing.JPanel();
        labelCreateStudios = new javax.swing.JLabel();
        panelCreateStudios = new PanelRound();
        labelStudioName = new javax.swing.JLabel();
        textStudioName = new TextFieldRound();
        labelDirector = new javax.swing.JLabel();
        textDirector = new TextFieldRound();
        labelStudioCountry = new javax.swing.JLabel();
        textStudioCountry = new TextFieldRound();
        buttonSubmitCreateStudios = new ButtonRound();
        buttonResetCreateStudios = new ButtonRound();
        CreatePublishers = new javax.swing.JPanel();
        labelCreatePublishers = new javax.swing.JLabel();
        panelCreatePublishers = new PanelRound();
        labelPublisherName = new javax.swing.JLabel();
        textPublisherName = new TextFieldRound();
        labelPublisherCountry = new javax.swing.JLabel();
        textPublisherCountry = new TextFieldRound();
        buttonSubmitCreatePublishers = new ButtonRound();
        buttonResetCreatePublishers = new ButtonRound();
        CreateGames = new javax.swing.JPanel();
        labelCreateGames = new javax.swing.JLabel();
        panelCreateGames = new PanelRound();
        labelGameTitle = new javax.swing.JLabel();
        textGameTitle = new TextFieldRound();
        labelStudio = new javax.swing.JLabel();
        labelPublisher = new javax.swing.JLabel();
        labelGenre = new javax.swing.JLabel();
        textGenre = new TextFieldRound();
        labelReleaseDate = new javax.swing.JLabel();
        textReleaseDate = new TextFieldRound();
        buttonReleaseDate = new ButtonRound();
        labelPrice = new javax.swing.JLabel();
        textPrice = new TextFieldRound();
        comboBoxStudio = new ComboBoxRound();
        comboBoxPublisher = new ComboBoxRound();
        buttonSubmitCreateGames = new ButtonRound();
        buttonResetCreateGames = new ButtonRound();

        labelHeader.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader.setForeground(new java.awt.Color(255, 255, 255));
        labelHeader.setText("HEADER");

        buttonReset.setBackground(new java.awt.Color(39, 59, 75));
        buttonReset.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonReset.setForeground(new java.awt.Color(103, 193, 245));
        buttonReset.setText("RESET");
        buttonReset.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonReset.setColor(new java.awt.Color(39, 59, 75));
        buttonReset.setColorClick(new java.awt.Color(19, 39, 55));
        buttonReset.setColorOver(new java.awt.Color(79, 99, 115));
        buttonReset.setPreferredSize(new java.awt.Dimension(100, 30));

        buttonSubmit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonSubmit.setText("SUBMIT");
        buttonSubmit.setBorderColor(new java.awt.Color(111, 166, 32));
        buttonSubmit.setColorClick(new java.awt.Color(91, 146, 12));
        buttonSubmit.setColorOver(new java.awt.Color(151, 206, 72));
        buttonSubmit.setPreferredSize(new java.awt.Dimension(100, 30));

        buttonDelete.setBackground(new java.awt.Color(173, 0, 26));
        buttonDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonDelete.setText("DELETE");
        buttonDelete.setBorderColor(new java.awt.Color(173, 0, 26));
        buttonDelete.setColor(new java.awt.Color(173, 0, 26));
        buttonDelete.setColorClick(new java.awt.Color(153, 0, 6));
        buttonDelete.setColorOver(new java.awt.Color(213, 40, 66));
        buttonDelete.setPreferredSize(new java.awt.Dimension(100, 30));

        buttonUpdate.setBackground(new java.awt.Color(0, 96, 213));
        buttonUpdate.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonUpdate.setText("UPDATE");
        buttonUpdate.setBorderColor(new java.awt.Color(0, 96, 213));
        buttonUpdate.setColor(new java.awt.Color(0, 96, 213));
        buttonUpdate.setColorClick(new java.awt.Color(0, 76, 193));
        buttonUpdate.setColorOver(new java.awt.Color(40, 136, 253));
        buttonUpdate.setPreferredSize(new java.awt.Dimension(100, 30));

        textSearch.setBackground(new java.awt.Color(51, 51, 51));
        textSearch.setForeground(new java.awt.Color(255, 255, 255));
        textSearch.setText("Search...");
        textSearch.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textSearch.setPreferredSize(new java.awt.Dimension(300, 30));

        label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(4, 175, 244));
        label.setText("label");

        textField.setPreferredSize(new java.awt.Dimension(300, 30));

        dateChooser.setForeground(new java.awt.Color(103, 193, 245));
        dateChooser.setDateFormat("yyyy-MM-dd");
        dateChooser.setTextRefernce(textReleaseDate);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1285, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu.setBackground(new java.awt.Color(0, 0, 0));
        Menu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Menu.setLayout(new java.awt.GridLayout(1, 5));

        buttonStudios.setBackground(new java.awt.Color(0, 0, 0));
        buttonStudios.setForeground(new java.awt.Color(158, 158, 158));
        buttonStudios.setText("STUDIOS");
        buttonStudios.setBorderColor(new java.awt.Color(0, 0, 0));
        buttonStudios.setColor(new java.awt.Color(0, 0, 0));
        buttonStudios.setColorClick(new java.awt.Color(0, 0, 0));
        buttonStudios.setColorOver(new java.awt.Color(30, 30, 30));
        buttonStudios.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buttonStudios.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonStudios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonStudiosMouseClicked(evt);
            }
        });
        Menu.add(buttonStudios);

        buttonPublishers.setBackground(new java.awt.Color(0, 0, 0));
        buttonPublishers.setForeground(new java.awt.Color(158, 158, 158));
        buttonPublishers.setText("PUBLISHERS");
        buttonPublishers.setBorderColor(new java.awt.Color(0, 0, 0));
        buttonPublishers.setColor(new java.awt.Color(0, 0, 0));
        buttonPublishers.setColorClick(new java.awt.Color(0, 0, 0));
        buttonPublishers.setColorOver(new java.awt.Color(30, 30, 30));
        buttonPublishers.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buttonPublishers.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonPublishers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonPublishersMouseClicked(evt);
            }
        });
        Menu.add(buttonPublishers);

        buttonGames.setBackground(new java.awt.Color(0, 0, 0));
        buttonGames.setForeground(new java.awt.Color(158, 158, 158));
        buttonGames.setText("GAMES");
        buttonGames.setBorderColor(new java.awt.Color(0, 0, 0));
        buttonGames.setColor(new java.awt.Color(0, 0, 0));
        buttonGames.setColorClick(new java.awt.Color(0, 0, 0));
        buttonGames.setColorOver(new java.awt.Color(30, 30, 30));
        buttonGames.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buttonGames.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonGamesMouseClicked(evt);
            }
        });
        Menu.add(buttonGames);

        buttonUsers.setBackground(new java.awt.Color(0, 0, 0));
        buttonUsers.setForeground(new java.awt.Color(158, 158, 158));
        buttonUsers.setText("USERS");
        buttonUsers.setBorderColor(new java.awt.Color(0, 0, 0));
        buttonUsers.setColor(new java.awt.Color(0, 0, 0));
        buttonUsers.setColorClick(new java.awt.Color(0, 0, 0));
        buttonUsers.setColorOver(new java.awt.Color(30, 30, 30));
        buttonUsers.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buttonUsers.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonUsersMouseClicked(evt);
            }
        });
        Menu.add(buttonUsers);

        buttonTransactions.setBackground(new java.awt.Color(0, 0, 0));
        buttonTransactions.setForeground(new java.awt.Color(158, 158, 158));
        buttonTransactions.setText("TRANSACTIONS");
        buttonTransactions.setBorderColor(new java.awt.Color(0, 0, 0));
        buttonTransactions.setColor(new java.awt.Color(0, 0, 0));
        buttonTransactions.setColorClick(new java.awt.Color(0, 0, 0));
        buttonTransactions.setColorOver(new java.awt.Color(30, 30, 30));
        buttonTransactions.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buttonTransactions.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonTransactionsMouseClicked(evt);
            }
        });
        Menu.add(buttonTransactions);

        getContentPane().add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 80));

        tabbedPane.setPreferredSize(new java.awt.Dimension(1290, 698));

        Studios.setBackground(new java.awt.Color(22, 25, 32));
        Studios.setPreferredSize(new java.awt.Dimension(1280, 670));

        labelStudios.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelStudios.setForeground(new java.awt.Color(255, 255, 255));
        labelStudios.setText("STUDIOS");

        textSearchStudios.setBackground(new java.awt.Color(51, 51, 51));
        textSearchStudios.setForeground(new java.awt.Color(255, 255, 255));
        textSearchStudios.setText("Search...");
        textSearchStudios.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textSearchStudios.setPreferredSize(new java.awt.Dimension(300, 30));

        buttonAddStudios.setText("ADD");
        buttonAddStudios.setBorderColor(new java.awt.Color(111, 166, 32));
        buttonAddStudios.setColorClick(new java.awt.Color(91, 146, 12));
        buttonAddStudios.setColorOver(new java.awt.Color(151, 206, 72));
        buttonAddStudios.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonAddStudios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAddStudiosMouseClicked(evt);
            }
        });

        scrollPaneStudios.setBackground(new java.awt.Color(31, 39, 51));
        scrollPaneStudios.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tableStudios.setAutoCreateRowSorter(true);
        tableStudios.setBackground(new java.awt.Color(31, 39, 51));
        tableStudios.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tableStudios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tableStudios.setForeground(new java.awt.Color(255, 255, 255));
        tableStudios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableStudios.setGridColor(new java.awt.Color(22, 25, 32));
        tableStudios.setRowHeight(20);
        scrollPaneStudios.setViewportView(tableStudios);

        buttonSearchStudios.setBackground(new java.awt.Color(39, 59, 75));
        buttonSearchStudios.setForeground(new java.awt.Color(103, 193, 245));
        buttonSearchStudios.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonSearchStudios.setColor(new java.awt.Color(39, 59, 75));
        buttonSearchStudios.setColorClick(new java.awt.Color(19, 39, 55));
        buttonSearchStudios.setColorOver(new java.awt.Color(79, 99, 115));
        buttonSearchStudios.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonSearchStudios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSearchStudiosMouseClicked(evt);
            }
        });

        buttonEditStudios.setBackground(new java.awt.Color(0, 96, 213));
        buttonEditStudios.setBorderColor(new java.awt.Color(0, 96, 213));
        buttonEditStudios.setColor(new java.awt.Color(0, 96, 213));
        buttonEditStudios.setColorClick(new java.awt.Color(0, 76, 193));
        buttonEditStudios.setColorOver(new java.awt.Color(40, 136, 253));
        buttonEditStudios.setPreferredSize(new java.awt.Dimension(100, 30));

        buttonDeleteStudios.setBorderColor(new java.awt.Color(173, 0, 26));
        buttonDeleteStudios.setColor(new java.awt.Color(173, 0, 26));
        buttonDeleteStudios.setColorClick(new java.awt.Color(153, 0, 6));
        buttonDeleteStudios.setColorOver(new java.awt.Color(213, 40, 66));
        buttonDeleteStudios.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout StudiosLayout = new javax.swing.GroupLayout(Studios);
        Studios.setLayout(StudiosLayout);
        StudiosLayout.setHorizontalGroup(
            StudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudiosLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(StudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStudios)
                    .addGroup(StudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(StudiosLayout.createSequentialGroup()
                            .addComponent(buttonEditStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(buttonDeleteStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPaneStudios, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(StudiosLayout.createSequentialGroup()
                            .addComponent(buttonAddStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textSearchStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(buttonSearchStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(100, 100, 100))
        );
        StudiosLayout.setVerticalGroup(
            StudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudiosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelStudios)
                .addGap(50, 50, 50)
                .addGroup(StudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonAddStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSearchStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneStudios, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(StudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonDeleteStudios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEditStudios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabbedPane.addTab("tab1", Studios);

        Publishers.setBackground(new java.awt.Color(22, 25, 32));
        Publishers.setPreferredSize(new java.awt.Dimension(1280, 670));

        labelPublishers.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelPublishers.setForeground(new java.awt.Color(255, 255, 255));
        labelPublishers.setText("PUBLISHERS");

        textSearchPublishers.setBackground(new java.awt.Color(51, 51, 51));
        textSearchPublishers.setForeground(new java.awt.Color(255, 255, 255));
        textSearchPublishers.setText("Search...");
        textSearchPublishers.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textSearchPublishers.setPreferredSize(new java.awt.Dimension(300, 30));

        buttonAddPublishers.setText("ADD");
        buttonAddPublishers.setBorderColor(new java.awt.Color(111, 166, 32));
        buttonAddPublishers.setColorClick(new java.awt.Color(91, 146, 12));
        buttonAddPublishers.setColorOver(new java.awt.Color(151, 206, 72));
        buttonAddPublishers.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonAddPublishers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAddPublishersMouseClicked(evt);
            }
        });

        scrollPanePublishers.setBackground(new java.awt.Color(31, 39, 51));
        scrollPanePublishers.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tablePublishers.setAutoCreateRowSorter(true);
        tablePublishers.setBackground(new java.awt.Color(31, 39, 51));
        tablePublishers.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tablePublishers.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tablePublishers.setForeground(new java.awt.Color(255, 255, 255));
        tablePublishers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePublishers.setGridColor(new java.awt.Color(22, 25, 32));
        tablePublishers.setRowHeight(20);
        scrollPanePublishers.setViewportView(tablePublishers);

        buttonSearchPublishers.setBackground(new java.awt.Color(39, 59, 75));
        buttonSearchPublishers.setForeground(new java.awt.Color(103, 193, 245));
        buttonSearchPublishers.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonSearchPublishers.setColor(new java.awt.Color(39, 59, 75));
        buttonSearchPublishers.setColorClick(new java.awt.Color(19, 39, 55));
        buttonSearchPublishers.setColorOver(new java.awt.Color(79, 99, 115));
        buttonSearchPublishers.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonSearchPublishers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSearchPublishersMouseClicked(evt);
            }
        });

        buttonEditPublishers.setBackground(new java.awt.Color(0, 96, 213));
        buttonEditPublishers.setBorderColor(new java.awt.Color(0, 96, 213));
        buttonEditPublishers.setColor(new java.awt.Color(0, 96, 213));
        buttonEditPublishers.setColorClick(new java.awt.Color(0, 76, 193));
        buttonEditPublishers.setColorOver(new java.awt.Color(40, 136, 253));
        buttonEditPublishers.setPreferredSize(new java.awt.Dimension(100, 30));

        buttonDeletePublishers.setBorderColor(new java.awt.Color(173, 0, 26));
        buttonDeletePublishers.setColor(new java.awt.Color(173, 0, 26));
        buttonDeletePublishers.setColorClick(new java.awt.Color(153, 0, 6));
        buttonDeletePublishers.setColorOver(new java.awt.Color(213, 40, 66));
        buttonDeletePublishers.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout PublishersLayout = new javax.swing.GroupLayout(Publishers);
        Publishers.setLayout(PublishersLayout);
        PublishersLayout.setHorizontalGroup(
            PublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PublishersLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(PublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PublishersLayout.createSequentialGroup()
                        .addComponent(buttonEditPublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDeletePublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(PublishersLayout.createSequentialGroup()
                            .addComponent(buttonAddPublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textSearchPublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(buttonSearchPublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(labelPublishers)
                        .addComponent(scrollPanePublishers, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        PublishersLayout.setVerticalGroup(
            PublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PublishersLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelPublishers)
                .addGap(50, 50, 50)
                .addGroup(PublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonAddPublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchPublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSearchPublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PublishersLayout.createSequentialGroup()
                        .addComponent(scrollPanePublishers, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDeletePublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonEditPublishers, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab2", Publishers);

        Games.setBackground(new java.awt.Color(22, 25, 32));
        Games.setPreferredSize(new java.awt.Dimension(1285, 670));

        labelGames.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelGames.setForeground(new java.awt.Color(255, 255, 255));
        labelGames.setText("GAMES");

        textSearchGames.setBackground(new java.awt.Color(51, 51, 51));
        textSearchGames.setForeground(new java.awt.Color(255, 255, 255));
        textSearchGames.setText("Search...");
        textSearchGames.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textSearchGames.setPreferredSize(new java.awt.Dimension(300, 30));

        buttonAddGames.setText("ADD");
        buttonAddGames.setBorderColor(new java.awt.Color(111, 166, 32));
        buttonAddGames.setColorClick(new java.awt.Color(91, 146, 12));
        buttonAddGames.setColorOver(new java.awt.Color(151, 206, 72));
        buttonAddGames.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonAddGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAddGamesMouseClicked(evt);
            }
        });

        scrollPaneGames.setBackground(new java.awt.Color(31, 39, 51));
        scrollPaneGames.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tableGames.setAutoCreateRowSorter(true);
        tableGames.setBackground(new java.awt.Color(31, 39, 51));
        tableGames.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tableGames.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tableGames.setForeground(new java.awt.Color(255, 255, 255));
        tableGames.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableGames.setGridColor(new java.awt.Color(22, 25, 32));
        tableGames.setRowHeight(20);
        scrollPaneGames.setViewportView(tableGames);

        buttonSearchGames.setBackground(new java.awt.Color(39, 59, 75));
        buttonSearchGames.setForeground(new java.awt.Color(103, 193, 245));
        buttonSearchGames.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonSearchGames.setColor(new java.awt.Color(39, 59, 75));
        buttonSearchGames.setColorClick(new java.awt.Color(19, 39, 55));
        buttonSearchGames.setColorOver(new java.awt.Color(79, 99, 115));
        buttonSearchGames.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonSearchGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSearchGamesMouseClicked(evt);
            }
        });

        buttonEditGames.setBackground(new java.awt.Color(0, 96, 213));
        buttonEditGames.setBorderColor(new java.awt.Color(0, 96, 213));
        buttonEditGames.setColor(new java.awt.Color(0, 96, 213));
        buttonEditGames.setColorClick(new java.awt.Color(0, 76, 193));
        buttonEditGames.setColorOver(new java.awt.Color(40, 136, 253));
        buttonEditGames.setPreferredSize(new java.awt.Dimension(100, 30));

        buttonDeleteGames.setBorderColor(new java.awt.Color(173, 0, 26));
        buttonDeleteGames.setColor(new java.awt.Color(173, 0, 26));
        buttonDeleteGames.setColorClick(new java.awt.Color(153, 0, 6));
        buttonDeleteGames.setColorOver(new java.awt.Color(213, 40, 66));
        buttonDeleteGames.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout GamesLayout = new javax.swing.GroupLayout(Games);
        Games.setLayout(GamesLayout);
        GamesLayout.setHorizontalGroup(
            GamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GamesLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(GamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(GamesLayout.createSequentialGroup()
                        .addComponent(buttonEditGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDeleteGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(GamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(GamesLayout.createSequentialGroup()
                            .addComponent(buttonAddGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textSearchGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(buttonSearchGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPaneGames, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelGames)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        GamesLayout.setVerticalGroup(
            GamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GamesLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelGames)
                .addGap(50, 50, 50)
                .addGroup(GamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonAddGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSearchGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneGames, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(GamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonDeleteGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEditGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab3", Games);

        Users.setBackground(new java.awt.Color(22, 25, 32));
        Users.setPreferredSize(new java.awt.Dimension(1280, 670));

        labelUsers.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelUsers.setForeground(new java.awt.Color(255, 255, 255));
        labelUsers.setText("USERS");

        textSearchUsers.setBackground(new java.awt.Color(51, 51, 51));
        textSearchUsers.setForeground(new java.awt.Color(255, 255, 255));
        textSearchUsers.setText("Search...");
        textSearchUsers.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textSearchUsers.setPreferredSize(new java.awt.Dimension(300, 30));

        scrollPaneUsers.setBackground(new java.awt.Color(31, 39, 51));
        scrollPaneUsers.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tableUsers.setAutoCreateRowSorter(true);
        tableUsers.setBackground(new java.awt.Color(31, 39, 51));
        tableUsers.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tableUsers.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tableUsers.setForeground(new java.awt.Color(255, 255, 255));
        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableUsers.setGridColor(new java.awt.Color(22, 25, 32));
        tableUsers.setRowHeight(20);
        scrollPaneUsers.setViewportView(tableUsers);

        buttonSearchUsers.setBackground(new java.awt.Color(39, 59, 75));
        buttonSearchUsers.setForeground(new java.awt.Color(103, 193, 245));
        buttonSearchUsers.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonSearchUsers.setColor(new java.awt.Color(39, 59, 75));
        buttonSearchUsers.setColorClick(new java.awt.Color(19, 39, 55));
        buttonSearchUsers.setColorOver(new java.awt.Color(79, 99, 115));
        buttonSearchUsers.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonSearchUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSearchUsersMouseClicked(evt);
            }
        });

        buttonDeleteUsers.setBackground(new java.awt.Color(173, 0, 26));
        buttonDeleteUsers.setBorderColor(new java.awt.Color(173, 0, 26));
        buttonDeleteUsers.setColor(new java.awt.Color(173, 0, 26));
        buttonDeleteUsers.setColorClick(new java.awt.Color(153, 0, 6));
        buttonDeleteUsers.setColorOver(new java.awt.Color(213, 40, 66));
        buttonDeleteUsers.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout UsersLayout = new javax.swing.GroupLayout(Users);
        Users.setLayout(UsersLayout);
        UsersLayout.setHorizontalGroup(
            UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonDeleteUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(UsersLayout.createSequentialGroup()
                            .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsersLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(textSearchUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelUsers))
                            .addGap(18, 18, 18)
                            .addComponent(buttonSearchUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPaneUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(150, 150, 150))
        );
        UsersLayout.setVerticalGroup(
            UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(UsersLayout.createSequentialGroup()
                        .addComponent(labelUsers)
                        .addGap(50, 50, 50)
                        .addComponent(textSearchUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonSearchUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonDeleteUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab4", Users);

        Transactions.setBackground(new java.awt.Color(22, 25, 32));
        Transactions.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Transactions.setPreferredSize(new java.awt.Dimension(1280, 670));

        labelTransactions.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelTransactions.setForeground(new java.awt.Color(255, 255, 255));
        labelTransactions.setText("TRANSACTIONS");

        textSearchTransactions.setBackground(new java.awt.Color(51, 51, 51));
        textSearchTransactions.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10));
        textSearchTransactions.setForeground(new java.awt.Color(255, 255, 255));
        textSearchTransactions.setText("Search...");
        textSearchTransactions.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textSearchTransactions.setPreferredSize(new java.awt.Dimension(300, 30));

        scrollPaneTransactions.setBackground(new java.awt.Color(31, 39, 51));
        scrollPaneTransactions.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tableTransactions.setAutoCreateRowSorter(true);
        tableTransactions.setBackground(new java.awt.Color(31, 39, 51));
        tableTransactions.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tableTransactions.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tableTransactions.setForeground(new java.awt.Color(255, 255, 255));
        tableTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableTransactions.setGridColor(new java.awt.Color(22, 25, 32));
        tableTransactions.setRowHeight(20);
        scrollPaneTransactions.setViewportView(tableTransactions);

        buttonSearchTransactions.setBackground(new java.awt.Color(39, 59, 75));
        buttonSearchTransactions.setForeground(new java.awt.Color(103, 193, 245));
        buttonSearchTransactions.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonSearchTransactions.setColor(new java.awt.Color(39, 59, 75));
        buttonSearchTransactions.setColorClick(new java.awt.Color(19, 39, 55));
        buttonSearchTransactions.setColorOver(new java.awt.Color(79, 99, 115));
        buttonSearchTransactions.setPreferredSize(new java.awt.Dimension(30, 30));
        buttonSearchTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSearchTransactionsMouseClicked(evt);
            }
        });

        buttonDeleteTransactions.setBackground(new java.awt.Color(173, 0, 26));
        buttonDeleteTransactions.setBorderColor(new java.awt.Color(173, 0, 26));
        buttonDeleteTransactions.setColor(new java.awt.Color(173, 0, 26));
        buttonDeleteTransactions.setColorClick(new java.awt.Color(153, 0, 6));
        buttonDeleteTransactions.setColorOver(new java.awt.Color(213, 40, 66));
        buttonDeleteTransactions.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout TransactionsLayout = new javax.swing.GroupLayout(Transactions);
        Transactions.setLayout(TransactionsLayout);
        TransactionsLayout.setHorizontalGroup(
            TransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransactionsLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(TransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTransactions)
                    .addGroup(TransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(buttonDeleteTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(TransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransactionsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(textSearchTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonSearchTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPaneTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(104, 104, 104))
        );
        TransactionsLayout.setVerticalGroup(
            TransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransactionsLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(labelTransactions)
                .addGap(50, 50, 50)
                .addGroup(TransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textSearchTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonDeleteTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab5", Transactions);

        CreateStudios.setBackground(new java.awt.Color(22, 25, 32));
        CreateStudios.setPreferredSize(new java.awt.Dimension(1280, 642));

        labelCreateStudios.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelCreateStudios.setForeground(new java.awt.Color(255, 255, 255));
        labelCreateStudios.setText("CREATE STUDIO");

        labelStudioName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelStudioName.setForeground(new java.awt.Color(4, 175, 244));
        labelStudioName.setText("Studio Name");

        textStudioName.setCaretColor(new java.awt.Color(4, 175, 244));
        textStudioName.setPreferredSize(new java.awt.Dimension(300, 30));

        labelDirector.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelDirector.setForeground(new java.awt.Color(4, 175, 244));
        labelDirector.setText("Director");

        textDirector.setPreferredSize(new java.awt.Dimension(300, 30));

        labelStudioCountry.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelStudioCountry.setForeground(new java.awt.Color(4, 175, 244));
        labelStudioCountry.setText("Studio Country");

        textStudioCountry.setPreferredSize(new java.awt.Dimension(300, 30));

        javax.swing.GroupLayout panelCreateStudiosLayout = new javax.swing.GroupLayout(panelCreateStudios);
        panelCreateStudios.setLayout(panelCreateStudiosLayout);
        panelCreateStudiosLayout.setHorizontalGroup(
            panelCreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCreateStudiosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textStudioName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelStudioName))
                .addGap(82, 82, 82)
                .addGroup(panelCreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDirector)
                    .addComponent(textDirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addGroup(panelCreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStudioCountry)
                    .addComponent(textStudioCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCreateStudiosLayout.setVerticalGroup(
            panelCreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCreateStudiosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStudioName)
                    .addComponent(labelStudioCountry)
                    .addComponent(labelDirector))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textStudioName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textStudioCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textDirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        buttonSubmitCreateStudios.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonSubmitCreateStudios.setText("SUBMIT");
        buttonSubmitCreateStudios.setBorderColor(new java.awt.Color(111, 166, 32));
        buttonSubmitCreateStudios.setColorClick(new java.awt.Color(91, 146, 12));
        buttonSubmitCreateStudios.setColorOver(new java.awt.Color(151, 206, 72));
        buttonSubmitCreateStudios.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonSubmitCreateStudios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSubmitCreateStudiosMouseClicked(evt);
            }
        });

        buttonResetCreateStudios.setBackground(new java.awt.Color(39, 59, 75));
        buttonResetCreateStudios.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonResetCreateStudios.setForeground(new java.awt.Color(103, 193, 245));
        buttonResetCreateStudios.setText("RESET");
        buttonResetCreateStudios.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonResetCreateStudios.setColor(new java.awt.Color(39, 59, 75));
        buttonResetCreateStudios.setColorClick(new java.awt.Color(19, 39, 55));
        buttonResetCreateStudios.setColorOver(new java.awt.Color(79, 99, 115));
        buttonResetCreateStudios.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonResetCreateStudios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonResetCreateStudiosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout CreateStudiosLayout = new javax.swing.GroupLayout(CreateStudios);
        CreateStudios.setLayout(CreateStudiosLayout);
        CreateStudiosLayout.setHorizontalGroup(
            CreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateStudiosLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(CreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCreateStudios)
                    .addComponent(panelCreateStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CreateStudiosLayout.createSequentialGroup()
                        .addComponent(buttonSubmitCreateStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonResetCreateStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        CreateStudiosLayout.setVerticalGroup(
            CreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateStudiosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelCreateStudios)
                .addGap(95, 95, 95)
                .addComponent(panelCreateStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(CreateStudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSubmitCreateStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonResetCreateStudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(317, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab6", CreateStudios);

        CreatePublishers.setBackground(new java.awt.Color(22, 25, 32));

        labelCreatePublishers.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelCreatePublishers.setForeground(new java.awt.Color(255, 255, 255));
        labelCreatePublishers.setText("CREATE PUBLISHER");

        labelPublisherName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelPublisherName.setForeground(new java.awt.Color(4, 175, 244));
        labelPublisherName.setText("Publisher Name");

        textPublisherName.setPreferredSize(new java.awt.Dimension(300, 30));

        labelPublisherCountry.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelPublisherCountry.setForeground(new java.awt.Color(4, 175, 244));
        labelPublisherCountry.setText("Publisher Country");

        textPublisherCountry.setPreferredSize(new java.awt.Dimension(300, 30));

        javax.swing.GroupLayout panelCreatePublishersLayout = new javax.swing.GroupLayout(panelCreatePublishers);
        panelCreatePublishers.setLayout(panelCreatePublishersLayout);
        panelCreatePublishersLayout.setHorizontalGroup(
            panelCreatePublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCreatePublishersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCreatePublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textPublisherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPublisherName))
                .addGap(82, 82, 82)
                .addGroup(panelCreatePublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textPublisherCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPublisherCountry))
                .addContainerGap(392, Short.MAX_VALUE))
        );
        panelCreatePublishersLayout.setVerticalGroup(
            panelCreatePublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCreatePublishersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCreatePublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPublisherName)
                    .addComponent(labelPublisherCountry))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCreatePublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textPublisherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPublisherCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        buttonSubmitCreatePublishers.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonSubmitCreatePublishers.setText("SUBMIT");
        buttonSubmitCreatePublishers.setBorderColor(new java.awt.Color(111, 166, 32));
        buttonSubmitCreatePublishers.setColorClick(new java.awt.Color(91, 146, 12));
        buttonSubmitCreatePublishers.setColorOver(new java.awt.Color(151, 206, 72));
        buttonSubmitCreatePublishers.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonSubmitCreatePublishers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSubmitCreatePublishersMouseClicked(evt);
            }
        });

        buttonResetCreatePublishers.setBackground(new java.awt.Color(39, 59, 75));
        buttonResetCreatePublishers.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonResetCreatePublishers.setForeground(new java.awt.Color(103, 193, 245));
        buttonResetCreatePublishers.setText("RESET");
        buttonResetCreatePublishers.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonResetCreatePublishers.setColor(new java.awt.Color(39, 59, 75));
        buttonResetCreatePublishers.setColorClick(new java.awt.Color(19, 39, 55));
        buttonResetCreatePublishers.setColorOver(new java.awt.Color(79, 99, 115));
        buttonResetCreatePublishers.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonResetCreatePublishers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonResetCreatePublishersMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout CreatePublishersLayout = new javax.swing.GroupLayout(CreatePublishers);
        CreatePublishers.setLayout(CreatePublishersLayout);
        CreatePublishersLayout.setHorizontalGroup(
            CreatePublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreatePublishersLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(CreatePublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCreatePublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CreatePublishersLayout.createSequentialGroup()
                        .addComponent(buttonSubmitCreatePublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonResetCreatePublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelCreatePublishers))
                .addGap(100, 100, 100))
        );
        CreatePublishersLayout.setVerticalGroup(
            CreatePublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreatePublishersLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelCreatePublishers)
                .addGap(95, 95, 95)
                .addComponent(panelCreatePublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(CreatePublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSubmitCreatePublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonResetCreatePublishers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(317, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab7", CreatePublishers);

        CreateGames.setBackground(new java.awt.Color(22, 25, 32));

        labelCreateGames.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelCreateGames.setForeground(new java.awt.Color(255, 255, 255));
        labelCreateGames.setText("CREATE GAME");

        labelGameTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelGameTitle.setForeground(new java.awt.Color(4, 175, 244));
        labelGameTitle.setText("Game Title");

        textGameTitle.setPreferredSize(new java.awt.Dimension(300, 30));

        labelStudio.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelStudio.setForeground(new java.awt.Color(4, 175, 244));
        labelStudio.setText("Studio");

        labelPublisher.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelPublisher.setForeground(new java.awt.Color(4, 175, 244));
        labelPublisher.setText("Publisher");

        labelGenre.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelGenre.setForeground(new java.awt.Color(4, 175, 244));
        labelGenre.setText("Genre");

        textGenre.setPreferredSize(new java.awt.Dimension(300, 30));

        labelReleaseDate.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelReleaseDate.setForeground(new java.awt.Color(4, 175, 244));
        labelReleaseDate.setText("Release Date");

        textReleaseDate.setPreferredSize(new java.awt.Dimension(300, 30));

        buttonReleaseDate.setBackground(new java.awt.Color(103, 193, 245));
        buttonReleaseDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonReleaseDate.setText("...");
        buttonReleaseDate.setAlignmentY(0.0F);
        buttonReleaseDate.setBorderColor(new java.awt.Color(103, 193, 245));
        buttonReleaseDate.setColor(new java.awt.Color(103, 193, 245));
        buttonReleaseDate.setColorClick(new java.awt.Color(63, 153, 205));
        buttonReleaseDate.setColorOver(new java.awt.Color(143, 233, 245));
        buttonReleaseDate.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonReleaseDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonReleaseDateMouseClicked(evt);
            }
        });

        labelPrice.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelPrice.setForeground(new java.awt.Color(4, 175, 244));
        labelPrice.setText("Price");

        textPrice.setPreferredSize(new java.awt.Dimension(300, 30));

        comboBoxStudio.setPreferredSize(new java.awt.Dimension(300, 30));

        comboBoxPublisher.setPreferredSize(new java.awt.Dimension(300, 30));

        javax.swing.GroupLayout panelCreateGamesLayout = new javax.swing.GroupLayout(panelCreateGames);
        panelCreateGames.setLayout(panelCreateGamesLayout);
        panelCreateGamesLayout.setHorizontalGroup(
            panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCreateGamesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCreateGamesLayout.createSequentialGroup()
                        .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelGenre))
                        .addGap(82, 82, 82)
                        .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelReleaseDate)
                            .addComponent(textReleaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(152, 152, 152)
                        .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPrice)
                            .addComponent(textPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelCreateGamesLayout.createSequentialGroup()
                        .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonReleaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelCreateGamesLayout.createSequentialGroup()
                                .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textGameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelGameTitle))
                                .addGap(82, 82, 82)
                                .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelStudio)
                                    .addComponent(comboBoxStudio, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(82, 82, 82)
                        .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPublisher)
                            .addComponent(comboBoxPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelCreateGamesLayout.setVerticalGroup(
            panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCreateGamesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGameTitle)
                    .addComponent(labelPublisher)
                    .addComponent(labelStudio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textGameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxStudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGenre)
                    .addComponent(labelPrice)
                    .addComponent(labelReleaseDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textReleaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonReleaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        buttonSubmitCreateGames.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonSubmitCreateGames.setText("SUBMIT");
        buttonSubmitCreateGames.setBorderColor(new java.awt.Color(111, 166, 32));
        buttonSubmitCreateGames.setColorClick(new java.awt.Color(91, 146, 12));
        buttonSubmitCreateGames.setColorOver(new java.awt.Color(151, 206, 72));
        buttonSubmitCreateGames.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonSubmitCreateGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSubmitCreateGamesMouseClicked(evt);
            }
        });

        buttonResetCreateGames.setBackground(new java.awt.Color(39, 59, 75));
        buttonResetCreateGames.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonResetCreateGames.setForeground(new java.awt.Color(103, 193, 245));
        buttonResetCreateGames.setText("RESET");
        buttonResetCreateGames.setBorderColor(new java.awt.Color(39, 59, 75));
        buttonResetCreateGames.setColor(new java.awt.Color(39, 59, 75));
        buttonResetCreateGames.setColorClick(new java.awt.Color(19, 39, 55));
        buttonResetCreateGames.setColorOver(new java.awt.Color(79, 99, 115));
        buttonResetCreateGames.setPreferredSize(new java.awt.Dimension(100, 30));
        buttonResetCreateGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonResetCreateGamesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout CreateGamesLayout = new javax.swing.GroupLayout(CreateGames);
        CreateGames.setLayout(CreateGamesLayout);
        CreateGamesLayout.setHorizontalGroup(
            CreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateGamesLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(CreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCreateGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCreateGames)
                    .addGroup(CreateGamesLayout.createSequentialGroup()
                        .addComponent(buttonSubmitCreateGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonResetCreateGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        CreateGamesLayout.setVerticalGroup(
            CreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateGamesLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelCreateGames)
                .addGap(95, 95, 95)
                .addComponent(panelCreateGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(CreateGamesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSubmitCreateGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonResetCreateGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(230, Short.MAX_VALUE))
        );

        tabbedPane.addTab("tab8", CreateGames);

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 55, 1290, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonStudiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonStudiosMouseClicked
        buttonMenuRemoveBold();
        buttonStudios.setBold();
        tabbedPane.setSelectedComponent(Studios);
    }//GEN-LAST:event_buttonStudiosMouseClicked

    private void buttonPublishersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPublishersMouseClicked
        buttonMenuRemoveBold();
        buttonPublishers.setBold();
        tabbedPane.setSelectedComponent(Publishers);
    }//GEN-LAST:event_buttonPublishersMouseClicked

    private void buttonGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonGamesMouseClicked
        buttonMenuRemoveBold();
        buttonGames.setBold();
        tabbedPane.setSelectedComponent(Games);
    }//GEN-LAST:event_buttonGamesMouseClicked

    private void buttonUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUsersMouseClicked
        buttonMenuRemoveBold();
        buttonUsers.setBold();
        tabbedPane.setSelectedComponent(Users);
    }//GEN-LAST:event_buttonUsersMouseClicked

    private void buttonTransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonTransactionsMouseClicked
        buttonMenuRemoveBold();
        buttonTransactions.setBold();
        tabbedPane.setSelectedComponent(Transactions);
    }//GEN-LAST:event_buttonTransactionsMouseClicked

    private void buttonSearchStudiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSearchStudiosMouseClicked
        setTableSearch(1);
    }//GEN-LAST:event_buttonSearchStudiosMouseClicked

    private void buttonSearchPublishersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSearchPublishersMouseClicked
        setTableSearch(2);
    }//GEN-LAST:event_buttonSearchPublishersMouseClicked

    private void buttonSearchGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSearchGamesMouseClicked
        setTableSearch(3);
    }//GEN-LAST:event_buttonSearchGamesMouseClicked

    private void buttonSearchUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSearchUsersMouseClicked
        setTableSearch(4);
    }//GEN-LAST:event_buttonSearchUsersMouseClicked

    private void buttonSearchTransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSearchTransactionsMouseClicked
        setTableSearch(5);
    }//GEN-LAST:event_buttonSearchTransactionsMouseClicked

    private void buttonAddStudiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAddStudiosMouseClicked
        tabbedPane.setSelectedComponent(CreateStudios);
    }//GEN-LAST:event_buttonAddStudiosMouseClicked

    private void buttonAddPublishersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAddPublishersMouseClicked
        tabbedPane.setSelectedComponent(CreatePublishers);
    }//GEN-LAST:event_buttonAddPublishersMouseClicked

    private void buttonAddGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAddGamesMouseClicked
        tabbedPane.setSelectedComponent(CreateGames);
        loadStudio();
        loadPublisher();
    }//GEN-LAST:event_buttonAddGamesMouseClicked

    private void buttonSubmitCreateStudiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSubmitCreateStudiosMouseClicked
        insertIntoTable(1);
    }//GEN-LAST:event_buttonSubmitCreateStudiosMouseClicked

    private void buttonResetCreateStudiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetCreateStudiosMouseClicked
        resetForm(1);
    }//GEN-LAST:event_buttonResetCreateStudiosMouseClicked

    private void buttonSubmitCreatePublishersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSubmitCreatePublishersMouseClicked
        insertIntoTable(2);
    }//GEN-LAST:event_buttonSubmitCreatePublishersMouseClicked

    private void buttonResetCreatePublishersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetCreatePublishersMouseClicked
        resetForm(2);
    }//GEN-LAST:event_buttonResetCreatePublishersMouseClicked

    private void buttonSubmitCreateGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSubmitCreateGamesMouseClicked
        insertIntoTable(3);
    }//GEN-LAST:event_buttonSubmitCreateGamesMouseClicked

    private void buttonResetCreateGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetCreateGamesMouseClicked
        resetForm(3);
    }//GEN-LAST:event_buttonResetCreateGamesMouseClicked

    private void buttonReleaseDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonReleaseDateMouseClicked
        dateChooser.showPopup();
    }//GEN-LAST:event_buttonReleaseDateMouseClicked

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CreateGames;
    private javax.swing.JPanel CreatePublishers;
    private javax.swing.JPanel CreateStudios;
    private javax.swing.JPanel Games;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Publishers;
    private javax.swing.JPanel Studios;
    private javax.swing.JPanel Transactions;
    private javax.swing.JPanel Users;
    private ButtonRound buttonAddGames;
    private ButtonRound buttonAddPublishers;
    private ButtonRound buttonAddStudios;
    private ButtonRound buttonDelete;
    private ButtonRound buttonDeleteGames;
    private ButtonRound buttonDeletePublishers;
    private ButtonRound buttonDeleteStudios;
    private ButtonRound buttonDeleteTransactions;
    private ButtonRound buttonDeleteUsers;
    private ButtonRound buttonEditGames;
    private ButtonRound buttonEditPublishers;
    private ButtonRound buttonEditStudios;
    private ButtonRound buttonGames;
    private ButtonRound buttonPublishers;
    private ButtonRound buttonReleaseDate;
    private ButtonRound buttonReset;
    private ButtonRound buttonResetCreateGames;
    private ButtonRound buttonResetCreatePublishers;
    private ButtonRound buttonResetCreateStudios;
    private ButtonRound buttonSearchGames;
    private ButtonRound buttonSearchPublishers;
    private ButtonRound buttonSearchStudios;
    private ButtonRound buttonSearchTransactions;
    private ButtonRound buttonSearchUsers;
    private ButtonRound buttonStudios;
    private ButtonRound buttonSubmit;
    private ButtonRound buttonSubmitCreateGames;
    private ButtonRound buttonSubmitCreatePublishers;
    private ButtonRound buttonSubmitCreateStudios;
    private ButtonRound buttonTransactions;
    private ButtonRound buttonUpdate;
    private ButtonRound buttonUsers;
    private ComboBoxRound comboBoxPublisher;
    private ComboBoxRound comboBoxStudio;
    private com.raven.datechooser.DateChooser dateChooser;
    private javax.swing.JLabel label;
    private javax.swing.JLabel labelCreateGames;
    private javax.swing.JLabel labelCreatePublishers;
    private javax.swing.JLabel labelCreateStudios;
    private javax.swing.JLabel labelDirector;
    private javax.swing.JLabel labelGameTitle;
    private javax.swing.JLabel labelGames;
    private javax.swing.JLabel labelGenre;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JLabel labelPrice;
    private javax.swing.JLabel labelPublisher;
    private javax.swing.JLabel labelPublisherCountry;
    private javax.swing.JLabel labelPublisherName;
    private javax.swing.JLabel labelPublishers;
    private javax.swing.JLabel labelReleaseDate;
    private javax.swing.JLabel labelStudio;
    private javax.swing.JLabel labelStudioCountry;
    private javax.swing.JLabel labelStudioName;
    private javax.swing.JLabel labelStudios;
    private javax.swing.JLabel labelTransactions;
    private javax.swing.JLabel labelUsers;
    private PanelRound panelCreateGames;
    private PanelRound panelCreatePublishers;
    private PanelRound panelCreateStudios;
    private javax.swing.JScrollPane scrollPaneGames;
    private javax.swing.JScrollPane scrollPanePublishers;
    private javax.swing.JScrollPane scrollPaneStudios;
    private javax.swing.JScrollPane scrollPaneTransactions;
    private javax.swing.JScrollPane scrollPaneUsers;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tableGames;
    private javax.swing.JTable tablePublishers;
    private javax.swing.JTable tableStudios;
    private javax.swing.JTable tableTransactions;
    private javax.swing.JTable tableUsers;
    private TextFieldRound textDirector;
    private TextFieldRound textField;
    private TextFieldRound textGameTitle;
    private TextFieldRound textGenre;
    private TextFieldRound textPrice;
    private TextFieldRound textPublisherCountry;
    private TextFieldRound textPublisherName;
    private TextFieldRound textReleaseDate;
    private TextFieldRound textSearch;
    private TextFieldRound textSearchGames;
    private TextFieldRound textSearchPublishers;
    private TextFieldRound textSearchStudios;
    private TextFieldRound textSearchTransactions;
    private TextFieldRound textSearchUsers;
    private TextFieldRound textStudioCountry;
    private TextFieldRound textStudioName;
    // End of variables declaration//GEN-END:variables
}
