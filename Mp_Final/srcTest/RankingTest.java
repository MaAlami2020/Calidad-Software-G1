import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RankingTest {

    @Test
    void addRanking() {
        Character aux = new Character();
        aux.setWins(10);
        Character aux2 = new Character();
        aux2.setWins(100);
        Character aux3 = new Character();
        aux3.setWins(1);
        List<Character> charList = new ArrayList<>();
        List<Character> charList2 = new ArrayList<>();
        charList.add(aux2);
        charList2.add(aux2);
        Ranking ranking = new Ranking();
        assertEquals(charList2, ranking.Ranking(charList));

        charList.add(aux3);
        charList.add(aux);
        charList2.add(aux);
        charList2.add(aux3);
        assertEquals(charList2, ranking.Ranking(charList));
    }

    @Test
    //test de integracion
    void showRankingList() {
        //crear DB
        DatabaseManager dbManager = new DatabaseManager();
        Map<String, User> databaseU = dbManager.obtainDatabaseU();

        //crear usuario
        User newUser1 = new User();
        newUser1.setBanned(false);
        newUser1.setName("user1");
        newUser1.setRegisterNumber("AAAA");

        //crear personajes de los usuarios
        Character character1 = new Character();
        character1.setName("character1");
        character1.setWins(10);

        //crear usuario
        User newUser2 = new User();
        newUser2.setBanned(false);
        newUser2.setName("user2");
        newUser2.setRegisterNumber("BBBB");

        Character character2 = new Character();
        character2.setName("character2");
        character2.setWins(100);

        newUser1.setCharacter(character1);
        newUser2.setCharacter(character2);

        //registrar usuarios en la bd
        databaseU.put(newUser1.getName(), newUser1);
        dbManager.saveDatabaseU(databaseU);
        databaseU.put(newUser2.getName(), newUser2);
        dbManager.saveDatabaseU(databaseU);

        User user1 = databaseU.get(newUser1.getName());
        User user2 = databaseU.get(newUser2.getName());

        //seleccion de una opcion del menu
        Menu menu = new Menu();
        boolean opt = menu.opcionValida("6");
        assertTrue(opt);

        List<Character> charList = new ArrayList<>();
        charList.add(user1.getCharacter());
        charList.add(user2.getCharacter());

        Ranking ranking = new Ranking();

        //ordenar personajes descendentemente por el número de victorias
        List<Character> rankingOrd = ranking.Ranking(charList);

        //mostrar ranking ordenado
        ShowRanking shRanking = new ShowRanking();
        String result = shRanking.createRanking(rankingOrd);

        String rankingListExpected = "[-----------Ranking-----------]";
        rankingListExpected += "Personaje: character2 Victorias: 100";
        rankingListExpected += "Personaje: character1 Victorias: 10";

        assertEquals(result, rankingListExpected);
    }
}