package victoriahessdorfer.com.hessdorf_reflex;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;


import android.content.Context;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * http://developer.android.com/reference/android/content/Context.html
 * https://github.com/joshua2ua/lonelyTwitter/blob/master/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java#L59
 * http://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
 * http://stackoverflow.com/questions/6484428/java-read-object-input-stream-into-arraylist
 */

public class DataRetention implements Serializable {

    private static final String TwoPlayerFilename = "TwoPlayer.sav";
    private static final String ThreePlayerFilename = "ThreePlayer.sav";
    private static final String FourPlayerFilename = "FourPlayer.sav";
    private static final String SinglePlayerFilename = "SinglePlayer.sav";

    private ArrayList<MultiPlayerObj> multiPlayerObject;
    private ArrayList<SinglePlayerObj> singlePlayerObject;

    public class MultiPlayerObj {
        public String winner;
    }

    public class SinglePlayerObj {
        public long reactionTime;
    }

    public String getFileName(String saveType){

        String fileName = "";

        switch(saveType){
            case "SinglePlayer":
                fileName = SinglePlayerFilename;
                break;
            case "TwoPlayer":
                fileName = TwoPlayerFilename;
                break;
            case "ThreePlayer":
                fileName = ThreePlayerFilename;
                break;
            case "FourPlayer":
                fileName = FourPlayerFilename;
                break;
        }

        return fileName;

    }

    public void gsonAddMultiPlayer(String winner, Context context, String saveType){

        MultiPlayerObj obj = new MultiPlayerObj();
        obj.winner = winner;

        String FILENAME = getFileName(saveType);

        multiPlayerObject = gsonReadMultiPlayer(context, FILENAME);

        try {
            multiPlayerObject.add(obj);
            gsonSave(context, saveType);
        } catch (Exception e){
            multiPlayerObject = new ArrayList<MultiPlayerObj>();
            multiPlayerObject.add(obj);
            gsonSave(context, saveType);
        }

    }

    public void gsonAddSinglePlayer(long time, Context context){

        SinglePlayerObj obj = new SinglePlayerObj();
        obj.reactionTime = time;

        singlePlayerObject = gsonReadSinglePlayer(context);

        try {
            singlePlayerObject.add(obj);
            gsonSave(context, "SinglePlayer");
        } catch (Exception e){
            singlePlayerObject = new ArrayList<SinglePlayerObj>();
            singlePlayerObject.add(obj);
            gsonSave(context, "SinglePlayer");
        }

    }

    public void gsonSave(Context context, String saveType) {

        String FILENAME = getFileName(saveType);

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);

            OutputStreamWriter out = new OutputStreamWriter(fos);
            Gson gson = new Gson();

            if (saveType == "SinglePlayer"){
                gson.toJson(singlePlayerObject, out);
            } else {
                gson.toJson(multiPlayerObject, out);
            }
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<MultiPlayerObj> gsonReadMultiPlayer(Context context, String FILENAME) {
        // single player
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<MultiPlayerObj>>() {}.getType();
            multiPlayerObject = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            multiPlayerObject = new ArrayList<>();
        }

        return multiPlayerObject;
    }

    public ArrayList<SinglePlayerObj> gsonReadSinglePlayer(Context context) {
        // single player
        try {
            FileInputStream fis = context.openFileInput(SinglePlayerFilename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<SinglePlayerObj>>() {}.getType();
            singlePlayerObject = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            singlePlayerObject = new ArrayList<>();
        }

        return singlePlayerObject;
    }

    public void gsonClear(Context context, String saveType) {

        if (saveType == "SinglePlayer"){
            singlePlayerObject = new ArrayList<>();
        } else {
            multiPlayerObject = new ArrayList<>();
        }
        gsonSave(context, saveType);

    }



}
