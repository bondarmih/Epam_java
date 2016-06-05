package bondarmih.edu.catalog;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by bondarm on 05.06.16.
 */
public class Catalog implements CatalogItem {
    private List<Artist> artists;

    public Catalog() {
        this.artists = new ArrayList<Artist>();
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }

    public void serialize() {
        try {
            BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(new File("catalog" + CatalogItem.fileExt)));
            for (String currentLine : this.toStringList()) {
                textFileWriter.write(currentLine);
                textFileWriter.newLine();
            }
            textFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize() {
        try {
            String currentLine;
            BufferedReader textFileReader = new BufferedReader(new FileReader(new File(fileName)));
            while ((currentLine = textFileReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(currentLine, " ,:");


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
