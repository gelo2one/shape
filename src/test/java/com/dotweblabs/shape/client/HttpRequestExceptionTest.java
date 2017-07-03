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
import com.google.gwt.junit.client.GWTTestCase;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * Unit tests of {@link HttpRequestExceptionTest}
 * @author Kerby Martino
 * @since 0-SNAPSHOT
 * @version 0-SNAPSHOT
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HttpRequestExceptionTest extends GWTTestCase {

    static final Logger logger = Logger.getLogger(HttpRequestExceptionTest.class.getName());

    @Override
    public String getModuleName() {
        return "com.dotweblabs.shape.Shape";
    }

    public void testHttpRequestException()
    {
        HttpRequestException hre = new HttpRequestException("test", 123);
        assertEquals(123, hre.getCode());
    }


    public static void log(String s){
        System.out.println(s);
    }

}
