package bondarmih.edu.catalog;

/**
 * Created by bondarm on 05.06.16.
 */
public class Track implements CatalogItem {
    private String name;
    private int length;

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public void serialize() {

    }

    public void deserialize() {

    }

    private String lengthToString() {
        String result = (this.length / 60) + ":" + String.format("%2d",this.length%60);
        return result;
    }

    public String toString() {
        String result = "Track, Name : " + this.name + ", length : " + this.lengthToString();
        return result;
    }
}
