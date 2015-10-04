package victoriahessdorfer.com.hessdorf_reflex;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.view.View;

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

/**
 * https://developer.android.com/guide/components/intents-common.html#Email
 */

public class EmailHandler {

    /*
        This class handles the basic email sending functionality.

        Used the android developers API to model creating an email with intents:
            https://developer.android.com/guide/components/intents-common.html#Email
     */

    public void composeEmail(View view, String emailBody) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
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
