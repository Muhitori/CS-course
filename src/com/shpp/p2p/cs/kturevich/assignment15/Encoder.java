package com.shpp.p2p.cs.kturevich.assignment15;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Encoder {
    //First file path
    private final String inFile;
    //Second file path
    private final String outFile;

    private ArrayList<Integer> stack = new ArrayList<>();
    private HashMap<Byte, String> map = new HashMap<>();

    LinkedList<Byte> leaves = new LinkedList<>();
    ArrayList<Boolean> treeShape = new ArrayList<>();

    private short zeroesCount = 0;

    Encoder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void main() throws Exception {
        long start = System.currentTimeMillis();

        //reading bytes & overall file size
        byte[] byteArray = Files.readAllBytes(Paths.get(inFile));
        long fileSize = Files.size(Paths.get(inFile));

        if (byteArray.length == 0)
            throw new Exception("Empty file!");

        Huffman huffman = new Huffman(toObjectArray(byteArray));
        Node huffmanTree = huffman.buildHuffmanTree();
        encode(huffmanTree);

        byte[] newBytes = binaryStringToBytes(getEncodedBits(byteArray));

        Byte[] newData = summarizeData(newBytes);

        write(newData);

        System.out.println("Archiving took " + (System.currentTimeMillis() - start) +" ms");
        System.out.println("Original file size: " + fileSize + " bytes");

        long archivedFileSize = newData.length + Short.BYTES + Short.BYTES;
        System.out.println("Archived file size: " + archivedFileSize + " bytes");

        double archivedPercentage = 100 - Math.round((archivedFileSize / (double) fileSize) * 100);
        System.out.println("Efficiency of archiving: " + archivedPercentage + "%");
    }

    private Byte[] summarizeData(byte[] newBytes) {
        ArrayList<Byte> result = new ArrayList<>();

        for (Boolean b : treeShape) {
            result.add((byte) (b ? 1 : 0));
        }

        result.addAll(leaves);

        for (byte b : newBytes){
            result.add(b);
        }

        return result.toArray(new Byte[0]);
    }

    private Byte[] toObjectArray(byte[] bytes) {
        Byte[] result = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = bytes[i];
        }
        return result;
    }

    private void encode(Node node) {
        if (node.getValue() != null) {
            treeShape.add(false);
            leaves.add(node.getValue());
            map.put(node.getValue(), joinStack());
        } else {
            treeShape.add(true);
        }

        if (node.getLeft() != null) {
            stack.add(0);
            encode(node.getLeft());
            stack.remove(stack.size() - 1);
        }

        if (node.getRight() != null) {
            stack.add(1);
            encode(node.getRight());
            stack.remove(stack.size() - 1);
        }
    }

    private String joinStack() {
        StringBuilder result = new StringBuilder();
        for (Integer i : stack) {
            result.append(i.intValue());
        }
        return result.toString();
    }

    private String getEncodedBits(byte[] byteArray) {
        StringBuilder result = new StringBuilder();
        for (byte b : byteArray) {
            result.append(map.get(b));
        }

        zeroesCount = 0;
        while (result.length() % 8 != 0) {
            result.append("0");
            zeroesCount++;
        }

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
    private void write(Byte[] newData) throws IOException {
        Files.deleteIfExists(Path.of(outFile));
        FileOutputStream fos = new FileOutputStream(outFile, true);

        fos.write(ByteBuffer.allocate(Short.BYTES).putShort(zeroesCount).array());

        fos.write(ByteBuffer.allocate(Short.BYTES).putShort((short) treeShape.size()).array());

        byte[] writableData = new byte[newData.length];
        for (int i = 0; i < newData.length; i++) {
            writableData[i] = newData[i];
        }

        fos.write(writableData);
        fos.close();
    }
}
