/**
 * @author Brandon Nsidinanya
 * A class that finds all files of a certain type within the directories of your computer
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileGrabber {
	
	private Map<String, File> fileMap;
	private String fileType;
	
	public FileGrabber(String fileType) {
		fileMap = new HashMap<String, File>();
		this.fileType = fileType;
	}
	
	/**
	 * Returns the map that maps String filenames to File objects
	 * @return The map that contains filenames and their corresponding File objects
	 */
	public Map<String, File> getFileMap() {
		return fileMap;
	}
	
	/**
	 * Returns the file type that you are searching for on your computer
	 * @return The String of the file type that you are looking for
	 */
	public String getFileType() {
		return fileType;
	}
	
	/**
	 * Sets the type of file that you wish to look for on your computer
	 * @param fileType The type of file that you are looking for
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	/**
	 * Goes through every directory on your computer, starting from the root directories, and finds
	 * every file of the specified file type that is present on your computer.
	 */
	public void findFiles() {
		for(File file: File.listRoots())
			findFilesHelper(file);
	}
	
	/**
	 * Recursively finds every file of a specified type on your computer
	 * @param file The current file or directory that is being looked at
	 */
	private void findFilesHelper(File file) {
		if(file.isFile()) {
			if(fileType.equals(getFileTypeOfFile(file)))
				fileMap.put(file.getName(), file);
		} else if(file.isDirectory()) {
			File[] files = file.listFiles();
			if(files != null) {
				for(File path: file.listFiles()) {
					findFilesHelper(path);
				}
			}
		}
	}
	
	/**
	 * Returns the file type of a file
	 * @param file The file whose type is being checked
	 * @return The type of file of the file. Returns null if the file doesn't have an 
	 * explicitly specified type
	 */
	private String getFileTypeOfFile(File file) {
		String name = file.getName();
		int index = name.length() - 1;
		while(index >= 0 && name.charAt(index) != '.') 
			index--;
		return index < 0 ? null : name.substring(index);
	}
	
	/**
	 * Gives a list of the files of a specified type that were located on your computer
	 * @return A List of Files of one specified type
	 */
	public List<File> getPaths(){
		List<File> paths = new ArrayList<>();
		for(String key: fileMap.keySet())
			paths.add(fileMap.get(key));
		return paths;
	}
	
	//a program to test the FileGrabber class
	//the file type must start with a dot (.)
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter file type: ");
		String fileType = scan.nextLine();
		System.out.println("Grabbing " + fileType + " Files...\n");
		FileGrabber fg = new FileGrabber(fileType);
		fg.findFiles();
		Map<String, File> map = fg.getFileMap();
		if(map.size() == 0)
			System.out.println("No such files found");
		else
			for(String file: map.keySet())
				System.out.println(file);
		scan.close();
	}
}
