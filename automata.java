public class automata {
    // Atributos
    public String lenguaje = "";
    public int cantidad;
    public String[] ArregloLenguaje;
    public String[] estados;
    public String EstadoInicial;
    public String EstadoFinal;
    public String[][] matrizTransiciones;

    // Constructor
    public automata(int cantidad,String lenguaje, String[] ArregloLenguaje, String[] estados, String EstadoInicial, String EstadoFinal, String[][] matrizTransiciones) {
        this.cantidad = cantidad;
        this.lenguaje = lenguaje;
        this.ArregloLenguaje = ArregloLenguaje;
        this.estados = estados;
        this.EstadoInicial = EstadoInicial;
        this.EstadoFinal = EstadoFinal;
        this.matrizTransiciones = matrizTransiciones;
    }

    

    public void imprimirAutomata(){
        //Lenguaje
        System.out.println("El lenguaje es: {" + lenguaje + "}" );
        
        //Lista de estados
        System.out.println("Lista De Estados" );

        for (int i = 0; i < cantidad; i++) {
            System.out.println("Estado " + (i + 1) + ": " + estados[i]);
        }

        //Estado inicial y final
        System.out.println("Estado Inicial: " + EstadoInicial );
        System.out.println("Estado Final: " + EstadoFinal );

        //Matriz de tranciciones
        System.out.println("Matriz de Transiciones");
        
        for (int i = 0; i < matrizTransiciones.length; i++) {
                for(int j = 0; j < matrizTransiciones[i].length; j++) {
                    System.out.print(matrizTransiciones[i][j] + " ");
                }
                System.out.println(); // Salto de línea después de cada fila
            }
    }
}

