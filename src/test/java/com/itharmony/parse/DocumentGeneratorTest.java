package com.itharmony.parse;

import org.junit.Before;
import org.junit.Test;

public class DocumentGeneratorTest {

    @Test
    public void testPdfParse() {
        String pdfFilename = "C:\\Users\\Mike Kolback\\Desktop\\testResumes\\BrandonMartinTechResume.pdf";

        DocumentInterface documentInterface = new DocumentGenerator();

        String pdfText = documentInterface.generateDocument(pdfFilename);

        System.out.println(pdfText);
    }

    @Test
    public void testdocxParse() {

        String docxFilename = "C:\\Users\\Mike Kolback\\Desktop\\testResumes\\BrandonMartinTechResumeTestdocx.docx";

        String docxFileLocation = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents" +
            "\\Kolback,MichaelJanResume.docx";

        DocumentInterface documentInterface = new DocumentGenerator();

        String docxText = documentInterface.generateDocument(docxFileLocation);

        System.out.println(docxText);


    }

    @Test
    public void testdocParse() {

        String docFilename = "C:\\Users\\Mike Kolback\\Desktop\\testResumes\\BrandonMartinTechResumedoc.doc";

        DocumentInterface documentInterface = new DocumentGenerator();

        String docText = documentInterface.generateDocument(docFilename);

        System.out.println(docText);


    }



}
