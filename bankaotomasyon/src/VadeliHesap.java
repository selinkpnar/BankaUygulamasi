public class VadeliHesap extends Hesap {
    private int vadeSuresi;
    private double faizOrani;

    public VadeliHesap(String hesapNo, String hesapTuru, double bakiye, int vadeSuresi, double faizOrani) {
        super(hesapNo, hesapTuru, bakiye);
        this.vadeSuresi = vadeSuresi;
        this.faizOrani = faizOrani;
    }

    public int getVadeSuresi() {
        return vadeSuresi;
    }

    public void setVadeSuresi(int vadeSuresi) {
        this.vadeSuresi = vadeSuresi;
    }

    public double getFaizOrani() {
        return faizOrani;
    }

    public void setFaizOrani(double faizOrani) {
        this.faizOrani = faizOrani;
    }
}