package System;

import Cards.SkiPassByRides;
import Cards.SkiPassByTime;
import Options.CardType;
import Cards.SkiPass;
import Options.NumberOfDays;
import Options.NumberOfRides;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkiPassSystem {
    private int ID = 0;
    List<SkiPass> registry = new ArrayList<>();
    List<Integer> blocked = new ArrayList<>();
    private final HashMap<NumberOfDays, Integer> daysToInt = new HashMap<>();
    private final HashMap<NumberOfRides, Integer> ridesToInt = new HashMap<>();


    public SkiPassSystem() {
        daysToInt.put(NumberOfDays.ONE_DAY, 1);
        daysToInt.put(NumberOfDays.TWO_DAYS, 2);
        daysToInt.put(NumberOfDays.FIVE_DAYS, 3);
        ridesToInt.put(NumberOfRides.TEN_RIDES, 10);
        ridesToInt.put(NumberOfRides.TWENTY_RIDES, 20);
        ridesToInt.put(NumberOfRides.FIFTY_RIDER, 50);
        ridesToInt.put(NumberOfRides.ONE_HUNDRED_RIDES, 100);
    }

    public boolean createNewCard(CardType type) {
        if (type == CardType.SEASON) {
            registry.add(new SkiPassByTime(generateNewId(), type, seasonEndData()));
            return true;
        }
        return false;
    }

    public boolean createNewCard(CardType type, NumberOfDays number) {
        if (type == CardType.WEEKDAYS) {
            LocalDate endData = LocalDate.ofYearDay(LocalDate.now().getYear(),
                    LocalDate.now().getDayOfYear() + daysToInt.get(number));
            registry.add(new SkiPassByTime(generateNewId(), type, endData));
            return true;
        }
        else if (type == CardType.WEEKENDS && number != NumberOfDays.FIVE_DAYS) {
            LocalDate endData = LocalDate.ofYearDay(LocalDate.now().getYear(),
                    LocalDate.now().getDayOfYear() + daysToInt.get(number));
            registry.add(new SkiPassByTime(generateNewId(), type, endData));
            return true;
        }
        return false;
    }

    public boolean createNewCard(CardType type, NumberOfRides number) {
        if (type != CardType.SEASON) {
            registry.add(new SkiPassByRides(generateNewId(), type, ridesToInt.get(number)));
            return true;
        }
        return false;
    }

    private LocalDate seasonEndData() {
        LocalDate curData = LocalDate.now();
        int month = curData.getMonth().getValue();
        int year = curData.getYear();

        if (month >= 3 && month < 6) return LocalDate.of(year, 5, 31);
        else if (month >= 6 && month < 9) return LocalDate.of(year, 8, 31);
        else if (month >= 9 && month < 12) return LocalDate.of(year, 11, 30);
        else if (curData.isLeapYear()) return LocalDate.of(year + 1, 2, 29);
        else return LocalDate.of(year + 1, 2, 28);
    }

    private int generateNewId() {
        return ID++;
    }

    public void block(SkiPass card) {
        card.block();
    }
}
