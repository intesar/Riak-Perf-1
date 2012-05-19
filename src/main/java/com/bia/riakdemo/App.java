package com.bia.riakdemo;

import java.util.Date;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 * mysql inserts : 50, time : 1662 inserts : 100, time : 1643 inserts : 200,
 * time : 1926 inserts : 500, time : 2778 inserts : 1000, time : 4611
 *
 * gets : 50, time : 1329 gets : 100, time : 1349 gets : 200, time : 1592 gets :
 * 500, time : 1702 gets : 1000, time : 2197
 *
 * riak inserts : 50, time : 1032 inserts : 100, time : 1389 inserts : 200, time
 * : 2555 inserts : 500, time : 6408 inserts : 1000, time : 10777
 *
 * gets : 50, time : 654 gets : 100, time : 1308 gets : 200, time : 2157 gets :
 * 500, time : 5062 gets : 1000, time : 8722
 *
 */
public class App {

    public static void main(String[] args) throws Exception {
        //System.out.println("Hello World!");
        // open/read the application context file
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // instantiate our spring dao object from the application context
        Example example = (Example) ctx.getBean(Example.class);

        String bucket = "riakdemo";
        //String key = new Date().toString();
        
        //runGet (example, bucket, 10000);
        
        runSet(example, bucket, 50);
        runSet(example, bucket, 100);
        runSet(example, bucket, 200);
        runSet(example, bucket, 500);
        runSet(example, bucket, 1000);
        //runSet (example, bucket, 10000);

        runGet(example, bucket, 50);
        runGet(example, bucket, 100);
        runGet(example, bucket, 200);
        runGet(example, bucket, 500);
        runGet(example, bucket, 1000);

    }

    private static void runGet(Example example, String bucket, int max) throws Exception {
        MyPojo pojo = new MyPojo();
        pojo.setEmail("mdshannan@gmail.com");
        pojo.setName("Intesar S Mohammed");

        long st = new Date().getTime();
        Integer i = 0;
        for (; i < max; i++) {
            //example.setData(bucket, i.toString(), pojo);
            example.getData(bucket, i.toString());
        }
        long et = new Date().getTime();
        System.out.println(" gets : " + i + ", time : " + (et - st));

        //example.getData(bucket, key);
    }

    private static void runSet(Example example, String bucket, int max) throws Exception {
        MyPojo pojo = new MyPojo();
        pojo.setEmail("mdshannan@gmail.com");
        pojo.setName("Intesar S Mohammed");

        long st = new Date().getTime();
        Integer i = 0;
        for (; i < max; i++) {
            example.setData(bucket, i.toString(), pojo);
            //example.getData(bucket, i.toString());
        }
        long et = new Date().getTime();
        System.out.println(" inserts : " + i + ", time : " + (et - st));


    }
}
