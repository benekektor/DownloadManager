package com.chmnu_ki_123.k3;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloadTask implements Runnable {
    private final String fileUrl;
    private final String outputPath;
    private final DownloadManager manager;

    public DownloadTask(String fileUrl, String outputPath, DownloadManager manager) {
        this.fileUrl = fileUrl;
        this.outputPath = outputPath;
        this.manager = manager;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(outputPath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
                synchronized (manager) {
                    while (manager.isPaused()) {
                        manager.wait();
                    }
                    if (manager.isStopped()) {
                        System.out.println("Download stopped.");
                        return;
                    }
                }
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("Download completed.");
            String fileHash = FileHasher.calculateSHA256(outputPath);
            System.out.println("SHA-256 Hash: " + fileHash);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

