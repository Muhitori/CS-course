package com.shpp.p2p.cs.kturevich.assignment14;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

public class Encoder {
    //First file path
    private final String inFile;
    //Second file path
    private final String outFile;
    //New length of byte
    private int byteLength;

    Encoder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void main() throws IOException {
        long start = System.currentTimeMillis();

        //reading bytes & overall file size
        byte[] byteArray = Files.readAllBytes(Paths.get(inFile));
        long fileSize = Files.size(Paths.get(inFile));

        //counting unique bytes & new byte length
        ArrayList<Byte> uniqueBytes = getUniqueBytes(byteArray);
        this.byteLength = newByteLength(uniqueBytes);

        //creating dictionary & cast it to bytes array
        HashMap<Byte, String> dictionary = generateDictionary(uniqueBytes);
        byte[] dictionaryBytes = dictionaryToBytes(dictionary);

        //encoding all data to binary string & generating byte array
        String stringOfBits = encode(byteArray, dictionary);
        byte[] newData = binaryStringToBytes(stringOfBits);

        write(fileSize, dictionaryBytes, newData);

        System.out.println("Archiving took " + (System.currentTimeMillis() - start) +" ms");
        System.out.println("Original file size: " + fileSize + " bytes");

        long archivedFileSize = Integer.BYTES + Long.BYTES + dictionaryBytes.length + newData.length;
        System.out.println("Archived file size: " + archivedFileSize + " bytes");

        double archivedPercentage = 100 - Math.round((archivedFileSize / (double) fileSize) * 100);
        System.out.println("Efficiency of archiving: " + archivedPercentage + "%");
    }

    //Creating dictionary for encoding
    private HashMap<Byte, String> generateDictionary(ArrayList<Byte> uniqueBytes) {
        HashMap<Byte, String> dictionary = new HashMap<>();

        String[] bitsStrings = getStringValues(uniqueBytes);

        for (int i = 0; i < uniqueBytes.size(); i++) {
            dictionary.put(uniqueBytes.get(i), bitsStrings[i]);
        }

        return dictionary;
    }

    //Get values for dictionary
    private String[] getStringValues (ArrayList<Byte> uniqueBytes) {
        String[] result = new String[uniqueBytes.size()];

        for (int i = 0; i < uniqueBytes.size(); i++) {
            result[i] = bitsetToBinaryString(BitSet.valueOf(new byte[] {(byte)i}));
        }

        return result;
    }

    /**
     * Im use bitset for generating string values.
     * Bitset contains indexes of '1' values in byte.
     * */
    private String bitsetToBinaryString(BitSet bitset) {
        StringBuilder newByte = new StringBuilder();
        //creating string of zeros
        newByte.insert(0, "0".repeat(byteLength));

        for (int i = 0; i < bitset.length(); i++) {
            //if '1' is found - write it on opposite side of byte
            if (bitset.get(i))
                newByte.setCharAt(byteLength - 1 - i, '1');
        }

        return newByte.toString();
    }

    //Cast dictionary to bytes array
    private byte[] dictionaryToBytes(HashMap<Byte, String> dictionary) throws IOException {
        //create output stream as buffer
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);

        //write dictionary to buffer & cast it to array
        out.writeObject(dictionary);
        return byteOut.toByteArray();
    }

    //Calculating new byte length
    private int newByteLength (ArrayList<Byte> uniqueBytes) {
        int result = 0;
        for (int i = 1; i <= 8; i++) {
            if (uniqueBytes.size() <= Math.pow(2, i)) {
                result = i;
                break;
            }
        }
        return result;
    }

    //Creating array of unuque bytes
    private ArrayList<Byte> getUniqueBytes(byte[] byteArray) {
        ArrayList<Byte> result = new ArrayList<>();
        for (byte b : byteArray) {
            if (!result.contains(b)) {
                result.add(b);
            }
        }
        return result;
    }

    //Creating encoded binary string
    private String encode(byte[] byteArray, HashMap<Byte, String> dictionary) {
        StringBuilder result = new StringBuilder();

        for (byte b : byteArray) {
            result.append(dictionary.get(b));
        }

        while (result.length() % 8 != 0)
            result.append(0);

        return result.toString();
    }

    //Cast binary string to byte array
    private byte[] binaryStringToBytes(String bits) {
        byte[] dataBytes = new byte[bits.length() / 8];

        for (int i = 0, j = 0; i < dataBytes.length; i++, j += 8) {
            dataBytes[i] = (byte) Integer.parseInt(bits.substring(j, j + 8), 2);
        }

        return dataBytes;
    }

    //Write all data to output file
    private void write(long fileSize, byte[] dictionaryBytes, byte[] newData) throws IOException {
        Files.deleteIfExists(Path.of(outFile));
        FileOutputStream fos = new FileOutputStream(outFile, true);

        //write dictionary length
        fos.write(ByteBuffer.allocate(4).putInt(dictionaryBytes.length).array());

        //write file length
        ByteBuffer longBuffer = ByteBuffer.allocate(Long.BYTES).putLong(fileSize);
        fos.write(longBuffer.array());

        //write dictionary data & file data
        fos.write(dictionaryBytes);
        fos.write(newData);
        fos.close();
    }
}
