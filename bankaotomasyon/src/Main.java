import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Yeni bir Kullanici nesnesi oluşturuyoruz
        Kullanici k = new Kullanici("1", "12345678901", "Aysu", "Mine", "aysumine", "password123", "1995-06-12");

        try {
            // Veritabanı bağlantısını kur
            Baglanti.Baglan();

            // Yeni kullanıcıyı veritabanına kaydet
            Baglanti.Kayit(k);

            // Veritabanındaki kullanıcıları listele
            Baglanti.Listele();

            // Bağlantıyı kapat
            Baglanti.Kapat();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
