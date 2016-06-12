package bondarmih.edu.persistence.serializator.txtserializator;

import bondarmih.edu.catalog.Album;
import bondarmih.edu.catalog.Artist;
import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.catalog.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 11.06.16.
 */
public class CatalogTextMapper {
    public static List<String> CatalogtoStringList (Catalog catalog){
        List<String> result = new ArrayList<>();
        result.add(getCatalogHeader());
        for (Artist artist : catalog.getArtists()) {
            result.addAll(ArtistToStringList(artist));
        }
        return result;
    }

    private static List<String> ArtistToStringList (Artist artist) {
        List<String> result = new ArrayList<>();
        result.add(getArtistHeader(artist));
        for (Album album:artist.getAlbums()) {
            result.addAll(AlbumToStringList(album));
        }
        return result;
    }

    private static List<String> AlbumToStringList (Album album) {
        List<String> result = new ArrayList<>();
        result.add(getAlbumHeader(album));
        for (Track track:album.getTracklist()) {
            result.add(trackToString(track));
        }
        return result;
    }

    private static String trackToString (Track track) {
        return "Track; Name = " + track.getName() + "; Length = " + trackLengthToString(track.getLength());
    }

    private static String getCatalogHeader() {
        return  "Catalog";
    }

    private static String getArtistHeader(Artist artist) {
        return  "Artist; Name = " + artist.getName();
    }

    private static String getAlbumHeader(Album album) {
        return  "Album; Name = "+ album.getName() + "; Genre = " + album.getGenre();
    }

    private static String trackLengthToString(int length) {
        return  (length / 60) + "m" + String.format("%2d",length%60) + "s";
    }

}

