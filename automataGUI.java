import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class automataGUI  extends JFrame {
    //Atributos
    private AutomataPanel miautomataPanel;
    private JLabel texto1;


    //constructor
    public automataGUI(automata miAutomata){

        //Atributos de la pagina principal
        setLayout(null);

        setTitle("Automata");
        this.setBounds(0, 0, 1000, 650);

        miautomataPanel = new AutomataPanel(miAutomata);
        miautomataPanel.setBounds(20, 100, 950, 500);
        miautomataPanel.setBorder(new LineBorder(Color.RED, 2));
        add(miautomataPanel);

        texto1 = new JLabel("AUTOMATA");
        texto1.setBounds(350,40,300,30);
        texto1.setFont(new Font("Andale Mono",1, 40));
        texto1.setForeground(new Color(0,0,0));
        add(texto1);

    }

    public void mostrar(automataGUI miAutomataGUI){
        miAutomataGUI.setLocationRelativeTo(null); // Centrar la ventana
        miAutomataGUI.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
