package bondarmih.edu.persistence.dataobjects;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bondarm on 11.06.16.
 */
public class ArtistDataObject implements Serializable{
    private String name;
    private List<AlbumDataObject> albums;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AlbumDataObject> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumDataObject> albums) {
        this.albums = albums;
    }

    public ArtistDataObject(String name, List<AlbumDataObject> albums) {

        this.name = name;
        this.albums = albums;
    }

}
