import java.util.Scanner;

public class Game {
    private static final int CIFRA_MINIMA = 10, CIFRA_MAXIMA = 99, NUM_MIN_JUEGO = 1, NUM_MAX_JUEGO = 9;;
    private static final String FIN = "Fin de la partida, ha perdido ", TURNO = "\nEs el turno de ";
    private static int ultimoNumero = 0 , totalEnJuego, numeroEnJuego, numeroMaximo;
    private static Players[] orderOfPlayers;
    static Scanner sc = new Scanner(System.in);

    public static void play(){
        clear();
        orderOfPlayers = Program.shufflePlayers(); // Desordenar antes de cada partida
        OrderPlayers();
        numeroMaximo = CheckValues.selectMax("Introduce la cifra máxima para el juego, debe estar entre 10 y 99,o pulsa -1 para que el juego decida una aleatoria: ",CIFRA_MINIMA,CIFRA_MAXIMA);
        System.out.println("El número maximo es " + numeroMaximo);
        boolean finDePartida = false;
        while (!finDePartida){
            for (int i = 0; i < orderOfPlayers.length; i++ ){

                Program.mostrarMatriz();
                System.out.println();
                mensajeInfo();
                System.out.println("\nEs el turno de " + orderOfPlayers[i].getName() + ".\n");
                numeroEnJuego = CheckValues.correctInteger("Introduce un número del 1 al 9 pra jugar: ", NUM_MIN_JUEGO, NUM_MAX_JUEGO);
                    if (ultimoNumero == 0){
                        ultimoNumero = numeroEnJuego;
                        totalEnJuego = numeroEnJuego;
                    } else {

                        while (!CheckValues.comprobarSiSonValidos(ultimoNumero,numeroEnJuego)){
                            numeroEnJuego = CheckValues.correctInteger("Número no valido, prueba otro: ", NUM_MIN_JUEGO, NUM_MAX_JUEGO);
                        }
                            totalEnJuego = totalEnJuego + numeroEnJuego;
                            ultimoNumero = numeroEnJuego;
                            if (totalEnJuego >= numeroMaximo) {
                                System.out.println(FIN + orderOfPlayers[i].getName());
                                orderOfPlayers[i].addLostGames();
                                anotherPlay();
                            }
//                            finDePartida = false;
                    }
                }
        }

    }

    private static void OrderPlayers() {
        System.out.println("Para que el juego sea mas ameno se desordenará el orden de los jugadores en cada partida\n" +
                           "                            Hasta que termine el juego\n");
        System.out.println("El orden de esta partida va a ser el siguiente: ");
        int contador = 1;
        for(Players player : orderOfPlayers){
            System.out.println(contador +".- " + player.getName());
            contador ++;
        }
        System.out.println();
    }


    public static void anotherPlay() {
            int respuesta = CheckValues.correctInteger("\n¿Quieres jugar otra partida? 1 para Si o 0 para no: ",0,1);
            if (respuesta == 0) {
                Program.printData();
                System.exit(0);
            } else {
            ultimoNumero = 0;
            totalEnJuego = 0;
            play(); // Jugar una partida
        }
    }
    /**
     * Mensaje de información del juego.
     */
    private static void mensajeInfo(){
        System.out.println("El ultimo número es |  " + ultimoNumero + "  | el total esta en |  " + totalEnJuego + "  | el juego finaliza en |  " + numeroMaximo + "  |");
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
