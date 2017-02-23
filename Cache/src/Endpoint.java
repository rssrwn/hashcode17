import java.util.*;
import java.util.stream.Collectors;

public class Endpoint {

    private Map<Video, Integer> videoRequests;
    private Map<Cache, Integer> latencyToCache;
    private final int latencyToDataCenter;
    public List<Cache> listofcaches = new ArrayList<>();
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
        listofcaches.add(cache);
    }

    public int getId() {
        return id;
    }




    public List<Video> listofcandidatevideos() {

        List<Video> vs = videoRequests.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(e -> e.getKey()).collect(Collectors.toList());
        Collections.reverse(vs);
        return vs;
    }

    public List<Cache> listofcacheinascendingorderoflatency() {
        List<Cache> returnlist = latencyToCache.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(e -> e.getKey()).collect(Collectors.toList());
        return returnlist;
    }

}
