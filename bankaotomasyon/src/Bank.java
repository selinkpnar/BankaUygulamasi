import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName;
    private List<Musteri> musteriler;
    private List<Personel> personeller;
    private List<Hesap> hesaplar;
    private List<Islem> islemler;

    // Constructor
    public Bank(String bankName) {
        this.bankName = bankName;
        this.musteriler = new ArrayList<>();
        this.personeller = new ArrayList<>();
        this.hesaplar = new ArrayList<>();
        this.islemler = new ArrayList<>();
    }

    // Add Customer
    public void addMusteri(Musteri musteri) {
        musteriler.add(musteri);
    }

    // Add Employee
    public void addPersonel(Personel personel) {
        personeller.add(personel);
    }

    // Add Account
    public void addHesap(Hesap hesap) {
        hesaplar.add(hesap);
    }

    // Add Transaction
    public void addIslem(Islem islem) {
        islemler.add(islem);
    }

    // Getters
    public List<Musteri> getMusteriler() {
        return musteriler;
    }

    public List<Personel> getPersoneller() {
        return personeller;
    }

    public List<Hesap> getHesaplar() {
        return hesaplar;
    }

    public List<Islem> getIslemler() {
        return islemler;
    }
}