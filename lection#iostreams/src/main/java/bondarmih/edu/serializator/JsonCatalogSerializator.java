package bondarmih.edu.serializator;

import bondarmih.edu.catalog.Catalog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Created by bondarm on 06.06.16.
 */
public class JsonCatalogSerializator implements CatalogSerializator {
    public void serialize(Catalog catalog) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String catalogGson = gson.toJson(catalog);
        try {
            BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(new File(fileName)));
            textFileWriter.write(catalogGson);
            textFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Catalog deserialize() {
        try {
            BufferedReader textFileReader = new BufferedReader(new FileReader(new File("catalog.txt")));
            Catalog catalog = new Gson().fromJson(textFileReader, Catalog.class);
            return catalog;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
