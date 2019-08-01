package game;

import datastructures.Vector2D;

public class Mind {
    private Vector2D desiredPosition;

    Mind(Vector2D desiredPosition){
        this.desiredPosition = new Vector2D(desiredPosition);
    }

    public Vector2D getDesiredPosition(){
        return desiredPosition;
    }
}
