package com.itharmony.parse;

import org.junit.Assert;
import org.junit.Test;
import com.itharmony.parse.Document;
import com.itharmony.parse.DocumentGenerator;

import java.io.File;

public class DocumentTest {

    /**Tests for document factory**/

    @Test
    public void testDocument() {

        DocumentFactory documentFactory = new DocumentFactory();

        Document XWPF = documentFactory.getDoc("docx");
        Document HWPF = documentFactory.getDoc("doc");
        Document PDF = documentFactory.getDoc("pdf");


        Assert.assertEquals(HWPF.getClass() , (documentFactory.getDoc("doc").getClass()));
        Assert.assertEquals(XWPF.getClass() , (documentFactory.getDoc("docx").getClass()));
        Assert.assertEquals(PDF.getClass() , (documentFactory.getDoc("pdf").getClass()));

    }





}
