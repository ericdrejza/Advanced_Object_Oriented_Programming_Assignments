package main.decoders;

//Eric Drejza
//Advanced Object Oriented Programming
//6/18/2021
//Assignment 1 - Creational Patterns

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class driver {
	/*
	
	*/
	
	public static void main(String[] args) {
		String suggestedDirectory = "C:\\Users\\Eric\\OneDrive\\Documents\\"
				+ "Rowan University\\Advanced Object Oriented Programming\\"
				+ "Advanced_Object_Oriented_Programming_Assignments\\"
				+ "Assignment_1-Creational_Patterns\\DataFormatConverter\\"
				+ "src\\main\\decoders\\input";
		
		CSVtoJSONDecoder decoder = new CSVtoJSONDecoder();
		String csvInputData = "";
		File selectedSourceFile = chooseFile("open", suggestedDirectory, "");
		System.out.println(selectedSourceFile.getAbsolutePath());
		
		// Read input data
		if (selectedSourceFile != null) {
			try {
				Scanner scnr = new Scanner(selectedSourceFile);
				while(scnr.hasNextLine()) {
					csvInputData = csvInputData + scnr.nextLine() + "\n";
				}
				scnr.close();
			}
			catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}	
			
			// Print input data
			System.out.println("Input (csv):\n\n" + csvInputData + "\n");
			
			// Decoder converts data format
			String json_out = decoder.convertDataFormat(selectedSourceFile);
			
			// Print newly formatted output data
			System.out.println("Output (json):\n\n" + json_out);
			
			// Save json data to .json file 
			File selectedSaveFile = chooseFile("save",
					selectedSourceFile.toPath().getParent().toString(),
					selectedSourceFile.getName().replace(".csv", ".json"));
			
			if (selectedSaveFile != null) {
				try {
					FileWriter writer = new FileWriter(selectedSaveFile);
					writer.write(json_out);
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	
	private static File chooseFile(String option, String suggestedDirectory, String suggestedFilename) {
		JFileChooser fileChooser = new JFileChooser();
		switch (option) {
		case "open":
			fileChooser.setCurrentDirectory(new File(suggestedDirectory));
			fileChooser.showOpenDialog(null);			
			break;
		case "save":
			fileChooser.setCurrentDirectory(new File(suggestedDirectory));
			fileChooser.setSelectedFile(new File(suggestedFilename));
			fileChooser.showSaveDialog(null);
			break;
		}
		
		return fileChooser.getSelectedFile();
	}
	
}
