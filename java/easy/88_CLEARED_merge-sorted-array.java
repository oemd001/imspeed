class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.out.print("[");
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                System.out.print(nums2[i] + " ");
            }
        }
        else if (n == 0) {
             for (int i = 0; i < m; i++) {
                System.out.print(nums1[i] + " ");
            }
        }
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        
        Arrays.sort(nums1);
        for (int i = 0; i < m; i++) {
                System.out.print(nums1[i] + " ");
            }
        System.out.print("]");
    }
}

//edge cases
/*
if one array's array.length is 0, return nums2.length
Must be stored within nums 1
*/
