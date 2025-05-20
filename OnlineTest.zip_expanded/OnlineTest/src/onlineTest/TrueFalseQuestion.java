package onlineTest;

import java.io.Serializable;

public class TrueFalseQuestion implements Serializable, Question {

	private static final long serialVersionUID = 1L;
	int examId, questionNumber;
	double points;
	boolean answer;
	String text;

	public TrueFalseQuestion(int examId, int questionNumber, String text,
			double points, boolean answer) {
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.points = points;
		this.text = text;
		this.answer = answer;
	}

	public String getText() {
		return text;
	}

	public double getPoints() {
		return points;
	}

	public boolean getAnswer() {
		return answer;
	}
}
