package br.com.fabricio.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.com.fabricio.models.InputDTO;
import br.com.fabricio.models.OutputDTO;
import br.com.fabricio.utils.PathUtils;

public class FileService {

	private String directory;
	private File folder = null;

	
	public FileService(String directory) {
		this.directory = directory;
		folder = new File(directory);
	}

	
	public List<String> getFilenames() throws IOException {
		List<String> filenames = new ArrayList<>();
		if (isFolderExists()) {
			List<File> listFiles = Arrays.asList(folder.listFiles());
			for (File file : listFiles) {
				String filename = file.getName();
				filenames.add(filename);
			}
		}
		return filenames;
	}

	
	public InputDTO readFileFromDirectory(String filename) throws IOException {
		InputDTO inpuDTO = null;
		if (isFolderExists()) {
			File file = new File(String.format("%s%s%s", directory, File.separator, filename));
			if (isFileExists(file)) {
				String data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
				if(data != null && !data.trim().isEmpty()) {
					inpuDTO = new InputDTO(filename, data);					
				}
			}
		}
		return inpuDTO;
	}

	
	public void writeFileToDirectory(OutputDTO outputDTO) throws IOException {
		if (isFolderExists()) {
			String filename = outputDTO.getFilename();
			String data = outputDTO.toString();

			File file = new File(String.format("%s%s%s", directory, File.separator, filename));

			FileUtils.writeStringToFile(file, data, StandardCharsets.UTF_8);
			System.out.println(String.format("FileService.writeFileToDirectory  ->  wrote \"%s\" to \"%s\"", filename, PathUtils.getOutputDirectory()));
		}
	}

	
	public void deleteFile(String filename) {
		if (isFolderExists()) {
			String absolutePath = directory + File.separator + filename;
			File file = new File(absolutePath);
			if (isFileExists(file)) {
				file.delete();
			}
		}
	}

	
	private boolean isFileExists(File file) {
		if (file != null && file.exists() && file.isFile()) {
			return true;
		}
		return false;
	}

	
	private boolean isFolderExists() {
		if (folder.exists() && !folder.isFile()) {
			return true;
		}
		folder.mkdirs();
		return false;
	}

}
