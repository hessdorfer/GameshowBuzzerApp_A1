package victoriahessdorfer.com.hessdorf_reflex;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;

/**
 * https://developer.android.com/guide/components/intents-common.html#Email
 */
public class EmailHandler {


    public void composeEmail(String address, Context context) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        //intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Stats from GameshowBuzzer Android Application");
        intent.putExtra(Intent.EXTRA_TEXT, "Email Body Here");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("mailto:"));

        try {
            if (intent.resolveActivity(context.getPackageManager()) != null)
                context.startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
