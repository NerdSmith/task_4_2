package ru.vsu.cs;

public class SmoothSort {
    private static final int[] leonardoNumbers = {1, 1, 3, 5, 9, 15, 25, 41, 67, 109,
            177, 287, 465, 753, 1219, 1973, 3193, 5167, 8361, 13529, 21891,
            35421, 57313, 92735, 150049, 242785, 392835, 635621, 1028457,
            1664079, 2692537, 4356617, 7049155, 11405773, 18454929, 29860703,
            48315633, 78176337, 126491971, 204668309, 331160281, 535828591,
            866988873};

    public static <T extends Comparable<T>> void sort(T[] data) {
        if(data.length - 1 > 7049156 ){
            throw new IllegalArgumentException("Array to big to sort using this method");
        }

        int head = 0;
        int p = 1;
        int pshift = 1;

        while (head < data.length - 1) {
            if ((p & 3) == 3) {
                sift(data, pshift, head);
                p >>>= 2;
                pshift += 2;
            }
            else {
                if (leonardoNumbers[pshift - 1] >= data.length - 1 - head) {
                    trinkle(data, p, pshift, head, false);
                }
                else {
                    sift(data, pshift, head);
                }

                if (pshift == 1) {
                    p <<= 1;
                    pshift--;
                }
                else {
                    p <<= (pshift - 1);
                    pshift = 1;
                }
            }
            p |= 1;
            head++;
        }

        trinkle(data, p, pshift, head, false);

        while (pshift != 1 || p != 1) {
            if (pshift <= 1) {
                int trail = Integer.numberOfTrailingZeros(p & ~1);
                p >>>= trail;
                pshift += trail;
            }
            else {
                p <<= 2;
                p ^= 7;
                pshift -= 2;

                trinkle(data, p >>> 1, pshift + 1, head - leonardoNumbers[pshift] - 1, true);
                trinkle(data, p, pshift, head - 1, true);
            }
            head--;
        }
    }

    private static <T extends Comparable<T>> void sift(T[] data, int pshift, int head) {
        T value = data[head];

        while (pshift > 1) {
            int rt = head - 1;
            int lf = head - 1 - leonardoNumbers[pshift - 2];

            if (value.compareTo(data[lf]) >= 0 && value.compareTo(data[rt]) >= 0) {
                break;
            }
            if (data[lf].compareTo(data[rt]) >= 0) {
                data[head] = data[lf];
                head = lf;
                pshift -= 1;
            }
            else {
                data[head] = data[rt];
                head = rt;
                pshift -= 2;
            }
        }
        data[head] = value;
    }

    private static <T extends Comparable<T>> void trinkle(T[] data, int p, int pshift, int head, boolean isTrusty) {
        T value = data[head];

        while (p != 1) {
            int stepson = head - leonardoNumbers[pshift];

            if (data[stepson].compareTo(value) <= 0) {
                break;
            }

            if (!isTrusty && pshift > 1) {
                int rt = head - 1;
                int lf = head - 1 - leonardoNumbers[pshift - 2];
                if (data[rt].compareTo(data[stepson]) >= 0 || data[lf].compareTo(data[stepson]) >= 0) {
                    break;
                }
            }

            data[head] = data[stepson];

            head = stepson;
            int trail = Integer.numberOfTrailingZeros(p & ~1);
            p >>>= trail;
            pshift += trail;
            isTrusty = false;
        }

        if (!isTrusty) {
            data[head] = value;
            sift(data, pshift, head);
        }
    }

}
