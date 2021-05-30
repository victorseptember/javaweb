package com.practice;

import com.google.gson.Gson;
import com.practice.ciphertextsrh.CreateDatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TestC {

    public static String byteToBinStr(byte[] bytes){
        int len = bytes.length;
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < len; i++) {
            String temp = Integer.toBinaryString(bytes[i] & 0xff);
            while (temp.length() < 8) {
                temp = '0' + temp;
            }
            result.append(temp);
        }

        return result.toString();
    }

    public static void main(String[] args) {
//        System.out.println(CreateDatabase.d.toString());
        //259034826762023357514824957751984826264
        //56885018666850526208848334256605631214
        //302625268256500005258730484032085327651
        CreateDatabase database = new CreateDatabase();
        byte[] hash = database.md_5("alice456");
        String hashBin = byteToBinStr(hash);
        BigInteger m = new BigInteger(hashBin,2);
        System.out.println(m.toString());
//        Gson gson = new Gson();
//        List<String> list = new ArrayList<>();
//        list.add("aaa");
//        list.add("bbb");
//        list.add("ccc");
//        list.add(0,"ddd");
//        String json = gson.toJson(list);
//        System.out.println(json);


    }
}
