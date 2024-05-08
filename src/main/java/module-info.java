module com.chessgame.chessgamejava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.chessgame.chessgamejava to javafx.fxml;
    exports com.chessgame.chessgamejava;
}