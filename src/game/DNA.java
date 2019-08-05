package game;

public class DNA {

    private DNAProperty[][] adultProperties;
    private DNAProperty[][] properties;
    private int width;
    private int height;

    DNA(int width, int height){
        adultProperties = new DNAProperty[width][height];
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

    void setAdultProperties(DNAProperty[][] properties) {
        this.adultProperties = properties;
    }

    void setProperties(DNAProperty[][] properties) {
        this.properties = properties;
    }

    void useProperties(Creature creature){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(properties[i][j] != null){
                    properties[i][j].useProperty(creature);
                }
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
        if(properties[x][y] == null){
            return 0x00000000;
        }
        return properties[x][y].getColor();
    }

    public void deleteRandomProperty() {
        for (int i = 0; i < properties.length; i++) {
            for (int j = 0; j < properties[i].length; j++) {
                if(Math.random() > 0.7){
                    properties[i][j] = null;
                }
            }
        }
    }

    public boolean isEmpty(){
        for (int i = 0; i < properties.length; i++) {
            for (int j = 0; j < properties[i].length; j++) {
                if(properties[i][j] == null){
                    return false;
                }
            }
        }

        return true;
    }
}
