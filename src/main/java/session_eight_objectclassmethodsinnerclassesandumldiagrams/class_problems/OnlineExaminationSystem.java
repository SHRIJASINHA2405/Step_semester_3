package session_eight_objectclassmethodsinnerclassesandumldiagrams.class_problems;

import java.util.LinkedHashMap;
import java.util.Map;

// ================= QUESTION =================

abstract class Question {

    private final int questionId;
    private final String questionText;
    private final int points;

    public Question(
            int questionId,
            String questionText,
            int points) {

        this.questionId = questionId;
        this.questionText = questionText;
        this.points = points;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getPoints() {
        return points;
    }

    // Each question type provides its own evaluation logic
    public abstract boolean evaluate(String answer);
}

// ================= MCQ =================

class MultipleChoiceQuestion extends Question {

    private final String correctOption;

    public MultipleChoiceQuestion(
            int questionId,
            String questionText,
            int points,
            String correctOption) {

        super(questionId, questionText, points);
        this.correctOption = correctOption;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctOption.equalsIgnoreCase(answer);
    }
}

// ================= TRUE / FALSE =================

class TrueFalseQuestion extends Question {

    private final boolean correctAnswer;

    public TrueFalseQuestion(
            int questionId,
            String questionText,
            int points,
            boolean correctAnswer) {

        super(questionId, questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

// ================= SHORT ANSWER =================

class ShortAnswerQuestion extends Question {

    private final String correctAnswer;

    public ShortAnswerQuestion(
            int questionId,
            String questionText,
            int points,
            String correctAnswer) {

        super(questionId, questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

// ================= STUDENT =================

class Student {

    private final int studentId;
    private final String name;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}

// ================= EXAMINATION =================

class Examination {

    private final String examName;
    private final Map<Integer, Question> questions;

    public Examination(String examName) {
        this.examName = examName;
        this.questions = new LinkedHashMap<>();
    }

    public String getExamName() {
        return examName;
    }

    public void addQuestion(Question question) {
        questions.put(question.getQuestionId(), question);
    }

    public Question getQuestion(int questionId) {
        return questions.get(questionId);
    }

    public Map<Integer, Question> getQuestions() {
        return questions;
    }

    public int getTotalMarks() {

        int total = 0;

        for (Question question : questions.values()) {
            total += question.getPoints();
        }

        return total;
    }
}

// ================= ANSWER =================

class Answer {

    private final Question question;
    private final String response;

    public Answer(
            Question question,
            String response) {

        this.question = question;
        this.response = response;
    }

    public Question getQuestion() {
        return question;
    }

    public String getResponse() {
        return response;
    }

    public boolean isCorrect() {
        return question.evaluate(response);
    }

    public int getScore() {

        if (isCorrect()) {
            return question.getPoints();
        }

        return 0;
    }
}

// ================= ATTEMPT =================

class Attempt {

    enum Status {
        IN_PROGRESS,
        SUBMITTED
    }

    private final Student student;
    private final Examination examination;

    private final Map<Integer, Answer> answers;

    private Status status;

    public Attempt(
            Student student,
            Examination examination) {

        this.student = student;
        this.examination = examination;
        this.answers = new LinkedHashMap<>();
        this.status = Status.IN_PROGRESS;
    }

    public void recordAnswer(
            int questionId,
            String response) {

        if (status == Status.SUBMITTED) {
            throw new IllegalStateException(
                    "Cannot change answers for a submitted examination."
            );
        }

        Question question =
                examination.getQuestion(questionId);

        if (question == null) {
            throw new IllegalArgumentException(
                    "Question does not exist."
            );
        }

        Answer answer =
                new Answer(question, response);

        answers.put(questionId, answer);

        System.out.println(
                "Answer recorded for Question "
                        + questionId
        );
    }

    public void submit() {

        if (status == Status.SUBMITTED) {
            throw new IllegalStateException(
                    "Examination is already submitted."
            );
        }

        status = Status.SUBMITTED;

        System.out.println(
                examination.getExamName()
                        + " submitted by "
                        + student.getName()
        );

        calculateResult();
    }

    private void calculateResult() {

        int totalScore = 0;

        System.out.println("Result:");

        for (Answer answer : answers.values()) {

            Question question =
                    answer.getQuestion();

            if (answer.isCorrect()) {

                totalScore += answer.getScore();

                System.out.println(
                        "Question "
                                + question.getQuestionId()
                                + ": Correct ("
                                + question.getPoints()
                                + " points)"
                );

            } else {

                System.out.println(
                        "Question "
                                + question.getQuestionId()
                                + ": Incorrect (0 points)"
                );
            }
        }

        System.out.println(
                "Total score: "
                        + totalScore
                        + "/"
                        + examination.getTotalMarks()
        );
    }
}

// ================= MAIN CLASS =================

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        // Create Student
        Student student =
                new Student(
                        1,
                        "Student 1"
                );

        // Create Examination
        Examination exam =
                new Examination("Exam A");

        // Question 1 - MCQ
        Question question1 =
                new MultipleChoiceQuestion(
                        1,
                        "Which is the correct option?",
                        5,
                        "C"
                );

        // Question 2 - True/False
        Question question2 =
                new TrueFalseQuestion(
                        2,
                        "Java is a purely procedural language.",
                        5,
                        false
                );

        exam.addQuestion(question1);
        exam.addQuestion(question2);

        // Start examination
        Attempt attempt =
                new Attempt(
                        student,
                        exam
                );

        System.out.println(
                exam.getExamName()
                        + " started by "
                        + student.getName()
        );

        // Record answers
        attempt.recordAnswer(
                1,
                "C"
        );

        attempt.recordAnswer(
                2,
                "True"
        );

        // Submit examination
        attempt.submit();

        // Try to change answer after submission
        System.out.println();

        try {

            attempt.recordAnswer(
                    1,
                    "A"
            );

        } catch (IllegalStateException e) {

            System.out.println(e.getMessage());
        }
    }
}