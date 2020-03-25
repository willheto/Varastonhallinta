package varasto;

/**
 * @author henri willman, henri.t.willman@student.jyu.fi
 * @version 21.03.2020
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
