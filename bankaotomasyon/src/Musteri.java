import java.util.List;

public class Musteri {
    private String id;
    private String tcNo;
    private String ad;
    private String soyad;
    private String kullaniciAdi;
    private String sifre;
    private String dogumTarihi;
    private List<Hesap> hesaplar;

    public Musteri(String id, String tcNo, String ad, String soyad, String kullaniciAdi, String sifre, String dogumTarihi) {
        this.id = id;
        this.tcNo = tcNo;
        this.ad = ad;
        this.soyad = soyad;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.dogumTarihi = dogumTarihi;
    }

    public List<Hesap> getHesaplar() {
        return hesaplar;
    }

    public void setHesaplar(List<Hesap> hesaplar) {
        this.hesaplar = hesaplar;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }
    public String getKullaniciAdi() {
        return kullaniciAdi;
    }
    public String getSifre() {
        return sifre;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }
    public String getTcNo() {
        return tcNo;
    }
    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }
    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
    public void setKullaniciAdi(String kullaniciAdi) {

    }
    public void setSifre(String sifre) {
        this.sifre = sifre;

    }
    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }



}