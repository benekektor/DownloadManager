package com.chmnu_ki_123.k3;

public class DownloadManager {
    private boolean isPaused = false;
    private boolean isStopped = false;

    public synchronized void pauseDownload() {
        isPaused = true;
        System.out.println("Download paused.");
    }

    public synchronized void resumeDownload() {
        isPaused = false;
        notify();
        System.out.println("Download resumed.");
    }

    public synchronized void stopDownload() {
        isStopped = true;
        isPaused = false;
        notify();
        System.out.println("Download stopped.");
    }

    public synchronized boolean isPaused() {
        return isPaused;
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
