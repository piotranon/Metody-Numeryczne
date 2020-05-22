package Lab1;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Zad12 {
    //aproksymacja wielomianowa - metoda najmniejszych kwadratów
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("podaj ilość punktów:");
        int n=sc.nextInt();
        double punkty[][] = new double[n][2];

        for(int i=0;i<n;i++)
        {
            System.out.println("Podaj wspolrzedna x punktu "+(i+1));
            punkty[i][0]=sc.nextDouble();
            System.out.println("Podaj wspolrzedna y punktu "+(i+1));
            punkty[i][1]=sc.nextDouble();
        }

        double Sx=0;
        double Sy=0;
        double Sxx=0;
        double Sxy=0;

        for(int i=0;i<n;i++)
        {
            Sx=Sx+punkty[i][0];
            Sy=Sy+punkty[i][1];
            Sxx=Sxx+punkty[i][0]*punkty[i][0];
            Sxy=Sxy+punkty[i][0]*punkty[i][1];

//            System.out.println("Sx: "+Sx);
//            System.out.println("Sy: "+Sy);
//            System.out.println("Sxx: "+Sxx);
//            System.out.println("Sxy: "+Sxy);
        }
        double delta=Sx*Sx-Sxx*n;
        double a=(Sx*Sy-Sxy*n)/delta;
        double b=(Sx*Sxy-Sxx*Sy)/delta;

        System.out.println("delta: "+delta);
        System.out.println("a: "+a);
        System.out.println("b: "+b);
    }


}
