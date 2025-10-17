import java.util.Random;
import java.util.Scanner;

public class CheckValues {
    static Scanner sc = new Scanner(System.in); // Inicializa el

    /**
     * Ponemos mensaje y valor máximo y mínimo del entero, comprueba que sea un número entero y esté dentro del rango indicado,
     * también hace un control de las excepciones, vuelve a solicitar el número en caso de que este no sea válido.
     *
     * @param dato
     * @param minInclusive
     * @param maxInclusive
     * @return
     */
    public static int correctInteger(String dato, int minInclusive, int maxInclusive) {
        int num = 0;
        boolean datoOk = false;
        while (!datoOk) {
            System.out.print(dato);         // Presenta en pantalla el mensaje que se introdujo en la llamada.
            String input = sc.nextLine();   // guarda la entrada del teclado en un String llamado input.
            try {
                num = Integer.parseInt(input);  // Convierte input a un entero y lo guarda en la variable num.
                if (num >= minInclusive && num <= maxInclusive) {   // Comprueba que num esté entre el rango introducido en la llamada.
                    datoOk = true;  // Sale del bucle while
                } else {
                    // Si el número no esta dentro del rango muestra el mensaje y vuelve al principio del While.
                    System.out.println("El valor '" + input + "' no está entre " + minInclusive + " y " + maxInclusive);
                }
            } catch (NumberFormatException e) {
                // Muestra el mensaje si lo que se introduce por teclado no es un número entero.
                System.out.println("Error: Introduzca un número válido.");
            }
        }
        return num; // Al salir del while envia el número porque cumple los parametros solicitados.
    }

    /**
     * Realiza la comprobación del entero, pero si se introduce un -1, crea un número en random dentro del rango indicado.
     *
     * @param dato
     * @param minInclusive
     * @param maxInclusive
     * @return
     */
    public static int selectMax(String dato, int minInclusive, int maxInclusive) {
        int num = 0;
        boolean datoOk = false;
        while (!datoOk) {
            System.out.print(dato);
            String input = sc.nextLine();
            try {
                num = Integer.parseInt(input);
                if (num == -1) { // Comprueba si el número introducido es -1
                    Random rand = new Random(); // Inicializa la clase Random
                    // Crea un número aleatorio de la cantidad de números que hay entre el minimo y el máximo solicitado y al sumarle el mínimo le indicas donde tiene que empezar la primera cifra.
                    num = rand.nextInt(maxInclusive - minInclusive + 1) + minInclusive;
                }
                // Si la cifra no es -1, comprueba que esté en el rango correcto.
                if (num >= minInclusive && num <= maxInclusive) {
                    datoOk = true;
                } else {
                    System.out.println("El valor '" + input + "' no está entre " + minInclusive + " y " + maxInclusive);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduzca un número válido.");
            }
        }
        return num;
    }

    /**
     * Comprueba que se introduce un nombre válido sin símbolos y con una longitud mínima de 3 caracteres.
     *
     * @return
     */
    public static String correctName() {
        String name = ""; // Inicialización segura
        do {
            System.out.print("Introduzca su nombre (mínimo 3 caracteres, solo letras y espacios): ");
            try {
                name = sc.nextLine().trim();
            } catch (Exception e) {
                System.out.println("Error al leer el nombre. Inténtelo de nuevo.");
                name = ""; // Reinicia para que el bucle continúe
            }
        } while (name.length() < 3 || !name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"));
        return name;
    }

    /**
     * Comprueba si el número que introduce el jugador es válido con respecto al último número
     *
     * @param lastNumber
     * @param numberAtStake
     * @return
     */
    static boolean comprobarSiSonValidos(int lastNumber, int numberAtStake) {
        int[] lastPosition = checkPosition(lastNumber); // Crea un array con la posición fila y columna del número a comprobar
        int[] newPosition = checkPosition(numberAtStake); // con respecto a una matriz ya creada.
        if (lastNumber == numberAtStake) { // Comprueba que el número que se introduce no es el mismo que el anterior.
            System.out.println("No se puede repetir el último número.\n");
            return false;
        }
        return
                lastPosition[0] == newPosition[0] // Comprueba que estén en la misma fila.
                        ||
                        lastPosition[1] == newPosition[1] // comprueba que estén en la misma columna.
                ;
    }

    /**
     * Nos da la posición del numero en la Matriz.
     *
     * @param number
     * @return
     */
    private static int[] checkPosition(int number) {
        for (int row = 0; row < 3; row++) {                     // Recorre las filas.
            for (int column = 0; column < 3; column++) {        // Recorre las columnas.
                if (Program.MATRIZ[row][column] == number) {    // Cuando el número a comprobar coincide con la matriz
                    return new int[]{row, column};              // envía un array con la posición [fila, columna]
                }
            }
        }
        return null; // No encontrado
    }
}
/*
public static int leerEntero(String mensaje, int minInclusive, int maxInclusive, boolean permitirAleatorio) {
    int num = 0;
    boolean datoOk = false;
    Random rand = new Random();

    while (!datoOk) {
        System.out.print(mensaje);
        String input = sc.nextLine();

        try {
            num = Integer.parseInt(input);

            if (permitirAleatorio && num == -1) {
                num = rand.nextInt(maxInclusive - minInclusive + 1) + minInclusive;
            }

            if (num >= minInclusive && num <= maxInclusive) {
                datoOk = true;
            } else {
                System.out.println("El valor '" + input + "' no está entre " + minInclusive + " y " + maxInclusive);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Introduzca un número válido.");
        }
    }

    return num;
}

 */