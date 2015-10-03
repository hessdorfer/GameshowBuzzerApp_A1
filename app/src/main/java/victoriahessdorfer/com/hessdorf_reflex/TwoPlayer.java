package victoriahessdorfer.com.hessdorf_reflex;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;


public class TwoPlayer extends AppCompatActivity {

    protected long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

    }

    public void chosenButton(View view) {
        // I can't extend another class, so i'm creating an instance of my superclass
        // and calling its method.

        Button button = (Button) view;
        String string = (String) button.getText();

        DataRetention dataRetention = new DataRetention();
        dataRetention.gsonAddMultiPlayer(string, getApplicationContext(), "TwoPlayer");
        string = string + " won!";

        // record who won, and what mode it was in

        final AlertDialog infoDialog = new AlertDialog.Builder(this).create();
        infoDialog.setMessage(string);
        infoDialog.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                infoDialog.dismiss();
            }
        }, 1500);


    }




}
