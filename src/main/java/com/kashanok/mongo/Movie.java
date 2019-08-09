package com.kashanok.mongo;

import org.bson.Document;

/**
 * Created by Raman Kashanok
 */
public class Movie
{
	private String _id;
	private String name;
	private Genre genre;
	private Double amount;

	public Movie(String name, Genre genre, Double amount)
	{
		this.name = name;
		this.genre = genre;
		this.amount = amount;
	}

	public String get_id()
	{
		return _id;
	}

	public void set_id(String _id)
	{
		this._id = _id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}
}
