package ua.lviv.lgs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;

public class Cinema implements Serializable {

	private static final long serialVersionUID = 945151870331136739L;
	private String name;
	private TreeMap<Days, Schedule> schedules;
	ArrayList<Movie> moviesLibrary = new ArrayList<>();
	public static Time open;
	public static Time close;

	public Cinema(String name, Time open, Time close) {
		this.name = name;
		this.schedules = new TreeMap<Days, Schedule>();
		for (Days day : Days.values())
			this.schedules.put(day, new Schedule());
		Cinema.open = open;
		Cinema.close = close;
	}

	public String getName() {
		return name;
	}

	public TreeMap<Days, Schedule> getScheduleMap() {
		return schedules;
	}

	public static Time getTimeOpen() {
		return open;
	}

	public void setTimeOpen(Time timeOpen) {
		Cinema.open = timeOpen;
	}

	public static Time getTimeClose() {
		return close;
	}

	public void setTimeClose(Time timeClose) {
		Cinema.close = timeClose;
	}

	@SuppressWarnings("resource")
	public static Cinema inputCinema() throws FailTimeException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter cinema name:");
		String name = sc.nextLine();
		if (name.equals("")) {
			System.err.println("Cinema name is incorrect");
			name = "Cinema without name";
		}
		System.out.println("Enter opening time");
		Time open = Time.inputTime();
		System.out.println("Enter closing time");
		Time close = Time.inputTime();
		System.out.println(name.toString() + " successfully created");

		return new Cinema(name, open, close);
	}

	public void addMovie() throws FailTimeException {
		Movie movie = Movie.inputMovie();
		moviesLibrary.add(movie);
		System.out.println("Movie \"" + movie.getTitle().toString() + "\" successfully added to list");
	}

	public void removeMovie(Movie movie) throws FailTimeException {

		Optional<Movie> movieFound = moviesLibrary.stream()
				.filter(entry -> entry.getTitle().equalsIgnoreCase(movie.getTitle())).findFirst();

		if (movieFound.isPresent()) {
			moviesLibrary.remove(movieFound.get());
			System.out.println("Movie \"" + movieFound.get().toString() + "\" successfully deleted from list");
		} else {
			System.out.println("There is no such movie in the list");
		}

		for (Days day : Days.values()) {
			Schedule schedule = schedules.get(day);
			Optional<Seance> movieScheduleFound = schedule.seances.stream()
					.filter(entry -> entry.getMovie().equals(movie)).findFirst();
			if (movieScheduleFound.isPresent()) {
				schedule.seances.remove(movieScheduleFound.get());
			}
		}
		System.out.println("Movie \"" + movieFound.get().toString() + "\" successfully deleted from schedual");
	}

	public void printAllMovie() {
		moviesLibrary.forEach(System.out::println);
	}

	public void addSeance(Movie movie, Days day) throws FailTimeException {
		System.out.println("Existing seances on " + day.name());
		Schedule schedule = schedules.get(day);
		if (schedule.seances.size() == 0) {
			System.out.println("There are no seances on this day");
		} else {
			schedule.seances.forEach(System.out::println);
		}
		System.out.println("Enter the beginig time of the seance");
		Time startTime = Time.inputTime();
		schedule.addSeance(new Seance(movie, startTime));
	}

	public void addSeanceAllDays(Movie movie) throws FailTimeException {
		Schedule schedule;
		System.out.println("Enter the beginig time of the seance");
		Time time = Time.inputTime();

		for (Days day : Days.values()) {
			schedule = schedules.get(day);
			schedule.addSeance(new Seance(movie, time));
		}
	}

	public void getSchedule(Schedule schedule) {
		if (schedule.seances.size() == 0) {
			System.out.println("There are no seances yet for this day");
		} else {
			schedule.seances.forEach(System.out::println);
		}
	}
	
	@SuppressWarnings("resource")
	public void removeSeance(Days day) {
		Schedule schedule = schedules.get(day);
		if (schedule.seances.size()==0) {
			System.out.println("There are no seances yet for this day");
		} else {
			Iterator<Seance> iterator = schedule.seances.iterator();
			int i=0;
			System.out.println("Select seance to delete");
			while (iterator.hasNext()) {
				i++;
				System.out.println(i+" - "+iterator.next().toString());
			}
			Scanner sc = new Scanner(System.in);
			Iterator<Seance> iterator1 = schedule.seances.iterator();
			i=0;
			int number=0;
			if (sc.hasNextInt()) number=sc.nextInt();
			while (iterator1.hasNext()) {
				iterator1.next();
				i++;
				if (i==number) iterator1.remove();				
			}	
		}	
	}

}