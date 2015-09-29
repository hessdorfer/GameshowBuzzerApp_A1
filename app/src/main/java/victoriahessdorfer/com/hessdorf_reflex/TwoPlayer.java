package victoriahessdorfer.com.hessdorf_reflex;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import java.io.*;


public class TwoPlayer extends AppCompatActivity {

    protected long startTime;
    private static final String FILENAME = "file.sav";

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

        saveInFile(string + ", " + this.getClass().getName());
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

    private void saveInFile(String text) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(text, out);
            fos.write(text.getBytes());
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }


    }




}
