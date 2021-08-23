boolean alternatingSort(int[] a) {
    //this is two pointes, much easier
    
    if (a.length == 1) return true; 
    
    
    int n = a.length; 
    int max = Integer.MIN_VALUE;
    
    if (n % 2 != 0) {
        if (a[0] > a[n - 1]) return false; 
        n = n - 1; 
    }
    
    for (int i = 0; i < n/2; i++) {
        if (a[i] > a[n - 1 - i]) {
            return false; 
        }
        max = Math.max(max, a[n - 1 - i]);
        
    }
    return true; 

}

//this does not work for odd number of elements
