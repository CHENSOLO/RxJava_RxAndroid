package com.example.chensolo.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * Created by Administrator on 2017/12/16.
 */

public class TestCombining {
    public static void main(String[] args){
        //testZip();
        //testMerg();
        //testStartWith();
        //testCombineLatest();
    }

    /**
     * 用于将两个Observable 最近发射的数据已经Func2函数的规则组合
     */
    private static void testCombineLatest() {
        Observable <Integer> observable7 = Observable.just(1,2,3);
        Observable <Integer> observable8 = Observable.just(4,5,6);

        observable7.combineLatest(observable7, observable8, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
              System.out.println("integer:" + integer +"integer2"+integer2);
                return integer+integer2;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
       System.out.println("Error"+e.getMessage());
            }

            @Override
            public void onNext(Integer item) {
                      System.out.println("Next" +item);
            }
        });
    }

    /**
     * 用于在Observable发射的数据前插入数据。使用Startwith（iterable<T>）我们可以在源Observable发射数据前插入Iterable
     */
    private static void testStartWith() {
        Observable <Integer> observable5 = Observable.just(1,3,5);
        Observable <Integer> observable6 = Observable.just(2,4,6);

        observable5.startWith(observable6).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
            System.out.println("Error:" + e.getMessage());
            }

            @Override
            public void onNext(Integer item) {
              System.out.println("NExt:"+item);
            }
        });
    }

    /**
     * 将两个Observable 发射的事件序列组合并成一个事件序列，就像一个Observable发射的一样
     * 可以简单的理解将两个Observable合并成一个Observable
     */
    private static void testMerg() {
        Observable <Integer> observable3 = Observable.just(1,3,5);
        Observable <Integer> observable4 = Observable.just(2,4,6);

        Observable.merge(observable3,observable4).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {
               System.err.println("Next:" + e.getMessage());
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("Next:" + item);
            }
        });
    }

    /**
     * 用来合并两个Observable发射的数据项，根据Func2函数生成一个新的值并且发射出去
     * 当其中一个Observable发送数据结束或者出现异常后
     * 另一个Observable也会停止发送数据
     */
    private static void testZip() {
        Observable <Integer> observable1= Observable.just(10,20,30);
        Observable <Integer> observable2 = Observable.just(20,30,40,50);
        Observable.zip(observable1, observable2, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer+integer2;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error():" +e.getMessage());

            }

            @Override
            public void onNext(Integer value) {
                  System.out.println("Next():"+value);
            }
        });

    }
}
