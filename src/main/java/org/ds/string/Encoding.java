package org.ds.string;

/**
 * <pre>
 * Run Length encoding
 *  input 'aaaabbczzx'
 *  encoding 4a2b1c2z1x
 *  decoding aaaabbczzx
 *  </pre>
 */
public class Encoding {


    static String encoding(String str){
     int count=1, index=0;
     StringBuilder sb = new StringBuilder();
     while(index < str.length()-1){
         if(str.charAt(index) == str.charAt(index+1)){
            count++;
        }else{
            sb.append(count).append(str.charAt(index));
            count=1;
        }
         index++;
     }
     sb.append(count).append(str.charAt(index));
     return sb.toString();
    }

    static String decoding(String str){
        int count =0;
        StringBuilder sb = new StringBuilder();

        for(char c : str.toCharArray()){
            if(isDigit(c)){
              count = count*10 + c - '0';
            }else{
                // append count time the character c
               while(count-- > 0) {
                   sb.append(c);
               }
               count=0;
            }

        }

        return sb.toString();
    }

    private static boolean isDigit(char c) {
        return c >='0' && c <= '9';
    }

    public static void main(String[] args) {
        System.out.println(encoding("aaabbbcccz"));
        System.out.println(decoding("3a3b3c1z"));
    }
}
