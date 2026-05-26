package highlighting.regex;

import highlighting.core.HighlightRegion;
import highlighting.core.SyntaxHighlighter;
import highlighting.presets.MiniJavaTokens;
import java.util.ArrayList;
import java.util.List;

public class RegexHighlighter extends SyntaxHighlighter {

  @Override
  public List<HighlightRegion> collectMatches(String text) {
    List<HighlightRegion> regions = new ArrayList<>();

    for (Token token : MiniJavaTokens.defaultTokens()) {
      regions.addAll(token.test(text));
    }

    return regions;
  }

  @Override
  public List<HighlightRegion> resolveConflicts(List<HighlightRegion> regions) {
    List<HighlightRegion> resolved = new ArrayList<>();

    for (HighlightRegion region : regions) {
      if (!overlapsWithAny(region, resolved)) {
        resolved.add(region);
      }
    }

    return resolved;
  }

  private boolean overlapsWithAny(HighlightRegion region, List<HighlightRegion> resolved) {
    for (HighlightRegion existing : resolved) {
      if (overlaps(region, existing)) {
        return true;
      }
    }

    return false;
  }

  private boolean overlaps(HighlightRegion a, HighlightRegion b) {
    return a.start() < b.end() && b.start() < a.end();
  }
}
