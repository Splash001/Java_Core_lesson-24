package ua.lviv.lgs;

import java.io.Serializable;
import java.util.Scanner;

public enum Days implements Serializable {
	MONDAY(1),
	TUESDAY(2),
	WEDNESDAY(3),
	THIRSDAY(4),
	FRIDAY(5),
	SATURDAY(6),
	SUNDAY(7);
	
	private int num;
	
	Days(int num) {
		this.num = num;
	}
	
	@SuppressWarnings("resource")
	public static Days inputDay() {
		int day = 0;
		Days findDay = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter day of the week (1 to 7):");
		day = sc.nextInt();
		if ((day < 1) || (day > 7)) {
			System.err.println("Entered day is incorrect");
		}
		for (Days days : Days.values()) {
			if (days.num == day) {
				findDay = days;
				break;
			}
		}
		return findDay;
	}
}
