public class KurManager {
    private static double altinKuru = 1500.0; // Örnek altın gram fiyatı
    private static double dolarKuru = 20.0;  // Örnek döviz kuru

    public static double getAltinKuru() {
        return altinKuru;
    }

    public static double getDolarKuru() {
        return dolarKuru;
    }

    public static void setAltinKuru(double yeniKurs) {
        altinKuru = yeniKurs;
    }

    public static void setDolarKuru(double yeniKurs) {
        dolarKuru = yeniKurs;
    }
}
