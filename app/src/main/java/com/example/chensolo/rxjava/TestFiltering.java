package com.example.chensolo.rxjava;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/12/16.
 */

public class TestFiltering {
    public static void main(String[] args){
        //testDebounce();
//        testDistinct();
      // testElementAt();
        //testFilter();
        //testFirst();
        //testIgnoreElements();
       // testlast();
        //testSample();
       // testSkip();
        testTake();
        
    }

    /**
     * take 取前面两个数据
     * takeLast 取最后两个数据
     */
    private static void testTake() {
        Observable.just(1,2,3,4).take(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.print("Oncomplete():" + "\n");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError():" + arg0+"\n");
            }

            @Override
            public void onNext(Integer arg0) {
                System.out.print("onNext():" + arg0+"\n");
            }
        });
    }

    // skipLast跳过最后两个数据
    //skip 跳过前面两个数据
    private static void testSkip() {
        Observable.just(1,2,3,4).skipLast(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.print("onComplete():"+ "\n");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError():"+arg0+ "\n");
            }

            @Override
            public void onNext(Integer arg0) {
                System.out.print("onNext():"+arg0+ "\n");
            }
        });
    }

    private static void testSample() {

        //创建被观察者
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> arg0) {

                try {
                    for (int i = 0; i <10 ; i++) {
                        Thread.sleep(1000);
                        arg0.onNext(i);

                    }
                    arg0.onCompleted();
                } catch (Exception e) {
                    arg0.onError(e);
                }
            }
        }).sample(1, TimeUnit.SECONDS).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.print("onComplete():"+ "\n");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError():"+arg0+ "\n");
            }

            @Override
            public void onNext(Integer arg0) {
                System.out.print("onNext():"+arg0+ "\n");
            }
        });
    }

    //取最后一个数据
    private static void testlast() {
        Observable.just(1,2,3,4).last().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.print("onComplete():" + "\n");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError():" +arg0+ "\n");
            }

            @Override
            public void onNext(Integer arg0) {
                System.out.print("onNext():" +arg0+ "\n");
            }
        });
    }

    //直接回调方法
    private static void testIgnoreElements() {
        Observable.just(321).ignoreElements().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                    System.out.print("onComplete():" + "\n");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError():" +arg0+ "\n");
            }

            @Override
            public void onNext(Integer arg0) {
                System.out.print("onNext():" +arg0+ "\n");
            }
        });
    }

    //取第一个数据
    private static void testFirst() {
        Observable.just(1,2,3,4,5).distinct().first().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.print("onComplete():"+ "\n");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError():"+ arg0+"\n");
            }

            @Override
            public void onNext(Integer arg0) {
                System.out.print("onNext():"+ arg0+"\n");
            }
        });
    }

    //过滤不想选中的数据
    private static void testFilter() {
        Observable.just(1,2,3,2,5).distinct().filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer arg0) {
                return  arg0>2;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.print("onComplete():" +"\n");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError():" +arg0+"\n");
            }

            @Override
            public void onNext(Integer arg0) {
                System.out.print("onNex():" +arg0+"\n");
            }
        });
    }

    //取出指定的数据
    private static void testElementAt() {
        Observable.just(1,2,3,4,5).elementAt(3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.print("onComplete"+"\n");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError"+arg0+"\n");
            }

            @Override
            public void onNext(Integer arg0) {
                System.out.print("onNext"+arg0+"\n");
            }
        });
    }

    //去掉重复的数据
    private static void testDistinct() {
        Observable.just(1,2,2,1,3).distinct().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.print("onComplere();"+"\n");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError();"+arg0+"\n");
            }

            @Override
            public void onNext(Integer arg0) {
                System.out.print("onNext();"+arg0+"\n");
            }
        });
    }

    private static void testDebounce() {
        //创建被观察者
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> arg0) {

                try {
                    for (int i = 0; i <10 ; i++) {
                        Thread.sleep(1000);
                        arg0.onNext(i);

                    }
                    arg0.onCompleted();
                } catch (Exception e) {
                    arg0.onError(e);
                }
            }
        }).debounce(1, TimeUnit.SECONDS).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                     System.out.print("onComplete():"+ "\n");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.print("onError():"+arg0+ "\n");
            }

            @Override
            public void onNext(Integer arg0) {
                System.out.print("onNext():"+arg0+ "\n");
            }
        });
    }
}
