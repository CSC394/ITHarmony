package com.itharmony.process;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DictionaryEntityFinderTest {

    @Test
    public void testDictionaryEntityFinder() {

        String[] webDevelopmentEntities = new String[]{"HTML", "JavaScript", "Django", "Ruby On Rails",
            "Bootstrap", "jQuery", "Spring Framework", "NodeJS", "CSS", "Angular"};
        String[] softEngineeringEntities = new String[]{"Front End", "Back End", "Fullstack", "Scripting",
            "Algorithms", "Java", "C", "C++", "C#", "Python"};
        String[] mobileDevelopmentEntities = new String[]{"iOS", "Android", "Swift", "Objective-C",
            "Kotlin", "UI", "UX"};

        DictionaryEntityFinder engineeringEntityFinder = new DictionaryEntityFinder(
            "SOFTWARE_ENGINEERING");
        DictionaryEntityFinder mobileEntityFinder = new DictionaryEntityFinder(
            "MOBILE_DEVELOPMENT");
        DictionaryEntityFinder webEntityFinder = new DictionaryEntityFinder(
            "WEB_DEVELOPMENT");


        String text = "Michael Kolback\n" +
            "Chicago, IL 60622\n" +
            "(724) 622-1730\n" +
            "MKOLBACK@mail.depaul.edu \n" +
            "\n" +
            "Objective:  Since 2010, when I joined the United Front End: Full Stack: States Air Force, I have had the opportunity to work on technology projects utilizing both hardware and software components while proving myself as a reliable team member and contributor.  This exposure has led to my decision to pursue computer science as my major at DePaul University.  I am currently seeking an opportunity with an organization where my skillset and experience will be leveraged and built upon while working to achieve team and company goals.\n" +
            "\n" +
            "Education\n" +
            "DePaul University, Chicago, IL - Expected Graduation Date: March 2018 \n" +
            "* Bachelor of Science: Computer Science\n" +
            "* GPA: 3.12\n" +
            "* www.github.com/mikespage\n" +
            "Community College of the Air Force, Keesler AFB, MS - Graduation Back End Date: August 2010\n" +
            "* Air Force Electronic Principles School \n" +
            "\n" +
            "Technical Skills\n" +
            "* Programming/Scripting Languages: Java, Python, Scala, JavaScript, R\n" +
            "* Programming Platforms: Eclipse, IntelliJ IDEA, VisualStudio\n" +
            "* Web Design: HTML, CSS \n" +
            "* Database Technologies: Microsoft Access, MySQL, MongoDB\n" +
            "\n" +
            "Projects\n" +
            "> Data Analysis:\n" +
            "o Designed a custom big on Spring Framework JAVA JAVA java java data-mining application for use in the analysis and graphing of social networks\n" +
            "o Analyzed and generated a regression model with a high degree of correlation that exposed factors impacting high-school dropout rates in American States\n" +
            "> Object – Oriented Design:\n" +
            "o Programmed a boolean satisfiability solver (SAT Solver) which was applied to complex puzzles to return effective solutions\n" +
            "o Developed front-end/back-end web application using JSP’s and both MySQL and MongoDB Databases\n" +
            "o Built a socket – based, multithreaded messenger program that supported multiple users sending text messages / files\n" +
            "\n" +
            "Professional Experience \n" +
            "* Ive also done work in android, swift, kotlin, ios:::TechNexus Venture Collaborative, September 2016 – November 2017, Technical Analyst (Internship)\n" +
            "- Lead project manager in facility-wide A/V upgrade to meet the needs of 400 daily customers.\n" +
            "- Provided rapid IT/Software support for customers to ensure a productive experience while working closely with the TechNexus CEO to resolve technology issues.\n" +
            "- Assisted with overall facility readiness; maintained and built relationships with over 50 companies in residence.\n" +
            "* Frequency A/V, April 2015 – March 2016, Audio/Visual Technician\n" +
            "- Deployed commercial A/V control system equipment to create fully integrated board room environments.\n" +
            "- Utilized signal flow principles, troubleshooting procedures, and wiring diagrams to remedy defective modules during the integration process.\n" +
            "* United States Air Force, April 2010 – July 2014, RF Transmissions Technician\n" +
            "- Responsible for the implementation of a core training initiative that accelerated the squadron’s adoption of the mobile radio system. \n" +
            "- Led 31st Combat Communications Unit radio systems/network upgrade project with a total project budget of $1.4M.\n" +
            "- Supported over 300 users and managed 3 long-haul satellite communication links allowing for a consistent flow of operations during North Atlantic Treaty Organization (NATO) exercise Dacain Thunder.\n" +
            "\n" +
            "\n" +
            "\n";

        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

        String[] tokens = simpleTokenizer.tokenize(text);

        Span[] engineArray = engineeringEntityFinder.entityFinder(tokens, softEngineeringEntities);
        Span[] mobileArray = mobileEntityFinder.entityFinder(tokens, mobileDevelopmentEntities);
        Span[] webArray = webEntityFinder.entityFinder(tokens, webDevelopmentEntities);

        System.out.println(engineArray.length);
        System.out.println(mobileArray.length);
        System.out.println(webArray.length);

        String[] engineeringTokens = Span.spansToStrings(engineArray, tokens);
        String[] mobileTokens = Span.spansToStrings(mobileArray, tokens);
        String[] webTokens = Span.spansToStrings(webArray, tokens);

        System.out.println("Engineering Tokens");
        for (String stuff : engineeringTokens) {
            System.out.println(stuff);
        }

        System.out.println("\n");

        System.out.println("Mobile Tokens");
        for (String stuff : mobileTokens) {
            System.out.println(stuff);
        }

        System.out.println("\n");

        System.out.println("Web Tokens");
        for (String stuff : webTokens) {
            System.out.println(stuff);
        }

    }


}
