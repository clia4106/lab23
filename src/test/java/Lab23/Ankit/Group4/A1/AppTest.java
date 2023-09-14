package Lab23.Ankit.Group4.A1;



import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.Scanner;

class AppTest {

    @Test
    public void testMain(){
        String text = "50\n7\n5\n";
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.scanner = new Scanner(bis);
        App.start();

        
    }

    @Test
    public void testInitDish() {
        App.initDish();
        Map<Integer, dish> dishesMap = App.dishesMap;
        for (Integer key: dishesMap.keySet()) {
            System.out.println(key+":"+dishesMap.get(key));
        }
    }



    @Test
    public void testAdminLoginAndDisplayMain(){
        String text = "Admin\n123456789";
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminLogin(new Scanner(bis));
        App.displayMain();
        text = "Admin\n12345678";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminLogin(new Scanner(bis));
        App.displayMain();
    }


    @Test
    public void testAdminPanel(){
        Map<Integer, dish> dishesMap = App.dishesMap;
        for (Integer key: dishesMap.keySet()) {
            System.out.println(key+":"+dishesMap.get(key));
        }
        System.out.println("---------------add---------------------------");
        String text = "1\n888\ntest\ntest add Item\n10";
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminPanel(new Scanner(bis));

        App.initDish();
        dishesMap = App.dishesMap;
        for (Integer key: dishesMap.keySet()) {
            System.out.println(key+":"+dishesMap.get(key));
        }
        System.out.println("---------------modify---------------------------");
        text = "3\n888\nmodify\ntest modify Item\n100";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminPanel(new Scanner(bis));
        App.initDish();
        dishesMap = App.dishesMap;
        for (Integer key: dishesMap.keySet()) {
            System.out.println(key+":"+dishesMap.get(key));
        }
        System.out.println("---------------delete---------------------------");
        text = "2\n888";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminPanel(new Scanner(bis));
        App.initDish();
        dishesMap = App.dishesMap;
        for (Integer key: dishesMap.keySet()) {
            System.out.println(key+":"+dishesMap.get(key));
        }
        System.out.println("---------------register---------------------------");
        text = "4\n1test\n1234567890";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminPanel(new Scanner(bis));
        text = "4\ntest\n123";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminPanel(new Scanner(bis));
        text = "4\n \n ";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminPanel(new Scanner(bis));

        text = "4\ntest\n1234567890";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminPanel(new Scanner(bis));
        text = "test\n1234567890";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminLogin(new Scanner(bis));
        assert (App.isAdminLoggedIn ==true);
        System.out.println("---------------order statistics---------------------------");
        text = "5\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminPanel(new Scanner(bis));
        text = "8\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminPanel(new Scanner(bis));
        text = "6\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        App.adminPanel(new Scanner(bis));
        
    }

    @Test
    public void testLoadOrderDetailsByOrderNumber(){
        String order = MenuOperations.loadOrderDetailsByOrderNumber(1);
        System.out.println(order == null ? "Order Not Found":order);
    }

    @Test
    public void testDishOperation(){
        dish dish = new dish(888,"test","test dish operation",100);
        dish.increaseQuantity();
        System.out.println(dish);
        dish.decreaseQuantity();
        System.out.println(dish.getQuantity());
    }

    @Test
    public void testSaveCurrentOrderNumber(){
        MenuOperations.currentOrderNumber = 10;
        MenuOperations.saveCurrentOrderNumber();
    }

    @Test
    public void testLoadCurrentOrder(){
        MenuOperations.loadCurrentOrderNumber();
        System.out.println(MenuOperations.currentOrderNumber);
    }

    @Test
    public void testDisplayMenu(){
        App.initDish();
        String text = "0\n";
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.DisplayMenu(App.dishesMap,App.ShowOrdered);
        text = "1\n0\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.DisplayMenu(App.dishesMap,App.ShowOrdered);
        for (dish dish : App.ShowOrdered){
            System.out.println(dish);
        }
        text = "8\n0\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.DisplayMenu(App.dishesMap,App.ShowOrdered);
    }

    @Test
    public void testShowOrder(){
        App.initDish();
        String text = "1\n0\n";
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.ShowOrdered(App.dishesMap,App.ShowOrdered);
        text = "2\n5\n3\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.ShowOrdered(App.dishesMap,App.ShowOrdered);
        text = "1\n0\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.DisplayMenu(App.dishesMap,App.ShowOrdered);
        text = "2\n1\n3\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.ShowOrdered(App.dishesMap,App.ShowOrdered);
    }

    @Test
    public void testPaymentAndHistoryOrder(){
        App.initDish();
        String text = "1\n0\n";
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.historyOrder(App.paymentList);
        text = "1\n0\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.DisplayMenu(App.dishesMap,App.ShowOrdered);
        text = "1\n2\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.processPayment(App.ShowOrdered,App.paymentList);
        text = "2\n1\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.processPayment(App.ShowOrdered,App.paymentList);
        text = MenuOperations.currentOrderNumber+"\n0\n";
        bis = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));
        MenuOperations.scanner = new Scanner(bis);
        MenuOperations.historyOrder(App.paymentList);
    }

    @Test
    public void testIsFileEmpty() {
        System.out.println(MenuOperations.isFileEmpty("adminOutput.txt"));
    }
}
