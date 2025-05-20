package onlineTest;

import java.io.Serializable;
import java.util.*;

public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Question> questions = new ArrayList<>();
	String title;

	public Exam(int examId, String title) {
		this.title = title;
	}

	public void addTrueFalse(TrueFalseQuestion tfQuestion) {
		questions.add(tfQuestion);
	}

	public void addMultChoice(MultChoiceQuestion mcQuestion) {
		questions.add(mcQuestion);
	}

	public void addFillN(FillNQuestion fnQuestion) {
		questions.add(fnQuestion);
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}
}
