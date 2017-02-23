import java.util.*;
import java.util.stream.Collectors;

public class Cache {

    private final int id;
    private static int size;
    private Map<Video,Integer> videoMap = new HashMap<>();
    private int memoryUsed = 0;

    public Cache(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public boolean addVideo(Video video, Endpoint endpoint) {
        int vidScore = endpoint.getVideoScore(video, this);
        int vidSize = video.getSize();
        if (vidSize > size) {
            return false;
        }
        if (memoryUsed + vidSize > size) {
            List<Video> smallestVids = sortVideos();
            List<Video> toRemove = new ArrayList<>();
            int i = 0;
            while (memorySum(toRemove) < vidSize) {
                toRemove.add(smallestVids.get(i));
                i++;
            }
            int score = scoreSum(toRemove);

            if (score < vidScore) {
                videoMap.put(video, vidScore);
                memoryUsed -= memorySum(toRemove);
                memoryUsed += vidSize;
                return true;
            } else {
                return false;
            }

        } else {
            videoMap.put(video, vidScore);
            memoryUsed += vidSize;
            return true;
        }

    }


    private List<Video> sortVideos() {
        return videoMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(e -> e.getKey())
                .collect(Collectors.toList());
    }

    private int memorySum(List<Video> input) {
        return input.stream()
                .mapToInt(v -> v.getSize())
                .sum();
    }

    private int scoreSum(List<Video> input) {
        return input.stream()
                .mapToInt(v -> videoMap.get(v))
                .sum();
    }

}
