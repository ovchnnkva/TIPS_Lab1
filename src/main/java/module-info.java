module com.example.tips_lab1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tips_lab1 to javafx.fxml;
    exports com.example.tips_lab1;
}