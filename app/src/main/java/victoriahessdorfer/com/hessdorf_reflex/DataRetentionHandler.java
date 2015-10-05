package victoriahessdorfer.com.hessdorf_reflex;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

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

public class DataRetentionHandler {


    /*
        This class handles the saving / loading from files, as well as clearing the files.

        Used the android developer guide for help with context:
            http://developer.android.com/reference/android/content/Context.html

        Used stack overflow with help with reading from a file with GSON:
            http://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
            User: Brian Roach, answered Mar 7 '12 at 9:42, retrieved October 2nd 2015

        Used lonelyTwitter with help with GSON saving methods:
            https://github.com/joshua2ua/lonelyTwitter
            Author: Joshua Campbell, retrieved on October 1st 2015

     */

    private static final String TwoPlayerFilename = "TwoPlayerSave.sav";
    private static final String ThreePlayerFilename = "ThreePlayerSave.sav";
    private static final String FourPlayerFilename = "FourPlayerSave.sav";
    private static final String SinglePlayerFilename = "SinglePlayer.sav";

    private ArrayList<MultiPlayerObj> multiPlayerObject;
    private ArrayList<SinglePlayerObj> singlePlayerObject;

    public class MultiPlayerObj {
        public String winner;
    }

    public class SinglePlayerObj {
        public long reactionTime;

    }

    private String getFileName(String saveType){

        String fileName = "";

        switch(saveType){
            case "SinglePlayerSave":
                fileName = SinglePlayerFilename;
                break;
            case "TwoPlayerSave":
                fileName = TwoPlayerFilename;
                break;
            case "ThreePlayerSave":
                fileName = ThreePlayerFilename;
                break;
            case "FourPlayerSave":
                fileName = FourPlayerFilename;
                break;
        }

        return fileName;

    }

    public void gsonAddMultiPlayer(String winner, Context context, String saveType){

        MultiPlayerObj obj = new MultiPlayerObj();
        obj.winner = winner;

        multiPlayerObject = gsonReadMultiPlayer(context, saveType);

        try {
            multiPlayerObject.add(obj);
            gsonSave(context, saveType);
        } catch (Exception e){
            multiPlayerObject = new ArrayList<>();
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
            gsonSave(context, "SinglePlayerSave");
        } catch (Exception e){
            singlePlayerObject = new ArrayList<SinglePlayerObj>();
            singlePlayerObject.add(obj);
            gsonSave(context, "SinglePlayerSave");
        }

    }

    public void gsonSave(Context context, String saveType) {

        String FILENAME = getFileName(saveType);

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);

            OutputStreamWriter out = new OutputStreamWriter(fos);
            Gson gson = new Gson();

            if (saveType == "SinglePlayerSave"){
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

    public ArrayList<MultiPlayerObj> gsonReadMultiPlayer(Context context, String saveType) {

        String FILENAME = getFileName(saveType);

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

    public void gsonClear(Context context) {

        singlePlayerObject = new ArrayList<>();
        gsonSave(context, "SinglePlayerSave");
        multiPlayerObject = new ArrayList<>();
        gsonSave(context, "TwoPlayerSave");
        gsonSave(context, "ThreePlayerSave");
        gsonSave(context, "FourPlayerSave");

    }

    public SinglePlayerObj returnSinglePlayerObj(){
        return new SinglePlayerObj();
    }

    public ArrayList<SinglePlayerObj> returnSinglePlayerArray(){
        return new ArrayList<SinglePlayerObj>();
    }



}
