package highlighting;

import highlighting.antlr.*;
import highlighting.core.SyntaxHighlighter;
import highlighting.presets.Texts;
import highlighting.regex.*;
import highlighting.ui.EditorUI;
import java.util.Scanner;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {

  public static void main(String... args) {
    SyntaxHighlighter regex = new RegexHighlighter();
    SyntaxHighlighter scanning = new ScanningHighlighter();
    SyntaxHighlighter antlrToken = new AntlrTokenCollector();

    EditorUI.show(Texts.START_TEXT, regex);
    EditorUI.show(Texts.START_TEXT, scanning);
    EditorUI.show(Texts.START_TEXT, antlrToken);

    Scanner scanner = new Scanner(System.in);
    System.out.print("Einrueckung pro Stufe: ");
    int indentWidth = scanner.nextInt();

    MiniJavaLexer lexer = new MiniJavaLexer(CharStreams.fromString(Texts.START_TEXT));
    MiniJavaParser parser = new MiniJavaParser(new CommonTokenStream(lexer));

    PrettyPrinterVisitor visitor = new PrettyPrinterVisitor(indentWidth);
    visitor.visit(parser.compilationUnit());

    System.out.println();
    System.out.println("Pretty Printed MiniJava:");
    System.out.println(visitor.result());
  }
}
