package subArrays;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Main
{
    private static final int anzahlThreads = 15;
    public static void main(String[] args)
    {
        Scanner s = null;
        try
        {
            s = new Scanner(new File("numbers\\numbers6"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        int teiler = Integer.parseInt(s.nextLine());
        List<Integer> numbers = new ArrayList<>();
        List<thready> threadList = new ArrayList<>();
        int maxDifference = 1;

        while(s.hasNextLine())
        {
            String[] array = s.nextLine().split(" ");
            for(String value : array)
            {
                numbers.add(Integer.parseInt(value));
            }
        }

        ExecutorService es = Executors.newFixedThreadPool(anzahlThreads);
        int abstand = numbers.size() / anzahlThreads;
        for(int i = 0; i < anzahlThreads; ++i)
        {
            threadList.add(new thready(teiler, numbers.subList(i * abstand, (i+1) * abstand)));
        }

        List<Future<Integer>> future;
        try
        {
            future = es.invokeAll(threadList);
            for(Future<Integer> integerFuture: future)
            {
                int difference = integerFuture.get();

                if(maxDifference < difference)
                {
                    maxDifference = difference;
                }
            }
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }



        System.out.println("Max difference: "+maxDifference);

    }
}
