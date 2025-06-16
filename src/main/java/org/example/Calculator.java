package org.example;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static String calculate(String s) {
        if (s.isEmpty()) return "0";

        String delimiter = ",";
        int startIndex = 0;

        // Check for custom delimiter
        if (s.startsWith("//")) {
            int newlineIndex = s.indexOf('\n');
            if (newlineIndex == -1) return "Invalid delimiter format";

            delimiter = s.substring(2, newlineIndex);
            startIndex = newlineIndex + 1;
        }

        List<String> numbers = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();

        boolean lastWasDelimiter = false;
        for (int i = startIndex; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '\n' || s.startsWith(delimiter, i)) {

                if (lastWasDelimiter) {
                    if (c == '\n') {
                        return "Number expected but '\\n' found at position " + i + ".";
                    } else {
                        return "Number expected but '" + delimiter + "' found at position " + i + ".";
                    }
                }

                if (currentNumber.length() > 0) {
                    numbers.add(currentNumber.toString());
                    currentNumber.setLength(0);
                } else if (c == '\n') {
                    return "Number expected but '\\n' found at position " + i + ".";
                }

                if (s.startsWith(delimiter, i)) {
                    i += delimiter.length() - 1;
                }
                lastWasDelimiter = true;
            } else if (Character.isDigit(c) || c == '-' || c == '.') {
                currentNumber.append(c);
                lastWasDelimiter = false;
            } else {
                return "'" + delimiter + "' expected but '" + c + "' found at position " + i + ".";
            }
        }

        if (lastWasDelimiter) {
            return "Number expected but EOF found.";
        }
        if (currentNumber.length() > 0) {
            numbers.add(currentNumber.toString());
        }

        // Check for negatives
        List<String> negatives = new ArrayList<>();
        double sum = 0.0;
        for (String numStr : numbers) {
            if (!numStr.isEmpty()) {
                double num = Double.parseDouble(numStr);
                if (num < 0) negatives.add(numStr);
                sum += num;
            }
        }

        if (!negatives.isEmpty()) {
            return "Negative not allowed : " + String.join(", ", negatives);
        }

        String ans = String.format("%.10f", sum).replaceAll("0+$", "").replaceAll("\\.$", "");
        return ans;
    }
}
