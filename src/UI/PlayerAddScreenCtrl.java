package UI;
import Code.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PlayerAddScreenCtrl {
    private Main main;
    FootballAssociation fa;
    @FXML
    private TextField name;
    @FXML
    private TextField club;
    @FXML
    private TextField country;
    @FXML
    private TextField age;
    @FXML
    private TextField number;
    @FXML
    private TextField height;
    @FXML
    private TextField ws;
    @FXML
    private Label invalidName;
    @FXML
    private Label invalidClub;
    @FXML
    private Label invalidCountry;
    @FXML
    private Label invalidAge;
    @FXML
    private Label invalidNumber;
    @FXML
    private Label invalidHeight;
    @FXML
    private Label invalidWs;
    @FXML
    private ChoiceBox position;
    @FXML
    private Label resultLabel;



    public void setMain(Main main) {
        this.main = main;
    }

    public void init(FootballAssociation fa){
        this.fa=fa;
        position.getItems().addAll("Forward","Midfielder","Defender","Goalkeeper");
        invalidName.setText("");
        invalidWs.setText("");
        invalidNumber.setText("");
        invalidClub.setText("");
        invalidHeight.setText("");
        invalidAge.setText("");
        invalidCountry.setText("");
    }

    public void nameP(ActionEvent actionEvent) {
        if(Utility.isNameType(name.getText()))
            invalidName.setText("");
        else
            invalidName.setText("Invalid format, only alphabets");
    }

    public void countryP(ActionEvent actionEvent) {
        if(Utility.isNameType(country.getText()))
            invalidCountry.setText("");
        else
            invalidCountry.setText("Invalid format, only alphabets");

    }

    public void ageP(ActionEvent actionEvent) {
        if(Utility.isInt(age.getText()))
            invalidAge.setText("");
        else
            invalidAge.setText("Invalid format");
    }

    public void heightP(ActionEvent actionEvent) {
        if(Utility.isDouble(height.getText()))
            invalidHeight.setText("");
        else
            invalidHeight.setText("Invalid format");
    }

    public void clubP(ActionEvent actionEvent) {
        if(!Utility.isNameType(club.getText()))
            invalidClub.setText("Invalid format, only alphabets");
        else
            invalidClub.setText("");
    }

    public void numberP(ActionEvent actionEvent) {
        if(Utility.isInt(number.getText()))
            invalidNumber.setText("");
        else
            invalidNumber.setText("Invalid format");
    }

    public void wsP(ActionEvent actionEvent) {
        if(Utility.isDouble(ws.getText()))
            invalidWs.setText("");
        else
            invalidWs.setText("Invalid format");
    }

    public void addButt(ActionEvent actionEvent) {
        invalidName.setText("");
        invalidWs.setText("");
        invalidNumber.setText("");
        invalidClub.setText("");
        invalidHeight.setText("");
        invalidAge.setText("");
        invalidCountry.setText("");
        Player p=new Player();
        boolean tmp=true;
        String Position=(String) position.getValue();
        if(Position==null){
            tmp=false;
        }
        else{
            p.setPosition(Position);
        }
        if(Utility.isNameType(name.getText()) && !name.getText().equals(""))
            p.setName(Utility.polishString(name.getText()));
        else{
            invalidName.setText("Invalid format, only alphabets");
            tmp=false;
        }
        if(Utility.isNameType(club.getText()) && !club.getText().equals(""))
            p.setClub(Utility.polishString(club.getText()));
        else{
            invalidClub.setText("Invalid format, only alphabets");
            tmp=false;
        }

        if(Utility.isNameType(country.getText())&&  !country.getText().equals(""))
            p.setCountry(Utility.polishString(country.getText()));
        else{
            invalidCountry.setText("Invalid format, only alphabets");
            tmp=false;
        }
        if(Utility.isInt(age.getText()))
            p.setAge(Integer.parseInt(age.getText()));
        else{
            invalidAge.setText("Invalid format");
            tmp=false;
        }
        if(Utility.isInt(number.getText()))
            p.setNumber(Integer.parseInt(number.getText()));
        else{
            invalidNumber.setText("Invalid format");
            tmp=false;
        }

        if(Utility.isDouble(height.getText()))
            p.setHeight(Double.parseDouble(height.getText()));
        else{
            invalidHeight.setText("Invalid format");
            tmp=false;
        }
        if(Utility.isDouble(ws.getText()))
            p.setWeeklySalary(Double.parseDouble(ws.getText()));
        else{
            invalidWs.setText("Invalid format");
            tmp=false;
        }
        if(tmp){
            int x=fa.addPlayer(p);
            if(x==0){
                resultLabel.setTextFill(Color.GREEN);
                resultLabel.setText("Player Added Successfully");
                p.show();
            }
            else if(x==1){
                resultLabel.setTextFill(Color.RED);
                resultLabel.setText("Can't add. Player with the same name  already available");
            }
            else if(x==2){
                resultLabel.setTextFill(Color.RED);
                resultLabel.setText("Can't add. Club full");
            }
            else if(x==3){
                resultLabel.setTextFill(Color.RED);
                resultLabel.setText("Can't add. Player with same number already in this club");
            }
            else if(x==4){
                resultLabel.setTextFill(Color.RED);
                resultLabel.setText("Can't add. Club full and player with same number already in this club");
            }
        }
        else{
            resultLabel.setText("Failed to add player");
        }

    }

    public void reset(ActionEvent actionEvent) {
        name.clear();
        club.clear();
        country.clear();
        age.clear();
        height.clear();
        number.clear();
        ws.clear();
        invalidName.setText("");
        invalidWs.setText("");
        invalidNumber.setText("");
        invalidClub.setText("");
        invalidHeight.setText("");
        invalidAge.setText("");
        invalidCountry.setText("");
    }

    public void backButton(ActionEvent actionEvent) {
        System.out.println("kala hba");
    }
}
