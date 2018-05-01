package com.sahajamit.rx;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.ResourceObserver;

import java.util.Arrays;
import java.util.List;

/**
 * Created by amitrawat on 1/5/18.
 */
public class RxExample {
    public static void main(String[] args) {
//        conciseObserver();
//        verboseObserver();
//        iterableObserver();
//        mapObserver();
//        filterObserver();
//        distinctObserver();
//        customDistinctObserver();
//        takeObserver();
//        takeObserver();
//        countObserver();
//        toListObserver();
//        reduceObserver();
//        scanObserver();
        flatMapObserver();
    }

    private static void conciseObserver(){
        Observable<Integer> source = Observable.just(1,2,3,4,5);
        source.subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done!")
        );
    }

    private static void verboseObserver(){
        Observable<Integer> source = Observable.just(1,2,3,4,5);
        Observer<Integer> subscriber = new ResourceObserver<Integer>() {
            @Override
            public void onComplete() {
                System.out.println("Done!");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        };
        source.subscribe(subscriber);
    }

    public static void iterableObserver(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Observable<Integer> source = Observable.fromIterable(list);
        source.subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done!")
        );
    }

    public static void mapObserver(){
//        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
//                .map(String::length)
//                .subscribe(System.out::println);

        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon") // calls onNext() on map()
                .map(s -> s.length()) // calls onNext() on Observer
                .subscribe(i -> System.out.println(i));
    }

    public static void filterObserver(){
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .filter(s -> s.length() >= 5)
                .subscribe(System.out::println);
    }


    public static void distinctObserver(){
        Observable.just("Alpha","Beta","Gamma","Alpha", "Epsilon")
                .distinct()
                .subscribe(System.out::println);
    }

    public static void customDistinctObserver(){
        Observable.just("Alpha","Beta","Gamma","Alpha", "Epsilon")
                .distinct(String::length)
                .subscribe(System.out::println);
    }

    public static void takeObserver(){
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .take(3)
                .subscribe(System.out::println);
    }

    public static void takeWhileObserver(){
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .takeUntil((String s) -> s.matches("D.*"))
                .subscribe(System.out::println);
    }

    public static void countObserver(){
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .count()
                .subscribe(System.out::println);
    }


    public static void toListObserver(){
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .toList()
                .subscribe(System.out::println);
    }

    public static void reduceObserver(){
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .map(String::length)
                .reduce(0,(current,next) -> current + next)
                .subscribe(System.out::println);
    }

    public static void scanObserver(){
        Observable.just("Alpha","Beta","Gamma","Delta", "Epsilon")
                .map(String::length)
                .scan(0,(current,next) -> current + next)
                .subscribe(System.out::println);
    }

    public static void flatMapObserver(){
        Observable.just("123/52/6345","23421/534","758/2341/74932")
                .flatMap(s -> Observable.fromArray(s.split("/")))
                .subscribe(System.out::println);
    }
}
