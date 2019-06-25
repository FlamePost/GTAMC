package me.darthteddy1.gta.guns;

import me.darthteddy1.gta.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;


public class GunListener implements Listener {

    //§

    HashMap<Player, ItemStack> reloading = new HashMap<>();
    HashMap<Player, ItemStack> shot = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!(e.getAction().toString().contains("RIGHT"))) return;
        if (!reloading.containsKey(e.getPlayer())) {
            if(e.getItem() != null && e.getItem().getType() != null) {
                if (e.getItem().getType() == Material.SUGAR_CANE) {
                    if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                        if (getAmmo(e.getItem()) != -1) {
                            Integer newAmmo = getAmmo(e.getItem()) - 1;
                            if (newAmmo < 0) {
                                reload(e.getPlayer(), "§eBlow Gun §l» 1 «", 10);
                            } else {
                                if (newAmmo == 0) {
                                    reload(e.getPlayer(), "§eBlow Gun §l» 1 «", 10);
                                }
                                setName(e.getItem(), e.getPlayer(), "§eBlow Gun §l» " + newAmmo + " «");
                                Arrow s = e.getPlayer().launchProjectile(Arrow.class);
                                s.setVelocity(s.getVelocity().multiply(20));
                                s.setCustomName("BLOWDART");
                                s.setCustomNameVisible(false);
                            }
                        } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                            setName(e.getItem(), e.getPlayer(), "§eBlow Gun §l» 1 «");
                        }
                    }
                    e.setCancelled(true);
                }
                if (e.getItem().getType() == Material.FEATHER) {
                    if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                        if (getAmmo(e.getItem()) != -1) {
                            Integer newAmmo = getAmmo(e.getItem()) - 1;
                            if (!shot.containsKey(e.getPlayer())) {
                                if (newAmmo < 0) {
                                    reload(e.getPlayer(), "§eTaser §l» 2 «", 10);
                                } else {
                                    if (newAmmo == 0) {
                                        reload(e.getPlayer(), "§eTaser §l» 2 «", 10);
                                    }
                                    cooldown(e.getPlayer(), e.getItem(), 35);
                                    setName(e.getItem(), e.getPlayer(), "§eTaser §l» " + newAmmo + " «");
                                    Arrow s = e.getPlayer().launchProjectile(Arrow.class);
                                    s.setCustomName("TASERPIN");
                                    s.setCustomNameVisible(false);
                                }
                            } else {
                                if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), "§eTaser §l» 2 «", 10);
                                    } else {
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), "§eTaser §l» 2 «", 10);
                                        }
                                        cooldown(e.getPlayer(), e.getItem(), 35);
                                        setName(e.getItem(), e.getPlayer(), "§eTaser §l» " + newAmmo + " «");
                                        Arrow s = e.getPlayer().launchProjectile(Arrow.class);
                                        s.setCustomName("TASERPIN");
                                        s.setCustomNameVisible(false);
                                    }
                                }
                            }
                        } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                            setName(e.getItem(), e.getPlayer(), "§eTaser §l» 2 «");
                        }
                    }
                    e.setCancelled(true);
                }
                if (e.getItem().getType() == Material.FLINT_AND_STEEL) {
                    if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                        if (getAmmo(e.getItem()) != -1) {
                            Integer newAmmo = getAmmo(e.getItem()) - 1;
                            if (newAmmo < 0) {
                                reload(e.getPlayer(), "§eFlamethrower §l» 200 «", 10);
                            } else {
                                if (newAmmo == 0) {
                                    reload(e.getPlayer(), "§eFlamethrower §l» 200 «", 10);
                                }
                                setName(e.getItem(), e.getPlayer(), "§eFlamethrower §l» " + newAmmo + " «");
                                Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                Snowball s1 = e.getPlayer().launchProjectile(Snowball.class);
                                Snowball s2 = e.getPlayer().launchProjectile(Snowball.class);
                                Snowball s3 = e.getPlayer().launchProjectile(Snowball.class);
                                Snowball s4 = e.getPlayer().launchProjectile(Snowball.class);
                                Snowball s5 = e.getPlayer().launchProjectile(Snowball.class);
                                Snowball s6 = e.getPlayer().launchProjectile(Snowball.class);
                                s.setVelocity(s.getVelocity());
                                s1.setVelocity(s.getVelocity().add(new Vector(0, 0.3, 0)));
                                s2.setVelocity(s.getVelocity().add(new Vector(0.5, 0.3, 0)));
                                s3.setVelocity(s.getVelocity().add(new Vector(0, 0.3, 0.5)));
                                s4.setVelocity(s.getVelocity().add(new Vector(0, -0.1, 0)));
                                s5.setVelocity(s.getVelocity().add(new Vector(0.5, -0.1, 0)));
                                s6.setVelocity(s.getVelocity().add(new Vector(0, -0.1, 0.5)));
                                s.setCustomName("FLAME");
                                s.setCustomNameVisible(false);
                                s1.setCustomName("FLAME");
                                s1.setCustomNameVisible(false);
                                s2.setCustomName("FLAME");
                                s2.setCustomNameVisible(false);
                                s3.setCustomName("FLAME");
                                s3.setCustomNameVisible(false);
                                s4.setCustomName("FLAME");
                                s4.setCustomNameVisible(false);
                                s5.setCustomName("FLAME");
                                s5.setCustomNameVisible(false);
                                s6.setCustomName("FLAME");
                                s6.setCustomNameVisible(false);
                            }
                        } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                            setName(e.getItem(), e.getPlayer(), "§eFlamethrower §l» 200 «");
                        }
                    }
                    e.setCancelled(true);
                }
                if (e.getItem().getType() == Material.CARROT_STICK) {
                    if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                        if (getAmmo(e.getItem()) != -1) {
                            Integer newAmmo = getAmmo(e.getItem()) - 1;

                            if (!shot.containsKey(e.getPlayer())) {
                                if (newAmmo < 0) {
                                    reload(e.getPlayer(), "§5Enderstaff §l» 10 «", 10);
                                } else {
                                    if (newAmmo == 0) {
                                        reload(e.getPlayer(), "§5Enderstaff §l» 10 «", 10);
                                    }
                                    cooldown(e.getPlayer(), e.getItem(), 18);
                                    setName(e.getItem(), e.getPlayer(), "§5Enderstaff §l» " + newAmmo + " «");
                                    e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
                                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 2);
                                }
                            } else {
                                if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), "§5Enderstaff §l» 10 «", 10);
                                    } else {
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), "§5Enderstaff §l» 10 «", 10);
                                        }
                                        cooldown(e.getPlayer(), e.getItem(), 18);
                                        setName(e.getItem(), e.getPlayer(), "§5Enderstaff §l» " + newAmmo + " «");
                                        e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
                                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 2);
                                    }
                                }
                            }

                        } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                            setName(e.getItem(), e.getPlayer(), "§5Enderstaff §l» 10 «");
                        }
                    }
                    e.setCancelled(true);
                }
                if (e.getItem().getType() == Material.DOUBLE_PLANT) {
                    if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                        if (getAmmo(e.getItem()) != -1) {
                            Integer newAmmo = getAmmo(e.getItem()) - 1;

                            if (!shot.containsKey(e.getPlayer())) {
                                if (newAmmo < 0) {
                                    reload(e.getPlayer(), "§eJetpack §l» 50 «", 15);
                                } else {
                                    if (newAmmo == 0) {
                                        reload(e.getPlayer(), "§eJetpack §l» 50 «", 15);
                                    }
                                    setName(e.getItem(), e.getPlayer(), "§eJetpack §l» " + newAmmo + " «");
                                    e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(1));
                                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BAT_TAKEOFF, 10, 1);
                                }
                            } else {
                                if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), "§eJetpack §l» 50 «", 15);
                                    } else {
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), "§eJetpack §l» 50 «", 15);
                                        }
                                        setName(e.getItem(), e.getPlayer(), "§eJetpack §l» " + newAmmo + " «");
                                        e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(1));
                                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BAT_TAKEOFF, 10, 1);
                                    }
                                }
                            }

                        } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                            setName(e.getItem(), e.getPlayer(), "§5Enderstaff §l» 10 «");
                        }
                    }
                    e.setCancelled(true);
                }
                if (e.getItem().getType() == Material.LEASH) {
                    if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                        if (getAmmo(e.getItem()) != -1) {
                            Integer newAmmo = getAmmo(e.getItem()) - 1;
                            if (newAmmo < 0) {
                                reload(e.getPlayer(), "§eMinigun §l» 500 «", 30);
                            } else {
                                if (newAmmo == 0) {
                                    reload(e.getPlayer(), "§eMinigun §l» 500 «", 30);
                                }
                                Egg s = e.getPlayer().launchProjectile(Egg.class);
                                s.setVelocity(s.getVelocity().multiply(5));
                                s.setCustomName("MINIGUN");
                                s.setCustomNameVisible(false);
                                setName(e.getItem(), e.getPlayer(), "§eMinigun §l» " + newAmmo + " «");
                                newAmmo--;
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        Egg s = e.getPlayer().launchProjectile(Egg.class);
                                        s.setVelocity(s.getVelocity().multiply(5));
                                        s.setCustomName("MINIGUN");
                                        s.setCustomNameVisible(false);

                                    }
                                }, 1);
                                setName(e.getItem(), e.getPlayer(), "§eMinigun §l» " + newAmmo + " «");
                                newAmmo--;
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        Egg s = e.getPlayer().launchProjectile(Egg.class);
                                        s.setVelocity(s.getVelocity().multiply(5));
                                        s.setCustomName("MINIGUN");
                                        s.setCustomNameVisible(false);

                                    }
                                }, 2);
                                setName(e.getItem(), e.getPlayer(), "§eMinigun §l» " + newAmmo + " «");
                                newAmmo--;
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        Egg s = e.getPlayer().launchProjectile(Egg.class);
                                        s.setVelocity(s.getVelocity().multiply(5));
                                        s.setCustomName("MINIGUN");
                                        s.setCustomNameVisible(false);
                                    }
                                }, 3);
                                setName(e.getItem(), e.getPlayer(), "§eMinigun §l» " + newAmmo + " «");
                                newAmmo--;
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        Egg s = e.getPlayer().launchProjectile(Egg.class);
                                        s.setVelocity(s.getVelocity().multiply(5));
                                        s.setCustomName("MINIGUN");
                                        s.setCustomNameVisible(false);

                                    }
                                }, 4);
                                setName(e.getItem(), e.getPlayer(), "§eMinigun §l» " + newAmmo + " «");
                            }
                        } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                            setName(e.getItem(), e.getPlayer(), "§eMinigun §l» 500 «");
                        }
                    }
                    e.setCancelled(true);
                }
                if (e.getItem().getType() == Material.IRON_BARDING) {
                    if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                        if (getAmmo(e.getItem()) != -1) {
                            Integer newAmmo = getAmmo(e.getItem()) - 1;
                            if (newAmmo < 0) {
                                reload(e.getPlayer(), "§eBazooka §l» 1 «", 20);
                            } else {
                                if (newAmmo == 0) {
                                    reload(e.getPlayer(), "§eBazooka §l» 1 «", 20);
                                }
                                Fireball f = e.getPlayer().launchProjectile(Fireball.class);
                                f.setCustomName("BAZOOKA");
                                f.setCustomNameVisible(false);
                                setName(e.getItem(), e.getPlayer(), "§eBazooka §l» " + newAmmo + " «");
                            }
                        } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                            setName(e.getItem(), e.getPlayer(), "§eBazooka §l» 1 «");
                        }
                    }
                    e.setCancelled(true);
                }
            }
        } else {
            reloading.remove(e.getPlayer());
            e.getPlayer().sendMessage("§c§lReload cancelled (§7Item used§c§l)");
        }

    }

    @EventHandler
    public void onEnt(PlayerInteractEntityEvent e) {
        if(e.getPlayer().getItemInHand().getType() == Material.LEASH) {
            e.setCancelled(true);
            e.getPlayer().updateInventory();
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getType() == Material.DOUBLE_PLANT) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eJetpack §l» 50 «", 15);
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.FEATHER) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eTaser §l» 2 «", 10);
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.FLINT_AND_STEEL) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eFlamethrower §l» 200 «", 10);
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.SUGAR_CANE) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eBlow Gun §l» 1 «", 10);
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.CARROT_STICK) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§5Enderstaff §l» 10 «", 10);
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.LEASH) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eMinigun §l» 500 «", 30);
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.IRON_BARDING) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eBazooka §l» 1 «", 20);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDmg(EntityDamageEvent e) {
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if(e.getEntity() instanceof Player) {
                Player p = (Player) e.getEntity();
                if(p.getItemInHand().getType() == Material.CARROT_STICK || p.getItemInHand().getType() == Material.DOUBLE_PLANT) {
                    e.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e)
    {
        if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.EGG)
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onProjectileHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow) {
            Arrow s = (Arrow) e.getDamager();
            if (s.getCustomName() != null) {
                if (s.getShooter() != e.getEntity()) {
                    if (s.getCustomName().equals("BLOWDART")) {
                        if (e.getEntity() instanceof Player) {
                            Player p = (Player) e.getEntity();
                            e.setDamage(6);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 3));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));

                        }
                    }
                    if (s.getCustomName().equals("TASERPIN")) {
                        if (e.getEntity() instanceof Player) {
                            Player p = (Player) e.getEntity();
                            e.setDamage(2);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 5));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 2));

                        }
                    }
                }
            }
        }
        if(e.getDamager() instanceof Snowball) {
            Snowball s = (Snowball) e.getDamager();
            if (s.getCustomName() != null) {
                if (s.getShooter() != e.getEntity()) {
                    if (s.getCustomName().equals("FLAME")) {
                            Entity p = e.getEntity();
                            p.setFireTicks(100);
                            e.setDamage(2);
                        }
                }
            }
        }
        if(e.getDamager() instanceof Egg) {
            Egg s = (Egg) e.getDamager();
            if (s.getCustomName() != null) {
                if (s.getShooter() != e.getEntity()) {
                    if (s.getCustomName().equals("MINIGUN")) {
                        e.setDamage(1);
                    }
                }
            }
        }
        if(e.getDamager() instanceof Fireball) {
            Fireball f = (Fireball) e.getDamager();
            if(f.getCustomName() != null) {
                if(f.getShooter() != e.getEntity()) {
                    if(f.getCustomName().equals("BAZOOKA")) {
                        e.setDamage(4);
                        e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 1, false);
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        ArrayList<Block> blocks = new ArrayList<>(e.blockList());
        for(Block b : blocks) {
            b.getState().update(true);
        }
        e.setYield(0);
        BazookaExplosion bz = new BazookaExplosion(blocks);
        Bukkit.getScheduler().runTaskLater(Core.getInstance(), bz, 20);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getClickedInventory().getTitle().equals("§c§lGuns")) {
            Player p = (Player) e.getWhoClicked();
            p.getInventory().addItem(e.getCurrentItem());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(ProjectileHitEvent e) {
        if(e.getEntity() instanceof Arrow){
            Arrow arrow = (Arrow) e.getEntity();
            arrow.remove();
        }
    }

    private Integer getAmmo(ItemStack i) {
        if(i.hasItemMeta() && i.getItemMeta().hasDisplayName()) {
            String[] st1 = i.getItemMeta().getDisplayName().split("»");
            String[] st2 = st1[1].split("«");
            return Integer.parseInt(st2[0].replace(" ", ""));
        }
        return -1;
    }

    private void setName(ItemStack i, Player p, String s) {
        if(i.hasItemMeta() && i.getItemMeta().hasDisplayName()) {
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(s);
            i.setItemMeta(im);
            p.updateInventory();
        }
    }

    private void reload(Player p, String name, Integer time) {
        if(!reloading.containsKey(p)) {
            p.sendMessage("§a§lReloading (§7" + time + " seconds§a§l)");
            reloading.put(p, p.getItemInHand());
            scheduleReload(p, name, time);
        }
    }
    private void reload(Player p, ItemStack i, String name, Integer time) {
        if(!reloading.containsKey(p)) {
            p.sendMessage("§a§lReloading (§7" + time + " seconds§a§l)");
            reloading.put(p, i);
            scheduleReload(p, name, time);
        }
    }

    private void scheduleReload(Player p, String name, Integer time) {
        final int t = time-1;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (reloading.containsKey(p)) {
                    if (p.getItemInHand().isSimilar(reloading.get(p))) {
                        if (t == 0) {
                            setName(p.getItemInHand(), p, name);
                            reloading.remove(p);
                        } else {
                            scheduleReload(p, name, t);
                        }
                    } else {
                        reloading.remove(p);
                        p.sendMessage("§c§lCancelled Reload (§7Switched item§c§l)");
                    }
                }
            }
        }, 20);
    }

    private void reload(ItemStack i, Player p, String name, Integer time, Material ammo, Integer ammoAmount) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(p.getInventory().contains(i)) {
                    setName(i, p, name);
                }
            }
        }, 20*time);
    }

    private void cooldown(Player p, ItemStack i, int time) {
        shot.put(p, i);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
            @Override
            public void run() {
                shot.remove(p, i);
            }
        }, time);
    }
}
