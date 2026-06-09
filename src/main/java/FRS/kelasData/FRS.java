package FRS.kelasData;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class FRS {
	private int id;
	private LocalDateTime datetime;
	private List<MataKuliah> courseTaken = new ArrayList<>();

	public FRS(int id, LocalDateTime datetime) {
		this.id = id;
		this.datetime = datetime;
	}

	public int getID() {
		return id;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public List<MataKuliah> getCourseTaken() {
		return courseTaken;
	}

	public void addMataKuliah(MataKuliah course) {
		courseTaken.add(course);
	}
}
