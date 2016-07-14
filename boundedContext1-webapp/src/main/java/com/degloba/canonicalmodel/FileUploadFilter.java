package com.degloba;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.gmr.web.multipart.GFileItemFactory;
import org.primefaces.webapp.MultipartRequest;

public class FileUploadFilter implements Filter {

    private final static Logger logger = Logger.getLogger(FileUploadFilter.class.getName());

    private final static String THRESHOLD_SIZE_PARAM = "thresholdSize";

    private final static String UPLOAD_DIRECTORY_PARAM = "uploadDirectory";

    private String thresholdSize;

    private String uploadDir;

    public void init(FilterConfig filterConfig) throws ServletException {
        thresholdSize = filterConfig.getInitParameter(THRESHOLD_SIZE_PARAM);
        uploadDir = filterConfig.getInitParameter(UPLOAD_DIRECTORY_PARAM);

        if(logger.isLoggable(Level.FINE))
            logger.fine("FileUploadFilter initiated successfully");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        boolean isMultipart = ServletFileUpload.isMultipartContent(httpServletRequest);

        if(isMultipart) {
            if(logger.isLoggable(Level.FINE))
                logger.fine("**************Parsing file upload request");

            //start change
            FileItemFactory diskFileItemFactory = new GFileItemFactory(); 
           /* if(thresholdSize != null) {
                diskFileItemFactory.setSizeThreshold(Integer.valueOf(thresholdSize));
            }
            if(uploadDir != null) {
                diskFileItemFactory.setRepository(new File(uploadDir));
            }*/
            //end change

            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            MultipartRequest multipartRequest = new MultipartRequest(httpServletRequest, servletFileUpload);

            if(logger.isLoggable(Level.FINE))
                logger.fine("File upload request parsed succesfully, continuing with filter chain with a wrapped multipart request");

            filterChain.doFilter(multipartRequest, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    public void destroy() {
        if(logger.isLoggable(Level.FINE))
            logger.fine("Destroying FileUploadFilter");
    }

}



