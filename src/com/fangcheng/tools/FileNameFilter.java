package com.fangcheng.tools;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameFilter implements FilenameFilter{
	private String regular=null; 
	private String ftime; 

	public FileNameFilter(String regular, String time) {
		this.regular = regular;
	    this.ftime = time; 
	}
	
	public FileNameFilter(String time) {
		this.regular = null;
	    this.ftime = time; 
	}
	@Override
	public boolean accept(File dir, String filename) {
		boolean fileOK = false;
		if(regular!=null && filename.matches(regular)&& filename.matches(".*?"+ftime+".*?"))
			fileOK=true;
		if(regular==null && filename.matches(".*?"+ftime+".*?"))
			fileOK=true;
		
	    return fileOK;
	}
}

