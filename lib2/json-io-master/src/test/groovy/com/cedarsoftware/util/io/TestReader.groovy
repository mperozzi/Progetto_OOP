package com.cedarsoftware.util.io

import groovy.transform.CompileStatic
import org.junit.Test

/**
 * @author John DeRegnaucourt (jdereg@gmail.com)
 *         <br>
 *         Copyright (c) Cedar Software LLC
 *         <br><br>
 *         Licensed under the Apache License, Version 2.0 (the "License")
 *         you may not use this file except in compliance with the License.
 *         You may obtain a copy of the License at
 *         <br><br>
 *         http://www.apache.org/licenses/LICENSE-2.0
 *         <br><br>
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *         See the License for the specific language governing permissions and
 *         limitations under the License.
 */
@CompileStatic
class TestReader
{
    @Test
    void testNewInstance()
    {
        Date d = JsonReader.newInstance(Date.class, new JsonObject()) as Date
        Integer a = JsonReader.newInstance(Integer.class, new JsonObject()) as Integer
        String x = JsonReader.newInstance(String.class, new JsonObject())

        assert d instanceof Date
        assert a instanceof Integer
        assert x instanceof String

        assert "" == x
        assert 0 == a
    }
}
