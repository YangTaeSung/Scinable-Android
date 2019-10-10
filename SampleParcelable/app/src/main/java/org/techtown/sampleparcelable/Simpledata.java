package org.techtown.sampleparcelable;

import android.os.Parcelable;
import android.os.Parcel;

public class Simpledata implements Parcelable {

    int number;
    String message;

    public Simpledata(int num, String msg ) {
        number = num;
        message = msg;
    }

    public Simpledata(Parcel src) {
        number = src.readInt();
        message = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Simpledata createFromParcel(Parcel in) {
            return new Simpledata(in);
        }

        public Simpledata[] newArray(int size) {
            return new Simpledata[size];
        }
    };

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
    }

}
