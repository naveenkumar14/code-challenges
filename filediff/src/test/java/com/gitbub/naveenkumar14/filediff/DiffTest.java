package com.gitbub.naveenkumar14.filediff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class DiffTest {

    Diff diff = new Diff();

    @Test
    void longestCommonSubsequence1() {
        List<String> res = diff.longestCommonSubsequence(
                new String[] { "This is a test which contains:", "this is the lcs" },
                new String[] { "this is the lcs", "we're testing" });
        System.out.println(res);
        List<String> exp = new ArrayList<>();
        exp.add("this is the lcs");
        assertEquals(exp, res);
    }

    @Test
    void longestCommonSubsequence2() {
        List<String> res = diff.longestCommonSubsequence(
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
            String[] strs = diff.readLines(file);
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
            String[] strs = diff.readLines(file);
            System.out.println(Arrays.toString(strs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readLinesTest3() {
        try {
            diff.readLines("original.txt");
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            Assertions.fail();
        }
    }

    @Test
    void findDiff() {
        List<String> res = diff.findDiff(
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

        // System.out.println(res);
        for (String str : res) {
            System.out.println(str);
        }
    }

    @Test
    void findDiff2() {
        List<String> res = diff.findDiff(
                new String[] { "This is a test which contains:", "this is the lcs" },
                new String[] { "this is the lcs", "we're testing" });
        // System.out.println(res);
        for (String str : res) {
            System.out.println(str);
        }
    }

    @Test
    void findDiffForFile() {
        List<String> res;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource1 = classLoader.getResource("newcc.txt");
            URL resource2 = classLoader.getResource("origcc.txt");
            res = diff.findDiff(resource1.getFile(), resource2.getFile());
            // System.out.println(String.join(",", res));
            for (String str : res) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findDiffForFile3() {
        List<String> res;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource1 = classLoader.getResource("original.txt");
            URL resource2 = classLoader.getResource("new.txt");
            res = diff.findDiff(resource1.getFile(), resource2.getFile());
            // System.out.println(String.join(",", res));
            for (String str : res) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findDiffForFile2() {
        List<String> res;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource1 = classLoader.getResource("new.txt");
            URL resource2 = classLoader.getResource("original.txt");
            res = diff.findDiff(resource1.getFile(), resource2.getFile());
            // System.out.println(String.join(",", res));
            for (String str : res) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
