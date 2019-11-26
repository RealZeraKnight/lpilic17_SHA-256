package ue3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner s = null;
        try
        {
            s = new Scanner(new File("sites\\sample3.html"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        List<String> lines = new ArrayList<>();
        List<String> linesLines = new ArrayList<>();
        while(s.hasNextLine())
        {
            lines.add(s.nextLine());
        }

        for(String line: lines)
        {
            String[] array = line.split(">");
            for(String br : array)
            {
                if(!br.startsWith("<"))
                {
                    String[] array2 = br.split("<");
                    for(String br2 : array2)
                    {
                        if(!br2.startsWith("/"))
                        {
                            if(br2.toCharArray().length >= 2)
                            {
                                linesLines.add(br2);
                            }
                        }

                    }
                }

            }
        }

        for(String text : linesLines)
        {
            System.out.println(text);
        }

    }
}
