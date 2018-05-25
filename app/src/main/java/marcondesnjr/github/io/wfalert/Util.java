package marcondesnjr.github.io.wfalert;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class Util {

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
