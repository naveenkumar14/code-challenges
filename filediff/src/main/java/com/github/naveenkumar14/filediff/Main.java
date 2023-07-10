package com.github.naveenkumar14.filediff;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Diff diff = new Diff();
        if (args.length != 2) {
            System.out.println("Invalid argument!!");
            return;
        }
        try {
            List<String> res = diff.findDiff(args[0], args[1]);
            for (String str : res) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
