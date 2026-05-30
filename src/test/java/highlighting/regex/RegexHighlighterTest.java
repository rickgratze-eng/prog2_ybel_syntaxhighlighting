package highlighting.regex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import highlighting.core.HighlightRegion;
import java.util.List;
import org.junit.jupiter.api.Test;

class RegexHighlighterTest {

  @Test
  void shouldReturnNoRegionsForEmptyText() {
    RegexHighlighter highlighter = new RegexHighlighter();

    List<HighlightRegion> regions = highlighter.computeRegions("");

    assertTrue(regions.isEmpty());
  }

  @Test
  void shouldHighlightSimpleKeyword() {
    RegexHighlighter highlighter = new RegexHighlighter();

    List<HighlightRegion> regions = highlighter.computeRegions("public");

    assertEquals(1, regions.size());
    assertEquals(0, regions.get(0).start());
    assertEquals(6, regions.get(0).end());
  }

  @Test
  void shouldPreferCommentOverKeywordInsideComment() {
    RegexHighlighter highlighter = new RegexHighlighter();

    List<HighlightRegion> regions = highlighter.computeRegions("// public");

    assertEquals(1, regions.size());
    assertEquals(0, regions.get(0).start());
    assertEquals(9, regions.get(0).end());
  }

  @Test
  void shouldKeepAdjacentRegions() {
    RegexHighlighter highlighter = new RegexHighlighter();

    List<HighlightRegion> regions = highlighter.computeRegions("\"a\"'b'");

    assertEquals(2, regions.size());
    assertEquals(0, regions.get(0).start());
    assertEquals(3, regions.get(0).end());
    assertEquals(3, regions.get(1).start());
    assertEquals(6, regions.get(1).end());
  }

  @Test
  void shouldPreferJavadocOverBlockComment() {
    RegexHighlighter highlighter = new RegexHighlighter();

    List<HighlightRegion> regions = highlighter.computeRegions("/** documentation */");

    assertEquals(1, regions.size());
  }
}
