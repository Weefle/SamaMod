package net.samagames.tools.powerups;

import net.samagames.api.SamaGamesAPI;
import net.samagames.tools.ParticleEffect;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

/*
 * This file is part of SamaGamesAPI.
 *
 * SamaGamesAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SamaGamesAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SamaGamesAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class ActivePowerup implements Listener
{
    private final UUID uuid;
    private final Powerup parent;
    private final Location location;

    private Item entityItem;
    private ArmorStand entityBase;
    private ArmorStand entityTitle;

    private BukkitTask particlesTask;

    private boolean alive;

    public ActivePowerup(Powerup parent, Location location)
    {
        this.uuid = UUID.randomUUID();
        this.parent = parent;
        this.location = location;

        this.spawn();
    }

    public void remove(boolean got)
    {
        this.entityTitle.remove();
        this.entityItem.remove();
        this.entityBase.remove();

        Color fwColor = got ? Color.BLUE : Color.RED;

        Firework fw = this.location.getWorld().spawn(this.location.clone().add(0.5, 1, 0.5), Firework.class);
        FireworkMeta fwm = fw.getFireworkMeta();
        FireworkEffect effect = FireworkEffect.builder().withColor(fwColor).with(this.parent.isSpecial() ? FireworkEffect.Type.STAR : FireworkEffect.Type.BALL).build();

        fwm.addEffects(effect);
        fwm.setPower(0);

        fw.setFireworkMeta(fwm);

        Bukkit.getScheduler().runTaskLater(SamaGamesAPI.get().getPlugin(), fw::detonate, 1L);

        this.particlesTask.cancel();

        this.alive = false;
    }

    @SuppressWarnings("deprecation")
	private void spawn()
    {
        World world = this.location.getWorld();

        ItemStack powerupItem = this.parent.getIcon().clone();

        ItemMeta powerupItemMeta = powerupItem.getItemMeta();
        powerupItemMeta.setDisplayName(this.uuid.toString());

        powerupItem.setItemMeta(powerupItemMeta);

        this.entityBase = world.spawn(this.location.clone().add(0, -0.5, 0), ArmorStand.class);
        this.entityBase.setVisible(false);
        this.entityBase.setSmall(true);
        this.entityBase.setGravity(false);

        this.entityItem = world.dropItem(this.location, powerupItem);
        this.entityItem.setPickupDelay(0);

        this.entityTitle = world.spawn(this.location, ArmorStand.class);
        this.entityTitle.setGravity(false);
        this.entityTitle.setVisible(false);
        this.entityTitle.setSmall(true);
        this.entityTitle.setCustomName(this.parent.getName());
        this.entityTitle.setCustomNameVisible(true);
        this.entityTitle.setCanPickupItems(false);

        this.entityBase.setPassenger(this.entityItem);
        this.entityItem.setPassenger(this.entityTitle);

        this.particlesTask = Bukkit.getScheduler().runTaskTimerAsynchronously(SamaGamesAPI.get().getPlugin(), () ->
                ParticleEffect.SPELL_INSTANT.display(0.5F, 0.5F, 0.5F, 0.1F, 2, this.location, 100.0), 1L, 5L);

        this.alive = true;

        Bukkit.getPluginManager().registerEvents(this, SamaGamesAPI.get().getPlugin());
    }

    @EventHandler
    private void onPlayerPickupItem(PlayerPickupItemEvent event)
    {
        if (event.getItem().getItemStack() != null && event.getItem().getItemStack().getItemMeta() != null && event.getItem().getItemStack().getItemMeta().getDisplayName() != null)
        {
            if (this.alive && event.getItem().getItemStack().getItemMeta().getDisplayName().equals(this.uuid.toString()))
            {
                event.setCancelled(true);

                HandlerList.unregisterAll(this);

                this.remove(true);
                this.parent.onPickup(event.getPlayer());
            }
        }
    }

    public boolean isAlive()
    {
        return this.alive;
    }
}
