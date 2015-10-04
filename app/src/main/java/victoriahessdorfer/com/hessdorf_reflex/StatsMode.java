package victoriahessdorfer.com.hessdorf_reflex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Objects;

public class StatsMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_mode);

        fillTable();
    }


    private void fillTable(){

        StatisticsManager stats = new StatisticsManager();


        StatisticsManager.Combined combined = stats.getAll(getApplicationContext());

        final TextView mTextView = (TextView) findViewById(R.id.P5);
        mTextView.setText(Long.toString(combined.min.overall));

        /*
        public class Combined
            Min min;
            Max max;
            Average average;
            Median median;
            TwoPlayer twoPlayer;
            ThreePlayer threePlayer;
            FourPlayer fourPlayer;
        }
        */


    }


}
