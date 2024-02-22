import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class User {
    String username;
    int score;

    public User(String username) {
        this.username = username;
        this.score = 0;
    }
}

class Question {
    String question;
    List<String> options;
    int correctOption;

    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

class Quiz {
    List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}

class QuizManager {
    List<User> users;
    Quiz quiz;

    public QuizManager() {
        this.users = new ArrayList<>();
        this.quiz = new Quiz();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void createQuiz() {
        // Add questions to the quiz
        Question q1 = new Question("What is the capital of France?",
                List.of("A. London", "B. Paris", "C. Rome", "D. Berlin"), 1);
        Question q2 = new Question("Which planet is known as the Red Planet?",
                List.of("A. Venus", "B. Jupiter", "C. Mars", "D. Saturn"), 2);

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
    }

    public void startQuiz(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Quiz!");
        int totalQuestions = quiz.questions.size();
        int score = 0;

        for (int i = 0; i < totalQuestions; i++) {
            Question currentQuestion = quiz.questions.get(i);
            System.out.println(currentQuestion.question);
            for (String option : currentQuestion.options) {
                System.out.println(option);
            }
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            if (choice == currentQuestion.correctOption) {
                score++;
            }
        }

        user.score = score;
        System.out.println("Quiz completed! Your score: " + score + "/" + totalQuestions);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizManager quizManager = new QuizManager();

        // Sample user creation
        User user1 = new User("Harshal");
        quizManager.addUser(user1);

        // Create quiz
        quizManager.createQuiz();

        // User authentication
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User currentUser = null;
        for (User user : quizManager.users) {
            if (user.username.equals(username)) {
                currentUser = user;
                break;
            }
        }
        if (currentUser == null) {
            System.out.println("User not found. Please sign up first.");
            return;
        }

        // Start quiz
        quizManager.startQuiz(currentUser);
    }
}
