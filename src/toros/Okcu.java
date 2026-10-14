package toros;

public class Okcu extends Karakter {
    private int okSayisi;

    public Okcu(String ad, Lonca lonca) {
        super(ad, lonca);
        this.okSayisi = 30;
    }

    public int getOkSayisi() {
        return okSayisi;
    }

    public int atesEt() {
        hayattaOldugunuDogrula();
        if (okSayisi <= 0) {
            throw new IllegalStateException("Ok yok.");
        }
        okSayisi--;
        kayitYaz("ok fırlattı");
        return saldir() + 3;
    }

    public void okTopla(int adet) {
        if (adet <= 0) {
            throw new IllegalArgumentException("Eklenen ok sayısı pozitif olmalıdır.");
        }
        okSayisi = Math.min(60, okSayisi + adet);
    }

    @Override
    public void bilgiGoster() {
        super.bilgiGoster();
        System.out.println("Ok: " + okSayisi + "/60");
    }

    @Override
    public String toString() {
        return "Okcu[" + getAd() + ", seviye " + getSeviye() + ", ok " + okSayisi + "]";
    }
}
