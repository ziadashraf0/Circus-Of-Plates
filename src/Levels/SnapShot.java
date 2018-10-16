package Levels;

/**
 * Created by AHMED ESSAM on 5/14/2017.
 */
public class SnapShot {
    private int score;
    private String Name;
    public void setScore(int score){
        this.score=score;
    }
    public int getScore() {
        return score;
    }

    public String getName() {
        return Name;
    }

    public SnapShot(int score, String Name) {
        this.score=score;
        this.Name=Name;

    }

}

