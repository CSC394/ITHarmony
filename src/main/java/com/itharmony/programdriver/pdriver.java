package com.itharmony.programdriver;

import com.itharmony.parse.DocumentGenerator;
import com.itharmony.parse.DocumentInterface;
import com.itharmony.tokenize.DocumentTokenizer;
import com.itharmony.tokenize.TokenizerInterface;



public class pdriver {

    public static void main (String[] args) {

        String fileLoc = "C:\\Users\\Mike Kolback\\Desktop\\Employment Documents\\Kolback,MichaelJanResumeDoc.doc";

        DocumentInterface documentInterface = new DocumentGenerator();

        String resumeText = documentInterface.generateDocument(fileLoc);

        System.out.println(resumeText);

        TokenizerInterface tokenizerInterface = new DocumentTokenizer();

        String[] tokens = tokenizerInterface.simpleTokenize(resumeText);

        for (String token: tokens) {
            System.out.println(token);
        }




    }






}
