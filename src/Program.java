import java.util.Random;

public class Program {

    private static int numberOfPlayers;
    private static Players players[];
    static final int[][] MATRIZ = {
            {7, 8, 9},
            {4, 5, 6},
            {1, 2, 3}
    };

    public static void main(String[] args) {
        enterNames();

        Game.play(); // Aquí se juega y se reordena en cada partida


    }

    public static void printData() {
        System.out.println("\nEl resultado del juego es:");
        for(Players player : players){
            int contador = 1;
            System.out.println(contador + ".- " + player.getName() + " ten " + player.getLostGames() + "partidas perdidas.");
        }
    }

    public static void enterNames() {
        numberOfPlayers = CheckValues.correctInteger("Introduce o número de xogadores ata un máximo de 10: ",2,10);
        players = new Players[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++ ){

            String name = CheckValues.correctName();
            System.out.println("Nombre leído para xogador " + (i + 1) + ": " + name);
            players[i] = new Players(name);
        }
    }

    public static Players[] shufflePlayers() {
        Players[] orderOfGame = new Players[numberOfPlayers];

        // Copiar manualmente los jugadores al nuevo array
        for (int i = 0; i < numberOfPlayers; i++) {
            orderOfGame[i] = players[i];
        }

        // Mezclar manualmente usando algoritmo de Fisher-Yates
        Random rand = new Random();
        for (int i = numberOfPlayers - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Intercambiar shuffled[i] con shuffled[j]
            Players temp = orderOfGame[i];
            orderOfGame[i] = orderOfGame[j];
            orderOfGame[j] = temp;
        }
        return orderOfGame;
    }

    public static void mostrarMatriz(){
        for (int i=0; i <3; i++) {
            System.out.print("\t");
            for (int j = 0; j < 3; j++) {
                System.out.print(MATRIZ[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}

