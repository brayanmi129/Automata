import java.util.*;

public class chatgpt{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Ingresar el lenguaje (alfabeto)
        System.out.print("Ingrese el lenguaje (caracteres separados por comas): ");
        String[] lenguaje = input.nextLine().split(",");

        // Ingresar los estados
        System.out.print("Ingrese los estados (separados por comas): ");
        String[] estados = input.nextLine().split(",");

        // Ingresar el estado inicial
        System.out.print("Ingrese el estado inicial: ");
        String estadoInicial = input.nextLine();

        // Ingresar el estado final
        System.out.print("Ingrese el estado final: ");
        String estadoFinal = input.nextLine();

        // Crear el conjunto de transiciones
        Map<String, Map<String, String>> transiciones = new HashMap<>();

        // Ingresar las transiciones
        System.out.println("Ingrese las transiciones (ejemplo: q0,a,q1):");
        System.out.println("Para finalizar, ingrese 'fin'");
        while (true) {
            String transicion = input.nextLine();
            if (transicion.equals("fin")) {
                break;
            }
            String[] partes = transicion.split(",");
            if (partes.length != 3) {
                System.out.println("Formato incorrecto. Debe ser de la forma q0,a,q1.");
                continue;
            }
            String estadoOrigen = partes[0];
            String simbolo = partes[1];
            String estadoDestino = partes[2];

            // Agregar la transición al conjunto de transiciones
            if (!transiciones.containsKey(estadoOrigen)) {
                transiciones.put(estadoOrigen, new HashMap<>());
            }
            transiciones.get(estadoOrigen).put(simbolo, estadoDestino);
        }

        // Verificar una palabra
        System.out.print("Ingrese una palabra para verificar: ");
        String palabra = input.nextLine();

        // Realizar la verificación utilizando el AFD
        String estadoActual = estadoInicial;
        for (char c : palabra.toCharArray()) {
            String simbolo = String.valueOf(c);
            if (!transiciones.containsKey(estadoActual) || !transiciones.get(estadoActual).containsKey(simbolo)) {
                System.out.println("La palabra no es válida.");
                return;
            }
            estadoActual = transiciones.get(estadoActual).get(simbolo);
        }

        if (estadoActual.equals(estadoFinal)) {
            System.out.println("La palabra es válida. Se alcanzó el estado final.");
        } else {
            System.out.println("La palabra no alcanzó el estado final.");
        }

        input.close();
    }
}

