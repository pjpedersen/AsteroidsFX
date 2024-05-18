package dk.sdu.mmmi.cbse.common.asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 * Class responsible for creating and splitting asteroids by extending base entity class.
 */
public class CommonAsteroidEntity extends Entity {

    private boolean isSplit = false;

    /**
     * Method to create an asteroid entity
     * @return Entity object containing the asteroid entity
     */
    public boolean isSplit() {
        return isSplit;
    }

    /**
     * Method to split an asteroid entity
     * @param isSplit boolean value indicating if the asteroid is split
     */
    public void setSplit(boolean isSplit) {
        this.isSplit = isSplit;
    }
}
