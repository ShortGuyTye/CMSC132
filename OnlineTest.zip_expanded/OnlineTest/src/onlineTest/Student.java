package onlineTest;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private HashMap<Integer, HashMap<Integer, Object>> questions = new HashMap<>();
	String name;

	public Student(String name) {
		this.name = name;
	}

	public void addTF(int examId, int questionNumber, boolean answer) {
		if (questions.containsKey(examId)) {
			questions.get(examId).put(questionNumber, answer);
		} else {
			HashMap<Integer, Object> answers = new HashMap<>();
			answers.put(questionNumber, answer);
			questions.put(examId, answers);
		}
	}

	public void addMC(int examId, int questionNumber, String[] answer) {
		if (questions.containsKey(examId)) {
			questions.get(examId).put(questionNumber, answer);
		} else {
			HashMap<Integer, Object> answers = new HashMap<>();
			answers.put(questionNumber, answer);
			questions.put(examId, answers);
		}
	}

	public void addFN(int examId, int questionNumber, String[] answer) {
		if (questions.containsKey(examId)) {
			questions.get(examId).put(questionNumber, answer);
		} else {
			HashMap<Integer, Object> answers = new HashMap<>();
			answers.put(questionNumber, answer);
			questions.put(examId, answers);
		}
	}

	public HashMap<Integer, Object> getAnswers(int examId) {
		return questions.get(examId);
	}

	public HashMap<Integer, HashMap<Integer, Object>> getExams() {
		return questions;
	}

	public String getName() {
		return name;
	}

}
