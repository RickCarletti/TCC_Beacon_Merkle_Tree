package com.example.beacon.vdf.application.combination;

import java.util.ArrayList;
import java.util.List;

import com.example.beacon.vdf.application.combination.dto.SeedUnicordCombinationVo;

public class MerkleTree {
    
    private MerkleTreeNode root;

    public MerkleTreeNode getRoot() {
        return root;
    }

    public MerkleTree(List<SeedUnicordCombinationVo> seedList){
        buildTree(seedList);
    }

    private void buildTree(List<SeedUnicordCombinationVo> seedList){
        List<MerkleTreeNode> nodes = new ArrayList<MerkleTreeNode>();
        for(SeedUnicordCombinationVo seed:seedList){
            nodes.add(new MerkleTreeNode(null, null, MerkleTreeNode.hash(seed.getSeed()), seed.getSeed()));
        }
        this.root = buildTreeRecursive(nodes);
    }

    private MerkleTreeNode buildTreeRecursive(List<MerkleTreeNode> nodes){
        if(nodes.size() % 2 == 1){
            nodes.add(nodes.get(nodes.size() - 1).copy());
        }
        int half = nodes.size() / 2;
        if(nodes.size() == 2){
            return new MerkleTreeNode(
                nodes.get(0), 
                nodes.get(1), 
                MerkleTreeNode.hash(nodes.get(0).getHashValue() + nodes.get(1).getHashValue()), 
                nodes.get(0).getContent() + "+" + nodes.get(1).getContent()
            );
        }

        MerkleTreeNode left = buildTreeRecursive(nodes.subList(0, half));
        MerkleTreeNode right = buildTreeRecursive(nodes.subList(half, nodes.size()));
        String hashValue = MerkleTreeNode.hash(left.getHashValue() + right.getHashValue());
        String content = left.getContent() + "+" + right.getContent();

        return new MerkleTreeNode(left, right, hashValue, content);
    }
}