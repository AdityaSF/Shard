package adiitya.shard.completion;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@UtilityClass
public class TabCompletions {

	/**
	 * Constructs a list of TabCompletions from the online players.
	 *
	 * @return The TabCompletions
	 */
	public List<TabCompletion> players() {

		return Bukkit.getOnlinePlayers().stream()
				.map((Player p) -> new TabCompletion(p.getName(), test -> startsWith(test, p.getName())))
				.collect(Collectors.toList());
	}

	/**
	 * A case insensitive replacement for {@link String#startsWith(String)}.
	 * This is useful for constructing case insensitive TabCompletions.
	 *
	 * @param test   The test String
	 * @param string The result
	 * @return Whether the test is valid
	 */
	public boolean startsWith(String test, String string) {

		String pattern = String.format("^\\Q%s\\E(\\w)*", test);
		Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(string);

		return matcher.matches();
	}

	/**
	 * Constructs a TabCompletion that always fails.
	 *
	 * @return The TabCompletion
	 */
	public TabCompletion never() {

		return new TabCompletion("", test -> false);
	}

	/**
	 * Constructs a TabCompletion that always passes.
	 *
	 * @param result The result
	 * @return The TabCompletion
	 */
	public TabCompletion always(String result) {

		return new TabCompletion(result, test -> true);
	}
}
