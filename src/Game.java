public class Game {
    private static final int LIMIT_MIN = 10, LIMIT_MAX = 99, NUM_MIN_PLAY = 1, NUM_MAX_PLAY = 9;

    private static int lastNumber = 0;
    private static int totalInPlay;
    private static int maxNumber;
    private static Players[] orderOfPlayers;


    /**
     * Cambia el orden de los jugadores y muestra el orden en el que van a jugar, coge el número para el límite de la partida.
     * Realiza todas las funciones del juego para cada jugador, presenta la matriz a cada jugador, le indica cuál es el último número,
     * el total en el que va el juego y donde está el límite, solicita el número para jugar y realiza todas las comprobaciones y
     * operaciones, también presenta el mensaje del fin de la partida.
     */
    public static void play() {
        boolean endGame = true;
        clear(); // Limpia la pantalla de la consola.
        orderOfPlayers = Program.shufflePlayers(); // Desordenar antes de cada partida
        OrderPlayers();                            // Muestra el orden de los jugadores en esta partida.
        // Guarda en maxNumber el número introducido por el usuario, despues de comprobar que esté dentro del rango y que sea un entero, el true le indica que acepte el -1 para hacerlo aleatorio.
        maxNumber = CheckValues.correctInteger("Introduce el límite para la partida, debe estar entre 10 y 99, o pulsa -1 para que el juego decida una cifra aleatoria: ", LIMIT_MIN, LIMIT_MAX, true);
        System.out.println("El número límite para finalizar la partida  es: " + maxNumber); // Muestra el número en el que va a finalizar la partida.
        while (endGame) {
            for (Players orderOfPlayer : orderOfPlayers) { // Recorre todos los elementos del array "orderOfPlayer"
                Program.showMatrix();   // Muestra la matriz de números.
                System.out.println();
                if (lastNumber == 0){
                    System.out.println("    *** Empieza la partida ***" +
                            "\nEl juego finaliza en: " + maxNumber);
                } else {
                    mensajeInfo();          // Muestra la información de la partida
                }
                System.out.println("\nEs el turno de " + orderOfPlayer.getName() + ".\n");  // Muestra el nombre del jugador que está jugando.
                // Recibe el número en juego del jugador que esta en turno en ese momento.
                int numberAtStake = CheckValues.correctInteger("Introduce un número del 1 al 9 para jugar: ", NUM_MIN_PLAY, NUM_MAX_PLAY,false);
                if (lastNumber == 0) { //si el último número es 0 indica que es el primer número introducido, no comprueba, solo iguala el último número y el total, al número introducido.
                    lastNumber = numberAtStake;
                    totalInPlay = numberAtStake;
                } else {
                    while (!CheckValues.comprobarSiSonValidos(lastNumber, numberAtStake)) { // Si no es el primer número comprueba que cumpla las condiciones del juego.
                        numberAtStake = CheckValues.correctInteger("Número no valido, prueba otro: ", NUM_MIN_PLAY, NUM_MAX_PLAY,false);
                    }
                    totalInPlay = totalInPlay + numberAtStake; //suma el número ya comprobado al total del juego.
                    lastNumber = numberAtStake;     // Indica que ahora este número sera el último introducido, para la comparación en el turno siguiente.
                    if (totalInPlay >= maxNumber) { // Comprueba si el total del juego es mayor o igual al número del límite de la partida.
                        clear();
                        mensajeInfo();
                        System.out.println("\nFin de la partida, " + orderOfPlayer.getName() + " ha perdido esta partida.");  // informa de quien ha perdido la partida si el total del juego a rebasado el límite.
                        orderOfPlayer.addLostGames();   // Añade una partida perdida en el contador del jugador que ha perdido.
                        anotherPlay();      // salta a la opción de seguir jugando.
                        endGame = false;
                    }
                }
            }
        }
    }

    /**
     * Muestra en consola el orden en el que entraran los jugadores en juego.
     */
    private static void OrderPlayers() {
        System.out.println("Para que el juego sea mas ameno se desordenará el orden de los jugadores en cada partida\n" +
                "                            Hasta que termine el juego\n");
        System.out.println("El orden de esta partida va a ser el siguiente: ");
        int counter = 1;    // Inicializa el contador
        for (Players player : orderOfPlayers) {   // for each, recorre todos los nombres de la lista de 1 en 1.
            System.out.println(counter + ".- " + player.getName());  // Imprime el nombre de la lista.
            counter++; // incrementa el contador, no es necesario contador, solo lo utilizo para poner un índice a los nombres por estética.
        }
        System.out.println();
    }

    /**
     * Pregunta si quieres jugar otra partida o salir del juego, si la respuesta es si inicializa las variables del juego
     */
    public static void anotherPlay() {
        System.out.println("El juego consta de varias partidas, los resultados del Juego se mostraran al salir.\n");
        //  Muestra el mensaje y espera respuesta numérica.
        int respuesta = CheckValues.correctInteger("\n¿Quieres jugar otra partida? " +
                "\n\t1.- Jugar otra vez." +
                "\n\t0.- Salir" +
                "\nElige opción: ", 0, 1, false);
        if (respuesta == 0) {
            Program.printData(); // Muestra el resultado del juego.
            System.exit(0); // Sale del juego en estado 0 (Sin fallos de ejecución)
        } else {
            lastNumber = 0; // Inicializa el último número a 0 para otra partida.
            totalInPlay = 0;    // Inicializa el total a 0 para otra partida.
            play(); // Jugar una partida
        }
    }

    /**
     * Mensaje de información del juego.
     */
    private static void mensajeInfo() {
        System.out.println(" El último número es: " + lastNumber +
                           "\n    El total está en: " + totalInPlay +
                           "\nEl juego finaliza en: " + maxNumber);
    }

    /**
     * Una forma peculiar de borrar la pantalla, no he encontrado ninguna instrucción que borre la consola.
     */
    static void clear() {
        for (int i = 0; i < 50; i++) {  // Bucle con 50 líneas en blanco,
            System.out.println();       // para simular un borrado de pantalla.
        }
    }
}
