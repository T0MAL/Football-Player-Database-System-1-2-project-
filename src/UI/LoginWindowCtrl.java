package UI;

import Clients.writeThread;
import Code.Club;
import Transferables.transferLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.NetworkUtil;


public class LoginWindowCtrl {
    private Main main;
    NetworkUtil nu;
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
                    nu=new NetworkUtil("127.0.0.1",33333);
                    transferLogin tl=new transferLogin(name);
                    nu.write(tl);
                    transferLogin tmp=(transferLogin) nu.read();

                    if(tmp.getResponse().equals("Accepted")){
                        Club club=tmp.getClub();
                        main.showClubHome(club,nu);
                    }
                    else if(tmp.getResponse().equals("Exists")){
                        System.out.println("Exists");
                        nu.closeConnection();
                    }
                    else{
                        System.out.println("Cant login");
                        nu.closeConnection();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
