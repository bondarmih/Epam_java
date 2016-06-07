package bondarmih.edu.serializator;

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
public class CatalogItemParser {
    private static final Pattern artistPattern = Pattern.compile("^.*Artist; Name = .+$");
    private static final Pattern albumPattern = Pattern.compile("^.*Album; Name = .+; Genre = .+$");
    private static final Pattern trackPattern = Pattern.compile("^.*Track; Name = .+; Length = \\d+m[0-5]\\ds$");


    public Catalog parseCatalog (List<String> stringList) {

        boolean catalogIsNotEmpty = (stringList!=null) &&  !stringList.isEmpty();
        if (catalogIsNotEmpty && stringList.get(0).contains("Catalog")) {
            Catalog result = new Catalog();
            stringList.remove(0);
            while (!stringList.isEmpty()) {
                List<String> currentArtistList = getItemsStringLists(stringList, "Artist");
                stringList.removeAll(currentArtistList);
                Artist currentArtist = parseArtist(currentArtistList);
                if (currentArtist != null && isValidArtist(currentArtist)) {
                    result.addArtist(currentArtist);
                }

            }
            return result;
        } else {
            throw new IllegalArgumentException("There is no Catalog in file. Possibly file is corrupted");
        }
    }

    private Artist parseArtist (List<String> stringList) {
        if (!isValidItem(stringList.get(0), artistPattern)) {
            return null;
        }
        List<String> tokenList = getTokens(stringList.get(0));
        String artistName = tokenList.get(2);
        Artist result = new Artist(artistName);
        stringList.remove(0);
        while (!stringList.isEmpty()) {
            List<String> currentAlbumList = getItemsStringLists(stringList, "Album");
            stringList.removeAll(currentAlbumList);
            Album currentAlbum = parseAlbum(currentAlbumList);
            if (currentAlbum != null && isValidAlbum(currentAlbum)) {
                result.addAlbum(currentAlbum);
            }

        }
        return result;
    }

    private Album parseAlbum (List<String> stringList) {
        if (!isValidItem(stringList.get(0), albumPattern)) {
            return null;
        }
        List<String> tokenList = getTokens(stringList.get(0));
        String albumName = tokenList.get(2);
        String albumGenre = tokenList.get(4);
        Album result = new Album(albumName,albumGenre);
        stringList.remove(0);
        for (String currentTrackString : stringList) {
            Track currentTrack = parseTrack(currentTrackString);
            if (currentTrack != null && isValidTrack(currentTrack)) {
                result.addTrack(currentTrack);
            }
        }
        return result;
    }

    private Track parseTrack (String itemString) {
        if (!isValidItem(itemString, trackPattern)) {
            return null;
        }
        List<String> tokenList = getTokens(itemString);
        String trackName = tokenList.get(2);
        int trackLength = parseTrackLength(tokenList.get(4));
        return new Track(trackName, trackLength);
    }

    private static int parseTrackLength(String lengthString) {
        StringTokenizer stringTokenizer = new StringTokenizer(lengthString, "ms");
        int minutes = Integer.valueOf(stringTokenizer.nextToken());
        int seconds = Integer.valueOf(stringTokenizer.nextToken());
        return 60* minutes + seconds;
    }

    private static List<String> getTokens(String itemString) {
        StringTokenizer stringTokenizer = new StringTokenizer(itemString,";=");
        List<String> tokenList = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            tokenList.add(stringTokenizer.nextToken().trim());
        }
        return tokenList;
    }

    private List<String> getItemsStringLists(List<String> stringList, String otherItemKey) {
        for (int i = 1; i < stringList.size(); i++) {
            if (stringList.get(i).startsWith(otherItemKey)) {
                List<String> result = new ArrayList<>(stringList.subList(0,i));
                return result;
            }
        }
        return new ArrayList<>(stringList);
    }

    private boolean isValidItem(String itemString, Pattern pattern) {
        Matcher matcher = pattern.matcher(itemString);
        return matcher.matches();
    }
    private boolean isValidArtist(Artist artist) {
        return true;
    }

    private boolean isValidAlbum(Album album) {
        return true;
    }

    private boolean isValidTrack(Track track) {
        return true;
    }

}
