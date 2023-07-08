package sqlObjects;

import java.time.LocalDate;


public class YoungestAndElder {
	private String name;
	private LocalDate birthday;
	private String type;

	public YoungestAndElder(String name, LocalDate birthday, String type) {
		this.name = name;
		this.birthday = birthday;
		this.type = type;
	}

	@Override
	public String toString() {
		return name+" - "+birthday+" - "+type;
	}
}
