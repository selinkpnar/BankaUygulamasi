import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class BankaGUI {
    private JFrame frame;
    private JTabbedPane tabbedPane;

    public BankaGUI() {
        frame = new JFrame("Banka Uygulaması");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        tabbedPane = new JTabbedPane();

        // Sekmeler
        tabbedPane.add("Müşteri Yönetimi", createMusteriPanel());
        tabbedPane.add("Personel Yönetimi", createPersonelPanel());
        tabbedPane.add("Hesap Yönetimi", createHesapPanel());


        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private JPanel createMusteriPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldDimension = new Dimension(200, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        JTextField idField = new JTextField();
        idField.setPreferredSize(fieldDimension);
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Ad:"), gbc);
        gbc.gridx = 1;
        JTextField adField = new JTextField();
        adField.setPreferredSize(fieldDimension);
        formPanel.add(adField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Soyad:"), gbc);
        gbc.gridx = 1;
        JTextField soyadField = new JTextField();
        soyadField.setPreferredSize(fieldDimension);
        formPanel.add(soyadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("TC No:"), gbc);
        gbc.gridx = 1;
        JTextField tcField = new JTextField();
        tcField.setPreferredSize(fieldDimension);
        formPanel.add(tcField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Kullanıcı Adı:"), gbc);
        gbc.gridx = 1;
        JTextField kullaniciAdiField = new JTextField();
        kullaniciAdiField.setPreferredSize(fieldDimension);
        formPanel.add(kullaniciAdiField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Şifre:"), gbc);
        gbc.gridx = 1;
        JPasswordField sifreField = new JPasswordField();
        sifreField.setPreferredSize(fieldDimension);
        formPanel.add(sifreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Doğum Tarihi (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        JTextField dogumField = new JTextField();
        dogumField.setPreferredSize(fieldDimension);
        formPanel.add(dogumField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JButton ekleButton = new JButton("Müşteri Ekle");
        ekleButton.setPreferredSize(new Dimension(200, 35));
        ekleButton.setBackground(new Color(70, 130, 180));
        ekleButton.setForeground(Color.WHITE);
        ekleButton.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(ekleButton, gbc);

        gbc.gridy = 8;
        JButton guncelleButton = new JButton("Müşteri Güncelle");
        guncelleButton.setPreferredSize(new Dimension(200, 35));
        guncelleButton.setBackground(new Color(34, 139, 34));
        guncelleButton.setForeground(Color.WHITE);
        guncelleButton.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(guncelleButton, gbc);

        gbc.gridy = 9;
        JButton silButton = new JButton("Müşteri Sil");
        silButton.setPreferredSize(new Dimension(200, 35));
        silButton.setBackground(new Color(178, 34, 34));
        silButton.setForeground(Color.WHITE);
        silButton.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(silButton, gbc);

        panel.add(formPanel, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Ad", "Soyad", "TC No", "Kullanıcı Adı", "Doğum Tarihi"}, 0);
        JTable musteriTable = new JTable(tableModel);
        panel.add(new JScrollPane(musteriTable), BorderLayout.CENTER);

        loadMusteriData(tableModel);

        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String ad = adField.getText();
                String soyad = soyadField.getText();
                String tcNo = tcField.getText();
                String kullaniciAdi = kullaniciAdiField.getText();
                String sifre = new String(sifreField.getPassword());
                String dogumTarihi = dogumField.getText();

                if (id.isEmpty() || ad.isEmpty() || soyad.isEmpty() || tcNo.isEmpty() || kullaniciAdi.isEmpty() || sifre.isEmpty() || dogumTarihi.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO musteri (id, ad, soyad, tc_no, kullanici_adi, sifre, dogum_tarihi) VALUES (?, ?, ?, ?, ?, ?, ?)");) {

                    stmt.setString(1, id);
                    stmt.setString(2, ad);
                    stmt.setString(3, soyad);
                    stmt.setString(4, tcNo);
                    stmt.setString(5, kullaniciAdi);
                    stmt.setString(6, sifre);
                    stmt.setString(7, dogumTarihi);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Müşteri başarıyla eklendi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);

                    tableModel.addRow(new Object[]{id, ad, soyad, tcNo, kullaniciAdi, dogumTarihi});

                    idField.setText("");
                    adField.setText("");
                    soyadField.setText("");
                    tcField.setText("");
                    kullaniciAdiField.setText("");
                    sifreField.setText("");
                    dogumField.setText("");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        guncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = musteriTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(panel, "Lütfen güncellenecek bir müşteri seçin!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String id = idField.getText();
                String ad = adField.getText();
                String soyad = soyadField.getText();
                String tcNo = tcField.getText();
                String kullaniciAdi = kullaniciAdiField.getText();
                String sifre = new String(sifreField.getPassword());
                String dogumTarihi = dogumField.getText();

                if (id.isEmpty() || ad.isEmpty() || soyad.isEmpty() || tcNo.isEmpty() || kullaniciAdi.isEmpty() || sifre.isEmpty() || dogumTarihi.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
                     PreparedStatement stmt = conn.prepareStatement("UPDATE musteri SET ad = ?, soyad = ?, tc_no = ?, kullanici_adi = ?, sifre = ?, dogum_tarihi = ? WHERE id = ?");) {

                    stmt.setString(1, ad);
                    stmt.setString(2, soyad);
                    stmt.setString(3, tcNo);
                    stmt.setString(4, kullaniciAdi);
                    stmt.setString(5, sifre);
                    stmt.setString(6, dogumTarihi);
                    stmt.setString(7, id);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Müşteri başarıyla güncellendi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);

                    tableModel.setValueAt(id, selectedRow, 0);
                    tableModel.setValueAt(ad, selectedRow, 1);
                    tableModel.setValueAt(soyad, selectedRow, 2);
                    tableModel.setValueAt(tcNo, selectedRow, 3);
                    tableModel.setValueAt(kullaniciAdi, selectedRow, 4);
                    tableModel.setValueAt(dogumTarihi, selectedRow, 5);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = musteriTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(panel, "Lütfen silinecek bir müşteri seçin!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String id = tableModel.getValueAt(selectedRow, 0).toString();

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM musteri WHERE id = ?");) {

                    stmt.setString(1, id);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Müşteri başarıyla silindi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);

                    tableModel.removeRow(selectedRow);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    private void loadMusteriData(DefaultTableModel tableModel) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, ad, soyad, tc_no, kullanici_adi, dogum_tarihi FROM musteri")) {

            while (rs.next()) {
                String id = rs.getString("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String tcNo = rs.getString("tc_no");
                String kullaniciAdi = rs.getString("kullanici_adi");
                String dogumTarihi = rs.getString("dogum_tarihi");

                tableModel.addRow(new Object[]{id, ad, soyad, tcNo, kullaniciAdi, dogumTarihi});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Veritabanından veri alınırken hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }



    private JPanel createPersonelPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255)); // Açık mavi arka plan

        // Üst panel (Personel ekleme formu)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255)); // Aynı renk
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldDimension = new Dimension(200, 30); // Text field boyutları

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        JTextField idField = new JTextField();
        idField.setPreferredSize(fieldDimension);
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Ad:"), gbc);
        gbc.gridx = 1;
        JTextField adField = new JTextField();
        adField.setPreferredSize(fieldDimension);
        formPanel.add(adField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Soyad:"), gbc);
        gbc.gridx = 1;
        JTextField soyadField = new JTextField();
        soyadField.setPreferredSize(fieldDimension);
        formPanel.add(soyadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Pozisyon:"), gbc);
        gbc.gridx = 1;
        JTextField pozisyonField = new JTextField();
        pozisyonField.setPreferredSize(fieldDimension);
        formPanel.add(pozisyonField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Kullanıcı Adı:"), gbc);
        gbc.gridx = 1;
        JTextField kullaniciAdiField = new JTextField();
        kullaniciAdiField.setPreferredSize(fieldDimension);
        formPanel.add(kullaniciAdiField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton ekleButton = new JButton("Personel Ekle");
        ekleButton.setPreferredSize(new Dimension(200, 35));
        ekleButton.setBackground(new Color(70, 130, 180)); // Koyu mavi buton
        ekleButton.setForeground(Color.WHITE);
        ekleButton.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(ekleButton, gbc);

        gbc.gridy = 6;
        JButton guncelleButton = new JButton("Personel Güncelle");
        guncelleButton.setPreferredSize(new Dimension(200, 35));
        guncelleButton.setBackground(new Color(34, 139, 34)); // Yeşil buton
        guncelleButton.setForeground(Color.WHITE);
        guncelleButton.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(guncelleButton, gbc);

        gbc.gridy = 7;
        JButton silButton = new JButton("Personel Sil");
        silButton.setPreferredSize(new Dimension(200, 35));
        silButton.setBackground(new Color(178, 34, 34)); // Kırmızı buton
        silButton.setForeground(Color.WHITE);
        silButton.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(silButton, gbc);

        panel.add(formPanel, BorderLayout.NORTH);

        // Alt panel (Personel listesi)
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Ad", "Soyad", "Pozisyon", "Kullanıcı Adı"}, 0);
        JTable personelTable = new JTable(tableModel);
        panel.add(new JScrollPane(personelTable), BorderLayout.CENTER);

        // Veritabanından verileri yükleme
        loadPersonelData(tableModel);

        // Ekle butonu dinleyicisi
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String ad = adField.getText();
                String soyad = soyadField.getText();
                String pozisyon = pozisyonField.getText();
                String kullaniciAdi = kullaniciAdiField.getText();

                if (id.isEmpty() || ad.isEmpty() || soyad.isEmpty() || pozisyon.isEmpty() || kullaniciAdi.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO personel (id, ad, soyad, pozisyon, kullanici_adi) VALUES (?, ?, ?, ?, ?)");) {

                    stmt.setString(1, id);
                    stmt.setString(2, ad);
                    stmt.setString(3, soyad);
                    stmt.setString(4, pozisyon);
                    stmt.setString(5, kullaniciAdi);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Personel başarıyla eklendi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);

                    // Tabloya eklenen veriyi ekle
                    tableModel.addRow(new Object[]{id, ad, soyad, pozisyon, kullaniciAdi});

                    // Alanları temizle
                    idField.setText("");
                    adField.setText("");
                    soyadField.setText("");
                    pozisyonField.setText("");
                    kullaniciAdiField.setText("");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Güncelle butonu dinleyicisi
        guncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = personelTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(panel, "Lütfen güncellenecek bir personel seçin!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String id = idField.getText();
                String ad = adField.getText();
                String soyad = soyadField.getText();
                String pozisyon = pozisyonField.getText();
                String kullaniciAdi = kullaniciAdiField.getText();

                if (id.isEmpty() || ad.isEmpty() || soyad.isEmpty() || pozisyon.isEmpty() || kullaniciAdi.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
                     PreparedStatement stmt = conn.prepareStatement("UPDATE personel SET ad = ?, soyad = ?, pozisyon = ?, kullanici_adi = ? WHERE id = ?");) {

                    stmt.setString(1, ad);
                    stmt.setString(2, soyad);
                    stmt.setString(3, pozisyon);
                    stmt.setString(4, kullaniciAdi);
                    stmt.setString(5, id);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Personel başarıyla güncellendi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);

                    tableModel.setValueAt(id, selectedRow, 0);
                    tableModel.setValueAt(ad, selectedRow, 1);
                    tableModel.setValueAt(soyad, selectedRow, 2);
                    tableModel.setValueAt(pozisyon, selectedRow, 3);
                    tableModel.setValueAt(kullaniciAdi, selectedRow, 4);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Sil butonu dinleyicisi
        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = personelTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(panel, "Lütfen silinecek bir personel seçin!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String id = tableModel.getValueAt(selectedRow, 0).toString();

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM personel WHERE id = ?");) {

                    stmt.setString(1, id);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Personel başarıyla silindi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);

                    tableModel.removeRow(selectedRow);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    // Veritabanından personel verilerini çekip tabloyu dolduran metot
    private void loadPersonelData(DefaultTableModel tableModel) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, ad, soyad, pozisyon, kullanici_adi FROM personel")) {

            while (rs.next()) {
                String id = rs.getString("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String pozisyon = rs.getString("pozisyon");
                String kullaniciAdi = rs.getString("kullanici_adi");

                tableModel.addRow(new Object[]{id, ad, soyad, pozisyon, kullaniciAdi});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Veritabanından veri alınırken hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }




    // Hesap panelini oluşturan metot
    private JPanel createHesapPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 240, 245)); // Pembe arka plan

        // Hesap ekleme formu (üst panel)
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.setBackground(new Color(255, 240, 245));

        formPanel.add(new JLabel("Hesap No:"));
        JTextField hesapNoField = new JTextField();
        formPanel.add(hesapNoField);

        formPanel.add(new JLabel("Hesap Türü:"));
        JComboBox<String> hesapTuruCombo = new JComboBox<>(new String[]{"Vadesiz", "Vadeli", "Yatırım"});
        formPanel.add(hesapTuruCombo);

        JLabel altSecenekLabel = new JLabel("Alt Seçenek:");
        altSecenekLabel.setVisible(false);
        JComboBox<String> altSecenekCombo = new JComboBox<>(new String[]{"Altın", "Döviz"});
        altSecenekCombo.setVisible(false);

        formPanel.add(altSecenekLabel);
        formPanel.add(altSecenekCombo);

        // Hesap türüne göre alt seçenekleri göster/gizle
        hesapTuruCombo.addActionListener(e -> {
            String selectedItem = (String) hesapTuruCombo.getSelectedItem();
            if ("Yatırım".equals(selectedItem)) {
                altSecenekLabel.setVisible(true);
                altSecenekCombo.setVisible(true);
            } else {
                altSecenekLabel.setVisible(false);
                altSecenekCombo.setVisible(false);
            }
        });

        formPanel.add(new JLabel("Bakiye:"));
        JTextField bakiyeField = new JTextField();
        formPanel.add(bakiyeField);

        JButton ekleButton = new JButton("Hesap Ekle");
        formPanel.add(ekleButton);

        panel.add(formPanel, BorderLayout.NORTH);

        // Hesap listesi (alt panel)
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Hesap No", "Hesap Türü", "Bakiye"}, 0);
        JTable hesapTable = new JTable(tableModel);
        panel.add(new JScrollPane(hesapTable), BorderLayout.CENTER);

        // Veritabanından hesapları yükle
        loadHesapData(tableModel);

        // Hesap ekleme işlemi
        ekleButton.addActionListener(e -> {
            String hesapNo = hesapNoField.getText();
            String hesapTuru = (String) hesapTuruCombo.getSelectedItem();
            String altSecenek = altSecenekCombo.isVisible() ? (String) altSecenekCombo.getSelectedItem() : null;
            String bakiye = bakiyeField.getText();

            if (hesapNo.isEmpty() || hesapTuru.isEmpty() || bakiye.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO hesap (hesapNo, hesapTuru, bakiye) VALUES (?, ?, ?)")) {

                stmt.setString(1, hesapNo);
                stmt.setString(2, altSecenek == null ? hesapTuru : hesapTuru + " (" + altSecenek + ")");
                stmt.setString(3, bakiye);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(panel, "Hesap başarıyla eklendi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);

                // Yeni veriyi tabloya ekleme
                tableModel.addRow(new Object[]{hesapNo, altSecenek == null ? hesapTuru : hesapTuru + " (" + altSecenek + ")", bakiye});

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Veritabanı hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // Veritabanından hesap bilgilerini tabloya yükleyen metot
    private void loadHesapData(DefaultTableModel tableModel) {
        // Tabloyu temizle
        tableModel.setRowCount(0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT hesapNo, hesapTuru, bakiye FROM hesap")) {

            while (rs.next()) {
                String hesapNo = rs.getString("hesapNo");
                String hesapTuru = rs.getString("hesapTuru");
                String bakiye = rs.getString("bakiye");

                // Tabloya veri ekleme
                tableModel.addRow(new Object[]{hesapNo, hesapTuru, bakiye});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Veritabanından hesap bilgileri alınırken hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }






    private JPanel createIslemPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 255, 240)); // Açık yeşil arka plan

        // Tablo modeli tanımlanıyor
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Tarih", "Miktar", "İşlem Türü", "Kaynak Hesap", "Hedef Hesap"}, 0);
        JTable islemTable = new JTable(tableModel);

        // Tabloyu kaydırma paneline ekleme
        panel.add(new JScrollPane(islemTable), BorderLayout.CENTER);

        // Veritabanından işlem geçmişini yükle
        loadIslemData(tableModel);

        return panel;
    }

    // Veritabanından işlem geçmişini tabloya yükleyen metot
    private void loadIslemData(DefaultTableModel tableModel) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, tarih, miktar, islemTuru, kaynakHesapNo, hedefHesapNo FROM islem")) {

            while (rs.next()) {
                String id = rs.getString("id");
                String tarih = rs.getString("tarih");
                String miktar = rs.getString("miktar");
                String islemTuru = rs.getString("islemTuru");
                String kaynakHesap = rs.getString("kaynakHesapNo");
                String hedefHesap = rs.getString("hedefHesapNo");

                tableModel.addRow(new Object[]{id, tarih, miktar, islemTuru, kaynakHesap, hedefHesap});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Veritabanından işlem geçmişi alınırken hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankaGUI::new);
    }
}
