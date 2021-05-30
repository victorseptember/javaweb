package com.practice.ciphertextsrh;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.*;
import java.util.Random;

public class CreateDatabase {

    private BigInteger e;
    private BigInteger d;
    private BigInteger n;
    private BigInteger k_b;  //  服务端盲化因子

    public void getKeyPairs() throws NoSuchAlgorithmException {
         KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  //RSA
         keyPairGen.initialize(1024,new SecureRandom());  //  1024bits key
         KeyPair keyPair = keyPairGen.generateKeyPair();
         RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
         RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
         d = privateKey.getPrivateExponent();
         e = publicKey.getPublicExponent();
         n = publicKey.getModulus();

         Random random = new Random();
         k_b =new BigInteger(1024,random); //随机盲化因子

    }

    public byte[] md_5(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            return md5.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 截取hash值的前两位
     * @param bytes
     * @return
     */
    public String byteToBinStr(byte[] bytes){
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

    public void saveKey(BigInteger d,BigInteger e,BigInteger n,BigInteger k) throws IOException {
        File file1 = new File("./web/privatekey.txt");
        File file2 = new File("./web/publickey.txt");
        File file3 = new File("./web/modulus.txt");
        File file4 = new File("./web/factor.txt");
        Writer writer1 = new FileWriter(file1);
        Writer writer2 = new FileWriter(file2);
        Writer writer3 = new FileWriter(file3);
        Writer writer4 = new FileWriter(file4);

        writer1.write(d.toString());
        writer2.write(e.toString());
        writer3.write(n.toString());
        writer4.write(k.toString());
        writer1.close();
        writer2.close();
        writer3.close();
        writer4.close();
    }

    public BigInteger blindMessage(BigInteger m, BigInteger factor, BigInteger e, BigInteger n){
        BigInteger msg = m.multiply(factor.modPow(e,n)).mod(n);
        return msg;
    }
    public BigInteger blindSignature(BigInteger msg, BigInteger d, BigInteger n){
        BigInteger signature = msg.modPow(d,n);
        return signature;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        CreateDatabase database = new CreateDatabase();
        database.getKeyPairs();
        System.out.println(database.d.toString());
        System.out.println(database.n.toString());
        System.out.println(database.e.toString());

        database.saveKey(database.d,database.e,database.n,database.k_b);

        Connection con = null;
        PreparedStatement ps_sel = null;
        PreparedStatement ps_ins = null;
        PreparedStatement ps_count = null;
        ResultSet rs = null;
        String connectionUrl = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8&serverTimezone=GMT";
        String dbUsername = "root";
        String passwd = "xycxyc123";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl,dbUsername,passwd);
            String sql_count = "select count(*) from user_p";
            ps_count = con.prepareStatement(sql_count);
            rs = ps_count.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            System.out.println("count:" + count);

            int start = 0, amount = 1000;
            //200040
            while (count != 0) {
                amount = count / 1000 == 0 ? count : 1000;
                String sql_sel = "select * from user_p where id > " + start + " limit " + amount;
                start = start + amount;
                count = count - 1000;
                ps_sel = con.prepareStatement(sql_sel);
                rs = ps_sel.executeQuery();


                String credential, hashBinStr;
                BigInteger m, msg;
                String sql_ins = "insert into user_c values (?,?)";
                ps_ins = con.prepareStatement(sql_ins);

                while(rs.next()){
                    credential = rs.getString("uname") + rs.getString("passwd");
                    hashBinStr = database.byteToBinStr(database.md_5(credential));

                    m = new BigInteger(hashBinStr, 2);  //明文信息
                    System.out.println("m:");
                    msg = database.blindMessage(m,database.k_b,database.e,database.n);
                    ps_ins.setString(1,msg.toString());
                    ps_ins.setString(2,hashBinStr.substring(0, 16));
                    if (ps_ins.execute()){  //  盲化后的信息插入user_c
                        System.out.println("failed");
                    }
                }
            }

        } catch (ClassNotFoundException |SQLException ex) {
            ex.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps_ins != null) {
                try {
                    ps_ins.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps_sel != null) {
                try {
                    ps_sel.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
