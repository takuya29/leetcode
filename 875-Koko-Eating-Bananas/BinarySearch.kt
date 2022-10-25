class Solution {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var low = 0
        var high = piles.max()!!
        
        while (high - low > 1) {
            val mid = (high + low) / 2
            var requiredHour: Long = 0
            for (pile in piles) {
                requiredHour += (pile + mid - 1) / mid
            }
            if (requiredHour <= h) {
                high = mid
            } else {
                low = mid
            }
        }
        
        return high
    }
}
