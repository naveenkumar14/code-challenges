package com.gitbub.naveenkumar14.filediff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Diff {

    /**
     * @param filename
     * @return
     * @throws IOException
     */
    public String[] readLines(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new IllegalArgumentException(filename + " does not exists");
        }
        FileReader fileReader = new FileReader(filename);
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
     * @param file1
     * @param file2
     * @return
     * @throws IOException
     */
    public List<String> findDiff(String file1, String file2) throws IOException {
        String[] text1 = readLines(file1);
        String[] text2 = readLines(file2);
        return findDiff(text1, text2);
    }

    /**
     * @param text1
     * @param text2
     * @return
     */
    public List<String> findDiff(String[] text1, String[] text2) {
        List<String> res = new ArrayList<>();
        List<String> lcs = longestCommonSubsequence(text1, text2);
        int i = 0, j = 0, k = 0;
        while (i < text1.length || j < text2.length) {
            String commonString = (k < lcs.size()) ? lcs.get(k) : "";
            if (i < text1.length && commonString.equals(text1[i])) {
                i++;
                while (j < text2.length && !commonString.equals(text2[j])) {
                    res.add("<" + text2[j++]);
                }
                j++;
                k++;
            } else if (j < text2.length && commonString.equals(text2[j])) {
                j++;
                while (i < text1.length && !commonString.equals(text1[i])) {
                    res.add(">" + text1[i++]);
                }
                i++;
                k++;
            } else {
                if (i < text1.length)
                    res.add(">" + text1[i++]);
                if (j < text2.length)
                    res.add("<" + text2[j++]);
            }
        }
        return res;

    }

    /**
     * @param text1
     * @param text2
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> longestCommonSubsequence(String[] text1, String[] text2) {
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
