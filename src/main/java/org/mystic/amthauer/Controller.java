package org.mystic.amthauer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;


public class Controller {

    private static final String TASK = "task_";
    private static final String LABEL = "label_";
    private static final int PREF_WIDTH = 700;
    private static final int OUTPUT_PREF_WIDTH = 300;
    private static final int LAYOUT_X = 50;
    private static final int LAYOUT_Y = 100;
    @FXML
    Button nextStep;
    @FXML
    TextArea instructionText;
    @FXML
    Button startTestButton;
    @FXML
    TextField userName;
    @FXML
    Label testName;
    @FXML
    ImageView image1;
    @FXML
    TextArea instructionText2;
    @FXML
    ImageView image2;
    private String login;
    private Scene scene;
    private PrintWriter printWriter;

    private int currentStep = 0;


    private static final Map<Integer, String> TASKS_120 = Map.ofEntries(
            new SimpleEntry<>(1, "1.    1) schreiben 2) hacken 3) schmieden 4) nähen 5) lesen "),
            new SimpleEntry<>(2, "2.    1) bald 2) demnächst 3) in Kürze 4) morgen 5) jetzt  "),
            new SimpleEntry<>(3, "3.    1) Kunde 2) Kompagnon 3) Mandant 4) Käufer 5) Patient"),
            new SimpleEntry<>(4, "4.    1) wesentlich 2) bemerkenswert 3) wichtig 4) charakteristisch 5) typisch  "),
            new SimpleEntry<>(5, "5.    1) verschieden 2) unterschiedlich 3) anders 4) ungleich 5) verändert   "),
            new SimpleEntry<>(6, "6.    1) neblig 2) frostig 3) windig 4) trüb 5) regnerisch  "),
            new SimpleEntry<>(7, "7.    1) Gespräch 2) Vortrag 3) Diskussion 4) Konferenz 5) Beratung  "),
            new SimpleEntry<>(8, "8.    1) blättern 2) wenden 3) umdrehen 4) umwälzen 5) umgehen  "),
            new SimpleEntry<>(9, "9.    1) nervös 2) zitternd 3) unruhig 4) unsicher 5) aufgeregt  "),
            new SimpleEntry<>(10, "10.  1) formen 2) brechen 3) biegen 4) ziehen 5) dehnen  "),
            new SimpleEntry<>(11, "11.  1) überprüfen 2) aussehen 3) vorsehen 4) überblicken 5) ansehen  "),
            new SimpleEntry<>(12, "12.  1) groß 2) massiv 3) dick 4) stattlich 5) voll  "),
            new SimpleEntry<>(13, "13.  1) ähnlich 2) gleich 3) identisch 4) gleichend 5) übereinstimmend  "),
            new SimpleEntry<>(14, "14.  1) stabil 2) beständig 3) kontinuierlich 4) dauerhaft 5) konstant  "),
            new SimpleEntry<>(15, "15.  1) reichen 2) aneignen 3) geben 4) darbieten 5) überreichen  "),
            new SimpleEntry<>(16, "16.  1) Aufzug 2) Treppe 3) Fallschirm 4) Leiter 5) Hubschrauber  "),
            new SimpleEntry<>(17, "17.  1) gesellig 2) diplomatisch 3) wohlwollend 4) gesprächig 5) höflich  "),
            new SimpleEntry<>(18, "18.  1) Migration 2) Bewegung 3) Entwicklung 4) Umsiedlung 5) Veränderung  "),
            new SimpleEntry<>(19, "19.  1) Neuerer 2) Erfinder 3) Vorreiter 4) Pionier 5) Rationalisator  "),
            new SimpleEntry<>(20, "20.  1) verbreitet 2) typisch 3) einfach 4) gewohnt 5) alltäglich  ")
    );

