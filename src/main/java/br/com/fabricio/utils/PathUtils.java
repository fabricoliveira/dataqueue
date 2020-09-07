package br.com.fabricio.utils;

import java.io.File;

public class PathUtils {
	
	private static String queueName = "queue";
	private static String homePath = System.getProperty("user.home");
	
	
	public static String getQueueName() {
		return queueName;
	}
	
	
	public static String getInputDirectory() {
		String inputPath = String.format("%s%s%s", "data", File.separator, "in");
		return String.format("%s%s%s", homePath, File.separator, inputPath);
	}
	
	
	public static String getOutputDirectory() {
		String outputPath = String.format("%s%s%s", "data", File.separator, "out");
		return String.format("%s%s%s", homePath, File.separator, outputPath);
	}

}
