package marcondesnjr.github.io.wfalert.services;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImagesService extends IntentService {

    private final String URL_ROOT_IMAGES = Server.SERVER+"img/";

    public LoadImagesService() {
        super("DEFAULT");
    }

    public  LoadImagesService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String bcListener = intent.getStringExtra("bcListener");
        String imgId = intent.getStringExtra("imgId");
        InputStream is = null;

        try {
            URL url = new URL(URL_ROOT_IMAGES + imgId);
            InputStream in = url.openStream();
            byte[] bArray = new byte[16384];

            Intent response = new Intent(bcListener);
            response.putExtra("img", convertInputStreamToByteArray(in));

            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(response);
        }catch (IOException ex){
            Log.e("wfalert.services", ex.getMessage(), ex);
        }
    }

    private byte[] convertInputStreamToByteArray(InputStream inputStream)
    {
        byte[] bytes= null;

        try
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte data[] = new byte[1024];
            int count;

            while ((count = inputStream.read(data)) != -1)
            {
                bos.write(data, 0, count);
            }

            bos.flush();
            bos.close();
            inputStream.close();

            bytes = bos.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return bytes;
    }
}
