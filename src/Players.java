/**
 * Objeto que guarda los nombres de los jugadores y el número de partidas perdidas en el juego para cada jugador.
 */

public class Players {
    //  Atributos
    private final String NAME;
    private int lostGames;

    //  Getters y Setters
    public String getNAME() {
        return NAME;
    }

    public int getLostGames() {
        return lostGames;
    }

    //  Constructor
    public Players(String NAME) {
        this.NAME = NAME;
        this.lostGames = 0;
    }

    //  Métodos

    /**
     * Metodo que incrementa en uno el número de partidas perdidas de un jugador, cuando este pierde.
     */
    public void addLostGames(){
        this.lostGames ++;
    }
}

