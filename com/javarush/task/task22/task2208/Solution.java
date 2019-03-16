package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Формируем WHERE
Сформируй часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.

Пример:
{name=Ivanov, country=Ukraine, city=Kiev, age=null}

Результат:
name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'


*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("name",null);
        params.put("country",null);
        params.put("city",null);
        params.put("age",null);
        System.out.println(params);
        System.out.println(getQuery(params));

    }
    public static String getQuery(Map<String, String> params) {
        String where = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> pair : params.entrySet()) {
            String key = pair.getKey();
            String value  = pair.getValue();
            if (value != null && key != null) {
                stringBuilder.append(String.format("%s = '%s' and ", key, value));
            }
        }
        if (stringBuilder.length() != 0) {
            int length = stringBuilder.length();
            stringBuilder.delete(length - 5, length);
            where = stringBuilder.toString();
        }

        return where;
    }
}
