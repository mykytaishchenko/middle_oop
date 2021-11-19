package Cards;

import Options.CardType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SkiPassByRides extends SkiPass {
    private int rides;

    public SkiPassByRides(int id, CardType type, int rides) {
        super(id, type);
        this.rides = rides;
    }

    public void useOneRide() {
        this.rides--;
    }
}
