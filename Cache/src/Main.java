import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // String locIn = args[0];
        String locIn = "assets/Example.in";
        Parser parser = new Parser(locIn);
    }

}
