package ru.otus.algoritm;

import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        /*long time = System.currentTimeMillis();
        long a = 1234567890;
        long b = 12;
        System.out.println("НОД a=" + a + " и  b=" + b + ", " + workTwo(a, b));
        System.out.println(System.currentTimeMillis() - time);*/
        /*long time = System.currentTimeMillis();
        System.out.println(expTwo(8, 13));
        System.out.println("time " + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        System.out.println(expThree(8, 13));
        System.out.println("time " + (System.currentTimeMillis() - time));*/
        /*long time = System.currentTimeMillis();
        fibbonachi(1200);
        System.out.println(System.currentTimeMillis() - time);*/

        /*long time = System.currentTimeMillis();
        System.out.println(searchTwo(10000));
        System.out.println("time " + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        System.out.println(searchThree(10000));
        System.out.println("time " + (System.currentTimeMillis() - time));*/

        System.out.println(fibonachchi(10));
        System.out.println(fibonachchiReq(10));
        System.out.println(fibonachchiGold(10));
    }

    private static long work(long a, long b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    private static long workTwo(long a, long b) {
        while (a != 0 && b != 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return a;
    }

    /**
     * степень через степень двойки с домножением
     */
    private static long expTwo(int a, int b) {
        long result = 1;
        while (b > 1) {
            if (b % 2 == 1) {
                result *= a;
            }
            a *= a;
            b /= 2;
        }
        if (b > 0)
            result *= a;
        return result;
    }

    /**
     * степень через двоичное разложение показателя степени
     */
    private static long expThree(int a, int b) {
        int[] bits = new int[8];
        boolean isBegin = true;
        for (int i = 7; i >= 0; i--) {
            int c = b & 1;
            bits[i] = c;
            b = b >> 1;
        }

        int z = 1;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] == 0 && isBegin) {
                continue;//пропускаем первые 0 из битового представления числа
            } else {
                isBegin = false;
            }
            if (bits[i] == 0) {
                z *= z;
            } else {
                z *= z;
                z *= a;
            }
        }
        return z;
    }

    /**
     * Решето Эратосфена через перебор делителей
     */
    private static List<Integer> searchOne(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int k = 0;
            for (int j = i; j >= 1; j--) {
                if (i % j == 0 && k <= 2) {
                    k++;
                }
            }
            if (k == 2)
                list.add(i);
        }
        return list;
    }

    /**
     * Решето Эратосфена
     */
    private static List<Integer> searchTwo(int n) {
        boolean[] a = new boolean[n + 1];
        for (int i = 0; i < a.length; i++) {
            a[i] = true;
        }

        for (int i = 2; i * i <= n; i++) {
            if (a[i]) {
                for (int j = i * i; j <= n; j += i) {
                    a[j] = false;
                }
            }
        }
        List<Integer> list = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (a[i]) list.add(i);
        }
        return list;
    }

    /**
     * Решето Эратосфена с битовой матрицей
     */
    private static List<Integer> searchThree(int n) {
        int[] a = new int[n / 8 + 1];
        for (int i = 0; i <= n / 8; i++) {
            a[i] = 0xff;
        }
        List<Integer> list = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            int flag = a[i / 8] & (0x80 >> i % 8);
            if (flag != 0) {
                for (int j = i * i; j <= n; j += i) {
                    a[j / 8] &= ~0x80 >> j % 8;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            int flag = a[i / 8] & (0x80 >> i % 8);
            if (flag >= 1) list.add(i);
        }
        return list;
    }

    private static long fibonachchiReq(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonachchiReq(n - 1) + fibonachchiReq(n - 2);
        }
    }

    private static int fibonachchi(int n) {
        int n0 = 1, n1 = 1, n2 = 0;
        //System.out.print(n0 + " " + n1 + " ");
        for (int i = 0; i < n - 2; i++) {
            n2 = n0 + n1;
            //System.out.print(n2 + " ");
            n0 = n1;
            n1 = n2;
        }
        return n2;
    }

    private static double fibonachchiGold(int n) {
        double f = (1 + Math.sqrt(5)) / 2;
        return (Math.pow(f, n) / Math.sqrt(5)) + 1 / 2;
    }
}
