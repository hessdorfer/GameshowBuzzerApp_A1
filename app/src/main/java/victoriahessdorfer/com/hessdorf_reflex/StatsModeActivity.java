package victoriahessdorfer.com.hessdorf_reflex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

public class StatsModeActivity extends AppCompatActivity {

    /*
        This class handles the statistics mode activity. It fills
        the table with the correct data, and uses the Statistics Manager
        to do so. It also outsources the email sending and clearing statistics
        code.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_mode);

        fillTable();
    }


    private void fillTable(){

        StatisticsManager stats = new StatisticsManager();

        StatisticsManager.Combined combined = stats.getAll(getApplicationContext());

        // Minimums

        TextView mTextView = (TextView) findViewById(R.id.P5);
        mTextView.setText(Long.toString(combined.min.overall));

        mTextView = (TextView) findViewById(R.id.P6);
        mTextView.setText(Long.toString(combined.min.last10));

        mTextView = (TextView) findViewById(R.id.P7);
        mTextView.setText(Long.toString(combined.min.last100));

        // Maximums

        mTextView = (TextView) findViewById(R.id.P9);
        mTextView.setText(Long.toString(combined.max.overall));

        mTextView = (TextView) findViewById(R.id.P10);
        mTextView.setText(Long.toString(combined.max.last10));

        mTextView = (TextView) findViewById(R.id.P11);
        mTextView.setText(Long.toString(combined.max.last100));

        // Averages

        mTextView = (TextView) findViewById(R.id.P13);
        mTextView.setText(Long.toString(combined.average.overall));

        mTextView = (TextView) findViewById(R.id.P14);
        mTextView.setText(Long.toString(combined.average.last10));

        mTextView = (TextView) findViewById(R.id.P15);
        mTextView.setText(Long.toString(combined.average.last100));

        // Medians

        mTextView = (TextView) findViewById(R.id.P17);
        mTextView.setText(Long.toString(combined.median.overall));

        mTextView = (TextView) findViewById(R.id.P18);
        mTextView.setText(Long.toString(combined.median.last10));

        mTextView = (TextView) findViewById(R.id.P19);
        mTextView.setText(Long.toString(combined.median.last100));

        // Two Player

        mTextView = (TextView) findViewById(R.id.P26);
        mTextView.setText(Long.toString(combined.twoPlayer.p1));

        mTextView = (TextView) findViewById(R.id.P27);
        mTextView.setText(Long.toString(combined.twoPlayer.p2));

        // Three Player

        mTextView = (TextView) findViewById(R.id.P31);
        mTextView.setText(Long.toString(combined.threePlayer.p1));

        mTextView = (TextView) findViewById(R.id.P32);
        mTextView.setText(Long.toString(combined.threePlayer.p2));

        mTextView = (TextView) findViewById(R.id.P33);
        mTextView.setText(Long.toString(combined.threePlayer.p3));

        // Four Player

        mTextView = (TextView) findViewById(R.id.P36);
        mTextView.setText(Long.toString(combined.fourPlayer.p1));

        mTextView = (TextView) findViewById(R.id.P37);
        mTextView.setText(Long.toString(combined.fourPlayer.p2));

        mTextView = (TextView) findViewById(R.id.P38);
        mTextView.setText(Long.toString(combined.fourPlayer.p3));

        mTextView = (TextView) findViewById(R.id.P39);
        mTextView.setText(Long.toString(combined.fourPlayer.p4));


    }

    private String parseDataAsString(){

        String s = "";

        StatisticsManager stats = new StatisticsManager();

        StatisticsManager.Combined combined = stats.getAll(getApplicationContext());

        s = s + ">>Reaction Time \n >All Values: \n";

        s = s + "Minimum: " + combined.min.overall + ", Maximum: " + combined.max.overall + "\n";
        s = s + "Average: " + combined.average.overall + ", Median: " + combined.median.overall + "\n";

        s = s + ">Last 10 Values: \n";

        s = s + "Minimum: " + combined.min.last10 + ", Maximum: " + combined.max.last10 + "\n";
        s = s + "Average: " + combined.average.last10 + ", Median: " + combined.median.last10 + "\n";


        s = s + ">Last 100 Values: \n";

        s = s + "Minimum: " + combined.min.last100 + ", Maximum: " + combined.max.last100 + "\n";
        s = s + "Average: " + combined.average.last100 + ", Median: " + combined.median.last100 + "\n";

        s = s + ">>Buzzer Presses: \n";

        s = s + ">Two Player: \n P1: " + combined.twoPlayer.p1 + ", P2: " + combined.twoPlayer.p2 + "\n";

        s = s + ">Three Player: \n P1: " + combined.threePlayer.p1 + ", P2: " + combined.threePlayer.p2 +
                ", P3: " + combined.threePlayer.p3 + "\n";

        s = s + ">Four Player: \n P1: " + combined.fourPlayer.p1 + ", P2: " + combined.fourPlayer.p2 +
                ", P3: " + combined.fourPlayer.p3 + ", P4: " + combined.fourPlayer.p4 + "\n";

        return s;
    }

    public void clearTable(View view) {

        DataRetentionHandler d = new DataRetentionHandler();
        d.gsonClear(getApplicationContext());
        fillTable();
    }

    public void createEmail(View view){
        EmailHandler h = new EmailHandler();
        String bodyString = parseDataAsString();
        h.composeEmail(view, bodyString);


    }


}
