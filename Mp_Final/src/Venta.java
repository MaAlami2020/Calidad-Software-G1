public class Venta {
    private Oferta oferta;
    public Venta(Oferta oferta){
        this.oferta = oferta;
    }

    public double perform(){
        User user = oferta.getVendedor();
        DatabaseManager databaseManager = new DatabaseManager();
        int oro = databaseManager.obtainDatabaseU().get(user.getName()).getCharacter().getGoldValue();
        double ganancia = oro + oferta.getPrecioVenta();
        databaseManager.obtainDatabaseU().get(user.getName()).getCharacter().setGoldValue((int)ganancia);
        return ganancia;
    }
}
