package marcondesnjr.github.io.wfalert.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import marcondesnjr.github.io.wfalert.entity.Fissure;
import marcondesnjr.github.io.wfalert.entity.Mission;

public class LoadFissuresService extends IntentService {

    public final static String LOADED_LISTENER_BC= "fissuresLoaded";

    public LoadFissuresService() {
        super("DEFAULT");
    }

    public LoadFissuresService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        InputStream is = null;
        try {
            URL url = new URL(Server.SERVER + "Event?id=fissure");
            is = url.openStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }

            JSONArray content = new JSONArray(builder.toString());

            ArrayList<Fissure> fissures = jsonArrayToFissure(content);

            Intent bc = new Intent(LOADED_LISTENER_BC);
            bc.putParcelableArrayListExtra("fissures", fissures);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(bc);
        }catch (JSONException | IOException e){
            Log.e("wfalert.services", e.getMessage(), e);
        }

    }

    private ArrayList<Fissure> jsonArrayToFissure(JSONArray array) throws JSONException{
        ArrayList<Fissure> fissures = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {

            JSONObject jFissure = array.getJSONObject(i);
            Fissure fis = new Fissure();
            fis.setId(jFissure.getString("id"));

            TimeZone UTC = TimeZone.getTimeZone("UTC");
            String val = jFissure.getString("expiry");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            sdf.setTimeZone(UTC);
            Calendar exp = new GregorianCalendar(UTC);
            try {
                exp.setTime(sdf.parse(val));
            } catch (ParseException e) {
                Log.e("wfalert.services", e.getMessage(),e);
            }
            fis.setExpiry(exp);

            JSONObject jMission = jFissure.getJSONObject("mission");
            Mission mis = new Mission();
            mis.setModifier(jMission.getString("modifier"));
            mis.setLocation(jMission.getString("location"));
            mis.setType(jMission.getString("type"));
            fis.setMission(mis);
            fissures.add(fis);
        }
        return fissures;
    }


}
