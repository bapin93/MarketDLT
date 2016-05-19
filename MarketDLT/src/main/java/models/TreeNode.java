package models;

import java.util.ArrayList;
import java.util.List;

import utilities.StockAnalysisAttribute;

public class TreeNode {
	
	private TreeNode _parent;
	private List<TreeNode> _children;
	private List<AAnalysisObject> _analysisList;
	private double _entropy;
	private boolean _homogeneous;
	private StockAnalysisAttribute _splitAttribute;
	
	public TreeNode() {
		
	}
	
	public TreeNode(final List<AAnalysisObject> analysisList) {
		_analysisList = analysisList;
		_entropy = 0;
		_homogeneous = false;
		_children = new ArrayList<TreeNode>();
	}
	
	public TreeNode(final TreeNode parent, final List<AAnalysisObject> analysisList) {
		_parent = parent;
		_parent.addChild(this);
		_entropy = 0;
		_homogeneous = false;
		_children = new ArrayList<TreeNode>();
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
	
	public double getEntropy() {
		return _entropy;
	}
	
	public void setEntropy(final double entropy) {
		_entropy = entropy;
	}
	
	public boolean isHomogeneous() {
		return _homogeneous;
	}
	
	public void setHomogeneous(final boolean isHomogeneous) {
		_homogeneous = isHomogeneous;
	}
	
	public StockAnalysisAttribute getSplitAttribute() {
		return _splitAttribute;
	}
	
	public void setSplitAttribute(final StockAnalysisAttribute attribute) {
		_splitAttribute = attribute;
	}
}
