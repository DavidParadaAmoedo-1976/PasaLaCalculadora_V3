import java.util.Scanner;

public class Game {
    private static final int LIMIT_MIN = 10, LIMIT_MAX = 99, NUM_MIN_PLAY = 1, NUM_MAX_PLAY = 9;;
    private static int lastNumber = 0 , totalInPlay, numberAtStake, maxNumber;
    private static Players[] orderOfPlayers;
    static Scanner sc = new Scanner(System.in);

    /**
     * Cambia el orden de los jugadores y muestra el orden en el que van a jugar, coge el número para el límite de la partida.
     * Realiza todas las funciones del juego para cada jugador ( presenta la matriz a cada jugador, le indica cual es el último número,
     * el total en el que va el juego y donde esta el límite, solicita el número para jugar y realiza todas las comprobaciones y
     * operaciones, tambien presenta el mensaje del fin de la partida.
     */
    public static void play(){
        clear();
        orderOfPlayers = Program.shufflePlayers(); // Desordenar antes de cada partida
        OrderPlayers();
        maxNumber = CheckValues.selectMax("Introduce el limite para la partida, debe estar entre 10 y 99, o pulsa -1 para que el juego decida uno aleatorio: ",LIMIT_MIN,LIMIT_MAX);
        System.out.println("El número maximo es " + maxNumber);
        while (true){
            for (int i = 0; i < orderOfPlayers.length; i++ ){
                Program.showMatrix();
                System.out.println();
                mensajeInfo();
                System.out.println("\nEs el turno de " + orderOfPlayers[i].getName() + ".\n");
                numberAtStake = CheckValues.correctInteger("Introduce un número del 1 al 9 pra jugar: ", NUM_MIN_PLAY, NUM_MAX_PLAY);
                    if (lastNumber == 0){
                        lastNumber = numberAtStake;
                        totalInPlay = numberAtStake;
                    } else {
                        while (!CheckValues.comprobarSiSonValidos(lastNumber, numberAtStake)){
                            numberAtStake = CheckValues.correctInteger("Número no valido, prueba otro: ", NUM_MIN_PLAY, NUM_MAX_PLAY);
                        }
                            totalInPlay = totalInPlay + numberAtStake;
                            lastNumber = numberAtStake;
                            if (totalInPlay >= maxNumber) {
                                System.out.println("\nFin de la partida, " + orderOfPlayers[i].getName() + " ha perdido la partida.");
                                orderOfPlayers[i].addLostGames();
                                anotherPlay();
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
        for(Players player : orderOfPlayers){   // for each, recorre todos los nombres de la lista de 1 en 1.
            System.out.println(counter +".- " + player.getName());  // Imprime el nombre de la lista.
            counter ++; // incrementa el contador, no es necesario contador, solo lo utilizo para poner un indice a los nombres por estética.
        }
        System.out.println();
    }

    /**
     * Pregunta si quieres jugar otra partida o salir del juego, si la respuesta es si inicializa las variables del juego
     */
    public static void anotherPlay() {
        clear();
        int respuesta = CheckValues.correctInteger("\n¿Quieres jugar otra partida? " +
                "\n\t1.- Jugar otra vez." +
                "\n\t0.- Salir" +
                "Elige opción: ",0,1);
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
    private static void mensajeInfo(){
        System.out.println("El ultimo número es |  " + lastNumber + "  | el total esta en |  " + totalInPlay + "  | el juego finaliza en |  " + maxNumber + "  |");
    }

    /**
     * Una forma peculiar de borrar la pantalla, no he encontrado ninguna instrucción que borre la consola.
     */
    static void clear(){
        for (int i = 0; i < 50; i++) {  // Bucle con 50 líneas en blanco,
            System.out.println();       // para simular un borrado de pantalla.
        }
    }
}
