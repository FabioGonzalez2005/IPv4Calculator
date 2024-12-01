package ipv4calculator;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Controller controller = new Controller(calculator);
        calculator.setVisible(true);
    }
}