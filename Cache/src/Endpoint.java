import java.util.HashMap;
import java.util.Map;

public class Endpoint {

    private Map<Video, Integer> videoRequests;
    private Map<Cache, Integer> latencyToCache;
    private final int latencyToDataCenter;
    private final int id;

    public Endpoint(int id, int latencyToDataCenter) {
        this.id = id;
        this.latencyToDataCenter = latencyToDataCenter;
        this.videoRequests = new HashMap<>();
        this.latencyToCache = new HashMap<>();
    }

    public void addVidReq(Video video, Integer request){
        videoRequests.put(video, request);
    }

    public void addCache(Cache cache, Integer latency){
        latencyToCache.put(cache, latency);
    }

    public int getId() {
        return id;
    }
}
