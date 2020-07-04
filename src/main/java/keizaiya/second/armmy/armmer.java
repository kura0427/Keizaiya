package keizaiya.second.armmy;

import keizaiya.second.Potato;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class armmer {

    public static void damege(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            Float famege = Float.valueOf(0);
            Float boot = Float.valueOf(0);
            Float Leggings = Float.valueOf(0);
            Float Chestplate = Float.valueOf(0);
            Float Helmet = Float.valueOf(0);
            Player player = (Player) e.getEntity();
            if(player.getInventory().getBoots() != null) {
                boot = player.getInventory().getBoots().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "armm"), PersistentDataType.FLOAT);
                if(boot == null){ boot = getNomaldatege(player.getInventory().getBoots().getType()); }
            }
            if(player.getInventory().getLeggings() != null) {
                Leggings = player.getInventory().getLeggings().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "armm"), PersistentDataType.FLOAT);
                if(Leggings == null){ Leggings = getNomaldatege(player.getInventory().getLeggings().getType());}
            }
            if(player.getInventory().getChestplate() != null) {
                Chestplate = player.getInventory().getChestplate().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "armm"), PersistentDataType.FLOAT);
                if(Chestplate == null){ Chestplate = getNomaldatege(player.getInventory().getChestplate().getType());}
            }
            if(player.getInventory().getHelmet() != null) {
                Helmet = player.getInventory().getHelmet().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "armm"), PersistentDataType.FLOAT);
                if(Helmet == null){ Helmet = getNomaldatege(player.getInventory().getHelmet().getType());}
            }
            famege = boot + Leggings + Chestplate + Helmet;
            if(famege < 1){
                famege = Float.valueOf(1);
            }
            Double damage = e.getDamage();
            damage = damage * famege;
            e.setDamage(damage);
        }
    }

    public static void click(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (e.getItem() != null) {
            Float name = e.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "armm"), PersistentDataType.FLOAT);
            Random rnd = new Random();
            List<String> lore = new ArrayList<>();
            if(Diamondarmmor.contains(e.getMaterial())) {
                if (name == null) {
                    ItemMeta meta = e.getItem().getItemMeta();
                    Integer before = rnd.nextInt(51);
                    Float data = Float.valueOf((before + 50));
                    data = data / 100;
                    meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"armm") , PersistentDataType.FLOAT, data);
                    lore.add("§7被ダメージ倍率: §e" + String.valueOf(data));
                    meta.setLore(lore);
                    e.getItem().setItemMeta(meta);
                }
            }else if(GOLDENarmmor.contains(e.getMaterial())){
                if (name == null) {
                    ItemMeta meta = e.getItem().getItemMeta();
                    Integer before = rnd.nextInt(21);
                    Float data = Float.valueOf((before + 35));
                    data = data / 100;
                    meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"armm") , PersistentDataType.FLOAT, data);
                    lore.add("§7被ダメージ倍率: §e" + String.valueOf(data));
                    meta.setLore(lore);
                    e.getItem().setItemMeta(meta);
                }
            }else if(IRONarmmor.contains(e.getMaterial())){
                if (name == null) {
                    ItemMeta meta = e.getItem().getItemMeta();
                    Integer before = rnd.nextInt(31);
                    Float data = Float.valueOf((before + 45));
                    data = data / 100;
                    meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"armm") , PersistentDataType.FLOAT, data);
                    lore.add("§7被ダメージ倍率: §e" + String.valueOf(data));
                    meta.setLore(lore);
                    e.getItem().setItemMeta(meta);
                }
            }else if(CHAINMAILarmmor.contains(e.getMaterial())){
                if (name == null) {
                    ItemMeta meta = e.getItem().getItemMeta();
                    Integer before = rnd.nextInt(21);
                    Float data = Float.valueOf((before + 40));
                    data = data / 100;
                    meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"armm") , PersistentDataType.FLOAT, data);
                    lore.add("§7被ダメージ倍率: §e" + String.valueOf(data));
                    meta.setLore(lore);
                    e.getItem().setItemMeta(meta);
                }
            }else if(LEATHERarmmor.contains(e.getMaterial())){
                if (name == null) {
                    ItemMeta meta = e.getItem().getItemMeta();
                    Integer before = rnd.nextInt(6);
                    Float data = Float.valueOf((before + 30));
                    data = data / 100;
                    meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"armm") , PersistentDataType.FLOAT, data);
                    lore.add("§7被ダメージ倍率: §e" + String.valueOf(data));
                    meta.setLore(lore);
                    e.getItem().setItemMeta(meta);
                }
            }
        }
    }

    public static Float getNomaldatege(Material metarial){
        Float data = Float.valueOf(1);
        if(Diamondarmmor.contains(metarial)){
            data = Float.valueOf(1);
        }
        if(GOLDENarmmor.contains(metarial)){
            data = 0.55F;
        }
        if(IRONarmmor.contains(metarial)){
            data = 0.75F;
        }
        if(CHAINMAILarmmor.contains(metarial)){
            data = 0.60F;
        }
        if(LEATHERarmmor.contains(metarial)){
            data = 0.35F;
        }
        return data;
    }

    public static List<Material> Diamondarmmor = new ArrayList<>(Arrays.asList(
            Material.DIAMOND_BOOTS,Material.DIAMOND_LEGGINGS,Material.DIAMOND_CHESTPLATE,Material.DIAMOND_HELMET
    ));
    public static List<Material> GOLDENarmmor = new ArrayList<>(Arrays.asList(
            Material.GOLDEN_BOOTS,Material.GOLDEN_LEGGINGS,Material.GOLDEN_CHESTPLATE,Material.GOLDEN_HELMET
    ));
    public static List<Material> IRONarmmor = new ArrayList<>(Arrays.asList(
            Material.IRON_BOOTS,Material.IRON_LEGGINGS,Material.IRON_CHESTPLATE,Material.IRON_HELMET,Material.TURTLE_HELMET
    ));
    public static List<Material> CHAINMAILarmmor = new ArrayList<>(Arrays.asList(
            Material.CHAINMAIL_BOOTS,Material.CHAINMAIL_LEGGINGS,Material.CHAINMAIL_CHESTPLATE,Material.CHAINMAIL_HELMET
    ));
    public static List<Material> LEATHERarmmor = new ArrayList<>(Arrays.asList(
            Material.LEATHER_BOOTS,Material.LEATHER_LEGGINGS,Material.LEATHER_CHESTPLATE,Material.LEATHER_HELMET
    ));
}

