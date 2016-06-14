package bondarmih.edu.persistence.serializer.txt;

import bondarmih.edu.catalog.Album;
import bondarmih.edu.catalog.Artist;
import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.catalog.Track;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bondarm on 06.06.16.
 */
public class CatalogTextParserMapper {
    private static final String CATALOG_SER_STRING = "Catalog";
    private static final String ARTIST_SER_STRING = "Artist";
    private static final String ALBUM_SER_STRING = "Album";
    private static final String TRACK_SER_STRING = "Artist";
    private static final Character TRACK_LENGTH_MINUTE_SER_STRING = 'm';
    private static final Character TRACK_LENGTH_SECOND_SER_STRING = 's';
    private static final Character ATTR_DIVIDER = ';';
    private static final Character ATTR_EQ = '=';
    private static final Pattern CATALOG_PATTERN = Pattern.compile("^" + CATALOG_SER_STRING + "$");
    private static final Pattern ARTIST_PATTERN = Pattern.compile(
            "^.*" + ARTIST_SER_STRING + ATTR_DIVIDER +" Name = .+$");
    private static final Pattern ALBUM_PATTERN = Pattern.compile(
            "^.*" + ALBUM_SER_STRING + ATTR_DIVIDER +" Name = .+" + ATTR_DIVIDER + " Genre = .+$");
    private static final Pattern TRACK_PATTERN = Pattern.compile(
            "^.*" + TRACK_SER_STRING + ATTR_DIVIDER +" Name = .+" + ATTR_DIVIDER + " Length = \\d+m[0-5]\\ds$");

    public Catalog parseCatalog (List<String> stringList) {
        if (isValidList(stringList)) {
            Catalog result = new Catalog();
            removeItemHeader(stringList);
            while (!stringList.isEmpty()) {
                int currentArtistUpperBound = getCurrentItemUpperBound(stringList, ARTIST_SER_STRING);
                List<String> currentArtistList = new ArrayList<>(stringList.subList(0,currentArtistUpperBound));
                trimListStart(stringList, currentArtistUpperBound);
                Artist currentArtist = parseArtist(currentArtistList);
                if (isValidArtist(currentArtist)) {
                    result.addArtist(currentArtist);
                }
            }
            return result;
        } else {
            throw new IllegalArgumentException("There is no Catalog in file. Possibly file is corrupted");
        }
    }

    private boolean isValidList(List<String> stringList) {
        return (stringList != null) && !stringList.isEmpty() && isValidItem(stringList.get(0), CATALOG_PATTERN);
    }

    private Artist parseArtist (List<String> stringList) {
        if (!isValidItem(stringList.get(0), ARTIST_PATTERN)) {
            return null;
        }
        List<String> tokenList = getTokens(stringList.get(0));
        String artistName = tokenList.get(2);
        Artist result = new Artist(artistName);
        removeItemHeader(stringList);
        while (!stringList.isEmpty()) {
            int currentAlbumUpperBound = getCurrentItemUpperBound(stringList, ALBUM_SER_STRING);
            List<String> currentAlbumList = new ArrayList<>(stringList.subList(0,currentAlbumUpperBound));
            trimListStart(stringList, currentAlbumUpperBound);
            Album currentAlbum = parseAlbum(currentAlbumList);
            if (isValidAlbum(currentAlbum)) {
                result.addAlbum(currentAlbum);
            }

        }
        return result;
    }

    private Album parseAlbum (List<String> stringList) {
        if (!isValidItem(stringList.get(0), ALBUM_PATTERN)) {
            return null;
        }
        List<String> tokenList = getTokens(stringList.get(0));
        String albumName = tokenList.get(2);
        String albumGenre = tokenList.get(4);
        Album result = new Album(albumName,albumGenre);
        removeItemHeader(stringList);
        for (String currentTrackString : stringList) {
            Track currentTrack = parseTrack(currentTrackString);
            if (isValidTrack(currentTrack)) {
                result.addTrack(currentTrack);
            }
        }
        return result;
    }

