public class AltinHesabi extends YatirimHesabi {
    private double gramAltin;

    public AltinHesabi(String hesapNo, String hesapTuru, double bakiye, double gramAltin) {
        super(hesapNo, hesapTuru, bakiye);
        this.gramAltin = gramAltin;
    }

    public double getGramAltin() {
        return gramAltin;
    }

    public void setGramAltin(double gramAltin) {
        this.gramAltin = gramAltin;
    }
}