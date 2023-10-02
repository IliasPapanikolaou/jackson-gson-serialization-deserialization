package com.ipap.springboot3formattingjsondate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.ipap.springboot3formattingjsondate.dto.AnotherDateItem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringBoot3FormattingJsonDateApplication implements CommandLineRunner {

	private final Gson gson;

	public SpringBoot3FormattingJsonDateApplication(Gson gson) {
		this.gson = gson;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3FormattingJsonDateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("\n----------------------------------------");
		System.out.println("date: " + new Date());
		System.out.println("localDateTime: " + LocalDateTime.now());
		System.out.println("localDate: " + LocalDate.now());

		ObjectMapper objectMapper = new ObjectMapper();

		// Solution - Include dependency: jackson-datatype-jsr310 and the below line
		objectMapper.registerModule(new JavaTimeModule());
		// Optional
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		// Hashmap
		Map<String, Object> map = new HashMap<>();
		map.put("date", new Date());
		map.put("localDateTime", LocalDateTime.now());
		map.put("localDate", LocalDate.now());

		// Object
		DateItem dateItem = new DateItem(new Date(), LocalDateTime.now(), LocalDate.now());

		// Another Object
		AnotherDateItem anotherDateItem = new AnotherDateItem(new Date(), LocalDateTime.now(), LocalDate.now());


		// Using Jackson

		// Error message
		// Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310"

		String jsonMap = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		String jsonItem = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dateItem);
		String anotherItem = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(anotherDateItem);

		System.out.println("\n----------Jackson Serializer ------------");
		System.out.println(jsonMap);
		System.out.println(jsonItem);
		System.out.println(anotherItem);

		// Using GSON

		// Error message
		// Failed making field 'java.time.LocalDate#year' accessible; either increase its visibility or write a custom TypeAdapter for its declaring type.
		// ....
		// Unable to make field private final int java.time.LocalDate.year accessible: module java.base does not "opens java.time" to unnamed module @15bfd87
		System.out.println("\n-----------Gson Serializer--------------");
		System.out.println(gson.toJson(map));
		System.out.println(gson.toJson(jsonItem));
		System.out.println(gson.toJson(anotherItem));


		// --------- Deserialization Example ----------- //
		final String jacksonJson =
				"""
				{
				  "date" : 1696269839496,
				  "localDateTime" : [ 2023, 10, 2, 21, 3, 59, 496884700 ],
				  "localDate" : [ 2023, 10, 2 ]
				}
				""";

		final String jsonJson =
				"""
				{
				  "date": "Oct 2, 2023, 9:25:05 PM",
				  "localDateTime": "2::Οκτ::2023 21::25::05",
				  "localDate": "2023-10-02"
				}
				""";


		System.out.println("\n----------Jackson Deserializer----------");
		DateItem jacksonDate = objectMapper.readValue(jacksonJson, DateItem.class);
		System.out.println(jacksonDate);

		System.out.println("\n----------Gson Deserializer-------------");
		DateItem gsonDate = gson.fromJson(jsonJson, DateItem.class);
		System.out.println(gsonDate);

	}

	record DateItem (Date date, LocalDateTime localDateTime, LocalDate localDate) {}
}
