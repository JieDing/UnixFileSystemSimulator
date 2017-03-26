package com.jieding.controller;

import static com.jieding.controller.constants.ControllerConstants.*;
public class CommandController {
	private String inputCmd;
	private String cName;
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	private String option;
	private String[] arguments;
	
	public CommandController(String inputCmd){
		this.inputCmd = inputCmd;
		splitInput(inputCmd);
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
					if(arguments[i].startsWith("-")){
						this.option =  arguments[i];
						arguments[i] = null;
					}
						
				}
			}
		}
	}
	
}
