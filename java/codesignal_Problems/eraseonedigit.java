boolean eraseOneDigit(String firstnum, String secondnum, String thirdnum) {
    //firstnum + secondnum = thirdnum
    if (Integer.valueOf(firstnum) + Integer.valueOf(secondnum) == Integer.valueOf(thirdnum)) return true;
    
    for (int i = 0; i < firstnum.length(); i++) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < firstnum.length(); j++) {
            if (j != i) {
                sb.append(firstnum.substring(j, j + 1));
            }   
        }
        String first = sb.toString(); 
        if (Integer.valueOf(first) + Integer.valueOf(secondnum) == Integer.valueOf(thirdnum)) {
            System.out.print(first.indexOf("0"));
            if (first.indexOf("0") == 0 && Integer.valueOf(thirdnum) != 0 &&  Integer.valueOf(thirdnum) <= 0) 
return false; 
            return true; 
        }
         
    }
    return false; 
}

