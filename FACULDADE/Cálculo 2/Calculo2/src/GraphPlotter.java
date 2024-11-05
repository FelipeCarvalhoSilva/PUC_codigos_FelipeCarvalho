import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GraphPlotter extends JFrame {
    private JTextField equationField;
    private JTextField pointsField;
    private JPanel graphPanel;
    private ArrayList<Point> points = new ArrayList<>();

    public GraphPlotter() {
        setTitle("Desenhador de Gráfico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel para entrada da equação e pontos
        JPanel inputPanel = new JPanel();
        equationField = new JTextField(20);
        pointsField = new JTextField(20);
        JButton drawButton = new JButton("Desenhar");

        inputPanel.add(new JLabel("Equação (ex: x^2 + 3*x - 2): f(x) = "));
        inputPanel.add(equationField);
        inputPanel.add(new JLabel("Pontos (ex: -2,1; 3,4):"));
        inputPanel.add(pointsField);
        inputPanel.add(drawButton);
        add(inputPanel, BorderLayout.NORTH);

        // Painel para o gráfico
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGraph(g);
                drawPoints(g);
            }
        };
        add(graphPanel, BorderLayout.CENTER);

        // Ação do botão para desenhar
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Processa os pontos inseridos
                processPoints(pointsField.getText());
                graphPanel.repaint();
            }
        });
    }

    private void drawGraph(Graphics g) {
        String equation = equationField.getText();
        if (equation.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        int width = graphPanel.getWidth();
        int height = graphPanel.getHeight();
        int originX = width / 2;
        int originY = height / 2;

        // Desenha os eixos no intervalo de -6 a 6
        g2.drawLine(0, originY, width, originY);
        g2.drawLine(originX, 0, originX, height);

        // Escala de -6 a 6
        double scaleX = width / 12.0;
        double scaleY = height / 12.0;

        // Desenha o gráfico da função
        for (double x = -6; x <= 6; x += 0.01) {
            double y = evaluateEquation(equation, x);
            int screenX = (int) (originX + x * scaleX);
            int screenY = (int) (originY - y * scaleY);

            if (screenX >= 0 && screenX < width && screenY >= 0 && screenY < height) {
                g2.drawLine(screenX, screenY, screenX, screenY);
            }
        }
    }

    private void drawPoints(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        int originX = graphPanel.getWidth() / 2;
        int originY = graphPanel.getHeight() / 2;

        // Escala de -6 a 6
        double scaleX = graphPanel.getWidth() / 12.0;
        double scaleY = graphPanel.getHeight() / 12.0;

        for (Point point : points) {
            int screenX = (int) (originX + point.x * scaleX);
            int screenY = (int) (originY - point.y * scaleY);

            // Marca o ponto na tela
            g2.fillOval(screenX - 4, screenY - 4, 8, 8);
        }
    }

    private double evaluateEquation(String equation, double x) {
        try {
            equation = equation.replace("x", "(" + x + ")");
            return eval(equation);
        } catch (Exception e) {
            return 0;
        }
    }

    private void processPoints(String pointsText) {
        points.clear();
        String[] pairs = pointsText.split(";");
        for (String pair : pairs) {
            String[] coords = pair.trim().split(",");
            if (coords.length == 2 && points.size() < 5) {
                try {
                    int x = (int) Double.parseDouble(coords[0].trim());
                    int y = (int) Double.parseDouble(coords[1].trim());
                    points.add(new Point(x, y));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Formato de ponto inválido: " + pair);
                }
            }
        }
    }
    

    public double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // adição
                    else if (eat('-')) x -= parseTerm(); // subtração
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplicação
                    else if (eat('/')) x /= parseFactor(); // divisão
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // operador unário
                if (eat('-')) return -parseFactor(); // operador unário

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parênteses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // números
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // funções
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Função desconhecida: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // potência

                return x;
            }
        }.parse();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphPlotter plotter = new GraphPlotter();
            plotter.setVisible(true);
        });
    }
}
