//package pl.edu.agh.student.movieconsultant;
//
//import jpl.*;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.*;
//import java.util.List;
//
//public class Main {
//
//    static Map<String, List<String>> questionsAndAnswers = new HashMap<>();
//    static Map<String, String> moviesMappings = new HashMap<>();
//    static Map<String, String> mappings = new HashMap<>();
//    static Map<String, String> answers = new HashMap<>();
//    static JLabel result = new JLabel();
//
//    static {
//        questionsAndAnswers.put("Jaki ma być film, który chcesz obejrzeć?",
//                Arrays.asList(
//                        "o problemach",
//                        "biograficzny",
//                        "zabawny",
//                        "romantyczny",
//                        "z wybuchami i strzelaniną",
//                        "z przemocą",
//                        "straszny",
//                        "bajka"
//                )
//        );
//
//        questionsAndAnswers.put("Z kim będziesz oglądać ten film?",
//                Arrays.asList(
//                        "chłopak",
//                        "dziewczyna",
//                        "rodzina",
//                        "znajomi",
//                        "syn",
//                        "córka",
//                        "sam"
//                )
//        );
//
//        questionsAndAnswers.put("Kiedy będziesz go oglądać?",
//                Arrays.asList(
//                        "wieczorem",
//                        "przy obiedzie",
//                        "w deszczowy dzień",
//                        "w urodziny",
//                        "w walentynki"
//                )
//        );
//
//        mappings.put("Jaki ma być film, który chcesz obejrzeć?", "czy_jest");
//        mappings.put("o problemach", "o_problemach");
//        mappings.put("biograficzny", "biograficzny");
//        mappings.put("zabawny", "zabawny");
//        mappings.put("romantyczny", "romantyczny");
//        mappings.put("z wybuchami i strzelaniną", "strzelanina");
//        mappings.put("z przemocą", "przemoc");
//        mappings.put("straszny", "straszny");
//        mappings.put("bajka", "bajka");
//
//        mappings.put("Z kim będziesz oglądać ten film?", "chcesz_obejrzec");
//        mappings.put("chłopak", "z_chlopakiem");
//        mappings.put("dziewczyna", "z_dziewczyna");
//        mappings.put("rodzina", "z_rodzina");
//        mappings.put("znajomi", "ze_znajomymi");
//        mappings.put("syn", "z_synem");
//        mappings.put("córka", "z_corka");
//        mappings.put("sam", "sam");
//
//        mappings.put("Kiedy będziesz go oglądać?", "czy_bedziesz_ogladac");
//        mappings.put("wieczorem", "wieczorem");
//        mappings.put("przy obiedzie", "przy_obiedzie");
//        mappings.put("w deszczowy dzień", "w_deszczowy_dzien");
//        mappings.put("w urodziny", "w_urodziny");
//        mappings.put("w walentynki", "w_walentynki");
//
//        moviesMappings.put("kobieta_na_skraju_dojrzalosci", "Kobieta na skraju dojrzałości");
//        moviesMappings.put("wielki_mike", "Wielki Mike");
//        moviesMappings.put("slodki_listopad", "Słodki listopad");
//        moviesMappings.put("idealny_facet_dla_mojej_dziewczyny", "Idealny facet dla mojej dziewczyny");
//        moviesMappings.put("matrix", "Matrix");
//        moviesMappings.put("transporter", "Transporter");
//        moviesMappings.put("amityville", "Amityville");
//        moviesMappings.put("smiertelna_wyliczanka", "Śmiertelna wyliczanka");
//        moviesMappings.put("wieczny_student", "Wieczny student");
//        moviesMappings.put("vinci", "Vinci");
//        moviesMappings.put("tytus_romek_i_atomek_wsrod_zlodziei_marzen", "Tytus, Romek i A'tomek wśród złodziei marzeń");
//        moviesMappings.put("barbie_roszpunka", "Barbie Roszpunka");
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Konsultant filmowy");
//            Container contentPane = frame.getContentPane();
//
//            contentPane.setLayout(new BorderLayout(5, 5));
//            contentPane.add(getMainLabel(), BorderLayout.PAGE_START);
//
//            JPanel questionsAndAnswersPanel = new JPanel(new GridLayout(1, 3, 5, 5));
//            for (Map.Entry<String, List<String>> entry : questionsAndAnswers.entrySet()) {
//                JPanel questionPanel = new JPanel();
//                questionPanel.setBorder(BorderFactory.createEtchedBorder());
//                questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));
//                String question = entry.getKey();
//                questionPanel.add(new JLabel(question));
//                ButtonGroup answersGroup = new ButtonGroup();
//                for (String answer : entry.getValue()) {
//                    JRadioButton radioButton = new JRadioButton(answer);
//                    answersGroup.add(radioButton);
//
//                    radioButton.addChangeListener(e -> {
//                        JRadioButton source = (JRadioButton) e.getSource();
//                        if (source.isSelected()) {
//                            answers.put(question, source.getText());
//                        }
//                    });
//
//                    questionPanel.add(radioButton);
//                }
//                questionsAndAnswersPanel.add(questionPanel);
//            }
//            contentPane.add(questionsAndAnswersPanel, BorderLayout.CENTER);
//
//            JPanel checkPanel = new JPanel();
//            checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.LINE_AXIS));
//            JButton button = new JButton("Sprawdź!");
//            button.addActionListener(e -> {
//                JPL.init();
//                new Query(new Compound("consult",  new Term[] { new Atom("se.pl")})).allSolutions();
//
//                for (Map.Entry<String, List<String>> entry : questionsAndAnswers.entrySet()) {
//                    String question = entry.getKey();
//                    String currentChosenAnswer = answers.get(question);
//                    String questionMapping = mappings.get(question);
//                    for (String answer : entry.getValue()) {
//                        String answerMapping = mappings.get(answer);
//                        String isChosen;
//                        if(answer.equals(currentChosenAnswer)) {
//                            isChosen = "tak";
//                        } else {
//                            isChosen = "nie";
//                        }
//
//                        new Query(new Compound("pamietaj",  new Term[] {
//                                new Atom(questionMapping),
//                                new Atom(answerMapping),
//                                new Atom(isChosen)
//                        })).allSolutions();
//                    }
//                }
//                Hashtable solution = new Query(new Compound("wybrany_film", new Term[]{new Variable("X")})).oneSolution();
//                if(solution != null) {
//                    result.setText("Polecamy film: " + moviesMappings.get(solution.get("X").toString()));
//                } else {
//                    result.setText("Nie potrafimy Ci nic polecić:(");
//                }
//
//                new Query(new Compound("wyczysc_fakty", 0)).oneSolution();
//            });
//            checkPanel.add(result);
//            checkPanel.add(Box.createHorizontalGlue());
//            checkPanel.add(button);
//            contentPane.add(checkPanel, BorderLayout.PAGE_END);
//
//            frame.setLocationRelativeTo(null);
//            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//            frame.pack();
//            frame.setVisible(true);
//        });
//    }
//
//    private static JLabel getMainLabel() {
//        JLabel mainLabel = new JLabel("Podaj swoje preferencje:");
//        mainLabel.setFont(mainLabel.getFont().deriveFont(16f));
//        return mainLabel;
//    }
//}
