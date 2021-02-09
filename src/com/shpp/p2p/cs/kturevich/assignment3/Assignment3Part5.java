package com.shpp.p2p.cs.kturevich.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.Random;

public class Assignment3Part5 extends TextProgram {

    private static final int NEED_TO_WIN = 20;

    public void run() {
        int base;
        int total = 0;
        int games = 0;
        Random coin = new Random();

        while (total < NEED_TO_WIN) {
            //Base number for each game is set to 1
            base = 1;

            //While coin result is equal to 1 game continues
            while (coinToss(coin)) {
                base += base;
            }

            //Counting total gain
            total += base;
            games++;

            println("This game, you earned $" + base);
            println("Your total is $" + total);
        }
        println("It took " + games + " games to earn $20");
    }

    //Toss A Coin To Your Witcher o-o-o -_-
    private boolean coinToss(Random coin) {
        //Returns 1 or 0
        return coin.nextInt(2) == 1;
    }
}
