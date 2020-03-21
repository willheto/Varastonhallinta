package varasto;

import java.io.PrintStream;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

/**
 * @author henri
 * @version 21.03.2020
 * Varastonkorjaus-luokka
 */
public class Varastonkorjaus {

    private int tuotenumero;
    private int vkid;
    private int muutos;
    private String tapahtuma;
    private String paiva;

    private static int seuraavaVkid = 1;

    /**
     * @return tuotenumero
     */
    public int getTuotenumero() {
        return tuotenumero;
    }


    /**
     * @return vkid
     */
    public int getVkid() {
        return vkid;
    }


    /**
     * @return muutos
     */
    public int getMuutos() {
        return muutos;
    }


    /**
     * @return tapahtuma
     */
    public String getTapahtuma() {
        return tapahtuma;
    }


    /**
     * @return paiva
     */
    public String getPaiva() {
        return paiva;
    }
    
    //                                  TÄMÄN YLÄPUOLELLA ATTRIBUUTIT JA GETTERIT
    //<-------------------------------------------------------------------------------------------------------------->
    //                                           ALAPUOLELLA METODIT

    /**
     * Pääohjelma varastonkorjaus-luokan testaamiseksi
     * @param args ei käytössä
     */
    public static void main(String[] args) {
            
        Varastonkorjaus korjaus = new Varastonkorjaus();
        Varastonkorjaus korjaus2 = new Varastonkorjaus();

        korjaus.alusta(1001, 2, "Varastonkorjaus");
        korjaus2.alusta(1001, 2, "Varastonkorjaus");

        
        korjaus.tulosta(System.out);
        korjaus2.tulosta(System.out);

    }


    /**
     * Tulostaa varastonkorjauksen tiedot
     * @param os mihin tulostetaan
     */
    public void tulosta(PrintStream os) {
        os.println("<--------------------------------------------------------->\n" +"Päivämäärä: " + this.getPaiva() + "\n" +
                            "Muutos: " + this.getMuutos() + "\n" +
                            "Selitys: " + this.getTapahtuma() +
                            "\n<--------------------------------------------------------->\n");
 
    }


    /**
     * Alustaa varastonkorjauksen tiedot
     * @param tapahtuma2 miksi varastoa korjattiin
     * @param muutos2 montako lisättiin tai poistettiin
     * @param tuotenumero2 korjatun tuotteen tuotenumero
     * @example
     * <pre name="test">
     * Varastonkorjaus korjaus = new Varastonkorjaus();
     * Varastonkorjaus korjaus2 = new Varastonkorjaus();
     * korjaus.alusta(1, 2, "Varastonkorjaus");
     * korjaus.getTuotenumero() === 1;
     * korjaus.getPaiva() === "29.02.2020";
     * korjaus.getMuutos() === 2;
     * korjaus.getTapahtuma() === "Varastonkorjaus";
     * korjaus.getVkid() === 1;
     * korjaus2.alusta(1, 2, "Varastonkorjaus");
     * korjaus2.getTuotenumero() === 1;
     * korjaus2.getPaiva() === "29.02.2020";
     * korjaus2.getMuutos() === 2;
     * korjaus2.getTapahtuma() === "Varastonkorjaus";
     * korjaus2.getVkid() === 2;
     * </pre>
     */
    public void alusta(int tuotenumero2, int muutos2, String tapahtuma2) {        
        
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        //LocalDate paivamaara = LocalDate.now();

        this.tuotenumero = tuotenumero2;
        this.muutos = muutos2;
        this.tapahtuma = tapahtuma2;
        this.paiva = "29.02.2020";
        this.vkid = seuraavaVkid;

        seuraavaVkid++;
    }
}
