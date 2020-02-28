package varasto.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import varasto.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.02.18 17:59:01 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TuoteTest {



  // Generated by ComTest BEGIN
  /** testAseta118 */
  @Test
  public void testAseta118() {    // Tuote: 118
    Tuote sanky = new Tuote(); 
    Tuote sanky2 = new Tuote(); 
    Tuote sanky3 = new Tuote(); 
    sanky.aseta(); 
    sanky2.aseta(); 
    sanky3.aseta(); 
    assertEquals("From: Tuote line: 125", sanky2.getTuotenumero() + 1, sanky3.getTuotenumero()); 
    assertEquals("From: Tuote line: 126", sanky.getTuotenumero() + 1, sanky2.getTuotenumero()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOikeellisuusTarkistus160 */
  @Test
  public void testOikeellisuusTarkistus160() {    // Tuote: 160
    Tuote sohva = new Tuote(); 
    sohva.oikeellisuusTarkistus("Patja", "Aktiivinen", 22, 50, 2); 
    assertEquals("From: Tuote line: 163", "Patja", sohva.getNimi()); 
    assertEquals("From: Tuote line: 164", 22, sohva.getVarastoarvo()); 
    assertEquals("From: Tuote line: 165", 50, sohva.getVarastokapasiteetti()); 
    assertEquals("From: Tuote line: 166", 2, sohva.getKollit()); 
    assertEquals("From: Tuote line: 167", "Aktiivinen", sohva.getStatus()); 
    sohva.oikeellisuusTarkistus("", "Aktiivinen", 22, 50, 2); 
    assertEquals("From: Tuote line: 169", "Patja", sohva.getNimi()); 
    assertEquals("From: Tuote line: 170", 22, sohva.getVarastoarvo()); 
    assertEquals("From: Tuote line: 171", 50, sohva.getVarastokapasiteetti()); 
    assertEquals("From: Tuote line: 172", 2, sohva.getKollit()); 
    assertEquals("From: Tuote line: 173", "Aktiivinen", sohva.getStatus()); 
    sohva.oikeellisuusTarkistus("SOHVA", "Poistunut", 232, 560, 24); 
    assertEquals("From: Tuote line: 175", "SOHVA", sohva.getNimi()); 
    assertEquals("From: Tuote line: 176", 232, sohva.getVarastoarvo()); 
    assertEquals("From: Tuote line: 177", 560, sohva.getVarastokapasiteetti()); 
    assertEquals("From: Tuote line: 178", 24, sohva.getKollit()); 
    assertEquals("From: Tuote line: 179", "Poistunut", sohva.getStatus()); 
  } // Generated by ComTest END
}