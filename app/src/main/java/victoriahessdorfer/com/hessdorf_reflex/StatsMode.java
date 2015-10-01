package victoriahessdorfer.com.hessdorf_reflex;

import android.app.AlertDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class StatsMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_mode);

        fillTable();

    }


    private void fillTable(){
        String string;

        DataRetention dataRetention = new DataRetention();
        ArrayList<DataRetention.MultiPlayerObj> multiObj = new ArrayList<DataRetention.MultiPlayerObj>();
        multiObj = dataRetention.MultiPlayerReadData(getApplicationContext());

        string = "";

        for (int i = 0; i < multiObj.size(); i++) {
            string = "mode: " + multiObj.get(i).mode + " winner: " + multiObj.get(i).winner ;
            if (multiObj.size() != i - 2 )
                string = string + " ** ";
        }


        final AlertDialog infoDialog = new AlertDialog.Builder(this).create();
        infoDialog.setMessage(string);
        infoDialog.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                infoDialog.dismiss();
            }
        }, 10000);

    }


}
