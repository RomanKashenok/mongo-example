package com.kashanok.mongo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Raman Kashanok
 */
public class Runner
{
	public static void main(String[] args)
	{
		MongoClient mongoClient = new MongoClient("localhost", 27017);

		//		MongoDatabase database = mongoClient.getDatabase("test");
		//		final MongoCollection<Document> movies = database.getCollection("movies");
		//		for (int i = 12; i < 17; i++)
		//		{
		//			movies.insertOne(new Document("name", "Avengers " + i));
		//		}
		//		final MongoCollection<Document> movies1 = database.getCollection("movies");
		//		movies1.find().forEach((Consumer<? super Document>) doc -> System.out.println(doc));

		MongoDatabase cinemaDB = mongoClient.getDatabase("cinema");
		final MongoCollection<Document> movies = cinemaDB.getCollection("movies");

		//		insertNewMovies(movies);
		getAllMovies(movies);

	}

	private static void insertNewMovies(MongoCollection collection)
	{
		List<Movie> moviesList = new ArrayList<>();
		Gson gson = new GsonBuilder().create();
		Random rand = new Random();
		for (int i = 0; i < 10; i++)
		{
			Genre g = Genre.values()[rand.nextInt(Genre.values().length)];
			Movie m = new Movie("name " + i, g, rand.nextDouble() * rand.nextInt(1000));
			moviesList.add(m);

			collection.insertMany(moviesList.stream().map(movie -> Document.parse(gson.toJson(movie))).collect(Collectors.toList()));
		}
	}

	private static List<Movie> getAllMovies(MongoCollection moviesCollection)
	{
		final FindIterable moviesIterable = moviesCollection.find();
		final Gson gson = new GsonBuilder().create();
		final MongoCursor iterator = moviesIterable.iterator();
		while (iterator.hasNext())
		{
//			final Object next = iterator.next().toString();
//			System.out.println(next);
			final Movie movie = (Movie) iterator.next();
			System.out.println(movie);
		}
		return null;
	}
}
