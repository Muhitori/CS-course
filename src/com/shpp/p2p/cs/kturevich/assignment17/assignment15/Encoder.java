package com.shpp.p2p.cs.kturevich.assignment17.assignment15;

import com.shpp.p2p.cs.kturevich.assignment17.MyHashMap;
import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyArrayList;
import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyLinkedList;
import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyStack;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Encoder {
    private long archivedFileSize;
    //First file path
    private final String inFile;
    //Second file path
    private final String outFile;

    private final MyStack<Integer> stack = new MyStack<>();
    private final MyHashMap<Byte, String> map = new MyHashMap<>();

    private final MyLinkedList<Byte> leaves = new MyLinkedList<>();
    private final MyArrayList<Boolean> treeShape = new MyArrayList<>();

    private byte zeroesCount = 0;

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
            throw new Exception("Empty file " + inFile + "!");

        Huffman huffman = new Huffman(toObjectArray(byteArray));
        Node huffmanTree = huffman.buildHuffmanTree();

        encode(huffmanTree);

        byte[] newBytes = binaryStringToBytes(compress(byteArray));

        Byte[] newData = summarizeData(newBytes);

        write(newData);

        System.out.println("Archiving took " + (System.currentTimeMillis() - start) +" ms");
        System.out.println("Original file size: " + fileSize + " bytes");

        archivedFileSize = Byte.BYTES + Short.BYTES + newData.length;
        System.out.println("Archived file size: " + archivedFileSize + " bytes");

        double archivedPercentage = 100 - Math.round((archivedFileSize / (double) fileSize) * 100);
        System.out.println("Efficiency of archiving: " + archivedPercentage + "%");
    }

    //Collect data in byte array
    private Byte[] summarizeData(byte[] newBytes) {
        MyLinkedList<Byte> result = new MyLinkedList<>();

        for (Boolean b : treeShape) {
            result.add((byte) (b ? 1 : 0));
        }

        for (Byte b : leaves) {
            result.add(b);
        }

        for (byte b : newBytes){
            result.add(b);
        }

        Object[] objects = result.toArray();
        Byte[] bytes = new Byte[objects.length];

        for (int i = 0; i < bytes.length; i++)
            bytes[i] = (Byte) objects[i];

        return bytes;
    }

    //Cast byte[] to Byte[]
    private Byte[] toObjectArray(byte[] bytes) {
        Byte[] result = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = bytes[i];
        }
        return result;
    }

    //This method is filling tree shape list, leaves list and map keys & values
    private void encode(Node node) {
        if (node.getValue() != null) {
            treeShape.add(false);
            leaves.add(node.getValue());
            map.put(node.getValue(), joinStack());
        } else {
            treeShape.add(true);
        }

        if (node.getLeft() != null) {
            stack.push(0);
            encode(node.getLeft());
            stack.pop();
        }

        if (node.getRight() != null) {
            stack.push(1);
            encode(node.getRight());
            stack.pop();
        }
    }

    //generate String from current Stack elements
    private String joinStack() {
        StringBuilder result = new StringBuilder();
        for (Integer i : stack) {
            result.append(i.intValue());
        }
        return result.toString();
    }

    //Mapping bytes with binary keys
    private String compress(byte[] byteArray) {
        StringBuilder result = new StringBuilder();
        for (byte b : byteArray) {
            result.append(map.get(b));
        }

        //adding external zeroes if needed
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

        //Writing external zeroes count
        fos.write(zeroesCount);

        //Writing tree shape size
        fos.write(ByteBuffer.allocate(Short.BYTES).putShort((short) treeShape.size()).array());

        byte[] writableData = new byte[newData.length];
        for (int i = 0; i < newData.length; i++) {
            writableData[i] = newData[i];
        }

        fos.write(writableData);
        fos.close();
    }

    public long getArchivedFileSize() {
        return archivedFileSize;
    }
}
