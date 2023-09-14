package Lab23.Ankit.Group4.A1;

public class dish {
    private int id;
    private String name;
    private double price;
    private int quantity = 1; 
    private String description;  // 新的属性

    public dish(int id, String name,  String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description; 
        this.price = price;
         // 初始化描述
    }

    // Copy constructor 
    public dish(dish other) {
        this.id = other.id;
        this.name = other.name;
        this.price = other.price;
        this.description = other.description;
        this.quantity = other.quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public boolean decreaseQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
            return true;
        } else {
            return false; 
        }
    }
    
    public double getTotalPrice() {
        return this.price * this.quantity;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return name + (quantity > 1 ? " x " + quantity : "");
    }
}