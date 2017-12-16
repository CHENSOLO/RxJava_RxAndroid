package com.example.chensolo.rxjava;


import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

/**
 * Created by Administrator on 2017/12/15.
 */

public class HelloWorld {
    private static final String TAG = "HelloWorld";
    public static void main(String[]args){

     
//        testStranform();
//        testStranform_flatMap();
//        testStranform_groupBy();
//        buffer();
        Scan();
        /**
         * 第一次创建被观察者
         */
      Observable myObservable = Observable.create(new Observable.OnSubscribe<String>() {
          public void call(Subscriber<? super String> subscriber) {
              subscriber.onNext("Hello World！！");
              subscriber.onCompleted();
          }
      });

        /**
         * 第一次创建观察者
         */
        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
             System.out.println("onCompleted() ");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.println("onError() ");
            }

            @Override
            public void onNext(String arg0) {
                System.out.println("onNext() :"+ arg0);
            }
        };
        /**
         * 订阅事件
         */
//        Observable myObservable = null;
             myObservable.subscribe(subscriber);
    }
//Scan 依次类推的加
    private static void Scan() {
        Observable.range(1,5).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer sum, Integer arg1) {
                return sum+arg1;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable arg0) {

            }

            @Override
            public void onNext(Integer arg0) {
      System.out.println("onNext:" + arg0);
            }
        });

    }


    // buffer
    private static void buffer() {
        Observable.range(1,5).buffer(2).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable arg0) {

            }

            @Override
            public void onNext(List<Integer> arg0) {
             System.out.println("onNext:" + arg0);
            }
        });

    }

    //groupBy 分组
    private static void testStranform_groupBy() {
        Observable.just(1,2,3,4).groupBy(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer arg0) {
                return arg0%2 ;
            }
        }).subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable agr0) {

            }

            @Override
            public void onNext(final GroupedObservable<Integer, Integer> arg0) {
                     arg0.subscribe(new Subscriber<Integer>() {
                         @Override
                         public void onCompleted() {

                         }

                         @Override
                         public void onError(Throwable arg0) {

                         }

                         @Override
                         public void onNext(Integer data) {
          System.out.println("group:"+ arg0.getKey()+" data:"+data);
                         }
                     });
            }
        });


    }




    //flatMap 例子 1对多
    private static void testStranform_flatMap() {
        Observable.just(1,2,3,4,5).flatMap(new Func1<Integer, Observable<? extends String>>() {
            @Override
            public Observable<? extends String> call(Integer arg0) {
                return Observable.just(arg0 + "");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:");
            }

            @Override
            public void onError(Throwable arg0) {

            }

            @Override
            public void onNext(String arg0) {
           System.out.println("onNext:" + arg0);
            }
        });
    }

    private static void testStranform() {

        ///Map操作字符 1对1的
        Observable.just(123).map(new Func1<Integer, String>() {


            @Override
            public String call(Integer arg0) {
                return arg0 + "";
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                     System.out.println("onCompleted:");
            }

            @Override
            public void onError(Throwable arg0) {

            }

            @Override
            public void onNext(String arg0) {
                System.out.println("onNext:" + arg0);
                Log.i(TAG, "onNext: arg0");
            }
        });
    }


    
}
