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
        clear();
        orderOfPlayers = Program.shufflePlayers(); // Desordenar antes de cada partida
        OrderPlayers();
        maxNumber = CheckValues.correctInteger("Introduce el límite para la partida, debe estar entre 10 y 99, o pulsa -1 para que el juego decida una cifra aleatoria: ", LIMIT_MIN, LIMIT_MAX, true);
        System.out.println("El número maximo es " + maxNumber);
        while (endGame) {
            for (Players orderOfPlayer : orderOfPlayers) { // Recorre todos los elementos del array "orderOfPlayer"
                Program.showMatrix();
                System.out.println();
                mensajeInfo();
                System.out.println("\nEs el turno de " + orderOfPlayer.getName() + ".\n");
                int numberAtStake = CheckValues.correctInteger("Introduce un número del 1 al 9 para jugar: ", NUM_MIN_PLAY, NUM_MAX_PLAY,false);
                if (lastNumber == 0) {
                    lastNumber = numberAtStake;
                    totalInPlay = numberAtStake;
                } else {
                    while (!CheckValues.comprobarSiSonValidos(lastNumber, numberAtStake)) {
                        numberAtStake = CheckValues.correctInteger("Número no valido, prueba otro: ", NUM_MIN_PLAY, NUM_MAX_PLAY,false);
                    }
                    totalInPlay = totalInPlay + numberAtStake;
                    lastNumber = numberAtStake;
                    if (totalInPlay >= maxNumber) {
                        clear();
                        mensajeInfo();
                        System.out.println("\nFin de la partida, " + orderOfPlayer.getName() + " ha perdido la partida.");
                        orderOfPlayer.addLostGames();
                        anotherPlay();
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
        int respuesta = CheckValues.correctInteger("\n¿Quieres jugar otra partida? " +
                "\n\t1.- Jugar otra vez." +
                "\n\t0.- Salir" +
                "\nElige opción: ", 0, 1, false);
        if (respuesta == 0) {
            Program.printData();
            System.exit(0);
        } else {
            lastNumber = 0;
            totalInPlay = 0;
            play(); // Jugar una partida
        }
    }

    /**
     * Mensaje de información del juego.
     */
    private static void mensajeInfo() {
        System.out.println("El último número es |  " + lastNumber + "  | el total esta en |  " + totalInPlay + "  | el juego finaliza en |  " + maxNumber + "  |");
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
