package victoriahessdorfer.com.hessdorf_reflex;

import java.util.ArrayList;
import android.content.Context;


public class StatisticsManager {
    

    private DataRetention.SinglePlayerObj singlePlayerObj;
    private ArrayList<DataRetention.SinglePlayerObj> arrayALL;
    private ArrayList<DataRetention.SinglePlayerObj> arrayLast10;
    private ArrayList<DataRetention.SinglePlayerObj> arrayLast100;
    private ArrayList<DataRetention.MultiPlayerObj> twoPlayerArray;
    private ArrayList<DataRetention.MultiPlayerObj> threePlayerArray;
    private ArrayList<DataRetention.MultiPlayerObj> fourPlayerArray;

    public class Min {
        long overall = 0;
        long last10 = 0;
        long last100 = 0;
    }

    public class Max {
        long overall = 0;
        long last10 = 0;
        long last100 = 0;
    }
    public class Average {
        long overall = 0;
        long last10 = 0;
        long last100= 0;
    }

    public class Median {
        long overall = 0;
        long last10 = 0;
        long last100 = 0;
    }

    public class TwoPlayer {
        long p1 = 0;
        long p2 = 0;
    }

    public class ThreePlayer {
        long p1 = 0;
        long p2 = 0;
        long p3 = 0;
    }

    public class FourPlayer {
        long p1 = 0;
        long p2 = 0;
        long p3 = 0;
        long p4 = 0;
    }

    public class Combined {
        Min min;
        Max max;
        Average average;
        Median median;
        TwoPlayer twoPlayer;
        ThreePlayer threePlayer;
        FourPlayer fourPlayer;
    }

    public Combined getAll(Context context){

        fillArrays(context);

        Combined combined = new Combined();

        combined.min = getMin();
        combined.max = getMax();
        combined.average = getAverage();
        combined.median = getMedian();

        combined.twoPlayer = getTwoPlayerStats();
        combined.threePlayer = getThreePlayerStats();
        combined.fourPlayer = getFourPlayerStats();

        return combined;

    }

    public void fillArrays(Context context){

        DataRetention d = new DataRetention();
        arrayALL = d.gsonReadSinglePlayer(context);

        singlePlayerObj = d.returnSinglePlayerObj();
        arrayLast10 = d.returnSinglePlayerArray();
        arrayLast100 = d.returnSinglePlayerArray();

        // grab array list of last ten entries
        int i = 0;
        int max = arrayALL.size();
        while (i < max & i < 10) {
            singlePlayerObj.reactionTime = arrayALL.get(max - i - 1).reactionTime;
            arrayLast10.add(singlePlayerObj);
            i++;
        }

        // grab array list of last 100 entries
        i = 0;
        while (i < max & i < 100) {
            singlePlayerObj.reactionTime = arrayALL.get(max - i - 1).reactionTime;
            arrayLast100.add(singlePlayerObj);
            i++;
        }


        twoPlayerArray = d.gsonReadMultiPlayer(context, "TwoPlayer");
        threePlayerArray = d.gsonReadMultiPlayer(context, "ThreePlayer");
        fourPlayerArray = d.gsonReadMultiPlayer(context, "FourPlayer");

    }



    public Min getMin(){

        Min min = new Min();

        // min overall
        int i = 1;
        min.overall = arrayALL.get(0).reactionTime;
        while (i < arrayALL.size()) {
            if (min.overall > arrayALL.get(i).reactionTime) {
                min.overall = arrayALL.get(i).reactionTime;
            }
            i++;
        }

        // min last ten
        i = 1;
        min.last10 = arrayLast10.get(0).reactionTime;
        while (i < arrayLast10.size()) {
            if (min.last10 > arrayLast10.get(i).reactionTime) {
                min.last10 = arrayLast10.get(i).reactionTime;
            }
            i++;
        }

        // min last 100
        i = 1;
        min.last100 = arrayLast100.get(0).reactionTime;
        while (i < arrayLast100.size()) {
            if (min.last10 > arrayLast100.get(i).reactionTime) {
                min.last10 = arrayLast100.get(i).reactionTime;
            }
            i++;
        }

        return min;

    }

    public Max getMax(){

        Max max = new Max();

        // min overall
        int i = 1;
        max.overall = arrayALL.get(0).reactionTime;
        while (i < arrayALL.size()) {
            if (max.overall < arrayALL.get(i).reactionTime) {
                max.overall = arrayALL.get(i).reactionTime;
            }
            i++;
        }

        // min last ten
        i = 1;
        max.last10 = arrayLast10.get(0).reactionTime;
        while (i < arrayLast10.size()) {
            if (max.last10 < arrayLast10.get(i).reactionTime) {
                max.last10 = arrayLast10.get(i).reactionTime;
            }
            i++;
        }

        // min last 100
        i = 1;
        max.last100 = arrayLast100.get(0).reactionTime;
        while (i < arrayLast100.size()) {
            if (max.last10 < arrayLast100.get(i).reactionTime) {
                max.last10 = arrayLast100.get(i).reactionTime;
            }
            i++;
        }

        return max;

    }

    public Average getAverage(){

        Average ave = new Average();

        // min overall
        int i = 1;
        ave.overall = arrayALL.get(0).reactionTime;
        while (i < arrayALL.size()) {
            ave.overall = ave.overall + arrayALL.get(i).reactionTime;
            i++;
        }
        ave.overall = (ave.overall / i);

        // min last ten
        i = 1;
        ave.last10 = arrayLast10.get(0).reactionTime;
        while (i < arrayLast10.size()) {
            ave.last10 = ave.last10 + arrayLast10.get(i).reactionTime;
            i++;
        }
        ave.last10 = (ave.last10 / i);

        // min last 100
        i = 1;
        ave.last100 = arrayLast100.get(0).reactionTime;
        while (i < arrayLast100.size()) {
            ave.last100 = ave.last100 + arrayLast100.get(i).reactionTime;
            i++;
        }
        ave.last100 = (ave.last100 / i);

        return ave;

    }

    public Median getMedian(){

        Median median = new Median();

        median.overall = 0;
        median.last10 = 0;
        median.last100 = 0;

        return median;

    }

    public TwoPlayer getTwoPlayerStats(){
        TwoPlayer twoPlayer = new TwoPlayer();

        int i = 0;
        while (i < twoPlayerArray.size()) {
            switch (twoPlayerArray.get(i).winner){
                case "Player One":
                    twoPlayer.p1++;
                    break;
                case "Player Two":
                   twoPlayer.p2++;
            }
            i++;
        }

        return twoPlayer;
    }

    public ThreePlayer getThreePlayerStats(){
        ThreePlayer threePlayer = new ThreePlayer();

        int i = 0;
        while (i < threePlayerArray.size()) {
            switch (threePlayerArray.get(i).winner){
                case "Player One":
                    threePlayer.p1++;
                    break;
                case "Player Two":
                    threePlayer.p2++;
                    break;
                case "Player Three":
                    threePlayer.p3++;
            }
            i++;
        }

        return threePlayer;
    }

    public FourPlayer getFourPlayerStats(){
        FourPlayer fourPlayer = new FourPlayer();

        int i = 0;
        while (i < fourPlayerArray.size()) {
            switch (fourPlayerArray.get(i).winner){
                case "Player One":
                    fourPlayer.p1++;
                    break;
                case "Player Two":
                    fourPlayer.p2++;
                    break;
                case "Player Three":
                    fourPlayer.p3++;
                    break;
                case "Player Four":
                    fourPlayer.p4++;
            }
            i++;
        }

        return fourPlayer;
    }






}
