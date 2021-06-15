package com.alyrow.gdx.particle.utilities;

import java.util.stream.Stream;

public class CUD {

    public static String sentenceConversion(String inputString) {
        if (inputString == null) return "Null";

        if (inputString.isEmpty()) return inputString;

        if (inputString.length() == 1) return inputString.toUpperCase();

        inputString = inputString.replaceAll("[^a-zA-Z0-9]", " ")
                .replaceAll("[^a-zA-Z ]", "")
                .replaceAll("[ ]{2,}", "");

        StringBuffer resultPlaceHolder = new StringBuffer(inputString.length());

        Stream.of(inputString.split(" ")).forEach(stringPart ->
        {
            if (stringPart.length() > 1)
                resultPlaceHolder.append(stringPart.substring(0, 1)
                        .toUpperCase())
                        .append(stringPart.substring(1)
                                .toLowerCase());
            else
                resultPlaceHolder.append(stringPart.toUpperCase());

            resultPlaceHolder.append(" ");
        });
        return resultPlaceHolder.toString().trim();
    }

}
