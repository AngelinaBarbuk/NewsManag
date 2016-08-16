package by.revotechs.barbuk;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by VVV on 03.08.2016.
 */
public class ImageService {

    private static String getFileName(final Part part){
        for(String content:part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content.substring(content.indexOf('=')+1).trim().replace("\"","").replace(" ","");
            }
        }
        return null;
    }

    public static String uploadImage(HttpServletRequest request, String title, Date date) throws IOException, ServletException {
        Part part= request.getPart("image");
        if(part.getSubmittedFileName().isEmpty()){
            return null;
        }
        final String fileName = getFileName(part);
        String path = "D:\\DBImages";
        try {
            String filePath = path+File.separator+title+fileName;
            OutputStream out = new FileOutputStream(new File(path+File.separator+title+date+fileName));
            InputStream fileContent = part.getInputStream();
            int read=0;
            final byte[] bytes= new byte[1024];
            while ((read=fileContent.read(bytes))!=-1)
                out.write(bytes,0,read);
        }catch (FileNotFoundException ex){

        }
        return "http://localhost:8082/"+title+date+fileName;
    }
}
