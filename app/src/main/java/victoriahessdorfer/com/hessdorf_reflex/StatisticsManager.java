package victoriahessdorfer.com.hessdorf_reflex;

import java.util.ArrayList;
import android.content.Context;
import java.util.Collections;
import java.util.List;

/*
    Copyright 2015 Victoria Hessdorfer

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 */


public class StatisticsManager {

    /*
        This class handles the statistics calculations for both Single and Multiplayer Modes.
        For multi player, it calculates buzzer presses, and for single player it calculates min,
        max, median and average of reaction times. It is all returned in the class Combined.

        Used the android developer guide for help with dialogs and handlers:
            http://developer.android.com/guide/topics/ui/dialogs.html
            http://developer.android.com/reference/android/os/Handler.html

        Used stack overflow with help with median calculation, for general style, did not copy code:
            http://stackoverflow.com/questions/11955728/how-to-calculate-the-median-of-an-array
            User: lynn, answered Aug 14 '12 at 15:37, retrieved on October 4th 2015



     */
    

    private DataRetentionHandler.SinglePlayerObj singlePlayerObj;
    private ArrayList<DataRetentionHandler.SinglePlayerObj> arrayALL;
    private ArrayList<DataRetentionHandler.SinglePlayerObj> arrayLast10;
    private ArrayList<DataRetentionHandler.SinglePlayerObj> arrayLast100;
    private ArrayList<DataRetentionHandler.MultiPlayerObj> twoPlayerArray;
    private ArrayList<DataRetentionHandler.MultiPlayerObj> threePlayerArray;
    private ArrayList<DataRetentionHandler.MultiPlayerObj> fourPlayerArray;

    public class Min {
        public long overall = 0;
        public long last10 = 0;
        public long last100 = 0;
    }

    public class Max {
        public long overall = 0;
        public long last10 = 0;
        public long last100 = 0;
    }
    public class Average {
        public long overall = 0;
        public long last10 = 0;
        public long last100= 0;
    }

    public class Median {
        public long overall = 0;
        public long last10 = 0;
        public long last100 = 0;
    }

    public class TwoPlayer {
        public long p1 = 0;
        public long p2 = 0;
    }

    public class ThreePlayer {
        public long p1 = 0;
        public long p2 = 0;
        public long p3 = 0;
    }

    public class FourPlayer {
        public long p1 = 0;
        public long p2 = 0;
        public long p3 = 0;
        public long p4 = 0;
    }

    public class Combined {
        public Min min;
        public Max max;
        public Average average;
        public Median median;
        public TwoPlayer twoPlayer;
        public ThreePlayer threePlayer;
        public FourPlayer fourPlayer;
    }

    public Combined getAll(Context context){

        fillArrays(context);

        Combined combined = new Combined();


        if (arrayALL.size() != 0) {
            combined.min = getMin();
            combined.max = getMax();
            combined.average = getAverage();
            combined.median = getMedian();
        } else {
            combined.min = new Min();
            combined.max = new Max();
            combined.average = new Average();
            combined.median = new Median();
        }

        if (twoPlayerArray.size() != 0) {
            combined.twoPlayer = getTwoPlayerStats();
        } else {
            combined.twoPlayer = new TwoPlayer();
        }

        if (threePlayerArray.size() != 0) {
            combined.threePlayer = getThreePlayerStats();
        } else {
            combined.threePlayer = new ThreePlayer();
        }

        if (fourPlayerArray.size() != 0) {
            combined.fourPlayer = getFourPlayerStats();
        } else {
            combined.fourPlayer = new FourPlayer();
        }

        return combined;

    }

    public void fillArrays(Context context){

        DataRetentionHandler d = new DataRetentionHandler();
        arrayALL = d.gsonReadSinglePlayer(context);

        singlePlayerObj = d.returnSinglePlayerObj();
        arrayLast10 = d.returnSinglePlayerArray();
        arrayLast100 = d.returnSinglePlayerArray();

        // grab array list of last ten entries
        int i = 0;
        int max = arrayALL.size();
        while (i < max & i < 10) {
            singlePlayerObj = d.returnSinglePlayerObj();
            singlePlayerObj.reactionTime = arrayALL.get(max - i - 1).reactionTime;
            arrayLast10.add(singlePlayerObj);
            i++;
        }

        // grab array list of last 100 entries
        i = 0;
        while (i < max & i < 100) {
            singlePlayerObj = d.returnSinglePlayerObj();
            singlePlayerObj.reactionTime = arrayALL.get(max - i - 1).reactionTime;
            arrayLast100.add(singlePlayerObj);
            i++;
        }

        twoPlayerArray = d.gsonReadMultiPlayer(context, "TwoPlayerSave");
        threePlayerArray = d.gsonReadMultiPlayer(context, "ThreePlayerSave");
        fourPlayerArray = d.gsonReadMultiPlayer(context, "FourPlayerSave");

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
            if (min.last100 > arrayLast100.get(i).reactionTime) {
                min.last100 = arrayLast100.get(i).reactionTime;
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
            if (max.last100 < arrayLast100.get(i).reactionTime) {
                max.last100 = arrayLast100.get(i).reactionTime;
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

        // all

        List<Integer> all = sortList(arrayALL);
        int len = all.size();

        if (len % 2 == 0)
            median.overall = (all.get(len/2) + all.get((len/2)-1))/2;
        else
            median.overall = all.get(len/2);

        // last ten

        all = sortList(arrayLast10);
        len = all.size();

        if (len % 2 == 0)
            median.last10 = (all.get(len/2) + all.get((len/2)-1))/2;
        else
            median.last10 = all.get(len/2);

        // last 100

        all = sortList(arrayLast100);
        len = all.size();

        if (len % 2 == 0)
            median.last100 = (all.get(len/2) + all.get((len/2)-1))/2;
        else
            median.last100 = all.get(len/2);

        return median;

    }

    public List<Integer> sortList(ArrayList<DataRetentionHandler.SinglePlayerObj> o){
        List<Integer> l = new ArrayList<>();

        int i = 0;
        while(i < o.size()){
           l.add((int) o.get(i).reactionTime);
           i++;
        }

        Collections.sort(l);

        return l;
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
