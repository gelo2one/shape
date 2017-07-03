/**
 *
 * Copyright (c) 2017 Dotweblabs Web Technologies and others. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.dotweblabs.shape.client;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.junit.client.GWTTestCase;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * Unit tests of {@link HttpRequestWithBodyTest}
 * @author Kerby Martino
 * @since 0-SNAPSHOT
 * @version 0-SNAPSHOT
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HttpRequestWithBodyTest extends GWTTestCase {

    static final Logger logger = Logger.getLogger(HttpRequestWithBodyTest.class.getName());

    @Override
    public String getModuleName() {
        return "com.dotweblabs.shape.Shape";
    }
    private RequestBuilder.Method method;
    public Multimap<String, String> headermap;
    public Map<String, String> querymap1 = null;

    public void testHeader()
    {
        HttpRequestWithBody hrwb = new HttpRequestWithBody("https://httpbin.org/get", method);
        hrwb.header("test", "test");
        assertEquals("https://httpbin.org/get", hrwb.getUrl());
        headermap = ArrayListMultimap.create();
        headermap.put("test", "test");
        assertEquals(headermap, hrwb.headerMap);
    }

    public void testBody()
    {
        Object body= "test";
        HttpRequestWithBody hrwb = new HttpRequestWithBody("https://httpbin.org/get", method);
        hrwb.body(body);
        assertEquals(body, hrwb.getBody());
    }

    public void testQueryString()
    {
        HttpRequestWithBody hrwb = new HttpRequestWithBody("https://httpbin.org/get", method);
        hrwb.queryString("test","test");
        Map<String, String> QueryMap;
        QueryMap = new LinkedHashMap<String, String>();
        QueryMap.put("test", "test");
        assertEquals(QueryMap, hrwb.getQuery());
    }

    public void testField()
    {
        HttpRequestWithBody hrwb = new HttpRequestWithBody("https://httpbin.org/get", method);
        hrwb.field("test","test");
        Map<String, String> field;
        field = new LinkedHashMap<String, String>();
        field.put("test", "test");
        assertEquals(field, hrwb.fields);
    }

    public void testBasicAuth()
    {
        HttpRequestWithBody hrwb = new HttpRequestWithBody("https://httpbin.org/get", method);
        hrwb.basicAuth("test", "test");
        assertEquals("Basic " + Base64.btoa("test" + ":" + "test"), hrwb.getAuthorization());
    }

    public void testAsJson()
    {
        HttpRequestWithBody hrwb = new HttpRequestWithBody("https://httpbin.org/get", method);
        if (querymap1 != null && !querymap1.isEmpty())
        {
            hrwb.url += '?';
            hrwb.url += hrwb.queries(hrwb.getQuery());
            String url1 = hrwb.url;
            url1 += '?';
            url1 += hrwb.queries(hrwb.queryMap);
            assertEquals(url1, hrwb.url);
        }
        headermap = ArrayListMultimap.create();
        if(!hrwb.hasAccept) {
            hrwb.headerMap.put("accept", "application/json");
            headermap.put("accept", "application/json");
            assertEquals(headermap, hrwb.headerMap);
        }
       if(!hrwb.hasContentType) {
            hrwb.headerMap.put("Content-Type", "application/json");
            headermap.put("Content-Type", "application/json");
            assertEquals(headermap, hrwb.headerMap);
        }
    }
    public void testSetUrl()
    {
        HttpRequestWithBody hrwb = new HttpRequestWithBody("https://httpbin.org/get", method);
        hrwb.setUrl("https://httpbin.org/get");
        assertEquals("https://httpbin.org/get", hrwb.getUrl());
    }

    public void testGetUrl()
    {
        HttpRequestWithBody hrwb = new HttpRequestWithBody("https://httpbin.org/get", method);
        assertNotNull(hrwb);
        assertEquals("https://httpbin.org/get", hrwb.getUrl());
    }

    public void testQueries() {
        HttpRequestWithBody hrwb = new HttpRequestWithBody("https://httpbin.org/get", method);
        if (hrwb.getQuery() != null) {
            String query = hrwb.runQuery(hrwb.getQuery());

            if (hrwb.getQuery().toString() == query) {
                finishTest();
            }
        }
    }


    public static void log(String s){
        System.out.println(s);
    }

}
