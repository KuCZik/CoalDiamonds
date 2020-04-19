package cz.KuCZik.CoalDiamonds;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author KuCZik
 * Using Spigot API build 2665
 */

public class Main extends JavaPlugin {

    // Declaration of variables
    private Block brokenByPlayer;
    private Player playerWhoBrokeBlock;

    // Declaring strings from config.yml
    private String noPerms;
    private String configReload;
    private String usingPerms;
    private String launchFirework;
    private String percentageError;
    private String numberError;
    private String syntaxError;
    private String badSyntax;
    private String percentageValue;
    private String enableFortune;
    private String xpDropValue;
    private String chanceError;
    private String woodenChance;
    private String stoneChance;
    private String ironChance;
    private String goldenChance;
    private String diamondChance;
    private String noCommandsPerms;
    private String help;
    private String helpReload;
    private String helpPerms;
    private String helpFirework;
    private String helpPercentage;
    private String helpFortune;
    private String helpXpDrop;
    private String helpWooden;
    private String helpStone;
    private String helpIron;
    private String helpGolden;
    private String helpDiamond;
    private String luckyDrop;
    private String onEnable;
    private String onDisable;

    // Declaring variables from config.yml
    private int configChanceWood;
    private int configChanceStone;
    private int configChanceIron;
    private int configChanceGold;
    private int configChanceDia;
    private int percentage;
    private boolean useFortune;
    private boolean usePerms;
    private int xpDrop;
    private boolean launchFireworkConfig;

    // Method to load data from created config.yml
    private void loadConfig(){
        noPerms = getConfig().getString("message.noPerms");
        configReload = getConfig().getString("message.configReload");
        usingPerms = getConfig().getString("message.usingPerms");
        launchFirework = getConfig().getString("message.launchFirework");
        percentageError = getConfig().getString("message.percentageError");
        numberError = getConfig().getString("message.numberError");
        syntaxError = getConfig().getString("message.syntaxError");
        badSyntax = getConfig().getString("message.badSyntax");
        percentageValue = getConfig().getString("message.percentageValue");
        enableFortune = getConfig().getString("message.enableFortune");
        xpDropValue = getConfig().getString("message.xpDropValue");
        chanceError = getConfig().getString("message.chanceError");
        woodenChance = getConfig().getString("message.woodenChance");
        stoneChance = getConfig().getString("message.stoneChance");
        ironChance = getConfig().getString("message.ironChance");
        goldenChance = getConfig().getString("message.goldenChance");
        diamondChance = getConfig().getString("message.diamondChance");
        noCommandsPerms = getConfig().getString("message.noCommandsPerms");
        help = getConfig().getString("message.help");
        helpReload = getConfig().getString("message.helpReload");
        helpPerms = getConfig().getString("message.helpPerms");
        helpFirework = getConfig().getString("message.helpFirework");
        helpPercentage = getConfig().getString("message.helpPercentage");
        helpFortune = getConfig().getString("message.helpFortune");
        helpXpDrop = getConfig().getString("message.helpXpDrop");
        helpWooden = getConfig().getString("message.helpWooden");
        helpStone = getConfig().getString("message.helpStone");
        helpIron = getConfig().getString("message.helpIron");
        helpGolden = getConfig().getString("message.helpGolden");
        helpDiamond = getConfig().getString("message.helpDiamond");
        luckyDrop = getConfig().getString("message.luckyDrop");
        onEnable = getConfig().getString("message.onEnable");
        onDisable = getConfig().getString("message.onDisable");
        configChanceWood = getConfig().getInt("chance.woodPickaxe");
        configChanceStone = getConfig().getInt("chance.stonePickaxe");
        configChanceIron = getConfig().getInt("chance.ironPickaxe");
        configChanceGold = getConfig().getInt("chance.goldPickaxe");
        configChanceDia = getConfig().getInt("chance.diaPickaxe");
        percentage = getConfig().getInt("chance.percentage");
        useFortune = getConfig().getBoolean("chance.useFortune");
        usePerms = getConfig().getBoolean("permissions.usePerms");
        xpDrop = getConfig().getInt("chance.xpDrop");
        launchFireworkConfig = getConfig().getBoolean("misc.launchFirework");
    }

