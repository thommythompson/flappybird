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
    private static Array<GameWorldObject> objects = new Array<GameWorldObject>();
    
    GameWorldObject(float xPos, float yPos){}

    GameWorldObject(float xPos, float yPos, float width, float height){
        objects.add(this);
        bounds = new Rectangle(xPos, yPos, width, height);
    }

    /**
     * @param float delta
     * bepaald de nieuwe staat van het object en wordt continue aangeroepen.
     */
    public abstract void update(float delta, float timeElapsed, float worldSpeed);

    /**
     * @param SpriteBatch batch
     * De draw methode tekent het waarde van het texture attribuut in de spritebatch met de afmetingen en positie die aanwezig zijn in het bounds attribuut.
     */
    public void draw(SpriteBatch batch){
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /**
     * @param Vector2 position
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
     * @param Vector2 position
     * Voegt de opgegeven x en y positie toe aan de huidige positie van het bounds attribuut.
     */
    public void addPosition(Vector2 position){
        setPosition(new Vector2(getPosition().x + position.x, getPosition().y + position.y));
    }

    /**
     * @param float delta
     * Beweegt het object naar links. De snelheid word bepaald door de delta time en de world speed.
     */
    public void moveToTheLeft(float delta, float worldSpeed){
        addPosition(new Vector2(-(delta * worldSpeed), 0));
    }

    /**
     * @param Rectangle bounds
     * @return boolean
     * Geeft de waarde true terug indien de vierhoek opgegeven als methode parameter overlapt met de vierhoek uit het bounds attribuut.
     */
    public boolean isCollisionDetected(Rectangle bounds){
        return this.bounds.overlaps(bounds);
    }

    /**
     * @return boolean
     * Geeft de waarde true terug indien het object zich volledig buiten het scherm begeeft.
     */
    public boolean isOffScreen(){
        return getPosition().x <= -bounds.width;
    }

    /**
     * Verwijderd de texture ingeladen in het texture attribuut uit het geheugen.
     */
    public void disposeTexture(){
        texture.dispose();
    }

    /**
     * @Return Array<WorldObject>
     * Geeft een array terug gevuld met referenties naar alle geinstantieerde WorldObjecten.
     */
    public static Array<GameWorldObject> getAllInstances(){
        return objects;
    }

    /**
     * Verwijderd alle Textures die ingeladen zijn door instanties van het WorldObject type uit het geheugen en leegt vervolgens de statisch array waarin het referenties naar instanties van het eigen type bijhoud.
     */
    public static void disposeAllInstances(){
        for(GameWorldObject worldObject : GameWorldObject.getAllInstances()) worldObject.disposeTexture();
        objects.clear();
    }
}
