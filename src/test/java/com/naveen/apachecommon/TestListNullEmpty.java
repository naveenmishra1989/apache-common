package com.naveen.apachecommon;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 class TestListNullEmpty {

    static List<String> getList() {
        List<String> strings = new ArrayList<>();
        strings.add("naveen");
        return strings;
    }

     public static <T> boolean IsNullOrEmpty(Collection<T> list) {
         return list == null || list.isEmpty();
     }

     private static <T> boolean IsNullOrEmpty1(Collection<T> list) {
         return list == null || list.isEmpty();
     }



    @Test
     void main() {
        List<String> list = getList();

        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start("Second");
        for (int i = 0; i < 1000; i++) {
            IsNullOrEmpty1(list);
        }
        stopWatch1.stop();
        System.out.println(stopWatch1.prettyPrint());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("First");
        for (int i = 0; i < 1000; i++) {
            IsNullOrEmpty(list);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());

    }
}

