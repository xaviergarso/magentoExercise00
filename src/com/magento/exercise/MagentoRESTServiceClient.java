package com.magento.exercise;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
 
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magento.domain.MagentoOrder;
import com.magento.domain.Order;

public class MagentoRESTServiceClient {
	public static void main(String[] args) {
		String string = "";
		String response = "";
		
		try {
 
			// Step1: Let's 1st read file from fileSystem
			// Change MagentoJSON.txt path here
			InputStream magentoInputStream = new FileInputStream("Json/entryJson.txt");
			InputStreamReader magentoReader = new InputStreamReader(magentoInputStream);
			BufferedReader br = new BufferedReader(magentoReader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}
			
			// Step2: Now pass String Data to REST Service
			try {
				URL url = new URL("http://localhost:8080/magentoExercise00/api/MagentoService");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(string);
				out.close();
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
				while ((line = in.readLine()) != null) {
					response += line + "\n";
				}
				
				System.out.println("\nMagento REST Service Invoked Successfully..");
				System.out.println("\n"+response);
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling Magento REST Service");
				System.out.println(e);
			}
 
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
