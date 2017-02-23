import com.sun.org.apache.xml.internal.security.algorithms.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Parser {

  private Scanner scanner;
  private Map<Integer, Cache> caches;
  private Map<Integer, Video> videos;
  private Map<Integer, Endpoint> endpoints;
  private Map<Video, Integer> requestNo;
  private List<Endpoint> ls = new ArrayList<>();

  public Parser(String locIn) throws FileNotFoundException {
    this.scanner = new Scanner(new File(locIn));
    this.caches = new HashMap<>();
    this.videos = new HashMap<>();
    this.endpoints = new HashMap<>();
    this.requestNo = new HashMap<>();
    parse();
    Algorithm a = new Algorithm((List)endpoints.values());
    Set<Cache> s = a.cacheVideos();
      PrintStream ps = new PrintStream(new File("out/rishi" + locIn ));
      ps.println(s.size());
      for(Cache c:s) {
          ps.println(c.getId() + " " + c.getVideos());
      }
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
      Video video = new Video(i, size);
      videos.put(i,video);
      requestNo.put(video,0);
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
      ls.add(current);
    }

    for (int n = 0; n < noOfReqDesc; n++) {
      int videoNo = scanner.nextInt();
      int endPoint = scanner.nextInt();
      int req = scanner.nextInt();
      Endpoint currentEndPoint = endpoints.get(endPoint);
      Video currentVid = videos.get(videoNo);
      currentEndPoint.addVidReq(currentVid, req);
      int oldReq = requestNo.get(currentVid);
      int newReq = req + oldReq;
      requestNo.replace(currentVid,oldReq,newReq);
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
