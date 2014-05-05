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
 * @author KuCZik
 * Using Bukkit API build 1933
 */

public class CoalDiamonds extends JavaPlugin {
    
    // Adding default values in config when is not created
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

    // Getting strings from config.yml that are used in plugin
    public String noPerms = getConfig().getString("message.noPerms");
    public String configReload = getConfig().getString("message.configReload");
    public String usingPerms = getConfig().getString("message.usingPerms");
    public String launchFirework = getConfig().getString("message.launchFirework");
    public String percentageError = getConfig().getString("message.percentageError");
    public String numberError = getConfig().getString("message.numberError");
    public String syntaxError = getConfig().getString("message.syntaxError");
    public String badSyntax = getConfig().getString("message.badSyntax");
    public String percentageValue = getConfig().getString("message.percentageValue");
    public String fortuneValue = getConfig().getString("message.fortuneValue");
    public String fortuneError = getConfig().getString("message.fortuneError");
    public String xpDropValue = getConfig().getString("message.xpDropValue");
    public String chanceError = getConfig().getString("message.chanceError");
    public String woodenChance = getConfig().getString("message.woodenChance");
    public String stoneChance = getConfig().getString("message.stoneChance");
    public String ironChance = getConfig().getString("message.ironChance");
    public String goldenChance = getConfig().getString("message.goldenChance");
    public String diamondChance = getConfig().getString("message.diamondChance");
    public String noCommandsPerms = getConfig().getString("message.noCommandsPerms");
    public String help = getConfig().getString("message.help");
    public String helpReload = getConfig().getString("message.helpReload");
    public String helpPerms = getConfig().getString("message.helpPerms");
    public String helpFirework = getConfig().getString("message.helpFirework");
    public String helpPercentage = getConfig().getString("message.helpPercentage");
    public String helpFortune = getConfig().getString("message.helpFortune");
    public String helpXpDrop = getConfig().getString("message.helpXpDrop");
    public String helpWooden = getConfig().getString("message.helpWooden");
    public String helpStone = getConfig().getString("message.helpStone");
    public String helpIron = getConfig().getString("message.helpIron");
    public String helpGolden = getConfig().getString("message.helpGolden");
    public String helpDiamond = getConfig().getString("message.helpDiamond");
    public String luckyDrop = getConfig().getString("message.luckyDrop");
    public String onEnable = getConfig().getString("message.onEnable");
    public String onDisable = getConfig().getString("message.onDisable");
    
    // Declaration of variables
    public CommandSender sender;
    public Block brokenByPlayer;
    public Player playerWhoBrokeBlock;
    public int item;
    public String gm;
    public String enchant;
    public BlockBreakEvent event;
    public int id;
    
    // Getting variables from config.yml
    public int configChanceWood = getConfig().getInt("chance.woodPickaxe");
    public int configChanceStone = getConfig().getInt("chance.stonePickaxe");
    public int configChanceIron = getConfig().getInt("chance.ironPickaxe");
    public int configChanceGold = getConfig().getInt("chance.goldPickaxe");
    public int configChanceDia = getConfig().getInt("chance.diaPickaxe");
    public int percentage = getConfig().getInt("chance.percentage");
    public int fortune = getConfig().getInt("chance.diaPickaxe");
    public boolean usePerms = getConfig().getBoolean("permissions.usePerms");
    public int xpDrop = getConfig().getInt("chance.xpDrop");
    public boolean launchFireworkConfig = getConfig().getBoolean("misc.launchFirework");

    // Enabling plugin
    @Override
    public void onEnable(){
        loadConfig();
        getServer().getPluginManager().registerEvents(new onBlockBreakListener(), this);
        getLogger().info("CoalDiamonds " + getDescription().getVersion() + " " + onEnable);
        saveConfig();
    }
    
    // Disabling plugin
    @Override
    public void onDisable() {
        getLogger().info("CoalDiamonds " + getDescription().getVersion() + " " + onDisable);
        saveConfig();
    }
    
