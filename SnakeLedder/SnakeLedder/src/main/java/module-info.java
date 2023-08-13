module com.example.snakeledder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeledder to javafx.fxml;
    exports com.example.snakeledder;
}