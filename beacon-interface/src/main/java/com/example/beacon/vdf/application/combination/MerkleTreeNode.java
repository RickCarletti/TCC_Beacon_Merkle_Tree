package com.example.beacon.vdf.application.combination;

import br.gov.inmetro.beacon.library.ciphersuite.suite0.CipherSuiteBuilder;
import br.gov.inmetro.beacon.library.ciphersuite.suite0.ICipherSuite;

class MerkleTreeNode {
    private MerkleTreeNode left;
    private MerkleTreeNode right;
    private String hashValue;
    private String content;
    private Integer lvl;
    

    public String getHashValue() {
        return hashValue;
    }

    public String getContent() {
        return content;
    }

    public Integer getLvl() {
        return lvl;
    }

    private static ICipherSuite cipherSuite = CipherSuiteBuilder.build(0);

    
    public MerkleTreeNode(MerkleTreeNode left, MerkleTreeNode right, String hashValue, String content, int lvl){
        this.left = left;
        this.right = right;
        this.hashValue = hashValue;
        this.content = content;
        this.lvl = lvl;
    }
    
    public static String hash(String s){
        return cipherSuite.getDigest(s);
    }

    public MerkleTreeNode copy(){
        return new MerkleTreeNode(left, right, hashValue, content, lvl+1);
    }
}