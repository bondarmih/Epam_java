package bondarmih.edu.serializator;

import bondarmih.edu.catalog.Catalog;

import java.io.Serializable;

/**
 * Created by bondarm on 05.06.16.
 */
public interface CatalogSerializator extends Serializable {
    static final String fileName ="catalog.txt";
    void serialize(Catalog catalog);
    Catalog deserialize();
}
