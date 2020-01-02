package br.com.api.restwithspringboot.data.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponseVO implements Serializable{
	

	private static final long serialVersionUID = -8457963044633837136L;

	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;

}
