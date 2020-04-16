package Lab1;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class Zad9 {
    //Całkowanie numeryczne - metoda parabol
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double poczatek, koniec, n;

        System.out.println("Podaj funkcje");
        String funkcja = sc.nextLine();

        System.out.println("Podaj poczatek przedzialu calkowania");
        poczatek = sc.nextDouble();

        System.out.println("Podaj koniec przedzialu calkowania");
        koniec = sc.nextDouble();

        System.out.println("Podaj ilość podziałów");
        n = sc.nextDouble();

        double odleglosc = (koniec - poczatek) / n;

        double calka = 0;
        double s = 0;
        double x;

        for (int i = 1; i < n; i++) {
            x = poczatek + i * odleglosc;

            Expression f = new ExpressionBuilder(funkcja).variables("x").build().setVariable("x", x - (odleglosc / 2));
            s += f.evaluate();

            Expression fx = new ExpressionBuilder(funkcja).variables("x").build().setVariable("x", x);
            calka += fx.evaluate();
        }

        Expression f = new ExpressionBuilder(funkcja).variables("x").build().setVariable("x", koniec - (odleglosc / 2));
        s += f.evaluate();

        Expression fpoczatek = new ExpressionBuilder(funkcja).variables("x").build().setVariable("x", poczatek);
        Expression fkoniec = new ExpressionBuilder(funkcja).variables("x").build().setVariable("x", koniec);
        calka = (odleglosc / 6) * (fpoczatek.evaluate() + fkoniec.evaluate() + 2 * calka + 4 * s);

        System.out.println("Wartość całki w przybliżeniu: " + calka);
    }
}
