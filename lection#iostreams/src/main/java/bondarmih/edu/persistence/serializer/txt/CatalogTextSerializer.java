package bondarmih.edu.persistence.serializer.txt;

import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.persistence.serializer.CatalogSerializer;
import bondarmih.edu.util.TextFileReaderWriter;

import java.util.List;

/**
 * Created by bondarm on 06.06.16.
 */
public class CatalogTextSerializer implements CatalogSerializer {
    private static final String filename = "./catalogText.txt";
    private CatalogTextParserMapper processor = new CatalogTextParserMapper();

    @Override
    public void serializeCatalog(Catalog catalog) {
        TextFileReaderWriter.writeToFile(processor.catalogToStringList(catalog), filename);
    }

    @Override
    public Catalog deserializeCatalog() {
        List<String> catalogStringList = TextFileReaderWriter.readFromFile(filename);
        Catalog result = processor.parseCatalog(catalogStringList);
        if (result != null) {
            return result;
        } else {
            throw new IllegalStateException("Catalog is not serialized");
        }
    }
}
