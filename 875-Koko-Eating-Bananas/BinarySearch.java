class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 0;
        int high = piles[0];
        for (int pile: piles) high = Math.max(high, pile);
        
        while (high - low > 1) {
            int mid = (high + low) / 2;
            long requiredHour = 0;
            for (int pile: piles) requiredHour += ((pile + mid - 1) / mid);
            
            if (requiredHour <= h) {
                high = mid;
            } else {
                low = mid;
            }
            
        }
        return high;
    }
}
