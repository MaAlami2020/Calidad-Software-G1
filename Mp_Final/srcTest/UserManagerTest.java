import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    @Test
    void login(){
        //verifico que los datos existen en la bd, verificar que se introdujeron los datos correctamente, mostrar pantalla menu
        DatabaseManager dbManager = new DatabaseManager();
        Map<String, User> databaseU = dbManager.obtainDatabaseU();

        User newUser = new User();
        newUser.setBanned(false);
        newUser.setName("Harrison");
        newUser.setPassword("pass123");
        newUser.setRegisterNumber("AAAA");

        databaseU.put(newUser.getName(), newUser);
        dbManager.saveDatabaseU(databaseU);
        User user = databaseU.get(newUser.getName());

        Login login = new Login();
        boolean result = login.log(user.getName(),"");
        assertFalse(result);
    }
    @Test//navegacion entre pantallas
    void menu(){
        //mostrar pantalla de la opcion
        DatabaseManager dbManager = new DatabaseManager();
        Map<String, User> databaseU = dbManager.obtainDatabaseU();

        User newUser = new User();
        newUser.setBanned(false);
        newUser.setName("Harrison");
        newUser.setPassword("pass123");
        newUser.setRegisterNumber("AAAA");

        databaseU.put(newUser.getName(), newUser);
        dbManager.saveDatabaseU(databaseU);
        User user = databaseU.get(newUser.getName());

        Menu menu = new Menu();

        String result = menu.detallesPantalla(user, "7");

        String rulesExpected = "Reglas";
        rulesExpected += "1. Cada jugador debe elegir un personaje para pelear, con habilidades, equipo, y esbirros.";
        rulesExpected += "2. El objetivo del juego es reducir la barra de vida del oponente a cero.";
        rulesExpected += "3. Para poder pelear le deberas mandar un desafío a quién quieras desafiar ";
        rulesExpected += "4. No podrás mandar un desafío a un jugador que haya perdido en el último día o podrás ser baneado ";
        rulesExpected += "5. La pelea se divide en rondas, donde cada jugador tira un dado para realizar una accion, en caso de éxito se comparan potenciales y el que tenga mas daño que defensa al segundo jugador le quita un pinto de vida";
        rulesExpected += "6. Cada personaje tiene unas caracteristicas unicas que varían a lo largo de la pelea";

        assertEquals(result, rulesExpected);
    }
    @Test
    void salir(){
        Menu menu = new Menu();
        boolean result = menu.opcionValida("0");
        assertTrue(result);
    }

    @Test
    void userManager() {
        DatabaseManager databaseManager = new DatabaseManager();
        Map<String, User> databaseU;
        UserManager userManager = new UserManager();
        User user = new User();


        user.setBanned(false);
        user.setName("Atahualpa");
        user.setRegisterNumber("AAAA");
        databaseU = databaseManager.obtainDatabaseU();
        databaseU.put(user.getName(), user);
        databaseManager.saveDatabaseU(databaseU);


        String[] entradas = {"1","2", "Atahualpa" ,"4"};
        String ch = String.join(System.lineSeparator(), entradas) + System.lineSeparator();



        InputStream in = new ByteArrayInputStream(ch.getBytes());
        System.setIn(in);

        userManager.UserManager();


        assertEquals(true, databaseManager.obtainDatabaseU().get(user.getName()).isBanned());


        databaseManager.obtainDatabaseU().get(user.getName()).setBanned(true);
        databaseManager.obtainDatabaseU().get(user.getName()).isBanned();
        String[] entradas2 = {"2", "Atahualpa" ,"4"};
        ch = String.join(System.lineSeparator(), entradas2) + System.lineSeparator();
        in = new ByteArrayInputStream(ch.getBytes());
        System.setIn(in);
        userManager.UserManager();
    }
}