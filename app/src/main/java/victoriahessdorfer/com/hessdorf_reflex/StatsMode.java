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
