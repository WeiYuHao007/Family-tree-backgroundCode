package com.orange.familyTree.pojo.util;

import java.util.ArrayList;
import java.util.List;

import com.orange.familyTree.pojo.specialPojo.NodeShowVO;

public class NodeUtil {
	
	// 节点工具
	public static ArrayList<NodeShowVO> addSonsNodes(List<String> nodesNameList, String centerName, 
			Integer cx, Integer cy, Integer radius) {
		// 儿子节点不同于其他节点，渲染是处于中间层
		ArrayList<NodeShowVO> nodes = new ArrayList<>();
		// 随机偏移量弧度
		double randomRadian = 2 * Math.PI * Math.random();
		for(int n = 0; n < nodesNameList.size(); n++) {
			// 随机偏移量弧度
			// 指定弧度
			double radian = 2 * Math.PI * (n + 1) / nodesNameList.size();
			// 真正的弧度
			double realRadian = randomRadian + radian;
			Integer x = new Integer(cx + (int)(Math.cos(realRadian) * (radius * 2)));
			Integer y = new Integer(cy + (int)(Math.sin(realRadian) * (radius * 2)));
			NodeShowVO node = new NodeShowVO(nodesNameList.get(n), x, y);
			nodes.add(node);
		}
		return nodes;
	}
	
	public static ArrayList<NodeShowVO> addNotSonsNodes(List<String> nodesNameList, String centerName, 
			Integer cx, Integer cy, Integer radius){
		// 其他节点不同于儿子节点，渲染是处于最外层
		ArrayList<NodeShowVO> nodes = new ArrayList<>();
		for(int n = 0; n < nodesNameList.size(); n++) {
			double radian = 2 * Math.PI * (n + 1) / nodesNameList.size();
			Integer x = new Integer((int)(cx + Math.cos(radian) * radius));
			Integer y = new Integer((int)(cy + Math.sin(radian) * radius));
			NodeShowVO node = new NodeShowVO(nodesNameList.get(n), x, y);
			nodes.add(node);
		}
		return nodes;
	}

	public static ArrayList<NodeShowVO> addHashNodes(List<String> nodesNameList, Double startX, Double startY,
													 Double maxX, Double maxY) {
		ArrayList<NodeShowVO> nodes = new ArrayList<>();
		Double length = maxX - startX;
		Double width = maxY - startY;
		for(int n = 0; n < nodesNameList.size(); n++) {
			Integer x = new Integer((int)(startX + length * Math.random()));
			Integer y = new Integer((int)(startY + width * Math.random()));
			NodeShowVO node = new NodeShowVO(nodesNameList.get(n), x, y);
			nodes.add(node);
		}
		return nodes;
	}
}
