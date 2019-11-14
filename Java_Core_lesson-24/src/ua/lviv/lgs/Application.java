package ua.lviv.lgs;

import java.io.File;
import java.util.Scanner;

public class Application {

	public static int menu() {
		System.out.println("Enter 10 to create new cinema");
		System.out.println("Enter 11 to save cinema into the file");
		System.out.println("Enter 12 to load cinema from file");
		System.out.println("Enter 13 to add movie");
		System.out.println("Enter 14 to delete movie");
		System.out.println("Enter 15 to show all movies");
		System.out.println("Enter 16 to add seance");
		System.out.println("Enter 17 to delete seance");
		System.out.println("Enter 18 to show all seances for a day");
		System.out.println("Enter 19 to show the schedual");
		System.out.println("Enter 0 to exit");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Make your choice: ");
		int menuChoise = scanner.nextInt();

		return menuChoise;
	}

	public static void main(String[] args) throws Exception {
		Cinema cinema = null;
		Days days;
		Schedule schedule;

		while (true) {
			switch (menu()) {

			case 10: {
				cinema = Cinema.inputCinema();
				break;
			}

			case 11: {
				SerializeMethods.serialize(cinema, new File("Cinema.txt"));
				System.out.println("Cinema \"" + cinema.getName() + "\" successfully saved into the file!\n");
				break;
			}

			case 12: {
				cinema = (Cinema) SerializeMethods.deserialize(new File("Cinema.txt"));
				System.out.println("CInema \"" + cinema.getName() + "\" successfully deleted from file!\n");
				break;
			}

			case 13: {
				cinema.addMovie();
				break;
			}

			case 14: {
				Movie movie = Movie.inputMovie();
				cinema.removeMovie(movie);
				break;
			}

			case 15: {
				cinema.printAllMovie();
				break;
			}

			case 16: {
				System.out.println("Enter the seance date:");
				days = Days.inputDay();
				Movie movie = Movie.inputMovie();
				cinema.addSeance(movie, days);
				break;
			}
			case 17: {
				System.out.println("Enter the seance date You wish to delete");
				days = Days.inputDay();
				for (Days day1 : Days.values()) {
					if (day1.name().equals(days.name().toUpperCase())) {
						cinema.removeSeance(day1);
					}
				}
				break;
			}

			case 18: {
				Days day = Days.inputDay();
				System.out.println("Show seance schedual for " + day.name());
				schedule = cinema.getScheduleMap().get(day);
				cinema.getSchedule(schedule);
				break;
			}

			case 19: {
				for (Days day1 : Days.values()) {
					System.out.println();
					System.out.println(day1.name());
					schedule = cinema.getScheduleMap().get(day1);
					cinema.getSchedule(schedule);
				}
				break;
			}

			case 0: {
				System.out.println("Thank You for using our cinema. Have a great day\n");
				System.exit(0);
				break;
			}

			default: {
				System.out.println("There is no such option!");
				break;
			}
			}
		}
	}
}