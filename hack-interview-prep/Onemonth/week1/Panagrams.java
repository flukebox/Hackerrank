package Onemonth.week1;

import java.util.HashSet;

public class Panagrams {

    public static String pangrams(String s) {
        // Write your code here
        String str = s.toLowerCase();
        HashSet<Character> chars = new HashSet<>();
        for(char c: str.toCharArray()){
            // if not space
            if(c != ' ') chars.add(c);
        }
        if(chars.size() == 26) return  "pangram";
        else return "not pangram";
    }
}
