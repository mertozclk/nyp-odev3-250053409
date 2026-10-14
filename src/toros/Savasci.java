package toros;

public class Savasci extends Karakter {
    private final int zirh;

    public Savasci(String ad, Lonca lonca, int zirh) {
        super(ad, lonca);
        if (zirh < 0 || zirh > 30) {
            throw new IllegalArgumentException("Zırh 0-30 arasında olmalıdır.");
        }
        this.zirh = zirh;
    }

    @Override
    public void hasarAl(int miktar) {
        if (miktar <= 0) {
            throw new IllegalArgumentException("Hasar miktarı pozitif olmalıdır.");
        }
        int etki = Math.max(1, miktar - zirh);
        super.hasarAl(etki);
    }

    @Override
    public void bilgiGoster() {
        super.bilgiGoster();
        System.out.println("Zırh: " + zirh);
    }

    @Override
    public String toString() {
        return "Savasci[" + getAd() + ", seviye " + getSeviye() + ", zırh " + zirh + "]";
    }
}
