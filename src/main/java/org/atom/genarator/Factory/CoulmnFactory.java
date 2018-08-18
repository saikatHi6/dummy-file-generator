package org.atom.genarator.Factory;

import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.atom.genarator.dto.FileStructure;
import org.springframework.stereotype.Component;

/**
 * 
 * @author SSadhukhan
 *
 * Help to create fields in file and generate random data. 
 *
 *
 */

@Component
public class CoulmnFactory {


	//private static int ID = 1;
	
	/**
	 * 
	 * @author SSadhukhan
	 * @param fileStructure
	 * pass file structure to create colums in file
	 *
	 *
	 */

	public String createFileStructure(FileStructure fileStructure) {

		StringBuffer sb = new StringBuffer();
		sb.append("ID");
		sb.append(",");
		AtomicInteger countColumn = new AtomicInteger(1);
		fileStructure.getColumns().forEach((k,v)->{
			sb.append(k);
			if(fileStructure.getColumns().size()==countColumn.get()) {
				sb.append("\n");
			}
			else {
				sb.append(",");
			}
			System.out.println("Column Size: "+fileStructure.getColumns().size());
			//System.out.println("Column Increase: "+countColumn);
			countColumn.getAndAdd(1);
		});
		//sb.append("\n");


		/*for (Map<String,String> coulmn : fileStructure.getColumns()) {




			sb.append(coulmn);
			if(fileStructure.getColumns().size()==countColumn) {
				sb.append("\n");
			}
			else {
				sb.append(",");
				countColumn++;
			}
		}*/
		return sb.toString();
	}
	
	/**
	 * 
	 * @author SSadhukhan
	 *
	 * @param fileStructure
	 * @param id
	 *
	 * pass file structure for fields type and rows id 
	 *
	 */

	public String genarateDummyDataInFile(FileStructure fileStructure,int id) {

		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append(",");
		AtomicInteger countColumn = new AtomicInteger(1);
		fileStructure.getColumns().forEach((k,v)->{
			String name = null;
			switch (v) {
			case NAME:
				name = getRandomName();
				sb.append(name);
				break;
			case DECIMEL:
				sb.append(getRandomDecimel());
				break;
			case EMAIL:
				sb.append(getRandomEmail(name));
				break;
			case DATE:
				sb.append(getRandomDate());
				break;
			case NUMBER:
				sb.append(getRandomNumber());
				break;
			case PHONE:
				sb.append(getRandomPhoneNo());
				break;

			default:
				throw new RuntimeException("Invalid Input");
			}

			if(fileStructure.getColumns().size()==countColumn.get()) {
				sb.append("\n");
			}
			else {
				sb.append(",");
			}
			countColumn.getAndAdd(1);
		});
		//sb.append("\n");
		return sb.toString();

	}

	/**
	 * 
	 * @author SSadhukhan
	 *
	 * This is for generate random string
	 *
	 *
	 */
	
	private String getRandomName() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	/**
	 * 
	 * @author SSadhukhan
	 *
	 * This is for generate random number
	 *
	 *
	 */

	private int getRandomNumber() {

		Random rand = new Random();
		return rand.nextInt(1000);

	}
	
	/**
	 * 
	 * @author SSadhukhan
	 *
	 * This is for generate random decimle 
	 *
	 *
	 */

	private float getRandomDecimel() {
		return new Random().nextFloat();

	}

	/**
	 * 
	 * @author SSadhukhan
	 *
	 * This is for generate random date
	 *
	 *
	 */
	
	private String getRandomDate() {
		GregorianCalendar gc = new GregorianCalendar();

		int year = randBetween(1900, 2010);

		gc.set(gc.YEAR, year);

		int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

		gc.set(gc.DAY_OF_YEAR, dayOfYear);

		return gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);
	}

	public static int randBetween(int start, int end) {
		return start + (int)Math.round(Math.random() * (end - start));
	}

	/**
	 * 
	 * @author SSadhukhan
	 *
	 * This is for generate random email
	 *
	 *
	 */
	
	private String getRandomEmail(String name) {
		return name+"@gmail.com";
	}

	/**
	 * 
	 * @author SSadhukhan
	 *
	 * This is for generate random phone
	 *
	 *
	 */
	
	
	private String getRandomPhoneNo() {
		Random rand = new Random();
		int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
		int num2 = rand.nextInt(743);
		int num3 = rand.nextInt(10000);

		DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
		DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros

		String phoneNumber = df3.format(num1) + "-" + df3.format(num2) + "-" + df4.format(num3);
		return phoneNumber;
	}


}
