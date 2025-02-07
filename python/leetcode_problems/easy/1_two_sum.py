class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        if (len(nums) == 0): 
            return []

        dict = {}
        for idx, i in enumerate(nums): 
            complement = target - i
            dict[complement] = idx
        
        for idx, i in enumerate(nums): 
            if (i in dict and dict[i] != idx):
                return [idx, dict[i]]
        return [0,0]

    def twoSum_poorly_written(self, nums: List[int], target: int) -> List[int]:
        if (len(nums) == 0): 
            return []
        
        """
        Best solution: 
            We want to iterate over a list and effectively find the complement solution. 

            1. Dictionary: iterate over list and set the complement values
            {complement, index}
            2. Iterate over the list again and if the complement exists in the dictionary, return the value of the key and the current key. (note: current index and complement index cannot be the same)
        """
        idx = 0
        dict = {}
        for i in nums: 
            complement = target - i
            dict[complement] = idx
            idx = idx + 1
        
        idx = 0
        for i in nums: 
            if (i in dict):
                if (dict[i] != idx):
                    return [dict[i], idx]
            idx = idx + 1

        return [1,2]