    private static final Map<Integer, String> TASKS_2140 = Map.ofEntries(
            new SimpleEntry<>(1, "21) Teuer — selten = billig — ?\n" +
                    "1) günstig 2) haltbar 3) erschwinglich 4) üblich 5) häufig"),
            new SimpleEntry<>(2, "22) Rechteck — Ellipse = Quadrat — ?\n" +
                    "1) Dreieck 2) Kreis 3) Sechseck 4) Winkel 5) Kegel"),
            new SimpleEntry<>(3, "23) Molekül — Atom = Pfund — ?\n" +
                    "1) Gewicht 2) Zentner 3) Gramm 4) Last 5) Masse"),
            new SimpleEntry<>(4, "24) Überschwemmung — Damm = Regen — ?\n" +
                    "1) nass 2) Wasser 3) Abfluss 4) Tropfen 5) Regenschirm"),
            new SimpleEntry<>(5, "25) Sägen — kleben = sieben — ?\n" +
                    "1) mischen 2) löten 3) gießen 4) schütten 5) filtern"),
            new SimpleEntry<>(6, "26) Brot — Teig = Koks — ?\n" +
                    "1) Heizung 2) Stahlverhüttung 3) Kohle 4) Verkokung 5) Keller"),
            new SimpleEntry<>(7, "27) Berücksichtigen — außer Acht lassen = erkennen — ?\n" +
                    "1) bemerken 2) ignorieren 3) beobachten 4) unterschätzen 5) verachten"),
            new SimpleEntry<>(8, "28) Sportler — Erfolg = Unternehmer — ?\n" +
                    "1) Luxus 2) Geld 3) Gewinn 4) Umsatz 5) Bewegung"),
            new SimpleEntry<>(9, "29) Liberal — radikal = gemäßigt — ?\n" +
                    "1) tolerant 2) engagiert 3) extrem 4) wohlwollend 5) versöhnlich"),
            new SimpleEntry<>(10, "30) Zahl — Bruch = Gebäude — ?\n" +
                    "1) Zimmer 2) Keller 3) Fenster 4) Scheune 5) Stockwerk"),
            new SimpleEntry<>(11, "31) Platin — Aluminium = Diamant — ?\n" +
                    "1) Edelstein 2) Schmuck 3) Glas 4) Hartmetall 5) Schliff"),
            new SimpleEntry<>(12, "32) Seite — Buch = Satz — ?\n" +
                    "1) Buchstabe 2) Wort 3) Inhalt 4) Kapitel 5) Titel"),
            new SimpleEntry<>(13, "33) Größe — Länge = unehrlich — ?\n" +
                    "1) Gefängnis 2) sündhaft 3) diebisch 4) unglücklich 5) Irrtum"),
            new SimpleEntry<>(14, "34) Entdeckung — Neugier = Handlung — ?\n" +
                    "1) Hoffnung 2) Prozess 3) Erfahrung 4) Absicht 5) Ergebnis"),
            new SimpleEntry<>(15, "35) Speise — Gewürze = Vortrag — ?\n" +
                    "1) Beleidigung 2) Rede 3) Humor 4) Ansprache 5) Gliederung"),
            new SimpleEntry<>(16, "36) Zunge — Bitterkeit = Auge — ?\n" +
                    "1) Sehkraft 2) Licht 3) Helligkeit 4) Rot 5) scharf"),
            new SimpleEntry<>(17, "37) Zorn — Affekt = Trauer — ?\n" +
                    "1) Freude 2) Gereiztheit 3) Stimmung 4) Wut 5) Verlust"),
            new SimpleEntry<>(18, "38) Mantel — Jackett = Wolle — ?\n" +
                    "1) Material 2) Schaf 3) Seide 4) Pullover 5) Textilien"),
            new SimpleEntry<>(19, "39) Wissenschaft — Mathematik = Verlag — ?\n" +
                    "1) Druckerei 2) Erzählung 3) Zeitschrift 4) Tageszeitung 5) Redaktion"),
            new SimpleEntry<>(20, "40) Fluss — Delta = Baum — ?\n" +
                    "1) Feuchtigkeit 2) Äste 3) Wurzeln 4) Krone 5) Triebe"));


    private static final Map<Integer, String> TASKS_4160 = Map.ofEntries(
            new SimpleEntry<>(1, "41.    1) Armut 2) Gefahr 3) Hunger 4) Krankheit 5) Angst 6) Durst"),
            new SimpleEntry<>(2, "42.    1) Charakter 2) Symptom 3) System 4) Wunsch 5) Merkmal 6) Diagnose  "),
            new SimpleEntry<>(3, "43.    1) Meer 2) Alge 3) Qualle 4) Delphin 5) Müll 6) Wal "),
            new SimpleEntry<>(4, "44.    1) Berührung 2) Parfüm 3) Nase 4) Geschmack 5) Reizung 6) Duft "),
            new SimpleEntry<>(5, "45.    1) Punkt 2) Gipfel 3) Wiese 4) Tal 5) Turm 6) Feld "),
            new SimpleEntry<>(6, "46.    1) Kreuz 2) Moschee 3) Turm 4) Altar 5) Kirche 6) Glocke "),
            new SimpleEntry<>(7, "47.    1) Kanister 2) Aschenbecher 3) Rucksack 4) Urne 5) Vase 6) Kochtopf  "),
            new SimpleEntry<>(8, "48.    1) Nilpferd 2) Schmetterling 3) Schildkröte 4) Regenwurm 5) Strauß 6) Igel  "),
            new SimpleEntry<>(9, "49.    1) waschen 2) färben 3) falten 4) polieren 5) reinigen 6) trocknen  "),
            new SimpleEntry<>(10, "50.   1) Geschichte 2) Philologie 3) Biologie 4) Wirtschaft 5) Pädagogik 6) Physik  "),
            new SimpleEntry<>(11, "51.   1) Flugzeug 2) Pilot 3) Gepäckaufbewahrung 4) Gepäck 5) Stewardess 6) Lokführer  "),
            new SimpleEntry<>(12, "52.   1) Kabel 2) Telefon 3) Kran 4) Turbine 5) Sicherung 6) Schalter  "),
            new SimpleEntry<>(13, "53.   1) Lineal 2) Vakuum 3) Winkel 4) Temperatur 5) Hitze 6) Thermometer  "),
            new SimpleEntry<>(14, "54.   1) Warnung 2) Welt 3) Epoche 4) Zeitung 5) Schule 6) Frist  "),
            new SimpleEntry<>(15, "55.   1) Fang 2) Angel 3) Hecht 4) Schleppnetz 5) Fischerboot 6) Muschel  "),
            new SimpleEntry<>(16, "56.   1) Jacke 2) Reißverschluss 3) Türriegel 4) Schlüsselbund 5) Fensterglas 6) Kleiderschrank  "),
            new SimpleEntry<>(17, "57.   1) weich 2) hart 3) elastisch 4) rund 5) warm 6) flüssig  "),
            new SimpleEntry<>(18, "58.   1) Butter 2) Eier 3) Brot 4) Reis 5) Quark 6) Hering  "),
            new SimpleEntry<>(19, "59.   1) sentimental 2) seltsam 3) poetisch 4) empfindlich 5) zart 6) exzentrisch  "),
            new SimpleEntry<>(20, "60.   1) lernen 2) sich anpassen 3) bleiben 4) beobachten 5) sich adaptieren 6) ausruhen  ")
    );

