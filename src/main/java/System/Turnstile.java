package System;

import Cards.SkiPass;
import Cards.SkiPassByRides;
import Cards.SkiPassByTime;
import Options.CardType;
import lombok.Getter;

import java.time.LocalDate;

public class Turnstile {
    @Getter
    private final SkiPassSystem sys;

    public Turnstile() {
        sys = new SkiPassSystem();
    }

    private boolean isDateGreater(LocalDate date1, LocalDate date2) {
        return (date1.getYear() >= date2.getYear() && date1.getDayOfYear() >= date2.getDayOfYear());
    }

    public boolean check(int cardNumberInRegistry) {
        return check(getSys().getRegistry().get(cardNumberInRegistry));
    }

    public boolean check(SkiPass card) {
        int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
        if (!card.isBlocked()) {
            if (card instanceof SkiPassByTime) {
                if (card.getType() == CardType.SEASON ||
                        (dayOfWeek >= 6 && card.getType() == CardType.WEEKENDS) ||
                        (dayOfWeek < 6 && card.getType() == CardType.WEEKDAYS)) {
                    if (isDateGreater(((SkiPassByTime) card).getEndData(),
                            LocalDate.now())) {
                        // Some logs
                        return true;
                    }
                }
                sys.block(card); // due to use on the wrong days
            } else if (card instanceof SkiPassByRides) {
                if ((dayOfWeek >= 6 && card.getType() == CardType.WEEKENDS) ||
                        (dayOfWeek < 6 && card.getType() == CardType.WEEKDAYS)) {
                    if (((SkiPassByRides) card).getRides() > 0) {
                        // Some logs
                        ((SkiPassByRides) card).useOneRide();
                        return true;
                    }
                }
                sys.block(card); // due to use on the wrong days
            }
        }
        return false;
    }
}
