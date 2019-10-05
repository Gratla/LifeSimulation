package game;

public class DNA {

    private static final boolean childDNAEnabled = false;

    private Creature creature;
    private DNAProperty[][] adultProperties;
    private DNAProperty[][] childProperties;
    private DNAProperty[][] properties;
    private int width;
    private int height;
    private double growProbability;

    private boolean isAdult;

    DNA(Creature creature, int width, int height){
        this.creature = creature;
        adultProperties = new DNAProperty[width][height];
        childProperties = new DNAProperty[width][height];
        properties = new DNAProperty[width][height];
        this.width = width;
        this.height = height;
        this.isAdult = false;
        this.growProbability = 0.001;
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

    void setChildProperties(DNAProperty[][] properties) {
        this.childProperties = properties;
    }

    void setProperties(DNAProperty[][] properties) {
        this.properties = properties;
    }

    void copyChildProperties(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(childDNAEnabled){
                    properties[i][j] = childProperties[i][j];
                }
                else{
                    properties[i][j] = adultProperties[i][j];
                }
            }
        }
    }

    DNAProperty getAdultProperty(int x, int y){
        if(x >= 0 && x < adultProperties.length && y >= 0 && y < adultProperties[0].length){
            return adultProperties[x][y];
        }
        return null;
    }

    DNAProperty getChildProperty(int x, int y){
        if(x >= 0 && x < childProperties.length && y >= 0 && y < childProperties[0].length){
            return childProperties[x][y];
        }
        return null;
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
        if(x < properties.length && x >= 0 && y < properties[x].length && y >= 0){
            if(properties[x][y] == null){
                return 0x00000000;
            }
            return properties[x][y].getColor();
        }
        else{
            return 0x00000000;
        }
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

    public void setIsAdult(){
        boolean adultFlag = true;
        for (int i = 0; i < width && adultFlag; i++) {
            for (int j = 0; j < height; j++) {
                if((properties[i][j] != null && !properties[i][j].getClass().equals(adultProperties[i][j].getClass())) || properties[i][j] != adultProperties[i][j]){
                    adultFlag = false;
                    break;
                }
            }
        }

        isAdult = adultFlag;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void grow(){
        setIsAdult();
        if(!isAdult && Math.random() < growProbability){
            boolean exitFlag = false;
            for (int i = 0; i < width && !exitFlag; i++) {
                for (int j = 0; j < height; j++) {
                    if((properties[i][j] != null && !properties[i][j].getClass().equals(adultProperties[i][j].getClass())) || properties[i][j] != adultProperties[i][j]){
                        properties[i][j] = adultProperties[i][j];
                        exitFlag = true;
                        break;
                    }
                }
            }
        }
    }
}
