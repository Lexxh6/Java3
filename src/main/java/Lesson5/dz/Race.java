package Lesson5.dz;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private boolean winner;
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
    synchronized boolean isWinner (){
        return !winner ? winner=true : false;
    }
}