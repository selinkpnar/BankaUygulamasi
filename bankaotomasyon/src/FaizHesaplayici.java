public class FaizHesaplayici {
    public static double hesapla(double bakiye, double faizOrani, int vade) {
        return bakiye * (1 + (faizOrani / 100) * vade);
    }
}