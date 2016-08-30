package bondarmih.edu.utility;

/**
 * Created by bondarm on 17.05.16.
 */
public class TimeCounter {
    private long startTime;
    private boolean isActive;
    public TimeCounter () {
        this.startTime = System.currentTimeMillis();
        this.isActive = true;
    }

    public long stop() {
        long endTime = System.currentTimeMillis();
        this.isActive = false;
        return endTime - this.startTime;
    }


}
