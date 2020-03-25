package varasto;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author henri willman, henri.t.willman@student.jyu.fi
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
     * @return varastonkorjauksien määrän
     */
    public int getKenttia() {
        return 4;
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
      
        korjaus.tulosta(System.out);
        korjaus2.tulosta(System.out);

    }


    /**
     * Tulostaa varastonkorjauksen tiedot
     * @param os mihin tulostetaan
     */
    public void tulosta(PrintStream os) {
        os.println(paiva + "|" + tuotenumero + "|" + tapahtuma + "|" + muutos + "|");
    }


    /**
     * Alustaa varastonkorjauksen tiedot
     * @param muutos2 montako lisättiin tai poistettiin
     * @param tapahtuma2 miksi varastoa korjattiin
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
    public void alusta(int tuotenumero2, String tapahtuma2, int muutos2) {        
              
        SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy '-' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        this.tuotenumero = tuotenumero2;
        this.muutos = muutos2;
        this.tapahtuma = tapahtuma2;
        this.paiva = formatter.format(date);
        this.vkid = seuraavaVkid;

        seuraavaVkid++;
    }


    /**
     * @param paiva2 päivämäärä korjaukselle
     * @param tuotenumero2 korjattavan tuotteen tuotenumero
     * @param tapahtuma2 miksi korjattiin eli syykoodi
     * @param muutos2 montako korjattiin (+ tai -)
     * Asettaa korjaukselle tiedot
     */
    public void alusta(String paiva2, Integer tuotenumero2, String tapahtuma2, Integer muutos2) {
        this.tuotenumero = tuotenumero2;
        this.muutos = muutos2;
        this.tapahtuma = tapahtuma2;
        this.paiva = paiva2;
        this.vkid = seuraavaVkid;

        seuraavaVkid++;      
    }
}
