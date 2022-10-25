import java.util.Arrays;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 0;
        int high = Arrays.stream(piles).max().getAsInt();
        
        while (high - low > 1) {
            int mid = (high + low) / 2;
            long requiredHour = Arrays.stream(piles).asLongStream()
                .map(x -> (x + mid - 1) / mid)
                .sum();
            if (requiredHour <= h) {
                high = mid;
            } else {
                low = mid;
            }
            
        }
        return high;
    }
}
