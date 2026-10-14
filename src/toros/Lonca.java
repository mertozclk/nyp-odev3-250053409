package toros;

public class Lonca {
    private final String ad;
    private final int kurulusYili;
    private String duyuru;

    public Lonca(String ad, int kurulusYili, String duyuru) {
        if (ad == null || ad.trim().isEmpty() || ad.length() < 3 || ad.length() > 25) {
            throw new IllegalArgumentException("Lonca adı 3-25 karakter olmalıdır.");
        }
        if (kurulusYili <= 0) {
            throw new IllegalArgumentException("Kuruluş yılı pozitif olmalıdır.");
        }
        if (duyuru == null || duyuru.trim().isEmpty() || duyuru.length() > 100) {
            throw new IllegalArgumentException("Duyuru boş olamaz ve 100 karakterden fazla olamaz.");
        }
        this.ad = ad;
        this.kurulusYili = kurulusYili;
        this.duyuru = duyuru;
    }

    public String getAd() {
        return ad;
    }

    public int getKurulusYili() {
        return kurulusYili;
    }

    public String getDuyuru() {
        return duyuru;
    }

    public void setDuyuru(String duyuru) {
        if (duyuru == null || duyuru.trim().isEmpty() || duyuru.length() > 100) {
            throw new IllegalArgumentException("Duyuru boş olamaz ve 100 karakterden fazla olamaz.");
        }
        this.duyuru = duyuru;
    }

    public String bilgiMetni() {
        return ad + " (kuruluş " + kurulusYili + ") - Duyuru: " + duyuru;
    }

    @Override
    public String toString() {
        return "Lonca[" + ad + "]";
    }
}
