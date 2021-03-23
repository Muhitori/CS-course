package com.shpp.p2p.cs.kturevich.assignment17;

public class Assignment17Part1 {

    public static void main(String[] args) throws Exception {
        try {
            Test test = new Test();

            test.hashmapCases();
            test.priorityQueueCases();

            test.assignment11Cases();
//            test.assignment13Cases();
//            test.assignment15Cases();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
