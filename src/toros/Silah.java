package toros;

public class Silah {
    private final String ad;
    private final int hasar;
    private int dayaniklilik;

    public Silah(String ad, int hasar, int dayaniklilik) {
        if (ad == null || ad.trim().isEmpty()) {
            throw new IllegalArgumentException("Silah adı boş olamaz.");
        }
        if (hasar < 1 || hasar > 100) {
            throw new IllegalArgumentException("Silah hasarı 1-100 arasında olmalıdır.");
        }
        if (dayaniklilik < 0 || dayaniklilik > 100) {
            throw new IllegalArgumentException("Dayanıklılık 0-100 arasında olmalıdır.");
        }
        this.ad = ad;
        this.hasar = hasar;
        this.dayaniklilik = dayaniklilik;
    }

    public String getAd() {
        return ad;
    }

    public int getHasar() {
        return hasar;
    }

    public int getDayaniklilik() {
        return dayaniklilik;
    }

    public int kullan() {
        if (dayaniklilik == 0) {
            throw new IllegalStateException("Silah kırık");
        }
        dayaniklilik = Math.max(0, dayaniklilik - 10);
        return hasar;
    }

    public void tamirEt() {
        dayaniklilik = 100;
    }

    public String bilgiMetni() {
        return ad + " (hasar " + hasar + ", dayanıklılık " + dayaniklilik + ")";
    }

    @Override
    public String toString() {
        return "Silah[" + ad + ", hasar " + hasar + "]";
    }
}
