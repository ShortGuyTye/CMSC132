package onlineTest;

import java.io.*;
import java.util.*;

public class SystemManager implements Serializable, Manager {

	private static final long serialVersionUID = 1L;
	HashMap<Integer, Exam> exams = new HashMap<>();
	HashMap<String, Student> students = new HashMap<>();

	String[] letterGrades;
	double[] cutoffs;

	public boolean addExam(int examId, String title) {
		if (exams.containsKey(examId)) {
			return false;
		} else {
			Exam exam = new Exam(examId, title);
			exams.put(examId, exam);
			return true;
		}
	}

	public void addTrueFalseQuestion(int examId, int questionNumber,
			String text, double points, boolean answer) {
		TrueFalseQuestion newQuestion = new TrueFalseQuestion(examId,
				questionNumber, text, points, answer);
		exams.get(examId).addTrueFalse(newQuestion);

	}

	public void addMultipleChoiceQuestion(int examId, int questionNumber,
			String text, double points, String[] answer) {
		MultChoiceQuestion newQuestion = new MultChoiceQuestion(examId,
				questionNumber, text, points, answer);
		exams.get(examId).addMultChoice(newQuestion);

	}

	public void addFillInTheBlanksQuestion(int examId, int questionNumber,
			String text, double points, String[] answer) {
		FillNQuestion newQuestion = new FillNQuestion(examId, questionNumber,
				text, points, answer);
		exams.get(examId).addFillN(newQuestion);

	}

	public String getKey(int examId) {
		Exam exam = exams.get(examId);
		String key = "";
		for (Question questions : exam.getQuestions()) {
			key += "Question Text: " + questions.getText() + "\n";
			key += "Points: " + questions.getPoints() + "\n";
			if (questions instanceof TrueFalseQuestion) {
				key += "Correct Answer: "
						+ (((TrueFalseQuestion) questions).getAnswer() ? "True"
								: "False")
						+ "\n";
			} else if (questions instanceof MultChoiceQuestion) {
				Arrays.sort(((MultChoiceQuestion) questions).getAnswer());
				key += "Correct Answer: "
						+ Arrays.toString(
								((MultChoiceQuestion) questions).getAnswer())
						+ "\n";
			} else if (questions instanceof FillNQuestion) {
				Arrays.sort(((FillNQuestion) questions).getAnswer());
				key += "Correct Answer: " + Arrays.toString(
						((FillNQuestion) questions).getAnswer()) + "\n";
			}
		}
		return key;
	}

	public boolean addStudent(String name) {
		Student student = new Student(name);
		students.put(name, student);
		return true;
	}

	public void answerTrueFalseQuestion(String studentName, int examId,
			int questionNumber, boolean answer) {
		students.get(studentName).addTF(examId, questionNumber, answer);

	}

	public void answerMultipleChoiceQuestion(String studentName, int examId,
			int questionNumber, String[] answer) {
		students.get(studentName).addMC(examId, questionNumber, answer);

	}

	public void answerFillInTheBlanksQuestion(String studentName, int examId,
			int questionNumber, String[] answer) {
		students.get(studentName).addFN(examId, questionNumber, answer);

	}

	public double getExamScore(String studentName, int examId) {
		int counter = 0;
		double points = 0;
		Set<Integer> questionSet = students.get(studentName).getAnswers(examId)
				.keySet();
		for (Integer questionId : questionSet) {
			points += getScore(studentName, examId, questionId, counter);
			counter++;
		}
		return points;
	}

	public String getGradingReport(String studentName, int examId) {
		String report = "";
		int counter = 0;
		double total = 0.0;
		;
		Set<Integer> questionSet = students.get(studentName).getAnswers(examId)
				.keySet();
		for (Integer questionId : questionSet) {
			report += "Question #" + questionId + " ";
			report += getScore(studentName, examId, questionId, counter);
			report += " points out of "
					+ exams.get(examId).getQuestions().get(counter).getPoints()
					+ "\n";
			total += exams.get(examId).getQuestions().get(counter).getPoints();
			counter++;
		}

		report += "Final Score: " + getExamScore(studentName, examId);
		report += " out of " + total;
		return report;
	}

	private double getTotalScore(int examId) {
		double total = 0.0;
		int size = exams.get(examId).getQuestions().size();
		for (int i = 0; i < size; i++) {
			total += exams.get(examId).getQuestions().get(i).getPoints();
		}
		return total;
	}

