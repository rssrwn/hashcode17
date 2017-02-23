import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {

  private Scanner scanner;
  private Map<Integer, Cache> caches;
  private Map<Integer, Video> videos;
  private Map<Integer, Endpoint> endpoints;

  public Parser(String locIn) throws FileNotFoundException {
    this.scanner = new Scanner(new File(locIn));
    this.caches = new HashMap<>();
    this.videos = new HashMap<>();
    this.endpoints = new HashMap<>();
    parse();
  }

  private void parse() {
    int noOfVideos = scanner.nextInt();
    int noOfEndpoints = scanner.nextInt();
    int noOfReqDesc = scanner.nextInt();
    int noOfCaches = scanner.nextInt();
    int cacheSize = scanner.nextInt();

    for (int m = 0; m < noOfCaches; m++) {
      Cache current = new Cache(m, cacheSize);
      caches.put(m, current);
    }

    for (int i = 0; i < noOfVideos; i++) {
      int size = scanner.nextInt();
      videos.put(i, new Video(i, size));
    }

    for (int j = 0; j < noOfEndpoints; j++) {
      int latency = scanner.nextInt();
      int cachesConnected = scanner.nextInt();

      Endpoint current = new Endpoint(j, latency);
      for (int k = 0; k < cachesConnected; k++) {
        int currentCache = scanner.nextInt();
        int speed = scanner.nextInt();
        Cache nowCache = caches.get(currentCache);
        current.addCache(nowCache, speed);
      }
      endpoints.put(j, current);
    }

    for (int n = 0; n < noOfReqDesc; n++) {
      int videoNo = scanner.nextInt();
      int endPoint = scanner.nextInt();
      int req = scanner.nextInt();
      Endpoint currentEndPoint = endpoints.get(endPoint);
      Video currentVid = videos.get(videoNo);
      currentEndPoint.addVidReq(currentVid, req);
    }
    System.out.println();
  }

  public Map<Integer, Cache> getCaches() {
    return caches;
  }

  public Map<Integer, Video> getVideos() {
    return videos;
  }

  public Map<Integer, Endpoint> getEndpoints() {
    return endpoints;
  }
}
