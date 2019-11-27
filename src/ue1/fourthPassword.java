package ue1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class fourthPassword
{
        private static int anzahlThreads = 12;
        public static void main(String[] args) throws IOException
        {
            Scanner s = new Scanner(new File("passwords\\password3"));
            ExecutorService es = Executors.newFixedThreadPool(anzahlThreads);
            List<fourthThready> threadList = new ArrayList<>();
            List<String> fabelWesen = new ArrayList<>();
            String password = s.nextLine();
            String crackedPassword = "BRUH";

            Document doc = Jsoup.connect("https://de.wikipedia.org/wiki/Liste_von_Fabelwesen").get();
            for(Element e: doc.getElementsByTag("a"))
            {
                fabelWesen.add(e.text());
            }

            int abstand = fabelWesen.size() / anzahlThreads;
            for(int i = 0; i < anzahlThreads; ++i)
            {
                threadList.add(new fourthThready(fabelWesen,password,i * abstand, (i+1) * abstand));
            }

            long startPoint = System.currentTimeMillis();
            try
            {
                crackedPassword = es.invokeAny(threadList);
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
            long endPoint = System.currentTimeMillis();

            System.out.println("Laufzeit: "+(endPoint - startPoint));
            System.out.println("Cracked Password: "+crackedPassword);
        }
}