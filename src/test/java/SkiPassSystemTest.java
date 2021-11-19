import Cards.SkiPass;
import Cards.SkiPassByRides;
import Cards.SkiPassByTime;
import Options.CardType;
import Options.NumberOfDays;
import Options.NumberOfRides;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import System.SkiPassSystem;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class SkiPassSystemTest {
    SkiPassSystem sys = new SkiPassSystem();
    SkiPass card;

    @BeforeEach
    void setUp() {
        sys.createNewCard(CardType.WEEKDAYS, NumberOfRides.FIFTY_RIDER);
        sys.createNewCard(CardType.WEEKENDS, NumberOfDays.TWO_DAYS);
    }

    @Test
    void createCard() {
        card = sys.getRegistry().get(0);
        assertEquals(card.getType(), CardType.WEEKDAYS);
        assertTrue(card instanceof SkiPassByRides);
        assertEquals(((SkiPassByRides) card).getRides(), 50);

        card = sys.getRegistry().get(1);
        assertEquals(card.getType(), CardType.WEEKENDS);
        assertTrue(card instanceof SkiPassByTime);
        assertEquals(((SkiPassByTime) card).getEndData(), LocalDate.ofYearDay(LocalDate.now().getYear(),
                LocalDate.now().getDayOfYear() + 1));
    }

    @Test
    void block() {
        card = sys.getRegistry().get(0);
        assertFalse(card.isBlocked());
        card.block();
        assertTrue(card.isBlocked());
    }
}
