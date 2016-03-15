/**
 * Prints out all links within the HTML source of a web page. 
 * @author Duke Software Team 
 */
import edu.duke.*;
public class urlFinder {
    
    public StorageResource findURLs(String url) {
        URLResource page = new URLResource(url);
        String source = page.asString();
        StorageResource store = new StorageResource();
        int start = 0;
        while (true) {
            int index = source.indexOf("href=", start);
            if (index == -1) {
                break;
            }
            int startQuote = index+6; // after href="
            int endQuote = source.indexOf("\"", startQuote);
            String sub = source.substring(startQuote, endQuote);
            if (sub.startsWith("http")) {
                store.add(sub);
            }
            start = endQuote + 1;
        }
        return store;
    }
    
    public void testURL() {
        StorageResource store = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        int securelinks = 0;
        int dotcom = 0;
        int enddotcom = 0;
        int enddotcom1 = 0;
        int dot = 0;
        for (String link : store.data()) {
            System.out.println(link);
            if (link.indexOf("https") != -1) {securelinks +=1;}
            if (link.indexOf(".com") != -1) {dotcom += 1;}
            if (link.indexOf(".com") != -1 && link.indexOf(".com") == link.length() - 4 ) {enddotcom +=1;}
            if (link.indexOf(".com/") != -1 && link.indexOf(".com/") == link.length() - 5) {enddotcom1 +=1;}
            int start = 0;
            while (true) {
                int index = link.indexOf(".", start);
                if (link.indexOf(".", start) == -1) {break;}
                start = index + 1;
                dot += 1;
            }
        }
        System.out.println("all links \t" + store.size());
        System.out.println("secure links \t" + securelinks);
        System.out.println("dotcom links \t" + dotcom);
        System.out.println("links that end with .com \t" + enddotcom);
        System.out.println("links that end with .com/ \t" + enddotcom1);
        System.out.println("all dots \t" + dot);
    }
    
}
