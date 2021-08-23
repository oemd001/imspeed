int[] mutateTheArray(int n, int[] a) {
    //there are three values that are being "mutated"
    //first index of b will add itself, a[0] and a[1]; (this is index 0)
    //second index of b will be a[0] + a[1] + a[2];
    //this will keep going until it reaches the end, which will be a[n - 2] + a[n - 1] + 0 (where n is a.length)
    if (a.length == 0) return null;
    if (a.length == 1) return a; 
    
    int[] b = new int[a.length];
    int f = 0 + a[0] + a[1];
    b[0] = f;
    
    for (int i = 1; i < n - 1; i++) {
            b[i] = a[i - 1] + a[i] + a[i + 1];
    }
    
    b[a.length - 1] = a[n - 2] + a[n - 1];
    return b;
}

//I took way too long for this holy crap
