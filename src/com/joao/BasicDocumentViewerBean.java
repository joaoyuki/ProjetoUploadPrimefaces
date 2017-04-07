package com.joao;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean  
@SessionScoped  
public class BasicDocumentViewerBean implements Serializable{

    private static final long serialVersionUID = 1L;  
    
    private StreamedContent content; 
    
    UploadedFile file;
    StreamedContent imagem;
  
    public void onPrerender(ComponentSystemEvent event) {  
  
        try {  
      
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
  
            Document document = new Document();  
            PdfWriter.getInstance(document, out);  
            document.open();  
  
            for (int i = 0; i < 50; i++) {  
                document.add(new Paragraph("All work and no play makes Jack a dull boy"));  
            }  
              
            document.close();  
            content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public StreamedContent getContent() {  
        return content;  
    }  
  
    public void setContent(StreamedContent content) {  
        this.content = content;  
    }  
    
    public StreamedContent abrirImagem(){
        
            StreamedContent imagem = null;
			try {
				imagem = new DefaultStreamedContent(this.file.getInputstream());
				setContent(imagem);
			} catch (IOException e) {
				e.printStackTrace();
			}
            return imagem;
    }
    
	public void fileUploadListener(FileUploadEvent e){
		
		this.file = e.getFile();
		System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}  
	
}