    private Track parseTrack (String itemString) {
        if (!isValidItem(itemString, TRACK_PATTERN)) {
            return null;
        }
        List<String> tokenList = getTokens(itemString);
        String trackName = tokenList.get(2);
        int trackLength = parseTrackLength(tokenList.get(4));
        return new Track(trackName, trackLength);
    }

    private int parseTrackLength(String lengthString) {
        StringTokenizer stringTokenizer =
                new StringTokenizer(lengthString, TRACK_LENGTH_MINUTE_SER_STRING.toString()
                        + TRACK_LENGTH_SECOND_SER_STRING.toString());
        int minutes = Integer.valueOf(stringTokenizer.nextToken());
        int seconds = Integer.valueOf(stringTokenizer.nextToken());
        return 60* minutes + seconds;
    }

    private List<String> getTokens(String itemString) {
        StringTokenizer stringTokenizer = new StringTokenizer(itemString, ATTR_DIVIDER.toString() + ATTR_EQ.toString());
        List<String> tokenList = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            tokenList.add(stringTokenizer.nextToken().trim());
        }
        return tokenList;
    }

    private int getCurrentItemUpperBound(List<String> stringList, String otherItemKey) {
        for (int i = 1; i < stringList.size(); i++) {
            if (stringList.get(i).startsWith(otherItemKey)) {
                return i;
            }
        }
        return stringList.size();
    }

    public void trimListStart(List<String> stringList, int edgeIndex) {
        int currentIndex = 0;
        while (currentIndex < edgeIndex) {
            stringList.remove(0);
            currentIndex++;
        }
    }

    private boolean isValidItem(String itemString, Pattern pattern) {
        Matcher matcher = pattern.matcher(itemString);
        return matcher.matches();
    }
    private boolean isValidArtist(Artist artist) {
        return (artist != null) && (artist.getAlbums() != null) && (artist.getAlbums().size() != 0);
    }

    private boolean isValidAlbum(Album album) {
        return (album != null) && (album.getTracklist() != null) && (album.getTracklist().size() != 0);
    }

    private boolean isValidTrack(Track track) {
       return (track != null) && (track.getLength() != 0);
    }

    public List<String> catalogToStringList(Catalog catalog){
        List<String> result = new ArrayList<>();
        result.add(getCatalogHeader());
        for (Artist artist : catalog.getArtists()) {
            result.addAll(artistToStringList(artist));
        }
        return result;
    }

    private List<String> artistToStringList(Artist artist) {
        List<String> result = new ArrayList<>();
        result.add(getArtistHeader(artist));
        for (Album album:artist.getAlbums()) {
            result.addAll(albumToStringList(album));
        }
        return result;
    }

    private List<String> albumToStringList(Album album) {
        List<String> result = new ArrayList<>();
        result.add(getAlbumHeader(album));
        for (Track track:album.getTracklist()) {
            result.add(trackToString(track));
        }
        return result;
    }

    private String trackToString (Track track) {
        return TRACK_SER_STRING
                + ATTR_DIVIDER
                + " Name "
                + ATTR_EQ + " "
                + track.getName()
                + ATTR_DIVIDER
                + " Length "
                + ATTR_EQ + " "
                + trackLengthToString(track.getLength());
    }

    private String getCatalogHeader() {
        return  CATALOG_SER_STRING;
    }

    private String getArtistHeader(Artist artist) {
        return  ARTIST_SER_STRING
                + ATTR_DIVIDER
                + "Name "
                + ATTR_EQ + " "
                + artist.getName();
    }

    private String getAlbumHeader(Album album) {
        return  ALBUM_SER_STRING
                + ATTR_DIVIDER
                + "Name "
                + ATTR_EQ + " "
                + album.getName() + " "
                + ATTR_DIVIDER + " "
                + "Genre "
                + ATTR_EQ + " "
                + album.getGenre();
    }

    private String trackLengthToString(int length) {
        return  (length / 60)
                + TRACK_LENGTH_MINUTE_SER_STRING.toString()
                + String.format("%2d",length%60)
                + TRACK_LENGTH_SECOND_SER_STRING.toString();
    }

    private void removeItemHeader(List<String> stringList) {
        stringList.remove(0);
    }

}
