package com.joao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class FileUploadManagedBean {

	UploadedFile file;
	String teste;
	StreamedContent imagem;
	

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}
	
	

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

	public String dummyAction(){
		System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
		return "";
	}
	
	public void fileUploadListener(FileUploadEvent e){
		this.setTeste("File uploaded at: "+LocalDateTime.now());
		this.file = e.getFile();
		System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
	}
	
    public StreamedContent abrirImagem(){
        
            StreamedContent imagem = null;
			try {
				imagem = new DefaultStreamedContent(this.file.getInputstream());
				setImagem(imagem);
			} catch (IOException e) {
				e.printStackTrace();
			}
            return imagem;
        
    }	
	
}
