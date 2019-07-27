# Shard
A command API for Spigot/Bukkit for 1.14

## Table of Contents
* [Overview](#overview)
* [Features](#features)
* [Usage](#usage)
* [Examples](#examples)

## Overview
Shard was created because, as I was making a lot of commands, 
I realized how tedious it is to make subcommands. Shard 
supports not only subcommands, but more vanilla style commands 
with no subcommands. This library was highly based on [Lucko's](https://github.com/lucko) 
command API for [LuckPerms](https://github.com/lucko/LuckPerms).

## Features
* Simple subcommand structure
* New tab completion API
* Vanilla style commands for simple command uses

## Usage
Shard is not currently deployed anywhere, so you must install it manually:
```
mvn clean install
```
Once installed, you can add it to your project:
```xml
<dependencies>
    <dependency>
        <groupId>adiitya.spigot</groupId>
        <artifactId>shard</artifactId>
        <version>@VERSION@</version>
    </dependency>
</dependencies>
```

## Examples
Creating a simple command:
```java
import adiitya.shard.SingleCommand;
import adiitya.shard.completion.TabCompleter;
import java.util.List;
import org.bukkit.command.CommandSender;

public class ExampleCommand extends SingleCommand {
    
    public ExampleCommand(JavaPlugin plugin) {
        super(plugin, "example", "[page]");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        
        int page = args.isEmpty() ? 0 : Integer.parseInt(args.get(0));

        // more logic here
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> args) {
        return TabCompleter.empty();
    }   
}
```

Using the tab completion API:
```java
    @Override
    public List<String> tabComplete(CommandSender sender, List<String> args) {
        return new TabCompleter()
            .add(0, new TabCompletion("yes", test -> "yes".startsWith(test)))
            .add(0, new TabCompletion("no", test -> "no".startsWith(test)))
            .get(args); // this will parse the args and return a list of valid completions
    }
```
