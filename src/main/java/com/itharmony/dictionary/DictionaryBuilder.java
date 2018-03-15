package com.itharmony.dictionary;

import opennlp.tools.util.Span;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public final class DictionaryBuilder {

    public static Map<String, HashSet<CharSequence>> createRegExMap (Span[] entities, String text) {

        Map<String, HashSet<CharSequence>> dictionary = new HashMap<>();

        for (Span span : entities) {
            if (dictionary.containsKey(span.getType())) {
                dictionary.get(span.getType()).add(span.getCoveredText(text));
            }
            else {
                dictionary.put(span.getType(), new HashSet<>());
                dictionary.get(span.getType()).add(span.getCoveredText(text));
            }
        }

        return dictionary;

    }

    public static Map<String, HashSet<CharSequence>> createDictMap (Span[] entities, String[] tokens) {

        Map<String, HashSet<CharSequence>> dictionary = new HashMap<>();

        String key = null;

        for (Span span : entities) {
            if (dictionary.containsKey(span.getType())) {
                key = span.getType();
                continue;
            }
            else {
                dictionary.put(span.getType(), new HashSet<>());
            }
        }

        String[] values = Span.spansToStrings(entities, tokens);

        for (String value : values) {
            dictionary.get(key).add(value);
        }

        return dictionary;

    }



}
