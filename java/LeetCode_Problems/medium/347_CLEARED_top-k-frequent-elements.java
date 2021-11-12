import static java.util.stream.Collectors.*;
 
import java.lang.*;
import java.util.*;
import java.util.stream.*;
import java.util.stream.Collectors;

class Solution {
    int[] unique;
    Map<Integer, Integer> count;

    public int[] topKFrequent(int[] nums, int k) {
        return quicksort(nums, k);
    }
    
    public static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm) {
        /*
        HashMap<Integer, Integer> temp = hm.entrySet()
                  .stream()
                  .sorted((i1, i2)
                              -> i1.getValue().compareTo(
                                  i2.getValue())).collect(Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue,
                      (e1, e2) -> e1, LinkedHashMap::new));
        */
        
        return hm.entrySet()
            .stream()
            .sorted(Map.Entry.<Integer, Integer> comparingByValue().reversed())
            // Type here -----^ reversed() here -------^
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
            ));
        //return temp;
    }
    
    public int[] hash(int[] nums, int k) {
        if (nums.length == 1) return nums; 
        
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(); 
        
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 0);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        
        Map<Integer, Integer> hm1 = sortByValue(map);
        int val = 0; 
        
        if (k == 1) {
            for (Map.Entry<Integer, Integer> it : hm1.entrySet()) {
            //val = Integer.parseInt(it.getValue());
                val = it.getValue();
                list.add(it.getKey());
                return list.stream().mapToInt(Integer::intValue).toArray();
            }
        }
        
        
        for (Map.Entry<Integer, Integer> it : hm1.entrySet()) {
            //val = Integer.parseInt(it.getValue());
            val = it.getValue();
            if (k > 0) {
                list.add(it.getKey());
            }
            k--;
        }
        
        System.out.println(hm1);
        if (list.size() == 0) {
            return nums;
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }

    public int partition(int left, int right, int pivot_index) {
        int pivot_frequency = count.get(unique[pivot_index]);
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;

        // 2. move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pivot_frequency) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }
    
    public void quickselect(int left, int right, int k_smallest) {
        /*
        Sort a list within left..right till kth less frequent element
        takes its place. 
        */

        // base case: the list contains only one element
        if (left == right) return;
        
        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left); 

        // find the pivot position in a sorted list
        pivot_index = partition(left, right, pivot_index);

        // if the pivot is in its final sorted position
        if (k_smallest == pivot_index) {
            return;    
        } else if (k_smallest < pivot_index) {
            // go left
            quickselect(left, pivot_index - 1, k_smallest);     
        } else {
            // go right 
            quickselect(pivot_index + 1, right, k_smallest);  
        }
    }
    
    public int[] quicksort(int[] nums, int k) {
        // build hash map : character and how often it appears
        count = new HashMap();
        for (int num: nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        // array of unique elements
        int n = count.size();
        unique = new int[n]; 
        int i = 0;
        for (int num: count.keySet()) {
            unique[i] = num;
            i++;
        }
        
        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till
        // (n - k)th less frequent element takes its place (n - k) in a sorted array. 
        // All element on the left are less frequent.
        // All the elements on the right are more frequent. 
        quickselect(0, n - 1, n - k);
        // Return top k frequent elements
        return Arrays.copyOfRange(unique, n - k, n);
    }

}
