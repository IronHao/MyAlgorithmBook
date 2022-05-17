import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord 
{
    public static void main(String[] args)
    {
        String survivingChampion = "";
        int currentIndex = 1;
        while (!StdIn.isEmpty())
        {
            String temp = StdIn.readString();
            Double p = 1.0 / Double.valueOf(currentIndex);
            if (StdRandom.bernoulli(p))
            {
                survivingChampion = temp;
            }
            currentIndex++;
        }
        System.out.println(survivingChampion);
    }
}
