package com.shpp.p2p.cs.kturevich.assignment14;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Decoder {
    //Path to the file that will be decoded
    private final String inFile;
    //Path to the output file
    private final String outFile;
    //Encoded file in bytes
    private byte[] byteArray;

    Decoder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void main() throws IOException, ClassNotFoundException {
        long start = System.currentTimeMillis();

        //Reading packed file
        this.byteArray = Files.readAllBytes(Paths.get(inFile));
        long packedFileSize = this.byteArray.length;

        //get dictionary & unpacked file sizes (which i do not know why i encoded)
        int dictionaryLength = ByteBuffer.wrap(sliceBytes(Integer.BYTES)).getInt();
        long fileLength = ByteBuffer.wrap(sliceBytes(Long.BYTES)).getLong();

        //get encoded dictionary
        HashMap<Byte, String> dictionary = bytesToDictionary(sliceBytes(dictionaryLength));

        //get encoded binary string & cast it to byte array
        String dataBinaryString = dataBinaryString();
        ArrayList<Byte> originalData = decode(dataBinaryString, dictionary);

        write(originalData);

        System.out.println("Unpacking took " + (System.currentTimeMillis() - start) +" ms");
        System.out.println("Archived file size: " + packedFileSize + " bytes");
        System.out.println("Unpacked file size: " + originalData.size() + " bytes");
    }

    //Decode binary string to byte array
    private ArrayList<Byte> decode(String dataBinaryString, HashMap<Byte, String> dictionary) {
        ArrayList<Byte> result = new ArrayList<>();

        //calculate reading step based on first value length
        String firstValue = (String) dictionary.values().toArray()[0];
        int step = firstValue.length();

        //create new dictionary from encoded
        HashMap<String, Byte> newDictionary = createNewDictionary(dictionary);

        for (int i = 0; i + step <= dataBinaryString.length(); i += step) {
            result.add(newDictionary.get(dataBinaryString.substring(i, i + step)));
        }

        return result;
    }

    //Change keys & values of dictionary >_>
    private HashMap<String, Byte> createNewDictionary(HashMap<Byte, String> dictionary) {
        HashMap<String, Byte> result = new HashMap<>();

        for(Map.Entry<Byte, String> entry : dictionary.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }

        return result;
    }

    //get dictionary from bytes array
    private HashMap<Byte, String> bytesToDictionary(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(byteIn);
        return (HashMap<Byte, String>) in.readObject();
    }

    //Slice global bytearray & return sliced value
    private byte[] sliceBytes(int sliceNumber) {
        byte[] result = new byte[sliceNumber];

        for (int i = 0; i < sliceNumber; i++) {
            result[i] = byteArray[i];
        }

        byteArray = Arrays.copyOfRange(byteArray, sliceNumber, byteArray.length);

        return result;
    }

    //Reading binary string from byte array
    private String dataBinaryString() {
        StringBuilder result = new StringBuilder();

        for (byte b : byteArray) {
            //read from the end
            for (int i = 7; i >= 0; --i) {
                // 0 & 1 == 0, 1 & 1 == 1 - append what lies in that bit
                result.append(b >>> i & 1);
            }
        }
        return result.toString();
    }

    private void write(ArrayList<Byte> originalData) throws IOException {
        Files.deleteIfExists(Path.of(outFile));
        FileOutputStream fos = new FileOutputStream(outFile, true);

        //cast arrayList to array for better performance
        byte[] dataBytes = new byte[originalData.size()];

        for (int i = 0; i < dataBytes.length; i++) {
            dataBytes[i] = originalData.get(i);
        }

        fos.write(dataBytes);
        fos.close();
    }
}
