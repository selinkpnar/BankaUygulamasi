public class DovizHesabi extends YatirimHesabi {
    private String dovizTuru;
    private double miktar;

    public DovizHesabi(String hesapNo, String hesapTuru, double bakiye, String dovizTuru, double miktar) {
        super(hesapNo, hesapTuru, bakiye);
        this.dovizTuru = dovizTuru;
        this.miktar = miktar;
    }

    public String getDovizTuru() {
        return dovizTuru;
    }

    public void setDovizTuru(String dovizTuru) {
        this.dovizTuru = dovizTuru;
    }

    public double getMiktar() {
        return miktar;
    }

    public void setMiktar(double miktar) {
        this.miktar = miktar;
    }
}