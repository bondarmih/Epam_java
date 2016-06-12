package bondarmih.edu.persistence.serializator.txtserializator;

import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.persistence.serializator.CatalogSerializer;
import bondarmih.edu.util.TextFileReaderWriter;

import java.util.List;

/**
 * Created by bondarm on 06.06.16.
 */
public class CatalogTextSerializer implements CatalogSerializer {
    public static String filename = "catalogText.txt";

    public void serializeCatalog(Catalog catalog) {
        TextFileReaderWriter.writeToFile(CatalogTextMapper.CatalogtoStringList(catalog), filename);
    }

    public Catalog deserializeCatalog() {
        List<String> catalogStringList = TextFileReaderWriter.readFromFile(filename);
        Catalog result = new CatalogItemParser().parseCatalog(catalogStringList);
        if (result != null) {
            return result;
        } else {
            throw new IllegalStateException("Catalog is not serialized");
        }
    }
}
