package adiitya.shard;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SingleCommand extends Command {

	public SingleCommand(JavaPlugin plugin, String name, String usage) {

		super(plugin, false, name, usage, i -> false);
	}

	public SingleCommand(JavaPlugin plugin, boolean async, String name, String usage) {

		super(plugin, async, name, usage, i -> false);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {

		List<String> argList = Arrays.asList(args);

		if (isAsync())
			Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> execute(sender, argList));
		else
			Bukkit.getScheduler().runTask(plugin, () -> execute(sender, argList));

		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
		return tabComplete(sender, new ArrayList<>(Arrays.asList(args)));
	}

	@Override
	public abstract void execute(CommandSender sender, List<String> args);

	@Override
	public abstract List<String> tabComplete(CommandSender sender, List<String> args);
}
