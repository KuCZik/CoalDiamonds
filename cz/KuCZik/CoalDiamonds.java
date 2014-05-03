package cz.KuCZik;
     
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
 *
 * @author KuCZik
 */

public class CoalDiamonds extends JavaPlugin {
    
    public void loadConfig(){
        getConfig().addDefault("permissions.usePerms", false);
        getConfig().addDefault("chance.percentage", 100);
        getConfig().addDefault("chance.fortune", 10);
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
        getConfig().addDefault("message.fortuneValue", "Fortune chance value set to");
        getConfig().addDefault("message.fortuneError", "You can't set fortune chance number higher than percentage number!");
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
        getConfig().addDefault("message.helpPercentage", "Percentage for chance to drop diamond (Default value is 100)");
        getConfig().addDefault("message.helpFortune", "Ammount of points added to chance when player have Fortune enchantment on pickaxe (Default value is 10)");
        getConfig().addDefault("message.helpXpDrop", "Ammount of XP points earned, when diamond is dropped (Default value is 100)");
        getConfig().addDefault("message.helpWooden", "Chance to drop diamond from coal with wooden pickaxe in percentage (Use values between zero and percentage value!)");
        getConfig().addDefault("message.helpStone", "Chance to drop diamond from coal with stone pickaxe in percentage (Use values between zero and percentage value!)");
        getConfig().addDefault("message.helpIron", "Chance to drop diamond from coal with iron pickaxe in percentage (Use values between zero and percentage value!)");
        getConfig().addDefault("message.helpGolden", "Chance to drop diamond from coal with golden pickaxe in percentage (Use values between zero and percentage value!)");
        getConfig().addDefault("message.helpDiamond", "Chance to drop diamond from coal with diamond pickaxe in percentage (Use values between zero and percentage value!)");
        getConfig().addDefault("message.luckyDrop", "Look on the ground! Take that lucky diamond mined from coal!");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    
    @Override
    public void onEnable(){
        loadConfig();
        String onEnable = getConfig().getString("message.onEnable");
        getServer().getPluginManager().registerEvents(new onBlockBreakListener(), this);
        getLogger().info("CoalDiamonds " + getDescription().getVersion() + " " + onEnable);
        saveConfig();
    }
    
    @Override
    public void onDisable() {
        String onDisable = getConfig().getString("message.onDisable");
        getLogger().info("CoalDiamonds " + getDescription().getVersion() + " " + onDisable);
        saveConfig();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        String noPerms = getConfig().getString("message.noPerms");
        String configReload = getConfig().getString("message.configReload");
        String usingPerms = getConfig().getString("message.usingPerms");
        String launchFirework = getConfig().getString("message.launchFirework");
        String percentageError = getConfig().getString("message.percentageError");
        String numberError = getConfig().getString("message.numberError");
        String syntaxError = getConfig().getString("message.syntaxError");
        String badSyntax = getConfig().getString("message.badSyntax");
        String percentageValue = getConfig().getString("message.percentageValue");
        String fortuneValue = getConfig().getString("message.fortuneValue");
        String fortuneError = getConfig().getString("message.fortuneError");
        String xpDropValue = getConfig().getString("message.xpDropValue");
        String chanceError = getConfig().getString("message.chanceError");
        String woodenChance = getConfig().getString("message.woodenChance");
        String stoneChance = getConfig().getString("message.stoneChance");
        String ironChance = getConfig().getString("message.ironChance");
        String goldenChance = getConfig().getString("message.goldenChance");
        String diamondChance = getConfig().getString("message.diamondChance");
        String noCommandsPerms = getConfig().getString("message.noCommandsPerms");
        String help = getConfig().getString("message.help");
        String helpReload = getConfig().getString("message.helpReload");
        String helpPerms = getConfig().getString("message.helpPerms");
        String helpFirework = getConfig().getString("message.helpFirework");
        String helpPercentage = getConfig().getString("message.helpPercentage");
        String helpFortune = getConfig().getString("message.helpFortune");
        String helpXpDrop = getConfig().getString("message.helpXpDrop");
        String helpWooden = getConfig().getString("message.helpWooden");
        String helpStone = getConfig().getString("message.helpStone");
        String helpIron = getConfig().getString("message.helpIron");
        String helpGolden = getConfig().getString("message.helpGolden");
        String helpDiamond = getConfig().getString("message.helpDiamond");
        if (cmd.getName().equalsIgnoreCase("cdia")){
            if (args.length > 0){
                if (args[0].equalsIgnoreCase("reload")){
                    if (sender.hasPermission("CoalDiamonds.cmd.reload")){
                        reloadConfig();
                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + configReload);
                        return true;
                    }
                    else {
                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                        return true;
                    }
                }
                else if (args[0].equalsIgnoreCase("useperms")){
                    if (sender.hasPermission("CoalDiamonds.cmd.usePerms")){
                        if (args.length > 1){
                            Boolean usePermsValue = Boolean.valueOf(args[1]);
                            getConfig().set("permissions.usePerms", usePermsValue);
                            saveConfig();
                            reloadConfig();
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + usingPerms + " " + usePermsValue);
                            return true;
                        }
                        else {
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + syntaxError);
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                        return true;
                    }
                }
                else if (args[0].equalsIgnoreCase("launchfirework")){
                    if (sender.hasPermission("CoalDiamonds.cmd.launchFirework")){
                        if (args.length > 1){
                            Boolean launchFireworkValue = Boolean.valueOf(args[1]);
                            getConfig().set("misc.launchFirework", launchFireworkValue);
                            saveConfig();
                            reloadConfig();
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + launchFirework + " " + launchFireworkValue);
                            return true;
                        }
                        else {
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + syntaxError);
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                        return true;
                    }
                }
                else if (args[0].equalsIgnoreCase("percentage")){
                    if (sender.hasPermission("CoalDiamonds.cmd.percentage")){
                        try {
                            if (args.length > 1){
                                int percentageNumber = Integer.parseInt(args[1]);
                                int woodNumber = getConfig().getInt("chance.woodPickaxe");
                                int stoneNumber = getConfig().getInt("chance.stonePickaxe");
                                int ironNumber = getConfig().getInt("chance.ironPickaxe");
                                int goldNumber = getConfig().getInt("chance.goldPickaxe");
                                int diaNumber = getConfig().getInt("chance.diaPickaxe");
                                if ((woodNumber > percentageNumber) || (stoneNumber > percentageNumber) || (ironNumber > percentageNumber) || (goldNumber > percentageNumber) || (diaNumber > percentageNumber)) {
                                    sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + percentageError);
                                    return true;
                                }
                                else {
                                    getConfig().set("chance.percentage", percentageNumber);
                                    saveConfig();
                                    reloadConfig();
                                    sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + percentageValue + " " + percentageNumber);
                                    return true;
                                }
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
                }
                else if (args[0].equalsIgnoreCase("fortune")){
                    if (sender.hasPermission("CoalDiamonds.cmd.fortune")){
                        try {
                            if (args.length > 1){
                                int fortuneNumber = Integer.parseInt(args[1]);
                                int percentageNumber = getConfig().getInt("chance.percentage");
                                if (fortuneNumber > percentageNumber) {
                                    sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + fortuneError);
                                    return true;
                                }
                                else {
                                    getConfig().set("chance.fortune", fortuneNumber);
                                    saveConfig();
                                    reloadConfig();
                                    sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + fortuneValue + " " + fortuneNumber);
                                    return true;
                                }
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
                }
                else if (args[0].equalsIgnoreCase("xpdrop")){
                    if (sender.hasPermission("CoalDiamonds.cmd.xpDrop")){
                        try {
                            if (args.length > 1){
                                int xpDropNumber = Integer.parseInt(args[1]);
                                getConfig().set("chance.xpDrop", xpDropNumber);
                                saveConfig();
                                reloadConfig();
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
                }
                else if (args[0].equalsIgnoreCase("chance")){
                    if (sender.hasPermission("CoalDiamonds.cmd.chance")){
                        try {
                            if (args.length > 2){
                                int chanceNumber = Integer.parseInt(args[2]);
                                int percentageNumber = getConfig().getInt("chance.percentage");
                                if (args[1].equalsIgnoreCase("wood")){
                                    if (chanceNumber > percentageNumber) {
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + chanceError);
                                        return true;
                                    }
                                    else {
                                        getConfig().set("chance.woodPickaxe", chanceNumber);
                                        saveConfig();
                                        reloadConfig();
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + woodenChance + " " + chanceNumber);
                                        return true;
                                    }
                                }
                                else if (args[1].equalsIgnoreCase("stone")){
                                    if (chanceNumber > percentageNumber) {
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + chanceError);
                                        return true;
                                    }
                                    else {
                                        getConfig().set("chance.stonePickaxe", chanceNumber);
                                        saveConfig();
                                        reloadConfig();
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + stoneChance + " " + chanceNumber);
                                        return true;
                                    }
                                }
                                else if (args[1].equalsIgnoreCase("iron")){
                                    if (chanceNumber > percentageNumber) {
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + chanceError);
                                        return true;
                                    }
                                    else {
                                        getConfig().set("chance.ironPickaxe", chanceNumber);
                                        saveConfig();
                                        reloadConfig();
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + ironChance + " " + chanceNumber);
                                        return true;
                                    }
                                }
                                else if (args[1].equalsIgnoreCase("gold")){
                                    if (chanceNumber > percentageNumber) {
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + chanceError);
                                        return true;
                                    }
                                    else {
                                        getConfig().set("chance.goldPickaxe", chanceNumber);
                                        saveConfig();
                                        reloadConfig();
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + goldenChance + " " + chanceNumber);
                                        return true;
                                    }
                                }
                                else if (args[1].equalsIgnoreCase("dia")){
                                    if (chanceNumber > percentageNumber) {
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + chanceError);
                                        return true;
                                    }
                                    else {
                                        getConfig().set("chance.diaPickaxe", chanceNumber);
                                        saveConfig();
                                        reloadConfig();
                                        sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + diamondChance + " " + chanceNumber);
                                        return true;
                                    }
                                }
                                else {
                                    sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + badSyntax);
                                    return true;
                                }
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
                }
                else {
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
                    if (sender.hasPermission("CoalDiamonds.cmd.fortune")){
                        sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "fortune" + ChatColor.GOLD + " <value>" + ChatColor.WHITE + " - " + helpFortune);
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
                    return true;
                }
            }
            else if (args.length==0){
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
                if (sender.hasPermission("CoalDiamonds.cmd.fortune")){
                    sender.sendMessage(ChatColor.RED + "/cdia " + ChatColor.GREEN + "fortune" + ChatColor.GOLD + " <value>" + ChatColor.WHITE + " - " + helpFortune);
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
                return true;
            }
        }
        return false;
    }
    
    public class onBlockBreakListener implements Listener {
        public Block brokenByPlayer;
        public Player playerWhoBrokeBlock;
        
        public Color getColor(int i) {
            Color c = null;
            if(i==1){c=Color.AQUA;}
            if(i==2){c=Color.BLACK;}
            if(i==3){c=Color.BLUE;}
            if(i==4){c=Color.FUCHSIA;}
            if(i==5){c=Color.GRAY;}
            if(i==6){c=Color.GREEN;}
            if(i==7){c=Color.LIME;}
            if(i==8){c=Color.MAROON;}
            if(i==9){c=Color.NAVY;}
            if(i==10){c=Color.OLIVE;}
            if(i==11){c=Color.ORANGE;}
            if(i==12){c=Color.PURPLE;}
            if(i==13){c=Color.RED;}
            if(i==14){c=Color.SILVER;}
            if(i==15){c=Color.TEAL;}
            if(i==16){c=Color.WHITE;}
            if(i==17){c=Color.YELLOW;}
            return c;
        }

        public void drop(){
            String luckyDrop = getConfig().getString("message.luckyDrop");
            int xpDrop = getConfig().getInt("chance.xpDrop");
            int xpDropExcess = xpDrop % 10;
            boolean launchFirework = getConfig().getBoolean("misc.launchFirework");
            brokenByPlayer.getWorld().dropItemNaturally(brokenByPlayer.getLocation(), new ItemStack(Material.DIAMOND, 1));
            for (int xp = 10; xp <= xpDrop; xp = xp+10){
                brokenByPlayer.getWorld().spawn(brokenByPlayer.getLocation(), ExperienceOrb.class).setExperience(10);
            }
            if (xpDropExcess != 0){
                brokenByPlayer.getWorld().spawn(brokenByPlayer.getLocation(), ExperienceOrb.class).setExperience(xpDropExcess);
            }
            if (launchFirework==true){
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
        
        @EventHandler
        public void onBlockBreak(BlockBreakEvent event) {
            brokenByPlayer = event.getBlock();
            playerWhoBrokeBlock = event.getPlayer();
            int item = event.getPlayer().getItemInHand().getTypeId();
            int id = brokenByPlayer.getTypeId();
            int percentage = getConfig().getInt("chance.percentage");
            int fortune = getConfig().getInt("chance.diaPickaxe");
            int configChanceWood = getConfig().getInt("chance.woodPickaxe");
            int configChanceCobble = getConfig().getInt("chance.stonePickaxe");
            int configChanceIron = getConfig().getInt("chance.ironPickaxe");
            int configChanceGold = getConfig().getInt("chance.goldPickaxe");
            int configChanceDia = getConfig().getInt("chance.diaPickaxe");
            String gm = event.getPlayer().getGameMode().name().toString();
            String enchant = event.getPlayer().getItemInHand().getEnchantments().toString();
            boolean usePerms = getConfig().getBoolean("permissions.usePerms");
            if(enchant.contains("FORTUNE")){
                switch(item){
                    case 270:
                        configChanceWood = configChanceWood + fortune;
                        break;
                    case 274:
                        configChanceCobble = configChanceCobble + fortune;
                        break;
                    case 257:
                        configChanceIron = configChanceIron + fortune;
                        break;
                    case 285:
                        configChanceGold = configChanceGold + fortune;
                        break;
                    case 278:
                        configChanceDia = configChanceDia + fortune;
                        break;
                }
            }
            if (id==16){
                if (usePerms==true){
                    if (playerWhoBrokeBlock.hasPermission("CoalDiamonds.canMine")){
                        if (!gm.equals("CREATIVE") && !enchant.contains("SILK_TOUCH") && event.isCancelled()==false){
                            switch(item){
                                case 270:
                                    if (new Random().nextInt(percentage) < configChanceWood){drop();}
                                    break;
                                case 274:
                                    if (new Random().nextInt(percentage) < configChanceCobble){drop();}
                                    break;
                                case 257:
                                    if (new Random().nextInt(percentage) < configChanceIron){drop();}
                                    break;
                                case 285:
                                    if (new Random().nextInt(percentage) < configChanceGold){drop();}
                                    break;
                                case 278:
                                    if (new Random().nextInt(percentage) < configChanceDia){drop();}
                                    break;
                            }
                        }
                    }
                }
                else if (usePerms==false){
                    if (!gm.equals("CREATIVE") && !enchant.contains("SILK_TOUCH") && event.isCancelled()==false){
                        switch(item){
                            case 270:
                                if (new Random().nextInt(percentage) < configChanceWood){drop();}
                                break;
                            case 274:
                                if (new Random().nextInt(percentage) < configChanceCobble){drop();}
                                break;
                            case 257:
                                if (new Random().nextInt(percentage) < configChanceIron){drop();}
                                break;
                            case 285:
                                if (new Random().nextInt(percentage) < configChanceGold){drop();}
                                break;
                            case 278:
                                if (new Random().nextInt(percentage) < configChanceDia){drop();}
                                break;
                        }
                    }
                }
            }
        }
    }
}