package varasto;

import java.util.ArrayList;

/**
 * @author henri
 * @version 29 Feb 2020
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
        korjaus.alusta(1001, 2, "Varastonkorjaus");
        Varastonkorjaus korjaus2 = new Varastonkorjaus();
        korjaus2.alusta(1001, 2, "Varastonkorjaus");
        
        varastonkorjaukset.lisaa(korjaus);
        varastonkorjaukset.lisaa(korjaus2);
        
    }
    
    
    /**
     * Lisää yksittäisen varastonkorjaukset korjauksiin
     * @param korjaus korjaus, joka lisätään
     */
    public void lisaa(Varastonkorjaus korjaus) {
        varastonkorjaukset.add(korjaus);
    }
    
    
}
