package com.cb.calculator;
import java.text.DecimalFormat;
import java.util.Stack;
import  java.util.Scanner;
public class TestStack {
    public static void main(String[] args){
//        InfixToPostfix("");

    }
    public static String InfixToPostfix(String inFix_Str){
        //https://youtu.be/JApN6H9Muh0?si=knK7p3_G64_cocnk
//        String inFix_Str = "((0.6+0.9)-5)/(31-56)";
        Stack<Character> st = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        int i = 0;
        while(i < inFix_Str.length()){
            char c =  inFix_Str.charAt(i);
            StringBuilder operand = new StringBuilder();
            if (Character.isDigit(c)){
                // cái này để kiểm tra các số từ hàng chục trở lên
                while(i < inFix_Str.length() && (Character.isDigit(inFix_Str.charAt(i)) || inFix_Str.charAt(i) == '.')){
                    operand.append(inFix_Str.charAt(i));
                    i++;
                }
                postfix.append(operand).append(" ");
            }
            else if (c == '('){
                st.push(c);
                i++;
            }
            else if (c == ')'){

                // nếu stack không rỗng và phần tử đầu của stack không bằng ( thì pop đi "("
                while (!st.isEmpty() && st.peek() != '(') {
                    postfix.append(st.pop()).append(" ");
                }
                st.pop();
                i++;
            }
            else{
                // precedence ở đây là độ ưu tiên của toán tử
                // nếu toán tử không rỗng và toán tử đang xét nhỏ hơn hoặc bằng toán tử trong stack thì thêm vào postfix
                // ngược lại nếu không cố cái nào thoả mãn ta sẽ thêm vào stack
                while (!st.isEmpty() && precedence(c)<=precedence(st.peek())){
                    postfix.append(st.pop()).append(" ");
                }
                st.push(c);
                i++;
            }

        }
        while (!st.isEmpty()) {
            postfix.append(st.pop()).append(" ");
        }
        System.out.println(postfix);
        CalculatePostfix(postfix.toString());
        return postfix.toString();
    }
    public static  int precedence(char c){
        switch (c){
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            default: return -1;
        }

    }
    public static double CalculatePostfix(String postfix){
//        34+5-21-/
//        75-21-/
//        221-/
//        21/
        Stack<Double> st = new Stack<>();
        String[] postfix_arr  = postfix.split(" ");
        String operator  = "+-*/";

        double result  = 0;

        for (int i = 0; i < postfix_arr.length; i++){
            String c = postfix_arr[i];

            if (!operator.contains(c)){

                st.push(Double.parseDouble(c));
            }
            else{
                double b = st.pop();
                double a = st.pop();
                result = operatorAB(c, a, b);
                st.push(result);

            }
        }
//        4545+
        System.out.println(result);

        return result;
    }

    public static double operatorAB(String op, double a, double b){
        switch (op){
            case "+":return a+b;
            case "-":return a-b;
            case "*":return a*b;
            case "/":return a/b;
            default: return  0;
        }


    }
    public static void BinaryToDecimal(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so can chuyen doi: ");
        int number = sc.nextInt();
        Stack<String> st = new Stack<String>();
        while (number>0){
            if (number%2==0){
                st.push("0");
            }
            else{
                st.push("1");
            }
            number = (int) number/2;
        }
        System.out.println("Sau khi chuyen doi: ");
        while (!st.isEmpty()){
            System.out.print(st.pop());
        }
    }
    public static void DecimalTobinary(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so nhi phan chuyen doi: ");
        String str_binary = sc.nextLine();
        //11101
        //(1*2^4+1*2^3+1*2^2+0*2^1+1*2^0) 16+8+4+0+1
        int kq = 0;
        int mu = str_binary.length()-1;
        for(int i=0; i < str_binary.length(); ++i){
            int p = Character.getNumericValue(str_binary.charAt(i));
//            int somu = 1;
//            for(int j=0; j<mu; ++j){
//                somu *= 2;
//            }
            int somu = (int) Math.pow(2, mu);
            kq += p*somu;
            --mu;
        }


        System.out.println(kq);
    }
}
