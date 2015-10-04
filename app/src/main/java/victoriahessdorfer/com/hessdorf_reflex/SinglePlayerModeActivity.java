package victoriahessdorfer.com.hessdorf_reflex;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;

import java.util.Random;

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




public class SinglePlayerModeActivity extends AppCompatActivity {

    /*
        This class handles the single player mode functionality, including calling the
        DataRetentionHandler to save, showing initial information, displaying time taken and
        complaining if the button was pressed too soon.

        Used the android developer guide for help with dialogs and handlers:
            http://developer.android.com/guide/topics/ui/dialogs.html
            http://developer.android.com/reference/android/os/Handler.html

        Used stack overflow with help on button colors:
            http://stackoverflow.com/questions/11176365/how-do-i-change-the-text-color-of-a-button
            by user ρяσѕρєя K, answered Jun 24 '12 at 9:37 and retrieved on October 1st 2015


     */

    protected long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // first time it's created - should show dialog instructions box
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_mode);

        showSinglePlayerInfo();

    }

    protected void showSinglePlayerInfo() {

        AlertDialog infoDialog = new AlertDialog.Builder(this).create();

        infoDialog.setTitle("How To:");

        infoDialog.setMessage("React quickly - hit the button when it turns Green!");

        infoDialog.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                waitForGame();
            }

        });

        // Showing Alert Message
        infoDialog.show();

    }

    protected void waitForGame(){

        int upperBound = 2000;
        int lowerBound = 10;

        Random delayNum = new Random();
        int i = delayNum.nextInt(upperBound - lowerBound) + lowerBound;

        final Button button = (Button) findViewById(R.id.click_quick_button);
        button.setTextColor(Color.BLACK);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setTextColor(Color.GREEN);
                startTime = System.currentTimeMillis();
            }
        }, i);

    }

    protected void displayTimeTaken(long endTime){

        String string;
        if (endTime == -1) {
            string = "Too fast! Try again.";
        } else {
            string = "Your score was: " + (endTime - startTime) + " ms.";
            DataRetentionHandler dataRetentionHandler = new DataRetentionHandler();
            dataRetentionHandler.gsonAddSinglePlayer(endTime - startTime, getApplicationContext());
        }

        final AlertDialog infoDialog = new AlertDialog.Builder(this).create();
        infoDialog.setMessage(string);
        infoDialog.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                infoDialog.dismiss();
                waitForGame();
            }
        }, 1500);

    }

    public void singlePlayerButtonClick(View view){

        // if buttonColor != Green then complain and restart
        final Button button = (Button) findViewById(R.id.click_quick_button);

        int colorGreen = -16711936;
        int currentColor = button.getCurrentTextColor();

        if (currentColor != colorGreen){
            displayTimeTaken(-1);
        } else {
            long endTime = System.currentTimeMillis();
            displayTimeTaken(endTime);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

