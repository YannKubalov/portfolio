
import java.util.concurrent.ForkJoinPool;

public class main {
    public static void main(String[] args) {
        linksReader test = new linksReader("https://skillbox.ru/");
        test.linksWriter(test.url, 3);
        new ForkJoinPool().invoke(test);
    }
}
