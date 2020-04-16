package Lab1;

import java.util.Scanner;

public class Zad2 {
    //Schemat Hornera - dzielenie wielomianu przez dwumian
    public static void main(String[] args) {

        System.out.println("znakiem rozgraniczającym kolejne argumenty jest +");
        System.out.println("metoda nie obsluguje funkcji trygonometrycznych");
        System.out.println("nie mozna pominac żadnego wyrazu np. jeśli mamy wzór");
        System.out.println("x^5+2x^3-6x^2+2  musimy go podać : x^5+0x^4+2x^3+-6x^2+2");
        System.out.println("");
        System.out.println("Podaj wielomian:");

        Scanner sc=new Scanner(System.in);
        String wielomian= sc.nextLine();

//        String wielomian="2x^3+-5x^2+4x+-1";
        System.out.println("Podaj dwumian :");
        String dwumian = sc.nextLine();
//        String dwumian="x-1";

        //tworzenie tabelki
        String[] wielomianwczesciach=wielomian.trim().toLowerCase().split("\\+");
        float[][] wspolczynniki =new float[wielomianwczesciach[0].length()][2];
        for(int i=0;i<wspolczynniki.length;i++)
        {
            String wsp=wielomianwczesciach[i].split("x")[0];
            if(wsp.length()==0)
                wsp="1";

            wspolczynniki[i][0]=Integer.parseInt(wsp);
//            System.out.println(wspolczynniki[i][0]);
        }

        dwumian=rotate(dwumian);
//        System.out.println(dwumian);

        float liczbaSama;
        boolean pierwszyminus=false;

        if(dwumian.indexOf("-")>=0 && dwumian.indexOf("+") >=0 && dwumian.indexOf("-")<dwumian.indexOf("+"))
            pierwszyminus=true;
        else if(dwumian.indexOf("-")>=0 && dwumian.indexOf("+")<0)
            pierwszyminus=true;

        if(pierwszyminus)
        {
//            System.out.println(":::"+dwumian.trim().toLowerCase().split("\\-")[0]);
//            System.out.println(":"+Integer.valueOf(dwumian.trim().toLowerCase().split("\\-")[0]));
            liczbaSama=-Integer.valueOf(dwumian.trim().toLowerCase().split("\\-")[0]);
        }
        else
        {
//            System.out.println("::"+dwumian.trim().toLowerCase().split("\\+")[0]);
//            System.out.println(":"+Integer.valueOf(dwumian.trim().toLowerCase().split("\\+")[0]));
            liczbaSama=Integer.valueOf(dwumian.trim().toLowerCase().split("\\+")[0]);
        }

//        System.out.println("sama:"+liczbaSama);
        dwumian=rotate(dwumian);
//        System.out.println("po samejliczbuie: "+dwumian);

        float liczbaPrzyX;
        if(dwumian.trim().toLowerCase().split("x")[0].length()==0)
            liczbaPrzyX=1;
        else
            liczbaPrzyX=Integer.valueOf(dwumian.trim().toLowerCase().split("x")[0]);
//        System.out.println("liczba przy x: "+liczbaPrzyX);

        float liczbaZerujacaDwumian=(-liczbaSama/liczbaPrzyX);
//        System.out.println("liczba zerujaca dwumian:"+liczbaZerujacaDwumian);

//        System.out.println(dwumian.trim().toLowerCase().split("x")[0]);

//        System.out.println("liczba zerujaca dwumian :"+liczabaZerujacaDwumian);

        //przepisanie pierwszej wlasnosci
        wspolczynniki[0][1]=wspolczynniki[0][0];
        for(int i=1;i<wspolczynniki.length;i++)
        {
            wspolczynniki[i][1]=liczbaZerujacaDwumian*wspolczynniki[i-1][1]+wspolczynniki[i][0];
        }

        // tabelka
        System.out.println("TABELKA");
        for(int j=0;j<2;j++)
        {
            for(int i=0;i<wspolczynniki.length;i++)
            {
                System.out.print(wspolczynniki[i][j]+" | ");
            }
            System.out.println("");
            System.out.println("----------------------------------");
        }

        StringBuilder rozwiazanie=new StringBuilder();

        for(int i=0;i<=wspolczynniki[1].length;i++)
        {
            rozwiazanie.append(wspolczynniki[i][1]+"x^"+(wspolczynniki[1].length-i)+"+");
        }
        //Wypisanie rozwiązania
        rozwiazanie.deleteCharAt(rozwiazanie.length()-1);
        System.out.println("Rozwiązanie:");
        System.out.println("( "+wielomian+" ) : ( "+dwumian+" ) = "+rozwiazanie.toString());
    }
    public static String rotate(String in)
    {
        StringBuilder out = new StringBuilder();
        for(int i=in.length()-1;i>=0;i--)
            out.append(in.charAt(i));

        return out.toString();
    }
}
