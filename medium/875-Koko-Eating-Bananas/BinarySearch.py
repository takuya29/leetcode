class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        left = 0
        right = max(piles)
        
        while right - left > 1:
            mid = (left + right) // 2
            required_hour = sum((p + mid - 1) // mid for p in piles)
            if required_hour <= h:
                right = mid
            else:
                left = mid
        
        return right
