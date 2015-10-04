package victoriahessdorfer.com.hessdorf_reflex;

import android.app.AlertDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class FourPlayer extends AppCompatActivity {

    protected long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_player);

    }

    public void chosenButton(View view) {
        // I can't extend another class, so i'm creating an instance of my superclass
        // and calling its method.

        //MultiPlayerSuper multiPlayerButton = new MultiPlayerSuper();
        //multiPlayerButton.chosenButton(view);

        Button button = (Button) view;
        String string = (String) button.getText();

        DataRetention dataRetention = new DataRetention();
        dataRetention.gsonAddMultiPlayer(string, getApplicationContext(), "FourPlayer");
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
