package com.itharmony.parse;

import java.util.regex.*;

public class ExtensionFinder {

    public String finder (String filename) {
        String ext = null;
        String extregex = "(?<=\\.).*$";
        Pattern pattern = Pattern.compile(extregex);
        Matcher matcher = pattern.matcher(filename);
        if (matcher.find()) {
            ext = matcher.group();
        }
        return ext;
    }

}
