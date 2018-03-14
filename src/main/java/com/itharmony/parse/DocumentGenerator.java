package com.itharmony.parse;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Class to determine what type of document factory to call.  Creates and returns string representation
 * of that file type
 *
 * @return Stringified document
 */

public class DocumentGenerator implements DocumentInterface {

    public String generateDocument (String fileLoc) {

        DocumentFactory documentFactory = new DocumentFactory();
        ExtensionFinder extensionFinder = new ExtensionFinder();
        String extension = extensionFinder.finder(fileLoc);

        if (!extension.equals("doc") && !extension.equals("docx") && !extension.equals("pdf")) {
            throw new NotImplementedException();
        }

        Document document = documentFactory.getDoc(extension);
        document.create(fileLoc);
        return document.getResumeText();

    }
}

