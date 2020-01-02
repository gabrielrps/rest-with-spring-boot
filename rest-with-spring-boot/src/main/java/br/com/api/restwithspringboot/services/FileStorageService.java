package br.com.api.restwithspringboot.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	
	public String storegeFile(MultipartFile file);
	
	public Resource loadFileAsResource(String fileName);

}
