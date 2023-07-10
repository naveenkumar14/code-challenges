package com.github.naveenkumar14.filediff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilTest {
   @Test
    void longestCommonSubsequence1() {
        List<String> res = Util.longestCommonSubsequence(
                new String[] { "This is a test which contains:", "this is the lcs" },
                new String[] { "this is the lcs", "we're testing" });
        System.out.println(res);
        List<String> exp = new ArrayList<>();
        exp.add("this is the lcs");
        assertEquals(exp, res);
    }

    @Test
    void longestCommonSubsequence2() {
        List<String> res = Util.longestCommonSubsequence(
                new String[] {
                        "Coding Challenges helps you become a better software engineer through that build real applications.",
                        "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                        "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
                        "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities." },
                new String[] {
                        "Helping you become a better software engineer through coding challenges that build real applications.",
                        "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                        "These are challenges that I’ve used or am using as exercises to learn a new programming language or technology.",
                        "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities." });
        System.out.println(res);
        List<String> exp = new ArrayList<>();
        exp.add("I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.");
        exp.add("Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities.");
        assertEquals(exp, res);
    }

    

    @Test
    void readLinesTest1() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("new.txt");
            String file = resource.getFile();
            String[] strs = Util.readLines(file);
            System.out.println(Arrays.toString(strs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readLinesTest2() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("original.txt");
            String file = resource.getFile();
            String[] strs = Util.readLines(file);
            System.out.println(Arrays.toString(strs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readLinesTest3() {
        try {
            Util.readLines("original.txt");
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            Assertions.fail();
        }
    }

}
