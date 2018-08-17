package org.atom.genarator.service;

import java.io.IOException;

import org.atom.genarator.dto.Employee;
import org.atom.genarator.dto.FileStructure;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

public interface FileServiceGenarator {
		//public void write() throws IOException;

		void write(int noOfRecords) throws IOException;
		
		void write(Employee employee) throws IOException;
		
		Resource getWriteFile(int noOfRecords) throws IOException;
		
		Resource getFileWithDynamicColums(FileStructure fileStructure) throws IOException;

}
