import java.util.HashMap;
import java.util.Map;

public class Cache {

    private final int id;
    private static int size;
    private Map<Video,Integer> videoMap = new HashMap<>();
    private int score = 0;
    private int memoryUsed;

    public Cache(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public boolean addVideo(Video video, Endpoint endpoint) {

    }

    private void
}
