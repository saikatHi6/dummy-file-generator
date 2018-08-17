package org.atom.genarator.dummyfilegenarator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"org.atom.genarator"})
public class DummyFileGenaratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DummyFileGenaratorApplication.class, args);
	}
}
