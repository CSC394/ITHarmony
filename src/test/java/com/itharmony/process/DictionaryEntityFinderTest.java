package com.itharmony.process;

import com.itharmony.tokenize.DocumentTokenizer;
import com.itharmony.tokenize.TokenizerInterface;
import opennlp.tools.util.Span;
import org.junit.Assert;
import org.junit.Test;

public class DictionaryEntityFinderTest {

    @Test
    public void testDictionaryEntities() {
        String text = "max a b c d vanessa williams";

        DictionaryEntityFinder dictionaryEntityFinder = new DictionaryEntityFinder();
        TokenizerInterface tokenizerInterface = new DocumentTokenizer();
        String[] tokens = tokenizerInterface.simpleTokenize(text);

        Span[] stuff = dictionaryEntityFinder.entityFinder(tokens);

        System.out.println(stuff.length);





    }







}
