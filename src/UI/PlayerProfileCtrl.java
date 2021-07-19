package UI;

import Code.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class PlayerProfileCtrl {
    private Player p;
    @FXML
    private Label name;
    @FXML
    private Label country;
    @FXML
    private Label club;
    @FXML
    private Label position;
    @FXML
    private Label number;
    @FXML
    ListView details;
//    Image img;


    public void init(Player p){
        name.setText(p.getName());
        country.setText(p.getCountry());
        club.setText(p.getClub());
        position.setText(p.getPosition());
        number.setText("#"+p.getNumber());
        details.getItems().add("age: "+String.valueOf(p.getAge()));
        details.getItems().add("height: "+String.valueOf(p.getHeight()));
        details.getItems().add(("weekly-salary: "+String.valueOf(p.getWeeklySalary())));
    }

    public void Sell(ActionEvent actionEvent) {

    }
}
