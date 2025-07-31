package com.pathfinder.blockchain;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

// main 클래스
public class ChainMain {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        blockchain.add(new Block("첫 블록", "0"));
        System.out.println("block 1 채굴 시도 중!! ");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("2번째 블록",blockchain.get(blockchain.size()-1).hash));
        System.out.println("block 2 채굴 시도 중!! ");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("3번째 블록",blockchain.get(blockchain.size()-1).hash));
        System.out.println("block 3 채굴 시도 중!! ");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain 유효성 검사: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nBlockchain list: ");
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        // list를 순회하면서 hash값 체크
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            // 등록된 hash값과 계산한 hash값을 비교!
            if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("스베 hash값이 다르잖아");
                return false;
            }
            // 이전 hash값과 등록된 이전 hash값을 비교
            if(!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("이전 hash값이 다르다맨이야");
                return false;
            }
            // 만약 hash를 해결했다면?
            if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)) {
                System.out.println("이 블록은 채굴되지 않았다맨!");
                return false;
            }
        }

        return true;
    }
}
