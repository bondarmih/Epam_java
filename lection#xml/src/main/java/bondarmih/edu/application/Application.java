package bondarmih.edu.application;

import bondarmih.edu.util.CatalogToXMLCreator;

/**
 * Created by bondarm on 14.06.16.
 */
public class Application extends Thread {
    public void run() {
        CatalogToXMLCreator creator = new CatalogToXMLCreator();
        creator.create();
    }
}
