package toros;

public class Suikastci extends Karakter {
    private boolean gizli;

    public Suikastci(String ad, Lonca lonca) {
        super(ad, lonca);
        this.gizli = false;
    }

    public void gizlen() {
        hayattaOldugunuDogrula();
        gizli = true;
        kayitYaz("gölgelere karıştı");
    }

    @Override
    public int saldir() {
        if (gizli) {
            int hasar = super.saldir() * 2;
            gizli = false;
            return hasar;
        }
        return super.saldir();
    }

    @Override
    public void bilgiGoster() {
        super.bilgiGoster();
        System.out.println("Gizli: " + gizli);
    }

    @Override
    public String toString() {
        return "Suikastci[" + getAd() + ", seviye " + getSeviye() + ", gizli=" + gizli + "]";
    }
}
