package org.atom.genarator.service;

import java.io.IOException;

import org.atom.genarator.dto.Employee;
import org.atom.genarator.dto.FileStructure;
import org.springframework.core.io.Resource;

public interface FileServiceGenarator {
		//public void write() throws IOException;

		/**
		 * 
		 * To write data with no of records needed
		 * @param noOfRecords
		 * @throws IOException
		 */
		void write(int noOfRecords) throws IOException;
		
		/**
		 * To write fixed no of colums
		 * @param employee
		 * @throws IOException
		 */
		void write(Employee employee) throws IOException;
		
		/**
		 * 
		 * To write data with no of records needed
		 * @param noOfRecords
		 * @return
		 * @throws IOException
		 */
		Resource getWriteFile(int noOfRecords) throws IOException;
		
		/**
		 * To generate dynamic columns in .csv file
		 * @param fileStructure
		 * @return
		 * @throws IOException
		 */
		Resource getFileWithDynamicColums(FileStructure fileStructure) throws IOException;

}
