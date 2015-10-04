package victoriahessdorfer.com.hessdorf_reflex;

import android.content.Intent;
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

public class MultiPlayerModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player_mode);
    }

    public void chosenButton(View view) {
        Button button = (Button) view;
        String string = (String) button.getText();

        Intent intent;
        switch (string) {
            case "Two Players":
                intent = new Intent(this, TwoPlayerActivity.class);
                startActivity(intent);
                break;
            case "Three Players":
                intent = new Intent(this, ThreePlayerActivity.class);
                startActivity(intent);
                break;
            case "Four Players":
                intent = new Intent(this, FourPlayerActivity.class);
                startActivity(intent);
                break;
        }

    }




}
