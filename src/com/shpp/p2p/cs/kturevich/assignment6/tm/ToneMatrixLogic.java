package com.shpp.p2p.cs.kturevich.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

        for (int i = 0; i < toneMatrix.length; i++) {
            if (toneMatrix[i][column]) {
                addingMatrix(result, samples[i]);
            }
        }

        normalize(result);

        return result;
    }

    /**
     * Adds all elements of sound to result
     * @param sounds array with approximated sounds
     * @param sound array that will be added
     * */
    private static void addingMatrix(double[] sounds, double[] sound) {
        for (int i = 0; i < sounds.length; i++)
            sounds[i] += sound[i];
    }

    /**
     * Divides the resulting sum of sounds by extremum
     * @param sounds finite array of approximated sounds
     * @return normalized sound matrix
     * */
    private static void normalize(double[] sounds) {
        double extremum = findExtremum(sounds);

        for (int i = 0; i < sounds.length; i++) {
            sounds[i] /= extremum;
        }
    }

    /**
     * Finds the greatest deviation from zero (biggest note value)
     * @param sounds finite array of approximated sounds
     * @return greatest deviation from zero (biggest note value)
     * */
    private static double findExtremum(double[] sounds) {
        double extremum = 1;

        for (double note : sounds) {
            if (Math.abs(note) > Math.abs(extremum))
                extremum = note;
        }

        return extremum;
    }
}
