package bondarmih.edu.catalog;

import java.io.Serializable;

/**
 * Created by bondarm on 05.06.16.
 */
public interface CatalogItem extends Serializable {
    static final String fileExt =".txt";
    void serialize();
    void deserialize();
}
