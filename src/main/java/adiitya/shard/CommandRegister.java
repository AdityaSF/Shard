package adiitya.shard;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegister {

	private final JavaPlugin plugin;

	/**
	 * The CommandRegister is used to easily register Shard commands.
	 * The JavaPlugin will be used to retrieve PluginCommands in
	 * the registerCommand method.
	 *
	 * @param plugin The plugin creating this instance. The commands
	 *                  will be registered with this plugin
	 */
	public CommandRegister(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * Registers the specified Command with the plugin. This will also
	 * register it under it's provided aliases, given the commands exist.
	 *
	 * @param command The Command
	 */
	public void registerCommand(Command command) {

		for (String alias : command.getAliases())
			registerCommand(command, alias);
	}

	/**
	 * Registers a Command with a certain name.
	 *
	 * @param command The Command
	 * @param alias The alias
	 */
	private void registerCommand(Command command, String alias) {

		PluginCommand pluginCommand = plugin.getCommand(alias);

		if (pluginCommand == null)
			return;

		pluginCommand.setExecutor(command);
		pluginCommand.setTabCompleter(command);
	}
}
