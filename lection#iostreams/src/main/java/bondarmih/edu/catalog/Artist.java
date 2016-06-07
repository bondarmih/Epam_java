package bondarmih.edu.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.StreamHandler;

/**
 * Created by bondarm on 05.06.16.
 */
public class Artist{
    private String name;
    private List<Album> albums;

    public Artist (String name) {
        this.name = name;
        this.albums = new ArrayList<Album>();
    }

    public void addAlbum (Album album) {
        this.albums.add(album);
    }

    public String toString() {
        String result = "Artist; Name = " + this.name;
        return result;
    }

    public List<String> toStringList() {
        List<String> result = new ArrayList<String>();
        result.add(this.toString());
        for (Album album:albums) {
            result.addAll(album.toStringList());
        }
        return result;
    }
}
