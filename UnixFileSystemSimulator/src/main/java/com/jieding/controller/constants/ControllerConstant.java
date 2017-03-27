package com.jieding.controller.constants;


public class ControllerConstant {
	public static final String[] SYSTEMCOMMANDS = {"commands","cd","mkdir","ls","rm","vim","mv","pwd"};
	public static final String[] EDITORCOMMANDS = {":wq",":q!"};
	public static final String INVALIDCOMMAND = " command is not a valid system built-in command\n"
			+ "ENTER ' commands ' to see all available commands!!";
	public static final String BUILTINCOMMANDS = "  **************************************************************************************************************************************************\n"
			+ "  *"+"  "+"commands"+"                "+"list all of available system commands."+"                                                                             *"+"\n"
			+ "  *"+"  "+"cd"+"                              "+"allow you to change your working directory."+"                                                                   *"+"\n"
			+ "  *"+"  "+"mkdir"+"                         "+"allow you to create directories."+"                                                                                        *"+"\n"
			+ "  *"+"  "+"ls"+"                               "+"allow you to list the content of a directory."+"                                                                      *"+"\n"
			+ "  *"+"  "+"rm"+"                              "+"allow you to remove files or directories."+"                                                                         *"+"\n"
			+ "  *"+"  "+"vim"+"                            "+"allow you to create new files or edit existed files."+"                                                         *"+"\n"
			+ "  *"+"  "+"mv"+"                             "+"allow you to move files or directories to another path."+"                                                 *"+"\n"
			+ "  *"+"  "+"pwd"+"                           "+"allow you to print the name of current working directory."+"                                            *"+"\n"
			+ "  *"+"                                                                                                                                                                                   *"+"\n"
			+ "  *"+"                                                                                                                                                                                   *"+"\n"
			+ "  *"+"      "+"Tips:  use 'command' --help  to see the detailed usage of that command"+"                                                 *"+"\n"
			+ "  *"+"                 "+"i.e.   cd --help, mkdir --help, ls --help    ..."+"                                                                                            *"+"\n"
			+ "  *"+"                                                                                                                                                                                   *"+"\n"
			+ "  **************************************************************************************************************************************************\n";
	
	public static final String MKDIR_NULL_ARGUMENTS = "mkdir command needs one argument\n"
			+ "i.e.  mkdir /abc indicates creating directory abc under the root directory \n"
			+ "       mkdir abc indicates creating directory abc under the current directory";
	public static final String MKDIR_TOO_MANY_ARGUMENTS = "mkdir command can only accept one argument\n"
			+ "i.e.  mkdir /abc indicates creating directory abc under the root directory \n"
			+ "       mkdir abc indicates creating directory abc under the current directory";
	public static final String MKDIR_ROOT = "'/' represents root directory, which cannot be created";
	public static final String MKDIR_ERROR = "cannot create directory \"";
	public static final String MKDIR_MISSING = "\" : No such file or directory!";
	public static final String MKDIR_EXISTING = "\" : the file or directory already exists!";
}
