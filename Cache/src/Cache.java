public class Cache {

    private final int id;
    private static int size;
    private Video videos;
    private int memoryUsed;

    public Cache(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }
}
