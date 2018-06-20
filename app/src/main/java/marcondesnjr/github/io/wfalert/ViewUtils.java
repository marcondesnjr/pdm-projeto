package marcondesnjr.github.io.wfalert;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public abstract class ViewUtils {

    public static void startCountdownTime(final TextView tx, final Calendar time, final String format){
        TimeZone UTC = TimeZone.getTimeZone("UTC");
        Calendar now = new GregorianCalendar(UTC);
        Map<Integer,Integer> mapTime = timeLeft(now, time);
        int hr = mapTime.get(HOUR);
        int min = mapTime.get(MINUTE);
        int sec = mapTime.get(SECOND);
        long mili = sec*1000;
        mili = mili + min*60*1000;
        mili = mili + hr*60*60*1000;
        new CountDownTimer(mili,1000){

            @Override
            public void onTick(long end) {
                TimeZone UTC = TimeZone.getTimeZone("UTC");
                Calendar now = new GregorianCalendar(UTC);
                Map<Integer,Integer> mapTime = timeLeft(now, time);
                int hr = mapTime.get(HOUR);
                int min = mapTime.get(MINUTE);
                int sec = mapTime.get(SECOND);
                tx.setText(String.format(format,hr,min,sec));
            }

            @Override
            public void onFinish() {
                tx.setText("Expired");
            }
        }.start();
    }





    public static final int SECOND = 1;
    public static final int MINUTE = 2;
    public static final int HOUR = 3;
    public static final int DAY = 4;


    public static Map<Integer, Integer> timeLeft(Calendar start, Calendar end){
        long endLong = end.getTimeInMillis();
        long startLong = start.getTimeInMillis();
        long left = TimeUnit.MILLISECONDS.toSeconds(endLong - startLong);
        long sec = left % 60;
        left = left / 60;
        long min = left % 60;
        left = left / 60;
        long hr = left % 24;
        long day = left / 24;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(SECOND,(int)sec);
        map.put(MINUTE,(int)min);
        map.put(HOUR,(int)hr);
        map.put(DAY,(int)day);

        return map;

    }
}
