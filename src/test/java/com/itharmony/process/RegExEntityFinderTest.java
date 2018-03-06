package com.itharmony.process;

import com.itharmony.tokenize.TokenizerInterface;
import com.itharmony.tokenize.DocumentTokenizer;
import opennlp.tools.util.Span;

public class RegExEntityFinderTest {

    public static void main(String[] args) {

        String text = "Michael Kolback\n" +
            "Chicago, IL 60622\n" +
            "(724) 622-1730\n" +
            "MKOLBACK@mail.depaul.edu \n" +
            "\n" +
            "Objective:  Since 2010, when I joined the United States Air Force, I have had the opportunity to work on technology projects utilizing both hardware and software components while proving myself as a reliable team member and contributor.  This exposure has led to my decision to pursue computer science as my major at DePaul University.  I am currently seeking an opportunity with an organization where my skillset and experience will be leveraged and built upon while working to achieve team and company goals.\n" +
            "\n" +
            "Education\n" +
            "DePaul University, Chicago, IL - Expected Graduation Date: March 2018 \n" +
            "•\tBachelor of Science: Computer Science\n" +
            "•\tGPA: 3.12\n" +
            "Community College of the Air Force, Keesler AFB, MS - Graduation Date: August 2010\n" +
            "•\tAir Force Electronic Principles School \n" +
            "\n" +
            "Technical Skills\n" +
            "•\tProgramming/Scripting Languages: Java, Python, Scala, JavaScript, R\n" +
            "•\tProgramming Platforms: Eclipse, IntelliJ IDEA, VisualStudio\n" +
            "•\tWeb Design: HTML, CSS \n" +
            "•\tDatabase Technologies: Microsoft Access, MySQL, MongoDB\n" +
            "\n" +
            "Projects\n" +
            "\uF0D8\tData Analysis:\n" +
            "\nwww.github.com/mikekolback/opennlp/asdasdasda "+
            "o\tDesigned a custom data-mining application for use in the analysis and graphing of social networks\n" +
            "o\tAnalyzed and generated a regression model with a high degree of correlation that exposed factors impacting high-school dropout rates in American States\n" +
            "\uF0D8\tObject – Oriented Design:\n" +
            "o\tProgrammed a boolean satisfiability solver (SAT Solver) which was applied to complex puzzles to return effective solutions\n" +
            "o\tDeveloped front-end/back-end web application using JSP’s and both MySQL and MongoDB Databases\n" +
            "o\tBuilt a socket – based, multithreaded messenger program that supported multiple users sending text messages / files\n" +
            "\n" +
            "Professional Experience \n" +
            "•\tTechNexus Venture Collaborative, September 2016 – November 2017, Technical Analyst (Internship)\n" +
            "-\tLead project manager in facility-wide A/V upgrade to meet the needs of 400 daily customers.\n" +
            "-\tProvided rapid IT/Software support for customers to ensure a productive experience while working closely with the TechNexus CEO to resolve technology issues.\n" +
            "-\tAssisted with overall facility readiness; maintained and built relationships with over 50 companies in residence.\n" +
            "•\tFrequency A/V, April 2015 – March 2016, Audio/Visual Technician\n" +
            "-\tDeployed commercial A/V control system equipment to create fully integrated board room environments.\n" +
            "-\tUtilized signal flow principles, troubleshooting procedures, and wiring diagrams to remedy defective modules during the integration process.\n" +
            "•\tUnited States Air Force, April 2010 – July 2014, RF Transmissions Technician\n" +
            "-\tResponsible for the implementation of a core training initiative that accelerated the squadron’s adoption of the mobile radio system. \n" +
            "-\tLed 31st Combat Communications Unit radio systems/network upgrade project with a total project budget of $1.4M.\n" +
            "-\tSupported over 300 users and managed 3 long-haul satellite communication links allowing for a consistent flow of operations during North Atlantic Treaty Organization (NATO) exercise Dacain Thunder.\n";

            RegExEntityFinder regExEntityFinder = new RegExEntityFinder();

            regExEntityFinder.setUp();

            TokenizerInterface tokenizerInterface = new DocumentTokenizer();

            String[] tokens = tokenizerInterface.whitespaceTokenize(text);


            Span[] span = regExEntityFinder.emailFinder(tokens);



            System.out.println(span.length);

            System.out.println(span[0]);
            System.out.println(span[1]);
            System.out.println(span[2]);

            System.out.println(tokens[span[0].getStart()]);
            System.out.println(tokens[6]);

            System.out.println(tokens[7]);

            System.out.println(tokens[163]);


    }



}
