package models;

import java.util.List;

public class TreeNode {
	
	private TreeNode _parent;
	private List<TreeNode> _children;
	private List<StockAnalysis> _analysisList;
	
	public TreeNode() {
		
	}
	
	public TreeNode(final List<StockAnalysis> analysisList) {
		_analysisList = analysisList;
	}
	
}