    // Adding default values in config when is not created
    private void loadDefaultConfig(){
        getConfig().addDefault("permissions.usePerms", false);
        getConfig().addDefault("chance.useFortune", true);
        getConfig().addDefault("chance.percentage", 100);
        getConfig().addDefault("chance.xpDrop", 100);
        getConfig().addDefault("chance.woodPickaxe", 0);
        getConfig().addDefault("chance.stonePickaxe", 0);
        getConfig().addDefault("chance.ironPickaxe", 1);
        getConfig().addDefault("chance.goldPickaxe", 2);
        getConfig().addDefault("chance.diaPickaxe", 3);
        getConfig().addDefault("misc.launchFirework", true);
        getConfig().addDefault("message.onEnable", "is now ENABLED!");
        getConfig().addDefault("message.onDisable", "is now DISABLED!");
        getConfig().addDefault("message.noPerms", "You don't have permissions to do that!");
        getConfig().addDefault("message.configReload", "Config reloaded");
        getConfig().addDefault("message.usingPerms", "Using permissions set to");
        getConfig().addDefault("message.launchFirework", "Launching fireworks set to");
        getConfig().addDefault("message.percentageError", "You can't set percentage number lower than one of your chance number!");
        getConfig().addDefault("message.numberError", "You can't use characters as arguments in this command!");
        getConfig().addDefault("message.syntaxError", "You must use some arguments with this command!");
        getConfig().addDefault("message.badSyntax", "You used bad argument! Please try it again.");
        getConfig().addDefault("message.percentageValue", "Percentage value set to");
        getConfig().addDefault("message.enableFortune", "Fortune chance set to");
        getConfig().addDefault("message.xpDropValue", "XP drop value set to");
        getConfig().addDefault("message.chanceError", "You can't set chance number higher than percentage number!");
        getConfig().addDefault("message.woodenChance", "Chance of wooden pickaxe set to");
        getConfig().addDefault("message.stoneChance", "Chance of stone pickaxe set to");
        getConfig().addDefault("message.ironChance", "Chance of iron pickaxe set to");
        getConfig().addDefault("message.goldenChance", "Chance of golden pickaxe set to");
        getConfig().addDefault("message.diamondChance", "Chance of diamond pickaxe set to");
        getConfig().addDefault("message.noCommandsPerms", "You don't have permissions to use any commands!");
        getConfig().addDefault("message.help", "Help");
        getConfig().addDefault("message.helpReload", "Reload config.yml");
        getConfig().addDefault("message.helpPerms", "If you set this to true, player need permissions to have a chance to drop diamonds from coal ore");
        getConfig().addDefault("message.helpFirework", "If you set this to true, when player drop diamond from coal, firework will be launched");
        getConfig().addDefault("message.helpPercentage", "Percentage for chance to drop diamond (Default value is 1000)");
        getConfig().addDefault("message.helpFortune", "If you set this to true, player have bigger chance to drop diamonds with Fortune enchantments calculated by vanilla mechanism");
        getConfig().addDefault("message.helpXpDrop", "Amount of XP points earned, when diamond is dropped (Default value is 100)");
        getConfig().addDefault("message.helpWooden", "Chance to drop diamond from coal with wooden pickaxe in percentage (Use values between zero and percentage value!)");
        getConfig().addDefault("message.helpStone", "Chance to drop diamond from coal with stone pickaxe in percentage (Use values between zero and percentage value!)");
        getConfig().addDefault("message.helpIron", "Chance to drop diamond from coal with iron pickaxe in percentage (Use values between zero and percentage value!)");
        getConfig().addDefault("message.helpGolden", "Chance to drop diamond from coal with golden pickaxe in percentage (Use values between zero and percentage value!)");
        getConfig().addDefault("message.helpDiamond", "Chance to drop diamond from coal with diamond pickaxe in percentage (Use values between zero and percentage value!)");
        getConfig().addDefault("message.luckyDrop", "Look on the ground! Take that lucky diamond mined from coal!");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    // Enabling plugin
    @Override
    public void onEnable(){
        loadDefaultConfig();
        loadConfig();
        getServer().getPluginManager().registerEvents(new onBlockBreakListener(), this);
        getLogger().info("CoalDiamonds " + getDescription().getVersion() + " " + onEnable);
    }

    // Disabling plugin
    @Override
    public void onDisable() {
        getLogger().info("CoalDiamonds " + getDescription().getVersion() + " " + onDisable);
    }

    // Commands
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("cdia")){
            if (args.length > 0){
                switch(args[0].toLowerCase()){

                    // Reload command
                    case "reload":
                        if (sender.hasPermission("CoalDiamonds.cmd.reload")){
                            reloadConfig();
                            loadConfig();
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + configReload);
                        }
                        else {
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                        }
                        return true;

                    // Useperms command
                    case "useperms":
                        if (sender.hasPermission("CoalDiamonds.cmd.usePerms")){
                            if (args.length > 1){
                                Boolean usePermsValue = Boolean.valueOf(args[1]);
                                getConfig().set("permissions.usePerms", usePermsValue);
                                saveConfig();
                                usePerms = getConfig().getBoolean("permissions.usePerms");
                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + usingPerms + " " + usePermsValue);
                            }
                            else {
                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + syntaxError);
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                        }
                        return true;

                    // Launchfirework command
                    case "launchfirework":
                        if (sender.hasPermission("CoalDiamonds.cmd.launchFirework")){
                            if (args.length > 1){
                                Boolean launchFireworkValue = Boolean.valueOf(args[1]);
                                getConfig().set("misc.launchFirework", launchFireworkValue);
                                saveConfig();
                                launchFireworkConfig = getConfig().getBoolean("misc.launchFirework");
                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + launchFirework + " " + launchFireworkValue);
                            }
                            else {
                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + syntaxError);
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                        }
                        return true;

