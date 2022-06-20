package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
* @version 1
* @author Thomas Hofman
* Deze abstracte klasse is een gestandardiseerde oplossing om spel objecten bij te houden. 
* Alle spel objecten hebben namelijk een texture, afmetingen en een positie. 
* Deze klasse bevat standaard methoden om deze attributen te manipuleren.
* De klasse houd ook een statische array bij met referenties naar instanties van het eigen Type. 
*/
public abstract class GameWorldObject {
    protected Texture texture;
    protected Rectangle bounds;
    private static Array<GameWorldObject> instances = new Array<GameWorldObject>();
    
    /**
     * @param xPos
     * @param yPos
     * Deze constructor bestaat omdat er voor elke klassen die over erven van de WorldObject klasse afmetingen gedefineerd zijn middels een constante variabele. 
     * Het opnemen van deze constructor maakt het mogelijk voor overervende klasses om deze constructor zelf te implementeren en dus niet onnodig om afmeting gerelateerde paramters hoeven te vragen.
     */
    GameWorldObject(float xPos, float yPos){}

    GameWorldObject(float xPos, float yPos, float width, float height){
        instances.add(this);
        bounds = new Rectangle(xPos, yPos, width, height);
    }

    /**
     * @param delta - De tijd verstreken vanaf de vorige aanroep in seconden
     * Abstract methode die de nieuwe staat van het object bepaald en wordt continue aangeroepen.
     */
    public abstract void update(float delta, float timeElapsed, float worldSpeed);

    /**
     * @param batch - De spritebatch
     * De draw methode tekent de waarde van het Texture attribuut op het scherm met de afmetingen en positie die opgeslagen zijn in het bounds attribuut.
     */
    public void draw(SpriteBatch batch){
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /**
     * @param position - De nieuwe positie van het object als Vector2
     * Zet de positie van het bounds attribuut.
     */
    public void setPosition(Vector2 position){
        this.bounds.setPosition(position);
    }

    /**
     * @return Vector2
     * Geeft een Vector2 terug aan de hand van de x en y attributen van het bounds attribuut.
     */
    public Vector2 getPosition(){
        return new Vector2(bounds.x, bounds.y);
    }

    /**
     * @param position - Vector2
     * Voegt de opgegeven x en y positie toe aan de huidige positie van het bounds attribuut.
     */
    public void addPosition(Vector2 position){
        setPosition(new Vector2(getPosition().x + position.x, getPosition().y + position.y));
    }

    /**
     * @param delta - De tijd verstreken vanaf de vorige aanroep in seconden
     * Beweegt het object naar links. De snelheid word bepaald door de delta time en de world speed.
     */
    public void moveToTheLeft(float delta, float worldSpeed){
        addPosition(new Vector2(-(delta * worldSpeed), 0));
    }

    /**
     * @param bounds - Rectangle van mogelijk overlappend object
     * @return boolean
     * Geeft de waarde true terug indien de vierhoek opgegeven als parameter overlapt met de vierhoek uit het bounds attribuut van de instantie waar de methode van aangeroepen wordt.
     */
    public boolean isCollisionDetected(Rectangle bounds){
        return this.bounds.overlaps(bounds);
    }

    /**
     * @return boolean
     * Geeft de waarde true terug indien het object zich volledig links buiten het scherm begeeft.
     */
    public boolean isOffScreen(){
        return getPosition().x <= -bounds.width;
    }

    /**
     * Verwijderd het ingeladen texture attribuut uit het geheugen.
     */
    public void dispose(){
        texture.dispose();
    }

    /**
     * Geeft een array terug gevuld met referenties naar alle geinstantieerde WorldObjecten.
     * @return {Array&lt;GameWorldObject&gt;}
     */
    public static Array<GameWorldObject> getAllInstances(){
        return instances;
    }

    /**
     * Verwijderd alle Textures die ingeladen zijn door instanties van het WorldObject type uit het geheugen en leegt vervolgens de statisch array waarin het referenties naar instanties van het eigen type bijhoud.
     */
    public static void disposeAllInstances(){
        for(GameWorldObject worldObject : GameWorldObject.getAllInstances()) worldObject.dispose();
        instances.clear();
    }
}
