package bondarmih.edu.persistence.serializer;

import bondarmih.edu.catalog.Catalog;

/**
 * Created by bondarm on 05.06.16.
 */
public interface CatalogSerializer {

    void serializeCatalog(Catalog serializedItem);


    Catalog deserializeCatalog();
}
