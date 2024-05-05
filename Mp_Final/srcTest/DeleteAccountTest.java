import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteAccountTest {
     @Test
     void deleteAccount(){
          //crear DB
          DatabaseManager dbManager = new DatabaseManager();
          Map<String, User> databaseU = dbManager.obtainDatabaseU();
          Map<String, History> databaseH = dbManager.obtainDatabaseH();
          //crear usuario 1
          User newUser1 = new User();
          newUser1.setBanned(false);
          newUser1.setName("user1");
          newUser1.setRegisterNumber("AAAA");
          //crear personaje del usuario 1
          Character character1 = new Character();
          character1.setName("character1");
          character1.setWins(10);
          //asignar al usuario 1 su personaje creado
          newUser1.setCharacter(character1);
          //crear usuario 2
          User newUser2 = new User();
          newUser2.setBanned(false);
          newUser2.setName("user2");
          newUser2.setRegisterNumber("BBBB");
          //crear personaje del usuario 2
          Character character2 = new Character();
          character2.setName("character2");
          character2.setWins(100);
          //asignar al usuario 1 su personaje creado
          newUser2.setCharacter(character2);
          //registrar usuarios en la bd
          databaseU.put(newUser1.getName(), newUser1);
          dbManager.saveDatabaseU(databaseU);
          databaseU.put(newUser2.getName(), newUser2);
          dbManager.saveDatabaseU(databaseU);

          User user1 = databaseU.get(newUser1.getName());
          User user2 = databaseU.get(newUser2.getName());

          //crear una nueva partida
          Match match = new Match(user1.getCharacter(), user2.getCharacter());

          //registrar partida en el historial de partidas
          History history = new History();
          history.appendMatch(match);

          //insertar historial en la bd
          databaseH.put(newUser1.getName(), history);
          dbManager.saveDatabaseH(databaseH);

          DeleteAccount dlAccount = new DeleteAccount();
          //borrar cuenta de la bd
          boolean result = dlAccount.removeFromDBs(dbManager, databaseU, databaseH, user1);
          //verificar borrado de cuenta
          assertTrue(result);
     }
     /*@Test
    void deleteAccount() {
         DatabaseManager databaseManager =new DatabaseManager();
         Map<String, User> databaseU = new HashMap<>();
         Map<String, User> databaseUaux = new HashMap<>();
         User user = new User();
         user.setName("AAAA");
         DeleteAccount deleteAccount = new DeleteAccount();
         String[] entradas = {"N"};
         String ch = String.join(System.lineSeparator(), entradas) + System.lineSeparator();
         InputStream in = new ByteArrayInputStream(ch.getBytes());
         System.setIn(in);

         databaseUaux = databaseManager.obtainDatabaseU();
         databaseU = databaseManager.obtainDatabaseU();
         databaseU.put(user.getName(), user);
         databaseManager.saveDatabaseU(databaseU);

         boolean userauxDeleted = deleteAccount.DeleteAccount(user);
         assertTrue(userauxDeleted);

         String[] entradas2 = {"S", "a"};
         ch = String.join(System.lineSeparator(), entradas2) + System.lineSeparator();
         in = new ByteArrayInputStream(ch.getBytes());
         System.setIn(in);

         boolean userDeleted = deleteAccount.DeleteAccount(user);
         databaseU = databaseManager.obtainDatabaseU();
         assertTrue(userDeleted);
         assertEquals(databaseUaux.size(), databaseU.size());

    }*/

}