package bondarmih.edu.util;

import bondarmih.edu.catalog.Album;
import bondarmih.edu.catalog.Artist;
import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.catalog.Track;

/**
 * Created by bondarm on 06.06.16.
 */
public class CatalogCreator {
    public static Catalog getCatalogInstance(int artistCount, int albumCount, int trackCount) {
        Catalog catalog = new Catalog();
        for (int i = 0; i < artistCount; i++) {
            Artist artist = new Artist("Artist#" + i);
            for (int j = 0; j < albumCount; j++) {
                Album album = new Album("Album#" + i + "-" + j, "Genre#" + j);
                for (int k = 0; k < trackCount; k++) {
                    Track track = new Track("Song#" + i + "-" + j + "-" + k, 100);
                    album.addTrack(track);
                }
                artist.addAlbum(album);
            }
            catalog.addArtist(artist);
        }
        return catalog;

    }


}
