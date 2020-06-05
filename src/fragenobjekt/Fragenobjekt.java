package fragenobjekt;
import java.io.Serializable;
import java.util.HashMap;

public class Fragenobjekt implements Serializable {

    private String frage;
    private HashMap<String, Boolean> antworten;


    public String getFrage() {
        return frage;
    }
    public HashMap<String, Boolean> getAntworten() {
        return antworten;
    }
    public Fragenobjekt(String frage, HashMap<String, Boolean> antworten) {
        this.frage = frage;
        this.antworten = antworten;
    }
    public boolean frageFragenobjektAb(String s){
        return antworten.get(s);
    }

}
