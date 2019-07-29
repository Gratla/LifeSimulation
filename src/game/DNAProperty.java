package game;

public abstract class DNAProperty {

    protected int color;

    public DNAProperty(int color){
        this.color = color;
    }

    public int getColor(){
        return color;
    }

    public void useProperty(Creature creature){

    }

}
