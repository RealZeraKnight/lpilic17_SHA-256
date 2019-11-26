package ue1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class firstPassword
{
    private static int anzahlThreads = 26;
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File("passwords\\password0"));
        ExecutorService es = Executors.newFixedThreadPool(anzahlThreads);
        List<firstThready> threadList = new ArrayList<>();
        String password = s.nextLine();
        String crackedPassword = "BRUH";

        char[] start = "aaaa".toCharArray();

        for(int i = 0; i < anzahlThreads; ++i)
        {
            String startString = start[0] +""+ start[1] +""+ start[2] +""+ start[3];
            threadList.add(new firstThready(password,startString));
            start[0] += 1;
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
