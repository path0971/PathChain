package com.pathfinder.blockchain.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// digital 지문을 생성하기 위한 Util 클래스
public class StringUtil {
    // input에 sha-256을 적용하고 이를 result로 반환
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // input에 sha-256 적용
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // 16진수로 hash값을 담는 변수
            for (int i = 0; i < hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]); // 하위 8비트만 뽑아서 양수로 바꿔주는 트릭
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
