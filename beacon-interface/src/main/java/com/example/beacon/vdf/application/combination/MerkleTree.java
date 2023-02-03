package com.example.beacon.vdf.application.combination;

import java.util.ArrayList;
import java.util.List;

import com.example.beacon.vdf.application.combination.dto.SeedUnicordCombinationVo;

class MerkleTree {
    
    private MerkleTreeNode root;
    List<MerkleTreeNode> nodes = new ArrayList<MerkleTreeNode>();

    public MerkleTreeNode getRoot() {
        return root;
    }

    public List<MerkleTreeNode> getList(){
        return nodes;
    }

    public MerkleTree(List<SeedUnicordCombinationVo> seedList){
        buildTree(seedList);
    }

    private void buildTree(List<SeedUnicordCombinationVo> seedList){
        for(SeedUnicordCombinationVo seed:seedList){
            nodes.add(new MerkleTreeNode(null, null, MerkleTreeNode.hash(seed.getSeed()), seed.getSeed(), 0));
        }

        int pos = 0;

        while(pos+1 < nodes.size()){
            if(nodes.get(pos).getLvl() == nodes.get(pos+1).getLvl()){
                nodes.add(new MerkleTreeNode(
                    nodes.get(pos), 
                    nodes.get(pos+1), 
                    MerkleTreeNode.hash(nodes.get(pos).getHashValue() + nodes.get(pos+1).getHashValue()), 
                    nodes.get(pos).getContent() + "+" + nodes.get(pos+1).getContent(), nodes.get(pos+1).getLvl()+1)
                );
                pos+=2;
            }else{
                nodes.add(nodes.get(pos).copy());
                pos++;
            }
        }

        this.root = nodes.get(nodes.size()-1);
    }
}