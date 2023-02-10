import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Main {
    static JFrame frame = new JFrame();
    static int width = 500, height = 500;
    static JPanel panel = new JPanel();
    static JLabel label = new JLabel("TEST");
    static Point startPoint; // Координаты точки, за которую ухватились мышкой
    static boolean move=false;

    public static void start(MouseEvent e){ // Метод захвата объекта мышкой
        if (e.getButton()==3) // Если кнопка правая
        {
            startPoint = e.getPoint(); // Сохраняем координаты точки, за которую ухватились (это координаты именно JLabel, относительные)
            move=true; // Метка о начале движения по правой кнопке
        }
    }

    public static void stop(MouseEvent e){ // Метод окончания перемещения
        move=false;
    }

    public static void move(MouseEvent e){ // Метод перемещения
        if (move) { // Если было начало движения
            Point p = SwingUtilities.convertPoint(label, e.getPoint(), panel); // Координаты JLabel приводим к координатам панели
            label.setLocation(p.x - startPoint.x, p.y - startPoint.y); // Перемещаем, и вычитаем координаты точки, за которую ухватились.
        }
    }

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Перемещение объекта");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
        frame.add(panel);
        panel.add(label);
        label.addMouseListener(new MouseAdapter() { // Добавляем слушателя мыши на метку
            public void mousePressed(MouseEvent e) {
                start(e);
            }
        });
        label.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                stop(e);
            }
        });
        label.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                move(e);
            }
        });
        frame.setVisible(true);
    }
}