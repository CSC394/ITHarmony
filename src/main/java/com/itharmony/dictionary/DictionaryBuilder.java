package com.itharmony.dictionary;

import opennlp.tools.util.Span;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public final class DictionaryBuilder {

    public static Map<String, HashSet<CharSequence>> createMap (Span[] entities, String text) {

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
}
