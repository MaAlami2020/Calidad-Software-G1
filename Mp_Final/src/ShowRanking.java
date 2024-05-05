import java.io.Serializable;
import java.util.*;

public class ShowRanking implements Serializable {
    private Map<String, Character> databaseR = new HashMap<>();
    private DatabaseManager databaseManager = new DatabaseManager();
    private final Ranking rank = new Ranking();


    public void ShowRanking(){
        databaseR = databaseManager.obtainDatabaseC();

        List<Character> characters = new ArrayList<>(databaseR.values());
        characters = rank.Ranking(characters);
        String ranking = createRanking(characters);
        System.out.println(ranking);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public String createRanking(List<Character> characters){
        String rankingList = "[-----------Ranking-----------]";
        for (Character c : characters) {
            rankingList += "Personaje: " + c.getName() + " Victorias: " + c.getWins();
        }
        return rankingList;
    }
    public Map<String, Character> getDatabaseR() {
        return databaseR;
    }

    public void setDatabaseR(Map<String, Character> databaseR) {
        this.databaseR = databaseR;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
