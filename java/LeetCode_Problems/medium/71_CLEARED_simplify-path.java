class Solution {
    public String simplifyPath(String path) {
        return w_stack(path);
    }
    
    //not the fastest way but this solution works
    public String w_arrlist(String path) {
        if (path.length() == 1) {
            if (path.charAt(0) == '/' || path.charAt(0) == '.') return "/";
            if (path.charAt(0) >= 'a' || path.charAt(0) <= 'z') return "/" + path.charAt(0);
        }
        
        String root = "/";
        ArrayList<String> dir = new ArrayList<>();
        for (String word : path.split("/")) {
            dir.add(word);
        }
        
        dir.removeAll(Arrays.asList("", null, " ", "exit"));
        
        List<String> result = new ArrayList<>(); 
        for (int i = 0; i < dir.size(); i++) {
            String val = dir.get(i);
            if (val.equals("..") && result.size() != 0) {
                result.remove(result.size() - 1);
            }
            else if (val.equals("..") && result.size() == 0) {
                continue;
            }
            else if(val.equals(".")) {
                continue;
            }
            else if(val.equals(" ")) {
                continue;
            }
            else {
                result.add(val + "/");
            }
        }
        
        String output = "/" + String.join("", result);
        if (output.charAt(output.length() - 1) == '/' && output.length() != 1) {
            return output.substring(0, output.length() - 1);
        }
        else {
            return "/" + String.join("", result);
        }
        
        //return "/";
    }
    
    public String w_stack(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");
        
        for (String directory : components) {
            if (directory.equals(".") || directory.isEmpty()) {
                continue; 
            } 
            else if (directory.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } 
            else {
                stack.add(directory);
            }
        }
        
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/");
            result.append(dir);
        }
        
        if (result.length() > 0) {
            return result.toString();
        }
        
        return "/";
    }
}

/*
split keyword
keep in mind of the previous directory 
    We know that "/" is the root level
    We can't go more backwards than "/"
    
$>/ ../../../
$>/

/home
/home/Desktop/folder / <-- "no slash at the end"

we could look at this during "runtime"
stack /
arraylist

list1 /a /. /b /.. / /.. /c
list2

*/
