package com.pune.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

/**
 * Created with IntelliJ IDEA.
 * User: kumar
 * Date: 1/12/14
 * Time: 7:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recursion {
    /**
     * print all possible string of length k that can be formed from a given set
     * of n characters
     * So as per permutation
     *   1 2 3 .. K  and at each place we can put n set character
     *  so it  boils down to n pow k
     *
     */

    public static void PrintAllKLengthString(){
        Set<String> characterSet = new HashSet<>();
        characterSet.add("a");
        characterSet.add("b");
        List<String> characterList = new ArrayList<>();
        characterList.addAll(characterSet);

        for(int i=0; i<characterSet.size();i++){
            for (int j=0;j<characterSet.size();j++){
                for(int k=0;k<characterSet.size();k++){
                       System.out.println(characterList.get(i) +characterList.get(j) + characterList.get(k));
                }
            }
        }

    }

    private static void printRecursion(List<String> list, Integer len){
        if(len.intValue() == 0 )    {
            System.out.println( list.get(0));
        }
        else{
                printRecursion(list, --len);
                System.out.println(list.get(len+1));
        }
    }

    public static void main(String [] args){
//        Recursion.PrintAllKLengthString();
        List<String> characterList = Lists.newArrayList();
        characterList.add("a");
        characterList.add("b");
        Recursion.printRecursion(characterList, 1);

    }
}