    private static final Map<Integer, String> CORRECT_ANSWERS = Map.<Integer, String>ofEntries(
            new SimpleEntry<>(1, "5"),
            new SimpleEntry<>(2, "5"),
            new SimpleEntry<>(3, "2"),
            new SimpleEntry<>(4, "3"),
            new SimpleEntry<>(5, "5"),
            new SimpleEntry<>(6, "4"),
            new SimpleEntry<>(7, "2"),
            new SimpleEntry<>(8, "5"),
            new SimpleEntry<>(9, "4"),
            new SimpleEntry<>(10, "2"),
            new SimpleEntry<>(11, "2"),
            new SimpleEntry<>(12, "1"),
            new SimpleEntry<>(13, "1"),
            new SimpleEntry<>(14, "3"),
            new SimpleEntry<>(15, "2"),
            new SimpleEntry<>(16, "3"),
            new SimpleEntry<>(17, "3"),
            new SimpleEntry<>(18, "3"),
            new SimpleEntry<>(19, "3"),
            new SimpleEntry<>(20, "3"),

            new SimpleEntry<>(21, "5"),
            new SimpleEntry<>(22, "2"),
            new SimpleEntry<>(23, "3"),
            new SimpleEntry<>(24, "5"),
            new SimpleEntry<>(25, "1"),
            new SimpleEntry<>(26, "3"),
            new SimpleEntry<>(27, "2"),
            new SimpleEntry<>(28, "3"),
            new SimpleEntry<>(29, "3"),
            new SimpleEntry<>(30, "4"),
            new SimpleEntry<>(31, "3"),
            new SimpleEntry<>(32, "4"),
            new SimpleEntry<>(33, "2"),
            new SimpleEntry<>(34, "4"),
            new SimpleEntry<>(35, "3"),
            new SimpleEntry<>(36, "4"),
            new SimpleEntry<>(37, "3"),
            new SimpleEntry<>(38, "3"),
            new SimpleEntry<>(39, "3"),
            new SimpleEntry<>(40, "4"),

            new SimpleEntry<>(41, "36"),
            new SimpleEntry<>(42, "25"),
            new SimpleEntry<>(43, "46"),
            new SimpleEntry<>(44, "46"),
            new SimpleEntry<>(45, "36"),
            new SimpleEntry<>(46, "25"),
            new SimpleEntry<>(47, "24"),
            new SimpleEntry<>(48, "16"),
            new SimpleEntry<>(49, "15"),
            new SimpleEntry<>(50, "36"),
            new SimpleEntry<>(51, "26"),
            new SimpleEntry<>(52, "36"),
            new SimpleEntry<>(53, "16"),
            new SimpleEntry<>(54, "36"),
            new SimpleEntry<>(55, "24"),
            new SimpleEntry<>(56, "23"),
            new SimpleEntry<>(57, "26"),
            new SimpleEntry<>(58, "15"),
            new SimpleEntry<>(59, "14"),
            new SimpleEntry<>(60, "25"),

            new SimpleEntry<>(121, "2"),
            new SimpleEntry<>(122, "5"),
            new SimpleEntry<>(123, "4"),
            new SimpleEntry<>(124, "1"),
            new SimpleEntry<>(125, "4"),
            new SimpleEntry<>(126, "5"),
            new SimpleEntry<>(127, "3"),
            new SimpleEntry<>(128, "5"),
            new SimpleEntry<>(129, "1"),
            new SimpleEntry<>(130, "3"),
            new SimpleEntry<>(131, "1"),
            new SimpleEntry<>(132, "4"),
            new SimpleEntry<>(133, "5"),
            new SimpleEntry<>(134, "2"),
            new SimpleEntry<>(135, "1"),
            new SimpleEntry<>(136, "2"),
            new SimpleEntry<>(137, "1"),
            new SimpleEntry<>(138, "5"),
            new SimpleEntry<>(139, "3"),
            new SimpleEntry<>(140, "3"),

            new SimpleEntry<>(141, "2"),
            new SimpleEntry<>(142, "4"),
            new SimpleEntry<>(143, "3"),
            new SimpleEntry<>(144, "1"),
            new SimpleEntry<>(145, "4"),
            new SimpleEntry<>(146, "1"),
            new SimpleEntry<>(147, "2"),
            new SimpleEntry<>(148, "5"),
            new SimpleEntry<>(149, "3"),
            new SimpleEntry<>(150, "4"),
            new SimpleEntry<>(151, "1"),
            new SimpleEntry<>(152, "2"),
            new SimpleEntry<>(153, "5"),
            new SimpleEntry<>(154, "4"),
            new SimpleEntry<>(155, "3"),
            new SimpleEntry<>(156, "2"),
            new SimpleEntry<>(157, "5"),
            new SimpleEntry<>(158, "1"),
            new SimpleEntry<>(159, "3"),
            new SimpleEntry<>(160, "5")
    );

