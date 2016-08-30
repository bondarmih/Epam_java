package bondarmih.edu.persistence.dataobjects;

import java.io.Serializable;

/**
 * Created by bondarm on 11.06.16.
 */
public class TrackDataObject implements Serializable{
    private String name;
    private int length;

    public TrackDataObject(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
