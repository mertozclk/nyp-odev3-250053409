package toros;

public class Buyucu extends Karakter {
    private int mana;

    public Buyucu(String ad, Lonca lonca) {
        super(ad, lonca);
        this.mana = 100;
    }

    public int getMana() {
        return mana;
    }

    public int buyuYap(String buyuAdi, int manaMaliyeti) {
        if (buyuAdi == null || buyuAdi.trim().isEmpty()) {
            throw new IllegalArgumentException("Büyü adı boş olamaz.");
        }
        if (manaMaliyeti < 1 || manaMaliyeti > 50) {
            throw new IllegalArgumentException("Mana maliyeti 1-50 olmalıdır.");
        }
        hayattaOldugunuDogrula();
        if (manaMaliyeti > mana) {
            throw new IllegalStateException("Mana yetersiz.");
        }
        mana -= manaMaliyeti;
        kayitYaz(buyuAdi + " büyüsü yapıldı");
        return manaMaliyeti * 2 + getSeviye();
    }

    public void manaTopla(int miktar) {
        if (miktar <= 0) {
            throw new IllegalArgumentException("Mana ekleme miktarı pozitif olmalıdır.");
        }
        mana = Math.min(100, mana + miktar);
    }

    @Override
    public void bilgiGoster() {
        super.bilgiGoster();
        System.out.println("Mana: " + mana + "/100");
    }

    @Override
    public String toString() {
        return "Buyucu[" + getAd() + ", seviye " + getSeviye() + ", mana " + mana + "]";
    }
}
