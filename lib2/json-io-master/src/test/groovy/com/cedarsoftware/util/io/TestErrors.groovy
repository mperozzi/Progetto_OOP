package com.cedarsoftware.util.io

import groovy.transform.CompileStatic
import org.junit.Test

import static org.junit.Assert.assertTrue
import static org.junit.Assert.fail

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
class TestErrors
{
    @Test
    void testBadJson()
    {
        Object o = null;

        try
        {
            o = TestUtil.readJsonObject('["bad JSON input"')
            fail()
        }
        catch(Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("expected ',' or ']' inside array"))
        }
        assertTrue(o == null)
    }

    @Test
    void testParseMissingQuote()
    {
        try
        {
            String json = '''{
  "array": [
    1,
    2,
    3
  ],
  "boolean": true,
  "null": null,
  "number": 123,
  "object": {
    "a": "b",
    "c": "d",
    "e": "f"
  },
  "string: "Hello World"
}'''
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("expected ':' between string field and value"))
        }
    }

    @Test
    void testParseInvalid1stChar()
    {
        String json = '''
"array": [
1,
2,
3
],
"boolean": true,
"null": null,
"number": 123,
"object": {
"a": "b",
"c": "d",
"e": "f"
},
"string:" "Hello World"
}'''
        def x = JsonReader.jsonToJava(json)
        assert "array" == x
    }

    @Test
    void testParseMissingLastBrace()
    {
        try
        {
            String json = """{
  "array": [
    1,1,1,1,1,1,1,1,1,1,
    2,0,0,0,0,0,0,0,0,0,0,0,0,
    3,4,5,6,7,8,9,10
  ],
  "boolean": true,
  "null": null,
  "number": 123,
  "object": {
    "a": "b",
    "c": "d",
    "e": "f"
  },
  "string": "Hello World"
"""
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("eof reached before closing '}'"))
        }
    }

    @Test
    void testParseMissingLastBracket()
    {
        String json = '[true, "bunch of ints", 1,2, 3 , 4, 5 , 6,7,8,9,10]'
        JsonReader.jsonToJava(json)

        try
        {
            json = '[true, "bunch of ints", 1,2, 3 , 4, 5 , 6,7,8,9,10'
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("expected ',' or ']' inside array"))
        }
    }

    @Test
    void testParseBadValueInArray()
    {
        String json

        try
        {
            json = '[true, "bunch of ints", 1,2, 3 , 4, 5 , 6,7,8,9,\'a\']'
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("unknown json value type"))
        }
    }

    @Test
    void testParseObjectWithoutClosingBrace()
    {
        try
        {
            String json = '{"key": true{'
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("object not ended with '}'"))
        }
    }

    @Test
    void testParseBadHex()
    {
        try
        {
            String json = '"\\u5h1t"'
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("expected hexadecimal digits"))
        }
    }

    @Test
    void testParseBadEscapeChar()
    {
        try
        {
            String json = '"What if I try to escape incorrectly \\L1CK"'
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("invalid character escape sequence"))
        }
    }

    @Test
    void testParseUnfinishedString()
    {
        try
        {
            String json = '"This is an unfinished string...'
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("eof reached"))
        }
    }

    @Test
    void testParseEOFInToken()
    {
        try
        {
            String json = "falsz"
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("expected token: false"))
        }
    }

    @Test
    void testParseEOFReadingToken()
    {
        try
        {
            String json = "tru"
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("eof reached while reading token: true"))
        }
    }

    @Test
    void testParseEOFinArray()
    {
        try
        {
            String json = "[true, false,"
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assertTrue(e.message.toLowerCase().contains("eof reached prematurely"))
        }
    }

    @Test
    void testMalformedJson()
    {
        String json;

        try
        {
            json = '{"field"0}'  // colon expected between fields
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("expected ':' between string field and value")
        }

        try
        {
            json = "{field:0}"  // not quoted field name
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("expected quote")
        }

        try
        {
            json = '{"field":0'  // object not terminated correctly (ending in number)
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("eof reached before closing '}'")
        }

        try
        {
            json = '{"field":true'  // object not terminated correctly (ending in token)
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("eof reached before closing '}'")
        }

        try
        {
            json = '{"field":"test"'  // object not terminated correctly (ending in string)
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("eof reached before closing '}'")
        }

        try
        {
            json = '{"field":{}'  // object not terminated correctly (ending in another object)
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("eof reached before closing '}'")
        }

        try
        {
            json = '{"field":[]'  // object not terminated correctly (ending in an array)
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("eof reached before closing '}'")
        }

        try
        {
            json = '{"field":3.14'  // object not terminated correctly (ending in double precision number)
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("eof reached before closing '}'")
        }

        try
        {
            json = '[1,2,3'
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("expected ',' or ']' inside array")
        }

        try
        {
            json = "[false,true,false"
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("expected ',' or ']' inside array")
        }

        try
        {
            json = '["unclosed string]'
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.toLowerCase().contains("eof reached while reading json string")
        }
    }

    @Test
    void testBadType()
    {
        String json = '{"@type":"non.existent.class.Non"}'
        Map map = (Map) TestUtil.readJsonObject(json)
        assert map.size() == 0

        // Bad class inside a Collection
        json = '{"@type":"java.util.ArrayList","@items":[null, true, {"@type":"bogus.class.Name", "fingers":5}]}'
        List list = (List) TestUtil.readJsonObject(json)
        assert list.size() == 3
        assert list[0] == null
        assert list[1]
        assert list[2]['fingers'] == 5
    }

    private static class Animal
    {

    }

    private static class Dog extends Animal
    {

    }

    private static class AnimalHolder
    {
        Animal dog;
    }

    @Test
    void testBadTypeObject()
    {
        // Unclear and misleading error if the type is not found
        AnimalHolder h = new AnimalHolder()
        h.dog = new Dog()

        String json = TestUtil.getJsonString(h)
        json = json.replace('$Dog', '$BadDog');
       try
        {
            TestUtil.readJsonObject(json)
        }
        catch (Exception e)
        {
            // Should indicate the type that was not found
            assert e.toString().contains("BadDog");
        }
    }

    @Test
    void testBadHexNumber()
    {
        StringBuilder str = new StringBuilder()
        str.append("[\"\\")
        str.append("u000r\"]")
        try
        {
            TestUtil.readJsonObject(str.toString())
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains('["\\u000r')
        }
    }

    @Test
    void testBadValue()
    {
        try
        {
            String json = '{"field":19;}'
            TestUtil.readJsonObject(json)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains('{"field":19;')
        }

        try
        {
            String json = '{"field":'
            TestUtil.readJsonObject(json)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains('{"field":')
        }

        try
        {
            String json = '{"field":joe'
            TestUtil.readJsonObject(json)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains('{"field":j')
        }

        try
        {
            String json = '{"field":trux}'
            TestUtil.readJsonObject(json)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains('{"field":trux')
        }

        try
        {
            String json = '{"field":tru'
            TestUtil.readJsonObject(json)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains('{"field":tru')
        }
    }

    @Test
    void testStringEscape()
    {
        String json = '["escaped slash \\\' should result in a single /"]'
        TestUtil.readJsonObject(json)

        json = '["escaped slash \\/ should result in a single /"]'
        TestUtil.readJsonObject(json)

        try
        {
            json = '["escaped slash \\x should result in a single /"]'
            TestUtil.readJsonObject(json)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains('["escaped slash \\x')
        }
    }

    @Test
    void testClassMissingValue()
    {
        try
        {
            TestUtil.readJsonObject('[{"@type":"class"}]')
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains('lass listed')
            assert e.message.contains('not found')
        }
    }

    @Test
    void testCalendarMissingValue()
    {
        try
        {
            TestUtil.readJsonObject('[{"@type":"java.util.Calendar"}]')
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains('lass listed')
            assert e.message.contains('not found')
        }
    }

    @Test
    void testBadFormattedCalendar()
    {
        try
        {
            TestUtil.readJsonObject('[{"@type":"java.util.GregorianCalendar","value":"2012-05-03T12:39:45.1X5-0400"}]')
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains('ailed to parse calendar')
        }
    }

    @Test
    void testEmptyClassName()
    {
        try
        {
            TestUtil.readJsonObject('{"@type":""}')
            fail()
        }
        catch(Exception e)
        {
            assert e.message.contains('nable to create class')
        }
    }

    @Test
    void testBadBackRef()
    {
        try
        {
            TestUtil.readJsonObject('{"@type":"java.util.ArrayList","@items":[{"@ref":1}]}')
            fail()
        }
        catch(Exception ignored)
        { }
    }

    @Test
    void testErrorReporting()
    {
        String json = '[{"@type":"funky"},\n{"field:"value"]'
        try
        {
            JsonReader.jsonToJava(json)
            fail()
        }
        catch (Exception e)
        {
            assert e.message.contains("xpected ':'")
            assert e.message.contains('"field:"v')
        }
    }

    @Test
    void testFieldMissingQuotes()
    {
        try
        {
            String json = '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, {"field":25, field2: "no quotes"}, 11, 12, 13]'
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (JsonIoException e)
        {
            assert e.message.contains('"field":25, f')
        }
    }

    @Test
    void testBadFloatingPointNumber()
    {
        try
        {
            String json = '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, {"field":25, "field2": "no quotes"}, 11, 12.14a, 13]'
            JsonReader.jsonToJava(json, [(JsonReader.USE_MAPS):true] as Map)
            fail()
        }
        catch (JsonIoException e)
        {
            assert e.message.toLowerCase().contains('expected \',\' or \']\' inside array')
        }
    }
}
