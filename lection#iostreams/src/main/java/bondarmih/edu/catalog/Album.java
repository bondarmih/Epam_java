package bondarmih.edu.catalog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 05.06.16.
 */
public class Album implements CatalogItem{
    private String name;
    private String genre;
    private List<Track> trackList;

    public Album(String name, String genre) {
        this.name = name;
        this.genre = genre;
        this.trackList = new ArrayList<Track>();
    }

    public void  addTrack(Track track) {
        this.trackList.add(track);
    }

    public void serialize() {

    }

    public void deserialize() {

    }

    public String toString() {
        String result = "Album, Name : "+ this.name + ", Genre : " + this.genre;
        return result;
    }

    public List<String> toStringList() {
        List<String> result = new ArrayList<String>();
        result.add(this.toString());
        for (Track track:trackList) {
            result.add(track.toString());
        }
        return result;
    }
}
