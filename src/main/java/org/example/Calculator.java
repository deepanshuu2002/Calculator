package org.example;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static String calculate(String s) {
        if (s.isEmpty()) {
            return "0";
        }

        // 1) Extract optional custom delimiter
        String delimiter = ",";
        int startIndex = 0;
        if (s.startsWith("//")) {
            int nl = s.indexOf('\n');
            if (nl == -1) {
                return "Invalid delimiter format";
            }
            delimiter   = s.substring(2, nl);
            startIndex  = nl + 1;
        }

        List<String> errors  = new ArrayList<>();
        List<Double> numbers = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean lastWasSep = false;

        int i = startIndex;
        while (i < s.length()) {

            // full‐delimiter match?
            if (!delimiter.isEmpty() && s.startsWith(delimiter, i)) {
                if (lastWasSep) {
                    errors.add("Number expected but '" + delimiter +
                            "' found at position " + i + ".");
                }
                finishToken(current, errors, numbers);
                current.setLength(0);
                i += delimiter.length();
                lastWasSep = true;

                // newline
            } else if (s.charAt(i) == '\n') {
                if (lastWasSep) {
                    errors.add("Number expected but '\\n' found at position " + i + ".");
                }
                finishToken(current, errors, numbers);
                current.setLength(0);
                i++;
                lastWasSep = true;

                // digit, minus or dot → accumulate a number token
            } else if (Character.isDigit(s.charAt(i)) ||
                    s.charAt(i) == '-' ||
                    s.charAt(i) == '.') {
                current.append(s.charAt(i));
                i++;
                lastWasSep = false;

                // anything else is an unexpected‐char error
            } else {
                finishToken(current, errors, numbers);
                current.setLength(0);
                errors.add("'" + delimiter +
                        "' expected but '" + s.charAt(i) +
                        "' found at position " + i + ".");
                i++;
                lastWasSep = false;
            }
        }

        // ended on a separator → EOF error; otherwise flush last token
        if (lastWasSep) {
            errors.add("Number expected but EOF found.");
        } else {
            finishToken(current, errors, numbers);
        }

        // if any errors, return them in the exact order
        if (!errors.isEmpty()) {
            return String.join("\n", errors);
        }

        // otherwise sum and format without trailing zeros
        double sum = 0;
        for (double d : numbers) {
            sum += d;
        }
        String formatted = String.format("%.10f", sum)
                .replaceAll("0+$", "")
                .replaceAll("\\.$", "");
        return formatted;
    }

    // Flushes the current token: reports negative‐number or collects positive
    private static void finishToken(StringBuilder cur,
                                    List<String> errors,
                                    List<Double> numbers) {
        if (cur.length() == 0) return;
        String tok = cur.toString();
        if (tok.startsWith("-")) {
            errors.add("Negative not allowed : " + tok);
        } else {
            numbers.add(Double.parseDouble(tok));
        }
    }
}