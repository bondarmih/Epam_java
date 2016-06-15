package bondarmih.edu.persistence.dao;

import bondarmih.edu.catalog.Album;
import bondarmih.edu.catalog.Artist;
import bondarmih.edu.catalog.Track;
import bondarmih.edu.persistence.xml.XmlDocumentHolder;
import bondarmih.edu.persistence.xml.XmlDocumentHolderFactory;
import com.sun.istack.internal.Nullable;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by bondarm on 14.06.16.
 */
public class ArtistXmlDao implements ArtistDao {
    private static final String FILENAME = "catalog.xml";
    private XmlDocumentHolder xmlDocumentHolder = new XmlDocumentHolderFactory().getXmlDocument();

    @Nullable
    public Artist selectById(int id) throws IllegalArgumentException {
        Element artistElement = getElementByAttrbuteValue("artist", "id", Integer.toString(id));
        if (artistElement != null) {
            String name = getChildElementByName(artistElement, "name").getTextContent().trim();
            List<Album> albumList = getAlbumList(artistElement);
            return new Artist(id, name, albumList);
        } else {
            return null;
        }
    }

    public List<Artist> getAll() {
        List<Artist> result = new ArrayList<Artist>();
        NodeList artistNodeList = getNodesByName("artist");
        for (int i = 0; i < artistNodeList.getLength(); i++) {
            result.add(getArtist((Element) artistNodeList.item(i)));
        }
        return result;
    }

    public void insert(Artist artist) {
        if (!isArtistIdExist(artist.getId())) {
            xmlDocumentHolder.getDocument().getDocumentElement().appendChild(setArtistElement(artist));
            xmlDocumentHolder.saveToXml(FILENAME);
        } else {
            throw new IllegalArgumentException("Artist with id = " + artist.getId() + " is already exists.");
        }
    }

    public void delete(int id) {
        if (isArtistIdExist(id)) {
            Element artistToDelete = getElementByAttrbuteValue("artist", "id", Integer.toString(id));
            xmlDocumentHolder.getDocument().removeChild(artistToDelete);
            xmlDocumentHolder.saveToXml(FILENAME);
        }

    }

    public void update(Artist artist) {
        if (isArtistIdExist(artist.getId())) {
            Element artistOld = getElementByAttrbuteValue("artist", "id", Integer.toString(artist.getId()));
            Element artistUpdated = setArtistElement(artist);
            xmlDocumentHolder.getDocument().getDocumentElement().replaceChild(artistUpdated, artistOld);
            xmlDocumentHolder.saveToXml(FILENAME);
        } else {
            throw new IllegalArgumentException("Artist with id = " + artist.getId() + " not exists.");
        }
    }

    public int getTracksTotalDuration(int id) {
        Element artistElement = getElementByAttrbuteValue("artist", "id", Integer.toString(id));
        if (artistElement != null) {
            NodeList tracklist = getNodesByName(artistElement, "track");
            int total = 0;
            for (int i = 0; i < tracklist.getLength(); i++) {
                Track currentTrack = getTrack((Element) tracklist.item(i));
                total += currentTrack.getLength();
            }
            return total;
        } else {
            throw new IllegalArgumentException("Artist with id = " + id + " not exists.");
        }

    }

    private boolean isArtistIdExist(int id) {
        return getElementByAttrbuteValue("artist", "id", Integer.toString(id)) != null;
    }

    private Artist getArtist(Element artistElement) {
        int id = Integer.parseInt(getAttributeValue(artistElement, "id"));
        String name = getChildElementByName(artistElement, "name").getTextContent().trim();
        List<Album> albumList = getAlbumList(artistElement);
        return new Artist(id, name, albumList);
    }

    private List<Album> getAlbumList(Element artistElement) {
        NodeList albumNodeList = getNodesByName(artistElement, "album");
        List<Album> result = new ArrayList<Album>();
        for (int i = 0; i < albumNodeList.getLength(); i++) {
            result.add(getAlbum((Element)albumNodeList.item(i)));
        }
        return result;
    }

