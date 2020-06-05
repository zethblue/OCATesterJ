package fragenobjekt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//Hardcoded Fragen bis zur Serialisierung zum Testen

public class Erstellen {

    public List<Fragenobjekt> Fragenobjekte = new ArrayList<>();
    public Erstellen(){

        HashMap hmf1 = new HashMap<String,Boolean>();
        hmf1.put("Der Code kompiliert fehlerfrei",true);
        hmf1.put("Es wird Barbie Ken gesoutet",false);
        hmf1.put("Es wird BarbieKen gesoutet",true);
        hmf1.put("Die Zeile 5 verursacht einen Fehler",false);
        hmf1.put("Die Zeile 6 verursacht einen Fehler",false);
        String sf1 = "public class StringFun {" +
                "\n\n\tpublic static void main(String[] args) {" +
                "\n\n\t\tString Friend = \"Barbie\";" +
                "\n\t\tString BFF = new String(\"Ken\");"+
                "\n\t\tSystem.out.println(Friend + BFF);\n\t}\n}";

        Fragenobjekt f1 = new Fragenobjekt(sf1,hmf1);
        Fragenobjekte.add(f1);

    }

    public List<Fragenobjekt> getFragenobjekte() {
        return Fragenobjekte;
    }
}