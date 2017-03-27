package com.jieding.model;

import static com.jieding.controller.constants.ControllerConstant.MKDIR_ERROR;
import static com.jieding.controller.constants.ControllerConstant.MKDIR_EXISTING;
import static com.jieding.controller.constants.ControllerConstant.MKDIR_MISSING;
import static com.jieding.controller.constants.ControllerConstant.MKDIR_ROOT;
import static com.jieding.model.constants.ModelConstant.ROOTNODE;

import java.io.File;
public class FileSystemTree{

	private Node rootNode;
	private int size = 0;
	private Node currentNode = null;
	private String actualHomeDirectory;
	
	public FileSystemTree(){
		
		File rootFile = new File(System.getProperty("user.home"),ROOTNODE);
		actualHomeDirectory = System.getProperty("user.home")+File.separator+ROOTNODE+File.separator;
		if(!rootFile.exists()){
			rootFile.mkdir();
		}
		
		rootNode = new Node(null,null,null,"/",rootFile);
		
		currentNode = rootNode;
	}
	
	public String getCurrentPath(){
		
		return currentNode.path;
	}
	
	public String makeDirectory(String path){
		
		//cannot create root directory
		if(path.equals("/"))
			return MKDIR_ROOT;
		//if the path ends with "/", we directory remove the last "/"
		if(path.endsWith("/"))
			path = path.substring(0,path.lastIndexOf("/"));
		//use the absolute path
		if(path.startsWith("/")){
			
			Node parent = findParentByPath(path);
			
			if(parent ==null)
				return MKDIR_ERROR+path+MKDIR_MISSING;
			String dirName = null;
			if(path.substring(path.indexOf("/")+1).contains("/"))
				dirName = obtainFileDirectory(path);
			else
				dirName = "/";
			String fileName = obtainFileName(path);
			dirName = translateToActualPath(dirName);
			
			File f = new File(dirName,fileName);
			if(!f.exists()){
				f.mkdir();
			}else{
				return MKDIR_ERROR+path+MKDIR_EXISTING;
			}
			
			Node newNode = new Node(parent,null,null,path,f);
			
			if(parent.firstChild!=null){
				Node n = null;
				for(n =parent.firstChild;n.nextSibling!=null; ){
					
					n = n.nextSibling;
					if(n.path.equals(path))
						return MKDIR_ERROR+path+MKDIR_EXISTING;
				}
				n.nextSibling = newNode;
			}
			else
				parent.firstChild = newNode;
			//System.out.println(rootNode.firstChild == null);
			size++;
		
		}
		//use the relative path
		else{
			
		}
		return null;
	}
	
	private Node findParentByPath(String path) {
		// TODO Auto-generated method stub
		Node n = rootNode.firstChild;
		
		if(!path.substring(path.indexOf("/")+1).contains("/"))
			return rootNode;
		
		while(path.contains("/")){
			//remove the "/"
			path = path.substring(path.indexOf("/")+1);

			String nodeName= path.substring(0,path.indexOf("/"));
			path = path.substring(path.indexOf("/"));
			
			
			
			n = traverseTree(n,nodeName);
			
			if(!path.substring(path.indexOf("/")+1).contains("/"))
				return n;
			
			if(n.firstChild==null)
				return null;
			n = n.firstChild;
			
		}
		return n;
	}
	private Node traverseTree(Node n, String nodeName) {
		// TODO Auto-generated method stub
		while(n!=null){
			
			if(n.file.getName().equals(nodeName)){
				
				return n;
			}
			n = n.nextSibling;
		}
		return null;
	}

	private String translateToActualPath(String path){
		if(path.startsWith("/")){
			path = path.substring(1);
			path = actualHomeDirectory+path;
			path = path.replace("/", "\\");
			
			//path = path.replaceFirst("/", actualHomeDirectory);
		}
		else{
			
		}
		return path;
	}
	
	
	private String obtainFileDirectory(String path){
		
		return path.substring(0,path.lastIndexOf("/"));
	}
	private String obtainFileName(String path){
		while(path.contains("/")){
			path = path.substring(path.indexOf("/")+1);
		}
		return path;
	}
	
	
	class Node{
		Node parent;
		Node firstChild;
		Node nextSibling;
		File file;
		String path;
		public File getElement() {
			return file;
		}
		
		Node(Node parent, Node firstChild, Node nextSibling, String path, File file){
			this.parent = parent;
			this.firstChild = firstChild;
			this.nextSibling = nextSibling;
			this.path = path;
			this.file = file;
			
		}
	}
}
