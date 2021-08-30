boolean[] boundedRatio(int[] a, int l, int r) {
    //a[i] = (i + 1) * x
    //our job is to find "x"
    //instead of that algebraic forumla, it should be a[i](i + 1) = x
    //caveat here is that x must be between l and r. 
    
    boolean[] b = new boolean[a.length];
    
    for (int i = 0; i < a.length; i++) {
        double s = a[i];
        double x = s / (i + 1);
        System.out.print(x + " ");
        
        
        if (x >= l && x <= r && x % 1 == 0) { 
            b[i] = true;
        }
        else {
            b[i] = false; 
        }
    }
    return b;
}

public boolean contains(final int[] array, double key) {
    
    for (final int i : array) {
        if (i == key) {
            return true;
        }
    }
    return false;
}

