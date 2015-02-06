package com.pixeldonor;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadedArchiver extends Archiver {
    private FileSystem zipfs;
    private ExecutorService es = Executors.newFixedThreadPool(2);
    private Visitor visitor = null;

    class Callable implements java.util.concurrent.Callable<Integer> {
        private Path file;

        Callable(Path file) {
            super();
            this.file = file;
        }

        @Override
        public Integer call() throws Exception {
            // copy input file to ZipFileSystem
        	Path root = zipfs.getPath("/");
            final Path dest = zipfs.getPath(root.toString(),
                    file.toString());
            Files.copy(file, dest);
            return 0;
        }
    }

    class Visitor extends SimpleFileVisitor<Path> {
    	FileSystem fs;
    	
    	
        public Visitor(FileSystem fs) {
			super();
			this.fs = fs;
		}

		@Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            // ignore directories and symbolic links
            if (attrs.isRegularFile()) {
                es.submit(new Callable(file));
                incrementCount();
            }
            return FileVisitResult.CONTINUE;
        }
        
        @Override
        public FileVisitResult preVisitDirectory(Path dir,
        		BasicFileAttributes attrs) throws IOException {
        	Path root = fs.getPath("/");
        	final Path dirToCreate = fs.getPath(root.toString(),
                    dir.toString());
        	if(Files.notExists(dirToCreate)){
        		Files.createDirectory(dirToCreate);
        	}
        	
        	return FileVisitResult.CONTINUE;
        }
    }

    ThreadedArchiver(String inputDir, String outputFile) {
        super(inputDir, outputFile);
        run();
    }
    
    public static void main(String[] args) {
    	String source = "C:/non_os/projects/helloAndriod/src";
    	String output ="C:/non_os/projects/sonu.zip";
		new ThreadedArchiver(source, output);
	}

    private void createZipFileSystem() throws IOException {
        // setup ZipFileSystem
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        Path path = Paths.get(getOutputFile());
        final URI uri = URI.create("jar:file:" + path.toUri().getPath());
        zipfs = FileSystems.newFileSystem(uri, env);
//        FileSystems.
    }

    public void setEs(ExecutorService es) {
        this.es = es;
    }

    @Override
    public void run() {
        try {
             this.createZipFileSystem();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // walk input directory using our visitor class
        FileSystem fs = FileSystems.getDefault();
        try {
            Files.walkFileTree(fs.getPath(this.getInputDir()), new Visitor(zipfs));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // shutdown ExecutorService and block till tasks are complete
        es.shutdown();
        try {
            es.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            zipfs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
