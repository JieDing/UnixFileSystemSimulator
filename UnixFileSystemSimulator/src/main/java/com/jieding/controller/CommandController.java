package com.jieding.controller;

import static com.jieding.controller.constants.ControllerConstant.BUILTINCOMMANDS;
import static com.jieding.controller.constants.ControllerConstant.MKDIR_NULL_ARGUMENTS;
import static com.jieding.controller.constants.ControllerConstant.MKDIR_TOO_MANY_ARGUMENTS;
import static com.jieding.controller.constants.ControllerConstant.SYSTEMCOMMANDS;
import static com.jieding.controller.constants.ControllerConstant.LS_TOO_MANY_ARGUMENTS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jieding.model.FileSystemTree;
public class CommandController {
	
	private FileSystemTree fst  ;
	private String inputCmd;
	private String cName;
	private String option;
	private List<String> arguments;
	
	
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
			this.arguments = new ArrayList<String>();
		}else{
			inputCmd = inputCmd.replaceAll(" +"," ");
			if(!inputCmd.contains(" ")){
				this.cName = inputCmd;
				this.option = null;
				this.arguments = new ArrayList<String>();
			}
			else{
				this.cName = inputCmd.substring(0,inputCmd.indexOf(" "));
				inputCmd = inputCmd.substring(inputCmd.indexOf(" ")+1);
				arguments = Arrays.asList(inputCmd.split(" "));
				arguments = new ArrayList<String> (arguments);
				for(int i=0; i<arguments.size(); i++){
					if(arguments.get(i).equals("-p")||arguments.get(i).equals("-l")){
						this.option =  arguments.get(i);
						arguments.remove(i);
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
				if(arguments.size()>1){
					return MKDIR_TOO_MANY_ARGUMENTS;
				}
				if("-p".equals(option)){
					return fst.makeDirectories(arguments.get(0));
				}
				else{
					return fst.makeDirectory(arguments.get(0));
				}
				
			}
			case "ls": {
				if(arguments.size()>1){
					return LS_TOO_MANY_ARGUMENTS;
				}
				if(arguments.size()==0 && !"-l".equals(option)){
					return fst.listFilesByAbsPath(null, false);
				}
				if(arguments.size()==0 && "-l".equals(option)){
					return fst.listFilesByAbsPath(null, true);
				}
				if(arguments.size()!=0 && !"-l".equals(option)){
					return fst.listFilesByAbsPath(arguments.get(0), false);
				}
				if(arguments.size()!=0 && "-l".equals(option)){
					return fst.listFilesByAbsPath(arguments.get(0), true);
				}
				
			}
			
			default: 
				return null;
				
		}
	}
}
