package com.practice.ciphertextsrh.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestPath {

    public static void main(String[] args) throws IOException {
        File file = new File("");
        System.out.println(file.getCanonicalPath());
        File file2 = new File("./web/publickey.txt");
        BufferedReader reader1 = new BufferedReader(new FileReader(file2));
        String publicKey = reader1.readLine();
        System.out.println(publicKey);
    }
}
