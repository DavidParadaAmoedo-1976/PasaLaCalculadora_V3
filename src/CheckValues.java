import java.util.Random;
import java.util.Scanner;

public class CheckValues {
    static Scanner sc = new Scanner(System.in); // Inicializa el scanner

    /**
     * Ponemos mensaje y valor máximo y mínimo del entero, comprueba que sea un número entero y esté dentro del rango indicado,
     * también hace un control de las excepciones, vuelve a solicitar el número en caso de que este no sea válido.
     * El booleano permite que se introduzca un -1, este, crea un número en random dentro del rango indicado.
     * @param mensaje ->Texto que queremos que aparezca al solicitar el entero.
     * @param minInclusive ->Valor mínimo del entero, este incluido en el límite inferior.
     * @param maxInclusive ->Valor máximo del entero, este tambien incluido
     * @param allowRandom ->indica si queromes que acepte el valor -1 fuera de los valores de los enteros (se necesita en una parte del juego)
     * @return ->Devuelve el número introducido si cumple las condiciones, o el generado aleatorio en caso de aceptar -1.
     */
    public static int correctInteger(String mensaje, int minInclusive, int maxInclusive, boolean allowRandom) {
        int num = 0;
        boolean datoOk = false;
        Random rand = new Random(); // Inicializa la clase Random.

        while (!datoOk) {
            System.out.print(mensaje);      // Presenta en pantalla el mensaje que se introdujo en la llamada.
            String input = sc.nextLine();   // guarda la entrada del teclado en un String llamado input.

            try {
                num = Integer.parseInt(input);  // Convierte input a un entero y lo guarda en la variable num.
                if (allowRandom && num == -1) { // comprueba que se cumplan las 2 condiciones, alloRandon a true y han introducido -1.
                    num = rand.nextInt(maxInclusive - minInclusive + 1) + minInclusive; // genera un número aleatorio dentro del rango establecido.
                }

                if (num >= minInclusive && num <= maxInclusive) {   // Comprueba que num esté entre el rango introducido en la llamada.
                    datoOk = true;  // Sale del bucle while
                } else {    // Si el número no esta dentro del rango muestra el mensaje y vuelve al principio del While.
                    System.out.println("El valor '" + input + "' no está entre " + minInclusive + " y " + maxInclusive);
                }
            } catch (NumberFormatException e) { // Muestra el mensaje si lo que se introduce por teclado no es un número entero.
                System.out.println("Error: Introduzca un número válido.");
            }
        }
        return num; // Al salir del while envia el número porque cumple los parametros solicitados.
    }

    /**
     * Comprueba que se introduce un nombre válido sin símbolos y con una longitud mínima de 3 caracteres.
     *
     * @return ->Retorna el nombre cuando cumpla las condiciones
     *
     */
    public static String correctName() {
        String name;
        do {
            System.out.print("Introduzca su nombre (mínimo 3 caracteres, solo letras y espacios): ");
            try {
                name = sc.nextLine().trim(); // coge el nombre y le saca los espacios que pueda haber antes y despues antes de comprobar.
            } catch (Exception e) {
                System.out.println("Error al leer el nombre. Inténtelo de nuevo.");
                name = ""; // Reinicia para que el bucle continúe
            }
        } while (name.length() < 3 || !name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")); // comprueba que la longitud del nombre sea mayor de 3 y que contenga los caracteres especificados.
        return name; // Retorna el nombre cuando cumpla las condiciones.
    }

    /**
     * Comprueba si el número que introduce el jugador es válido con respecto al último número
     * @param lastNumber ->Último número usado.
     * @param numberAtStake ->Número introducido en ese turno para el juego
     * @return ->Retorna True o False, si el número a comprobar es válido respecto al último número en juego.
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
                        ||                        // OR lógico, si se cumple 1 de las condiciones la salida sera True.
                lastPosition[1] == newPosition[1]; // comprueba que estén en la misma columna.
    }

    /**
     * Nos da la posición del número en la Matriz.
     *
     * @param number ->Número del que queremos saber en que posicion se encuentra en la matriz diseñada.
     * @return ->Retorna la posición (fila, columna) del número a comprobar.
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
