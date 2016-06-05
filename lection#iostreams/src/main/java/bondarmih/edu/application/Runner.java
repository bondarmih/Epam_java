package bondarmih.edu.application;

import bondarmih.edu.catalog.Album;
import bondarmih.edu.catalog.Artist;
import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.catalog.Track;


/**
 * Created by bondarm on 05.06.16.
 */
public class Runner extends Thread {
    public void run() {
        Catalog catalog = new Catalog();
        Artist[] artists = new Artist[10];
        for (int i = 0; i < 10; i++) {
            artists[i] = new Artist("Artist#" + i);
            Album[] albums = new Album[2];
            for (int j = 0; j < 2; j++) {
                albums[j] = new Album("Album#" + i + "-" + j, "Genre#" + j);
                Track[] tracks = new Track[5];
                for (int k = 0; k < 5; k++) {
                    tracks[k] = new Track("Song#" + i + "-" + j + "-" + k, 100);
                    albums[j].addTrack(tracks[k]);
                }
                artists[i].addAlbum(albums[j]);
            }
            catalog.addArtist(artists[i]);
        }
        catalog.printCatalog();

        catalog.serialize();
    }
}
