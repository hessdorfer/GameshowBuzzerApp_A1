package victoriahessdorfer.com.hessdorf_reflex;

import android.app.AlertDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/*
* This is the superclass for all multi player levels.
*/

public class MultiPlayerSuper extends AppCompatActivity {

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
