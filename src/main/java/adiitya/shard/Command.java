package adiitya.shard;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Command implements TabExecutor {

	protected final JavaPlugin plugin;
	@Getter private final boolean async;

	@Getter private final String name;
	protected final String usage;
	@Getter private final Predicate<Integer> argumentCount;
	@Getter protected final List<Command> children = new ArrayList<>();

	public Command(JavaPlugin plugin, String name, String usage, Predicate<Integer> argumentCount) {
		this(plugin, false, name, usage, argumentCount);
	}

	public Command(JavaPlugin plugin, boolean async, String name, String usage, Predicate<Integer> argumentCount) {
		this.plugin = plugin;
		this.async = async;
		this.name = name;
		this.usage = usage;
		this.argumentCount = argumentCount;
	}

	/**
	 * This method should be overwritten to provide command functionality.
	 * This method will be called synchronously or asynchronously depending
	 * on what is specified in the constructor.
	 *
	 * @param sender The sender
	 * @param args The args
	 */
	public abstract void execute(CommandSender sender, List<String> args);

	/**
	 * This methos should be overwritten to provide command functionality.
	 * This method should provide a list of completions based on {@code args}.
	 *
	 * @param sender The sender
	 * @param args The args
	 * @return The completions to be displayed
	 */
	public abstract List<String> tabComplete(CommandSender sender, List<String> args);

	/**
	 * This method should provide a list of aliases for this command. If
	 * returning an empty list, only the command name will be registered.
	 *
	 * @return The aliases
	 */
	public abstract List<String> getAliases();

	public String getUsage() {
		return String.format("/%s", getRawUsage());
	}

	public String getRawUsage() {
		return String.format("%s %s", getName(), usage).trim();
	}
}
