package tdt.edu.spring.dao;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MyUploadForm {
 
    
     
    // Upload files.
    private CommonsMultipartFile[] fileDatas;
 
   
 
    public CommonsMultipartFile[] getFileDatas() {
        return fileDatas;
    }
 
    public void setFileDatas(CommonsMultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }
     
}
