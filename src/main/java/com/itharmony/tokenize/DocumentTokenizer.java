package com.itharmony.tokenize;

import com.itharmony.parse.DocumentGenerator;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.tokenize.TokenizerME;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public final class DocumentTokenizer implements TokenizerInterface {

    public String[] whitespaceTokenize (String text) {
        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
        String tokens[] =  whitespaceTokenizer.tokenize(text);

        return tokens;
    }

    public String[] simpleTokenize (String text) {
        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
        String tokens[] =  simpleTokenizer.tokenize(text);

        return tokens;
    }

    public String[] tokenizerME (String text) {
        InputStream inputStream = null;
        TokenizerModel tokenModel = null;
        try {
            inputStream = new FileInputStream("src/main/resources/OpenNLP_Models/en-token.bin");
            tokenModel = new TokenizerModel(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Instantiating the TokenizerME class
        TokenizerME tokenizer = new TokenizerME(tokenModel);

        //Tokenizing the given raw text
        String tokens[] = tokenizer.tokenize(text);

        return tokens;

    }



}
