public class Hesap {
    private String hesapNo;
    private String hesapTuru;
    private double bakiye;

    public Hesap(String hesapNo, String hesapTuru, double bakiye) {
        this.hesapNo = hesapNo;
        this.hesapTuru = hesapTuru;
        this.bakiye = bakiye;
    }
    public String getHesapTuru(){
        return hesapTuru;
    }

    public String getHesapNo() {
        return hesapNo;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void setBakiye(double bakiye) {
        this.bakiye = bakiye;
    }

    public void setHesapNo(String hesapNo) {
        this.hesapNo = hesapNo;
    }

    public void setHesapTuru(String hesapTuru) {
        this.hesapTuru = hesapTuru;
    }






}

