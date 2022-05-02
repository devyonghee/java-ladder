package nextstep.ladder.model;

import nextstep.common.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Results {

    private static final int MINIMUM_COUNT = 2;

    private final List<Result> results;

    private Results(List<Result> results) {
        Assert.notEmpty(results, "results must not be empty");
        validateSize(results);
        this.results = new ArrayList<>(results);
    }

    public static Results from(List<String> strings) {
        Assert.notNull(strings, "strings must not be null");
        return new Results(strings.stream()
                .map(Result::from)
                .collect(Collectors.toList())
        );
    }

    public List<Result> list() {
        return results;
    }

    public boolean hasDifferentSize(int size) {
        return results.size() != size;
    }

    public Result result(int index) {
        if (results.size() <= index || index < 0) {
            throw new IndexOutOfBoundsException(String.format("results index(%d) out of range : results size(%d)", index, results.size()));
        }
        return results.get(index);
    }

    private void validateSize(List<Result> results) {
        if (results.size() < MINIMUM_COUNT) {
            throw new IllegalArgumentException(String.format("results count(%d) must be at least %d", results.size(), MINIMUM_COUNT));
        }
    }

    @Override
    public String toString() {
        return "Results{" +
                "results=" + results +
                '}';
    }
}
