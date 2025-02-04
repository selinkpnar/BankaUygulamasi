import java.sql.*;

public class Baglanti {
    static Connection con;
    static Statement st;
    static ResultSet rs;

    // Veritabanına bağlantı
    public static void Baglan() {
        try {
            String url = "jdbc:mysql://localhost:3306/banka";
            String kullaniciAdi = "root";
            String sifre = "1234"; // Şifreni kontrol et
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
            st = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Kullanıcıyı veritabanına ekle
    public static void Kayit(Kullanici kullanici) throws SQLException {
        String query = "INSERT INTO users(id, tc_no, ad, soyad, kullanici_adi, sifre, dogum_tarihi) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, kullanici.getId());
            pstmt.setString(2, kullanici.getTcNo());
            pstmt.setString(3, kullanici.getAd());
            pstmt.setString(4, kullanici.getSoyad());
            pstmt.setString(5, kullanici.getKullaniciAdi());
            pstmt.setString(6, kullanici.getSifre());
            pstmt.setString(7, kullanici.getDogumTarihi());
            pstmt.executeUpdate();
        }
    }

    // Kullanıcıları listele
    public static void Listele() throws SQLException {
        String query = "SELECT * FROM kullanici";
        rs = st.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getString("ad") + " " + rs.getString("soyad"));
        }
    }

    // Bağlantıyı kapat
    public static void Kapat() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public static Kullanici dogrula(String username, String password) {
        return null;
    }
}

