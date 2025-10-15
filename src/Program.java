import java.util.Random;

public class Program {

    private static int numberOfPlayers;
    private static Players players[];
    private static final int MAX_OF_PLAYERS = 10, MIN_OF_PLAYERS = 2;
    static final int[][] MATRIZ = {
            {7, 8, 9},
            {4, 5, 6},
            {1, 2, 3}
    };

    public static void main(String[] args) {
        enterNames();
        Game.play(); // Aquí se juega y se reordena en cada partida
    }

    /**
     * Imprime los datos de los jugadores y el número de partidas perdidas.
     */
    public static void printData() {
        System.out.println("\nEl resultado del juego es:");
        int contador = 1;
        for(Players player : players){
            System.out.println(contador + ".- " + player.getName() + " tiene " + player.getLostGames() + " partidas perdidas.");
            contador ++;
        }
    }

    /**
     * Recoge los nombres de todos los jugadores y los guarda en una lista de "objetos". trata a cada jugador como si fuese un objeto.
     */
    public static void enterNames() {
        numberOfPlayers = CheckValues.correctInteger("Introduce el número de jugadores hasta un máximo de 10: ",MIN_OF_PLAYERS, MAX_OF_PLAYERS);
        players = new Players[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++ ){
            String name = CheckValues.correctName();
            System.out.println("Nombre leído para el jugador" + (i + 1) + ": " + name);
            players[i] = new Players(name);
        }
    }

    /**
     * Recibe la lista de los jugadores, la desordena y la guarda en otra lista para jugar la partida.
      * @return
     */
    public static Players[] shufflePlayers() {
        Players[] orderOfGame = new Players[numberOfPlayers]; // Inicializa la nueva lista.
        for (int i = 0; i < numberOfPlayers; i++) { // Recorre la lista
            orderOfGame[i] = players[i];            // realiza una copia.
        }
        Random rand = new Random(); // Inicializa la Clase Ramdon.
        for (int i = numberOfPlayers - 1; i > 0; i--) { // Recorre la lista hacia atras para evitar un error que produce "nextInt(0)"
            int j = rand.nextInt(i + 1); // Realiza un random entre 1 y el numero de jugadores
            Players temp = orderOfGame[i];      //
            orderOfGame[i] = orderOfGame[j];    // Intercambia la posicion del nombre en la lista por la posicion aleatoria.
            orderOfGame[j] = temp;              //
        }
        return orderOfGame; // Devuelve la lista en el nuevo orden.
    }

    /**
     * Imprime la matriz en la consola.
     */
    public static void showMatrix(){
        for (int i=0; i <3; i++) {  // Recorre las filas.
            System.out.print("\t");
            for (int j = 0; j < 3; j++) {   // Recorre las columnas.
                System.out.print(MATRIZ[i][j] + " "); // Imprime el valor en la posición de la matriz.
            }
            System.out.print("\n");
        }
    }
}

