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
        String extension = ExtensionFinder.finder(fileLoc);

        if (!extension.equals("doc") && !extension.equals("docx") && !extension.equals("pdf")) {
            throw new NotImplementedException();
        }

        Document document = DocumentFactory.getDoc(extension);
        document.create(fileLoc);
        return document.getResumeText();
    }
}