    @FXML
    private void closeWindow() {
        if (this.printWriter != null) {
            this.printWriter.flush();
            this.printWriter.close();
        }
        System.exit(0);
    }

    @FXML
    public void startTest() throws FileNotFoundException {
        this.scene = nextStep.getScene();
        if (userName.getText().equalsIgnoreCase("Ihren Namen eingeben") || userName.getText().length() == 0) {

        } else {
            this.login = userName.getText();
            File csvOutputFile = new File("%s.csv".formatted(this.login));
            this.printWriter = new PrintWriter(csvOutputFile);
            this.printWriter.println("task_id,raw_response,is_correct");
            this.printWriter.flush();
            userName.setVisible(false);
            startTestButton.setDisable(true);
            instructionText.setText("""
                    Untertest 1.
                    Bei den folgenden Aufgaben finden Sie jeweils fünf Wörter. Vier dieser Wörter lassen sich zu einer Gruppe zusammenfassen. Finden Sie das Wort, das nicht zur Gruppe gehört.
                    Beispiel 1
                    1) Tisch   2) Stuhl   3) Meise   4) Schrank   5) Bett
                    Antwort: 3) Meise
                    Die vier Wörter Tisch, Stuhl, Schrank und Bett gehören zusammen als Möbelstücke, während das Wort „Meise" nicht dazu passt und das „fremde" Wort in dieser Reihe ist.


                    Beispiel 2
                    1) sitzen   2) liegen   3) stehen   4) gehen   5) knien
                    Antwort: 4) gehen
                    Die vier Begriffe sitzen, liegen, stehen und knien beschreiben Ruhelagen, während „gehen" eine Bewegung ausdrückt und daher nicht dazu gehört.
                    Es gibt zwanzig solcher Aufgaben. Tragen Sie Ihre Antwort in das Kästchen nach der jeweiligen Aufgabe ein. Jedes Wort ist mit einer Zahl nummeriert: 1, 2, 3, 4, 5. Schreiben Sie die Nummer des nicht passenden Wortes in das leere Kästchen.
                    Arbeiten Sie schnell. Die Zeit ist begrenzt. Wenn Sie bei einer Aufgabe nicht sicher sind, überspringen Sie diese (Sie können sie später noch bearbeiten, wenn Zeit übrig ist). Wenn Sie einen Fehler gemacht haben, können Sie die Antwort korrigieren.
                    Klicken Sie auf WEITER und beginnen Sie zu arbeiten, wenn Sie bereit sind. Wenn die Zeit abgelaufen ist, wird das Aufgabenformular automatisch geschlossen, unabhängig davon, ob Sie alle Aufgaben bearbeitet haben oder nicht. Wenn Sie früher fertig sind, können Sie Ihre Arbeit überprüfen oder einfach warten, aber beginnen Sie nicht mit den nächsten Aufgaben. Der Übergang zur nächsten Aufgabe erfolgt automatisch nach Ablauf der Zeit.

                    """);
            instructionText.setVisible(true);
            nextStep.setVisible(true);
        }
    }

    @FXML
    public void clearTextField() {
        userName.clear();
    }

    @FXML
    public void nextStep() {
        callNextStep();
    }

