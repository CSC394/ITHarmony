package com.itharmony.parse;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class DocumentGenerator implements DocumentInterface {

    public String generateDocument (String fileLoc) {

        DocumentFactory documentFactory = new DocumentFactory();
        ExtensionFinder extensionFinder = new ExtensionFinder();

        String extension = extensionFinder.finder(fileLoc);

        Document document = documentFactory.getDoc(extension);

        document.create(fileLoc);

        return document.getResumeText();

    }






}

