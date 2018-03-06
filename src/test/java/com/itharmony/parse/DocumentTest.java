package com.itharmony.parse;

import org.junit.Assert;
import org.junit.Test;
import com.itharmony.parse.Document;
import com.itharmony.parse.DocumentGenerator;

import java.io.File;

public class DocumentTest {

    /**Tests for document factory**/

    @Test
    public void testDocumentDifferentType() {

        String docFile = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents\\Kolback,MichaelJanResumeDoc.doc";
        String docxFile = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents\\Kolback,MichaelJanResume.docx";
        String pdfFile = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents\\Kolback,Mike-ResumeMay2017.pdf";

        DocumentFactory documentFactory = new DocumentFactory();
        ExtensionFinder extensionFinder = new ExtensionFinder();

        Document doc = documentFactory.getDoc(extensionFinder.finder(docFile));
        doc.create(docFile);
        Document docx = documentFactory.getDoc(extensionFinder.finder(docxFile));
        docx.create(docxFile);
        Document pdf = documentFactory.getDoc(extensionFinder.finder(pdfFile));
        pdf.create(pdfFile);

        Assert.assertTrue(doc.getResumeText().length() > 1);
        Assert.assertTrue(docx.getResumeText().length() > 1);
        Assert.assertTrue(pdf.getResumeText().length() > 1);

    }





}
