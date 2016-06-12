package bondarmih.edu.persistence.dataobjects;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bondarm on 11.06.16.
 */
public class AlbumDataObject implements Serializable{
    private String name;
    private String genre;
    private List<TrackDataObject> tracks;

    public AlbumDataObject(String name, String genre, List<TrackDataObject> tracks) {
        this.name = name;
        this.genre = genre;
        this.tracks = tracks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<TrackDataObject> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDataObject> tracks) {
        this.tracks = tracks;
    }
}


