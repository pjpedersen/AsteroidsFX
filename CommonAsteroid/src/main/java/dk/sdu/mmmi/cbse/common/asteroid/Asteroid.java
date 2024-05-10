package dk.sdu.mmmi.cbse.common.asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {

    private boolean isSplit = false;

    public boolean isSplit() {
        return isSplit;
    }

    public void setSplit(boolean isSplit) {
        this.isSplit = isSplit;
    }
}
