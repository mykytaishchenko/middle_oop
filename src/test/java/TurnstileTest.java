import Options.CardType;
import Options.NumberOfDays;
import org.junit.jupiter.api.BeforeEach;

import System.Turnstile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurnstileTest {
    Turnstile turnstile = new Turnstile();

    @BeforeEach
    void setUp() {
        turnstile.getSys().createNewCard(CardType.SEASON);
        turnstile.getSys().createNewCard(CardType.WEEKENDS, NumberOfDays.TWO_DAYS);
    }

    @Test
    void check() {
        turnstile.getSys().getRegistry().get(1).block();

        assertFalse(turnstile.check(1));
        assertTrue(turnstile.check(0));
    }
}
