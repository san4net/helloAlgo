package cloud;


import io.swagger.models.auth.In;

import java.util.*;
import java.util.function.Supplier;


public class Problem1 {

    static Supplier<Boolean> firstFunc = ()->{
        System.out.println("first supplier");
        return false;};
    static Supplier<Boolean> secondFunc = ()->{
        System.out.println("second supplier");
        return true;};

    private static void delayExecution(boolean first, boolean second){
        if(first && second){
            System.out.println("classic | both running ");
        }else {
            System.out.println("both not running");
        }
    }
    private static void delayExecutionFn(Supplier<Boolean> first, Supplier<Boolean> second){
        if(first.get() && second.get()){
            System.out.println("both running");
        }else {
            System.out.println("both not running");
        }
    }

    private static boolean second() {
        System.out.println("second ");
        return true;
    }

    private static boolean first() {
        System.out.println("first");
        return true;
    }

    public boolean canCross(int[] stones) {
        // from one you can take k, k-1 and k+1 step
        Map<Integer, Set<Integer>> stepFromStone = new HashMap<>();
        Set<Integer> s = new HashSet<>();
        s.add(1);
        stepFromStone.put(0, s);
        for (int i=1; i<stones.length; i++){
            stepFromStone.put(stones[i], new HashSet<>());
        }

        // now iterate over stones and

        for (int i = 0; i < stones.length; i++) {
             int stoneValue = stones[i];
             Set<Integer> steps = stepFromStone.get(stoneValue);

             for (int step : steps) {
               int reach = stoneValue + step;

               if(reach == stones[stones.length-1]){
                   return  true;
               }
               // check if this reach takes you to some stone
                Set<Integer> stepThatCanbeTaken = stepFromStone.get(reach);
                if (stepThatCanbeTaken != null){
                    stepThatCanbeTaken.add(step);
                    stepThatCanbeTaken.add(step+1);
                    if(step - 1 >0 ){
                        stepThatCanbeTaken.add(step-1);
                    }
                }
             }
        }
        return false;
    }
    public static void main(String args[]) {

        delayExecution(first(), second());
        delayExecutionFn(firstFunc, secondFunc);

//        List<Object> list = getOriginalList();
//        //
//        // 1. Implement the dump(list) method that shows the contents of a list
//        //
//        System.out.println("Original List:" + list);
//        dump(list); // 1
//        System.out.println("reverse of original List:" + list);
//        //
//        // 2. Reverse the list without modifying the contents of the original list
//        //
//        List<Object> reversedList = reverse(list);
//        System.out.println("List reversal..." + reversedList);  // this should be same as original i.e 1, 5
//        System.out.println("-- Original list:" + list);
//
//        dump(list); // 2
//        System.out.println("-- Reversed list:"+ list);
//        dump(reversedList);
//        System.out.println(" --revese of 2nd method" + reversedList);
    }

    private static List<Object> getOriginalList() {
        // Note: This is just sample data.  There is no guarantee how the list is actually being constructed
        return Arrays.asList(1, 5, 6, 4, 3, 2);
    }

    private static void dump(List<Object> list) {
        List<Object> dupList = new ArrayList<>(list);
        int s = dupList.size()-1;

        for(int i = 0; i < dupList.size();  i++) {
           list.set(s-i, dupList.get(i));
        }
        // we are having double element
        // todo: Implement a technique to print the list in a single comma-separated line
    }

    private static List<Object> reverse(List<Object> list) {
        // todo: Return a new list that is the reverse of the original list
        // todo: Note: Do not modify the original list
        // todo: Note: Do not use Collections.reverse() or any similar built-in method.
        // todo: Note: We want to see an implementation of how reverse would work
        return reverseWithoutModifyingOriginal(list);
    }

    private static List<Object> reverseWithoutModifyingOriginal(List<Object> list){
        List<Object> dupList = new ArrayList<>(list.size());
        for(int i= list.size()-1, j=0; i >=0;  i--, j++) {
            dupList.add(j, list.get(i));
        }
        return dupList;
    }
}