	private double getScore(String studentName, int examId, int questionId,
			int counter) {
		double points = 0.0;
		if (exams.get(examId).getQuestions()
				.get(counter) instanceof TrueFalseQuestion) {
			boolean studentAnswer = (boolean) students.get(studentName)
					.getAnswers(examId).get(questionId);
			boolean answer = ((TrueFalseQuestion) exams.get(examId)
					.getQuestions().get(counter)).getAnswer();
			if (studentAnswer == answer) {
				points += exams.get(examId).getQuestions().get(counter)
						.getPoints();
			}
		} else if (exams.get(examId).getQuestions()
				.get(counter) instanceof MultChoiceQuestion) {
			String[] studentAnswer = (String[]) students.get(studentName)
					.getAnswers(examId).get(questionId);
			String[] answer = ((MultChoiceQuestion) exams.get(examId)
					.getQuestions().get(counter)).getAnswer();
			if (Arrays.equals(studentAnswer, answer)) {
				points += exams.get(examId).getQuestions().get(counter)
						.getPoints();
			}
		} else if (exams.get(examId).getQuestions()
				.get(counter) instanceof FillNQuestion) {
			String[] studentAnswer = (String[]) students.get(studentName)
					.getAnswers(examId).get(questionId);
			String[] answer = ((FillNQuestion) exams.get(examId).getQuestions()
					.get(counter)).getAnswer();
			double pointsPer = (exams.get(examId).getQuestions().get(counter)
					.getPoints()) / answer.length;
			for (int i = 0; i < answer.length; i++) {
				for (int j = 0; j < studentAnswer.length; j++) {
					if (answer[i].equals(studentAnswer[j])) {
						points += pointsPer;
					}
				}
			}
		}

		return points;
	}

	@Override
	public void setLetterGradesCutoffs(String[] letterGrades,
			double[] cutoffs) {
		this.letterGrades = letterGrades;
		this.cutoffs = cutoffs;

	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		double grade = 0;
		Set<Integer> examSet = students.get(studentName).getExams().keySet();
		for (Integer examId : examSet) {
			grade += (getExamScore(studentName, examId))
					/ (getTotalScore(examId));
		}
		return 100 * ((grade) / (examSet.size()));
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		double grade = getCourseNumericGrade(studentName);
		String letterGrade = "";
		for (int i = 0; i < cutoffs.length; i++) {
			if (grade >= cutoffs[i]) {
				letterGrade = letterGrades[i];
				return letterGrade;
			}
		}
		return letterGrade;
	}

	@Override
	public String getCourseGrades() {
		String answer = "";
		TreeSet<String> studentKeys = new TreeSet<String>(students.keySet());
		for (String studentName : studentKeys) {
			String name = students.get(studentName).getName();
			answer += name + " ";
			answer += getCourseNumericGrade(name) + " ";
			answer += getCourseLetterGrade(name) + "\n";
		}
		return answer;
	}

	@Override
	public double getMaxScore(int examId) {
		double best = 0;
		Set<String> studentKeys = students.keySet();
		for (String studentName : studentKeys) {
			double curr = getExamScore(studentName, examId);
			if (curr > best)
				best = curr;
		}
		return best;
	}

	@Override
	public double getMinScore(int examId) {
		double worst = 100;
		Set<String> studentKeys = students.keySet();
		for (String studentName : studentKeys) {
			double curr = getExamScore(studentName, examId);
			if (curr < worst)
				worst = curr;
		}
		return worst;
	}

	@Override
	public double getAverageScore(int examId) {
		double average = 0;
		Set<String> studentKeys = students.keySet();
		for (String studentName : studentKeys) {
			double curr = getExamScore(studentName, examId);
			average += curr;
		}
		return (average / studentKeys.size());
	}

	@Override
	public void saveManager(Manager manager, String fileName) {
		try {
			File file = new File(fileName);
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream(file));

			output.writeObject(manager);
			output.close();
		} catch (Exception e) {

		}

	}

	@Override
	public Manager restoreManager(String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				return new SystemManager();
			} else {
				ObjectInputStream input = new ObjectInputStream(
						new FileInputStream(file));
				SystemManager manager = (SystemManager) input.readObject();
				input.close();
				return manager;
			}
		} catch (Exception e) {
			return null;
		}

	}

}
