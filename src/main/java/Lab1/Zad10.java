package Lab1;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Zad10 {
    //Rozwiązywanie równań liniowych - eliminacji Gaussa
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

        double wektor[]= new double[n];
        double wektorWynikowy[]= new double[n];

        System.out.println("podaj wektor z wynikami");
        for(int i=0;i<n;i++)
        {
            System.out.println("podaj wartość w "+(i+1)+" wierszu.");
            wektorWynikowy[i]=wektor[i]=sc.nextDouble();
        }

        for (int i = 0; i<n-1; i++){
            for (int j = i+1; j<=n-1; j++){
                for (int k = 0; k<n; k++){
                    macierzWynikowa[j][k]=macierz[j][k]-(macierz[i][k]*(macierz[j][i]/macierz[i][i]));
                }
                wektorWynikowy[j]=wektor[j]-(wektor[i]*(macierz[j][i]/macierz[i][i]));
                for (int l = 0; l<macierzWynikowa.length; l++){
                    for (int k = 0; k<macierzWynikowa.length; k++){
                        macierz[l][k]=macierzWynikowa[l][k];
                    }
                    wektor[l]=wektorWynikowy[l];
                }
            }
        }

        System.out.println("Wynik:");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                boolean space=false;
                if(macierzWynikowa[i][j]>=0)
                    space=true;
                System.out.print(" | "+ ((space) ? ' '+String.format("%.3f", macierzWynikowa[i][j]): String.format("%.3f", macierzWynikowa[i][j])));
            }
            System.out.println(" :: "+wektor[i]);
        }
    }
}
