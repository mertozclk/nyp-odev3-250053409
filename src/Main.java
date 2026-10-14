import toros.*; // toros klasöründeki bütün herşeyi çekiştirdik

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int kDegeri = 27; // pdfte istendiği gibi k değeri
        //loncalarımızı bir listede tutalım ki menüde listeleyebilelim
        ArrayList<Lonca> loncalar = new ArrayList<>(Arrays.asList(
                new Lonca("Kartal Loncası", 1071, "Cuma akşamı kale kuşatması!"),
                new Lonca("Yılan Loncası", 1250, "Gece sessizliğiyle güçlenir.")
        ));

        Lonca kartalLonca = new Lonca("Kartal Loncası", 1071, "Cuma akşamı kale kuşatması!");
        Lonca yilanLonca = new Lonca("Yılan Loncası", 1250, "Gece sessizliğiyle güçlenir."); // 2 adet lonca oluşturuldu.

        int savasciZirh = kDegeri % 31; // Savaşçı zırh değeri belirlendi.
        //karakterlerimizi bir listede tutalım ki menüde listeleyebilelim
        ArrayList<Karakter> karakterler = new ArrayList<>();

        Savasci savasciKarakter = new Savasci("Ayşe", kartalLonca, savasciZirh);
        Buyucu buyucuKarakter = new Buyucu("Kaan", kartalLonca);
        Okcu okcuKarakter = new Okcu("Duru", yilanLonca); // 3 farklı özellikte karakter oluşturuldu
        karakterler.add(savasciKarakter);
        karakterler.add(buyucuKarakter);
        karakterler.add(okcuKarakter);

        int kilicHasar = kDegeri < 10 ? 10 : kDegeri; // Kılıç hasarı belirlendi.
        Silah kilic = new Silah("Kılıç", kilicHasar, 100);
        Silah yay = new Silah("Yay", 12, 100); // 2 adet silah oluşturuldu.

        savasciKarakter.silahKusan(kilic);
        okcuKarakter.silahKusan(yay);// karakterlerimize silahlarını verdik

        System.out.println("=== Başlangıç Durumu ==="); // Karakterlerin başlangıç durumlarını gösteriyoruz
        for (Karakter karakter : karakterler) {
            karakter.bilgiGoster();
        }
        okcuKarakter.bilgiGoster();

        System.out.println("\n=== Saldırılar ==="); // savaşçıyı 3 kez saldırttık
        for (int i = 1; i <= 3; i++) {
            int hasar = savasciKarakter.saldir();
            System.out.println("Saldırı " + i + ": " + hasar + " hasar, Kılıç dayanıklılığı: " + kilic.getDayaniklilik());
        }

        System.out.println("\n=== Aynı hasar etkisi ==="); // Aynı hasarı hem savaşçıya hem büyücüye verdik zırhlarının kalitesi belli olsun :)
        savasciKarakter.hasarAl(40);
        buyucuKarakter.hasarAl(40);
        System.out.println("Savaşçı can: " + savasciKarakter.getCan());
        System.out.println("Büyücü can: " + buyucuKarakter.getCan());

        System.out.println("\n=== Büyüler ==="); // büyücüye 2 adet büyü yaptırdık ve manasını bitirdik sonra 3. büyüyü yapmaya çalıştık ve yapamadı
        System.out.println("Büyü 1 sonucu: " + buyucuKarakter.buyuYap("Ateş Topu", 30));
        System.out.println("Büyü 2 sonucu: " + buyucuKarakter.buyuYap("Buz Kılıcı", 30));
        //try-catch bloğu ile programın çökmesini engelledik ve kullanıcıya hata mesajı verdik
        try {
            buyucuKarakter.buyuYap("Yıldırım", 50);
        } catch (IllegalStateException e) {
            System.out.println("Yıldırım reddedildi: " + e.getMessage());
        }


        System.out.println("\n=== Ok atışı ==="); // okçuyu 2 kez saldırttık ve kalan ok sayısını gösterdik
        System.out.println("Ok 1: " + okcuKarakter.atesEt());
        System.out.println("Ok 2: " + okcuKarakter.atesEt());
        System.out.println("Kalan ok sayısı: " + okcuKarakter.getOkSayisi());


        System.out.println("\n=== İyileştirme ve seviye atlama ==="); // büyücüye can verdik seviye atlattık ve bilgilerini gösterdik
        buyucuKarakter.iyiles(30);
        buyucuKarakter.seviyeAtla();
        buyucuKarakter.bilgiGoster();

        System.out.println("\n=== Geçersiz işlemler ==="); // Geçersiz işlemler için try-catch blokları kullanıldı
        try {
            new Karakter("Al", kartalLonca);
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        try {
            savasciKarakter.hasarAl(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        try {
            buyucuKarakter.iyiles(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        System.out.println("\n=== Duyuru değişimi ===");
        kartalLonca.setDuyuru("Kale kuşatması yarın sabah başlıyor!");
        savasciKarakter.bilgiGoster();
        buyucuKarakter.bilgiGoster();

        System.out.println("\n=== toString örneği ===");
        System.out.println(okcuKarakter);

        System.out.println("\n=== Suikastçı ===");
        Suikastci suikastciKarakter = new Suikastci("Selin", kartalLonca);
        suikastciKarakter.gizlen();
        System.out.println("Suikastçı ilk saldırı: " + suikastciKarakter.saldir());
        System.out.println("Suikastçı ikinci saldırı: " + suikastciKarakter.saldir());
        System.out.println(suikastciKarakter);


        //şimdi bir menü yapalım kullanıcıya seçim yaptırıp ona göre işlemler yaptıracağız
        // menüde yeni karakter oluştuma da olsun
        // menüde karakterlerin bilgilerini gösterme de olsun
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menü ===");
            System.out.println("1. Lonca Oluştur");
            System.out.println("2. Loncaları Listele");
            System.out.println("3. Karakterleri Listele");
            System.out.println("4. Yeni Karakter Oluştur");
            System.out.println("5. Karakter Bilgilerini Düzenle");
            System.out.println("6. Karakter Saldırıları");
            System.out.println("7. karaktere Hasar ver");
            System.out.println("8. Karakter Sil");
            System.out.println("9. Çıkış");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            switch (secim) {
                case 1:
                    // Yeni lonca oluşturalım
                    System.out.print("Lonca adı: ");
                    String loncaAdi = scanner.next();
                    System.out.print("Kuruluş yılı: ");
                    int kurulusYili = scanner.nextInt();
                    System.out.print("Duyuru: ");
                    String duyuru = scanner.next();
                    Lonca yeniLonca = new Lonca(loncaAdi, kurulusYili, duyuru);
                    System.out.println("Yeni lonca oluşturuldu: " + yeniLonca.bilgiMetni());
                    loncalar.add(yeniLonca);
                    break;
                case 2:
                    // olan bütün loncaları oluşturduğumuz listeden listeleyelimss
                    System.out.println("=== Loncalar ===");
                    for (Lonca lonca : loncalar) {
                        System.out.println(lonca.bilgiMetni());
                    }
                    break;
                case 3:
                    // Karakterleri listeleyelim
                    System.out.println("=== Karakterler ===");
                    for (Karakter karakter : karakterler) {
                        System.out.println();
                        karakter.bilgiGoster();
                    }
                    break;
                case 4:
                    // Karakter oluşturma işlemlerini yapalım
                    System.out.print("Karakter adı: ");
                    String karakterAdi = scanner.next();
                    System.out.print("Karakter türü (1: Savaşçı, 2: Büyücü, 3: Okçu, 4: Suikastçı): ");
                    int turSecim = scanner.nextInt();
                    System.out.println("karakter silahı seçiniz (1: Kılıç, 2: Yay): ");
                    int silahSecim = scanner.nextInt();
                    // yanlış silah seçilirse
                    if (silahSecim < 1 || silahSecim > 2) {
                        System.out.println("Geçersiz silah seçimi.");
                        break;
                    }
                    System.out.print("Lonca seçimi : ");
                    for (int i = 0; i < loncalar.size(); i++) {
                        System.out.println((i + 1) + ". " + loncalar.get(i).getAd());
                    }
                    int loncaSecim = scanner.nextInt() - 1;
                    if (loncaSecim < 0 || loncaSecim >= loncalar.size()) {
                        System.out.println("Geçersiz lonca seçimi.");
                    } else {
                        Lonca secilenLonca = loncalar.get(loncaSecim);
                        Karakter yeniKarakter;
                        switch (turSecim) {
                            case 1:
                                yeniKarakter = new Savasci(karakterAdi, secilenLonca, kDegeri % 31);
                                yeniKarakter.silahKusan(silahSecim == 1 ? kilic : yay);
                                break;
                            case 2:
                                yeniKarakter = new Buyucu(karakterAdi, secilenLonca);
                                yeniKarakter.silahKusan(silahSecim == 1 ? kilic : yay);
                                break;
                            case 3:
                                yeniKarakter = new Okcu(karakterAdi, secilenLonca);
                                yeniKarakter.silahKusan(silahSecim == 1 ? kilic : yay);
                                break;
                            case 4:
                                yeniKarakter = new Suikastci(karakterAdi, secilenLonca);
                                yeniKarakter.silahKusan(silahSecim == 1 ? kilic : yay);
                                break;
                            default:
                                System.out.println("Geçersiz karakter türü.");
                                continue;
                        }
                        karakterler.add(yeniKarakter);
                        System.out.println("Yeni karakter oluşturuldu: " + yeniKarakter.getAd());
                    }
                    break;
                case 5:
                    // Karakter düzenleme işlemleri
                    System.out.println("=== Karakterler ===");
                    for (int i = 0; i < karakterler.size(); i++) {
                        System.out.println((i + 1) + ". " + karakterler.get(i).getAd());
                    }
                    System.out.print("Düzenlemek istediğiniz karakterin numarasını girin: ");
                    int karakterSecim = scanner.nextInt() - 1;
                    if (karakterSecim < 0 || karakterSecim >= karakterler.size()) {
                        System.out.println("Geçersiz karakter seçimi.");
                    } else {
                        Karakter secilenKarakter = karakterler.get(karakterSecim);
                        System.out.println("1. Canı iyileştir");
                        System.out.println("2. Seviye atla");
                        System.out.print("Seçiminiz: ");
                        int duzenlemeSecim = scanner.nextInt();
                        switch (duzenlemeSecim) {
                            case 1:
                                //iyileştirme miktarında seviyesine göre belirlenmiş olan azami can değerini geçmeyecek şekilde iyileştirme yapalım
                                int iyilesmeMiktari = secilenKarakter.azamiCan() - secilenKarakter.getCan();
                                secilenKarakter.iyiles(iyilesmeMiktari);
                                System.out.println("Karakterin yeni canı: " + secilenKarakter.getCan());
                                break;
                            case 2:
                                secilenKarakter.seviyeAtla();
                                System.out.println("Karakterin yeni seviyesi: " + secilenKarakter.getSeviye());
                                break;
                            default:
                                System.out.println("Geçersiz seçim.");
                        }
                    }
                    break;

                case 6:
                    // Karakter saldırıları
                    System.out.println("=== Karakterler ===");
                    for (int i = 0; i < karakterler.size(); i++) {
                        System.out.println((i + 1) + ". " + karakterler.get(i).getAd());
                    }
                    System.out.print("Saldırmasını istediğiniz karakterin numarasını girin: ");
                    int saldirmaSecim = scanner.nextInt() - 1;
                    if (saldirmaSecim < 0 || saldirmaSecim >= karakterler.size()) {
                        System.out.println("Geçersiz karakter seçimi.");
                    } else {
                        Karakter secilenKarakter = karakterler.get(saldirmaSecim);
                        int hasar = secilenKarakter.saldir();
                        System.out.println(secilenKarakter.getAd() + " saldırdı ve " + hasar + " hasar verdi.");
                    }
                    break;
                case 7:
                    // Karaktere hasar verme işlemleri eğer karakterin canı 0 ve altı olursa karakter silinicek
                    System.out.println("=== Karakterler ===");
                    for (int i = 0; i < karakterler.size(); i++) {
                        System.out.println((i + 1) + ". " + karakterler.get(i).getAd() + " (Can: " + karakterler.get(i).getCan() + ")");
                    }
                    System.out.print("Hasar vermek istediğiniz karakterin numarasını girin: ");
                    int hasarSecim = scanner.nextInt() - 1;
                    if (hasarSecim < 0 || hasarSecim >= karakterler.size()) {
                        System.out.println("Geçersiz karakter seçimi.");
                    } else {
                        Karakter secilenKarakter = karakterler.get(hasarSecim);
                        System.out.print("Verilecek hasar miktarı: ");
                        int hasarMiktari = scanner.nextInt();
                        secilenKarakter.hasarAl(hasarMiktari);
                        System.out.println(secilenKarakter.getAd() + " karakteri " + hasarMiktari + " hasar aldı. Kalan can: " + secilenKarakter.getCan());
                        if (secilenKarakter.getCan() <= 0) {
                            karakterler.remove(secilenKarakter);
                            System.out.println(secilenKarakter.getAd() + " karakteri öldü ve silindi.");
                        }
                    }
                    break;
                case 8:
                    // Karakter silme işlemleri
                    System.out.println("=== Karakterler ===");
                    for (int i = 0; i < karakterler.size(); i++) {
                        System.out.println((i + 1) + ". " + karakterler.get(i).getAd());
                    }
                    System.out.print("Silmek istediğiniz karakterin numarasını girin: ");
                    int silmeSecim = scanner.nextInt() - 1;
                    if (silmeSecim < 0 || silmeSecim >= karakterler.size()) {
                        System.out.println("Geçersiz karakter seçimi.");
                    } else {
                        Karakter silinenKarakter = karakterler.remove(silmeSecim);
                        System.out.println(silinenKarakter.getAd() + " karakteri silindi.");
                    }
                    break;
                case 9:
                    System.out.println("Çıkış yapılıyor...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }

        }
    }

    }
