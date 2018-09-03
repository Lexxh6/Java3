package SnakePattern;

public class Main {
    public static void main(String[] args) {
        int a = 7;
        int b = 4;
        snake(a,b);
    }
    // работает с прозвольной размерностью
    public static void snake(int a,int b){
        int max = a*b;
        int x = 0;
        int y = 0;
        int it = 1;
        int[][] mass = new int[b][a];
        while (it <=max){
            for (int i = x; i < a && it <= max; i++){
                if (mass[y][i] ==0){
                    mass[y][x=i] = it++;
                } else break;
            }
            ++y;
            for (int i = y; i < b && it <= max; i++){
                if (mass[i][x] ==0){
                    mass[y=i][x] = it++;
                } else break;
            }
            --x;
            for (int i = x; i > -1 && it <= max; i--){
                if (mass[y][i] ==0){
                    mass[y][x=i] = it++;
                } else break;
            }
            --y;
            for (int i = y; i > -1 && it <= max; i--){
                if (mass[i][x] ==0){
                    mass[y=i][x] = it++;
                } else break;
            }
            ++x;
        }
        for (int[] m: mass) {
            for (int q: m) System.out.print("\t"+q);;
            System.out.println("");
        }
    }
}
