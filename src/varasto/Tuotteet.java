/**
 * 
 */
package varasto;

/**
 * @author henri
 * @version 17.2.2020
 * Tuotteet-luokka
 * - osaa lisätä ja poistaa tuotteita
 * - lukee ja kirjoittaa tietyn tuotteen tiedostoon käyttämällä avustajia -TODO
 * - osaa etsiä ja lajitella 
 */
public class Tuotteet {
    
    private Tuote[] tuotteetTaulukossa = new Tuote[MAX_TUOTTEET];
    private static final int MAX_TUOTTEET = 5;
    private int lukumaara = 0;
    
    //                                      TÄMÄN YLÄPUOLELLA VAIN ATTRIBUUTIT
    //<-------------------------------------------------------------------------------------------------------------->
    //                                              ALAPUOLELLA GETTERIT   
    
    /**
     * @param i indeksi
     * @return tuotteen alkio
     * @throws IndexOutOfBoundsException jos i ei ole alueella
     */
    public Tuote getAlkiot(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lukumaara < 1) throw new IndexOutOfBoundsException("Laitoin indeksi: " + i);
        return tuotteetTaulukossa[i];
    }
    
    
    /**
     * @return tuotteiden lukumäärä
     */
    public int getLukumaara() {
        return lukumaara;
    }
    
   
    
   
    //                                  TÄMÄN YLÄPUOLELLA ATTRIBUUTIT JA GETTERIT
    //<-------------------------------------------------------------------------------------------------------------->
    //                                           ALAPUOLELLA METODIT

    
    /**
     * Oletusmuodostaja
     */
    public Tuotteet() {
        //
    }
    
    
    /**
     * @param args ei käytössä
     * Pääohjelma testaamista varten
     */
    public static void main(String[] args) {
        Tuotteet tuotteet = new Tuotteet();
        
        Tuote patja = new Tuote(), patja2 = new Tuote(), patja3 = new Tuote();
        
        try {
            tuotteet.lisaa(patja);
            tuotteet.lisaa(patja2);
            tuotteet.lisaa(patja3);
        
            patja.oikeellisuusTarkistus("Patja1", "Aktiivinen", 22, 50, 2);    
        
            patja2.oikeellisuusTarkistus("Patja2", "Postunut", 22, 50, 2);    
        
            patja3.oikeellisuusTarkistus("Patja3", "Aktiivinen", 22, 50, 2);    
        
            for (int i = 0; i < tuotteet.getLukumaara(); i++) {
                Tuote testattava = tuotteet.getAlkiot(i);
                testattava.tulostaTiedot(System.out);

            }
        } catch (TaynnaException ex) {
            System.err.println(ex.getMessage());
        }
        

        
    }
    
    
    /**
     * @param tuote lisättävä tuote
     * Lisää tuotteen tuotteet-taulukkoon
     * @throws TaynnaException jos tuotteita ollaan laittamassa liikaa
     */
    public void lisaa(Tuote tuote) throws TaynnaException {
        if(lukumaara >= MAX_TUOTTEET) throw new TaynnaException("Liikaa tuotteita!");
        
        tuotteetTaulukossa[lukumaara] = tuote;
        lukumaara++;
        
    }
    
    
    /**
     * Muuttaa tuotteen varastoarvoa
     * @param tuote tuote jonka varastoarvoa muutetaan
     * @param muutos muutoksen määrä
     */
    public void muutaVarastoarvoa(Tuote tuote, int muutos) {
        tuote.muutaVarastoarvoa(muutos);
    }


}
