import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.awt.geom.QuadCurve2D;

public class AutomataPanel extends JPanel {
    private automata miAutomata; // Tu clase automata
    private HashMap<String, Point> statePositions; // Almacena las posiciones de los estados
    private int stateRadius;

    public AutomataPanel(automata automata) {
        this.miAutomata = automata;
        this.statePositions = new HashMap<>();
        this.stateRadius = 100;
    }

    // Este método se llama automáticamente cuando el panel necesita ser repintado
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Dibuja los estados
        drawStates(g);

        // Dibuja las transiciones
        drawTransitions(g);
    }

    // Dibuja los estados como círculos
    private void drawStates(Graphics g) {
        int x = 50; // Coordenada x inicial
        int y = getHeight() / 2 - 50; // Altura centrada

        g.setColor(Color.BLACK);
        for (int i = 0; i < miAutomata.estados.length; i++) {
            String estado = miAutomata.estados[i];
            // Dibuja un círculo para el estado
            if( i == (miAutomata.estados.length - 1)){
                g.drawOval(x, y, stateRadius, stateRadius);
                g.drawOval(x - 10, y - 10, stateRadius + 20, stateRadius + 20);
            }else if(i == 0){
                g.drawOval(x, y, stateRadius, stateRadius);
                g.drawLine(x - 30 , y + 50, x, y + 50);
                g.drawLine(x - 10 , y + 60, x, y + 50);
                g.drawLine(x - 10 , y + 40, x, y + 50);
            }
            else{
            g.drawOval(x, y, stateRadius, stateRadius);
            }
            // Almacena la posición del estado
            statePositions.put(estado, new Point(x + stateRadius / 2, y + stateRadius / 2));

            g.drawString(estado, x + stateRadius / 2 - 10, y + stateRadius / 2 + 5); // Ajusta las coordenadas para centrar el texto

            // Aumenta la coordenada x para el siguiente estado
            x += 150;
            if (i % 2 == 0) {
                y = getHeight() / 2 + 50; // Siguiente estado abajo
            } else {
                y = getHeight() / 2 - 150; // Siguiente estado arriba
            }
        }
    }

    // Dibuja las transiciones como líneas
    private void drawTransitions(Graphics g) {
        HashMap<String, Integer> destinationLevels = new HashMap<>();
        g.setColor(Color.BLACK);

        int offset = 50; // Ajusta este valor para definir la distancia desde el borde del círculo
    
        for (String[] transicion : miAutomata.matrizTransiciones) {
            String estadoOrigen = transicion[0];
            String estadoDestino = transicion[2];
            if (statePositions.containsKey(estadoOrigen) && statePositions.containsKey(estadoDestino)) {
                // Obtiene las posiciones de los estados de inicio y fin
                Point inicio = statePositions.get(estadoOrigen);
                Point fin = statePositions.get(estadoDestino);

                // Calcula las coordenadas de inicio y fin con un offset
                int x1 = inicio.x; // Suma el radio del estado para empezar desde el borde derecho
                int y1 = inicio.y; // Ajusta la coordenada Y para centrar la línea verticalmente
                int x2 = fin.x; // Resta el offset para terminar en el borde izquierdo del destino
                int y2 = fin.y; // Ajusta la coordenada Y para centrar la línea verticalmente
                
        // Si el estado de origen es el mismo que el destino

                int destinationLevel = destinationLevels.getOrDefault(estadoDestino, 0);
                if (estadoOrigen.equals(estadoDestino)) {
                
                x1 = inicio.x + 10;
                y1 = inicio.y - 50 ; // Punto de inicio arriba del círculo
                y2 = fin.y - 150; // Ajusta la coordenada Y para centrar la línea verticalmente
                
                int x3 = x1 - 20;
                int y3 = y1; // Mismo nivel que el punto de inicio
            
                QuadCurve2D quadCurve = new QuadCurve2D.Float(x1, y1, x2, y2, x3, y3);
                // Dibuja la curva Bezier cuadrática
                ((Graphics2D) g).draw(quadCurve);
        
                x1 = inicio.x + 10;
                y1 = inicio.y - 60 - destinationLevel * 30; // Punto de inicio arriba del círculo
                x2 = x1 - 20;
            
                g.drawString(transicion[1], (x1 + x2) / 2, y1 - 50); // Ajusta las coordenadas para centrar el texto
                destinationLevels.put(estadoDestino, destinationLevel + 1);

    
        } else {
            x1 = inicio.x + offset; // Suma el offset para terminar en el borde izquierdo del destino
            x2 = fin.x - offset; // Resta el offset para terminar en el borde izquierdo del destino
             // Dibuja una línea entre los estados
            //g.drawLine(inicio.x, inicio.y, fin.x, fin.y);
            g.drawLine(x1, y1, x2, y2);
            
            x1 = inicio.x + 50 + destinationLevel * 30;; // Suma el offset para terminar en el borde izquierdo del destino
            g.drawString(transicion[1], (x1 + x2) / 2, (y1 + y2)/2 );
            destinationLevels.put(estadoDestino, destinationLevel + 1);
            }
        }
        }
    }

}