                    // Percentage command
                    case "percentage":
                        if (sender.hasPermission("CoalDiamonds.cmd.percentage")){
                            try {
                                if (args.length > 1){
                                    int percentageNumber = Integer.parseInt(args[1]);
                                    if ((configChanceWood > percentageNumber) || (configChanceStone > percentageNumber) || (configChanceIron > percentageNumber) || (configChanceGold > percentageNumber) || (configChanceDia > percentageNumber)) {
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + percentageError);
                                    }
                                    else {
                                        getConfig().set("chance.percentage", percentageNumber);
                                        saveConfig();
                                        percentage = getConfig().getInt("chance.percentage");
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + percentageValue + " " + percentageNumber);
                                    }
                                }
                                else {
                                    sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + syntaxError);
                                }
                                return true;
                            }
                            catch (NumberFormatException e){
                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + numberError);
                                return true;
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                            return true;
                        }

                    // Fortune command
                    case "usefortune":
                        if (sender.hasPermission("CoalDiamonds.cmd.useFortune")){
                            if (args.length > 1){
                                Boolean usePermsValue = Boolean.valueOf(args[1]);
                                getConfig().set("chance.useFortune", usePermsValue);
                                saveConfig();
                                useFortune = getConfig().getBoolean("chance.useFortune");
                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + enableFortune + " " + usePermsValue);
                            }
                            else {
                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + syntaxError);
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                        }
                        return true;

                    // Xpdrop command
                    case "xpdrop":
                        if (sender.hasPermission("CoalDiamonds.cmd.xpDrop")){
                            try {
                                if (args.length > 1){
                                    int xpDropNumber = Integer.parseInt(args[1]);
                                    getConfig().set("chance.xpDrop", xpDropNumber);
                                    saveConfig();
                                    xpDrop = getConfig().getInt("chance.xpDrop");
                                    sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + xpDropValue + " " + xpDropNumber);
                                    return true;
                                }
                                else {
                                    sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + syntaxError);
                                }
                            }
                            catch (NumberFormatException e){
                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + numberError);
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                            return true;
                        }

                    // Chance command
                    case "chance":
                        if (sender.hasPermission("CoalDiamonds.cmd.chance")){
                            try {
                                if (args.length > 2){
                                    int chanceNumber = Integer.parseInt(args[2]);
                                    switch(args[1].toLowerCase()){
                                        case "wood":
                                            if (chanceNumber > percentage) {
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + chanceError);
                                            }
                                            else {
                                                getConfig().set("chance.woodPickaxe", chanceNumber);
                                                saveConfig();
                                                configChanceWood = getConfig().getInt("chance.woodPickaxe");
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + woodenChance + " " + chanceNumber);
                                            }
                                            return true;
                                        case "stone":
                                            if (chanceNumber > percentage) {
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + chanceError);
                                            }
                                            else {
                                                getConfig().set("chance.stonePickaxe", chanceNumber);
                                                saveConfig();
                                                configChanceStone = getConfig().getInt("chance.stonePickaxe");
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + stoneChance + " " + chanceNumber);
                                            }
                                            return true;
                                        case "iron":
                                            if (chanceNumber > percentage) {
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + chanceError);
                                            }
                                            else {
                                                getConfig().set("chance.ironPickaxe", chanceNumber);
                                                saveConfig();
                                                configChanceIron = getConfig().getInt("chance.ironPickaxe");
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + ironChance + " " + chanceNumber);
                                            }
                                            return true;
                                        case "gold":
                                            if (chanceNumber > percentage) {
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + chanceError);
                                            }
                                            else {
                                                getConfig().set("chance.goldPickaxe", chanceNumber);
                                                saveConfig();
                                                configChanceGold = getConfig().getInt("chance.goldPickaxe");
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + goldenChance + " " + chanceNumber);
                                            }
                                            return true;
                                        case "dia":
                                            if (chanceNumber > percentage) {
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + chanceError);
                                            }
                                            else {
                                                getConfig().set("chance.diaPickaxe", chanceNumber);
                                                saveConfig();
                                                configChanceDia = getConfig().getInt("chance.diaPickaxe");
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + diamondChance + " " + chanceNumber);
                                            }
                                            return true;
                                        default:
                                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + badSyntax);
                                            return true;
                                    }
                                }
                                else {
                                    sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + syntaxError);
                                    return true;
                                }
                            }
                            catch (NumberFormatException e){
                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + numberError);
                                return true;
                            }
                        }
                        else {
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                            return true;
                        }

                    // Help if argument is bad
                    default:
                        help(sender);
                        return true;
                }
            }

            // Help if no argument is given
            else {
                help(sender);
                return true;
            }
        }
        return false;
    }

    // Help method
    private void help(CommandSender sender){
        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.WHITE + help + ":");
        if (sender.hasPermission("CoalDiamonds.cmd.reload")){
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "reload" + ChatColor.WHITE + " - " + helpReload);
        }
        if (sender.hasPermission("CoalDiamonds.cmd.usePerms")){
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "useperms" + ChatColor.GOLD + " <true/false>" + ChatColor.WHITE + " - " + helpPerms);
        }
        if (sender.hasPermission("CoalDiamonds.cmd.launchFirework")){
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "launchfirework" + ChatColor.GOLD + " <true/false>" + ChatColor.WHITE + " - " + helpFirework);
        }
        if (sender.hasPermission("CoalDiamonds.cmd.percentage")){
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "percentage" + ChatColor.GOLD + " <value>" + ChatColor.WHITE + " - " + helpPercentage);
        }
        if (sender.hasPermission("CoalDiamonds.cmd.useFortune")){
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "usefortune" + ChatColor.GOLD + " <true/false>" + ChatColor.WHITE + " - " + helpFortune);
        }
        if (sender.hasPermission("CoalDiamonds.cmd.xpDrop")){
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "xpdrop" + ChatColor.GOLD + " <value>" + ChatColor.WHITE + " - " + helpXpDrop);
        }
        if (sender.hasPermission("CoalDiamonds.cmd.chance")){
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "chance" + ChatColor.YELLOW + " wood" + ChatColor.GOLD + " <value>" + ChatColor.WHITE + " - " + helpWooden);
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "chance" + ChatColor.YELLOW + " stone" + ChatColor.GOLD + " <value>" + ChatColor.WHITE + " - " + helpStone);
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "chance" + ChatColor.YELLOW + " iron" + ChatColor.GOLD + " <value>" + ChatColor.WHITE + " - " + helpIron);
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "chance" + ChatColor.YELLOW + " gold" + ChatColor.GOLD + " <value>" + ChatColor.WHITE + " - " + helpGolden);
            sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "chance" + ChatColor.YELLOW + " dia" + ChatColor.GOLD + " <value>" + ChatColor.WHITE + " - " + helpDiamond);
        }
        else {
            sender.sendMessage(ChatColor.RED + noCommandsPerms);
        }
    }

    public class onBlockBreakListener implements Listener {

        // Color declaration
        Color getColor(int i) {
            Color c = null;
            switch (i){
                case 1: c=Color.AQUA; break;
                case 2: c=Color.BLACK; break;
                case 3: c=Color.BLUE; break;
                case 4: c=Color.FUCHSIA; break;
                case 5: c=Color.GRAY; break;
                case 6: c=Color.GREEN; break;
                case 7: c=Color.LIME; break;
                case 8: c=Color.MAROON; break;
                case 9: c=Color.NAVY; break;
                case 10: c=Color.OLIVE; break;
                case 11: c=Color.ORANGE; break;
                case 12: c=Color.PURPLE; break;
                case 13: c=Color.RED; break;
                case 14: c=Color.SILVER; break;
                case 15: c=Color.TEAL; break;
                case 16: c=Color.WHITE; break;
                case 17: c=Color.YELLOW; break;
            }
            return c;
        }

        // BlockBreak event
        @EventHandler
        public void onBlockBreak(BlockBreakEvent event) {
            brokenByPlayer = event.getBlock();
            playerWhoBrokeBlock = event.getPlayer();

            // call coalBreak only when the block is coal ore
            if (brokenByPlayer.getType()==Material.COAL_ORE){

                // check for permission usage as declared in config.yml
                if (usePerms){
                    if (playerWhoBrokeBlock.hasPermission("CoalDiamonds.canMine")){
                        coalBreak(event);
                    }
                }
                else {
                    coalBreak(event);
                }
            }
        }

        // What to do, when coal ore is mined
        void coalBreak(BlockBreakEvent event){
            playerWhoBrokeBlock = event.getPlayer();

            // Setting bonus chance gained by Fortune to zero
            int bonusChance = 0;

            // Random number creation (-1 is to calculate properly, random number create number from 0)
            int randomNumber = new Random().nextInt(percentage - 1);

            // Check for Fortune and if yes add level number of fortune to bonusChance
            if (useFortune) {
                if (playerWhoBrokeBlock.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)){
                    bonusChance = playerWhoBrokeBlock.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                }
            }
            String gm = event.getPlayer().getGameMode().name();
            Material item = event.getPlayer().getInventory().getItemInMainHand().getType();
            boolean silkTouch = event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH);

            // Check if user is not in creative, does not have silk touch on pickaxe and the event is not cancelled
            if (!gm.equals("CREATIVE") && !silkTouch && !event.isCancelled()){

                // Debug - will be removed in full release
                // playerWhoBrokeBlock.sendMessage(percentage + " " + randomNumber + " " + configChanceDia + " " + bonusChance);

                switch(item){
                    case WOODEN_PICKAXE: if (randomNumber < (configChanceWood + bonusChance)){drop();} break;
                    case STONE_PICKAXE: if (randomNumber < (configChanceStone + bonusChance)){drop();} break;
                    case IRON_PICKAXE: if (randomNumber < (configChanceIron + bonusChance)){drop();} break;
                    case GOLDEN_PICKAXE: if (randomNumber < (configChanceGold + bonusChance)){drop();} break;
                    case DIAMOND_PICKAXE: if (randomNumber < (configChanceDia + bonusChance)){drop();} break;
                    default: break;
                }
            }
        }

        // Drops and effects when coalBreak method is called
        void drop(){
            int xpDropExcess = xpDrop % 10;

            // Drop diamond
            brokenByPlayer.getWorld().dropItemNaturally(brokenByPlayer.getLocation(), new ItemStack(Material.DIAMOND, 1));

            // Drop experience
            for (int xp = 10; xp <= xpDrop; xp = xp+10){
                brokenByPlayer.getWorld().spawn(brokenByPlayer.getLocation(), ExperienceOrb.class).setExperience(10);
            }
            if (xpDropExcess != 0){
                brokenByPlayer.getWorld().spawn(brokenByPlayer.getLocation(), ExperienceOrb.class).setExperience(xpDropExcess);
            }

            // Launch firework on drop if true in config.yml
            if (launchFireworkConfig){
                Random r = new Random();
                int r1i = r.nextInt(17) + 1;
                int r2i = r.nextInt(17) + 1;
                Color c1 = getColor(r1i);
                Color c2 = getColor(r2i);
                Firework fw = (Firework) playerWhoBrokeBlock.getWorld().spawnEntity(playerWhoBrokeBlock.getLocation(), EntityType.FIREWORK);
                FireworkMeta fwm = fw.getFireworkMeta();
                FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(Type.STAR).trail(r.nextBoolean()).build();
                fwm.addEffect(effect);
                fwm.setPower(0);
                fw.setFireworkMeta(fwm);
            }
            playerWhoBrokeBlock.sendMessage(ChatColor.AQUA + luckyDrop);
        }
    }
}