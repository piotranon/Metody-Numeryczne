package Lab1;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class Zad1 {

    //Schemat Hornera - wyznaczanie wartości wielomianu w punkcie

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        System.out.println("Podaj wielomian:");
        String expression=sc.nextLine().trim().toLowerCase();

        System.out.println("Podaj wartość x:");
        float x=sc.nextFloat();

        Expression e = new ExpressionBuilder(expression).variables("x").build().setVariable("x", x);
        double result = e.evaluate();

        System.out.println("W(x)="+expression);
        System.out.println("W("+x+")="+result);
    }
}
