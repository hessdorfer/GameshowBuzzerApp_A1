package victoriahessdorfer.com.hessdorf_reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.ActionBar;

import java.util.Random;

public class TwoPlayer extends AppCompatActivity {

    protected long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

    }

    public void chosenButton(View view) {
        Button button = (Button) view;
        String string = (String) button.getText();

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
