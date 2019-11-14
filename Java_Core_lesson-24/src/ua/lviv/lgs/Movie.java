package ua.lviv.lgs;

import java.io.Serializable;
import java.util.Scanner;

public class Movie implements Serializable {

	private static final long serialVersionUID = -6545616262416464L;
	public String title;
	public Time duration;
	
	public Movie(String title, Time duration) {
		this.title = title;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public Time getDuration() {
		return duration;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}
	
	@SuppressWarnings("resource")
	public static Movie inputMovie() throws FailTimeException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter title of the movie:");
		String title = sc.nextLine();
		if (title.equals("")) {
			System.err.println("Wrong title");
			title = "Movie without a title";
		}
		System.out.println("Movie duration:");
		Time duration = Time.inputTime();
		return new Movie(title, duration);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie - " + "\"" + title + "\"" + ", seance duration - " + duration.toString();
	}
}
