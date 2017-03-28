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
	public String makeDirectories(String path){
		if(path.equals("/"))
			return MKDIR_ROOT;
		if(path.endsWith("/"))
			path = path.substring(0,path.lastIndexOf("/"));
		//use absolute path
		if(path.startsWith("/")){
			Node n = findNodeByAbsPath(path);
			if(n!=null)
				return MKDIR_ERROR+path+MKDIR_EXISTING;
			else{
				forcingCreateNodeByAbsPath(path);
			}
		}
		//use relative path
		else{
			
		}
		
		return null;
	}
	
	public String listFilesByAbsPath(String absPath, boolean detailed){
		if(!detailed){
			if(absPath==null){
				Node n = currentNode.firstChild;
				StringBuilder sb = new StringBuilder();
				int i=0;
				while(n!=null){
					
					sb.append(n.file.getName()+"\t");
					i++;
					if(i%2 ==4)
						sb.append("\n");
					n = n.nextSibling;
				}
				
				return sb.toString();
			}else{
				
			}
		}else{
			
		}
		return null;
	}
	
	private void forcingCreateNodeByAbsPath(String path){
		Node parent = rootNode;
		Node n = parent.firstChild;
		String dirName = "";
		StringBuilder pathValue = new StringBuilder();
		while(path.contains("/")){
			
			
			
			
			if(path.substring(path.indexOf("/")+1).contains("/")){
				String s = path.substring(path.indexOf("/")+1);
				pathValue.append("/");
				pathValue.append(s.substring(0,s.indexOf("/")));
				
			}
			else{
				
				pathValue.append(path.substring(0,path.length()));
				
			}
			
			//remove the "/"
			path = path.substring(path.indexOf("/")+1);
			String nodeName = null;
			if(path.contains("/")){
				nodeName= path.substring(0,path.indexOf("/"));
				path = path.substring(path.indexOf("/"));
			}
			else nodeName = path;
			n = traverseTree(n,nodeName);
			
			if(n==null){
			
				dirName = translateToActualPath(parent.path);
				
				File f = new File(dirName,nodeName);
				
				if(!f.exists()){
					
					f.mkdir();
				}
				
				 Node newNode = new Node(parent,null,null,pathValue.toString(),f);
				 if(parent.firstChild!=null){
						Node x = null;
						for(x =parent.firstChild;x.nextSibling!=null; ){
							x = x.nextSibling;
						}
						x.nextSibling = newNode;
					}
					else
						parent.firstChild = newNode;
				 if(path.contains("/"))
					 parent = newNode;
			}
			else{
				if(path.contains("/")){
					parent = n;
					
				}
			}	
		}
	}
	
	public String makeDirectory(String path){
		
		//cannot create root directory
		if(path.equals("/"))
			return MKDIR_ROOT;
		//if the path ends with "/", we directory remove the last "/"
		if(path.endsWith("/"))
			path = path.substring(0,path.lastIndexOf("/"));
		//use the absolute path
		
		Node parent = null;
		String dirName = null;
		Node newNode = null;
		String pathValue = null;
		
		if(path.startsWith("/")){
			parent = findParentByAbsPath(path);
			
			if(path.substring(path.indexOf("/")+1).contains("/"))
				dirName = obtainFileDirectory(path);
			else
				dirName = "/";
			
			pathValue = path;
		}
		//use the relative path
		else{
			parent = findParentByRelativePath(path);
			if(path.contains("/")){
				if(currentNode == rootNode)
					dirName = "/"+obtainFileDirectory(path);
				else
					dirName = parent.path+"/"+obtainFileDirectory(path);
				pathValue = dirName+ "/"+obtainFileName(path);
			}
			else{
				if(currentNode == rootNode){
					dirName = currentNode.path;
					pathValue = dirName+obtainFileName(path);
				}
				else{
					dirName = currentNode.path;
					pathValue = dirName+ "/"+obtainFileName(path);
				}
			}
			
		}
		
		if(parent ==null)
			return MKDIR_ERROR+path+MKDIR_MISSING;
		
		
		String fileName = obtainFileName(path);
		dirName = translateToActualPath(dirName);
		
		File f = new File(dirName,fileName);
		
		if(!f.exists()){
			f.mkdir();
		}else{
			return MKDIR_ERROR+path+MKDIR_EXISTING;
		}
		
		 newNode = new Node(parent,null,null,pathValue,f);
		
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
		
		size++;	
				
		
		return null;
	}
	
	private Node findParentByRelativePath(String path){
		Node n = currentNode.firstChild;
		
		if(!path.contains("/"))
			return currentNode;
		
		while(path.contains("/")){
			
			String nodeName= path.substring(0,path.indexOf("/"));
			path = path.substring(path.indexOf("/"));
			
			n = traverseTree(n,nodeName);
			
			if(!path.substring(path.indexOf("/")+1).contains("/"))
				return n;
			if(n.firstChild==null)
				return null;
			n = n.firstChild;
			path = path.substring(path.indexOf("/")+1);
		}
		return n;
	}
	private Node findNodeByAbsPath(String path){
		Node n = rootNode.firstChild;
		while(path.contains("/")){
			//remove the "/"
			path = path.substring(path.indexOf("/")+1);
			String nodeName = null;
			if(path.contains("/")){
				nodeName= path.substring(0,path.indexOf("/"));
				path = path.substring(path.indexOf("/"));
			}
			else nodeName = path;
			n = traverseTree(n,nodeName);
			if(n ==null)
				return null;
			if(path.contains("/"))
				n = n.firstChild;
		}
		return n;
	}
	private Node findParentByAbsPath(String path) {
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
		
			path = path.substring(1);
			path = actualHomeDirectory+path;
			path = path.replace("/", "\\");
			
		
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
