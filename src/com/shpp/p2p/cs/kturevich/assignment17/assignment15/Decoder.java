package com.shpp.p2p.cs.kturevich.assignment17.assignment15;

import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyArrayList;
import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyLinkedList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Decoder {
    private long unarchivedFileSize;

    //Path to the file that will be decoded
    private final String inFile;
    //Path to the output file
    private final String outFile;
    //Encoded file in bytes
    private byte[] byteArray;

    MyArrayList<Boolean> treeShape = new MyArrayList<>();
    MyLinkedList<Byte> leaves = new MyLinkedList<>();

    Decoder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void main() throws Exception {
        long start = System.currentTimeMillis();

        //Reading packed file
        this.byteArray = Files.readAllBytes(Paths.get(inFile));
        long packedFileSize = this.byteArray.length;

        //original array is sliced each time i get some data
        //get zeroes count
        int zeroesCount = byteArray[0];
        sliceBytes(Byte.BYTES);

        //get tree size & tree shape
        int treeSize = ByteBuffer.wrap(sliceBytes(Short.BYTES)).getShort();
        decodeTreeShape(treeSize);
        sliceBytes(treeSize);

        //get leaves count & leaves
        int leavesCount = leavesCount();
        decodeLeaves(leavesCount);
        sliceBytes(leavesCount);

        //get tree & compressed binary
        Node huffmanTree = unflatteringTree();
        String[] decodedBinary = dataBinaryString().split("");

        //if while archiving was added external zeroes
        if (zeroesCount > 0)
            decodedBinary = removeExternalZeroes(decodedBinary, zeroesCount);

        MyLinkedList<Byte> originalData = decode(huffmanTree, decodedBinary);
        System.out.println("decode");

        write(originalData);

        System.out.println("Unpacking took " + (System.currentTimeMillis() - start) +" ms");
        System.out.println("Archived file size: " + packedFileSize + " bytes");

        unarchivedFileSize = originalData.size();
        System.out.println("Unpacked file size: " + unarchivedFileSize + " bytes");
    }


    private void decodeTreeShape(int treeSize) {
        for (int i = 0; i < treeSize; i++)
            treeShape.add(byteArray[i] == 1);
    }

    private void decodeLeaves(int leavesCount) {
        for (int i = 0; i < leavesCount; i++)
            leaves.add(byteArray[i]);
    }

    //Building tree from tree shape & leaves data
    private Node unflatteringTree() throws Exception {
        //if root is leave - return node with value
        if (!treeShape.get(0))
            return new Node(leaves.get(0));

        //create node for root & starting from index 1
        Node node = new Node(null);
        for (int i = 1; i < treeShape.size(); i++) {
            //true - empty node, false - leave
            if (treeShape.get(i)) {
                Node newNode = new Node(null);
                addChild(node, newNode);

                //set new node as current
                node = newNode;
            } else {
                Node newNode = new Node(leaves.poll());
                addChild(node, newNode);

                //looking for empty right spot
                while (node.getRight() != null && node.getParent() != null)
                    node = node.getParent();
            }
        }
        return node;
    }

    private void addChild(Node parentNode, Node childNode) throws Exception {
        if (parentNode.getLeft() == null) {
            parentNode.setLeft(childNode);
        } else if (parentNode.getRight() == null){
            parentNode.setRight(childNode);
        } else {
            throw new Exception("Problem with three shape");
        }
        childNode.setParent(parentNode);
    }

    //Get original data from compressed binary
    private MyLinkedList<Byte> decode(Node node, String[] binaryString) {
        MyLinkedList<Byte> result = new MyLinkedList<>();

        for (String s : binaryString) {
            node = s.equals("0") ? node.getLeft() : node.getRight();

            if (node.getValue() != null) {
                result.add(node.getValue());
                while (node.getParent() != null)
                    node = node.getParent();
            }
        }
        return result;
    }

    //Remove zeroes, which might be added while archiving
    private String[] removeExternalZeroes(String[] decodedBinary, int zeroesCount) {
        String[] result = new String[decodedBinary.length - zeroesCount];
        if (result.length >= 0)
            System.arraycopy(decodedBinary, 0, result, 0, result.length);
        return result;
    }

    //Slice global bytearray & return sliced value
    private byte[] sliceBytes(int sliceNumber) {
        byte[] result = new byte[sliceNumber];

        if (sliceNumber >= 0) {
            System.arraycopy(byteArray, 0, result, 0, sliceNumber);
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

    private int leavesCount() {
        int result = 0;
        for (Boolean aBoolean : treeShape) {
            if (!aBoolean)
                result++;
        }
        return result;
    }

    private void write(MyLinkedList<Byte> originalData) throws IOException {
        Files.deleteIfExists(Path.of(outFile));
        FileOutputStream fos = new FileOutputStream(outFile, true);

        Object[] objects = originalData.toArray();
        byte[] bytes = new byte[objects.length];

        for (int i = 0; i < bytes.length; i++)
            bytes[i] = (Byte) objects[i];

        fos.write(bytes);
        fos.close();
    }

    public long getUnarchivedFileSize() {
        return unarchivedFileSize;
    }
}
