int maxArithmeticLength(int[] a, int[] b) {
    int minDiff = findMinDiff(a);
        int i=1,j = 1,M = a.length, N = b.length, count = 1, previous = a[0];
        Set<Integer> listA = Arrays.stream(a).boxed().collect(Collectors.toSet());
        Set<Integer> listB = Arrays.stream(b).boxed().collect(Collectors.toSet());
        System.out.println("listA.contains(previous+minDiff) "+listA.contains(4));

        int res = 0;
        for(int k = 0; k < minDiff; k++){
          while (i<M || j < N){
              if (listA.contains(previous+ k)) {
                  previous = previous+ k;
                  count++;
                  i++;
              }
              else if(listB.contains(previous + k)) {
                  previous =previous + k;
                  count++;
                  j++;

              }
              else break;
            
          }
          res = Math.max(res, i< a.length ? -1 : count);
        }
        return res;
    }
private int findMinDiff(int[] a) {
        int min = Integer.MAX_VALUE;
        for(int i=0; i<a.length-1;i++){
                min = Math.min(min,a[i+1]-a[i]);
        }
        return min;
    }

