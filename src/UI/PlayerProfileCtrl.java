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
import javafx.scene.image.ImageView;
import util.NetworkUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayerProfileCtrl {
    public Button sellforu;
    public ImageView img;
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
    Image image;


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
        if(main.getMarketListPlayers().contains(p)){
            sellforu.setDisable(true);
        }
        String tmp="/images/Players/"+p.getName()+".png";
        image = new Image(getClass().getResourceAsStream(tmp));

        //imageView = new ImageView(image);
        img.setImage(image);
        //Setting the position of the image
//        imageView.setX(0);
//        imageView.setY(4);

        //setting the fit height and width of the image view
//        imageView.setFitHeight(291);
//        imageView.setFitWidth(245);
//
//        //Setting the preserve ratio of the image view
//        imageView.setPreserveRatio(false);
    }

    public void Sell(ActionEvent actionEvent) {
        transferPlayer tp = new transferPlayer("forSell");
        tp.setFromClub(main.getClub());
        tp.setPlayer(p);
        try {
            main.getNetworkUtil().write(tp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sellforu.setDisable(true);
    }
}
