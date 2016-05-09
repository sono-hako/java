import java.util.Scanner;

public class Solution {
    //Using a 3-D array for this
    static final int INTS_LINE = 0;//y-index for input lines: arr[][INTS_LINE][]
    static final int SUMS = 1;//y-index for sum: arr[][SUMS][]
    static final int L_SUM = 0;//z-index for left sum: arr[][SUMS][L_SUM]
    static final int R_SUM = 1;//z-index for right sum: arr[][SUMS][R_SUM]

    /**
     * Runs in O(n) time
     *
     * The way this works is that when we first read in arr[]
     * took full sum of it, stored in arr[][][R_SUM].
     * This is the right side sum.
     *
     * Now we just walk across the array
     * subtracting from right_side_sum, and compare to value of left_side_sum
     * if not equal, add value at current position to left_side_sum, repeat until end or point found
     *
     * Keep in mind: This only finds the -first- point where the array is balanced.
     *
     * arr[]   = array to check
     * sums[L_SUM] = left side sum
     * sums[R_SUM] = right side sum
     *
     * return TRUE  : Balance point exists
     * return FALSE : No point exists
     */
    public static boolean balance(int arr[], int sums[]){
        for(int value : arr){//hit every position in arr
            if(sums[L_SUM] == (sums[R_SUM] -= value))
                return true;
            sums[L_SUM] += value;
        }
        return false;
    }
    /**
     * Technically the whole program should run in O(n^2)(?)
     * Needed extra iterations to parse input correctly from STDIN
     *
     * I'm not doing any error checking for the input either.
     */
    public static void main(String[] args) {
        int[][][] arr;
	    Scanner line = new Scanner(System.in);
        Scanner token = new Scanner(line.nextLine());
        arr = new int[token.nextInt()][2][];

        for(int i = 0, j = 0; i < arr.length; i++, j=0){
            token = new Scanner(line.nextLine());
            // - Allocating and initializing space in storage -
            arr[i][INTS_LINE] = new int[token.nextInt()];
            arr[i][SUMS] = new int[2];//Only need 2 positions for sum (left/right)
            arr[i][SUMS][L_SUM] = 0;
            arr[i][SUMS][R_SUM] = 0;
            //  - END: Allocating and initializing space -
            //  - Read in input lines and populate INTS_LINE and R_SUM appropriately -
            token = new Scanner(line.nextLine());
            while(token.hasNextInt()){
                arr[i][INTS_LINE][j] = token.nextInt();
                arr[i][SUMS][R_SUM] += arr[i][INTS_LINE][j++];
            }
            //  - END: input read -
        }

        for(int[][] check : arr){
            if(balance(check[INTS_LINE], check[SUMS])){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
