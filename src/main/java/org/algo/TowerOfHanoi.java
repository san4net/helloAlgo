package org.algo;

public class TowerOfHanoi {

    public static void computeHanoiSteps(int n, int source, int destination, int buffer){
        if(n>0){
            computeHanoiSteps(n-1,source, buffer, destination);
            System.out.println("move "+n+ " to "+destination);
            computeHanoiSteps(n-1, buffer, destination, source);
        }

    }

    public static void main(String[] args) {
        computeHanoiSteps(3, 1, 3, 2);
    }
}
