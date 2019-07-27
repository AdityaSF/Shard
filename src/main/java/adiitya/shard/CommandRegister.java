package adiitya.shard;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegister {

	private final JavaPlugin plugin;

	public CommandRegister(JavaPlugin plugin) {

		this.plugin = plugin;
	}

	public void registerCommand(Command command) {

		for (String alias : command.getAliases())
			registerCommand(command, alias);
	}

	private void registerCommand(Command command, String alias) {

		PluginCommand pluginCommand = plugin.getCommand(alias);

		if (pluginCommand == null)
			return;

		pluginCommand.setExecutor(command);
		pluginCommand.setTabCompleter(command);
	}
}
