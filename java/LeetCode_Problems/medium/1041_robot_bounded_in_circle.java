class Solution {
    public boolean isRobotBounded(String instructions) {
        //return verbose(instructions);
        return math_solution(instructions);
    }
    public boolean partial(String instructions) {
        /*
        This is a graph question
            Possible "keys": 
            L: Turn left
            R: Turn right
            G: Go forward
            
        This is what we should do: 
            1. We need to "virtualize" where this robot is going
            (Like what direction is this robot facing)
            2. We also need to keep track of where (how far) is this robot going
        
        This combination will allow us to better understand the robot's position and direction 
            When the robot is traverlling north: +1
            When the robot is travelling south: -1
            When the robot is travelling right: +1
            Whe nthe robot is travelling south: +1
        
        Initial position: (0,0) facing north
        
        How to keep track of where the robot is facing
        LRLLLL
            left_stack: 5
            right_stack: 1
            2 - 1 = 1 (positive, turn left once)
            5 - 1 = 4 (positive, turn left four times)
            
            How to determine LHFR
            Left: if the number is number is odd
            right: if the number is odd and negative
            front: if the number is either 0 or divisible by 4
            back: if the number is even, negative OR positive and divisible by 2
            
        If, at the end, all values are not equal to 0, 
        */
        
        if (!instructions.contains("G")) return true; 
        
        int left = 0; 
        int right = 0; 
        int forward = 0; 
        int back = 0; 
        
        Stack<Integer> left_stack = new Stack<>(); 
        Stack<Integer> right_stack = new Stack<>(); 
        
        for (int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
            if (c == 'L') {
                left_stack.push(1);
                continue;
            }
            else if (c == 'R') {
                right_stack.push(1);
                continue; 
            }
            else {
                int size = left_stack.size() - right_stack.size(); 
                if (size == 0 || size % 4 == 0) forward++; 
                else if (size % 2 == 0) back--; 
                else if (size < 0) left--; 
                else right++; 
            }
        }
        /*
        System.out.println(left);
        System.out.println(right);
        System.out.println(forward);
        System.out.println(back);
        */
        
        if (left + right + back + forward == 0 || left + right + back + forward == 1) return true; 
        
        return false; 
        
        
    }
    
    public boolean verbose(String instructions) {
        if (!instructions.contains("G")) return true; 
        
        int x = 0; 
        int y = 0; 
        String position = "North";
        
        for (int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
            
            if (c == 'G') {
                if (position.equals("North")) {
                    y++; 
                }
                else if (position.equals("West")) {
                    x--;
                }
                else if (position.equals("East")) {
                    x++;
                }
                else if (position.equals("South")) {
                    y--; 
                }
            }
            else {
                if (c == 'L') {
                    if (position.equals("North")) position = "East";
                    else if (position.equals("East")) position = "South";
                    else if (position.equals("South")) position = "West";
                    else position = "North";
                }
                else {
                    if (position.equals("North")) position = "West";
                    else if (position.equals("West")) position = "South";
                    else if (position.equals("South")) position = "East";
                    else position = "North";
                }
            }
        }
        
        if (y == 0 && x == 0 || !position.equals("North")) return true; 
        
        return false; 
    }
}

