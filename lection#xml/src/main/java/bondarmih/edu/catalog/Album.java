package bondarmih.edu.catalog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 05.06.16.
 */
public class Album { add id!!!!!!! to all items
    private String name;
    private String genre;
    private List<Track> trackList;

    public Album(String name, String genre) {
        this.name = name;
        this.genre = genre;
        this.trackList = new ArrayList<Track>();
    }

    public Album(String name, String genre, List<Track> trackList) {
        this(name,genre);
        for (Track track: trackList) {
            this.addTrack(track);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getGenre() {
        return this.genre;
    }

    public List<Track> getTracklist() {
        return new ArrayList<>(this.trackList);
    }

    public void  addTrack(Track track) {
        this.trackList.add(track);
    }

    public String toString() {
        String result = "Album; Name = "+ this.name + "; Genre = " + this.genre;
        return result;
    }
}
