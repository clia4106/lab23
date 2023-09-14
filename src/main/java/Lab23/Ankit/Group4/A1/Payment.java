package Lab23.Ankit.Group4.A1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Payment {

    private List<dish> dishes = new ArrayList<>();
    private double total ;
    private Date paymentDate;
    private int orderNumber; 


    public Payment(List<dish> dishes, double total, Date paymentDate, int orderNumber) {
        this.dishes = dishes;
        this.total = total;
        this.paymentDate = paymentDate;
        this.orderNumber = orderNumber;
    }

    public List<dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<dish> dishes) {
        this.dishes = dishes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
