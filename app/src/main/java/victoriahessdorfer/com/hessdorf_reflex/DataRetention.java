package victoriahessdorfer.com.hessdorf_reflex;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.*;

import java.io.BufferedWriter;
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

public class DataRetention {

   private static final String MultiPlayerFilename = "MultiPlayer.ser";
   private static final String SinglePlayerFilename = "SinglePlayer.sav";


    public class MultiPlayerObj implements java.io.Serializable {
        private String winner;
        private String mode;

        public void setWinner(String inp){
            this.winner = inp;
        }

        public void setMode(String inp){
           this.mode = inp;
        }
    }

    public void SinglePlayerSave(String text, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(SinglePlayerFilename, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(text, out);
            //fos.write(text.getBytes());

            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void MultiPlayerSave(String winner, String mode, Context context){


        MultiPlayerObj obj = new MultiPlayerObj();
        obj.setWinner("test1");
        obj.setMode("test1");

        try {
            /*
            FileOutputStream fos = context.openFileOutput(MultiPlayerFilename, context.MODE_APPEND);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(obj);

            oos.close();
            fos.close();
            */


            FileOutputStream fileOut = context.openFileOutput(MultiPlayerFilename, context.MODE_APPEND);

            //String filePath = context.getFilesDir().getPath().toString() + "/" + MultiPlayerFilename;
            //FileOutputStream fileOut = new FileOutputStream(filePath, Boolean.TRUE);

            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/employee.ser");

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



    public ArrayList<MultiPlayerObj> MultiPlayerReadData2(Context context) {

        ArrayList<MultiPlayerObj> multiObj = new ArrayList<MultiPlayerObj>();

        try {
            FileInputStream fis = context.openFileInput(MultiPlayerFilename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();

            JsonParser parser = new JsonParser();

            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new StringReader(line));
            reader.setLenient(true);

            JsonArray jArray = parser.parse(reader).getAsJsonArray();

            for(JsonElement obj : jArray )
            {
                MultiPlayerObj mobj = gson.fromJson( obj , MultiPlayerObj.class);
                multiObj.add(mobj);
            }


        } catch (FileNotFoundException e) {
            // DO SOMETHING HERE - see lonelyTwitter
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return multiObj;

    }

    public ArrayList<MultiPlayerObj> MultiPlayerReadData(Context context) {

        ArrayList<MultiPlayerObj> multiObj = new ArrayList<MultiPlayerObj>();

        try {
            ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(MultiPlayerFilename));
            Object object = objInput.readObject();
            while (object != null) {
                multiObj.add((MultiPlayerObj) object);
                object = objInput.readObject();
            }
            objInput.close();
        } catch(Exception e) {
            // everything else
        }

        return multiObj;

    }

}
