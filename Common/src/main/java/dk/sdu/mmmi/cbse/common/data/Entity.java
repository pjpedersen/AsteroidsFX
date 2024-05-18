package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;
/**
 * Entity class acting as a base class for all entities in the game
 */
public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private double lifePoints;
    private float radius;

    /**
     * Method to get the ID of the entity
     * @return String containing the ID of the entity
     */
    public String getID() {
        return ID.toString();
    }

    /**
     * Method to set the coordinates of the polygon
     * @param coordinates double array containing the coordinates of the polygon
     */
    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    /**
     * Method to get the coordinates of the polygon
     * @return double array containing the coordinates of the polygon
     */
    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    /**
     * Method to set the x coordinate of the entity
     * @param x double value containing the x coordinate of the entity
     */
    public void setX(double x) {
        this.x =x;
    }

    /**
     * Method to get the x coordinate of the entity
     * @return double value containing the x coordinate of the entity
     */
    public double getX() {
        return x;
    }

    /**
     * Method to set the y coordinate of the entity
     * @param y double value containing the y coordinate of the entity
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Method to get the y coordinate of the entity
     * @return double value containing the y coordinate of the entity
     */
    public double getY() {
        return y;
    }

    /**
     * Method to set the rotation of the entity
     * @param rotation double value containing the rotation of the entity
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    /**
     * Method to get the rotation of the entity
     * @return double value containing the rotation of the entity
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Method to set the life points of the entity
     * @param lifePoints double value containing the life points of the entity
     */
    public void setLifePoints(double lifePoints) {
        this.lifePoints = lifePoints;
    }

    /**
     * Method to get the life points of the entity
     * @return double value containing the life points of the entity
     */
    public double getLifePoints() {
        return lifePoints;
    }

    /**
     * Method to set the radius of the entity
     * @param radius float value containing the radius of the entity
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * Method to get the radius of the entity
     * @return float value containing the radius of the entity
     */
    public float getRadius() {
        return radius;
    }
}
