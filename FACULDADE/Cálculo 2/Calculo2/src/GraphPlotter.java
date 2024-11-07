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
    private ArrayList<String> evaluatedPoints = new ArrayList<>();

    public GraphPlotter() {
        // Configurações principais da janela
        setTitle("Desenhador de Gráfico");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel para entrada de dados (equação e pontos)
        JPanel inputPanel = new JPanel();
        equationField = new JTextField(20);
        pointsField = new JTextField(20);
        JButton drawButton = new JButton("Desenhar");
        JButton integralButton = new JButton("Calcular Integral");

        // Adiciona campos de entrada e botões no painel superior
        inputPanel.add(new JLabel("Equação (ex: x^2 + 3*x - 2): f(x) = "));
        inputPanel.add(equationField);
        inputPanel.add(new JLabel("Pontos (ex: -2,1; 3,4):"));
        inputPanel.add(pointsField);
        inputPanel.add(drawButton);
        inputPanel.add(integralButton);
        add(inputPanel, BorderLayout.NORTH);

        // Painel para exibição do gráfico
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGraph(g);         // Desenha o gráfico da função
                drawPoints(g);        // Desenha os pontos fornecidos pelo usuário
                drawEvaluatedPoints(g); // Exibe os pontos avaliados (resultados calculados)
            }
        };
        add(graphPanel, BorderLayout.CENTER);

        // Define a ação do botão de desenhar
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Processa os pontos inseridos
                processPoints(pointsField.getText());
                evaluatePoints(); // Calcula o valor de f(x) para cada ponto
                graphPanel.repaint(); // Re-renderiza o painel para exibir o gráfico e os pontos
            }
        });

        // Define a ação do botão de integral
        integralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String integralResult = calculateIntegral(equationField.getText());
                JOptionPane.showMessageDialog(GraphPlotter.this, "Integral: " + integralResult);
            }
        });
    }

    /**
     * Calcula a integral da equação fornecida (simplificada para polinômios).
     * @param equation a equação a ser integrada.
     * @return uma string com a integral da função.
     */
    private String calculateIntegral(String equation) {
        // Este método faz uma integração simbólica simples para polinômios
        if (equation.isEmpty()) return "Equação não fornecida.";

        try {
            String[] terms = equation.split("\\+|\\-");
            StringBuilder integral = new StringBuilder();

            for (String term : terms) {
                term = term.trim();
                if (term.contains("x^")) {
                    String[] parts = term.split("x\\^");
                    double coef = parts[0].isEmpty() ? 1 : Double.parseDouble(parts[0]);
                    int exp = Integer.parseInt(parts[1]);
                    integral.append(String.format("%.2fx^%d", coef / (exp + 1), exp + 1));
                } else if (term.contains("x")) {
                    double coef = term.equals("x") ? 1 : Double.parseDouble(term.replace("x", ""));
                    integral.append(String.format("%.2fx^2", coef / 2));
                } else {
                    double coef = Double.parseDouble(term);
                    integral.append(String.format("%.2fx", coef));
                }
                integral.append(" + ");
            }

            return integral.toString().replaceAll("\\+ $", "");
        } catch (Exception e) {
            return "Erro ao calcular a integral.";
        }
    }

    /**
     * Desenha o gráfico da função com base na equação fornecida.
     * @param g o contexto gráfico usado para desenhar no painel.
     */
    private void drawGraph(Graphics g) {
        String equation = equationField.getText();
        if (equation.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        int width = graphPanel.getWidth();
        int height = graphPanel.getHeight();
        int originX = width / 2;
        int originY = height / 2;

        // Desenha os eixos coordenados no centro do painel
        g2.drawLine(0, originY, width, originY); // eixo X
        g2.drawLine(originX, 0, originX, height); // eixo Y

        // Define a escala para ajustar o gráfico ao painel
        double scaleX = width / 12.0;
        double scaleY = height / 12.0;

        // Desenha a curva da função no intervalo de -6 a 6
        for (double x = -6; x <= 6; x += 0.01) {
            double y = evaluateEquation(equation, x);
            int screenX = (int) (originX + x * scaleX);
            int screenY = (int) (originY - y * scaleY);

            if (screenX >= 0 && screenX < width && screenY >= 0 && screenY < height) {
                g2.drawLine(screenX, screenY, screenX, screenY);
            }
        }
    }

    /**
     * Desenha os pontos fornecidos pelo usuário no gráfico.
     * @param g o contexto gráfico usado para desenhar no painel.
     */
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

            // Marca o ponto com um pequeno círculo
            g2.fillOval(screenX - 4, screenY - 4, 8, 8);
        }
    }

    /**
     * Exibe os valores calculados de f(x) para os pontos fornecidos.
     * @param g o contexto gráfico usado para desenhar no painel.
     */
    private void drawEvaluatedPoints(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        int startY = 20;
        for (String eval : evaluatedPoints) {
            g2.drawString(eval, 10, startY); // Exibe cada resultado na tela
            startY += 15;
        }
    }

    /**
     * Avalia a equação fornecida para um valor específico de x.
     * @param equation a equação da função.
     * @param x o valor de x.
     * @return o valor de f(x).
     */
    private double evaluateEquation(String equation, double x) {
        try {
            equation = equation.replace("x", "(" + x + ")");
            return eval(equation);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Processa os pontos fornecidos pelo usuário, convertendo-os em objetos Point.
     * @param pointsText texto contendo os pontos no formato "x,y; x,y; ...".
     */
    private void processPoints(String pointsText) {
        points.clear();
        evaluatedPoints.clear();
        String[] pairs = pointsText.split(";");
        for (String pair : pairs) {
            String[] coords = pair.trim().split(",");
            if (coords.length == 2 && points.size() < 5) { // Limite de 5 pontos para simplificação
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

    /**
     * Calcula o valor de f(x) para cada ponto fornecido e armazena o resultado.
     */
    private void evaluatePoints() {
        if (!points.isEmpty()) {
            for (Point point : points) {
                double result = evaluateEquation(equationField.getText(), point.x);
                evaluatedPoints.add("a = " + point.x + " f(" + point.x + ") = " + result);
            }
        }
    }

    /**
     * Avaliador de expressões matemáticas simples.
     * @param str expressão matemática a ser avaliada.
     * @return o resultado da expressão.
     */
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

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponenciação

                return x;
            }
        }.parse();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GraphPlotter().setVisible(true);
        });
    }
}
