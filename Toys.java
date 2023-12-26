/**
 * Класс Игрушек: toyId - id вида игрушки; toyName - наименование;
 * toyQuant - количество данного вида; toyWeight - "вес" (частота выпадения при розыгрыше от 0 до 100)
 */
public class Toys {
    private String toyId;
    private String toyName;
    private String toyQuant;
    private String toyWeight;
    

    public Toys() {

        this.toyId = null;
        this.toyName = "unknown";
        this.toyQuant = null;
        this.toyWeight = null;
    }
    public String getToyId() {
        return toyId;
    }
    public void setToyId(String toyId) {
        this.toyId = toyId;
    }
    public String getToyName() {
        return toyName;
    }
    public void setToyName(String toyName) {
        this.toyName = toyName;
    }
    public String getToyQuant() {
        return toyQuant;
    }
    public void setToyQuant(String toyQuant) {
        this.toyQuant = toyQuant;
    }
    public String getToyWeight() {
        return toyWeight;
    }
    public void setToyWeight(String toyWeight) {
        this.toyWeight = toyWeight;
    }

  
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("%s;%s;%s;%s", toyId, toyName, toyQuant, toyWeight);
        //return toyId + ";" + toyName + ";" + toyQuant + ";" + toyWeight;
    }

}
