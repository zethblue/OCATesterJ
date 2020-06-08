//simples Programm um fragenobjekte vorerst nur anzusehen und auszulesen
//Die Files müssen einfach im src Ordner vorhanden sein und mit qu1 anfangen - der Fragenserialisierer erstellt die Files mit diesen Werten

package fragenanzeiger;

import fragenobjekt.Fragenobjekt;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Fragenanzeiger extends JFrame {
    private Fragenobjekt f1;
    int fileInteger = 0;


    public Fragenanzeiger(){
        JTextArea frage = new JTextArea("Hier steht die Frage");
        frage.setFocusable(false); frage.setBorder(BorderFactory.createEtchedBorder());
        JTextArea antworten = new JTextArea("Hier stehen die Antworten");
        antworten.setFocusable(false);antworten.setBorder(BorderFactory.createEtchedBorder());
        JButton nextQuestion = new JButton("nächste Frage");
        nextQuestion.setBorder(BorderFactory.createBevelBorder(5));

        this.setLayout(new GridLayout(3,1));
        this.add(frage); this.add(antworten);this.add(nextQuestion);

        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        nextQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileInteger++;
                File F1 = setFile1();
                try {
                    FileInputStream fip = new FileInputStream(F1);
                    ObjectInputStream ois = new ObjectInputStream(fip);
                    Object object1 = ois.readObject();
                    f1 = (Fragenobjekt) object1;
                    frage.setText(f1.getFrage());
                    StringBuilder x = new StringBuilder();

                    for(String s : f1.getAntworten().keySet()){
                        x.append(s).append("\t");x.append(f1.getAntworten().get(s).toString()).append("\n");
                    }
                    antworten.setText(x.toString());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });

    }
    public File setFile1(){
        File f1 = new File("qu"+fileInteger);
        if (!f1.exists()) {
            fileInteger = 1;
            f1 = new File("qu" + fileInteger);
        }
        return f1;
    }


}
