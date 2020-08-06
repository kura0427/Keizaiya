package keizaiya.second.item;

import keizaiya.second.Potato;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class superpixcel {
    public static ItemStack getitem(){
        ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§6§lSuper Pickaxe");
        List<String> lore = new ArrayList<>();
        lore.add("§7このツールで採掘した場合3×3×3の範囲を採掘します。");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"tool") , PersistentDataType.STRING,"Super_Pickaxe");
        stack.setItemMeta(meta);
        return stack;
    }

    public static void breakblocktoSP(BlockBreakEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand() != null) {
            ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
            if (tool.getItemMeta() != null) {
                String name = tool.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "tool")
                        , PersistentDataType.STRING);
                if(name != null) {
                    if (name.contains("Super_Pickaxe")) {
                        Location o = event.getBlock().getLocation();
                        List<ItemStack> drops = new ArrayList<>();
                        event.setDropItems(true);
                        o.setX(o.getBlockX() - 1);
                        o.setY(o.getBlockY() - 1);
                        o.setZ(o.getBlockZ() - 1);
                        Location location = event.getBlock().getLocation();
                        o.setX(location.getBlockX() - 1);
                        o.setY(location.getBlockY() - 1);
                        o.setZ(location.getBlockZ() - 1);
                        for (int x = 0; x <= 2; x++) {
                            location.setX(o.getBlockX() + x);
                            for (int y = 0; y <= 2; y++) {
                                location.setY(o.getBlockY() + y);
                                for (int z = 0; z <= 2; z++) {
                                    location.setZ(o.getBlockZ() + z);
                                    Collection collection = location.getBlock().getDrops(tool);
                                    if(collection instanceof List){
                                        drops.addAll(collection);
                                    }
                                    location.getBlock().setType(Material.AIR);
                                }
                            }
                        }
                        for (ItemStack stacks : drops) {
                            event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(),stacks);
                        }
                    }
                }
            }
        }
    }
}
