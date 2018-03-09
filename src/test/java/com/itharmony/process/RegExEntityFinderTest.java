package com.itharmony.process;

import com.itharmony.tokenize.DocumentTokenizer;
import com.itharmony.tokenize.TokenizerInterface;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegExEntityFinderTest {

    private Span[] simpleentities;
    private Span[] whiteentities;

    @Before
    public void setUp() {
        String text = " my email is openthis@gmail.com, and my phone number is 724-333-3333, " +
            " i also have a github at the link github.com/mikescoolstuff. " +
            " im attending DePaul University, " +
            " another cell phone number i use is (323)-232-2222. " +
            " i attended univerity of toronto as well" +
            " i use mikee@yahoo.com too";

        TokenizerInterface tokenizerInterface = new DocumentTokenizer();
        String[] whiteTokens = tokenizerInterface.whitespaceTokenize(text);
        String[] simpleTokens = tokenizerInterface.simpleTokenize(text);

        RegExEntityFinder regExEntityFinder = new RegExEntityFinder();

        simpleentities = regExEntityFinder.entityFinderPhoneURLEmail(simpleTokens);
        whiteentities = regExEntityFinder.entityFinderCollege(whiteTokens);

        System.out.println(Span.spansToStrings(simpleentities, simpleTokens));



    }

    @Test
    public void testPhoneNumber() {

        Assert.assertEquals(2, simpleentities.length);

    }


}
