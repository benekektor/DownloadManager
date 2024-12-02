package com.chmnu_ki_123.k3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DownloadManagerTest {

    @Test
    void testPauseAndResume() {
        DownloadManager manager = new DownloadManager();

        manager.pauseDownload();
        assertTrue(manager.isPaused());

        manager.resumeDownload();
        assertFalse(manager.isPaused());
    }

    @Test
    void testStop() {
        DownloadManager manager = new DownloadManager();

        manager.stopDownload();
        assertTrue(manager.isStopped());
    }
}
