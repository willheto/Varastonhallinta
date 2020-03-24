/**
 * 
 */
package varasto;

import java.io.PrintStream;

/**
 * @author henri
 * @version 21.03.2020
 * Tuote-luokka
 * - Tietää tuotteiden tiedot ja osaa tarkastaa, ettei väärään kenttään syötetä 
 * väärää tietoa. (esim. kirjaimia varastoaarvoon)  - TEHTY
 * - osaa muuttaa palkeilla paloitellut merkkijonot  - TODO
 * erillisiksi tiedoiksi(1|sohva sopiva|...) -> 1, sohva sopiva, ...                          
 * - osaa antaa merkkijonona tietyn kentän tiedot   - TEHTY  
 * - osaa laittaa merkkijono kentän tiedoksi       - TEHTY
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
    
    
    /**
     * @return seuraavanTuotenumeron
     */
    public int getSeuraavaTuoteNumero() {
        return seuraavaTuoteNumero;
    }
    
    
    /**
     * Asettaa luetun perusteella uuden seuraavan tuotenumeron
     * @param numero mikä asetetaan
     */
    public void setSeuraavaTuoteNumero(int numero) {
        seuraavaTuoteNumero = numero;
    }

    // TÄMÄN YLÄPUOLELLA VAIN GETTERIT JA ATTRIBUUTIT
    // <-------------------------------------------------------------------------------------------------------------->
    // ALAPUOLELLA METODEJA

    /**
     * @param args ei käytössä
     * Pääohjelma tuotteet-luokan testaamista varten
     */
    public static void main(String[] args) {
        Tuote tuote = new Tuote();
        Tuote tuote2 = new Tuote();

        tuote.tulostaTiedot(System.out);
        tuote2.tulostaTiedot(System.out);

    }


    /**
     * Tulostaa tuotteen tiedot
     * @param os printstreami 
     */
    public void tulostaTiedot(PrintStream os) {
        os.println(nimi + "|" + 
                + tuotenumero + "|" + varastoarvo + "|"
                + varastokapasiteetti + "|"
                + kollit + "|" + status
                + "|");

    }
    
    
    /**
     * Muuttaa varastoarvoa tuotteesta
     * @param muutos muutoksen määrä(esim -5 tai 7)
     */
    public void muutaVarastoarvoa(int muutos) {
        this.varastoarvo += muutos;
    }


    /**
     * Asettaa tuotteelle tiedot
     * @param ehdokasNimi tuotteen nimi jota tarjotaan
     * @param kapasi tarjottavan tuotteen kapasiteetti varastossa
     * @param ehdokasKollit tuotteen kollit
     * @param ehdokasStatus tuotteen status
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
     * </pre>
     */
    public void aseta(String ehdokasNimi, int kapasi, int ehdokasKollit, String ehdokasStatus) {

        oikeellisuusTarkistus(ehdokasNimi, seuraavaTuoteNumero, 0, kapasi, ehdokasKollit, ehdokasStatus);
        seuraavaTuoteNumero++;

    }
    
    
    /**
     * @param ehdokasNimi tuotteen nimi jota tarjotaan
     * @param kapasi tarjottavan tuotteen kapasiteetti varastossa
     * @param ehdokasKollit tuotteen kollit
     * @param ehdokasStatus tuotteen status
     * @param nolla kuormituksen apumuuttuja
     */
    public void aseta(String ehdokasNimi, int kapasi, int ehdokasKollit, String ehdokasStatus, int nolla) {

        oikeellisuusTarkistus(ehdokasNimi, this.tuotenumero, this.varastoarvo, kapasi, ehdokasKollit, ehdokasStatus);

    }


    /**
     * Täyttää tiedot jos tiedot menevät läpi oikeellisuustarkastuksesta
     * @param tuotenumero 
     */
    private void taytaTiedot(String ehdokasNimi, String ehdokasStatus, int ehdokasVarastoarvo, int ehdokasVarastokapasiteetti, int ehdokasKollit, int ehdokasTuotenumero) {
        nimi = ehdokasNimi;
        varastoarvo = ehdokasVarastoarvo;
        varastokapasiteetti = ehdokasVarastokapasiteetti;
        kollit = ehdokasKollit;
        status = ehdokasStatus;
        tuotenumero = ehdokasTuotenumero;
    }


    /**
     * Tekee oikeellisuustarkastuksen
     * @param ehdokasNimi testattava nimi
     * @param ehdokasTuotenumero tuotenumero
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
    public void oikeellisuusTarkistus(String ehdokasNimi, int ehdokasTuotenumero,
            int ehdokasVarastoarvo, int ehdokasVarastokapasiteetti,
            int ehdokasKollit, String ehdokasStatus) {

        String[] merkkijonot = { ehdokasNimi, ehdokasStatus };
        int[] luvut = { ehdokasVarastoarvo, ehdokasVarastokapasiteetti,
                ehdokasKollit };

        for (int i = 0; i < merkkijonot.length; i++) {
            if (merkkijonot[i] == null || merkkijonot[i] == "") {
                return;
            }
        }

        for (int i = 0; i < luvut.length; i++) {
            if (luvut[i] < 0) {
                return;
            }
        }
        
        if(ehdokasStatus.contains("Aktiivinen") || ehdokasStatus.contains("Poistunut")) {
            taytaTiedot(ehdokasNimi, ehdokasStatus, ehdokasVarastoarvo,
                    ehdokasVarastokapasiteetti, ehdokasKollit, ehdokasTuotenumero);
        }

    }

}
