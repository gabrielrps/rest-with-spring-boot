package br.com.api.restwithspringboot.services.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.restwithspringboot.config.FileStorageConfig;
import br.com.api.restwithspringboot.exception.FileNotFoundException;
import br.com.api.restwithspringboot.exception.FileStorageException;
import br.com.api.restwithspringboot.services.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService{

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageServiceImpl(FileStorageConfig fileStorageConfig) {
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be storage",e);
		}
	}

	@Override
	public String storegeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("Filename contains invalid path sequence " + fileName);
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (Exception e) {
			throw new FileStorageException("Could not store "+fileName+" the directory where the uploaded files will be storage",e);
		}
	}

	@Override
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists()) {
				return resource;
			}

			throw new FileNotFoundException("Could not found the file");
		} catch (Exception e) {
			throw new FileNotFoundException("Could not found the file",e);
		}
	}

}
