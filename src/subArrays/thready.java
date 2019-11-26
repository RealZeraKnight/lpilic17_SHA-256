package subArrays;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class thready implements Callable<Integer>
{
    private int teiler;
    private List<Integer> numbers;
    private List<List<Integer>> subNumbers;
    private int maxDifference;
    public thready(int teiler, List<Integer> numbers)
    {
        this.teiler = teiler;
        this.numbers = numbers;
        subNumbers = new ArrayList<>();
        maxDifference = 1;
    }

    @Override
    public Integer call()
    {
        for(int i = 0; i < numbers.size() - teiler; ++i)
        {
            subNumbers.add(numbers.subList(i, i + teiler));
        }
        for(List<Integer> integerList: subNumbers)
        {
            int difference = integerList.stream().distinct().collect(Collectors.toList()).size();
            if(maxDifference < difference)
            {
                maxDifference = difference;
            }
        }
        return maxDifference;
    }
}
