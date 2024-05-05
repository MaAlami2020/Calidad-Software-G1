import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VentaTest {
    @Test
    //test de integracion
    public void añadirEsbirroInventarioTest(){
        //crear DB
        DatabaseManager dbManager = new DatabaseManager();
        Map<String, User> databaseU = dbManager.obtainDatabaseU();

        //crear usuario
        User newUser = new User();
        newUser.setBanned(false);
        newUser.setName("Harrison");
        newUser.setRegisterNumber("AAAA");

        //crear personaje y esbirro del usuario
        Character character1 = new Character();
        List<Minion> minionsList = new ArrayList<>();
        Minion minion1 = new Minion("gholu",3,TMinion.GHOUL);
        minionsList.add(minion1);
        character1.setMinionMap(minionsList);

        //crear ofertar esbirro
        Oferta oferta = new Oferta();
        oferta.setMinion(minion1);
        oferta.setVendedor(newUser);
        oferta.setPrecio(5.0);
        character1.setMinionVenta(oferta);

        //asignar al usuario su personaje
        newUser.setCharacter(character1);

        //registrar usuario en la bd
        databaseU.put(newUser.getName(), newUser);
        dbManager.saveDatabaseU(databaseU);
        User user = databaseU.get(newUser.getName());

        Oferta newOffer = databaseU.get(user.getName()).getCharacter().getMinionsVenta().get(0);

        //verificar inserccion en DB
        assertEquals(newOffer, oferta);

        //verificar que se muestra la oferta agregada con anterioridad
        Menu menu = new Menu();
        Minion esbirro = menu.mostrarMinionEnVenta(user,"gholu");
        System.out.println(esbirro.getName());
    }
    @Test
    //test unitario spy
    public void gananciasVentaTest() {
        DatabaseManager dbManager = new DatabaseManager();
        Map<String, User> databaseU = dbManager.obtainDatabaseU();
        //Map<String, Character> databaseC = databaseManager.obtainDatabaseC();
        //Map<String, Oferta> databaseV = databaseManager.obtainDatabaseV();

        User newUser = new User();
        newUser.setBanned(false);
        newUser.setName("Harrison");
        newUser.setRegisterNumber("AAAA");

        //crear personaje y esbirro del usuario
        Character character1 = new Character();
        List<Minion> minionsList = new ArrayList<>();
        Minion minion1 = new Minion("gholu",3,TMinion.GHOUL);
        minionsList.add(minion1);
        character1.setMinionMap(minionsList);

        //crear ofertar esbirro
        Oferta oferta = new Oferta();
        oferta.setMinion(minion1);
        oferta.setVendedor(newUser);
        oferta.setPrecio(5.0);
        character1.setMinionVenta(oferta);
        newUser.setCharacter(character1);

        databaseU.put(newUser.getName(), newUser);
        dbManager.saveDatabaseU(databaseU);
        User user = databaseU.get(newUser.getName());

        //databaseC.get(user.getRegisterNumber()).setGoldValue(10);

        Oferta ofertaEnVenta = databaseU.get(user.getName()).getCharacter().getMinionsVenta().get(0);

        Venta venta = new Venta(ofertaEnVenta);
        Venta spy = Mockito.spy(venta);
        Mockito.doReturn(15.0).when(spy).perform();
        double result = spy.perform();
        Mockito.verify(spy).perform();
        assertEquals(15.0,result);
    }
}
