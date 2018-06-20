package marcondesnjr.github.io.wfalert.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventListener;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import marcondesnjr.github.io.wfalert.entity.Alert;
import marcondesnjr.github.io.wfalert.entity.Mission;
import marcondesnjr.github.io.wfalert.entity.Reward;

public class LoadAlertsService extends Service{

    private final IBinder binder = new LoadAlertsBinder();
    private LoadedListener<List<Alert>> listener;

    public void loadAlerts(LoadedListener<List<Alert>> lis) {
        this.listener = lis;
        URL url = null;
        try {
            url = new URL(Server.SERVER+"Event?id=alert");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new LocalTask().execute(url);
    }

    private void loaded(String response){
        JSONArray jsonArray = null;
        try {
            if(response != null) {
                jsonArray = new JSONArray(response);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(jsonArray != null) {
            listener.onFinish(jsonToAlert(jsonArray));
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private List<Alert> jsonToAlert(JSONArray array){
        List<Alert> alertsList = new ArrayList<>();
        for(int i = 0; i < array.length(); i++) {
            try {
                JSONObject jAlert = array.getJSONObject(i);
                Alert al = new Alert();

                al.setId(jAlert.getString("id"));

                TimeZone UTC = TimeZone.getTimeZone("UTC");
                String val = jAlert.getString("validade");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                sdf.setTimeZone(UTC);
                Calendar exp = new GregorianCalendar(UTC);
                exp.setTime(sdf.parse(val));
                al.setExpiry(exp);

                al.setMaxLevel(jAlert.getInt("maxLevel"));

                al.setMinLevel(jAlert.getInt("minLevel"));

                JSONObject jMission = jAlert.getJSONObject("mission");
                Mission m = new Mission();
                m.setFaction(jMission.getString("faction"));
                m.setLocation(jMission.getString("location"));
                m.setType(jMission.getString("type"));
                al.setMission(m);

                JSONArray arrayRewards = jAlert.getJSONArray("rewards");
                List<Reward> rewards = new ArrayList<>();
                for (int j = 0; j < arrayRewards.length(); j++) {
                    JSONObject jRw = arrayRewards.getJSONObject(j);
                    Reward rw = new Reward();
                    rw.setName(jRw.getString("name"));
                    rw.setImgRef(jRw.getString("imgRef"));
                    rw.setQuant(jRw.getInt("quant"));
                    rewards.add(rw);
                }
                al.setRewards(rewards);
                alertsList.add(al);
            }catch(org.json.JSONException | ParseException ex){
                Log.e("LoadAlerts", ex.getMessage(),ex);
            }
        }
        return alertsList;
    }



    public class LoadAlertsBinder extends Binder{
        public LoadAlertsService getService(){
            return LoadAlertsService.this;
        }
    }

    class LocalTask extends AsyncTask<URL,String,String> {

        @Override
        protected String doInBackground(URL... urls) {
            InputStream in = null;
            try {
                URL url = urls[0];
                in = url.openStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
                return builder.toString();
            } catch (IOException e) {
                Log.e("wfalert.services", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            loaded(s);
        }
    }
}
