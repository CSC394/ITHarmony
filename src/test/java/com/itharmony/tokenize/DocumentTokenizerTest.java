package com.itharmony.tokenize;

import org.junit.Assert;
import org.junit.Test;

public class DocumentTokenizerTest {

    @Test
    public void testTokenizer() {

        String text = "Objective:  Since 2010, when I joined the United States Air Force, I have had the opportunity to work on technology projects utilizing both hardware and software components while proving myself as a reliable team member and contributor.  This exposure has led to my decision to pursue computer science as my major at DePaul University.  I am currently seeking an opportunity with an organization where my skillset and experience will be leveraged and built upon while working to achieve team and company goals.\n" +
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
            "•\tDatabase Technologies: Microsoft Access, MySQL, MongoDB\n";

        DocumentTokenizer documentTokenizer = new DocumentTokenizer();

        String[] docSimpleTokenized = documentTokenizer.simpleTokenize(text);

        for (String token : docSimpleTokenized) {
            System.out.println(token);
        }

        Assert.assertTrue(docSimpleTokenized.length > 50);









    }
}
