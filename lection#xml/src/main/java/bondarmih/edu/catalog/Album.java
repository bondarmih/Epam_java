package bondarmih.edu.catalog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 05.06.16.
 */
public class Album {
    private int id;
    private String name;
    private String genre;
    private List<Track> trackList;

    public Album(int id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.trackList = new ArrayList<Track>();
    }

    public Album(int id, String name, String genre, List<Track> trackList) {
        this(id, name,genre);
        for (Track track: trackList) {
            this.addTrack(track);
        }
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

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public List<Track> getTracklist() {
        return new ArrayList<>(this.trackList);
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public void  addTrack(Track track) {
        this.trackList.add(track);
    }

    public String toString() {
        String result = "Album; Name = "+ this.name + "; Genre = " + this.genre;
        return result;
    }
}
