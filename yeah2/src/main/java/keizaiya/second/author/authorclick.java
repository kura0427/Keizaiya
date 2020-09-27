package keizaiya.second.author;

import keizaiya.second.Potato;
import keizaiya.second.inventory.menu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

public class authorclick {
    public static void authorclick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getItem() != null) {
                String name = e.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "summon")
                        , PersistentDataType.STRING);
                if(name != null) {
                    if (name.contains("villager")) {
                        Location location = e.getClickedBlock().getLocation();
                        location.setY(location.getY() + 1);
                        e.getPlayer().getWorld().spawnEntity(location, EntityType.VILLAGER);
                    }
                }
            }
        }if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
            if(e.getPlayer().isSneaking()){
                if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK){
                    menu.opengui(e.getPlayer());
                }
            }
        }
    }
}
