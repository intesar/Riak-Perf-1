/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.riakdemo;

/**
 *
 * @author intesar
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.riak.core.RiakTemplate;
import org.springframework.stereotype.Component;

//@Component
public class Example {

    //@Autowired
    RiakTemplate riakTemplate;

    public RiakTemplate getRiakTemplate() {
        return riakTemplate;
    }

    public void setRiakTemplate(RiakTemplate riakTemplate) {
        this.riakTemplate = riakTemplate;
    }

    public void setData(String bucket, String key, String data) throws Exception {
        riakTemplate.set(bucket, key, data); // Set as Content-Type: text/plain
        riakTemplate.setAsBytes(bucket, key, data.getBytes()); // Set as Content-Type: application/octet-stream
    }

    public void setData(String bucket, String key, MyPojo data) throws Exception {
        riakTemplate.set(bucket, key, data); // Converted to JSON automatically, Content-Type: application/json
    }

    public void getData(String bucket, String key) throws Exception {
        // What you get depends on Content-Type. 
        // application/json=Map, text/plain=String, etc...
        //Object o = riakTemplate.get(bucket, key);
        //System.out.println(o);

        // If your entry is Content-Type: application/json...
        // It will automatically be converted when retrieved.
        MyPojo s = riakTemplate.getAsType(bucket, key, MyPojo.class);
        //System.out.println(o);

        // If your entry is Content-Type: application/octet-stream,
        // you can access the raw bytes.
        //byte[] b = riakTemplate.getAsBytes(bucket, key);
        //System.out.println(b.length);

    }
}
