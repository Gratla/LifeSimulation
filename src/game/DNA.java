package game;

public class DNA {

    private DNAProperty[][] properties;
    private int width;
    private int height;

    public DNA(int width, int height){
        properties = new DNAProperty[width][height];
        this.width = width;
        this.height = height;
    }

    public void createRandomProperties(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                properties[i][j] = new DNAMovement();
            }
        }
    }

    public void useProperties(Creature creature){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                properties[i][j].useProperty(creature);
            }
        }
    }

    public int getColor(int x, int y){
        return properties[x][y].getColor();
    }

}
