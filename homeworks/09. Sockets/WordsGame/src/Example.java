import javax.swing.*;

public class Example {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello world");// создаем главное окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//заверщение процесса после закрытия окна

        JButton button = new JButton("Sign in");//создаем кнопку Sign in
        frame.getContentPane().add(button);//добавляем кнопку на панель окна
        button.addActionListener(Ev);
        frame.setBounds(0, 0, 1100, 800);//задаем положения окна по координатной оси и его размер
        frame.setVisible(true);//делаем окно видимым
    }
}
