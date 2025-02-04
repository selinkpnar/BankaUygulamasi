import java.util.Date;

public class Islem {
    private int id;
    private Date tarih;
    private double miktar;
    public String islemTuru; // PARA_YATIRMA, PARA_CEKME, HAVALE
    private Hesap kaynakHesap;
    private Hesap hedefHesap;

    // Constructor
    public Islem(int id, Date tarih, double miktar, String islemTuru, Hesap kaynakHesap, Hesap hedefHesap) {
        this.id = id;
        this.tarih = tarih;
        this.miktar = miktar;
        this.islemTuru = islemTuru;
        this.kaynakHesap = kaynakHesap;
        this.hedefHesap = hedefHesap;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public double getMiktar() {
        return miktar;
    }

    public void setMiktar(double miktar) {
        this.miktar = miktar;
    }

    public String getIslemTuru() {
        return islemTuru;
    }

    public void setIslemTuru(String islemTuru) {
        this.islemTuru = islemTuru;
    }

    public Hesap getKaynakHesap() {
        return kaynakHesap;
    }

    public void setKaynakHesap(Hesap kaynakHesap) {
        this.kaynakHesap = kaynakHesap;
    }

    public Hesap getHedefHesap() {
        return hedefHesap;
    }

    public void setHedefHesap(Hesap hedefHesap) {
        this.hedefHesap = hedefHesap;
    }
}
