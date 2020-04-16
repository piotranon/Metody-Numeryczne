package Lab1;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Zad4 {
    //Rozwiązywanie równań nieliniowych - metoda stycznych
    public static void main(String[] args) {
        System.out.println("funkcja f oraz jej pierwsza i druga pochodna są ciągłe w badanym przedziale <a, b>,");
        System.out.println("wewnątrz <a, b> znajduje się dokładnie jeden pierwiastek,");
        System.out.println("f(a)*f(b) < 0,");
        System.out.println("pierwsza i druga pochodna mają stały znak w badanym przedziale <a ,b>.");
        System.out.println();
        System.out.println("Podaj wielomian");

        Scanner sc = new Scanner(System.in);
        String wielomian = sc.nextLine();

        String pochodna1stopnia = pochodna(wielomian.replaceAll("-", "\\+-"));
        String pochodna2stopnia = pochodna(pochodna1stopnia.replaceAll("-", "\\+-"));

        double y = 0;
        double z = 0;

        //wczytujemy przedział
        boolean rozneznaki = false;
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

        Expression wiel=new ExpressionBuilder(wielomian).variables("x").build().setVariable("x",y);
        Expression poch2=new ExpressionBuilder(pochodna2stopnia).variables("x").build().setVariable("x",y);

        double c;

        //jesli pochodnia 2 stopnia * wielomian >0
        //to iterujemy od a
        //jesli nie to iterujemy od b
        if(poch2.evaluate()*wiel.evaluate()>0)
            c=y;
        else
            c=z;

        System.out.println("c:"+c);
        for(int i=0;i<iteracje;i++)
        {
            Expression w=new ExpressionBuilder(wielomian).variables("x").build().setVariable("x",c);
            Expression wp=new ExpressionBuilder(pochodna1stopnia).variables("x").build().setVariable("x",c);

            c=c-(w.evaluate()/wp.evaluate());

            if(w.evaluate()==0)
                break;
        }

        Expression w=new ExpressionBuilder(wielomian).variables("x").build().setVariable("x",c);

        if(w.evaluate()==0)
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
