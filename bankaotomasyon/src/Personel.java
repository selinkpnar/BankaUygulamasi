public class Personel extends Kullanici {
    private String pozisyon;

    public Personel(String id, String tcNo, String ad, String soyad, String kullaniciAdi, String sifre, String dogumTarihi, String pozisyon) {
        super(id, tcNo, ad, soyad, kullaniciAdi, sifre, dogumTarihi);

        this.pozisyon = pozisyon;
    }

    public String getPozisyon() {
        return pozisyon;
    }

    public void setPozisyon(String pozisyon) {
        this.pozisyon = pozisyon;
    }
}