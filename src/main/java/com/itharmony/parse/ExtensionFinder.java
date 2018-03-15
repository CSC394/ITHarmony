package com.itharmony.parse;

import java.util.regex.*;

/**
 * class to return file descriptor
 *
 * @return returns file descriptor
 */

abstract class ExtensionFinder {

    public static String finder (String filename) {
        String extension = "notextension";
        String extRegEx = "(?<=\\.).*$";
        Pattern pattern = Pattern.compile(extRegEx);
        Matcher matcher = pattern.matcher(filename);
        if (matcher.find()) {
            extension = matcher.group();
            extension = extension.toLowerCase();
        }
        return extension;
    }


}
