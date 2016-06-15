package bondarmih.edu.persistence.dao;

import bondarmih.edu.catalog.Album;
import bondarmih.edu.catalog.Artist;
import bondarmih.edu.catalog.Track;
import bondarmih.edu.persistence.xml.XmlDocumentHolder;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by bondarm on 14.06.16.
 */
public class ArtistXmlDao implements ArtistDao {
    private XmlDocumentHolder xmlDocumentHolder;

    public ArtistXmlDao(XmlDocumentHolder xmlDocumentHolder) {
        this.xmlDocumentHolder = xmlDocumentHolder;
    }

    public Artist selectById(int id) throws IllegalArgumentException {
        Element artistElement = getElementByAttrbuteValue("artist", "id", Integer.toString(id));
        if (artistElement != null) {
            String name = getChildElementByName(artistElement, "name").getTextContent().trim();
            List<Album> albumList = getAlbumList(artistElement);
            return new Artist(id, name, albumList);
        } else {
            throw new IllegalArgumentException("No user with id = " + id);
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

    }

    public void delete(int id) {

    }

    public void update(Artist artist) {

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
        return artistElement;
    }

    private Element setAlbumElement(Album album) {
        Element albumElement = xmlDocumentHolder.getDocument().createElement("album");
        albumElement.setAttribute("id", Integer.toString(album.getId()));

    }

    private Element setTrackElement(Track track) {

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
