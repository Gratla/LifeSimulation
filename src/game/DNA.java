package game;

public class DNA {

    private DNAProperty[][] properties;
    private int width;
    private int height;

    DNA(int width, int height){
        properties = new DNAProperty[width][height];
        this.width = width;
        this.height = height;
    }

    /*public void createRandomProperties(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                properties[i][j] = new DNAMovement();
            }
        }
    }*/

    void setProperties(DNAProperty[][] properties) {
        this.properties = properties;
    }

    void useProperties(Creature creature){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                properties[i][j].useProperty(creature);
            }
        }
    }

    int getNumberOfDNAProperties(Class dnaClass){
        int result = 0;

        for (int i = 0; i < properties.length; i++) {
            for (int j = 0; j < properties[i].length; j++) {
                if(properties[i][j] != null){
                    if(properties[i][j].getClass() == dnaClass){
                        result++;
                    }
                }
            }
        }
        
        return result;
    }

    int getColor(int x, int y){
        return properties[x][y].getColor();
    }

}
