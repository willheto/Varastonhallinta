package varasto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author henri
 * @version 21.03.2020
 * Varastonkorjaukset-luokka, joka pitää huolta kaikista varastonkorjauksista.
 */
public class Varastonkorjaukset {

    private ArrayList<Varastonkorjaus> varastonkorjaukset = new ArrayList<Varastonkorjaus>();

    /**
     * @return varastonkorjauksien määrän
     */
    public int getLukumaara() {
        return varastonkorjaukset.size();
    }


    /**
     * @param i paikka, josta korjaus halutaan ottaa
     * @return halutussa paikassa olevan varastonkorjauksen
     */
    public Varastonkorjaus getAlkiot(int i) {
        return varastonkorjaukset.get(i);
    }


    /**
     * Pääohjelma luokan testaamiseksi
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Varastonkorjaukset varastonkorjaukset = new Varastonkorjaukset();

        Varastonkorjaus korjaus = new Varastonkorjaus();

        varastonkorjaukset.lisaa(korjaus);

    }


    /**
     * Lisää yksittäisen varastonkorjaukset korjauksiin
     * @param korjaus korjaus, joka lisätään
     * @example
     * <pre name="test">
     * Varastonkorjaukset varastonkorjaukset = new Varastonkorjaukset();
     * varastonkorjaukset.getLukumaara() === 0;
     * Varastonkorjaus korjaus = new Varastonkorjaus();
     * Varastonkorjaus korjaus2 = new Varastonkorjaus();
     * korjaus.alusta(1001, 2, "Varastonkorjaus");
     * korjaus2.alusta(1000, 3, "Varastonkorjaus");
     * varastonkorjaukset.lisaa(korjaus);
     * varastonkorjaukset.lisaa(korjaus2);
     * varastonkorjaukset.getLukumaara() === 2;
     * varastonkorjaukset.getAlkiot(0) === korjaus;
     * varastonkorjaukset.getAlkiot(1) === korjaus2;
     * </pre>
     */
    public void lisaa(Varastonkorjaus korjaus) {
        varastonkorjaukset.add(korjaus);
    }


    /**
     * Tallentaa varastonkorjaukset
     * @param nimi mihin varastoon tallennetaan
     * @throws TaynnaException jos ei aukea
     */
    public void tallenna(String nimi) throws TaynnaException {
        try (PrintStream fo = new PrintStream(new FileOutputStream(
                nimi + "/varastonkorjaukset.dat", false))) {

            for (int i = 0; i < getLukumaara(); i++) {
                Varastonkorjaus korjaus = this.getAlkiot(i);
                korjaus.tulosta(fo);

            }

        } catch (FileNotFoundException ex) {
            throw new TaynnaException("Tiedosto ei aukea!");
        }

    }


    /**
     * @param varastonNimi varasto, jonka varastonkorjaukset luetaan
     * Lukee varaston varastonkorjaukset
     */
    public void lue(String varastonNimi) {

        try (Scanner fi = new Scanner(new FileInputStream(
                new File(varastonNimi + "/varastonkorjaukset.dat")))) {

            this.varastonkorjaukset = new ArrayList<Varastonkorjaus>();

            fi.close();
            try (Scanner fi2 = new Scanner(new FileInputStream(
                    new File(varastonNimi + "/varastonkorjaukset.dat")))) {

                while (fi2.hasNextLine()) {
                    StringBuilder rivi = new StringBuilder(fi2.nextLine());
                    Varastonkorjaus korjaus = new Varastonkorjaus();

                    korjaus.alusta(Mjonot.erota(rivi, '|'),
                            Integer.valueOf(Mjonot.erota(rivi, '|')),
                            Mjonot.erota(rivi, '|'),
                            Integer.valueOf(Mjonot.erota(rivi, '|')));

                    varastonkorjaukset.add(korjaus);

                }
                fi2.close();
            }
        } catch (FileNotFoundException e) {
            File tiedosto = new File(varastonNimi);
            tiedosto.mkdir();

        }

    }

}
