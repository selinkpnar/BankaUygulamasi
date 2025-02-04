public class VadesizHesap extends Hesap {
    private double limit;

    public VadesizHesap(String hesapNo, String hesapTuru, double bakiye, double limit) {
        super(hesapNo, hesapTuru, bakiye);
        this.limit = limit;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
}
}