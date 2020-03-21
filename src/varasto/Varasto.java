package varasto;

/**
 * Varasto-luokka
 * @author henri
 * @version 21.03.2020
 *
 */
public class Varasto {

    Tuotteet tuotteet = new Tuotteet();
    Varastonkorjaukset varastonkorjaukset = new Varastonkorjaukset();

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
     * @return varastonkorjauksien lukumäärän
     */
    public int getVarastonkorjauksia() {
        return varastonkorjaukset.getLukumaara();
    }


    /**
     * @param i paikka, josta korjaus halutaan ottaa
     * @return halutussa paikassa olevan varastonkorjauksen
     */
    public Varastonkorjaus annaKorjaus(int i) {
        return varastonkorjaukset.getAlkiot(i);
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

        Varastonkorjaus korjaus = new Varastonkorjaus();
        korjaus.alusta(1, 2, "Varastonkorjaus");
        varasto.lisaaKorjaus(korjaus);

        for (int i = 0; i < varasto.getTuotteita(); i++) {
            Tuote tuote = varasto.annaTuote(i);
            System.out.println("Tuote paikassa: " + i);
            tuote.tulostaTiedot(System.out);
        }

    }


    /**
     * Lisää varastonkorjauksen korjauksiin
     * @param korjaus lisättävä korjaus
     */
    public void lisaaKorjaus(Varastonkorjaus korjaus) {

        for (int i = 0; i < getTuotteita(); i++) {
            Tuote tuote = annaTuote(i);
            if (tuote.getTuotenumero() == korjaus.getTuotenumero()) {
                varastonkorjaukset.lisaa(korjaus);
                tuotteet.muutaVarastoarvoa(tuote, korjaus.getMuutos());
            }
        }

        // System.err.println("Tuotetta ei ole olemassa!");
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
     * varasto.getTuotteita() === 0;
     * varasto.lisaa(tyyny); varasto.getTuotteita() === 1;
     * varasto.lisaa(tyyny2); varasto.getTuotteita() === 2;
     * varasto.lisaa(tyyny3); varasto.getTuotteita() === 3;
     * varasto.annaTuote(0) === tyyny;
     * varasto.annaTuote(1) === tyyny2;
     * varasto.annaTuote(2) === tyyny3;
     * varasto.annaTuote(3) === null;
     */
    public void lisaa(Tuote tuote) throws TaynnaException {
        tuotteet.lisaa(tuote);
    }
}
