module main.e_commerce {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main.e_commerce to javafx.fxml;
    exports main.e_commerce;
}