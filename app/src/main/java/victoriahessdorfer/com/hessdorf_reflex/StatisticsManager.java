package victoriahessdorfer.com.hessdorf_reflex;

import java.util.ArrayList;
import android.content.Context;
import java.util.Collections;

/**
 * Created by victoria on 2015-10-03.
 */
public class StatisticsManager {

    private DataRetention.SinglePlayerObj singlePlayerObj;
    private ArrayList<DataRetention.SinglePlayerObj> arrayALL;
    private ArrayList<DataRetention.SinglePlayerObj> arrayLast10;
    private ArrayList<DataRetention.SinglePlayerObj> arrayLast100;

    public class Min {
        long overall;
        long last10;
        long last100;
    }

    public class Max {
        long overalll;
        long last10;
        long last100;
    }
    public class Median {
        long overalll;
        long last10;
        long last100;
    }

    public class Mean {
        long overalll;
        long last10;
        long last100;
    }

    public void getAll(Context context, String saveType){

        fillArrays(context, saveType);

        Min min = getMin(context, saveType);

    }

    public void fillArrays(Context context, String saveType){

        DataRetention d = new DataRetention();
        arrayALL = d.gsonReadSinglePlayer(context);

        // grab array list of last ten entries
        int i = 0;
        int max = arrayALL.size();
        while (i < max & i < 10) {
            singlePlayerObj.reactionTime = arrayALL.get(max - i).reactionTime;
            arrayLast10.add(singlePlayerObj);
            i++;
        }

        // grab array list of last 100 entries
        i = 0;
        while (i < max & i < 100) {
            singlePlayerObj.reactionTime = arrayALL.get(max - i).reactionTime;
            arrayLast100.add(singlePlayerObj);
            i++;
        }

    }



    public Min getMin(Context context, String saveType){

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



}
