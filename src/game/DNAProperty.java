package game;

public abstract class DNAProperty {

    protected int color;

    public DNAProperty(int color){
        this.color = color;
    }

    public static DNAProperty getRandomAnimalProperty() {
        double numberOfProperties = 4;
        double random = Math.random();

        if(random < 1/numberOfProperties){
            return new DNAEyes();
        }
        else if(random < 2/numberOfProperties){
            return new DNAMovement();
        }
        else if(random < 3/numberOfProperties){
            return new DNATeeth();
        }
        else{
            return null;
        }
    }

    public int getColor(){
        return color;
    }

    public void useProperty(Creature creature){

    }

}
