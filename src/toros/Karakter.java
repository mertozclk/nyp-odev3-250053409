package toros;

public class Karakter {
    private final String ad;
    private int seviye;
    private int can;
    private final Lonca lonca;
    private Silah silah;

    public Karakter(String ad, Lonca lonca) {
        if (ad == null || ad.trim().isEmpty() || ad.length() < 3 || ad.length() > 15) {
            throw new IllegalArgumentException("Ad 3-15 karakter olmalıdır.");
        }
        if (lonca == null) {
            throw new IllegalArgumentException("Lonca null olamaz.");
        }
        this.ad = ad;
        this.lonca = lonca;
        this.seviye = 1;
        this.can = azamiCan();
        this.silah = null;
    }

    public final int azamiCan() {
        return 100 + seviye * 10;
    }

    public final boolean hayattaMi() {
        return can > 0;
    }

    protected final void hayattaOldugunuDogrula() {
        if (!hayattaMi()) {
            throw new IllegalStateException("Karakter hayatta değil.");
        }
    }

    protected void kayitYaz(String olay) {
        System.out.println("[" + ad + "] " + olay);
    }

    public String getAd() {
        return ad;
    }

    public int getSeviye() {
        return seviye;
    }

    public int getCan() {
        return can;
    }

    public Lonca getLonca() {
        return lonca;
    }

    public Silah getSilah() {
        return silah;
    }

    public void hasarAl(int miktar) {
        if (miktar <= 0) {
            throw new IllegalArgumentException("Hasar miktarı pozitif olmalıdır.");
        }
        can = Math.max(0, can - miktar);
    }

    public void iyiles(int miktar) {
        if (miktar <= 0) {
            throw new IllegalArgumentException("İyileştirme miktarı pozitif olmalıdır.");
        }
        hayattaOldugunuDogrula();
        can = Math.min(azamiCan(), can + miktar);
    }

    public void seviyeAtla() {
        if (seviye == 50) {
            throw new IllegalStateException("Seviye en fazla 50 olabilir.");
        }
        seviye++;
        can = azamiCan();
    }

    public void silahKusan(Silah yeniSilah) {
        if (yeniSilah == null) {
            throw new IllegalArgumentException("Silah null olamaz.");
        }
        this.silah = yeniSilah;
    }

    public int saldir() {
        hayattaOldugunuDogrula();
        if (silah == null) {
            return 5 + seviye;
        }
        return silah.kullan() + seviye;
    }

    public void bilgiGoster() {
        String silahMetni = silah == null ? "yok" : silah.getAd();
        System.out.printf("%s | Seviye %d | Can %d/%d | Lonca: %s | Silah: %s%n",
                ad, seviye, can, azamiCan(), lonca.getAd(), silahMetni);
    }

    @Override
    public String toString() {
        return "Karakter[" + ad + ", seviye " + seviye + "]";
    }
}
