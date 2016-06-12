package bondarmih.edu.persistence.dataobjects;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bondarm on 11.06.16.
 */
public class CatalogDataObject implements Serializable{
    private List<ArtistDataObject> artists;

    public CatalogDataObject(List<ArtistDataObject> artists) {
        this.artists = artists;
    }

    public List<ArtistDataObject> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDataObject> artists) {
        this.artists = artists;
    }
}
