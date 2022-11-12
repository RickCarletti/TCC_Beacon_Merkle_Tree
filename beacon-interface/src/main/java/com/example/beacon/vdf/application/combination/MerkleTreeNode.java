package com.example.beacon.vdf.application.combination;

import br.gov.inmetro.beacon.library.ciphersuite.suite0.CipherSuiteBuilder;
import br.gov.inmetro.beacon.library.ciphersuite.suite0.ICipherSuite;

public class MerkleTreeNode {
    private MerkleTreeNode left;
    private MerkleTreeNode right;
    private String hashValue;
    private String content;
    
    // public MerkleTreeNode getLeft() {
    //     return left;
    // }

    // public void setLeft(MerkleTreeNode left) {
    //     this.left = left;
    // }

    // public MerkleTreeNode getRight() {
    //     return right;
    // }

    // public void setRight(MerkleTreeNode right) {
    //     this.right = right;
    // }

    public String getHashValue() {
        return hashValue;
    }

    // public void setHashValue(String hashValue) {
    //     this.hashValue = hashValue;
    // }

    public String getContent() {
        return content;
    }

    // public void setContent(String content) {
    //     this.content = content;
    // }

    private static ICipherSuite cipherSuite = CipherSuiteBuilder.build(0);

    
    public MerkleTreeNode(MerkleTreeNode left, MerkleTreeNode right, String hashValue, String c){
        this.left = left;
        this.right = right;
        this.hashValue = hashValue;
        this.hashValue = hashValue;
    }
    
    public static String hash(String s){
        return cipherSuite.getDigest(s);
    }

    public MerkleTreeNode copy(){
        return new MerkleTreeNode(left, right, hashValue, content);
    }
}
