package game;

import datastructures.Vector2D;

public class DNAMovement extends DNAProperty {

    private Vector2D velocity;
    private double maxSpeed;
    private Vector2D acceleration;

    DNAMovement() {
        super(0xFF000000);

        this.velocity = new Vector2D(Math.random()-0.5, Math.random()-0.5);
        this.velocity.normalize();
        this.acceleration = new Vector2D(0,0);

        this.maxSpeed = 1;
    }

    public void useProperty(Creature creature){
        seek(creature);

        velocity.add(acceleration);
        limit(velocity);
        creature.position.add(velocity);
        acceleration.multiply(0);
    }

    private void seek(Creature creature){
        Vector2D desiredPosition = creature.mind.getDesiredPosition();
        if(desiredPosition != null){
            Vector2D desiredVelocity = Vector2D.subtract(desiredPosition, creature.position);
            desiredVelocity.normalize();
            desiredVelocity.multiply(maxSpeed);

            Vector2D steering = Vector2D.subtract(desiredVelocity,velocity);
            steering.normalize();
            steering.multiply(maxSpeed/50);
            //limit(steering);

            acceleration = new Vector2D(steering);
        }
    }

    private void limit(Vector2D velocity){
        if(velocity.getLength() > maxSpeed){
            velocity.normalize();
            velocity.multiply(maxSpeed);
        }
    }
}
