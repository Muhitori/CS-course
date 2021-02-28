package com.shpp.p2p.cs.kturevich.assignment14;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Encoder {
    private final String inFile;
    private final String outFile;
    private int byteLength;

    Encoder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void encoding() throws IOException {
        byte[] byteArray = Files.readAllBytes(Paths.get(inFile));
        long fileSize = Files.size(Paths.get(inFile));

        ArrayList<Byte> uniqueBytes = getUniqueBytes(byteArray);
        this.byteLength = newByteLength(uniqueBytes);

        HashMap<Byte, String> dictionary = generateDictionary(uniqueBytes);
        byte[] dictionaryBytes = dictionaryToBytes(dictionary);

        String newData = dataToString(byteArray, dictionary);

        write(fileSize, dictionaryBytes, newData);
    }

    private HashMap<Byte, String> generateDictionary(ArrayList<Byte> uniqueBytes) {
        HashMap<Byte, String> dictionary = new HashMap<>();

        String[] bitsStrings = getStringValues(uniqueBytes);

        for (int i = 0; i < uniqueBytes.size(); i++) {
            dictionary.put(uniqueBytes.get(i), bitsStrings[i]);
        }

        return dictionary;
    }

    private String[] getStringValues (ArrayList<Byte> uniqueBytes) {
        String[] result = new String[uniqueBytes.size()];

        for (int i = 0; i < uniqueBytes.size(); i++) {
            result[i] = bitsetToBinaryString(BitSet.valueOf(new byte[] {(byte)i}));
        }

        return result;
    }

    private String bitsetToBinaryString(BitSet bitset) {
        StringBuilder newByte = new StringBuilder();
        newByte.insert(0, "0".repeat(byteLength));

        for (int i = 0; i < bitset.length(); i++) {
            if (bitset.get(i))
                newByte.setCharAt(byteLength - 1 - i, '1');
        }

        return newByte.toString();
    }

    private byte[] dictionaryToBytes(HashMap<Byte, String> dictionary) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);

        out.writeObject(dictionary);
        return byteOut.toByteArray();
    }

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

    private ArrayList<Byte> getUniqueBytes(byte[] byteArray) {
        ArrayList<Byte> result = new ArrayList<>();
        for (byte b : byteArray) {
            if (!result.contains(b)) {
                result.add(b);
            }
        }
        return result;
    }

    private String dataToString(byte[] byteArray, HashMap<Byte, String> dictionary) {
        StringBuilder result = new StringBuilder();

        for (byte b : byteArray) {
            result.append(dictionary.get(b));
        }

        while (result.length() % 8 != 0)
            result.append(0);

        return result.toString();
    }

    private void write(long fileSize, byte[] dictionaryBytes, String bits) throws IOException {
        Files.deleteIfExists(Path.of(outFile));
        FileOutputStream fos = new FileOutputStream(outFile, true);

        fos.write(ByteBuffer.allocate(4).putInt(dictionaryBytes.length).array());

        ByteBuffer longBuffer = ByteBuffer.allocate(Long.BYTES).putLong(fileSize);
        fos.write(longBuffer.array());

        fos.write(dictionaryBytes);

        for (int i = 0; i + 8 <= bits.length(); i += 8) {
            fos.write(Integer.parseInt(bits.substring(i, i + 8), 2));
        }
        fos.close();
    }
}
