package blueshift;


public class AllCases {


    public static void main(String[] args) {
        String str = "abcde";
        String d  = "-23.00";
        System.out.println(Double.parseDouble(d));

    }


    private static void printAll(String str, String prefix){
        if(str.isEmpty() || str == null){
            System.out.println(prefix);
        }else{
            String c = str.charAt(0)+"";
            printAll(str.substring(1), prefix+c.toLowerCase());
            printAll(str.substring(1), prefix+c.toUpperCase());
        }
    }

}
