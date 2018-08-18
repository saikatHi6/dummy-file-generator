package org.atom.genarator.service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.atom.genarator.Factory.CoulmnFactory;
import org.atom.genarator.Factory.EmployeeFactory;
import org.atom.genarator.dto.Employee;
import org.atom.genarator.dto.FileStructure;
import org.atom.genarator.exception.DummyFileNotFoundException;
import org.atom.genarator.util.FileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class FileServiceGenaratorImpl implements FileServiceGenarator{

	@Autowired
	private FileWriter writer;

	private EmployeeFactory employeeFactory;

	/*@Autowired
	private DataProcessor dataProcessor;*/
	
	@Autowired
	private CoulmnFactory coulmnFactory;


	public EmployeeFactory getEmployeeFactory() {
		return employeeFactory;
	}

	@Autowired
	public void setEmployeeFactory(EmployeeFactory employeeFactory) {
		this.employeeFactory = employeeFactory;
	}

	public FileServiceGenaratorImpl() {
		System.out.println("===File Factory==");
	}

	/*
	 * 
	 * We can pass object and write multiple type object in a different file
	 * 
	 * 
	 * */
	@Override
	public void write(int noOfRecords) throws IOException {

		writer.filiWriteUsingFileWriter();

		writeToFile(noOfRecords);

		//fileWriteExecuter(noOfRecords);

		//dataProcessor.processor(noOfRecords);

		writer.close();

	}

	private void writeToFile(int noOfRecords) throws IOException {
		for(int i=0;i<noOfRecords;i++) {
			Employee employee = getEmployeeFactory().getEmployee();
			StringBuffer sb = new StringBuffer();
			sb.append(employee.getId());
			sb.append(",");
			sb.append(employee.getFirstName());
			sb.append(",");
			sb.append(employee.getLastName());
			sb.append(",");
			sb.append(employee.getPhoneNo());
			sb.append("\n");

			writer.fileWrite(sb.toString());
		}
	}

	private void fileWriteExecuter(int noOfRecords) {

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		for(int i=0;i<noOfRecords;i++) {
			executorService.execute(()->{
				Employee employee = getEmployeeFactory().getEmployee();
				StringBuffer sb = new StringBuffer();
				sb.append(employee.getId());
				sb.append(",");
				sb.append(employee.getFirstName());
				sb.append(",");
				sb.append(employee.getLastName());
				sb.append(",");
				sb.append(employee.getPhoneNo());
				sb.append("\n");

				try {
					writer.fileWrite(sb.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

		executorService.shutdown();

		while (!executorService.isTerminated()) {
		}
		System.out.println("Finished");
	}






	@Override
	public void write(Employee employee) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(employee.getId());
		sb.append(",");
		sb.append(employee.getFirstName());
		sb.append(",");
		sb.append(employee.getLastName());
		sb.append(",");
		sb.append(employee.getPhoneNo());
		sb.append("\n");

		writer.fileWrite(sb.toString());
	}

	@Override
	public Resource getWriteFile(int noOfRecords) throws IOException {

		Resource resource = loadFileAsResources();
		StringBuilder sb = new StringBuilder();
		sb.append("id");
		sb.append(",");
		sb.append("First Name");
		sb.append(",");
		sb.append("Last Name");
		sb.append(",");
		sb.append("Phone");
		sb.append("\n");
		writer.fileWrite(sb.toString());
		writeToFile(noOfRecords);

		//fileWriteExecuter(noOfRecords);

		//dataProcessor.processor(noOfRecords);

		writer.close();


		return resource;
	}

	private Resource loadFileAsResources() {
		try {
			Resource resource = new UrlResource(writer.getFileWithPath().toUri());

			if(resource.exists()) {
				return resource;
			}
			else {
				throw new DummyFileNotFoundException("File Not found");
			}

		} catch (IOException e) {
			throw new DummyFileNotFoundException("File not found due to",e);
		}
	}

	@Override
	public Resource getFileWithDynamicColums(FileStructure fileStructure) throws IOException {

		Resource resource = loadFileAsResources();
		writer.fileWrite(coulmnFactory.createFileStructure(fileStructure));
		for (int i = 0; i < fileStructure.getRows(); i++) {
			writer.fileWrite(coulmnFactory.genarateDummyDataInFile(fileStructure,i));
		}
		writer.close();
		return resource;
	}

}
