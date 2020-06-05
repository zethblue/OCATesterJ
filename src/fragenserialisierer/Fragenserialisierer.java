package fragenserialisierer;


import fragenobjekt.Fragenobjekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class Fragenserialisierer extends JFrame {

    File dateiname;
    String frage;
    HashMap<String, Boolean> antworten = new HashMap<>();

    JTextArea frageErstellArea = new JTextArea("Frage hier eingeben\ndann speichern drücken");
    JTextArea frageIstErstelltArea = new JTextArea("hier wird die gespeicherte Frage ausgegeben");
    JTextArea antwortErstellArea = new JTextArea("Antwort hier eingeben\ndann speichern drücken");
    JTextArea antwortIstErstelltArea = new JTextArea("hier werden die gespeichereten Antworten ausgegeben");
    JButton buttonFrageOK = new JButton("Frage speichern");
    JButton buttonantwortSpeichernTRUE = new JButton("richtige Antwort");
    JButton buttonantwortSpeichernFALSE = new JButton("falsche Antwort");
    JLabel anzahlAntworten = new JLabel("Anzahl Antworten: 0");
    JButton serialisieren = new JButton("Serialisierung");
    JPanel xEmpty = new JPanel();


    public Fragenserialisierer() {
        frageErstellArea.setBorder(BorderFactory.createEtchedBorder());
        antwortErstellArea.setBorder(BorderFactory.createEtchedBorder());
        frageIstErstelltArea.setBorder(BorderFactory.createEtchedBorder());
        antwortIstErstelltArea.setBorder(BorderFactory.createEtchedBorder());

        this.setLayout(new GridLayout(3, 2));

        JLabel buttonLabel = new JLabel();
        buttonLabel.setLayout(new GridLayout(2, 2));
        buttonLabel.add(buttonFrageOK);
        buttonLabel.add(anzahlAntworten);
        buttonLabel.add(buttonantwortSpeichernFALSE);
        buttonLabel.add(buttonantwortSpeichernTRUE);

        this.add(frageErstellArea);
        this.add(frageIstErstelltArea);
        this.add(antwortErstellArea);
        this.add(antwortIstErstelltArea);
        this.add(buttonLabel);
        this.add(serialisieren);

        this.setSize(1000, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        buttonFrageOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frage = frageErstellArea.getText();
                frageIstErstelltArea.setText(frage);

            }
        });
        buttonantwortSpeichernTRUE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = antwortErstellArea.getText();
                antworten.put(s, true);
                aktualisiereAnzeigeAntworten();


            }
        });
        buttonantwortSpeichernFALSE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = antwortErstellArea.getText();
                antworten.put(s, false);
                aktualisiereAnzeigeAntworten();


            }
        });
        serialisieren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fragenobjekt f1 = new Fragenobjekt(frage, antworten);
                aktualisiereDateiname();
                try {
                    FileOutputStream fos = new FileOutputStream(dateiname);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(f1);
                    oos.close(); fos.close();
                    System.out.println("saved" + dateiname.toString());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private void aktualisiereAnzeigeAntworten() {
        anzahlAntworten.setText("Anzahl Antworten: " + String.valueOf(antworten.size()));
        StringBuilder x = new StringBuilder();
        for (String s : antworten.keySet()) {
            x.append(s + "\n");
        }
        antwortIstErstelltArea.setText(x.toString());
    }

    private void aktualisiereDateiname() {
        int i = 1;
        boolean doesNotExist = false;
        do {
            dateiname = new File("qu" + i++);
            if (!(dateiname.exists())) {
                doesNotExist = true;
            }
        } while (!doesNotExist);
    }
}
