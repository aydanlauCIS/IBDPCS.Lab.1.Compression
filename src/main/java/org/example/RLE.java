package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RLE {

    public static void main(String[] args) throws FileNotFoundException {
        String decompressedString = textToString("src/main/resources/COVID-19");
        String compressedString = compress(decompressedString);
        System.out.println(compressedString);
    }

    /** This method converts the information stored in a text file into a String. */
    public static String textToString(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            String subSeq = sc.next();
            for (int i = 0; i < subSeq.length(); i++) {
                sb.append(subSeq.charAt(i));
            }
        }
        return sb.toString();
    }

    /** TODO 1: Given a String (a genome sequence of COVID-19) implement the RLE algorithm that will use RLE to compress a String. Returns the compressed String. */
    public static String compress(String uncompressed) {
        StringBuilder compressed = new StringBuilder();

        int count = 0;
        char prev = uncompressed.charAt(0);
        for (char c: uncompressed.toCharArray()) {
            if (prev == c) {
                count++;
            } else {
                compressed.append(count);
                compressed.append(prev);
                prev = c;
                count = 1;
            }
        }

        return compressed.toString();
    }

    /** TODO 2: Given a String (a genome sequence of COVID-19) implement the RLE algorithm that will use RLE to decompress a String. Returns the uncompressed String. */
    public static String decompress(String compressed) {
        StringBuilder decompressed = new StringBuilder();

        int count = -1;
        for (int i = 0; i < compressed.length(); i++) {
            if (i % 2 == 0) {
                count = compressed.charAt(i) - '0';
            } else {
                decompressed.append(String.valueOf(compressed.charAt(i)).repeat(count));
            }
        }

        return decompressed.toString();
    }


}