    // Commands
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("cdia")){
            if (args.length > 0){
                switch(args[1].toLowerCase()){
                    
                    // Reload command
                    case "reload":
                        if (sender.hasPermission("CoalDiamonds.cmd.reload")){
                            reloadConfig();
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + configReload);
                            return true;
                        }
                        else {
                            sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.RED + noPerms);
                            return true;
                        }
                    
                    // Useperms command
                    case "useperms":
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
                    
                    // Launchfirework command
                    case "launchfirework":
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
                    
                    // Percentage command
                    case "percentage":
                        if (sender.hasPermission("CoalDiamonds.cmd.percentage")){
                            try {
                                if (args.length > 1){
                                    int percentageNumber = Integer.parseInt(args[1]);
                                    if ((configChanceWood > percentageNumber) || (configChanceStone > percentageNumber) || (configChanceIron > percentageNumber) || (configChanceGold > percentageNumber) || (configChanceDia > percentageNumber)) {
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
                    
                    // Fortune command
                    case "fortune":
                        if (sender.hasPermission("CoalDiamonds.cmd.fortune")){
                            try {
                                if (args.length > 1){
                                    int fortuneNumber = Integer.parseInt(args[1]);
                                    if (fortuneNumber > percentage) {
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
                    
                    // Xpdrop command
                    case "xpdrop":
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
                                                return true;
                                            }
                                            else {
                                                getConfig().set("chance.woodPickaxe", chanceNumber);
                                                saveConfig();
                                                reloadConfig();
                                                sender.sendMessage(ChatColor.AQUA + "[CoalDiamonds] " + ChatColor.GREEN + woodenChance + " " + chanceNumber);
                                                return true;
                                            }
                                        case "stone":
                                            if (chanceNumber > percentage) {
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
                                        case "iron":
                                            if (chanceNumber > percentage) {
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
                                        case "gold":
                                            if (chanceNumber > percentage) {
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
                                        case "dia":
                                            if (chanceNumber > percentage) {
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
                                        default:
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
                    
                    // Help if argument is bad
                    default:
                        help();
                        return true;
                }
            }
            
            // Help if no argument is given
            else if (args.length==0){
                help();
                return true;
            }
        }
        return false;
    }
    
    // Help method
    public void help(){
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
    }
    
    public class onBlockBreakListener implements Listener {
        
        // Color declaration
        public Color getColor(int i) {
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
        
        // What to do, when coal ore is mined
        public void coalBreak(){
            if (!gm.equals("CREATIVE") && !enchant.contains("SILK_TOUCH") && event.isCancelled()==false){
                switch(item){
                    case 270: if (new Random().nextInt(percentage) < configChanceWood){drop();} break;
                    case 274: if (new Random().nextInt(percentage) < configChanceStone){drop();} break;
                    case 257: if (new Random().nextInt(percentage) < configChanceIron){drop();} break;
                    case 285: if (new Random().nextInt(percentage) < configChanceGold){drop();} break;
                    case 278: if (new Random().nextInt(percentage) < configChanceDia){drop();} break;
                    default: break;
                }
            }
        }
        
        // Drops and effects when coalBreak method is called
        public void drop(){
            int xpDropExcess = xpDrop % 10;
            brokenByPlayer.getWorld().dropItemNaturally(brokenByPlayer.getLocation(), new ItemStack(Material.DIAMOND, 1));
            for (int xp = 10; xp <= xpDrop; xp = xp+10){
                brokenByPlayer.getWorld().spawn(brokenByPlayer.getLocation(), ExperienceOrb.class).setExperience(10);
            }
            if (xpDropExcess != 0){
                brokenByPlayer.getWorld().spawn(brokenByPlayer.getLocation(), ExperienceOrb.class).setExperience(xpDropExcess);
            }
            if (launchFireworkConfig==true){
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
        
        // BlockBreak event
        @EventHandler
        public void onBlockBreak(BlockBreakEvent event) {
            brokenByPlayer = event.getBlock();
            playerWhoBrokeBlock = event.getPlayer();
            item = event.getPlayer().getItemInHand().getTypeId();
            id = brokenByPlayer.getTypeId();
            gm = event.getPlayer().getGameMode().name();
            enchant = event.getPlayer().getItemInHand().getEnchantments().toString();
            if(enchant.contains("FORTUNE")){
                switch(item){
                    case 270: configChanceWood = configChanceWood + fortune; break;
                    case 274: configChanceStone = configChanceStone + fortune; break;
                    case 257: configChanceIron = configChanceIron + fortune; break;
                    case 285: configChanceGold = configChanceGold + fortune; break;
                    case 278: configChanceDia = configChanceDia + fortune; break;
                    default: break;
                }
            }
            if (id==16){
                if (usePerms==true){
                    if (playerWhoBrokeBlock.hasPermission("CoalDiamonds.canMine")){
                        coalBreak();
                    }
                }
                else if (usePerms==false){
                    coalBreak();
                }
            }
        }
    }
}