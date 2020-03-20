package com.rehoshi.stream;

import org.apache.poi.ss.formula.functions.T;

import java.util.*;
import java.util.stream.Stream;

public class HStream {
    public static <T>Stream<T> emptyStream(){
        return Stream.empty() ;
    }

    public static  <T>Stream<T> $(T... objs){
        return $(Arrays.asList(objs)) ;
    }

    public static <T> Stream<T> $(Collection<T> collection){
        if(nullOrEmpty(collection)){
            return emptyStream();
        }
        return collection.stream().filter(Objects::nonNull) ;
    }

    public static boolean nullOrEmpty(Collection collection){
        return collection == null || collection.isEmpty() ;
    }

    public static boolean nullOrEmpty(Object[] array){
        return array == null || array.length == 0 ;
    }

    public static boolean nullOrEmpty(Map map){
        return map == null || map.isEmpty() ;
    }

    public static int count(Collection collection){
        if(nullOrEmpty(collection)){
            return 0 ;
        }
        return collection.size() ;
    }

    public static int count(Object[] array){
        if(nullOrEmpty(array)){
            return 0;
        }
        return array.length ;
    }

    public static int count(Map map){
        if(nullOrEmpty(map)){
            return 0 ;
        }
        return map.size() ;
    }

}
