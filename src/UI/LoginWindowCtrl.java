package UI;

import Clients.writeThread;
import Code.Club;
import Transferables.transferLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.NetworkUtil;


public class LoginWindowCtrl {
    private Main main;
    NetworkUtil nu;
    @FXML
    private TextField clubName;
    @FXML
    private Label error;

    public void setMain(Main main) {
        this.main = main;
    }

    public void init(){
        error.setText("");
    }

    public void goButt(ActionEvent actionEvent) {
        String name=clubName.getText();
                try {
                    nu=new NetworkUtil("127.0.0.1",33333);
                    transferLogin tl=new transferLogin(name);
                    nu.write(tl);
                    transferLogin tmp=(transferLogin) nu.read();

                    if(tmp.getResponse().equals("Accepted")){
                        error.setText("");
                        Club club=tmp.getClub();
                        main.showClubHome(club,nu);
                    }
                    else if(tmp.getResponse().equals("Exists")){
                        error.setText("Already logged in");
                        nu.closeConnection();
                    }
                    else{
                        error.setText("Club not found");
                        nu.closeConnection();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
