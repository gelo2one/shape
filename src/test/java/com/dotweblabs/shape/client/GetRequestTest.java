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
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.apache.tools.ant.taskdefs.Get;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Unit tests of {@link GetRequestTest}
 * @author Kerby Martino
 * @since 0-SNAPSHOT
 * @version 0-SNAPSHOT
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetRequestTest extends GWTTestCase {

    static final Logger logger = Logger.getLogger(GetRequestTest.class.getName());

    @Override
    public String getModuleName() {
        return "com.dotweblabs.shape.Shape";
    }

    public Multimap<String, String> headermap1 = null;
    public Map<String, String> querymap1 = null;

    public void testBasicAuth()
    {
        GetRequest getRequest = new GetRequest("https://httpbin.org/get");
        getRequest.basicAuth("test", "test");
        assertNotNull(getRequest);
        assertEquals("Basic " + Base64.btoa("test" + ":" + "test"), getRequest.getAuthorization());
    }

    public void testSetUrl()
    {
        GetRequest getRequest = new GetRequest("https://httpbin.org/get");
        getRequest.setUrl("https://httpbin.org/get");
        assertEquals("https://httpbin.org/get", getRequest.getUrl());
    }

    public void testGetUrl()
    {
        GetRequest getRequest = new GetRequest("https://httpbin.org/get");
        assertNotNull(getRequest);
        assertEquals("https://httpbin.org/get", getRequest.getUrl());
    }

    public void testHeader()
    {
        GetRequest getRequest =  new GetRequest("https://httpbin.org/get");
        getRequest.header("test", "test");
        headermap1 = ArrayListMultimap.create();
        headermap1.put("test", "test");
        assertEquals(headermap1, getRequest.getHeaderMap());
    }

    public void testAsJson()
    {
        GetRequest getRequest =  new GetRequest("https://httpbin.org/get");
        if (querymap1 != null && !querymap1.isEmpty())
        {
            getRequest.url += '?';
            getRequest.url += getRequest.queries(getRequest.queryMap);
            String url1 = getRequest.getUrl();
            url1 += '?';
            url1 += getRequest.queries(getRequest.queryMap);
            assertEquals(url1, getRequest.url);
        }
        headermap1 = ArrayListMultimap.create();
        headermap1.put("Content-Type", "application/json");
        headermap1.put("accept", "application/json");
        getRequest.headerMap.put("Content-Type", "application/json");
        getRequest.headerMap.put("accept", "application/json");
        assertEquals(headermap1, getRequest.headerMap);
    }

    public void testQueries() {
        GetRequest getRequest = new GetRequest("https://httpbin.org/get");
        if (getRequest.queryMap != null) {
            String query = getRequest.runQuery(getRequest.queryMap);

            if (getRequest.queryMap.toString() == query) {
                finishTest();
            }
        }
    }

    public static void log(String s){
        System.out.println(s);
    }

}
