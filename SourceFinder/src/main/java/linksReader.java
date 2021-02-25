import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.RecursiveAction;


public class linksReader extends RecursiveAction {
    public String url;
    public int level;
    public HashSet<String> links;
    List<linksReader> taskList = new ArrayList<>();

    public linksReader(String url, int level) {
        this.url = url;
        this.level = level;

        try {
            this.links = linksGetter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public linksReader(String url) {
        this.url = url;
        this.level = counter(url) + 1;
        try {
            this.links = linksGetter();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public HashSet<String> linksGetter() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("a");
        HashSet<String> rightURL = new HashSet<>();
        elements.forEach(element -> {
            if (!element.absUrl("href").contains("#") &&
                    element.attr("abs:href").contains(url) &&
                    counter(element.attr("abs:href")) == level) {
                rightURL.add(element.absUrl("href"));
            }
        });
        return rightURL;
    }

    public static int counter(String url) {
        String cut = url;
        int counter = 0;
        for (int i = 0; i <= url.length(); i++) {
            if (cut.contains("/")) {
                cut = cut.substring(cut.indexOf("/") + 1);
                counter++;
            }

        }
        return counter;
    }


    public void linksWriter(String link, int level) {
        try {
            FileWriter writer = new FileWriter("src/main/java/links.txt", true);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 3; i < level; i++) {
                stringBuilder.append("\t");
            }
            String str = stringBuilder.append(link).append("\n").toString();
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean hasNext(String link) {
        linksReader check = new linksReader(link);
        return !check.links.isEmpty();
    }
    @Override
    protected void compute() {
        for (String link : links) {
            linksWriter(link, level);
            if (hasNext(link)) {
                linksReader task1 = new linksReader(link);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task1.fork();
                taskList.add(task1);
            }
            for (linksReader task : taskList) {
                task.join();
            }
        }

    }
}