    private void callNextStep() {
        currentStep++;
        if (currentStep == 1) {
            System.out.println("step 1");
            tasks120();
        } else if (currentStep == 2) {
            System.out.println("step 2");
            getResults(1, 20);
            testName.setVisible(false);
            nextStep.setVisible(true);
            instructionText.setText(
                    """
                            Untertest 2.
                            Bei den folgenden Aufgaben gilt es, die Beziehung zwischen den ersten beiden Wörtern zu erkennen und dann das passende Wort für das dritte Wort aus den fünf Antwortmöglichkeiten zu wählen.
                            Beispiel 1
                            Wald — Bäume = Wiese — ?
                            1) Heu   2) Strauch   3) Futter   4) Gras   5) Weide
                            Antwort: 4) Gras.
                            Die Beziehung zwischen den ersten beiden Wörtern (Wald — Bäume) lässt sich so formulieren: „Im Wald wachsen Bäume." Nach dieser Regel passt zum Wort „Wiese" das Wort „Gras", da auf der Wiese Gras wächst.
                            Beispiel 2
                            Dunkel — hell = nass — ?
                            1) feucht   2) regnerisch   3) bewölkt   4) sonnig   5) trocken
                            Antwort: 5) trocken.
                            Die ersten beiden Wörter (dunkel — hell) sind Antonyme; nach dieser Regel passt zum Wort „nass" das Wort „trocken".
                            Es gibt wieder zwanzig solcher Aufgaben. Tragen Sie Ihre Antwort in das Kästchen nach der jeweiligen Aufgabe ein. Die Hauptaufgabe besteht darin, die Beziehung zwischen den ersten beiden Wörtern zu verstehen und nach diesem Prinzip das passende Wort für das dritte zu finden. Jedes Wort ist nummeriert: 1, 2, 3, 4, 5. Schreiben Sie die Nummer des gewählten Wortes in das leere Kästchen.
                            Wenn Sie die Antwort nicht wissen, können Sie die Aufgabe überspringen. Sie können später zurückkehren, wenn Zeit übrig bleibt. Falls Sie sich geirrt haben, können Sie die Antwort korrigieren. Arbeiten Sie schnell, da die Zeit begrenzt ist.
                            Klicken Sie auf WEITER und beginnen Sie zu arbeiten, wenn Sie bereit sind. Wenn die Zeit abgelaufen ist, wird das Aufgabenformular automatisch geschlossen. Wenn Sie früher fertig sind, können Sie Ihre Arbeit überprüfen oder einfach warten, aber beginnen Sie nicht mit den nächsten Aufgaben. Der Übergang zur nächsten Aufgabe erfolgt automatisch nach Ablauf der Zeit.
                            """
            );
            instructionText.setVisible(true);
        } else if (currentStep == 3) {
            System.out.println("step 3");
            tasks2140();
        } else if (currentStep == 4) {
            System.out.println("step 4");
            getResults(21, 40);
            testName.setVisible(false);
            nextStep.setVisible(true);
            instructionText.setText(
                    """
                            Untertest 3.
                            Bei den folgenden Aufgaben finden Sie jeweils sechs Wörter. Zwei dieser Wörter lassen sich zusammenfassen, weil sie gleichartig sind, zur selben Kategorie gehören und unter einen gemeinsamen Oberbegriff gebracht werden können. Zwischen den Wörtern darf es keine anderen Beziehungen geben, z.B. funktionale oder kausale.
                            Beispiel 1
                            1) Messer   2) Apfel   3) Zeitung   4) Brot   5) Zigarre   6) Armband
                            Antwort: 24 (Apfel und Brot)
                            „Apfel" und „Brot" sind gleichartig, gehören zur Gruppe „Lebensmittel" und haben keine direkte Verbindung zueinander. Man darf z.B. nicht „Messer" und „Brot" wählen, da diese funktional verbunden sind (mit dem Messer schneidet man Brot), aber keine gleichartige Gruppe bilden.
                            Beispiel 2
                            1) Gras   2) Roggen   3) Kuchen   4) Mehl   5) Weizen   6) Baum
                            Antwort: 25 (Roggen und Weizen)
                            „Roggen" und „Weizen" sind gleichartig, gehören zur Kategorie „Getreide" und haben keine direkte Verbindung zueinander. Man darf z.B. nicht „Mehl" und „Kuchen" wählen, da diese funktional verbunden sind. Auch „Gras" und „Baum" sind keine richtige Antwort, obwohl sie beide zur Gruppe „Pflanzen" gehören — denn dann würden auch Roggen und Weizen dazu gehören, und es wären vier Wörter statt zwei. Suchen Sie immer die Kategorie, in die genau zwei Wörter passen.
                            Es gibt wieder zwanzig solche Aufgaben. Tragen Sie Ihre Antwort in das Kästchen nach der jeweiligen Aufgabe ein. Schreiben Sie beide Ziffern in ein Kästchen, ohne sie durch Kommas, Punkte oder Bindestriche zu trennen, wie im Beispiel angegeben.
                            Wenn Sie die Antwort nicht wissen, können Sie die Aufgabe überspringen. Sie können später zurückkehren, wenn Zeit übrig bleibt. Falls Sie sich geirrt haben, können Sie die Antwort korrigieren. Arbeiten Sie schnell, da die Zeit begrenzt ist.
                            Klicken Sie auf WEITER und beginnen Sie zu arbeiten, wenn Sie bereit sind. Wenn die Zeit abgelaufen ist, wird das Aufgabenformular automatisch geschlossen. Wenn Sie früher fertig sind, können Sie Ihre Arbeit überprüfen oder einfach warten, aber beginnen Sie nicht mit den nächsten Aufgaben. Der Übergang zur nächsten Aufgabe erfolgt automatisch nach Ablauf der Zeit.

                            """
            );
            instructionText.setVisible(true);
        } else if (currentStep == 5) {
            System.out.println("step 5");
            tasks4160();
        } else if (currentStep == 6) {
            System.out.println("step 6");
            getResults(41, 60);
            testName.setVisible(false);
            nextStep.setVisible(true);
            instructionText.setText(
                    """
                            Untertest 4.
                            Bei der folgenden Aufgabe sollen Sie eine zerschnittene Figur, deren Teile in zufälliger Anordnung auf einer Fläche liegen, gedanklich zusammensetzen.
                            """
            );
            instructionText.setVisible(true);
            instructionText.setPrefHeight(100f);
            Image example = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest7/example.png")).toString());
            image1.setImage(example);
            image1.setVisible(true);
            image1.setLayoutY(200f);
            instructionText2.setText(
                    """
                            In der oberen Reihe sind Musterfiguren abgebildet, nummeriert mit 1, 2, 3, 4, 5. Darunter sind dieselben Figuren, aber in Stücke zerschnitten, abgebildet. Sie sollen aus den Stücken irgendeine Musterfigur zusammensetzen. Aus den ersten unteren Stücken ergibt sich Figur 1. Aus den zweiten unteren Stücken ergibt sich Figur 5, aus den dritten — Figur 2, aus den vierten — 4.
                            Das Beispiel ist sehr einfach, die eigentliche Aufgabe wird etwas schwieriger sein, aber das Arbeitsprinzip bleibt dasselbe. Die Aufgabe besteht aus zwei Teilen mit je 10 Figuren (scrollen Sie bis zum Ende, um alle Aufgaben zu sehen). In der oberen Reihe sind ebenfalls Musterfiguren abgebildet, nummeriert 1, 2, 3, 4, 5. Darunter befinden sich zwei Reihen von Stücken. Dann folgen die Antwortfelder für den ersten Teil. Weiter unten ist eine weitere Reihe von Musterfiguren abgebildet (ebenfalls nummeriert 1, 2, 3, 4, 5), darunter weitere zwei Reihen mit Stückvarianten und Antwortfeldern. Ihre Aufgabe besteht darin, aus jedem Stücksatz gedanklich eine der Musterfiguren zusammenzusetzen. Beim Zusammensetzen müssen alle Stücke verwendet werden, einzelne Teile dürfen nicht weggelassen werden.
                            Die Antwort ist die Nummer der Musterfigur, die nach Ihrer Meinung aus den Stücken entsteht. Diese Nummer ist in die Felder für die Aufgaben 121–140 einzutragen. Die Nummern der Musterfiguren wiederholen sich, da es nur fünf Muster aber zehn Stückvarianten gibt. Ein Muster kann zwei- oder dreimal vorkommen, vielleicht auch hintereinander. Lassen Sie sich davon nicht verwirren. Wenn nicht sofort erkennbar ist, welche Figur aus den Stücken entsteht, überspringen Sie diese und gehen Sie zur nächsten. Sie können später zurückkehren, wenn Zeit übrig bleibt.
                            Denken Sie daran, schnell zu arbeiten, da die Zeit begrenzt ist. Falls Sie sich geirrt haben, können Sie die Antwort korrigieren. Klicken Sie auf WEITER und beginnen Sie zu arbeiten, wenn Sie bereit sind. Wenn die Zeit abgelaufen ist, wird das Aufgabenformular automatisch geschlossen. Wenn Sie früher fertig sind, können Sie Ihre Arbeit überprüfen oder einfach warten, aber beginnen Sie nicht mit den nächsten Aufgaben. Der Übergang zur nächsten Aufgabe erfolgt automatisch nach Ablauf der Zeit.
                             """
            );
            instructionText2.setVisible(true);
        } else if (currentStep == 7) {
            System.out.println("step 7");
            tasks121140();
        } else if (currentStep == 8) {
            System.out.println("step 8");
            getResults(121, 140);
            testName.setVisible(false);
            nextStep.setVisible(true);
            instructionText.setText(
                    """
                            Untertest 5.
                            Die folgende Aufgabe ähnelt der vorherigen, nur dass diesmal Würfel als Muster verwendet werden.
                            """
            );
            instructionText.setPrefHeight(100f);
            instructionText.setLayoutY(50f);
            instructionText.setVisible(true);
            Image example = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest8/example.png")).toString());
            image1.setImage(example);
            image1.setVisible(true);
            image1.setLayoutY(150f);
            instructionText2.setText(
                    """
                            Die obere Reihe zeigt Musterwürfel, nummeriert 1, 2, 3, 4, 5. Alle Musterwürfel sind verschieden, da ihre Flächen unterschiedlich bemalt sind (keine Löcher, sondern aufgemalte Kreise, Quadrate und Linien). Darunter sind dieselben Würfel in veränderter Lage abgebildet. Sie können in der horizontalen oder vertikalen Ebene gedreht sein, oder in beiden gleichzeitig. Wenn ein Würfel gedreht wird, verändert sich sein Aussehen. Eine Fläche kann verschwinden und eine neue erscheinen, aber zwei Flächen sind immer sichtbar, wenn auch in veränderter Position. Sie sollen, indem Sie die Muster auf den Flächen vergleichen, bestimmen, welchem Muster jeder der darunter abgebildeten Würfel entspricht.
                             Der erste untere Würfel entspricht Muster 1. Der zweite untere Würfel stellt Muster 5 dar. Verfolgen wir die Veränderungen des dritten unteren Würfels: Wenn er einmal in der vertikalen Ebene gegen den Uhrzeigersinn gedreht wird, bewegt sich der Kreis aus der oberen linken Ecke in die untere linke, die obere Fläche verschwindet, die rechte Fläche wird zur oberen, und an ihrer Stelle erscheint eine neue Fläche. Das Ergebnis ist Muster 2. Der vierte untere Würfel stellt Muster 3 dar, der fünfte — Muster 4.
                             Die eigentliche Aufgabe ist genau gleich. In der oberen Reihe befinden sich Musterwürfel (nummeriert 1, 2, 3, 4, 5), darunter Reihen von Würfeln, die mit den Mustern verglichen und identifiziert werden sollen. Die Antwort (d.h. die Nummer des gewählten Musterwürfels) ist in den Feldern für die Aufgaben 141–160 einzutragen. Mehrere Aufgabenwürfel können einem Muster ähneln, da es nur fünf Muster, aber zwanzig Würfel gibt. Die Antwortnummern wiederholen sich daher, jede kann mehrfach vorkommen, möglicherweise auch hintereinander. Wenn ein Würfel nicht bestimmbar ist, können Sie ihn überspringen.
                             Denken Sie daran, dass die Zeit begrenzt ist. Versuchen Sie, in dieser Zeit alle Aufgabenwürfel zu betrachten. Am Ende könnten leichtere Aufgaben stehen. Falls Sie sich geirrt haben, können Sie die Antwort korrigieren.
                             Klicken Sie auf WEITER und beginnen Sie zu arbeiten, wenn Sie bereit sind. Wenn die Zeit abgelaufen ist, wird das Aufgabenformular automatisch geschlossen. Wenn Sie früher fertig sind, können Sie Ihre Arbeit überprüfen oder einfach warten, aber beginnen Sie nicht mit den nächsten Aufgaben. Der Übergang zur nächsten Aufgabe erfolgt automatisch nach Ablauf der Zeit.
                            """
            );
            instructionText2.setVisible(true);
            image2.setVisible(false);
        } else if (currentStep == 9) {
            System.out.println("step 9");
            tasks141160();
        } else if (currentStep == 10) {
            System.out.println("step 10");
            getResults(141, 160);
            closeWindow();
            this.printWriter.close();
        }
    }

