package cn.fate.ssm.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * FastDFS文件上传下载包装类
 *
 * @author shangzf
 * @date 2019-03-01 14:40
 */
public class FastDfsClientWrapper {

    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient storageClient;

    static {
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] upload(MultipartFile file) {
        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(file.getBytes(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return uploadResults;
    }
}

