package org.atom.genarator.resources;

import javax.servlet.http.HttpServletRequest;

import org.atom.genarator.dto.FileStructure;
import org.atom.genarator.service.FileServiceGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyGenaratorController {

	@Autowired
	public FileServiceGenarator fileServiceGenarator; 


	/**
	 * This is a GET method which will take no of rows need to be generate in .csv file and return as response
	 * 
	 * @param String rowSize
	 * @param HttpServletRequest request
	 * @return ResponseEntity response 
	 */
	
	@GetMapping("/dummyData/download")
	public ResponseEntity<Resource> fileDownloadWithDummyData(@RequestParam(name="row") String rowSize,HttpServletRequest request) {
		String contentType = null;
		Resource resource = null;
				try {
					long startTime = System.currentTimeMillis();

					// Load file as Resource
					resource = fileServiceGenarator.getWriteFile(Integer.parseInt(rowSize));

					System.out.println("Total time :"+(System.currentTimeMillis()-startTime));
					// Try to determine file's content type

					contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

					/* To write 1000000 row (53MB) in CSV file for different approches
					 * 88741 MS for Executor Service with 5 thread pool
					 *	889  using for loop
					 *   36275	using Producer consumer approach
					 */ 	


				}catch(Exception e) {
					e.printStackTrace();
				}

		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
	}
	
	
	/**
	 * 
	 * This is a POST end point 
	 * pass JSON request with no of rows required to generate and no of columns 
	 * return .csv file with auto generated data
	 * 
	 * @param FileStructure fileStructure
	 * @param HttpServletRequest request
	 * @return
	 */
	@PostMapping("/dummyData/download/givenCoulmn")
	public ResponseEntity<Resource> fileDownloadWithGivenColumn(@RequestBody FileStructure fileStructure,HttpServletRequest request) {
		String contentType = null;
		Resource resource = null;
				try {
					long startTime = System.currentTimeMillis();

					// Load file as Resource
					resource = fileServiceGenarator.getFileWithDynamicColums(fileStructure);

					System.out.println("Total time :"+(System.currentTimeMillis()-startTime));
					// Try to determine file's content type

					contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

					/* To write 1000000 row (53MB) in CSV file for different approches
					 * 88741 MS for Executor Service with 5 thread pool
					 *	889  using for loop
					 *   36275	using Producer consumer approach
					 */ 	


				}catch(Exception e) {
					e.printStackTrace();
				}

		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
	}

}
