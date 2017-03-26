package com.jieding.model;
/**
 * 
* @ClassName: Command 
* @author JieDing dingjwilliams@gmail.com
* 
* @Description:  Define the Command model.
 */
public class Command {
	
	//command name
	private String cName;
	private String option;
	private String[] arguments;
	
	public Command(String cName, String option, String[] arguments){
		this.cName = cName;
		this.option = option;
		this.arguments = arguments;
	}

	public String getcName() {
		return cName;
	}

	public String getOption() {
		return option;
	}

	public String[] getArguments() {
		return arguments;
	}

	
	
	
}
