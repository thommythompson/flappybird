package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public abstract class WorldObject {
    protected Texture texture;
    protected Rectangle bounds;
    private static Array<WorldObject> objects = new Array<WorldObject>();
    
    WorldObject(float xPos, float yPos, float width, float height){
        objects.add(this);
        bounds = new Rectangle(xPos, yPos, width, height);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void update(float delta){
        addPosition(new Vector2(-(delta * World.WORLD_SPEED), 0));
    }

    public static Array<WorldObject> getObjects(){
        return objects;
    }

    public static void disposeObjects(){
        objects.clear();
    }

    public Vector2 getPosition(){
        return new Vector2(bounds.x, bounds.y);
    }

    public void setPosition(Vector2 position){
        this.bounds.setPosition(position);
    }

    public void addPosition(Vector2 position){
        setPosition(new Vector2(bounds.x + position.x, bounds.y + position.y));
    }

    public boolean isCollision(Rectangle bounds){
        return this.bounds.overlaps(bounds);
    }

    public boolean isOffScreen(){
        return getPosition().x <= -bounds.width;
    }
}
