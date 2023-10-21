import java.util.Arrays;
import java.util.Scanner;


public class index {
    public static void main(String args[]){

        Scanner input = new Scanner(System.in);
        String lenguaje = "";
        String[] ArregloLenguaje;
        String[] palabrasEnOracion;
        int cantidad;
        String Oracion;
        boolean pertenece = false;

        do {
            System.out.print("Ingrese cada simbolo de el lenguaje separado por comas (elejmplo: 1,0): " );
            lenguaje = input.nextLine();
        } while (!validarCadena(lenguaje)); //mientras el valor de la funcion validar cadena sea falso el bucle sigue
        
        ArregloLenguaje = lenguaje.split(",");

        
        System.out.print("Ingrese la catidad de estados seran representados de E0(INICIAL) al E(n - 1)(FINAL): " );
    
        cantidad = input.nextInt();
        input.nextLine(); //Se limpia el input para las siguientes solicitudes
        String estados[] = new String[cantidad];


        System.out.println("Lista De Estados" );
        for (int i = 0; i < cantidad; i++) {
            estados[i] = "E" + i;
            System.out.println("Estado " + (i + 1) + ": " + estados[i]);
        }

        String EstadoInicial = estados[0]; 
        String EstadoFinal = estados[cantidad - 1]; 
        

        String[][] matrizTransiciones = crearMatriz(estados,ArregloLenguaje,input);

        System.out.println("Ingrese la oracion con caracteres pertenecientes al lenguaje para evaluar sin comas" );

        do {
            pertenece = true;
            Oracion = input.nextLine();

            palabrasEnOracion = Oracion.split("");

            for (String palabra : palabrasEnOracion) {
                if (!Arrays.asList(ArregloLenguaje).contains(palabra)) {
                    pertenece = false;
                    break;
                }
            }
            if (!pertenece) {
            System.out.println("La oración contiene caracteres que no pertenecen al lenguaje");
        }

        } while (!pertenece); //mientras el valor de la funcion validar cadena sea falso el bucle sigue

        String estadoActual = EstadoInicial;
        System.out.print("Recorrido: " + estadoActual);
        
        for (String caracter : palabrasEnOracion) {
            boolean transicionEncontrada = false;
        
            for (String[] transicion : matrizTransiciones) {
                if (transicion[0].equals(estadoActual) && transicion[1].equals(caracter)) {
                    estadoActual = transicion[2];
                    transicionEncontrada = true;
                    System.out.print(" -> " + estadoActual);
                    break;
                }
            }
        
            if (!transicionEncontrada) {
                System.out.println("\nLa oración no es válida en el autómata.");
                break; // Sal del bucle si no se encuentra una transición válida
            }
        }
        
        if (estadoActual.equals(EstadoFinal)) {
            System.out.println("\nLa oración es válida en el autómata.");
        } else {
            System.out.println("\nLa oración no llega al estado final del autómata.");
        }


    automata miAutomata = new automata(cantidad,lenguaje, ArregloLenguaje, estados, EstadoInicial, EstadoFinal, matrizTransiciones);
    miAutomata.imprimirAutomata();
    automataGUI miautomataGUI = new automataGUI(miAutomata);
    miautomataGUI.mostrar(miautomataGUI);


    
     input.close();
    }

    // Validacion del lenguaje
            public static boolean validarCadena(String cadena) {
            if (cadena.isEmpty()) {
                System.out.println("El lenguaje no puede estar vacio");
                return false;
            }
            if(cadena.endsWith(",")){
                    System.out.println("La cadena no puede acabar en ,");
                    return false;
                }
        
            // que el lenguaje tenga la forma de carcater unico separado por coma 
            if(!cadena.matches("^[^,](,[^,])*(,[^,])?$")){ 
                    System.out.println("El lenguaje solo puede estar conformado por caracteres unicos separados por comas");
                    return false;
                }
            
            for (char c : cadena.toCharArray()) {
                if (c != ',' && (c < 48 || c > 57) && (c < 65 || c > 90 ) && (c < 97 || c > 122 ) ) {
                    System.out.println("El lenguaje solo puede tener numeros o letras mayusculas y minusuculas" );
                    return false;
                }
            }
            return true;
        }

    // Crear matriz
            public static String[][] crearMatriz(String[] estados, String[] ArregloLenguaje,Scanner input){

                int filas = (ArregloLenguaje.length * estados.length);
                int columnas = 3;
                int contador = 0;
                String valor;

                String[][] matriz = new String[filas][columnas];
                
                for (int i = 0; i < estados.length; i++) {
                    for (int j = 0; j < ArregloLenguaje.length; j++) {
                        matriz[contador][0] = estados[i];
                        matriz[contador][1] = ArregloLenguaje[j];

                         boolean validacion = false;

                        do{
                        System.out.print("Ingrese el valor para la transición desde " + estados[i] + " con " + ArregloLenguaje[j] + ": ");
                        valor = input.nextLine();
                        // Verificar si el valor ingresado pertenece al arreglo de estados
                        for (String estado : estados) {
                            if (valor.equals(estado)) {
                                validacion = true;
                                break; // Si encuentra una coincidencia, salimos del bucle
                            }
                        }if(!validacion) {
                            System.out.println("El valor ingresado no pertenece al arreglo de estados. Intente de nuevo.");
                        }
                        }while(!validacion);

                        matriz[contador][2] = valor;
                        contador++;
                    }
                }

                return matriz;
        }


}