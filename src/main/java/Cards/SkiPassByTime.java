package Cards;

import Options.CardType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
public class SkiPassByTime extends SkiPass {
    private LocalDate endData;

    public SkiPassByTime(int id, CardType workDays, LocalDate endData) {
        super(id, workDays);
        this.endData = endData;
    }
}