    private void getResults(int start, int finish) {
        Pane parent = (Pane) testName.getParent();
        for (int i = start; i <= finish; ++i) {
            TextField textField = (TextField) parent.lookup("#" + TASK + i);
            this.printWriter.println("%s,%s,%s".formatted(i, textField.getText(), isAnswerCorrect(i, textField.getText())));
            textField.setDisable(true);
            textField.setVisible(false);
            parent.getChildren().remove(textField);
            Label l = (Label) parent.lookup("#" + LABEL + i);
            l.setDisable(true);
            l.setVisible(false);
            parent.getChildren().remove(l);
        }
        this.printWriter.flush();
    }

    private String isAnswerCorrect(int taskId, String rawAnswer) {
        if (CORRECT_ANSWERS.containsKey(taskId))
            return CORRECT_ANSWERS.get(taskId).equalsIgnoreCase(rawAnswer) ? "true" : "false";
        return "false";
    }

    private void tasks120() {
        testName.setVisible(true);
        testName.setText("AUFGABEN 1-20");
        instructionText.setVisible(false);
        nextStep.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.minutes(6),
                        actionEvent -> callNextStep()
                ));
        animation.setCycleCount(1);
        animation.play();
        for (int i = 1; i <= 20; ++i) {
            Label e = new Label(String.valueOf(i));
            e.setId(LABEL + i);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i);
            e.setText(TASKS_120.getOrDefault(i, String.valueOf(i)));
            parent.getChildren().add(e);
            TextField inputField = new TextField("");
            UnaryOperator<TextFormatter.Change> rejectChange = c -> {
                if (c.isContentChange()) {
                    for (char ch : c.getControlNewText().toCharArray()) {
                        if (!Character.isDigit(ch)) {
                            return null;
                        }
                    }
                    return c;
                }
                return c;
            };
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + i);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + LAYOUT_X);
            parent.getChildren().add(inputField);
        }
    }

    private void tasks2140() {
        testName.setVisible(true);
        testName.setText("AUFGABEN 21-40");
        instructionText.setVisible(false);
        nextStep.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.minutes(7),
                        actionEvent -> callNextStep()
                ));
        animation.setCycleCount(1);
        animation.play();
        for (int i = 1; i <= 20; ++i) { // + 20
            int idx = i + 20;
            Label e = new Label(String.valueOf(idx));
            e.setId(LABEL + idx);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i);
            e.setText(TASKS_2140.getOrDefault(i, String.valueOf(i)));
            parent.getChildren().add(e);
            TextField inputField = new TextField("");
            UnaryOperator<TextFormatter.Change> rejectChange = c -> {
                if (c.isContentChange()) {
                    for (char ch : c.getControlNewText().toCharArray()) {
                        if (!Character.isDigit(ch)) {
                            return null;
                        }
                    }
                    return c;
                }
                return c;
            };
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + idx);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + LAYOUT_X);
            parent.getChildren().add(inputField);
        }
    }

    private void tasks4160() {
        testName.setVisible(true);
        testName.setText("AUFGABEN 41-60");
        instructionText.setVisible(false);
        nextStep.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.minutes(8),
                        actionEvent -> callNextStep()
                ));
        animation.setCycleCount(1);
        animation.play();
        for (int i = 1; i <= 20; ++i) { // +40
            int idx = i + 40;
            Label e = new Label(String.valueOf(idx));
            e.setId(LABEL + idx);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i);
            e.setText(TASKS_4160.getOrDefault(i, String.valueOf(i)));
            parent.getChildren().add(e);
            TextField inputField = new TextField("");
            UnaryOperator<TextFormatter.Change> rejectChange = c -> {
                if (c.isContentChange()) {
                    for (char ch : c.getControlNewText().toCharArray()) {
                        if (!Character.isDigit(ch)) {
                            return null;
                        }
                    }
                    return c;
                }
                return c;
            };
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + idx);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + LAYOUT_X);
            parent.getChildren().add(inputField);
        }
    }

    private void tasks121140() {
        testName.setVisible(true);
        testName.setText("AUFGABEN 121-140");
        instructionText.setVisible(false);
        instructionText2.setVisible(false);
        Image base = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest7/121-130.png")).toString());
        image1.setImage(base);
        image1.setLayoutY(50f);
        image1.setFitHeight(500f);
        image1.setFitWidth(1000f);
        image1.setPreserveRatio(true);
        image1.setSmooth(true);
        image1.setVisible(true);
        nextStep.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.minutes(7),
                        actionEvent -> callNextStep()
                ));
        animation.setCycleCount(1);
        animation.play();
        for (int i = 1; i <= 10; ++i) { // +120
            int idx = i + 120;
            Label e = new Label(String.valueOf(idx));
            e.setId(LABEL + idx);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i + 450);
            e.setText(String.valueOf(idx));
            parent.getChildren().add(e);
            TextField inputField = new TextField("");
            UnaryOperator<TextFormatter.Change> rejectChange = c -> {
                if (c.isContentChange()) {
                    for (char ch : c.getControlNewText().toCharArray()) {
                        if (!Character.isDigit(ch)) {
                            return null;
                        }
                    }
                    return c;
                }
                return c;
            };
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + idx);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + 450 + 25);
            parent.getChildren().add(inputField);
        }
        // add another image view
        Image base2 = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest7/131-140.png")).toString());
        image2.setImage(base2);
        image2.setLayoutY(1500f);
        image2.setFitHeight(500f);
        image2.setFitWidth(1000f);
        image2.setPreserveRatio(true);
        image2.setSmooth(true);
        image2.setVisible(true);
        for (int i = 1; i <= 10; ++i) { // +130
            int idx = i + 130;
            Label e = new Label(String.valueOf(idx));
            e.setId(LABEL + idx);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i + 1900);
            e.setText(String.valueOf(idx));
            parent.getChildren().add(e);
            TextField inputField = new TextField("");
            UnaryOperator<TextFormatter.Change> rejectChange = c -> {
                if (c.isContentChange()) {
                    for (char ch : c.getControlNewText().toCharArray()) {
                        if (!Character.isDigit(ch)) {
                            return null;
                        }
                    }
                    return c;
                }
                return c;
            };
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + idx);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + 1900 + 25);
            parent.getChildren().add(inputField);
        }
    }

    private void tasks141160() {
        testName.setVisible(true);
        testName.setText("AUFGABEN 141-160");
        instructionText.setVisible(false);
        instructionText2.setVisible(false);

        Image base = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest8/base.png")).toString());
        image1.setImage(base);
        image1.setFitHeight(500f);
        image1.setFitWidth(1000f);
        image1.setLayoutY(50f);
        image1.setPreserveRatio(true);
        image1.setSmooth(true);
        image1.setVisible(true);
        Image tests = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest8/141-160.png")).toString());
        image2.setImage(tests);
        image2.setFitHeight(500f);
        image2.setFitWidth(1000f);
        image2.setLayoutY(250f);
        image2.setPreserveRatio(true);
        image2.setSmooth(true);
        image2.setVisible(true);
        nextStep.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.minutes(9),
                        actionEvent -> callNextStep()
                ));
        animation.setCycleCount(1);
        animation.play();
        for (int i = 1; i <= 20; ++i) { // +140
            int idx = i + 140;
            Label e = new Label(String.valueOf(idx));
            e.setId(LABEL + idx);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i + 650);
            e.setText(String.valueOf(idx));
            parent.getChildren().add(e);
            TextField inputField = new TextField("");
            UnaryOperator<TextFormatter.Change> rejectChange = c -> {
                if (c.isContentChange()) {
                    for (char ch : c.getControlNewText().toCharArray()) {
                        if (!Character.isDigit(ch)) {
                            return null;
                        }
                    }
                    return c;
                }
                return c;
            };
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + idx);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + 650 + 25);
            parent.getChildren().add(inputField);
        }

    }
}
