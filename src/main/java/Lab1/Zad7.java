package Lab1;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class Zad7 {
    //Całkowanie numeryczne - metoda trapezów
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        double poczatek,koniec,n;

        System.out.println("Podaj funkcje");
        String funkcja=sc.nextLine();

        System.out.println("Podaj poczatek przedzialu calkowania");
        poczatek = sc.nextDouble();

        System.out.println("Podaj koniec przedzialu calkowania");
        koniec = sc.nextDouble();

        System.out.println("Podaj ilość podziałów");
        n = sc.nextDouble();

        double odleglosc=(koniec-poczatek)/n;

        double calka=0;

        for(int i=1;i<n;i++)
        {
            Expression f=new ExpressionBuilder(funkcja).variables("x").build().setVariable("x",poczatek+i*odleglosc);
            calka+=f.evaluate();
        }
        Expression fpoczatku=new ExpressionBuilder(funkcja).variables("x").build().setVariable("x",poczatek);
        Expression fkoniec=new ExpressionBuilder(funkcja).variables("x").build().setVariable("x",koniec);

        calka += (fpoczatku.evaluate()+fkoniec.evaluate())/2;
        calka *= odleglosc;

        System.out.println("Wartość całki w przybliżeniu: "+calka);
    }
}
