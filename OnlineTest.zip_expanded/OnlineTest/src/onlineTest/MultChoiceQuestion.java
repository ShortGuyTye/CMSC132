package onlineTest;

import java.io.Serializable;

public class MultChoiceQuestion implements Question, Serializable {

	private static final long serialVersionUID = 1L;
	int examId, questionNumber;
	double points;
	String[] answer;
	String text;

	public MultChoiceQuestion(int examId, int questionNumber, String text,
			double points, String[] answer) {
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

	public String[] getAnswer() {
		return answer;
	}
}
