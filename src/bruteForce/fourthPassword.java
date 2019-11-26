package bruteForce;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class fourthPassword
{
        private static int anzahlThreads = 10;
        public static void main(String[] args) throws FileNotFoundException
        {
            Scanner s = new Scanner(new File("passwords\\password3"));
            Scanner fw = new Scanner(new File("passwords\\fabelwesen.txt"));
            ExecutorService es = Executors.newFixedThreadPool(anzahlThreads);
            List<fourthThready> threadList = new ArrayList<>();
            List<String> fabelWesen = new ArrayList<>();
            String password = s.nextLine();
            String crackedPassword = "BRUH";

            while(fw.hasNextLine())
            {
                fabelWesen.add(fw.nextLine());
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