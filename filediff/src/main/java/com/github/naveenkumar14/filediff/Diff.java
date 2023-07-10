package com.github.naveenkumar14.filediff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Diff {

    /**
     * get the difference from requested files
     * 
     * @param file1
     * @param file2
     * @return
     * @throws IOException
     */
    public List<String> findDiff(String file1, String file2) throws IOException {
        String[] text1 = Util.readLines(file1);
        String[] text2 = Util.readLines(file2);
        return findDiff(text1, text2);
    }

    /**
     * get the difference from requested files
     * 
     * @param text1
     * @param text2
     * @return
     */
    private List<String> findDiff(String[] text1, String[] text2) {
        List<String> res = new ArrayList<>();
        List<String> lcs = Util.longestCommonSubsequence(text1, text2);
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

}
