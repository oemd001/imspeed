class Solution {
    public int[] maxNumber(int[] a, int[] b, int k) {
        int n = a.length;
        int m = b.length;
        
        int x[][] = new int[n][];
        int y[][] = new int[m][];
        for(int i=0;i<n;i++)
            x[i] = new int[i+1];
        for(int i=0;i<m;i++)
            y[i] = new int[i+1];
        
        build(a , x);
        build(b , y);
        
        int ans[] = new int[k];
        
        for(int i=0;i<=k;i++){
            int count1 = i;
            int count2 = k-i;
            if(count1<=n && count2<=m){
                int left[] = new int[0];
                int right[] = new int[0];
                if(count1>0)
                    left = x[count1-1];
                if(count2>0)
                    right = y[count2-1];
                int now[] = max(left , right);
                if(less(ans , now)<0){
                    ans = now;
                }
            }
        }
        return ans;
    }
    
    public int[] max(int a[] , int b[]){
        int c[] = new int[a.length+b.length];
        int i=0;
        int j=0;
        int k=0;
        while(i<a.length && j<b.length){
            if(a[i] == b[j]){
                int x = check(a , b , i , j);
                if(x == 0)
                    c[k++] = a[i++];
                else
                    c[k++] = b[j++];
            }
            else if(a[i]>b[j])
                c[k++] = a[i++];
            else
                c[k++] = b[j++];
        }
        while(i<a.length)
            c[k++] = a[i++];
        while(j<b.length)
            c[k++] = b[j++];
        return c;
    }
    
    public int check(int a[] , int b[] , int s1 , int s2){
        // 0 -> a 1 -> b
        int i=s1;
        int j=s2;
        while(i<a.length && j<b.length){
            if(a[i] == b[j]){
                i++;
                j++;
                continue;
            }
            if(a[i]<b[j])
                return 1;
            else
                return 0;
        }
        if(i == a.length)
            return 1;
        return 0;
    }
    
    public void build(int a[] , int x[][]){
        for(int i=0;i<a.length;i++){
            int num = a[i];
            if(i == 0){
                x[i][0] = num;
                continue;
            }
            for(int j=i;j>0;j--){
                int res = less(x[j] , x[j-1]);
                if(res>0);
                else if(res == 0){
                    if(x[j][j]<num){
                        x[j][j] = num;
                    }
                }
                else{
                    copy(x[j-1] , x[j]);
                    x[j][j] = num;
                }
            }
            if(x[0][0]<num)
                x[0][0] = num;
        }
    }
    
    public void copy(int a[] , int b[]){
        for(int i=0;i<a.length;i++)
            b[i] = a[i];
    }
    
    public int less(int a[] , int b[]){
        // 0 -> equal -1 -> less 1 -> more
        for(int i=0;i<Math.min(a.length , b.length);i++){
            if(a[i] == b[i])
                continue;
            else if(a[i]<b[i])
                return -1;
            return 1;
        }
        return 0;
    }
    
}
