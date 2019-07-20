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

	public abstract void execute(CommandSender sender, List<String> args);
	public abstract List<String> tabComplete(CommandSender sender, List<String> args);

	public String getUsage() {
		return String.format("/%s", getRawUsage());
	}

	public String getRawUsage() {
		return String.format("%s %s", getName(), usage).trim();
	}
}
