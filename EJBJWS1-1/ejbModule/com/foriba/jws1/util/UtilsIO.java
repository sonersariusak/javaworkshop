package com.foriba.jws1.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


@SuppressWarnings("unused")
public class UtilsIO {

	public static final String UTF8_CHARSET = "UTF-8";
	private static final int BUFFER_SIZE = 1024;

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static String readStringFromInputStream(InputStream is) throws IOException {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader isr = new InputStreamReader(is, UTF8_CHARSET);
			br = new BufferedReader(isr);
			String line = br.readLine();
			while(line != null) {
				sb.append(line);
				line = br.readLine();
				if(line != null) {
					sb.append(LINE_SEPARATOR);
				}
			}
		}
		finally {
			if(null != br) {
				br.close();
			}

		}
		return sb.toString();
	}

	public static byte[] readBytesFromInputStream(InputStream is) throws IOException {
		BufferedInputStream bis = null;
		byte[] data = null;
		try {
			bis = new BufferedInputStream(is);
			data = new byte[is.available()];
			bis.read(data);
		}
		finally {
			if(null != bis) {
				bis.close();
			}

		}
		return data;
	}

	public static byte[] zip(ZipFile zipFile) throws IOException {

		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = null;
		ZipOutputStream zos = null;
		try {
			bais = new ByteArrayInputStream(zipFile.data);
			baos = new ByteArrayOutputStream();
			zos = new ZipOutputStream(baos);

			zos.putNextEntry(new ZipEntry(zipFile.name));
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			while((length = bais.read(buffer)) > 0) {
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();

			return baos.toByteArray();
		}
		finally {
			UtilsIO.closeStream(bais, zos, baos);
		}
	}

	public static List<ZipFile> unzip(byte[] data) throws Exception {

		ZipInputStream zis = null;
		ByteArrayOutputStream baos = null;
		try {
			zis = new ZipInputStream(new ByteArrayInputStream(data));
			List<ZipFile> zipFiles = new ArrayList<ZipFile>();
			ZipEntry entry = zis.getNextEntry();

			while(entry != null) {
				try {
					byte[] buffer = new byte[BUFFER_SIZE];
					int length = 0;
					baos = new ByteArrayOutputStream();
					while((length = zis.read(buffer)) > 0) {
						baos.write(buffer, 0, length);
					}
					baos.flush();
					ZipFile zipFile = new ZipFile();
					zipFile.name = entry.getName();
					if(baos.toByteArray() != null) {
						zipFile.data = baos.toByteArray();
					}
					zipFiles.add(zipFile);

					entry = zis.getNextEntry();
				}
				finally {
					UtilsIO.closeStream(baos);
				}
			}
			return zipFiles;
		}
		finally {
			UtilsIO.closeStream(zis);
		}
	}

	public static class ZipFile {
		public String name;
		public byte[] data;
	}

	public static String calculateMD5Checksum(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(data, 0, data.length);
		BigInteger bi = new BigInteger(1, md.digest());
		return String.format("%1$032X", bi);
	}

	public static String calculateSHA256(byte[] value) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(value);
		byte byteData[] = md.digest();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	// http://en.wikipedia.org/wiki/Byte_order_mark
	public static byte[] escapeUTF8BOM(byte[] data) {
		if(hasBOMBytes1(data)) {
			return Arrays.copyOfRange(data, 3, data.length);
		}
		return data;
	}

	private static boolean hasBOMBytes1(byte[] data) {
		if(data.length >= 3 && ((data[0] & 0xFF) == 0xEF && (data[1] & 0xFF) == 0xBB && (data[2] & 0xFF) == 0xBF)) {
			return true;
		}
		return false;
	}

	private static boolean hasBOMBytes2(byte[] data) {
		return data.length >= 3 && (data[0] == -17 && data[1] == -69 && data[2] == -65);
	}

	public static boolean isValidUTF8(byte[] input) {
		CharsetDecoder cs = Charset.forName("UTF-8").newDecoder();
		try {
			cs.decode(ByteBuffer.wrap(input));
			return true;
		}
		catch (CharacterCodingException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public static void handleTrivialException(Class c, Exception e) {
		// do nothing
		return;
	}

	@SuppressWarnings("unchecked")
	public static String formatStackTrace(Class c, Exception e) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = null;
		String str = null;
		try {
			sw.append("FIT eInvoice Core Engine Exception in class " + c.getName());
			sw.append("\n\nStack Trace:\n");
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			int i = 0;
			Throwable t = e.getCause();
			while(t != null) {
				sw.append("\n\nCause Stack Trace[" + i + "]:\n");
				t.printStackTrace(pw);
				t = t.getCause();
				i++;
			}
			str = sw.toString();
		}
		finally {
			UtilsIO.closeStream(sw, pw);
		}
		return str;
	}


	public static void closeStream(Closeable... c) {
		for(Closeable c1 : c) {
			try {
				if(c1 != null) {
					c1.close();
				}
			}
			catch (Exception e) {
				UtilsIO.handleTrivialException(UtilsIO.class, e);
			}
		}
	}

}
