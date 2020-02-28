package varasto;

/**
 * Varasto-luokka
 * @author henri
 * @version 18.2.2020
 *
 */
public class Varasto {
    
    Tuotteet tuotteet = new Tuotteet();
    
    
    /**
     * @return tuotemäärä
     */
    public int getTuotteita() {
        return tuotteet.getLukumaara();
    }
    
    /**
     * @param i monesko tuote palautetaan
     * @return viite i:teen tuotteeseen
     */
    public Tuote annaTuote(int i) {
        return tuotteet.getAlkiot(i);
    }
    
    
    /**
     * Pääohjelma testaamiseksi
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Varasto varasto = new Varasto();
        
        Tuote tyyny = new Tuote();
        Tuote tyyny2 = new Tuote();
        Tuote tyyny3 = new Tuote();
        tyyny.aseta();
        tyyny2.aseta();
        tyyny3.aseta();
        
        try {
            varasto.lisaa(tyyny);
            varasto.lisaa(tyyny2);
            varasto.lisaa(tyyny3);

        } catch (TaynnaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        for (int i = 0; i < varasto.getTuotteita(); i++) {
            Tuote tuote = varasto.annaTuote(i);
            System.out.println("Tuote paikassa: " + i);
            tuote.tulostaTiedot(System.out);
        }
        
    }
    
    /**
     * Lisää varastoon uuden tuotteen
     * @param tuote lisättävä tuote
     * @throws TaynnaException jos ei mahdu
     * @example
     * <pre name="test">
     * #THROWS TaynnaException
     * Varasto varasto = new Varasto();
     * Tuote tyyny = new Tuote();
     * Tuote tyyny2 = new Tuote();
     * Tuote tyyny3 = new Tuote();
     * tyyny.aseta();
     * tyyny2.aseta();
     * tyyny3.aseta();
     * tuotteet.getTuotteita() === 0;
     * varasto.lisaa(tyyny); kerho.getJasenia() === 1;
     * varasto.lisaa(tyyny2); kerho.getJasenia() === 2;
     * varasto.lisaa(tyyny3); kerho.getJasenia() === 3;
     * varasto.annaTuote(0) === tyyny;
     * varasto.annaTuote(1) === tyyny2;
     * varasto.annaTuote(2) === tyyny3;
     * varasto.annaTuote(3) === tyyny; #THROWS IndexOutOfBoundsException
     */
    public void lisaa(Tuote tuote) throws TaynnaException {
       tuotteet.lisaa(tuote);
    }
}
