package UI;

import Code.FootballAssociation;

public class PlayerSearchMenuCtrl {
    private Main main;
    FootballAssociation fa;

    public void setMain(Main main){
        this.main=main;
    }

    public void init(FootballAssociation fa){
        this.fa=fa;
    }
}
