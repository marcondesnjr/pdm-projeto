package marcondesnjr.github.io.wfalert.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Mission implements Parcelable {
    private String type;
    private String location;
    private String modifier;
    private String faction;

    public Mission() {
    }

    public Mission(Parcel parcel) {
        type = parcel.readString();
        location = parcel.readString();
        modifier = parcel.readString();
        faction = parcel.readString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", modifier='" + modifier + '\'' +
                ", faction='" + faction + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(location);
        dest.writeString(modifier);
        dest.writeString(faction);
    }

    private Parcelable.Creator<Mission> CREATOR = new Parcelable.Creator<Mission>() {
        @Override
        public Mission createFromParcel(Parcel source) {
            return new Mission(source);
        }

        @Override
        public Mission[] newArray(int size) {
            return new Mission[size];
        }
    };
}
