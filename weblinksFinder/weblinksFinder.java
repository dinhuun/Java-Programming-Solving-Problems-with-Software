/**
 * Find youtube.com links in webpage
 * @DinhHuuNguyen
 * @01/06/2016
 */
import edu.duke.*;
import java.io.*;
public class weblinksFinder{
    public void testweblinksFinder() {
        URLResource webpage = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : webpage.words()) {
           String wordlower = word.toLowerCase();            
           int id = wordlower.indexOf("youtube.com");
           if (id != -1){
           int beg = word.lastIndexOf("\"", id);
           int end = word.indexOf("\"", id);
           System.out.println(word.substring(beg + 1, end));
          }
        }
    }
}
