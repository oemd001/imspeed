class Solution {
    public int mySqrt(int x) {
        //Binary Search
        long begin = 0; 
        long end = x;
        
        while (begin + 1 < end) {
            //this is the binary search algorithm. 
            //This is used when you want to find the midpoint quickly
            long mid = begin + (end - begin) / 2;
            if (mid * mid == x) {
                return (int)mid; 
            }
            else if (mid * mid < x) {
                begin = mid; 
            }
            else {
                end = mid; 
            }
        }
        if (end * end == x) {
            return (int)end;
        }
        return (int)begin; 
    }
}





//copied solution
/*
class Solution {
    public int mySqrt(int x) {
	if (x == 0) return 0;
	int start = 1, end = x;
	while (start < end) { 
		int mid = start + (end - start) / 2;
		if (mid <= x / mid && (mid + 1) > x / (mid + 1))// Found the result
			return mid; 
		else if (mid > x / mid)// Keep checking the left part
			end = mid;
		else
			start = mid + 1;// Keep checking the right part
	}
	return start;
}
}
*/
