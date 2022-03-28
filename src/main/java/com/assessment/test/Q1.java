package com.assessment.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;

public class Q1 {

	static String currentUsersHomeDir = System.getProperty("user.home");
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<String> data = readTestData();
		String newFileName = currentUsersHomeDir+"\\eclipse-workspace\\test_assessments\\src\\main\\resources\\firstFile.csv";
		FileWriter newFile = new FileWriter(newFileName);
		CSVWriter cw= new CSVWriter(newFile);

		int devide=  data.indexOf("smh_seg_id,000020");
		int size=data.size();
		List<String> d1= new ArrayList<>(data.subList(0, devide));
		List<String> d2= new ArrayList<>(data.subList((devide), size));
		for(String s: d1) {
			String[] sp=s.split(",");
		cw.writeNext(sp);
		}
		
		cw.flush();
		cw.close();
		System.out.println("Created new file 1 and written on first file.");
		//Close the File Writer
		newFile.close();
		
		String newFileName2 = currentUsersHomeDir+"\\eclipse-workspace\\test_assessments\\src\\main\\resources\\secondFile.csv";
		FileWriter newFile2 = new FileWriter(newFileName2);
		CSVWriter cw2= new CSVWriter(newFile2);
		for(String s: d2) {
			String[] sp=s.split(",");
		cw2.writeNext(sp);
		}
		cw2.flush();
		cw2.close();
		System.out.println("Created new file 2 and written on second file.");
		//Close the File Writer
		newFile2.close();
	}


	public static  List<String> readTestData() throws IOException {
		Scanner scanner = new Scanner(new File(currentUsersHomeDir+"\\eclipse-workspace\\test_assessments\\src\\main\\resources\\q1.test_data"));
		List<String> dataLines = new ArrayList<>(); 
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if(!line.contains(" [record")) {
				String s;
				if(line.contains("\"")) {
					String[] d=line.split("\"");
					s=d[0].trim()+","+d[1].trim();
					dataLines.add(s);
				}else{
					String[] d=line.split("\\[");
					s=d[0].trim()+","+d[1].replace("\\]", "").trim();
					dataLines.add(s);
				}
			}
		}
		System.out.println("Read finished from file.");
		return dataLines;
	}

	
}
