package org.ds.stack;


import com.networknt.schema.CollectorContext;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/contest/weekly-contest-296/problems/design-a-text-editor/
 */
public class TextEditor {
    LinkedList<Character> left = new LinkedList<>();
    LinkedList<Character> right = new LinkedList<>();
    public TextEditor() {

    }

    public void addText(String text) {
        for (char c : text.toCharArray()) {
            left.addFirst(c);
        }
    }

    public int deleteText(int k) {
      int r =k;
      if ( left.size()<=k){
           r= left.size();
         left=new LinkedList<>();
      }else {
          while (k>0){
              left.removeFirst();
              k--;
          }
      }
      return r;
    }

    public String cursorLeft(int k) {
        // left
        while (k>0 && !left.isEmpty()){
            right.addFirst(left.removeFirst());
            k--;
        }

       // min (10,len)
        int i=10;
        List<Character> characters = new ArrayList<>();

        while (!left.isEmpty() && i>0){
            characters.add(left.removeFirst());
            i--;
        }

         StringBuilder sb = new StringBuilder();
         for (int j=characters.size()-1; j>=0;j--){
             sb.append(characters.get(j));
             left.addFirst(characters.get(j));
         }

         return sb.toString();
    }

    public String cursorRight(int k) {

      while (!right.isEmpty() && k>0){
          left.addFirst(right.removeFirst());
          k--;
       }

        // min (10,len)
        int i=10;
        List<Character> characters = new ArrayList<>();

        while (!left.isEmpty() && i>0){
            characters.add(left.removeFirst());
            i--;
        }

        StringBuilder sb = new StringBuilder();
        for (int j=characters.size()-1; j>=0;j--){
            sb.append(characters.get(j));
            left.addFirst(characters.get(j));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TextEditor textEditor =  new TextEditor();
        textEditor.addText("leetcode");
        System.out.println( textEditor.deleteText(4));
        textEditor.addText("practice");
        System.out.println(textEditor.cursorRight(3));
        System.out.println(textEditor.cursorLeft(8));
        System.out.println(textEditor.deleteText(10));
        System.out.println(textEditor.cursorLeft(2) );
        System.out.println(textEditor.cursorRight(6));

        System.out.println(strongPasswordCheckerII("IloveLe3tcode!"));
        int i = (int)Math.ceil(3/2f);
        System.out.println( i);

                //[5,1,3], potions = [1,2,3,4,5], success = 7
//        successfulPairs(new int[]{5,1,3},new int[]{1,2,3,4,5}, 7);
//        successfulPairs(new int[]{3,1,2},new int[]{8,5,8}, 16);
        successfulPairs(new int[]{13,33,12,18,38},new int[]{34,40,20,33,20,38,24,36,35,38}, 538);
        //
        //[13,33,12,18,38]
        //[34,40,20,33,20,38,24,36,35,33]
        //538
        // ans: [0,10,0,7,10]
        System.out.println( distributeCookies(new int[]{8,15,10,20,8},2));
    }

    public static  int distributeCookies(int[] cookies, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        int max = 0;

        for (int a: cookies){
            if(pq.size()<k){
                 pq.add(a);
            }else {
               int t =  pq.poll();
               pq.add(t+a);
            }
        }

        while (!pq.isEmpty()){
            System.out.println(pq.poll());
        }
        return -1;
    }

    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        // sort and do binary search  where index where product >=
        Arrays.sort(potions);
        System.out.println(Arrays.stream(potions).boxed().collect(Collectors.toList()));
        TreeMap<Long, Integer> treeMap = new TreeMap<>();
        treeMap.put(Long.MAX_VALUE, potions.length);
        int[] r =  new int[spells.length];
        for (int i = potions.length-1; i >=0; i--) {
            treeMap.put((long)potions[i], i);
        }

        for (int i = 0; i < spells.length; i++) {
            long  pivot = (long) Math.ceil((success*1f)/spells[i]);
            Map.Entry<Long, Integer> idx =   treeMap.ceilingEntry(pivot);
            r[i]= spells.length-idx.getValue();
        }

        List<Integer> rr =  Arrays.stream(r).boxed().collect(Collectors.toList());
        System.out.println(rr);
        return r;
    }

    private static int bsearch(int[] ar, int start, int end, int pivot){

        while (start<=end){
            int m = start +(end-start)/2;
            if(ar[m]==pivot){
                int n = m-1;
                while (n>-1 && ar[n]==pivot){
                    n--;
                }
                return n+1;
            }
            if (ar[m]>pivot){
                end = m-1;
            }else {
                start=m+1;
            }
        }

        return -1;

    }


   static String special ="!@#$%^&*()-+";
    public static  boolean strongPasswordCheckerII(String password) {
        if(password == null || password.length()<8){
            return false;
        }
        char[] c = password.toCharArray();
        boolean lowercase=false, uppecase=false, onedigit=false, onespecial=false, twoconsecutive=false;

        Character prev  = null;

        for (char e:c){

            if (prev != null && prev == e){
                 return false;
            }

            if(e >='a' && e<='z'){
                lowercase=true;
            } else if (e>='A' && e<= 'Z') {
                uppecase=true;
            } else if (e-'0'>=0 && e-'0'<=9) {
                onedigit=true;
            } else if (special.indexOf(e) != -1) {
                onespecial=true;
            }
            prev = e;
        }
        System.out.println(lowercase+","+uppecase+","+onedigit+","+onespecial+","+twoconsecutive);
        return lowercase && uppecase && onedigit && onespecial && !twoconsecutive;

    }
}