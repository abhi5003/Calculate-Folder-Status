
package com.folderStats;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class FolderStats {
    
    public long size = 0;
    public long txtCount = 0, pngCount = 0, docCount = 0, docxCount = 0, pdfCount = 0, pptxCount = 0;
    public String ext;
   
    
    public static void main(String[] args) {
        
        
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter path of folder or file in string format");
        
        String pathToFolder=sc.nextLine().trim();
        
        FolderStats obj=new FolderStats();
        
      FolderStats exp=obj.calculateStats(pathToFolder);
      
       
       
    }
   
    //Method for checking Extension
    
    public String checkExtension(String fileName ){
    int dotIndex = fileName.lastIndexOf('.');
    return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
    
    
    
    
    public FolderStats calculateStats(String pathToFolder)
    {
        
        
       File file=new File(pathToFolder);
       
       // Calling other methods
       
       displayDirectoryContents(file);
       size=getFileFolderSize(file);
        
        // Puting file types and there value in Map
        
        Map<String, Long> countByType = new HashMap<String, Long>();
        countByType.put("txt", txtCount);
        countByType.put("png", pngCount);
        countByType.put("doc", docCount);
        countByType.put("docx", docxCount);
        countByType.put("pdf", pdfCount);
        countByType.put("pptx", pptxCount);
      
        System.out.println("Desired Files Count By Type");
        System.out.println(countByType);
        System.out.println("------------------------------------------------------------------");
        
        // Converting folder size in Mb
        
        double sizeMB = (double) size / 1024 / 1024;
		String s = " MB";
		if (sizeMB < 1) {
			sizeMB = (double) size / 1024;
			s = " KB";
		}
		System.out.println("Total Folder Size : "+file.getName() + " : " + sizeMB + s);
                
                
     
        return this;
    }
    
   
    // Method of calculating folder and file size
    
    public static long getFileFolderSize(File dir) {
		long size = 0;
		if (dir.isDirectory()) {
			for (File file : dir.listFiles()) {
				if (file.isFile()) {
					size += file.length();
				} else
					size += getFileFolderSize(file);
			}
		} else if (dir.isFile()) {
			size += dir.length();
		}
		return size;
	}
    
    

	// Method for show file content or path

	public void displayDirectoryContents(File dir) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					displayDirectoryContents(file);
				} else
                                {
				System.out.println("File Canonical Path : " + file.getCanonicalPath());
                                
                                   String abc=file.getName();
                                   System.out.println("File Name : " + abc);
                                   
                                   size=file.length();
                                   System.out.println("File Size : " + size);
                                    
                                   // Calling checextension method
                                   
                                    ext = checkExtension(abc);
                                    System.out.println("FIle Extension Type : "+ext);
                                   
                                    if (ext.equals("txt")) {
                                        
                                        txtCount++;
                                       
                                    } else if (ext.equals("png")) {
                                        pngCount++;
                                    } else if (ext.equals("doc")) {
                                        docCount++;
                                    } else if (ext.equals("docx")) {
                                        docxCount++;
                                    }
                                    else if (ext.equals("pdf")) {
                                        pdfCount++;
                                    }
                                    else if (ext.equals("pptx")) {
                                        pptxCount++;
                                    }
                                   System.out.println("");
                                    
                              
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
