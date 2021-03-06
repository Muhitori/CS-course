package com.shpp.p2p.cs.kturevich.assignment15;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Decoder {
    //Path to the file that will be decoded
    private final String inFile;
    //Path to the output file
    private final String outFile;
    //Encoded file in bytes
    private byte[] byteArray;

    private ArrayList<Integer> stack = new ArrayList<>();
    private HashMap<Byte, String> map = new HashMap<>();

    ArrayList<Boolean> treeShape = new ArrayList<>();
    LinkedList<Byte> leaves = new LinkedList<>();


    Decoder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void main() throws IOException, ClassNotFoundException {
        long start = System.currentTimeMillis();

        //Reading packed file
        this.byteArray = Files.readAllBytes(Paths.get(inFile));
        long packedFileSize = this.byteArray.length;

        int zeroesCount = ByteBuffer.wrap(sliceBytes(Short.BYTES)).getShort();
        int treeSize = ByteBuffer.wrap(sliceBytes(Short.BYTES)).getShort();

        decodeTree(treeSize);
        sliceBytes(treeSize);

        int leavesCount = leavesCount();
        decodeLeaves(leavesCount);
        sliceBytes(leavesCount);

        Node huffmanTree = unflattenTree();

        String[] decodedBinary = dataBinaryString().split("");

        String[] decodedData = new String[decodedBinary.length - zeroesCount];

        for (int i = 0; i < decodedData.length; i++) {
            decodedData[i] = decodedBinary[i];
        }

        ArrayList<Byte> originalData = decode(huffmanTree, decodedData);

        write(originalData);

        System.out.println("Unpacking took " + (System.currentTimeMillis() - start) +" ms");
        System.out.println("Archived file size: " + packedFileSize + " bytes");
        System.out.println("Unpacked file size: " + originalData.size() + " bytes");
    }

    private void decodeTree(int treeSize) {
        for (int i = 0; i < treeSize; i++)
            treeShape.add(byteArray[i] == 1);
    }

    private void decodeLeaves(int leavesCount) {
        for (int i = 0; i < leavesCount; i++)
            leaves.add(byteArray[i]);
    }

    private Node unflattenTree() {
        if (!treeShape.get(0))
            return new Node(leaves.get(0));

        Node node = new Node(null);
        for (int i = 1; i < treeShape.size(); i++) {
            if (treeShape.get(i)) {
                Node newNode = new Node(null);

                if (node.getLeft() == null) {
                    node.setLeft(newNode);
                } else {
                    node.setRight(newNode);
                }

                newNode.setParent(node);
                node = newNode;
            } else {
                Node newNode = new Node(leaves.poll());

                if (node.getLeft() == null) {
                    node.setLeft(newNode);
                } else {
                    node.setRight(newNode);
                }

                newNode.setParent(node);

                while (node.getRight() != null && node.getParent() != null)
                    node = node.getParent();
            }
        }
        return node;
    }

    private ArrayList<Byte> decode(Node node, String[] binaryString) {
        ArrayList<Byte> result = new ArrayList<>();
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

    private int leavesCount() {
        int result = 0;
        for (Boolean aBoolean : treeShape) {
            if (!aBoolean)
                result++;
        }
        return result;
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
