package com.foriba.jws1.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class ZipUtil {

	private static final int BUFFER = 2048;

	public static byte[] zip(String nameWithExtension, byte[] data) throws IOException {
		ByteArrayOutputStream baos = null;
		ByteArrayInputStream bais = null;
		ZipOutputStream zos = null;
		byte[] baosData;
		try {
			baos = new ByteArrayOutputStream();
			bais = new ByteArrayInputStream(data);
			zos = new ZipOutputStream(baos);
			ZipEntry zipEntry = new ZipEntry(nameWithExtension);
			zipEntry.setSize(data.length);
			zos.putNextEntry(zipEntry);

			byte[] buffer = new byte[BUFFER];
			int length = 0;
			while((length = bais.read(buffer)) > 0) {
				zos.write(buffer, 0, length);
			}
			if(zos != null) {
				zos.closeEntry();
			}
			zos.finish();
			baosData = baos.toByteArray();
			return baosData;
		}
		finally {
			
			UtilsIO.closeStream(bais, baos, zos);
		}
	}


	public static byte[] unzip(byte[] data) throws IOException {
		ZipInputStream zis = null;
		ByteArrayOutputStream baos = null;
		try {
			byte[] buffer = new byte[BUFFER];
			int length;
			baos = new ByteArrayOutputStream();
			zis = new ZipInputStream(new ByteArrayInputStream(data));
			zis.getNextEntry();
			while((length = zis.read(buffer)) > 0) {
				baos.write(buffer, 0, length);
			}
			byte[] returnData = baos.toByteArray();
			baos.flush();
			return returnData;
		}
		finally {
			UtilsIO.closeStream(zis);

		}
	}
	


	public static List<String> getEntryNames(byte[] data) throws IOException {
		List<String> entryList = new ArrayList<String>(10);
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(new ByteArrayInputStream(data));
			ZipEntry zipEntry;
			while((zipEntry = zis.getNextEntry()) != null) {
				System.out.println("size from BYTE:" + zipEntry.getSize());
				System.out.println("compressed size from BYTE:" + zipEntry.getCompressedSize());
				entryList.add(zipEntry.getName());
			}
			return entryList;
		}
		finally {
			if(zis != null)
				zis.close();
			zis = null;
		}
	}

	public static List<String> getEntryNames(File file) throws IOException {
		ZipFile zipFile = null;
		try {
			List<String> entryList = new ArrayList<String>(10);
			zipFile = new ZipFile(file);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while(entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				entryList.add(zipEntry.getName());
			}
			return entryList;
		}
		finally {
			if(zipFile != null)
				zipFile.close();
		}

	}

	public static byte[] extractZipEntry(ZipInputStream zipIn) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			byte[] bytesIn = new byte[4096];
			int read = 0;
			while((read = zipIn.read(bytesIn)) != -1) {
				bos.write(bytesIn, 0, read);
			}
			return bos.toByteArray();
		}
		finally {
			bos.close();
		}
	}

	public static void zipFilesInAFolder(String folder, FilenameFilter filenameFilter, String targetZip) {
		BufferedInputStream origin = null;
		FileOutputStream dest = null;
		ZipOutputStream out = null;
		FileInputStream fi = null;
		try {
			dest = new FileOutputStream(targetZip);
			out = new ZipOutputStream(new BufferedOutputStream(dest));
			// out.setMethod(ZipOutputStream.DEFLATED);
			byte data[] = new byte[BUFFER];
			// get a list of files from current directory
			File f = new File(folder);
			String files[] = f.list(filenameFilter);

			for(int i = 0; i < files.length; i++) {
//				System.out.println("Adding: " + files[i]);
				fi = new FileInputStream(files[i]);
				origin = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = new ZipEntry(files[i]);
				out.putNextEntry(entry);
				int count;
				while((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				// origin.close();
			}
			// out.close();
		}
		catch (Exception e) {
			// e.printStackTrace();
			UtilsIO.handleTrivialException(ZipUtil.class, e);
		}
		finally {
			UtilsIO.closeStream(origin, out, dest, fi);
		}
	}

	public static void defleterTest(byte[] bytes, boolean print) throws IOException {
		OutputStream out = null;
		DeflaterOutputStream dos = null;
		long start;
		int count;
		int size;
		try {
			out = new ByteOutputStream(bytes.length);
			Deflater def = new Deflater(Deflater.BEST_SPEED);
			dos = new DeflaterOutputStream(out, def, 4 * 1024);
			start = System.nanoTime();
			count = 0;
			size = 5 * 1024;
			for(int i = 0; i < bytes.length - size; i += size, count++) {
				dos.write(bytes, i, size);
				dos.flush();
			}
		}
		finally {
			UtilsIO.closeStream(dos, out);
		}

		long time = System.nanoTime() - start;
		long latency = time / count;
		// 1 byte per ns = 1000 MB/s.
		long bandwidth = (count * size * 1000L) / time;
		if(print)
			System.out.println("Average latency " + latency + " ns. Bandwidth " + bandwidth + " MB/s.");
	}

	// public static void main(String[] args) {
	// try {
	// byte[] data = MyFileUtil.readBytesFromFile(new File("C:/users/xuxux/Desktop/zipizip.zip"));
	// getEntryNames(data);
	// getEntryNames(new File("C:/users/xuxux/Desktop/zipizip.zip"));
	// }
	// catch (IOException e) {
	// // e.printStackTrace();
	// }
	// }

}
