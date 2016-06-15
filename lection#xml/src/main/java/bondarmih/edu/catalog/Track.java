package bondarmih.edu.catalog;

/**
 * Created by bondarm on 05.06.16.
 */
public class Track {
    private int id;
    private String name;
    private int length;

    public Track(int id, String name, int length) {
        this.id = id;
        this.name = name;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private String lengthToString() {
        String result = (this.length / 60) + "m" + String.format("%2d",this.length%60) + "s";
        return result;
    }

    public String toString() {
        String result = "Track; Name = " + this.name + "; Length = " + this.lengthToString();
        return result;
    }
}
