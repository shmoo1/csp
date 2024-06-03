public class CrazyCard {
    public String type;
    public String value;
    public CrazyCard(String type, String value){
        this.type = type;
        this.value = value;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getValue(){
        return value;
    }

    public String toString(){
        return type + " " + value;
    }
}
