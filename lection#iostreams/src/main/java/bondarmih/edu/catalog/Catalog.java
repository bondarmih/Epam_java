package bondarmih.edu.catalog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 05.06.16.
 */
public class Catalog {
    private List<Artist> artists;

    public Catalog() {
        this.artists = new ArrayList<Artist>();
    }

    public Catalog(List<Artist> artists) {
        this();
        for (Artist artist: artists) {
            this.addArtist(artist);
        }
    }

    public List<Artist> getArtists() {
        return new ArrayList<Artist>(this.artists);
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }

    public String toString() {
        String result = "Catalog";
        return result;

    }
}
