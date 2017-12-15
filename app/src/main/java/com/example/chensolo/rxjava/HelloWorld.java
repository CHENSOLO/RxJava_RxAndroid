package com.example.chensolo.rxjava;


import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/12/15.
 */

public class HelloWorld {

    public static void main(String[]args){

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
             System.out.print("onCompleted() ");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError() ");
            }

            @Override
            public void onNext(String arg0) {
                System.out.print("onNext() :"+ arg0);
            }
        };
        /**
         * 订阅事件
         */
//        Observable myObservable = null;
             myObservable.subscribe(subscriber);
    }
}
