package by.epam.movierating.controller.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by Даша on 29.03.2017.
 */
public class UploadUtil {
    private static final String TEMP_DIR = "javax.servlet.context.tempdir";
    private static final String ACTOR = "actor";
    private static final String MOVIE = "movie";
    private static final String TARGET_PATH = "target\\movie_rating\\WEB-INF";
    private static final String ACTOR_PATH = "src\\main\\webapp\\images\\actor";
    private static final String MOVIE_PATH = "src\\main\\webapp\\images\\poster";
    private static final String TARGET_ACTOR_PATH = "\\images\\actor\\";
    private static final String TARGET_MOVIE_PATH = "\\images\\poster\\";
    private static final String IMAGES_ACTOR = "images\\actor\\";
    private static final String IMAGES_POSTER = "images\\poster\\";
    private static final String WEB_INF = "\\WEB-INF\\";

    public static ServletFileUpload createServletFileUpload(HttpServletRequest request) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);

        ServletContext servletContext = request.getServletContext();
        File tempDir = (File) servletContext.getAttribute(TEMP_DIR);
        factory.setRepository(tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024 * 10);
        return upload;
    }

    public static String processUploadedFile(FileItem item, ServletContext servletContext,
                                             String entityName)
            throws Exception {
        File uploadedFile;

        String path = servletContext.getRealPath(WEB_INF + item.getName());
        String programPath = buildProgramImagePath(entityName, path);
        uploadedFile = new File(programPath);
        //noinspection ResultOfMethodCallIgnored
        uploadedFile.createNewFile();
        item.write(uploadedFile);

        String targetPath = buildTargetPath(entityName, path);
        uploadedFile = new File(targetPath);
        //noinspection ResultOfMethodCallIgnored
        uploadedFile.createNewFile();
        item.write(uploadedFile);

        return buildPathForEntity(entityName, item.getName());
    }

    private static String buildProgramImagePath(String entityName, String oldPath) {
        switch (entityName) {
            case ACTOR:
                return oldPath.replace(TARGET_PATH, ACTOR_PATH);
            case MOVIE:
                return oldPath.replace(TARGET_PATH, MOVIE_PATH);
            default:
                return "";
        }
    }

    private static String buildTargetPath(String entityName, String oldPath) {
        switch (entityName) {
            case ACTOR:
                return oldPath.replace(WEB_INF, TARGET_ACTOR_PATH);
            case MOVIE:
                return oldPath.replace(WEB_INF, TARGET_MOVIE_PATH);
            default:
                return "";
        }
    }

    private static String buildPathForEntity(String entityName, String currentName) {
        switch (entityName) {
            case ACTOR:
                return IMAGES_ACTOR + currentName;
            case MOVIE:
                return IMAGES_POSTER + currentName;
            default:
                return "";
        }
    }

}
