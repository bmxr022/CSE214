/*
 * Zach Samuels
 * 108941490
 * samuels.zach@gmail.com
 * HW #5
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw5;

public class ParticleNode {

	private String name = "";
	private ParticleNode leftChild = null;
	private ParticleNode rightChild = null;
	
	/**
	 * Constructor.
	 * @param name Name of node.
	 */
	
	public ParticleNode(String name) {
		this.name = name;
	}
	
	/**
	 * Assign a left child to this node.
	 * @param leftChild Node to become left child.
	 */
	
	public void setLeftChild(ParticleNode leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * Return the left child of this node.
	 * @return Left child node.
	 */
	
	public ParticleNode getLeftChild() {
		return leftChild;
	}
	
	/**
	 * Assign a right child to this node.
	 * @param rightChild Node to become right child.
	 */
	
	public void setRightChild(ParticleNode rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * Return the right child of this node.
	 * @return Right child node.
	 */
	
	public ParticleNode getRightChild() {
		return rightChild;
	}
	
	/**
	 * Returns name of this ParticleNode.
	 */
	
	public String toString() {
		return name;
	}
}
