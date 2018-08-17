package org.atom.genarator.process;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.atom.genarator.Factory.EmployeeFactory;
import org.atom.genarator.dto.Employee;
import org.atom.genarator.util.FileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataProcessor {

	private static final int QUEUE_SIZE = 100;

	private static int EMP_COUNT = 0;
	BlockingQueue<Employee> employeeQue = new ArrayBlockingQueue<>(QUEUE_SIZE);

	@Autowired
	private EmployeeFactory employeeFactory;

	@Autowired
	private FileWriter fileWriter;


	public void processor(int totalRecords) {


		Runnable dataProducer =  ()-> {

			int producerCount = 0;
			while(true) {

				synchronized (this) {

					while (employeeQue.size()==QUEUE_SIZE) {
						try {
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}					
					}
					Employee emp = employeeFactory.getEmployee();
					try {
						employeeQue.put(emp);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					producerCount++;
					System.out.println("========Data Produces====="+producerCount + "Employee Id is:" + emp.getId());
					notify();
					/*try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}

				if(totalRecords==producerCount) {
					break;
				}

			}
		};


		Runnable dataConsumer = ()->{

			int producerCount = 0;

			while(true) {
				synchronized (this) {

					while(employeeQue.isEmpty()) {
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					try {
						Employee employee = employeeQue.take();
						StringBuffer sb = new StringBuffer();
						sb.append(employee.getId());
						sb.append(",");
						sb.append(employee.getFirstName());
						sb.append(",");
						sb.append(employee.getLastName());
						sb.append(",");
						sb.append(employee.getPhoneNo());
						sb.append("\n");

						fileWriter.fileWrite(sb.toString());
						producerCount++;
						System.out.println("---------Data Consumed-----"+producerCount+" employee id: "+employee.getId());
					} catch (InterruptedException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					notify();
					/*try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}

				if(totalRecords==producerCount) {
					break;
				}

			}


		};

		Thread producerThread = new Thread(dataProducer);
		Thread consumerThread = new Thread(dataConsumer);

		
		producerThread.start();
		consumerThread.start();

		try {
			producerThread.join();
			consumerThread.join();

			fileWriter.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
