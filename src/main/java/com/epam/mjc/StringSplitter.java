package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> substrings = new ArrayList<>();
        StringBuilder currentSubstring = new StringBuilder();
        boolean foundDelimiter = false;

        for (char c : source.toCharArray()){
            String charString = String.valueOf(c);
            if (delimiters.contains(charString)){
                substrings.add(currentSubstring.toString());
                currentSubstring.setLength(0);
                foundDelimiter = true;
            }else {
                currentSubstring.append(c);
                foundDelimiter = false;
            }
        }
        if (!foundDelimiter || currentSubstring.length() > 0){
            substrings.add(currentSubstring.toString());
        }

        return substrings;
    }
}
