package keizaiya.second.armmy;

import keizaiya.second.Potato;
import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.player.Playerdata;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class armmy {

    public static boolean used(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (e.getItem() != null) {
            String name = e.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin,"armmy")
                    , PersistentDataType.STRING);
            if(name != null ){
                if(name.contains("Null") == false) {
                    if (Countrydata.checkpermission(player, "head")) {
                        if (Countrytag.contains(Playerdata.getNowCountry(player)) == false) {
                            if (name.contains("strongarmmy")) {
                                player.getInventory().getItemInMainHand().setAmount(
                                        player.getInventory().getItemInMainHand().getAmount() - 1);
                                for (Player check : Countrydata.getOnlinemember(Playerdata.getNowCountry(player))) {
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 3));
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1));
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 600, 1));
                                    check.sendMessage("§8[§7System§8] §7強行軍を使用しました");
                                }
                            } else if (name.contains("Invasion")) {
                                player.getInventory().getItemInMainHand().setAmount(
                                        player.getInventory().getItemInMainHand().getAmount() - 1);
                                for (Player check : Countrydata.getOnlinemember(Playerdata.getNowCountry(player))) {
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1800, 2));
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1800, 1));
                                    check.sendMessage("§8[§7System§8] §7侵略を使用しました");
                                }
                            } else if (name.contains("Blitzkrieg")) {
                                player.getInventory().getItemInMainHand().setAmount(
                                        player.getInventory().getItemInMainHand().getAmount() - 1);
                                for (Player check : Countrydata.getOnlinemember(Playerdata.getNowCountry(player))) {
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1800, 2));
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1800, 1));
                                    check.sendMessage("§8[§7System§8] §7電撃戦を使用しました");
                                }
                            } else if (name.contains("offensive")) {
                                player.getInventory().getItemInMainHand().setAmount(
                                        player.getInventory().getItemInMainHand().getAmount() - 1);
                                for (Player check : Countrydata.getOnlinemember(Playerdata.getNowCountry(player))) {
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 2));
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1));
                                    check.sendMessage("§8[§7System§8] §7攻勢を使用しました");
                                }
                            } else if (name.contains("Holywar")) {
                                player.getInventory().getItemInMainHand().setAmount(
                                        player.getInventory().getItemInMainHand().getAmount() - 1);
                                for (Player check : Countrydata.getOnlinemember(Playerdata.getNowCountry(player))) {
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6000, 3));
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 3));
                                    check.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 6000, 1));
                                    check.sendMessage("§8[§7System§8] §7聖戦を使用しました");
                                }
                            } else if (name.contains("def")) {
                                if(bouei.contains(Countrydata.getIdeology(Playerdata.getNowCountry(player)))){
                                    player.getInventory().getItemInMainHand().setAmount(
                                            player.getInventory().getItemInMainHand().getAmount() - 1);
                                    for (Player check : Countrydata.getOnlinemember(Playerdata.getNowCountry(player))) {
                                        check.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 72000, 2));
                                        check.sendMessage("§8[§7System§8] §7守勢を使用しました");
                                    }
                                }else{
                                    player.sendMessage("§8[§7System§8] §7平和,中立,非干渉主義でなければ使用できません");
                                    return false;
                                }
                            } else if (name.contains("PeaceofWar")) {
                                if(peace.contains(Countrydata.getIdeology(Playerdata.getNowCountry(player)))) {
                                    player.getInventory().getItemInMainHand().setAmount(
                                            player.getInventory().getItemInMainHand().getAmount() - 1);
                                    for (Player check : Countrydata.getOnlinemember(Playerdata.getNowCountry(player))) {
                                        check.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 12000, 2));
                                        check.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 12000, 2));
                                        check.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 12000, 2));
                                        check.sendMessage("§8[§7System§8] §7平和のための戦争を使用しました");
                                    }
                                }else{
                                    player.sendMessage("§8[§7System§8] §7平和主義でなければ使用できません");
                                    return false;
                                }
                            } else if (name.contains("defense")) {
                                if(bouei.contains(Countrydata.getIdeology(Playerdata.getNowCountry(player)))) {
                                    player.getInventory().getItemInMainHand().setAmount(
                                            player.getInventory().getItemInMainHand().getAmount() - 1);
                                    for (Player check : Countrydata.getOnlinemember(Playerdata.getNowCountry(player))) {
                                        check.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 6000, 1));
                                        check.sendMessage("§8[§7System§8] §7防御を使用しました");
                                    }
                                }else{
                                    player.sendMessage("§8[§7System§8] §7平和,中立,非干渉主義でなければ使用できません");
                                    return false;
                                }
                            }
                            setCountry(player);
                        } else{
                            player.sendMessage("§8[§7System§8] §7クールダウン中です");
                        }
                    } else {
                        player.sendMessage("§8[§7System§8] §7あなたは使用できません");
                    }
                }

            }

        }
        return true;
    }

    public static ItemStack getarmmycard(String type){
        ItemStack stack = new ItemStack(Material.PAPER);
        ItemMeta meta =stack.getItemMeta();
        meta.setDisplayName("§c§l戦術カード");
        String data = "Null";
        List<String> lore = new ArrayList<>();
        if(type.contains("強行軍")) {
            data = "strongarmmy";
            lore.add("§7Name: 強行軍");
            lore.add("§7Effect:");
            lore.add("§8>> §7Strength Ⅲ 30s");
            lore.add("§8>> §7SResistance Ⅰ 30s");
            lore.add("§8>> §7Hunger Ⅰ 30s");
        }else if(type.contains("侵略")) {
            data = "Invasion";
            lore.add("§7Name: 侵略");
            lore.add("§7Effect:");
            lore.add("§8>> §7Strength Ⅱ 90s");
            lore.add("§8>> §7SRegeneration Ⅰ 90s");
        }else if(type.contains("電撃戦")) {
            data = "Blitzkrieg";
            lore.add("§7Name: 電撃戦");
            lore.add("§7Effect:");
            lore.add("§8>> §7Strength Ⅱ 90s");
            lore.add("§8>> §7Speed Ⅰ 90s");
        }else if(type.contains("攻勢")) {
            data = "offensive";
            lore.add("§7Name: 攻勢");
            lore.add("§7Effect:");
            lore.add("§8>> §7Strength Ⅱ 30s");
            lore.add("§8>> §7SRegeneration Ⅰ 30s");
        }else if(type.contains("聖戦")) {
            data = "Holywar";
            lore.add("§7Name: 聖戦");
            lore.add("§7Effect:");
            lore.add("§8>> §7Strength Ⅲ 300s");
            lore.add("§8>> §7Speed Ⅲ 300s");
            lore.add("§8>> §7SResistance Ⅰ 300s");
        }else if(type.contains("防御")) {
            data = "defense";
            lore.add("§7Name: 防御");
            lore.add("§7Effect:");
            lore.add("§8>> §7SResistance Ⅰ 300s");
        }else if(type.contains("守勢")) {
            data = "def";
            lore.add("§7Name: 守勢");
            lore.add("§7Effect:");
            lore.add("§8>> §7SResistance Ⅱ 60min");
        }else if(type.contains("平和を守るための戦争")) {
            data = "PeaceofWar";
            lore.add("§7Name: 平和を守るための戦争");
            lore.add("§7Effect:");
            lore.add("§8>> §7Strength Ⅱ 600s");
            lore.add("§8>> §7Speed Ⅱ 600s");
            lore.add("§8>> §7SResistance Ⅱ 600s");
        }else{
            lore.add("エラー^^");
        }
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"armmy") , PersistentDataType.STRING, data);
        stack.setItemMeta(meta);
        return stack;
    }
    public static List<String> Countrytag = new ArrayList<>();

    public static void setCountry(Player player){
        Countrytag.add(Playerdata.getNowCountry(player));
        new BukkitRunnable(){
            Integer timer = Potato.config.getInt("cardtimer",12000);
            String tag = Playerdata.getNowCountry(player);
            @Override
            public void run(){
                timer--;
                if(timer <= 0){
                    Countrytag.remove(tag);
                    cancel();
                }
            }
        }.runTaskTimer(Potato.plugin,0,1);
    }

    public static List<String> bouei = new ArrayList<>(Arrays.asList(
            "Neul","Neul2","Neul3","Noin","Noin2","Noin3","peace","peace2","peace3"));

    public static List<String> peace = new ArrayList<>(Arrays.asList(
            "peace","peace2","peace3"));
}
