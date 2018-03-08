package com.itharmony.parse;

import org.junit.Assert;
import org.junit.Test;

public class ExtensionFinderTest {

    @Test
    public void testExtensionFinder() {

        ExtensionFinder extensionFinder = new ExtensionFinder();

        String[] fileNameArray = new String[5];

        fileNameArray[0] = "mikesresume";
        fileNameArray[1] = "mikesresume.txt.txt";
        fileNameArray[2] = "mikesresume.docx";
        fileNameArray[3] = "donsresume.DoC";
        fileNameArray[4] = "patsresume.PDF";

        Assert.assertEquals(null , extensionFinder.finder(fileNameArray[0]));
        Assert.assertEquals("txt.txt" , extensionFinder.finder(fileNameArray[1]));
        Assert.assertEquals("docx" , extensionFinder.finder(fileNameArray[2]));
        Assert.assertEquals("doc" , extensionFinder.finder(fileNameArray[3]));
        Assert.assertEquals("pdf" , extensionFinder.finder(fileNameArray[4]));

    }


}
