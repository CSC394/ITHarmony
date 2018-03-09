package com.itharmony.parse;

import org.junit.Assert;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DocumentGeneratorTest {

    DocumentInterface documentInterface = new DocumentGenerator();

    @Test (expected = NotImplementedException.class)
    public void testNotFileLocation () {
        documentInterface.generateDocument("notafilelocation", "alsonotone");
    }

    @Test (expected = NotImplementedException.class)
    public void testNotImplemented () {
        documentInterface.generateDocument("resume.csv", "resume.csv");
    }






}
