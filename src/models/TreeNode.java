package models;

import java.util.List;

public class TreeNode {
	
	private TreeNode _parent;
	private List<TreeNode> _children;
	private List<AAnalysisObject> _analysisList;
	
	public TreeNode() {
		
	}
	
	public TreeNode(final List<AAnalysisObject> analysisList) {
		_analysisList = analysisList;
	}
	
	public TreeNode(final TreeNode parent, final List<AAnalysisObject> analysisList) {
		_parent = parent;
		_parent.addChild(this);
		_analysisList = analysisList;
	}
	
	public TreeNode getParent() {
		return _parent;
	}
	
	public void setParent(final TreeNode parent) {
		_parent = parent;
	}
	
	public List<TreeNode> getChildren() {
		return _children;
	}
	
	public void setChildren(final List<TreeNode> children) {
		_children = children;
	}
	
	public void addChild(final TreeNode child) {
		_children.add(child);
	}
	
	public List<AAnalysisObject> getAnalysisList() {
		return _analysisList;
	}
}
