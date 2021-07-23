package UI;

import Code.Club;
import Code.Player;
import Transferables.transferPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import util.NetworkUtil;

import java.io.IOException;

public class PlayerProfileMarketCtrl {
    public Button buyforu;
    private Main main;
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


    public void setMain(Main main) {
        this.main = main;
    }

    public void init(Player p) {
        this.p=p;
        name.setText(p.getName());
        country.setText(p.getCountry());
        club.setText(p.getClub());
        position.setText(p.getPosition());
        number.setText("#" + p.getNumber());
        details.getItems().add("age: " + String.valueOf(p.getAge()));
        details.getItems().add("height: " + String.valueOf(p.getHeight()));
        details.getItems().add(("weekly-salary: " + String.valueOf(p.getWeeklySalary())));
    }


    public void Buy(ActionEvent event) {
        transferPlayer tp = new transferPlayer("Buy");
        tp.setFromClub(main.getClub());
        tp.setPlayer(p);
        try {
            main.getNetworkUtil().write(tp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buyforu.setDisable(true);
    }
}
