package adiitya.shard;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public abstract class SubCommand extends Command {

	private final Command parent;

	/**
	 * The instance created will have the same
	 * synchronization as it's parent.
	 *
	 * @param name The command name. This isn't the same as an alias
	 * @param usage The usage
	 * @param argumentCount The argument count predicate
	 * @param parent The parent of this command
	 */
	public SubCommand(String name, String usage, Predicate<Integer> argumentCount, Command parent) {
		super(parent.plugin, parent.isAsync(), name, usage, argumentCount);
		this.parent = parent;
	}

	@Override
	public final boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
		execute(sender, new ArrayList<>(Arrays.asList(args)));
		return true;
	}

	@Override
	public final List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
		return tabComplete(sender, new ArrayList<>(Arrays.asList(args)));
	}

	/**
	 * This method combines the raw usage of it's parent with it's own.
	 * An example of this is <br>
	 * {@code /example <yes|no> subcommand [optional parameter]}.
	 *
	 * @return The usage
	 */
	@Override
	public String getUsage() {
		return String.format("%s %s", parent.getRawUsage(), getRawUsage()).trim();
	}
}
