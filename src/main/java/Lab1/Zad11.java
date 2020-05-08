package Lab1;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Zad11 {
    //Rozwiązywanie równań liniowych - rozkład Lu
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("podaj rozmiar macierzy:");
        int n=sc.nextInt();

        double macierz[][] =new double[n][n];
        double macierzWynikowa[][] =new double[n][n];
        for(int i=0;i<n;i++) {
            System.out.println("podaj wartości w "+(i+1)+" wierszu.");
            for (int j = 0; j < n; j++) {
                System.out.println("podaj wartość w "+(j+1)+" kolumnie.");
                macierzWynikowa[i][j]=macierz[i][j]=sc.nextDouble();
            }
        }

        System.out.println("Macierz do rozwiązania: ");

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                boolean space=false;
                if(macierz[i][j]>=0)
                    space=true;
                System.out.print(" | "+ ((space) ? ' '+String.format("%.3f", macierz[i][j]): String.format("%.3f", macierz[i][j])));
            }
            System.out.print(" |");
            System.out.println();
        }

        //podzielenie macierzy na górny i dolny trójkąt

        double [][]dolny = new double[n][n];
        double [][]gorny = new double[n][n];

        for(int i=0;i<n;i++)
        {
            //gorny trojkat
            for(int k=i;k<n;k++)
            {
                int sum=0;
                for(int j=0;j<k;j++)
                    sum+=(dolny[i][j]*gorny[j][k]);

                gorny[i][k]= macierz[i][k]-sum;
            }

            //dolny trojkat
            for(int k=i;k<n;k++)
            {
                if(i == k)
                    dolny[i][i]=1;
                else
                {
                    int sum=0;
                    for(int j=0;j<i;j++)
                        sum+=(dolny[k][j]* gorny[j][i]);
                    dolny[k][i]=(macierz[k][i] - sum) / gorny[i][i];
                }
            }
        }

        System.out.println("Górny Trójkat");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                boolean space=false;
                if(gorny[i][j]>=0)
                    space=true;
                System.out.print(" | "+ ((space) ? ' '+String.format("%.3f", gorny[i][j]): String.format("%.3f", gorny[i][j])));
            }
            System.out.print(" |");
            System.out.println();
        }

        System.out.println("Dolny Trójkat");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                boolean space=false;
                if(dolny[i][j]>=0)
                    space=true;
                System.out.print(" | "+ ((space) ? ' '+String.format("%.3f", dolny[i][j]): String.format("%.3f", dolny[i][j])));
            }
            System.out.print(" |");
            System.out.println();
        }

    }
}
