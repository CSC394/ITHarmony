package com.itharmony.parse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Document {

    StringBuilder sbuilder;
    String resumeText;

    abstract void create(String fileName);

    public String getResumeText() {
        return resumeText;
    }
}

class XWPF extends Document{

    @Override
    void create(String fileName) {
        File resumeFile = new File(fileName);
        sbuilder = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(resumeFile.getAbsolutePath());
            XWPFDocument doc = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                System.out.println(para.getParagraphText());
                sbuilder.append(para.getParagraphText());
                sbuilder.append(" ");
            }
            fis.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        resumeText = sbuilder.toString();
    }
}

class HWPF extends Document {

    @Override
    void create(String fileName) {
        File resumeFile = new File(fileName);
        sbuilder = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(resumeFile.getAbsolutePath());
            HWPFDocument doc = new HWPFDocument(fis);
            WordExtractor extractor = new WordExtractor(doc);
            String[] fileData = extractor.getParagraphText();
            for (int i = 0; i < fileData.length; i++){
                System.out.println(fileData[i]);
                sbuilder.append(fileData[i]);
            }
            fis.close();
        }
        catch (Exception e) {

        }
        resumeText = sbuilder.toString();
    }
}

class PDDoc extends Document {

    @Override
    void create(String fileName) {
        File resumeFile = new File(fileName);

        try {
            PDDocument doc = PDDocument.load(resumeFile);
            if (!doc.isEncrypted()) {
                PDFTextStripperByArea strip = new PDFTextStripperByArea();
                strip.setSortByPosition(true);

                PDFTextStripper tstrip = new PDFTextStripper();

                String pdfFileInText = tstrip.getText(doc);

                System.out.println(pdfFileInText);
            }
        }
        catch (Exception e){

        }
    }
}

class DocumentFactory {

    public Document getDoc(String ext) {

        if (ext.equals("docx")) {
            return new XWPF();
        } else if (ext.equals("doc")) {
            return new HWPF();
        } else if (ext.equals("pdf")) {
            return new PDDoc();
        }

        return null;
    }

}
