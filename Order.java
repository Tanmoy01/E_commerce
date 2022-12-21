package main.e_commerce;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Order {

    void placeOrder(String productID) throws SQLException {
        ResultSet res= main.connection.executeQuery("Select max(orderID) as orderID from orders");
        int orderID =1;
        if(res.next()){
            orderID= res.getInt("orderID") + 1;
        }
        //Date date= new Date(Calendar.getInstance().getTime().getTime());
        Timestamp ts= new Timestamp(Calendar.getInstance().getTime().getTime());
        String query = String.format("Insert Into Orders values(%s, %s, '%s', '%s')"
                ,orderID,productID,main.emailId, ts);
        int response= main.connection.executeUpdate(query);
        if(response>0){
            Dialog<String> dialog= new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type= new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Your Order is Placed");
            dialog.showAndWait();
            //System.out.println("The order is placed");
        }
        else{
            System.out.println("The order is not placed");
        }
    }
}
