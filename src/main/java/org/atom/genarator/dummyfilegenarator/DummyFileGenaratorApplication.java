package org.atom.genarator.dummyfilegenarator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author SSadhukhan
 *
 * This is launcher class
 *
 *
 */

@SpringBootApplication(scanBasePackages= {"org.atom.genarator"})
public class DummyFileGenaratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DummyFileGenaratorApplication.class, args);
	}
}
