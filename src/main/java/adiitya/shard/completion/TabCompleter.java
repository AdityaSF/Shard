package adiitya.shard.completion;

import java.util.*;
import java.util.function.BiPredicate;

public final class TabCompleter {

	private final Map<Integer, List<TabCompletion>> completionMap;

	public TabCompleter() {
		completionMap = new HashMap<>();
	}

	/**
	 * Returns an empty list.
	 *
	 * @return The completions
	 */
	public static List<String> empty() {
		return new TabCompleter().get(new ArrayList<>());
	}

	/**
	 * Adds a tab completion at the specified position.
	 *
	 * @param pos The position
	 * @param completion The TabCompletion
	 * @return The TabCompleter
	 */
	public TabCompleter add(int pos, TabCompletion completion) {

		List<TabCompletion> completions = completionMap.getOrDefault(pos, new ArrayList<>());
		completions.add(completion);

		completionMap.put(pos, completions);

		return this;
	}

	/**
	 * Adds multiple tab completions at the specified position. This
	 * is useful for {@link TabCompletions#players()}.
	 *
	 * @param pos The position
	 * @param completions The TabCompletions
	 * @return The TabCompleter
	 */
	public TabCompleter add(int pos, List<TabCompletion> completions) {

		completions.forEach(c -> add(pos, c));

		return this;
	}

	/**
	 * Registers each String as a TabCompletion with the given BiPredicate.
	 * The first parameter of the BiPredicate is the test value, the second
	 * is the result.
	 *
	 * @param pos The position
	 * @param completions The result strings
	 * @param condition The condition
	 * @return The TabCompleter
	 */
	public TabCompleter add(int pos, List<String> completions, BiPredicate<String, String> condition) {

		completions.stream()
				.map(s -> new TabCompletion(s, test -> condition.test(test, s)))
				.forEach(c -> add(pos, c));

		return this;
	}

	/**
	 * Processes the registered TabCompletions and returns the results
	 * of each passing TabCompletion.
	 *
	 * @param args The tab completion args
	 * @return The tab completions
	 */
	public List<String> get(List<String> args) {

		if (args.isEmpty())
			return new ArrayList<>();

		List<String> results = new ArrayList<>();
		String search = args.get(args.size() - 1);

		completionMap.getOrDefault(args.size() - 1, new ArrayList<>())
				.stream()
				.filter(c -> c.passes(search))
				.forEach(c -> results.add(c.getResult()));

		return results;
	}
}
