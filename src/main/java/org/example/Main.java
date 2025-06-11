package org.example;
import java.util.Stack;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String s = "\n3,4\n,6\n\n10.7\n";
        int n = s.length();

        Stack<String> stack = new Stack<>();
        StringBuilder temp = new StringBuilder();

        boolean flag = false;
        if(n==0) {
            stack.push("0");
        }
        else if(n==1 && (int)s.charAt(0) >= 48 && (int)s.charAt(0) <= 57){
            temp.append(s.charAt(0));
        }
        else {


            for (int i = 0; i < n; i++) {

                if(temp.length()>0 && ((int)s.charAt(i) < 48 || (int)s.charAt(i) > 57) ){
                    stack.push(temp.toString());
                    temp = new StringBuilder();
                }

                if (n >= 2) {
                    if (s.charAt(i) == '\n') {
                        if(temp.length()>0){
                            stack.push(temp.toString());
                            temp = new StringBuilder();
                        }
                        continue;
                    }
                    else if(i == n - 1 && s.charAt(i) == ',') {
                        flag = true;
                        break;
                    }
                    else if(i<n-1 && s.charAt(i) == ',' && ((s.charAt(i+1) == ',') || (s.charAt(i+1) == '\n'))){
                        flag = true;
                        break;
                    }
                    else if (s.charAt(i) == ',') {
                        if(temp.length()>0){
                            stack.push(temp.toString());
                            temp = new StringBuilder();
                        }
                    }
                    else if ( ((int) s.charAt(i) >= 48 && (int) s.charAt(i) <= 57 )|| (s.charAt(i) == '.')) {
                        temp.append(s.charAt(i));
                    }
                    else{
                        flag = true;
                        break;
                    }
                }
            }


        }
        if(flag) System.out.println("invalid input: error");

        else{
            //if temp is not empty
            if(temp.length()>0){
                stack.push(temp.toString());
                temp = new StringBuilder();
            }

            Stack<Double> doubleStack = new Stack<>();

            for (String str : stack) {
                doubleStack.push(Double.parseDouble(str));  // Convert String → double
            }
            Double sum = 0.0;
            for( Double x : doubleStack) {
                sum += x;
            }
            String ans = Double.toString(sum);
            System.out.println(ans);

        }


    }
}