package com.chmnu_ki_123.k3;

public class Main {
    public static void main(String[] args) {
        String fileUrl = "https://www.sample-videos.com/zip/10mb.zip";
        String outputPath = "downloaded_file.zip";

        DownloadManager manager = new DownloadManager();
        DownloadTask task = new DownloadTask(fileUrl, outputPath, manager);

        Thread downloadThread = new Thread(task);
        downloadThread.start();

        try {
            Thread.sleep(5000);
            manager.pauseDownload();

            Thread.sleep(3000);
            manager.resumeDownload();

            Thread.sleep(5000);
            manager.stopDownload();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
