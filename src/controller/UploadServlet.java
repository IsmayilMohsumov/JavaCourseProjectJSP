package controller;
import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDao;
import DAO.UserDaoImplement;
import ModelDTO.UserDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

public class UploadServlet extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 100 * 1024;//kb
    private int maxMemSize = 4 * 1024;
    private File file ;

    public void init( ){
        // Get the file location where it would be stored.
        filePath = getServletContext().getRealPath("/")+"upload"+File.separator;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();


        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        System.out.println(filePath);
        UserDao userDao = new UserDaoImplement();

        HttpSession session= request.getSession(true);
        UserDTO user = (UserDTO) session.getAttribute("loginUser");

        Integer userId= user.getId();


        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File(filePath));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );

        try {
            // Parse the request to get file items.
            List<FileItem> fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator<FileItem> i = fileItems.iterator();

            while ( i.hasNext () ) {
                FileItem fi = i.next();
                if ( !fi.isFormField () ) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                    String writeName = UUID.randomUUID().toString()+fileExtension;

                    OutputStream writer = new FileOutputStream(new File(filePath+writeName));
                    writer.write(fi.get());
                    writer.close();

                    userDao.changeImage(userId,writeName);
                    user.setImageUrl(writeName);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName = filePath+"a38cbace-f14b-4a91-bb11-c81c25556e66.pptx";
        // Find this file id in database to get file name, and file type

        // You must tell the browser the file type you are going to send
        // for example application/pdf, text/plain, text/html, image/jpg
        response.setContentType("APPLICATION/OCTET-STREAM");

        // Make sure to show the download dialog
        response.setHeader("Content-Disposition","attachment; filename=" + fileName );

        // Assume file name is retrieved from database
        // For example D:\\file\\test.pdf

        File my_file = new File(fileName);

        // This should send the file to browser
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    public static void main(String[] args) {
        String fileName = "SQL-2.pptx";
        String s = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(s);
    }
}