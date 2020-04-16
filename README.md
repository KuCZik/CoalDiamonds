# CoalDiamonds

Mine coal with chance to drop diamonds!!!

## Introduction

This plugin give you a changeable chance to drop diamond from coal ore when you mine it.

This plugin is under development, so you can contribute to developing with your ideas!

Idea for this plugin was invited by Mertum. Thanks bro!

## Status

Version 1.2 for Spigot 1.15.2 now WIP!

## Features

* Mine coal ore with chance to drop diamond
* Changeable percentage
* Changeable chance for every pickaxe
* Permissions support (You can turn using perms on/off with command or in config file)
* Few admin commands
* Changeable messages (You can translate this plugin on your own)
* Giving more XP from coal ore, when diamond was dropped (This value can be changed)
* Launching firework when you have luck and you drop diamond from coal ore (This can be turned off)
* You have better chance to drop diamond, when you have Fortune on your pickaxe (Adding Fortune enchant level number to base pickaxe chance value)

## Commands

* /cdia - show you help
* /cdia reload - reload config.yml
* /cdia useperms &lt;true/false&gt; - turning usage of permissions on and off
* /cdia launchfirework &lt;true/false&gt; - turning launching fireworks on and off
* /cdia usefortune &lt;true/false&gt; - turning adding Fortune level number to base chance on and off
* /cdia percentage &lt;value&gt; - set percentage value (default: 100, how my chance system work you can find under Installation section)
* /cdia xpdrop &lt;value&gt; - set ammount of XP points earned, when diamond is dropped (default: 100)
* /cdia chance wood &lt;value&gt; - set chance to wood pickaxe (default: 0)
* /cdia chance stone &lt;value&gt; - set chance to stone pickaxe (default: 0)
* /cdia chance iron &lt;value&gt; - set chance to iron pickaxe (default: 1)
* /cdia chance gold &lt;value&gt; - set chance to gold pickaxe (default: 2)
* /cdia chance dia &lt;value&gt; - set chance to diamond pickaxe (default: 3)

## Permissions

* CoalDiamonds.* - Gives player chance to mine diamonds from coal ore and access to every command
* CoalDiamonds.cmd.* - Gives player access to every command
* CoalDiamonds.canMine - Gives player chance to mine diamonds from coal ore
* CoalDiamonds.cmd.reload - Gives player access to /cdia reload command
* CoalDiamonds.cmd.percentage - Gives player access to /cdia percentage command
* CoalDiamonds.cmd.chance - Gives player access to /cdia chance command
* CoalDiamonds.cmd.usePerms - Gives player access to /cdia useperms command
* CoalDiamonds.cmd.useFortune - Gives player access to /cdia usefortune command
* CoalDiamonds.cmd.xpDrop - Gives player access to /cdia xpdrop command
* CoalDiamonds.cmd.launchFirework - Gives player access to /cdia launchfirework command

## Planning

* Update checking

If you have something good on your mind that this plugin don't have, create issue with your feature please.

## Known Issues

* Nothing yet

## Installation

Just put CoalDiamonds.jar in plugins folder. Config.yml will be created automatically. You can edit everything in your config by commands.

## Chance System

If you have percentage value 100 and Diamond Pickaxe chance on 3, you will have 3% chance to drop Diamond from Coal Ore (If you use Fortune feature then with Fortune III and Diamond Pickaxe you will have 6% chance). If you have percentage value on 1000 and Diamond Pickaxe chance on 3, you will have 0,3% chance.

## Download

You can download this plugin on:

* [GitHub](https://github.com/KuCZik/CoalDiamonds/releases "GitHub")
* [SpigotMC]( "SpigotMC")
* [BukkitDev](https://dev.bukkit.org/projects/coaldiamonds "BukkitDev")

## Feedback

If you like this plugin, you can donate [HERE](https://paypal.me/KuCZik "Donate").

If you have something on your mind what I can change or add, or if you find a bug, please create issue.

I am begineer in programming, so be patient (or if something could be done better, please tell me).

PS: Sorry for my bad English.