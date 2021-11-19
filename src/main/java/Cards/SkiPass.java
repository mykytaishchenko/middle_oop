package Cards;

import Options.CardType;
import lombok.Getter;
import lombok.Setter;

public abstract class SkiPass {
    @Setter @Getter
    private int id;
    @Setter @Getter
    private CardType type;
    private boolean blocked;

    public SkiPass(int id, CardType type) {
        this.id = id;
        this.type = type;
        this.blocked = false;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }
}
