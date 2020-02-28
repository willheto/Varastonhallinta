/**
 * 
 */
package varasto;

import java.io.PrintStream;

/**
 * @author henri
 * @version 17.2.2020
 * Tuote-luokka
 * - Tietää tuotteiden tiedot ja osaa tarkastaa, ettei väärään kenttään syötetä 
 * väärää tietoa. (esim. kirjaimia varastoaarvoon)  - TEHTY
 * - osaa muuttaa palkeilla paloitellut merkkijonot  - TODO
 * erillisiksi tiedoiksi(1|sohva sopiva|...) -> 1, sohva sopiva, ...                          
 * - osaa antaa merkkijonona tietyn kentän tiedot   - TEHTY  
 * - osaa laittaa merkkijono kentän tiedoksi       - TODO 
 * - osaa kommunikoida varastonkorjauksen kanssa,   - TODO
 *  kun tuotteen tilaksi merkataan poistunut        
 */
public class Tuote {

    private String nimi;
    private int tuotenumero;
    private int varastoarvo;
    private int varastokapasiteetti;
    private int kollit;
    private String status;

    private static int seuraavaTuoteNumero = 1;

    // TÄMÄN YLÄPUOLELLA VAIN ATTRIBUUTIT
    // <-------------------------------------------------------------------------------------------------------------->
    // ALAPUOLELLA GETTERIT

    /**
     * @return tuotteen nimen
     */
    public String getNimi() {
        return this.nimi;
    }


    /**
     * @return tuotteen tuotenumeron
     */
    public int getTuotenumero() {
        return this.tuotenumero;
    }


    /**
     * @return tuotteen varastoarvon
     */
    public int getVarastoarvo() {
        return this.varastoarvo;
    }


    /**
     * @return tuotteen varastokapasiteetin
     */
    public int getVarastokapasiteetti() {
        return this.varastokapasiteetti;
    }


    /**
     * @return tuotteen kollimäärän
     */
    public int getKollit() {
        return this.kollit;
    }


    /**
     * @return tuotteen statuksen
     */
    public String getStatus() {
        return this.status;
    }

    // TÄMÄN YLÄPUOLELLA VAIN GETTERIT JA ATTRIBUUTIT
    // <-------------------------------------------------------------------------------------------------------------->
    // ALAPUOLELLA METODEJA


    /**
     * @param args ei käytössä
     * Pääohjelma testaamista varten
     */
    public static void main(String[] args) {
        Tuote tuote = new Tuote();
        Tuote tuote2 = new Tuote();

        tuote.aseta();
        tuote2.aseta();

        tuote.tulostaTiedot(System.out);
        tuote2.tulostaTiedot(System.out);

    }


    /**
     * Tulostaa tuotteen tiedot
     * @param os printstreami 
     */
    public void tulostaTiedot(PrintStream os) {
        os.println("Nimi: " + nimi + "\n" + "Tuotenumero: "
                + tuotenumero + "\n" + "Varastoarvo: " + varastoarvo + "\n"
                + "Varastokapasiteetti: " + varastokapasiteetti + "\n"
                + "Kollien määrä: " + kollit + "\n" + "Status: " + status
                + "\n");

    }


    /**
     * Asetta tuotteelle tiedot
     * @example
     * <pre name="test">
     * Tuote sanky = new Tuote();
     * Tuote sanky2 = new Tuote();
     * Tuote sanky3 = new Tuote();
     * sanky.aseta();
     * sanky2.aseta();
     * sanky3.aseta();
     * sanky3.getTuotenumero() === sanky2.getTuotenumero() + 1;
     * sanky2.getTuotenumero() === sanky.getTuotenumero() + 1;
     * 
     * </pre>
     */
    public void aseta() {
        this.tuotenumero = seuraavaTuoteNumero;
        seuraavaTuoteNumero++;

        oikeellisuusTarkistus("Patja", "Aktiivinen", 22, 50, 2);
    }


    /**
     * Täyttää tiedot jos tiedot menevät läpi oikeellisuustarkastuksesta
     */
    private void taytaTiedot(String ehdokasNimi, String ehdokasStatus,
            int ehdokasVarastoarvo, int ehdokasVarastokapasiteetti,
            int ehdokasKollit) {
        nimi = ehdokasNimi;
        varastoarvo = ehdokasVarastoarvo;
        varastokapasiteetti = ehdokasVarastokapasiteetti;
        kollit = ehdokasKollit;
        status = ehdokasStatus;
    }


    /**
     * Tekee oikeellisuustarkastuksen
     * TODO: tee vielä tarkastus, ettei tuotenumero tai nimi ole jo käytössä! Varastoarvo ei saa myöskään ylittää kapasiteettia!
     * TODO: kirjoita vielä noille testit!!!
     * @param ehdokasNimi testattava nimi
     * @param ehdokasStatus testattava status
     * @param ehdokasVarastoarvo testattava varastoarvo
     * @param ehdokasVarastokapasiteetti testattava varastokapasiteetti
     * @param ehdokasKollit testattava kollien määrä
     * @example
     * <pre name="test">
     *  Tuote sohva = new Tuote();
     *  sohva.oikeellisuusTarkistus("Patja", "Aktiivinen", 22, 50, 2);   
     *  sohva.getNimi() === "Patja";
     *  sohva.getVarastoarvo() === 22;
     *  sohva.getVarastokapasiteetti() === 50;
     *  sohva.getKollit() === 2;
     *  sohva.getStatus() === "Aktiivinen";
     *  sohva.oikeellisuusTarkistus("", "Aktiivinen", 22, 50, 2);   
     *  sohva.getNimi() === "Patja";
     *  sohva.getVarastoarvo() === 22;
     *  sohva.getVarastokapasiteetti() === 50;
     *  sohva.getKollit() === 2;
     *  sohva.getStatus() === "Aktiivinen";
     *  sohva.oikeellisuusTarkistus("SOHVA", "Poistunut", 232, 560, 24);   
     *  sohva.getNimi() === "SOHVA";
     *  sohva.getVarastoarvo() === 232;
     *  sohva.getVarastokapasiteetti() === 560;
     *  sohva.getKollit() === 24;
     *  sohva.getStatus() === "Poistunut";
     * </pre>
     */
    public void oikeellisuusTarkistus(String ehdokasNimi, String ehdokasStatus,
            int ehdokasVarastoarvo, int ehdokasVarastokapasiteetti,
            int ehdokasKollit) {

        String[] merkkijonot = { ehdokasNimi, ehdokasStatus };
        int[] luvut = { ehdokasVarastoarvo, ehdokasVarastokapasiteetti,
                ehdokasKollit };

        for (int i = 0; i < merkkijonot.length; i++) {
            if (merkkijonot[i] == null || merkkijonot[i] == "") {
                return;
            }
        }

        for (int i = 0; i < luvut.length; i++) {
            if (luvut[i] == 0) {
                return;
            }
        }
        taytaTiedot(ehdokasNimi, ehdokasStatus, ehdokasVarastoarvo,
                ehdokasVarastokapasiteetti, ehdokasKollit);
    }

}
