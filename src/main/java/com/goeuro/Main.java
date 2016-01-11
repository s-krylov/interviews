package com.goeuro;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.goeuro.datamodel.CityInfo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

public class Main {

    private static final String API_ENDPOINT = "http://api.goeuro.com/api/v2/position/suggest/en/{0}";

    private static final String CSV_FILE_NAME = "{0}.csv";

    public static void main(String [] args) {
        if (args.length < 1) {
            System.out.println("Please provide city name to get csv file");
            return;
        }
        String cityName = args[0];
        try {
            URL url = new URL(MessageFormat.format(API_ENDPOINT, cityName));
            ObjectMapper mapper = new ObjectMapper();
            JsonParser parser = new JsonFactory().createParser(url);
            CityInfo[] info;
            try {
                info = mapper.readValue(parser, CityInfo[].class);
            } finally {
                parser.close();
            }
            CsvMapper csvMapper = new CsvMapper();
            ObjectWriter csvWriter = csvMapper.writerFor(CityInfo[].class).with(csvMapper.schemaFor(CityInfo.class).withHeader());
            csvWriter.writeValue(new File(MessageFormat.format(CSV_FILE_NAME, cityName)), info);
        } catch (MalformedURLException murle) {
            System.out.println("Incorrect format for City name. Try for example \"Berlin\"");
        } catch (JsonMappingException | JsonParseException | JsonGenerationException ie) {
            System.out.println("Internal error. Please contact customer service");
            ie.printStackTrace(System.out);
        } catch (IOException ioe) {
            System.out.println("Service doesn't responding. Please try again later or connect customer service");
        }
    }
}
