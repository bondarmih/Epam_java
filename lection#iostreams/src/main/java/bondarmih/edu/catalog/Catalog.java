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

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }

    public String toString() {
        String result = "Catalog";
        return result;

    }

    public List<String> toStringList() {
        List<String> result = new ArrayList<String>();
        result.add(this.toString());
        for (Artist artist : artists) {
            result.addAll(artist.toStringList());
        }
        return result;
    }

    public void printCatalog() {
        List<String> list = this.toStringList();
        for (String string : list) {
            System.out.println(string);
        }
    }
}
