/**
 * 
 */
package varasto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author henri
 * @version 21.03.2020
 * Tuotteet-luokka
 * - osaa lisätä ja poistaa tuotteita
 * - lukee ja kirjoittaa tietyn tuotteen tiedostoon käyttämällä avustajia -TODO
 * - osaa etsiä ja lajitella 
 */
public class Tuotteet {

    private Tuote[] tuotteetTaulukossa = new Tuote[MAX_TUOTTEET];
    private static final int MAX_TUOTTEET = 5;
    private int lukumaara = 0;

    // TÄMÄN YLÄPUOLELLA VAIN ATTRIBUUTIT
    // <-------------------------------------------------------------------------------------------------------------->
    // ALAPUOLELLA GETTERIT

    /**
     * @param i indeksi
     * @return tuotteen alkio
     * @throws IndexOutOfBoundsException jos i ei ole alueella
     */
    public Tuote getAlkiot(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lukumaara < 1)
            throw new IndexOutOfBoundsException("Laitoin indeksi: " + i);
        return tuotteetTaulukossa[i];
    }


    /**
     * @return tuotteiden lukumäärä
     */
    public int getLukumaara() {
        return lukumaara;
    }

    // TÄMÄN YLÄPUOLELLA ATTRIBUUTIT JA GETTERIT
    // <-------------------------------------------------------------------------------------------------------------->
    // ALAPUOLELLA METODIT


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

            // patja.oikeellisuusTarkistus("Patja1", "Aktiivinen", 22, 50, 2);

            // patja2.oikeellisuusTarkistus("Patja2", "Postunut", 22, 50, 2);

            // patja3.oikeellisuusTarkistus("Patja3", "Aktiivinen", 22, 50, 2);

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
        if (lukumaara >= MAX_TUOTTEET)
            throw new TaynnaException("Liikaa tuotteita!");

        tuotteetTaulukossa[lukumaara] = tuote;
        lukumaara++;

    }


    /**
     * @param tuote poistettava tuote
     * Poistaa tuotteen
     */
    public void poistaTuote(Tuote tuote) {

        for (int i = 0; i < tuotteetTaulukossa.length; i++) {
            if (tuotteetTaulukossa[i] == tuote) {
                tuotteetTaulukossa[i] = null;
                for (int j = i; j < tuotteetTaulukossa.length; j++) {
                    try {
                        tuotteetTaulukossa[j] = tuotteetTaulukossa[j + 1];
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        }

        lukumaara--;
    }


    /**
     * Muuttaa tuotteen varastoarvoa
     * @param tuote tuote jonka varastoarvoa muutetaan
     * @param muutos muutoksen määrä
     * @example
     * <pre name="test">
     * Tuotteet tuotteet = new Tuotteet();
     * Tuote tuote = new Tuote();
     * tuote.getVarastoarvo() === 0;
     * tuotteet.muutaVarastoarvoa(tuote, -2);
     * tuote.getVarastoarvo() === -2;
     * tuotteet.muutaVarastoarvoa(tuote, 2);
     * tuote.getVarastoarvo() === 0;
     * try {
     * tuotteet.lisaa(tuote);
     * } catch(Exception e) {
     * System.out.println("ei toimi");
     * }
     * tuotteet.getLukumaara() === 1;
     * tuotteet.getAlkiot(0) === tuote;
     * Tuote tuote2 = new Tuote();
     * Tuote tuote3 = new Tuote();
     * Tuote tuote4 = new Tuote();
     * Tuote tuote5 = new Tuote();
     * try {
     * tuotteet.lisaa(tuote2);
     * tuotteet.lisaa(tuote3);
     * tuotteet.lisaa(tuote4);
     * tuotteet.lisaa(tuote5);
     * } catch(Exception e) {
     * System.out.println("ei toimi");
     * }
     * tuotteet.getLukumaara() === 5;
     * </pre>
     */
    public void muutaVarastoarvoa(Tuote tuote, int muutos) {
        tuote.muutaVarastoarvoa(muutos);
    }


    /**
     * Tallentaa tuotteet
     * @param nimi mihin varastoon tallennetaan
     * @throws TaynnaException jos ei aukea
     */
    public void tallenna(String nimi) throws TaynnaException {

        File tiedosto = new File(nimi);
        if (!tiedosto.exists())
            tiedosto.mkdir();
        try (PrintStream fo = new PrintStream(
                new FileOutputStream(nimi + "/tuotteet.dat", false))) {
            try {
                fo.println(tuotteetTaulukossa[0].getSeuraavaTuoteNumero());
            } catch (Exception e) {
                fo.println(1);
            }
            for (int i = 0; i < lukumaara; i++) {
                tuotteetTaulukossa[i].tulostaTiedot(fo);
            }

        } catch (FileNotFoundException ex) {
            throw new TaynnaException("Tiedosto ei aukea!");
        }
    }


    /**
     * @param varastonNimi varaston nimi
     * Lukee varaston tuotteet listaan
     */
    public void lue(String varastonNimi) {

        try (Scanner fi = new Scanner(new FileInputStream(
                new File(varastonNimi + "/tuotteet.dat")))) {

            String numero = fi.nextLine().toString();

            int montakoTuotetta = 0;

            while (fi.hasNextLine()) {
                montakoTuotetta++;
                System.out.println(fi.nextLine());
            }

            this.tuotteetTaulukossa = new Tuote[montakoTuotetta + 5];
            this.lukumaara = montakoTuotetta;

            fi.close();
            try (Scanner fi2 = new Scanner(new FileInputStream(
                    new File(varastonNimi + "/tuotteet.dat")))) {
                fi2.nextLine();

                int apuindeksi = 0;
                while (fi2.hasNextLine()) {
                    StringBuilder rivi = new StringBuilder(fi2.nextLine());
                    Tuote tuote = new Tuote();

                    tuote.oikeellisuusTarkistus(Mjonot.erota(rivi, '|'),
                            Integer.valueOf(Mjonot.erota(rivi, '|')),
                            Integer.valueOf(Mjonot.erota(rivi, '|')),
                            Integer.valueOf(Mjonot.erota(rivi, '|')),
                            Integer.valueOf(Mjonot.erota(rivi, '|')),
                            Mjonot.erota(rivi, '|'));
                    tuotteetTaulukossa[apuindeksi] = tuote;
                    apuindeksi++;

                    tuotteetTaulukossa[0]
                            .setSeuraavaTuoteNumero(Integer.valueOf(numero));

                }
                fi2.close();
            }

        } catch (FileNotFoundException e) {
            File tiedosto = new File(varastonNimi);
            tiedosto.mkdir();

        }

    }

}
