import java.io.Serializable;
import java.util.Map;
import java.util.Scanner;

public class Login implements Serializable {
    
    private DatabaseManager databaseManager = new DatabaseManager();
    private Map<String, User> databaseU = databaseManager.obtainDatabaseU();

    private User user;
    private volatile boolean truepassword = false;
    public Login(User user) {
    }

    public Login(){}

    public void Login(String username, String password){
        //databaseManager = new DatabaseManager();
        Scanner input = new Scanner(System.in);

        //databaseU = databaseManager.obtainDatabaseU();
        boolean valid = false ;
        //User user;
        TUser tipouser = TUser.ADMIN;
        System.out.print("\n<><><><><><><><><><><><><><><><><><><>\n");
        System.out.println("\n[-------------------------------------]");
        System.out.println("                LOGIN");
        System.out.println("[-------------------------------------]\n");
        do {
            do {
                do {
                    System.out.print("--> Introduce tu nombre (escribe 'salir' para salir): ");
                    username = input.nextLine();
                    System.out.print("--> Introduce tu contraseña: ");
                    password = input.nextLine();
                    if(!truepassword){
                        System.out.println("    <<Contraseña incorrecta>>");
                    }
                    if (username.equalsIgnoreCase("salir")){
                        this.welcome();
                        break;
                    }
                    log(username,password);
                    if(databaseU.get(username) == null){
                        System.out.println("               <<Nombre no existente>>");
                    }
                }while(databaseU.get(username) == null);


            }while(!truepassword);

            if (!this.user.isBanned()) {
                System.out.print("    <<Login correcto>>");
                System.out.println();
                valid = true;


                if (user.getUsertype() == TUser.ADMIN) {

                    menuAdmin(user);

                } else {
                    menu(user);

                }

            } else {
                System.out.print("Usuario baneado");
            }
        } while (!valid);
    }

    public boolean log(String username, String password) {
        this.user = databaseU.get(username);
        if(this.user != null){
            if(user.getPassword().equals(password)){
                truepassword = true;
            }
        }
        return truepassword;
    }

    public String menuAdminDevolver(User u){
        String menu = "\n[-------------------------------------]";
        menu += "           MENU ADMINISTRADOR";
        menu += "[-------------------------------------]\n";
        menu += "1. Eliminar Administrador";
        menu += "2. Editar Personaje";
        menu += "3. Validar Desafío";
        menu += "4. Agregar Característica";
        menu += "5. Banear Usuario";
        menu += "6. Salir";
        menu += "Seleccione una opción: ";

        return menu;
    }

    private void menuAdmin(User u){
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.MenuAdmin(u);

    }

    private void menu(User u){
        Menu menu = new Menu();
        menu.Menu(u);

    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public void welcome(){
        Welcome welcome = new Welcome();
        welcome.Welcome();

    }
}
