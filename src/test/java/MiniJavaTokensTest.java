import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import highlighting.presets.MiniJavaTokens;
import org.junit.jupiter.api.Test;

public class MiniJavaTokensTest {

  @Test
  void shouldMatchStringLiteral() {
    String text = "\"hello\"";

    boolean found =
        MiniJavaTokens.defaultTokens().stream().anyMatch(token -> !token.test(text).isEmpty());

    assertTrue(found);
  }

  @Test
  void shouldMatchKeyword() {
    String text = "public class Test";

    boolean found =
        MiniJavaTokens.defaultTokens().stream().anyMatch(token -> !token.test(text).isEmpty());

    assertTrue(found);
  }

  @Test
  void shouldMatchComment() {
    String text = "// comment";

    boolean found =
        MiniJavaTokens.defaultTokens().stream().anyMatch(token -> !token.test(text).isEmpty());

    assertTrue(found);
  }

  @Test
  void shouldNotMatchRandomText() {
    String text = "abcdefgh";

    boolean found =
        MiniJavaTokens.defaultTokens().stream().anyMatch(token -> !token.test(text).isEmpty());

    assertFalse(found);
  }

  @Test
  void shouldMatchAnnotation() {
    String text = "@Override";

    boolean found =
        MiniJavaTokens.defaultTokens().stream().anyMatch(token -> !token.test(text).isEmpty());

    assertTrue(found);
  }

  @Test
  void shouldMatchCharacterLiteral() {
    String text = "'a'";

    boolean found =
        MiniJavaTokens.defaultTokens().stream().anyMatch(token -> !token.test(text).isEmpty());

    assertTrue(found);
  }

  @Test
  void shouldMatchBlockComment() {
    String text = "/* comment */";

    boolean found =
        MiniJavaTokens.defaultTokens().stream().anyMatch(token -> !token.test(text).isEmpty());

    assertTrue(found);
  }
}
