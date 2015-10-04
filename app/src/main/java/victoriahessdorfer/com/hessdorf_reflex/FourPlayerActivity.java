package victoriahessdorfer.com.hessdorf_reflex;

import android.app.AlertDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class FourPlayerActivity extends AppCompatActivity {

    /*
        This class handles the basic button presses in the four
        player activity, and calls the DataRetentionHandler to
        implement saving.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_player);

    }

    public void chosenButton(View view) {

        Button button = (Button) view;
        String string = (String) button.getText();

        // use DataRetentionHandler to save
        DataRetentionHandler dataRetentionHandler = new DataRetentionHandler();
        dataRetentionHandler.gsonAddMultiPlayer(string, getApplicationContext(), "FourPlayerSave");
        string = string + " won!";

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
