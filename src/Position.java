public class Position {

    // add order object from order class


    public Position(int id, String name, String symbol, double basis_price, double basis_money, double quantity, Order[] orders, double stop_price, Boolean is_true_short, Boolean is_commidity) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.basis_price = basis_price;
        this.basis_money = basis_money;
        this.quantity = quantity;
        this.orders = orders;
        this.stop_price = stop_price;
        this.is_true_short = is_true_short;
        
    }

    public Position() {
    }

    public Position(String symbol, double basis_price, double quantity,double basis_money) {
        this.symbol = symbol;
        this.basis_price = basis_price;
        this.quantity = quantity;
        this.basis_money = basis_money;
    }


    int id;
    String name;
    String symbol;
    String equity_classification;
    double position_value;
    double basis_price;
    double basis_money;         //  basis_price * quantity + transaction cost
    double quantity;
    Order orders[];
    double stop_price;
    Boolean is_true_short;


    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public double getPosition_value() {
        return position_value;
    }
    public void setPosition_value(double position_value) {
        this.position_value = position_value;
    }




    public double getBasis_price() {
        return basis_price;
    }

    public void setBasis_price(double basis_price) {
        this.basis_price = basis_price;
    }
    public double getBasis_money() {
        return basis_money;
    }

    public void setBasis_money(double basis_money) {
        this.basis_money = basis_money;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getStop_price() {
        return stop_price;
    }

    public void setStop_price(double stop_price) {
        this.stop_price = stop_price;
    }

    public Boolean getIs_true_short() {
        return is_true_short;
    }

    public void setIs_true_short(Boolean is_true_short) {
        this.is_true_short = is_true_short;
    }












}
