package org.stream;

import com.google.common.base.Suppliers;

import java.util.function.Supplier;

public class Workers {

    public static boolean eagerWorker(String identifier, boolean isDone){
        System.out.printf("called %s\n", identifier);
        return isDone;
    }

    public static Supplier<Boolean> trueSupplier = ()->{
        System.out.println("true supplier called");
        return true;
    };

   public static Supplier<Boolean> falseSupplier = ()->{
        System.out.println("false supplier called");
        return false;
    };
    public static Supplier<Boolean> lazyWorker(String identifier, boolean isDone){
        return Suppliers.ofInstance(isDone);
    }

    public static boolean fullWork(boolean first, boolean second){
        return  eagerWorker("first", first) && eagerWorker("second", second);
    }

    public static boolean fullWorkLazy(Supplier<Boolean> first, Supplier<Boolean> second){
        System.out.println("called inside fullWorkLazy");
        return  first.get() && second.get();
    }
}
