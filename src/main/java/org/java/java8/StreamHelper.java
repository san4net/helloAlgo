package org.java.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class StreamHelper {

    /**
     *
     * @param list
     * @param <K>
     * @param <V>
     * @return
     */
    public <K,V> Map<K,V> freqencyMap(List<Map<K,V>> list){
        return (Map<K, V>) list.stream()
                .flatMap(x->x.entrySet().stream())
                .collect(groupingBy(e ->e.getKey(),
                        mapping(
                            v->v.getValue(), Collectors.toList()
                         )
                ) );
    }

    public int[] findingUsersActiveMinutes1(int[][] logs, int k) {
        Map<Integer, Long> fmap = Arrays
                .stream(logs)
                .parallel()
                .collect(groupingBy((log) -> log[0], mapping((log) -> log[1], Collectors.toSet())))
                .entrySet()
                .stream()
                .map(entry -> entry.getValue().size())
                .collect(groupingBy(Function.identity(), counting()));

        return IntStream
                .range(1, k + 1)
                .map(i -> fmap.getOrDefault(i, 0L).intValue())
                .toArray();
    }

    /**
     * logs = [[0,5],[1,2],[0,2],[0,5],[1,3]], k = 5
     * The logs are represented by a 2D integer array logs where each logs[i] = [IDi, timei]
     * indicates that the user with IDi performed an action at the minute timei
     * Multiple users can perform actions simultaneously, and a single user can perform multiple actions in the same minute.
     * <pre>
     *
     * To find:
     * The user active minutes (UAM) for a given user is defined as the number of unique minutes in which the user performed an action on LeetCode.
     * A minute can only be counted once, even if multiple actions occur during it.
     *  Users 0 was active (5,2)
     *  Users 1 was active (2,3)
     *
     *  answer[j] is the number of users whose UAM equals j
     * </pre>
     * @param logs
     * @param k
     * @return
     */
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        return
                Arrays.stream(logs)
                        .collect(()->new HashMap<Integer, Set<Integer>>(),
                                (HashMap<Integer,Set<Integer>> map, int[] e)->{
                                    map.putIfAbsent(e[0], new HashSet<>());
                                    map.get(e[0]).add(e[1]);
                                }, HashMap::putAll
                        )
                        .entrySet()
                        .stream()
                        .collect(
                                Collectors.collectingAndThen(
                                        Collectors.toMap(
                                                e1->e1.getValue().size(),
                                                v1->1,
                                                Integer::sum

                                        ),
                                        m->{
                                            return IntStream.range(0,k)
                                                    .map(i->{
                                                        Integer noOfIds =  m.get(i+1);
                                                        if(noOfIds==null)
                                                            return 0;
                                                        else
                                                            return noOfIds;
                                                    })
                                                    .toArray();
                                        }
                                )
                        );


    }


    public static void main(String[] args) {
       int a[][] = {{1,2},{2,4},{3,2},{4,1}};

    }





}
