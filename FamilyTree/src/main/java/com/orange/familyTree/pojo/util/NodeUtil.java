package com.orange.familyTree.pojo.util;

import java.util.ArrayList;
import java.util.List;

import com.orange.familyTree.pojo.NodeVO;

public class NodeUtil {
	
	// 节点工具
	public static ArrayList<NodeVO> addSonsNodes(List<String> nodesNameList, NodeVO fatherNode, Integer radius) {
		// 儿子节点不同于其他节点，渲染是处于中间层
		ArrayList<NodeVO> nodes = new ArrayList<>();
		// 随机偏移量弧度
		double randomRadian = 2 * Math.PI * Math.random();
		for(int n = 0; n < nodesNameList.size(); n++) {
			// 随机偏移量弧度
			// 指定弧度
			double radian = 2 * Math.PI * (n + 1) / nodesNameList.size();
			// 真正的弧度
			double realRadian = randomRadian + radian;
			Integer x = new Integer(fatherNode.getX() + (int)(Math.cos(realRadian) * (radius * 2)));
			Integer y = new Integer(fatherNode.getY() + (int)(Math.sin(realRadian) * (radius * 2)));
			NodeVO node = new NodeVO(nodesNameList.get(n), x, y);
			nodes.add(node);
		}
		return nodes;
	}
	
	public static ArrayList<NodeVO> addNotSonsNodes(List<String> nodesNameList, NodeVO fatherNode, Integer radius){
		// 其他节点不同于儿子节点，渲染是处于最外层
		ArrayList<NodeVO> nodes = new ArrayList<>();
		for(int n = 0; n < nodesNameList.size(); n++) {
			double radian = 2 * Math.PI * (n + 1) / nodesNameList.size();
			Integer x = new Integer((int)(fatherNode.getX() + Math.cos(radian) * radius));
			Integer y = new Integer((int)(fatherNode.getY() + Math.sin(radian) * radius));
			NodeVO node = new NodeVO(nodesNameList.get(n), x, y);
			nodes.add(node);
		}
		return nodes;
	}
}
