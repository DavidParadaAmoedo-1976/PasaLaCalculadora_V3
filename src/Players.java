public class Players {
    private String name;
    private int lostGames;


    public String getName() {
        return name;
    }


    public int getLostGames() {
        return lostGames;
    }

    public Players(String name) {
        this.name = name;
        this.lostGames = 0;
    }

    public void addLostGames(){
        this.lostGames ++;
    }

    public void GamerList(){

    }
}

