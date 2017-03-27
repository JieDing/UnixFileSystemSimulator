package com.jieding.controller;

import static com.jieding.controller.constants.ControllerConstant.*;

import java.io.File;

import com.jieding.model.FileSystemTree;
public class CommandController {
	
	private FileSystemTree fst  ;
	private String inputCmd;
	private String cName;
	
	public CommandController(){
		fst= new FileSystemTree();
	}
	public void setInputCmd(String inputCmd){
		this.inputCmd = inputCmd;
		splitInput(inputCmd);
	}
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	private String option;
	private String[] arguments;
	
	
	public boolean isCommandValid(){
		for(String s: SYSTEMCOMMANDS){
			if(s.equals(cName))
				return true;
		}
		return false;
	}
	
	private void splitInput(String inputCmd) {
		// TODO Auto-generated method stub
		if(!inputCmd.contains(" ")){
			this.cName = inputCmd;
			this.option = null;
			this.arguments = null;
		}else{
			inputCmd = inputCmd.replaceAll(" +"," ");
			if(!inputCmd.contains(" ")){
				this.cName = inputCmd;
				this.option = null;
				this.arguments = null;
			}
			else{
				this.cName = inputCmd.substring(0,inputCmd.indexOf(" "));
				inputCmd = inputCmd.substring(inputCmd.indexOf(" ")+1);
				arguments = inputCmd.split(" ");
				for(int i=0; i<arguments.length; i++){
					if(arguments[i].startsWith("--")){
						this.option =  arguments[i];
						arguments[i] = null;
					}
						
				}
			}
		}
	}
	
	public String exectueCommand(){
		
		
		switch(cName){
			case "commands" : {
				//System.out.println(System.getProperty("user.home"));
				
				return BUILTINCOMMANDS;
				
			}
			case "pwd" :{
				return fst.getCurrentPath();
			}
			case "mkdir" :{
				if(arguments == null){
					return MKDIR_NULL_ARGUMENTS;
				}
				if(arguments.length>1){
					return MKDIR_TOO_MANY_ARGUMENTS;
				}
				else{
					//System.out.println(arguments[0]);
					return fst.makeDirectory(arguments[0]);
				}
				
			}
			default: 
				return null;
				
		}
	}
}
