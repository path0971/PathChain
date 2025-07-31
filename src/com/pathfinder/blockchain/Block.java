package com.pathfinder.blockchain;

import com.pathfinder.blockchain.utils.StringUtil;

import java.util.Date;

public class Block {

    public String hash;  // 디지털 서명용
    public String previousHash; // 이전 block의 hash값
    private String data; // 메시지로 블록 데이터를 hold
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash();
    }

    // Util 클래스에서 만든 메소드를 통해 hash를 계산하는 메소드
    public String calculateHash() {
        String calculatedHash = StringUtil.applySha256(
                  previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedHash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0','0');
        while (!hash.substring(0,difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("채굴 완료! " + hash);
    }
}
