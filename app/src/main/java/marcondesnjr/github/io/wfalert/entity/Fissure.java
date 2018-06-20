package marcondesnjr.github.io.wfalert.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Fissure implements Parcelable {

    private String id;
    private Mission mission;
    private Calendar expiry;

    public Fissure() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Calendar getExpiry() {
        return expiry;
    }

    public void setExpiry(Calendar expiry) {
        this.expiry = expiry;
    }

    protected Fissure(Parcel in) {
        id = in.readString();
        mission = in.readParcelable(Mission.class.getClassLoader());
    }

    public static final Creator<Fissure> CREATOR = new Creator<Fissure>() {
        @Override
        public Fissure createFromParcel(Parcel in) {
            return new Fissure(in);
        }

        @Override
        public Fissure[] newArray(int size) {
            return new Fissure[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(mission, flags);
    }


}
