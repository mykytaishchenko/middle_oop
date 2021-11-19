package System;

import Cards.SkiPass;
import Cards.SkiPassByRides;
import Cards.SkiPassByTime;
import Options.CardType;

import java.time.LocalDate;

public class Turnstile {
    private final SkiPassSystem sys;

    public Turnstile() {
        sys = new SkiPassSystem();
    }

    private boolean isDateGreater(LocalDate date1, LocalDate date2) {
        return (date1.getYear() >= date2.getYear() && date1.getDayOfYear() >= date2.getDayOfYear());
    }

    public boolean check(SkiPass card) {
        if (!card.isBlocked()) {
            if (card instanceof SkiPassByTime) {
                if (card.getType() == CardType.SEASON ||
                        (LocalDate.now().getDayOfWeek().getValue() >= 6 && card.getType() == CardType.WEEKENDS) ||
                        (LocalDate.now().getDayOfWeek().getValue() < 6 && card.getType() == CardType.WEEKDAYS)) {
                    if (isDateGreater(((SkiPassByTime) card).getEndData(),
                            LocalDate.now())) {
                        // Some logs
                        return true;
                    }
                }
                sys.block(card);
            } else if (card instanceof SkiPassByRides) {
                if ((LocalDate.now().getDayOfWeek().getValue() >= 6 && card.getType() == CardType.WEEKENDS) ||
                        (LocalDate.now().getDayOfWeek().getValue() < 6 && card.getType() == CardType.WEEKDAYS)) {
                    if (((SkiPassByRides) card).getRides() > 0) {
                        // Some logs
                        ((SkiPassByRides) card).useOneRide();
                        return true;
                    }
                }
                sys.block(card);
            }
        }
        return false;
    }
}
