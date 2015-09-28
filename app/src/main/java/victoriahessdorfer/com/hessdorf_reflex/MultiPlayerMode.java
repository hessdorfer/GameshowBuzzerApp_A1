package victoriahessdorfer.com.hessdorf_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultiPlayerMode extends AppCompatActivity {

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
                intent = new Intent(this, TwoPlayer.class);
                startActivity(intent);
                break;
            case "Three Players":
                intent = new Intent(this, ThreePlayer.class);
                startActivity(intent);
                break;
            case "Four Players":
                intent = new Intent(this, FourPlayer.class);
                startActivity(intent);
                break;
        }

    }




}
