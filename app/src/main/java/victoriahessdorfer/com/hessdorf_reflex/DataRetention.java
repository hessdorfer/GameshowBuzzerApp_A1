package victoriahessdorfer.com.hessdorf_reflex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


import android.content.Context;

import java.io.*;
import java.util.ArrayList;

/**
 * http://developer.android.com/reference/android/content/Context.html
 * https://github.com/joshua2ua/lonelyTwitter/blob/master/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java#L59
 */

public class DataRetention {

   private static final String MultiPlayerFilename = "singlePlayer.sav";
   private static final String SinglePlayerFilename = "MultiPlayer.sav";

    class MultiPlayerObj {
        public String winner;
        public String mode;
    }

    public void SinglePlayerSave(String text, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(SinglePlayerFilename, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(text, out);
            fos.write(text.getBytes());
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void MultiPlayerSave(String winner, String mode, Context context) {

        MultiPlayerObj obj = new MultiPlayerObj();
        obj.winner = winner;
        obj.mode = mode;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(obj));


        try {
            FileOutputStream fos = context.openFileOutput(MultiPlayerFilename, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            //Gson gson = new Gson();
            gson.toJson(obj, out);
            //fos.write(text.getBytes());
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String SinglePlayerReadData(Context context) {


        String string;
        string = "";

        try {
            FileInputStream fis = context.openFileInput(SinglePlayerFilename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                string = string + line + " ";
                line = in.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return string;
    }

    public String MultiPlayerReadData(Context context) {

        ArrayList<MultiPlayerObj> multiObj = new ArrayList<MultiPlayerObj>();

        String string;
        string = "";

        try {
            FileInputStream fis = context.openFileInput(MultiPlayerFilename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                string = string + line + " ";
                line = in.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return string;
    }
}