    private Album getAlbum(Element albumElement) {
        int id = Integer.parseInt(getAttributeValue(albumElement, "id"));
        String name = getChildElementByName(albumElement, "name").getTextContent().trim();
        String genre = getChildElementByName(albumElement, "genre").getTextContent().trim();
        List<Track> trackList = getTrackList(albumElement);
        return new Album(id, name, genre, trackList);
    }

    private List<Track> getTrackList(Element albumElement) {
        NodeList trackNodeList = getNodesByName(albumElement, "track");
        List<Track> result = new ArrayList<Track>();
        for (int i = 0; i < trackNodeList.getLength(); i++) {
            result.add(getTrack((Element)trackNodeList.item(i)));
        }
        return result;
    }

    private Track getTrack(Element trackElement) {
        int id = Integer.parseInt(getAttributeValue(trackElement, "id"));
        String name = getChildElementByName(trackElement, "name").getTextContent().trim();
        int length = getTrackLength(getChildElementByName(trackElement, "length").getTextContent().trim());
        return new Track(id, name, length);
    }

    private int getTrackLength(String lengthString) {
        StringTokenizer tokenizer = new StringTokenizer(lengthString, ":");
        int minutes = Integer.valueOf(tokenizer.nextToken());
        int seconds = Integer.valueOf(tokenizer.nextToken());
        return 60* minutes + seconds;
    }

    private Element setArtistElement(Artist artist) {
        Element artistElement = xmlDocumentHolder.getDocument().createElement("artist");
        artistElement.setAttribute("id", Integer.toString(artist.getId()));
        Element artistName = xmlDocumentHolder.getDocument().createElement("name");
        artistName.setTextContent(artist.getName());
        artistElement.appendChild(artistName);
        Element artistAlbums = xmlDocumentHolder.getDocument().createElement("albums");
        for (Album album: artist.getAlbums()) {
            Element albumElement = setAlbumElement(album);
            artistAlbums.appendChild(albumElement);
        }
        artistElement.appendChild(artistAlbums);
        return artistElement;
    }

    private Element setAlbumElement(Album album) {
        Element albumElement = xmlDocumentHolder.getDocument().createElement("album");
        albumElement.setAttribute("id", Integer.toString(album.getId()));
        Element albumName = xmlDocumentHolder.getDocument().createElement("name");
        albumName.setTextContent(album.getName());
        albumElement.appendChild(albumName);
        Element albumGenre = xmlDocumentHolder.getDocument().createElement("genre");
        albumGenre.setTextContent(album.getGenre());
        albumElement.appendChild(albumGenre);
        Element albumTracks = xmlDocumentHolder.getDocument().createElement("tracks");
        for (Track track : album.getTracklist()) {
            Element trackElement = setTrackElement(track);
            albumTracks.appendChild(trackElement);
        }
        albumElement.appendChild(albumTracks);
        return albumElement;
    }

    private Element setTrackElement(Track track) {
        Element trackElement = xmlDocumentHolder.getDocument().createElement("track");
        trackElement.setAttribute("id", Integer.toString(track.getId()));
        Element trackName = xmlDocumentHolder.getDocument().createElement("name");
        trackName.setTextContent(track.getName());
        trackElement.appendChild(trackName);
        Element trackLength = xmlDocumentHolder.getDocument().createElement("length");
        trackLength.setTextContent(trackLengthtoString(track.getLength()));
        trackElement.appendChild(trackLength);
        return trackElement;
    }

    private String trackLengthtoString(int length) {
        return (length / 60) + ":" + String.format("%2d",length%60);
    }

    public NodeList getNodesByName(String tagName) {
        Element root = xmlDocumentHolder.getDocument().getDocumentElement();
        return getNodesByName(root, tagName);
    }

    public NodeList getNodesByName(Element element, String tagName) {
        NodeList nodes = element.getElementsByTagName(tagName);
        return nodes;
    }

    public String getAttributeValue(Element element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public Element getElementByAttrbuteValue(String tagName, String attributeName, String attributeValue) {
        NodeList nodeList = getNodesByName(tagName);
        //check null or empty
        for (int i = 0; i< nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            if (getAttributeValue(element, attributeName).equals(attributeValue)) {
                return element;
            }
        }
        return null;
    }

    public Element getChildElementByName(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);
        return child;
    }
}
