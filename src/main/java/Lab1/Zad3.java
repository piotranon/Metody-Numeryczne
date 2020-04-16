package Lab1;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

import static java.lang.StrictMath.abs;

public class Zad3 {
    //Rozwiązywanie równań nieliniowych - metoda bisekcji
    public static void main(String[] args) {

        System.out.println("Podaj wielomian");

        Scanner sc=new Scanner(System.in);
        String wielomian = sc.nextLine();

        System.out.println("Podaj kraniec przedziału a:");
        float a=sc.nextFloat();
        System.out.println("Podaj kraniec przedziału b:");
        float b=sc.nextFloat();

        //podmieniamy a z wartoscia mniejsza
        if(a>b)
        {
            float temp=a;
            a=b;
            b=temp;
        }
        System.out.println("a:"+a);
        System.out.println("b:"+b);
        //sprawdzamy czy krance przedziałów maja różne znaki
        if(a<b)
            if(a>0)
            {
                if(b>0)
                {
                    System.out.println("bledne krance przedziałow");
                    return;
                }
            }else if(a<0)
            {
                if(b<0)
                {
                    System.out.println("bledne krance przedziałow");
                    return;
                }
            }
        int opcja;
        while(true)
        {
            System.out.println("która opcje wybierasz:");
            System.out.println("1 - wybrana ilosc iteracji");
            System.out.println("2 - do wybranego przyblizenia");
            opcja=sc.nextInt();

            if(opcja==1||opcja==2)
                break;
        }

        if(opcja==1)
        {
            System.out.println("Ilość iteracji:");
            int iteracje=sc.nextInt();
            System.out.println("");
            System.out.println("NR | A | B | środek | wartość funkcji srodka");
            System.out.println("--------------------------------------");
            for(int i=0;i<iteracje;i++)
            {
                float srodek=(a+b)/2;
                Expression fx = new ExpressionBuilder(wielomian).variables("x").build().setVariable("x", srodek);
                double pierw = fx.evaluate();

                System.out.println(i+1+" | "+a+ " | "+b+ " | "+srodek+" | "+pierw);
                if(i==iteracje-1)
                {
                    System.out.println("--------------------------------------");
                    System.out.println("Wartość przybliżenia w punkcie "+srodek+" = "+abs(pierw));
                }

                Expression ax = new ExpressionBuilder(wielomian).variables("x").build().setVariable("x", a);
                double pierwA = ax.evaluate();

                Expression bx = new ExpressionBuilder(wielomian).variables("x").build().setVariable("x", b);
                double pierwB = bx.evaluate();

                if(pierw*pierwA<0)
                {
                    b=srodek;
                }else if(pierw*pierwB<0)
                {
                    a=srodek;
                }
            }
        }else if(opcja==2)
        {
            System.out.println("Dokładność epsilon (przecinek):");
            double dokladnosc=sc.nextDouble();
            System.out.println("");
            System.out.println("NR | A | B | środek | wartość funkcji srodka");
            System.out.println("--------------------------------------");
            int nr=1;
            while (true)
            {
                float srodek=(a+b)/2;

                Expression fx = new ExpressionBuilder(wielomian).variables("x").build().setVariable("x", srodek);
                double pierw = fx.evaluate();

                if(abs(pierw)<dokladnosc)
                {
                    System.out.println("--------------------------------------");
                    System.out.println("Wartość przybliżenia w punkcie "+srodek+" = "+abs(pierw)+" co jest mniejsze od zadanego epsilon : "+dokladnosc);
                    break;
                }

                System.out.println(nr+" | "+a+ " | "+b+ " | "+srodek+" | "+pierw);
                nr++;

                Expression ax = new ExpressionBuilder(wielomian).variables("x").build().setVariable("x", a);
                double pierwA = ax.evaluate();

                Expression bx = new ExpressionBuilder(wielomian).variables("x").build().setVariable("x", b);
                double pierwB = bx.evaluate();

                if(pierw*pierwA<0)
                {
                    b=srodek;
                }else if(pierw*pierwB<0)
                {
                    a=srodek;
                }
            }
        }





    }
}
