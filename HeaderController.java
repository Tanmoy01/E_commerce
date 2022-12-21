package main.e_commerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
    @FXML
    public void initialize(){
        if(!main.emailId.equals("")){
            loginbutton.setOpacity(0);
            email.setText(main.emailId);
        }
    }
    @FXML
    Button loginbutton,logoutbutton;

    @FXML
    Label email;

    @FXML
    TextField searchtext;

    @FXML
    public  void login(MouseEvent e) throws IOException {
        AnchorPane LoginPage= FXMLLoader.load((getClass().getResource("LoginPage.fxml")));
        main.root.getChildren().add(LoginPage);
    }

    @FXML
    public void search(MouseEvent e) throws IOException, SQLException {
        ProductPage productPage= new ProductPage();

        Header header= new Header();
        AnchorPane productPane= new AnchorPane();
        productPane.getChildren().add(productPage.productsbySearch(searchtext.getText()));
        productPane.setLayoutX(150);
        productPane.setLayoutY(150);
        main.root.getChildren().clear();
        main.root.getChildren().addAll(header.root, productPane);
    }
    @FXML
    public void logoutapper(MouseEvent e){
        if(logoutbutton.getOpacity()==0){
            logoutbutton.setOpacity(1);
        }
        else{
            logoutbutton.setOpacity(0);
        }
    }
    @FXML
    public void logout(MouseEvent e) throws IOException {
        if(logoutbutton.getOpacity()==1) {
            main.emailId = "";
            logoutbutton.setOpacity(0);
            Header header = new Header();
            main.root.getChildren().add(header.root);
        }
    }
}
