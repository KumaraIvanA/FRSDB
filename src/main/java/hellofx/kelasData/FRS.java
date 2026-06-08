package hellofx.kelasData;

import java.util.ArrayList;
import java.util.List;
import java.time.Instant;

public class FRS {
	private int id;
	private Instant datetime;
	private List<MataKuliah> courseTaken = new ArrayList<>();

	public FRS(int id, Instant datetime) {
		this.id = id;
		this.datetime = datetime;
	}

	public int getID() {
		return id;
	}

	public Instant getDatetime() {
		return datetime;
	}

	public List<MataKuliah> getCourseTaken() {
		return courseTaken;
	}

	public void addMataKuliah(MataKuliah course) {
		courseTaken.add(course);
	}
}
