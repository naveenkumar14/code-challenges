package com.gitbub.naveenkumar14.filediff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for DiffTest
 */
class DiffTest {

    Diff diff = new Diff();

    private Method getFindDiffMethod() throws NoSuchMethodException {
        Method method = Diff.class.getDeclaredMethod("findDiff", String[].class, String[].class);
        method.setAccessible(true);
        return method;
    }

    @Test
    @SuppressWarnings("unchecked")
    void findDiff() {
        List<String> res;
        try {
            res = (List<String>) getFindDiffMethod().invoke(diff, new String[] {
                    "Coding Challenges helps you become a better software engineer through that build real applications.",
                    "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                    "I’ve used or am using these coding challenges as exercise to learn a new programming language or technology.",
                    "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities." },
                    new String[] {
                            "Helping you become a better software engineer through coding challenges that build real applications.",
                            "I share a weekly coding challenge aimed at helping software engineers level up their skills through deliberate practice.",
                            "These are challenges that I’ve used or am using as exercises to learn a new programming language or technology.",
                            "Each challenge will have you writing a full application or tool. Most of which will be based on real world tools and utilities." });
            List<String> exp=new ArrayList<>();
            exp.add(">Coding Challenges helps you become a better software engineer through that build real applications.");
            exp.add("<Helping you become a better software engineer through coding challenges that build real applications.");
            exp.add(">I\u2019ve used or am using these coding challenges as exercise to learn a new programming language or technology.");
            exp.add("<These are challenges that I\u2019ve used or am using as exercises to learn a new programming language or technology.");
            assertEquals(exp, res);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException e) {
            Assertions.fail();
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void findDiff2() {
        List<String> res;
        try {
            res = (List<String>) getFindDiffMethod().invoke(diff,
                    new String[] { "This is a test which contains:", "this is the lcs" },
                    new String[] { "this is the lcs", "we're testing" });
            List<String> exp=new ArrayList<>();
            exp.add(">This is a test which contains:");
            exp.add("<we're testing");
            assertEquals(exp, res);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException e) {
            Assertions.fail();
        }
    }

    @Test
    void findDiffForFile() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource1 = classLoader.getResource("newcc.txt");
            URL resource2 = classLoader.getResource("origcc.txt");
            List<String> res = diff.findDiff(resource1.getFile(), resource2.getFile());
            String exp[]=new String[]{">Helping you become a better software engineer through coding challenges that build real applications.",
            "<Coding Challenges helps you become a better software engineer through that build real applications.",
            ">These are challenges that I have used or am using as exercises to learn a new programming language or technology.",
            "<I have used or am using these coding challenges as exercises to learn a new programming language or technology."};
            assertEquals(Arrays.asList(exp), res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findDiffForFile3() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource1 = classLoader.getResource("original.txt");
            URL resource2 = classLoader.getResource("new.txt");
            List<String> res = diff.findDiff(resource1.getFile(), resource2.getFile());
            String exp[]=new String[]{"<This is an important",
            "<notice! It should",
            "<therefore be located at",
            "<the beginning of this",
            "<document!",
            "<",
            ">This paragraph contains",
            ">text that is outdated.",
            ">It will be deleted in the",
            ">near future.",
            ">",
            ">check this dokument. On",
            "<check this document. On",
            "<This paragraph contains",
            "<important new additions",
            "<to this document."};
            assertEquals(Arrays.asList(exp), res);
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
            String exp[]=new String[]{">This is an important",
            ">notice! It should",
            ">therefore be located at",
            ">the beginning of this",
            ">document!",
            ">",
            "<This paragraph contains",
            "<text that is outdated.",
            "<It will be deleted in the",
            "<near future.",
            "<",
            ">check this document. On",
            "<check this dokument. On",
            ">This paragraph contains",
            ">important new additions",
            ">to this document."};
            assertEquals(Arrays.asList(exp), res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
