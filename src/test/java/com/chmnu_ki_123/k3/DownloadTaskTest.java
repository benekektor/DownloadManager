package com.chmnu_ki_123.k3;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DownloadTaskTest {

    @Test
    void testDownloadAndHash() throws InterruptedException {
        String fileUrl = "https://www.sample-videos.com/text/Sample-text-file-10kb.txt";
        String outputPath = "test_download.txt";

        DownloadManager manager = new DownloadManager();
        DownloadTask task = new DownloadTask(fileUrl, outputPath, manager);

        Thread downloadThread = new Thread(task);
        downloadThread.start();

        downloadThread.join();

        File downloadedFile = new File(outputPath);
        assertTrue(downloadedFile.exists());

        downloadedFile.delete();
    }
}
