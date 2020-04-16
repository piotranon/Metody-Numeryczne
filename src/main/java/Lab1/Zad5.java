package Lab1;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Scanner;

public class Zad5 {
    //Rozwiązywanie równań nieliniowych - metoda siecznych
    public static void main(String[] args) {
        System.out.println("funkcja f oraz jej pierwsza i druga pochodna są ciągłe w badanym przedziale <a, b>,");
        System.out.println("wewnątrz <a, b> znajduje się dokładnie jeden pierwiastek,");
        System.out.println("pierwsza i druga pochodna mają stały znak w badanym przedziale <a ,b>.");
        System.out.println();
        System.out.println("Podaj wielomian");

        Scanner sc = new Scanner(System.in);
        String wielomian = sc.nextLine();

        String pochodna1stopnia=pochodna(wielomian.replaceAll("-","\\+-"));
        String pochodna2stopnia=pochodna(pochodna1stopnia.replaceAll("-","\\+-"));

        double y=0;
        double z=0;

        //wczytujemy przedział
        boolean rozneznaki=false;
        while (!false) {
            System.out.println("Podaj kraniec przedziału a:");
            y = sc.nextFloat();
            System.out.println("Podaj kraniec przedziału b:");
            z = sc.nextFloat();

            //podmieniamy a z wartoscia mniejsza
            if (y > z) {
                double temp = y;
                y = z;
                z = temp;
            }
            System.out.println("a:" + y);
            System.out.println("b:" + z);


            Expression fa = new ExpressionBuilder(wielomian).variables("x").build().setVariable("x", y);
            Expression fb = new ExpressionBuilder(wielomian).variables("x").build().setVariable("x", z);


            //sprawdzamy czy funkcja ma rozne znaki na krancach przedziałów
            double rozne = fa.evaluate() * fb.evaluate();
            if (rozne < 0) {
                break;
            }
            System.out.println("Na przedziale nie ma pierwiastka");
            System.out.println("Podaj krance przedziałów ponownie");
        }
        System.out.println("Podaj ilość iteracji: ");
        int  iteracje=sc.nextInt();

        Expression fp=new ExpressionBuilder(pochodna1stopnia).variables("x").build().setVariable("x",y);
        Expression sp=new ExpressionBuilder(pochodna2stopnia).variables("x").build().setVariable("x",y);

        double c;
        double d=0;
        //jesli pochodna 1 stopnia razy pochodnia 2 stopnia < 0
        //zaczynamy iteracje od b
        if(fp.evaluate()*sp.evaluate()<0)
        {
            c=z;
            Expression wc=new ExpressionBuilder(wielomian).variables("x").build().setVariable("x",c);
            Expression wy=new ExpressionBuilder(wielomian).variables("x").build().setVariable("x",y);
            c=c-(wc.evaluate()/(wy.evaluate()-wc.evaluate()))*(y-c);
        }
        else
        {
            c=y;
            Expression wc=new ExpressionBuilder(wielomian).variables("x").build().setVariable("x",c);
            Expression wz=new ExpressionBuilder(wielomian).variables("x").build().setVariable("x",z);
            c=c-(wc.evaluate()/(wz.evaluate()-wc.evaluate()))*(z-c);
        }

        double e;
        for(int i=0;i<iteracje;i++)
        {
            Expression wc=new ExpressionBuilder(wielomian).variables("x").build().setVariable("x",c);
            Expression wd=new ExpressionBuilder(wielomian).variables("x").build().setVariable("x",d);

            if((wc.evaluate()==0)||(wc.evaluate()-wd.evaluate()==0))
                break;

            e = c;
            c = c-((wc.evaluate()*(c-d))/(wc.evaluate()-wd.evaluate()));
            d = e;
        }

        Expression wc=new ExpressionBuilder(wielomian).variables("x").build().setVariable("x",c);

        if(wc.evaluate()==0)
        {
            System.out.println("Dokładny pierwiastek wynosi: "+c);
        }else
        {
            System.out.println("Przybliżony pierwiastek wynosi: "+c);
        }
    }
    public static String pochodna(String wielomian)
    {
        //dzielimy wielomian co +
        String[] podz=wielomian.split("\\+");

        StringBuilder poch=new StringBuilder();

        for(int j=0;j<podz.length;j++)
        {
            String[] sub =podz[j].split("x");

            for(int i=0;i<sub.length-1;i++)
            {
                poch.append("("+sub[i]+"*"+String.valueOf(Float.parseFloat(sub[i+1].substring(1))-1)+")x^"+String.valueOf(Float.parseFloat(sub[i+1].substring(1))-1)+"+");
            }
        }

        poch.deleteCharAt(poch.length()-1);
        return poch.toString().replaceAll("\\*","");
    }
}
