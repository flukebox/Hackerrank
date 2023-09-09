package blueshift;


public class Palindrom {

    public static void main(String[] args) {
        String str = "race a car";
        System.out.println(str+" ==> " +isPalindrome(str));
    }

    private static boolean isAlphaNumeric( char c){
        if((c >= 'a' && c <= 'z')||(c >= '0' && c <= '9'))
            return true;
        else
            return  false;
    }


    private static boolean isPalindrome(String str){
        String str1 = str.toLowerCase();
        int len = str.length();
        int i=0, j=len-1;

        while( i < j){
            if(isAlphaNumeric(str1.charAt(i)) && isAlphaNumeric(str1.charAt(j)) && str1.charAt(i) == str1.charAt(j)){
                i++;
                j--;
                continue;
            }
            if(isAlphaNumeric(str1.charAt(i)) && isAlphaNumeric(str1.charAt(j)) && str1.charAt(i) != str1.charAt(j)) return false;
            if(!isAlphaNumeric(str1.charAt(i))) i++;
            if(!isAlphaNumeric(str1.charAt(j))) j--;
        }
        return true;
    }

}
