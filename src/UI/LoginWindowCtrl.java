package UI;

import Transferables.transferLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class LoginWindowCtrl {
    private Main main;
    @FXML
    private TextField clubName;

    public void setMain(Main main) {
        this.main = main;
    }

    public void init(){

    }

    public void goButt(ActionEvent actionEvent) {
        String name=clubName.getText();
        try {
            transferLogin tl=new transferLogin(name);
            main.getNetworkUtil().write(tl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}