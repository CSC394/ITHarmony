package com.itharmony.analysisdriver;

import com.itharmony.dictionary.DictionaryBuilder;
import com.itharmony.json.JsonBuilder;
import com.itharmony.parse.DocumentGenerator;
import com.itharmony.parse.DocumentInterface;
import com.itharmony.process.DictionaryEntityFinder;
import com.itharmony.process.RegExEntityFinder;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.itharmony.dictionary.DictionaryBuilder.createRegExMap;

public class ProgramDriver {

    private static DocumentInterface documentInterface;
    private static RegExEntityFinder regExEntityFinder;
    private DictionaryEntityFinder webEntityFinder;
    private DictionaryEntityFinder mobileEntityFinder;
    private DictionaryEntityFinder engineeringEntityFinder;

    public void process () {

        String[] webDevelopmentEntities = new String[]{"HTML", "JavaScript", "Django", "Ruby On Rails",
            "Bootstrap", "jQuery", "Spring Framework", "NodeJS", "CSS", "Angular"};
        String[] softEngineeringEntities = new String[]{"Front End", "Back End", "Fullstack", "Scripting",
            "Algorithms", "Java", "C", "C++", "C#", "Python"};
        String[] mobileDevelopmentEntities = new String[]{"iOS", "Android", "Swift", "Objective-C",
            "Kotlin", "UI", "UX"};

        documentInterface = new DocumentGenerator();
        regExEntityFinder = new RegExEntityFinder();
        engineeringEntityFinder = new DictionaryEntityFinder("SOFTWARE_ENGINEERING");
        webEntityFinder = new DictionaryEntityFinder("WEB_DEVELOPMENT");
        mobileEntityFinder = new DictionaryEntityFinder("MOBILE_DEVELOPMENT");




        String docFileLocation = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents" +
            "\\Kolback,MichaelJanResumeDoc.doc";

        String docxFileLocation = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents" +
            "\\Kolback,MichaelJanResume.docx";

        String pdfFileLocation = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents" +
            "\\Kolback,MichaelJanResumeDoc.pdf";

        String docxText = documentInterface.generateDocument(docxFileLocation);

        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

        String[] docxTokens = simpleTokenizer.tokenize(docxText);

        Span[] engineArray = engineeringEntityFinder.entityFinder(docxTokens, softEngineeringEntities);
        Span[] mobileArray = mobileEntityFinder.entityFinder(docxTokens, mobileDevelopmentEntities);
        Span[] webArray = webEntityFinder.entityFinder(docxTokens, webDevelopmentEntities);
        Span[] regExArray = regExEntityFinder.entityFinder(docxText);

        Map<String, HashSet<CharSequence>> regExMap = DictionaryBuilder.createRegExMap(regExArray, docxText);
        Map<String, HashSet<CharSequence>> engineMap = DictionaryBuilder.createDictMap(engineArray, docxTokens);
        Map<String, HashSet<CharSequence>> webMap = DictionaryBuilder.createDictMap(webArray, docxTokens);
        Map<String, HashSet<CharSequence>> mobileMap = DictionaryBuilder.createDictMap(mobileArray, docxTokens);

        System.out.println(mobileMap.size());


        Map<String, HashSet<CharSequence>> tempMap = Stream.concat(regExMap.entrySet().stream(), engineMap.entrySet().stream())
            .collect(Collectors.toMap(
                entry -> entry.getKey(), // The key
                entry -> entry.getValue() // The value
                )
            );

        Map<String, HashSet<CharSequence>> tempMap2 = Stream.concat(tempMap.entrySet().stream(), mobileMap.entrySet().stream())
            .collect(Collectors.toMap(
                entry -> entry.getKey(), // The key
                entry -> entry.getValue() // The value
                )
            );


        Map<String, HashSet<CharSequence>> finalMap = Stream.concat(tempMap2.entrySet().stream(), webMap.entrySet().stream())
            .collect(Collectors.toMap(
                entry -> entry.getKey(), // The key
                entry -> entry.getValue() // The value
                )
            );


        //Stream.of(finalMap.values().toString()).forEach(System.out::println);





        String json = JsonBuilder.mapToJson(finalMap);

        System.out.println(json);







    }

    public static void main(String[] args) {

        ProgramDriver programDriver = new ProgramDriver();

        programDriver.process();


    }






}
