package bondarmih.edu.persistence.serializator;

import bondarmih.edu.catalog.Catalog;

import java.io.Serializable;

/**
 * Created by bondarm on 05.06.16.
 */
public interface CatalogSerializer {

    void serializeCatalog(Catalog serializedItem);


    Catalog deserializeCatalog();
}
