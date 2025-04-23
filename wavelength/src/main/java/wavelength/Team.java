package wavelength;

public class Team {
    private String teamName;
    private int teamPoints;

    public Team(String teamName){
        this.teamName = teamName;
        this.teamPoints = 0;
    }

    public String getName(){
        return teamName;
    }

    public int getPoints(){
        return teamPoints;
    }

    public void addPoints(int addedPoints){
        teamPoints += addedPoints;
    }
}
