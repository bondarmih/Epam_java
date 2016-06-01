package bondarmih.edu.diningphilosophers;

import javax.xml.ws.Holder;
import java.text.Format;

/**
 * Created by bondarm on 31.05.16.
 */
public class Fork {
    private int id;
    private Philosopher holder = null;

    public Fork(int id) {
        this.id = id;
    }

    public Philosopher getHolder() {
        return this.holder;
    }

    public void pick(Philosopher holder) {
        if (this.holder == null) {
            this.holder = holder;
            //System.out.println("Fork #" + id + "picked by Ph #" + holder.getId());
        }
        else {
            throw new IllegalStateException("New holder " + holder.toString() + ";fork #" + this.id + "; old holder " + this.holder.toString());
        }
    }

    public void drop() {
        //System.out.println("Fork #" + id + "dropped by Ph #" + holder.getId());
        this.holder = null;
    }
}
