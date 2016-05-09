import java.util.Arrays;
import java.util.Scanner;
/*
import java.util.HashMap;//Originally solved with hashmaps
import java.util.Map;//Originally solved with hashmaps
*/
public class Solution {
    /**
     * Runs in O(n^2 log(n)) time.
     * Finds pairs of elements, where (arr[i] == arr[j]) && (i != j)
     *      --finding unique permutations that is--
     *
     * I mostly handled the input as strings, probably would speed up if I worked with ints instead.
     *
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int lines = Integer.parseInt(in.nextLine());
        int equalVals;
        long total;

        String[] line;
        for(int i = 0; i < lines; i++){//O(n *( nlog(n) ) =  O(n^2 log(n))
            in.nextLine();//i don't need this value in my implementation (size of array)
            total = 0;
            equalVals = 0;

            Arrays.sort(line = in.nextLine().split("\\s+"));//split on ALL whitespace, and sort that string: O(nlogn) - javadocs

            for(int j = 0; j < line.length-1; j++){//stopping at length-1 to prevent indexOutOfBounds exception
                if(line[j].equals(line[j+1]))//end up checking final position here anyway
                    equalVals++;
                else {//didn't encounter an equal value (or end of a string of them..)
                    equalVals++;
                    if(equalVals >= 2) {//since we want to permute in groups of 2
                        //n(n-1) = (n!)/(n!-2!)
                        //also casting to long to avoid any potential overflow for int multiplication
                        total += (long) equalVals * ((long) equalVals - 1);
                    }
                    equalVals = 0;
                }
            }
            if(equalVals != 0){//pair appeared at the end
                equalVals++;
                total += (long) equalVals * ((long)equalVals - 1);
            }
            System.out.println(total);
        }
        /*
        //I originally solved this with using maps..
        //This way could use up 'n' additional memory
        //otherwise runs a bit faster: (O(N^2))
        //compared to O(n^2 log(n)) from above (sort/counting)

        int lines = Integer.parseInt(in.nextLine());
        long total;

        String[] line;

        while (lines-- > 0) { //O(n * (n + n))
            Map<Integer, Integer> map = new HashMap<>();
            in.nextLine();//i dont.. need this value, just want to advance past it
            total = 0;
            line = in.nextLine().split("\\s+");//split on ALL whitespace

            for (String val : line) {//O(n)
                int key = Integer.parseInt(val);
                if (map.containsKey(key))
                    map.put(key, map.get(key) + 1);
                else
                    map.put(key, 1);
            }

            for (int n : map.values()) {
                //n(n-1) = (n!)/(n!-2!), since we want to permute in groups of 2
                //also casting to long to avoid any potential overflow
                total += (long) n * ((long) n - 1);//get total permutations of that value

            }
            System.out.println(total);

        }*/
    }
}
