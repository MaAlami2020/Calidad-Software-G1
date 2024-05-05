import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ChallengeMenuTest {

    @Test
    //test unitario fake
    void challengeCharacter(){
        FakeServer fakeServer = new FakeServer();
        ChallengeMenu challengeMenu = new ChallengeMenu();
        Map<String, List<Challenge>> databaseP = fakeServer.obtainDatabaseP();

        User defiant = new User();
        User defied = new User();
        defied.setName("jugador2");

        Challenge challenge = new Challenge();
        challenge.setDefiant(defiant);
        challenge.setDefied(defied);
        challenge.setGold(8);
        challenge.setValid(false);
        List<Challenge> challengeList = new ArrayList<>();
        challengeList.add(0, challenge);
        //String aux2 = defied.getRegisterNumber();
        databaseP.put(defied.getName(), challengeList);
        challengeMenu.saveChallenge(fakeServer);
    }

    private class FakeServer extends DatabaseManager{}
    @Test
    void challengeMenu() {
        ChallengeMenu challengeMenu = new ChallengeMenu();
        DatabaseManager databaseManager = new DatabaseManager();
        Map<String, Character> databaseC = databaseManager.obtainDatabaseC();
        User user = new User();
        Map<String, List<Challenge>> databaseP = databaseManager.obtainDatabaseP();
        String[] entradas = {"¬q","qwertyu","yepa","salir"};
        String ch = String.join(System.lineSeparator(), entradas) + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(ch.getBytes());
        System.setIn(in);
        user.setName("yepa");
        user.setRegisterNumber("0000");
        Character character = new Character();
        character.setGoldValue(1000);
        databaseC.put("0000",character);
        challengeMenu.ChallengeMenu(user);


        Map<String, List<Challenge>> aux = databaseManager.obtainDatabaseP();
        assertEquals(aux.get("I87AC").size(),databaseP.get("I87AC").size());

        databaseManager.saveDatabaseC(databaseC);
        String[] entradas2 = {"q","a","-1","2","a"};

        ch = String.join(System.lineSeparator(), entradas2) + System.lineSeparator();
        in = new ByteArrayInputStream(ch.getBytes());
        System.setIn(in);

        challengeMenu.ChallengeMenu(user);



        aux = databaseManager.obtainDatabaseP();

        assertEquals(aux.get("I87AC").size()-1,databaseP.get("I87AC").size());

        Challenge p = aux.get("I87AC").remove(0);
        databaseManager.saveDatabaseP(aux);
        assertFalse(p.isValid());
        assertEquals(p.getDefiant().getName(),user.getName());
        assertEquals(p.getDefied().getRegisterNumber(),"I87AC");
        assertEquals(p.getGold(), 2);
    }
}