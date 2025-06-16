package org.example;

import java.util.Stack;

public class Calculator {

    public static String calculate(String s) {
        int n = s.length();

        Stack<String> stack = new Stack<>();
        StringBuilder temp = new StringBuilder();

        boolean flag = false;

        if (n == 0) {
            stack.push("0");
        } else if (n == 1 && Character.isDigit(s.charAt(0))) {
            temp.append(s.charAt(0));
        } else if (s.startsWith("//")) {
            StringBuilder delimiter = new StringBuilder();
            StringBuilder chkDelimiter = new StringBuilder();
            int i = 2;
            for (; i < n; i++) {
                if (s.charAt(i) != '\n' && !Character.isDigit(s.charAt(i))) {
                    delimiter.append(s.charAt(i));
                } else break;
            }
            while (i < n && s.charAt(i) == '\n') i++;

            while (i < n) {
                while (i < n && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                    temp.append(s.charAt(i));
                    i++;
                }

                while (i < n && s.charAt(i) == '\n') i++;
                if (temp.length() > 0) {
                    stack.push(temp.toString());
                    temp = new StringBuilder();
                }

                while (i < n && s.charAt(i) != '\n' && !Character.isDigit(s.charAt(i))) {
                    chkDelimiter.append(s.charAt(i));
                    i++;
                }
                if (chkDelimiter.length() > 0 && !chkDelimiter.toString().equals(delimiter.toString())) {
                    flag = true;
                    break;
                } else {
                    chkDelimiter = new StringBuilder();
                }
            }

        } else {
            for (int i = 0; i < n; i++) {
                if (temp.length() > 0 && !Character.isDigit(s.charAt(i)) && s.charAt(i) != '.') {
                    stack.push(temp.toString());
                    temp = new StringBuilder();
                }

                if (n >= 2) {
                    if (s.charAt(i) == '\n') {
                        if (temp.length() > 0) {
                            stack.push(temp.toString());
                            temp = new StringBuilder();
                        }
                        continue;
                    } else if (i == n - 1 && s.charAt(i) == ',') {
                        flag = true;
                        break;
                    } else if (i < n - 1 && s.charAt(i) == ',' && (s.charAt(i + 1) == ',' || s.charAt(i + 1) == '\n')) {
                        flag = true;
                        break;
                    } else if (s.charAt(i) == ',') {
                        if (temp.length() > 0) {
                            stack.push(temp.toString());
                            temp = new StringBuilder();
                        }
                    } else if (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.') {
                        temp.append(s.charAt(i));
                    } else {
                        flag = true;
                        break;
                    }
                }
            }
        }

        if (flag) return "invalid input: error";

        if (temp.length() > 0) {
            stack.push(temp.toString());
        }

        double sum = 0.0;
        for (String str : stack) {
            sum += Double.parseDouble(str);
        }

        // ✅ Format output nicely
        String ans = String.format("%.10f", sum).replaceAll("0+$", "").replaceAll("\\.$", "");
        return ans;
    }
}
