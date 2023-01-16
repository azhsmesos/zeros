package com.github.zeros.storage;

import static com.github.zeros.storage.config.Serialization.encode;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zeros.storage.config.Entry;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-16
 */
public class AppendFile {

    private static final Logger logger = LoggerFactory.getLogger(AppendFile.class);

    private final static String SUFFIX = ".data";

    private String rootPath, filename;

    private AtomicReference<IndexFile> indexFile = new AtomicReference<>(null);

    private AtomicReference<MappedByteBuffer> mappedByteBuffer = new AtomicReference<>(null);

    public AppendFile(String rootPath, String filename) throws IOException {
        this.rootPath = rootPath;
        this.filename = filename;
        ensureFileExist();
    }

    private void ensureFileExist() throws IOException {
        createNewFile();
    }

    private void createNewFile() throws IOException {
        String newFilename = filename + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String newIndexFilename = newFilename;
        newFilename += SUFFIX;
        File file = new File(rootPath + "/" + newFilename);
        mkdirsAndCreateFile(file);
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        FileChannel channel = accessFile.getChannel();
        mappedByteBuffer.set(channel.map(MapMode.READ_WRITE, 0, 90));
        indexFile.set(new IndexFile(rootPath, newIndexFilename));
//        channel.close();
    }

    private void mkdirsAndCreateFile(File file) {
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                logger.error("Exec mkdirs command error, file path: {}", file.getAbsoluteFile());
                return;
            }
        }
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    logger.error("create new file error, file path: {}", file.getAbsoluteFile());
                    return;
                }
            }
            logger.info("create new file success, path: {}", file.getAbsoluteFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void append(Entry entry) {
        mappedByteBuffer.get().position(mappedByteBuffer.get().position());
        System.out.println(mappedByteBuffer.get().position());
        System.out.println(new String(encode(entry)));
        mappedByteBuffer.get().put(encode(entry));
    }
}
