/*
 * Zach Samuels
 * 108941490
 * samuels.zach@gmail.com
 * HW #5
 * CSE214
 * R05 - Vyassa Baratham
 */
package hw5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ParticleTree {

	private ParticleNode root;
	
	/**
	 * Constructs a binary tree from a text file.
	 * @param filename Name of file from which to construct the tree.
	 * @throws IOException File not found.
	 * @throws IllegalArgumentException Text file improperly formatted.
	 */
	
	public ParticleTree(String filename) throws IOException, IllegalArgumentException {
		FileInputStream fis = new FileInputStream(filename);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader buf = new BufferedReader(isr);
		
		String dataLine = buf.readLine();
		StringTokenizer st = new StringTokenizer(dataLine);
		
		root = new ParticleNode(st.nextToken());
		ParticleNode focusNode = root;
		System.out.println("Root: " + root.toString());
		
		if (st.hasMoreTokens()) {
		
			root.setLeftChild(new ParticleNode(st.nextToken()));
			System.out.println("Left: " + root.getLeftChild().toString());
		
		}
		
		if (st.hasMoreTokens()) {
			
			root.setRightChild(new ParticleNode(st.nextToken()));
			System.out.println("Right: " + root.getRightChild().toString());
			
		}
			dataLine = buf.readLine();
			st = new StringTokenizer(dataLine);
		
		while (dataLine != null) {					//for each line in file
			
			focusNode = findParticleHelper(root, st.nextToken());
			
			focusNode.setLeftChild(new ParticleNode(st.nextToken()));
			System.out.println("Left: " + focusNode.getLeftChild().toString());
			
			focusNode.setRightChild(new ParticleNode(st.nextToken()));
			System.out.println("Right: " + focusNode.getRightChild().toString());
			
			dataLine = buf.readLine();
			if (dataLine != null) {
				st = new StringTokenizer(dataLine);
			}
		}
	}
	
	/**
	 * Check whether the tree contains any ParticleNodes.
	 * @return True if ParticleTree is empty.
	 */
	
	public boolean isEmpty() {
		
		return (root == null);
		
	}
	
	/**
	 * Traverse the tree until a matching String is found.
	 * @param particleName Name of particle to search for.
	 * @return ParticleNode with matching String.
	 * @throws IllegalArgumentException No matching String was found.
	 */
	
	public ParticleNode findParticle(String particleName) throws IllegalArgumentException {
		ParticleNode result = findParticleHelper(root, particleName);
		if (result == null)
			throw new IllegalArgumentException("Didn't find that in the tree.");
		return result;
	}
	
	/**
	 * Allows findParticleNode() to only require a String parameter.
	 * Traverse the tree until a matching String is found.
	 * @param focus Node to start at.
	 * @param particleName String to search for.
	 * @return ParticleNode with matching String.
	 */
	
	private ParticleNode findParticleHelper(ParticleNode focus, String particleName) {
		
		if (focus == null)															//if focus is null, return null			
			return null;
		if (focus.toString().equalsIgnoreCase(particleName))						//if its a match, return focus
			return focus;	
		ParticleNode result = findParticleHelper(focus.getLeftChild(), particleName);		//search left
		if (result != null)
			return result;
		return findParticleHelper(focus.getRightChild(), particleName);					//search right
	}
	
	/**
	 * Get depth of tree.
	 * @return Depth of tree.
	 */
	
	public int depth() {
		return depthHelper(root, 0);
	}
	
	/**
	 * Allows the depth() method to not require any parameters.
	 * Recursively traverse the tree and return its depth.
	 * @param node Node to start at.
	 * @param depth Depth to start at.
	 * @return Depth of tree.
	 */
	
	private int depthHelper(ParticleNode node, int depth) {
		
		int left = depth;
		int right = depth;
		
		if (node.getLeftChild() != null)
			left = depthHelper(node.getLeftChild(), (depth + 1));
		if (node.getRightChild() != null)
			right = depthHelper(node.getRightChild(), (depth + 1));
		
		return (left > right ? left : right);
	}
	
	/**
	 * Inorder traversal of tree (starting from root) while printing every node's name.
	 */
	
	public void printAllParticles() {
		printAllParticlesHelper(root);
	}
	
	/**
	 * Allows printAllParticles() to have no parameters.
	 * Inorder traversal of tree while printing every node's name.
	 * @param focusNode Node to start at.
	 */
	
	private void printAllParticlesHelper(ParticleNode focusNode) {
		if (focusNode != null) {
			printAllParticlesHelper(focusNode.getLeftChild());
			System.out.println(focusNode.toString());
			printAllParticlesHelper(focusNode.getRightChild());
		}	
	}
	
	/**
	 * Preorder traversal of the tree while printing each node.
	 * @param focusNode Node to start at.
	 */
	
	public void preOrderTrav(ParticleNode focusNode) {
		if (focusNode != null) {
			System.out.println(focusNode.toString());
			preOrderTrav(focusNode.getLeftChild());
			preOrderTrav(focusNode.getRightChild());
		}
	}
	
	/**
	 * Prints decay modes of the given particle.
	 * @param particleName Name of particle.
	 * @throws IllegalArgumentException Couldn't find particle.
	 */
	
	public void printDecayModes(String particleName) throws IllegalArgumentException {
		ParticleNode focusNode = findParticle(particleName);
		preOrderTrav(focusNode.getLeftChild());
		System.out.println(focusNode.getRightChild().toString());
		preOrderTrav(focusNode.getRightChild());
		System.out.println(focusNode.getLeftChild().toString());
	}
	
	/**
	 * Returns root of the ParticleTree.
	 * @return Root of the ParticleTree.
	 */
	
	public ParticleNode getRoot() {
		return root;
	}
}
