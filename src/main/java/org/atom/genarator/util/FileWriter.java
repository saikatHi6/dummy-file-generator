package org.atom.genarator.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Service;

@Service
public class FileWriter {
	
	
	
	private BufferedWriter writer;
	private FileChannel rwChannel;
	
	//flaws cause it is creating only one file
	
	public FileWriter() throws IOException {
		
	}
	
	public void fileWrite(String newLine) throws IOException {
		
		//Path path  = Paths.get("output"+System.currentTimeMillis()+".csv");
		//try(BufferedWriter writer = Files.newBufferedWriter(path,StandardOpenOption.CREATE_NEW))
			writer.write(newLine);
	}
	
	public  void close() throws IOException {
		writer.close();
	}
	
	public Path filiWriteUsingFileWriter() throws IOException {
		Path path  = Paths.get("output"+System.currentTimeMillis()+".csv");
		System.out.println(path);
		writer = Files.newBufferedWriter(path,StandardOpenOption.CREATE_NEW);
		StringBuilder sb = new StringBuilder();
		sb.append("id");
		sb.append(",");
		sb.append("First Name");
		sb.append(",");
		sb.append("Last Name");
		sb.append(",");
		sb.append("Phone");
		sb.append("\n");
		fileWrite(sb.toString());
		return path;
	}
	
	
	/**
	 * Create a .csv file in class path
	 * 
	 * @return
	 * @throws IOException
	 */
	public Path getFileWithPath() throws IOException {
		Path path  = Paths.get("output"+System.currentTimeMillis()+".csv");
		System.out.println(path);
		writer = Files.newBufferedWriter(path,StandardOpenOption.CREATE_NEW);
		return path;
	}
	
	
	
	
	
	
	
	
	
	
	private void filiWriteUsingByteBuffer() throws IOException {
		Path path  = Paths.get("output"+System.currentTimeMillis()+".csv");
		System.out.println(path);
		rwChannel = new RandomAccessFile(path.toString(), "rw").getChannel();
		StringBuilder sb = new StringBuilder();
		sb.append("id");
		sb.append(",");
		sb.append("First Name");
		sb.append(",");
		sb.append("Last Name");
		sb.append(",");
		sb.append("Phone");
		sb.append("\n");
		fileWrite(sb.toString());
	}
	
	
	
	

}
