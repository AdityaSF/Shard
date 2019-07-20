package adiitya.shard;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public abstract class SubCommand extends Command {

	private final Command parent;

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

	@Override
	public String getUsage() {
		return String.format("%s %s", parent.getRawUsage(), getRawUsage()).trim();
	}
}
