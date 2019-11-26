package bruteForce;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class thirdPassword
{
    private static int anzahlThreads = 59;
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File("passwords\\password2"));
        ExecutorService es = Executors.newFixedThreadPool(anzahlThreads);
        List<thirdThready> threadList = new ArrayList<>();
        String password = s.nextLine();
        String crackedPassword = "BRUH";
        char[] start = "00000".toCharArray();

        for(int i = 0; i < anzahlThreads; ++i)
        {
            String startString = start[0] +""+ start[1] +""+ start[2] +""+ start[3] +""+ start[4];
            threadList.add(new thirdThready(password,startString));
            if(start[0] == '9')
            {
                start[0] = 65;
            }
            else if(start[0] == 'Z')
            {
                start[0] = 97;
            }
            else
            {
                start[0] += 1;
            }
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
