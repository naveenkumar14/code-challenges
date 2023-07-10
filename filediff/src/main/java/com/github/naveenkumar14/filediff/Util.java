package com.github.naveenkumar14.filediff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
    /**
     * Read the file and return array of string with each string containging a line
     * of file
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    public static String[] readLines(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new IllegalArgumentException(filename + " does not exists");
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }

    /**
     * returns the longest common subsequence of requested 2 string arrays
     * using dynamic programming approach
     * @param text1
     * @param text2
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String> longestCommonSubsequence(String[] text1, String[] text2) {
        List<String> dp[][] = new ArrayList[text1.length + 1][text2.length + 1];
        for (List<String>[] arr : dp) {
            Arrays.fill(arr, new ArrayList<>());
        }
        for (int i = text1.length - 1; i >= 0; i--) {
            for (int j = text2.length - 1; j >= 0; j--) {
                String str1 = text1[i];
                String str2 = text2[j];
                if (str1.equals(str2)) {
                    dp[i][j] = new ArrayList<>();
                    dp[i][j].add(str1);
                    dp[i][j].addAll(dp[i + 1][j + 1]);
                } else {

                    if (dp[i][j + 1].size() > dp[i + 1][j].size()) {
                        dp[i][j] = dp[i][j + 1];
                    } else {
                        dp[i][j] = dp[i + 1][j];
                    }
                }
            }
        }
        return dp[0][0];
    }
}
