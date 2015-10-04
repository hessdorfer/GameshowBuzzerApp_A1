package victoriahessdorfer.com.hessdorf_reflex;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.view.View;

/**
 * https://developer.android.com/guide/components/intents-common.html#Email
 */
public class EmailHandler {


    public void composeEmail(View view, String emailBody) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        //intent.setType("*/*");
        //intent.setType("message/rfc822");
        //intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Stats from GameshowBuzzer Android Application");

        intent.putExtra(Intent.EXTRA_TEXT, emailBody);


        Context context = view.getContext();

        try {
            if (intent.resolveActivity(context.getPackageManager()) != null)
                context.startActivity(Intent.createChooser(intent, "email"));
        } catch (Exception e){
            e.printStackTrace();
        }

    }



}
