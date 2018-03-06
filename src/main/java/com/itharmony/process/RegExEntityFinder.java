package com.itharmony.process;

import com.itharmony.tokenize.DocumentTokenizer;
import opennlp.tools.namefind.RegexNameFinder;
import opennlp.tools.namefind.RegexNameFinderFactory;
import opennlp.tools.util.Span;




import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class RegExEntityFinder {

    private static RegexNameFinder regexNameFinder;

    public void setUp() {

        regexNameFinder =RegexNameFinderFactory.getDefaultRegexNameFinders(
            RegexNameFinderFactory.DEFAULT_REGEX_NAME_FINDER.DEGREES_MIN_SEC_LAT_LON,
            RegexNameFinderFactory.DEFAULT_REGEX_NAME_FINDER.EMAIL,
            RegexNameFinderFactory.DEFAULT_REGEX_NAME_FINDER.MGRS,
            RegexNameFinderFactory.DEFAULT_REGEX_NAME_FINDER.USA_PHONE_NUM,
            RegexNameFinderFactory.DEFAULT_REGEX_NAME_FINDER.URL,
            RegexNameFinderFactory.DEFAULT_REGEX_NAME_FINDER.COLLEGE
        );
    }

    public Span[] emailFinder (String[] tokens) {

        Span[] find = regexNameFinder.find(tokens);



        return find;

    }



















    public Span[] collegeFinder (String[] tokens) {

        String collegeRegEx = "university\\s+of\\s+\\w+";
        String collegeRegEx2 = "\\w+\\s+university";
        Pattern collegePattern = Pattern.compile(collegeRegEx, Pattern.CASE_INSENSITIVE);
        Pattern collegePattern2 = Pattern.compile(collegeRegEx2, Pattern.CASE_INSENSITIVE);

        Pattern[] patterns = new Pattern[] {collegePattern,collegePattern2};

        Map<String, Pattern[]> regexMap = new HashMap<>();

        String type = "college";

        regexMap.put(type, patterns);

        RegexNameFinder finder = new RegexNameFinder(regexMap);

        Span[] result = finder.find(tokens);

        return result;

    }

    public static void main (String[] args) {

        String text = "Objective:  Since 2010, when I joined the United States Air Force, I have had the opportunity to work on technology projects utilizing both hardware and software components while proving myself as a reliable team member and contributor.  This exposure has led to my decision to pursue computer science as my major at DePaul University.  I am currently seeking an opportunity with an organization where my skillset and experience will be leveraged and built upon while working to achieve team and company goals.\n" +
            "\n" +
            "Education\n" +
            "DePaul University, Chicago, IL - Expected Graduation Date: March 2018 \n" +
            "•\tBachelor of Science: Computer Science\n" +
            "•\tGPA: 3.12\n" +
            "Community College of the Air Force, Keesler AFB, MS - Graduation Date: August 2010\n" +
            "•\tAir Force Electronic Principles School University of Rochester \n" +
            "\n" +
            "Technical Skills\n" +
            "•\tProgramming/Scripting Languages: Java, Python, Scala, JavaScript, R\n" +
            "•\tProgramming Platforms: Eclipse, IntelliJ IDEA, VisualStudio\n" +
            "•\tWeb Design: HTML, CSS \n" +
            "•\tDatabase Technologies: Microsoft Access, MySQL, MongoDB\n";


        DocumentTokenizer documentTokenizer = new DocumentTokenizer();

        String[] docSimpleTokenized = documentTokenizer.simpleTokenize(text);

        /*for (String thing: docSimpleTokenized) {
            System.out.println(thing);
        }*/

        RegExEntityFinder regExEntityFinder = new RegExEntityFinder();

        Span[] results = regExEntityFinder.collegeFinder(docSimpleTokenized);

        System.out.println(results.length);

        System.out.println(results[0].toString());
        System.out.println(results[1].toString());
        System.out.println(results[2].toString());
        System.out.println(results[3].toString());



    }



}
