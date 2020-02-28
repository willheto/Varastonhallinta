package varasto;

/**
 * @author henri
 * @version 17.2.2020
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille
 */
public class TaynnaException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Poikkeuksen muodostaja
     * @param viesti poikkeuksen viesti
     */
    public TaynnaException(String viesti) {
        super(viesti);
    }

}
