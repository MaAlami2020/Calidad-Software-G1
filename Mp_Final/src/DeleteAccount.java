import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DeleteAccount  implements Serializable {

    private DatabaseManager databaseManager = new DatabaseManager();
    private Map<String, User> databaseU = new HashMap<>();
    private Map<String, Character> databaseC = new HashMap<>();
    private final Map<String, History> databaseH = new HashMap<>();

    public boolean DeleteAccount(User u) {
        boolean removed = false;
        System.out.println("¿Seguro que quiere eliminar la cuenta? (S/N)");
        Scanner scan = new Scanner(System.in);
        String sure = scan.nextLine();

        if (sure.equalsIgnoreCase("S")) {
            databaseU = databaseManager.obtainDatabaseU();
            // Eliminar los personajes asociados al usuario de la base de datos C
            DeleteCharacter delCh = new DeleteCharacter();
            delCh.deleteCharacter2(u);

            removed = removeFromDBs(databaseManager, databaseU, databaseH, u);

            // Volver al principio
            System.out.println("<<Cuenta eliminada correctamente>>");
            return removed;
        } else {
            return removed;
        }
    }

    public boolean removeFromDBs(DatabaseManager databaseManager, Map<String, User> databaseU, Map<String, History> databaseH, User u){
        // Obtener el nombre de usuario del usuario que se va a eliminar
        String username = u.getName();
        //String regnum = u.getRegisterNumber();

        // Eliminar el usuario de la base de datos U
        boolean removed = databaseU.remove(username,u);
        //u = databaseU.get(username);

        // Eliminar el historial del usuario de la base de datos H
        History h = databaseH.remove(username);

        // Guardar las bases de datos actualizadas
        databaseManager.saveDatabaseU(databaseU);
        databaseManager.saveDatabaseH(databaseH);
        return removed;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Map<String, User> getDatabaseU() {
        return databaseU;
    }

    public void setDatabaseU(Map<String, User> databaseU) {
        this.databaseU = databaseU;
    }

    public Map<String, Character> getDatabaseC() {
        return databaseC;
    }

    public void setDatabaseC(Map<String, Character> databaseC) {
        this.databaseC = databaseC;
    }


    private  void welcome(){
        Welcome welcome = new Welcome();
        welcome.Welcome();
    }
}
