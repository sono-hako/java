
import java.util.Scanner;

public class Solution {
    static final int MAX_RANGE = 100;//given constraint for 'val' integer range

    /**
     * I wanted to keep the input values together. Felt like it was easier this way.
     * Made this class to represent them
     *
     * Sample:
     * [val] [word]
     *  0     ab
     *  6     cd
     *  0     ef
     *
     */
    private class IntStrings{
        int val;
        String word;

        public IntStrings(int x, String word){
            this.val = x;
            this.word = word;
        }
    }

    /**
     * Overall runs in O(n) time.
     *     */
    public static void main(String[] args) {
        Solution m = new Solution();//so i can access my private class in this scope
	    Scanner in = new Scanner(System.in);

        int size = Integer.parseInt(in.nextLine());
        int mid = (size / 2);//point to stop replacing words with "-"
        int[] tmp = new int[MAX_RANGE + 1];//needed max_size + 1 for counting sort
        IntStrings[] out = new IntStrings[size];//output (sorted) array
        IntStrings[] arr = new IntStrings[size];//input (unsorted) array



        for(int i = 0; in.hasNextLine(); i++){
            String[] split = in.nextLine().split("\\s+");//split on ALL whitespace
            arr[i] = m.new IntStrings(Integer.parseInt(split[0]), split[1]);
            if(i < mid){
                arr[i].word = "-";//lets handle the stuff we don't want need right now
                //i suppose i could extend this to just ignore these elements too
            }
        }

        /*
        counting sort starts here
        stable sort, so it'll be fine for this problem
         */

        for(int i = 0; i < arr.length; i++){
            tmp[arr[i].val] += 1;//count occurrences of this value
        }

        for(int i = 1; i < tmp.length; i++){
            tmp[i] += tmp[i - 1];//sum of elements at this index, and previous ones
            //that is, these now become the new indices of the sorted array
        }

        for(int i = (arr.length - 1); i >= 0; i--){//iterate backwards to get correct order now
            out[tmp[arr[i].val] - 1] = arr[i];//place into sorted output array, with our new indices
            tmp[arr[i].val] -= 1;
        }

        StringBuilder br = new StringBuilder();
        for(IntStrings s : out){
            br.append(s.word).append(" ");
            //i'd prefer to print this out all at once
            //since max list size is 1,000,000 I figure printing would be slow
        }
        System.out.println(br);
    }
}
