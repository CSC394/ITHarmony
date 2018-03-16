package com.itharmony.process;


import opennlp.tools.namefind.RegexNameFinder;
import opennlp.tools.namefind.RegexNameFinderFactory;
import opennlp.tools.util.Span;

/**
 * class to return matched entity regular expressions
 *
 * @return matched entities within a string of text
 */

public class RegExEntityFinder {

    private static RegexNameFinder regexNameFinder;

    public Span[] entityFinder (String text) {

        regexNameFinder = RegexNameFinderFactory.getDefaultRegexNameFinders(
            RegexNameFinderFactory.DEFAULT_REGEX_NAME_FINDER.EMAIL,
            RegexNameFinderFactory.DEFAULT_REGEX_NAME_FINDER.USA_PHONE_NUM,
            RegexNameFinderFactory.DEFAULT_REGEX_NAME_FINDER.URL,
            RegexNameFinderFactory.DEFAULT_REGEX_NAME_FINDER.COLLEGE);

        return regexNameFinder.find(text);

    }
}
