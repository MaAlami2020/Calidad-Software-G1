public class Oferta {
    private Minion minion;
    private double precioVenta;
    private User vendedor;

    public Oferta() {}

    public Minion getMinion() {
        return minion;
    }

    public void setVendedor(User user){
        this.vendedor = user;
    }

    public User getVendedor(){
        return this.vendedor;
    }
    public void setMinion(Minion esbirroT) {
        this.minion = esbirroT;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecio(double precioVenta) {
        this.precioVenta = precioVenta;
    }

}
