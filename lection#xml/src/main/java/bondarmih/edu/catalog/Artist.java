package bondarmih.edu.catalog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 05.06.16.
 */
public class Artist{
    private int id;
    private String name;
    private List<Album> albums;

    public Artist (int id, String name) {
        this.id = id;
        this.name = name;
        this.albums = new ArrayList<Album>();
    }

    public Artist (int id, String name, List<Album> albums) {
        this(id, name);
        for (Album album: albums) {
            this.addAlbum(album);
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

    public List<Album> getAlbums() {
        return new ArrayList<Album>(this.albums);
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public void addAlbum (Album album) {
        this.albums.add(album);
    }

    public String toString() {
        String result = "Artist; Name = " + this.name;
        return result;
    }
}
