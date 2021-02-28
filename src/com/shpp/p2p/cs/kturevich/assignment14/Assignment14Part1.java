package com.shpp.p2p.cs.kturevich.assignment14;

import java.io.*;

public class Assignment14Part1 {

    public static void main(String[] args) {
        try {
            Encoder encoder = new Encoder("assets/archive/d.txt", "assets/archive/eh.par");
            encoder.encoding();
            Decoder decoder = new Decoder("assets/archive/eh.par", "assets/archive/d1.txt");
            decoder.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
