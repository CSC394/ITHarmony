package com.itharmony.analysisdriver;

import com.itharmony.dictionary.DictionaryBuilder;
import com.itharmony.json.JsonBuilder;
import com.itharmony.parse.DocumentGenerator;
import com.itharmony.parse.DocumentInterface;
import com.itharmony.parse.ExtensionFinder;
import com.itharmony.process.DictionaryEntityFinder;
import com.itharmony.process.RegExEntityFinder;
import opennlp.tools.util.Span;

import java.util.*;

import static com.itharmony.dictionary.DictionaryBuilder.createMap;

public class ProgramDriver {

    private static DocumentInterface documentInterface;
    private static ExtensionFinder extensionFinder;
    private static RegExEntityFinder regExEntityFinder;
    private static DictionaryEntityFinder dictionaryEntityFinder;

    public void process () {

        documentInterface = new DocumentGenerator();
        regExEntityFinder = new RegExEntityFinder();
        dictionaryEntityFinder = new DictionaryEntityFinder();

        String docFileLocation = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents" +
            "\\Kolback,MichaelJanResumeDoc.doc";

        String docxFileLocation = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents" +
            "\\Kolback,MichaelJanResumeDoc.docx";

        String pdfFileLocation = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents" +
            "\\Kolback,MichaelJanResumeDoc.pdf";

        String docText = documentInterface.generateDocument(docFileLocation);
        String docxText = documentInterface.generateDocument(docxFileLocation);
        String pdfText = documentInterface.generateDocument(pdfFileLocation);


        Span[] spanArray = regExEntityFinder.entityFinder(pdfText);

        System.out.println(spanArray.length);


        Map<String, HashSet<CharSequence>> entityMap = DictionaryBuilder.createMap(spanArray, pdfText);

        String json = JsonBuilder.mapToJson(entityMap);

        System.out.println(json);







    }

    public static void main(String[] args) {

        ProgramDriver programDriver = new ProgramDriver();

        programDriver.process();


    }






}
