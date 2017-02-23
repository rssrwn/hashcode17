import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Algorithm {

  private List<Endpoint> endpoints;


  public Algorithm(List<Endpoint> endpoints) {
    this.endpoints = endpoints;
  }

  public Set<Cache> cacheVideos() {
    Set<Cache> cachesUsed = new HashSet<>();

    for (Endpoint endpoint : endpoints) {
      Map<Cache, Integer> caches =  endpoint.getCaches();
      Queue<Cache> cachesQueue = new ArrayDeque(caches.keySet());
      List<Video> videos = endpoint.listofcandidatevideos();
      Cache cache = cachesQueue.poll();
      ListIterator<Video> videoIterator = videos.listiterator();
      while (videoIterator.hasNext() && !cachesQueue.isEmpty()) {
        Video video = videoIterator.next();
        boolean isCached = cache.addToCache(video);
        while (!isCached && !cachesQueue.isEmpty()) {
          cache = cachesQueue.poll();
          isCached = cache.addToCache(video);
        }
      }
    }
    return cachesUsed;
  }
}
