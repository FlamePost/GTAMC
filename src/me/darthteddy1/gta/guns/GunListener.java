package me.darthteddy1.gta.guns;

import me.darthteddy1.gta.Core;
import me.darthteddy1.gta.utils.MessageUtils;
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
        if(e.getItem() != null) {
            if (!reloading.containsKey(e.getPlayer())) {
                if (e.getItem() != null && e.getItem().getType() != null) {
                    if (e.getItem().getType() == Material.SUGAR_CANE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (e.getItem().getAmount() > 1) {
                                e.getPlayer().sendMessage(MessageUtils.PREFIX_BAD + "§cYou cannot use stacked weapons");
                                return;
                            }
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
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (e.getItem().getAmount() > 1) {
                                e.getPlayer().sendMessage(MessageUtils.PREFIX_BAD + "§cYou cannot use stacked weapons");
                                return;
                            }
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
                        e.setCancelled(true);
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
                                    for (int i = 0; i < 7; i++) {
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        if (i == 0) {
                                            s.setVelocity(s.getVelocity());
                                        } else if (i == 1) {
                                            s.setVelocity(s.getVelocity().add(new Vector(0, 0.3, 0)));
                                        } else if (i == 2) {
                                            s.setVelocity(s.getVelocity().add(new Vector(0.5, 0.3, 0)));
                                        } else if (i == 3) {
                                            s.setVelocity(s.getVelocity().add(new Vector(0, 0.3, 0.5)));
                                        } else if (i == 4) {
                                            s.setVelocity(s.getVelocity().add(new Vector(0, -0.1, 0)));
                                        } else if (i == 5) {
                                            s.setVelocity(s.getVelocity().add(new Vector(0.5, -0.1, 0)));
                                        } else if (i == 6) {
                                            s.setVelocity(s.getVelocity().add(new Vector(0, -0.1, 0.5)));
                                        }
                                        s.setCustomName("FLAME");
                                        s.setCustomNameVisible(false);
                                    }
                                }
                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§eFlamethrower §l» 200 «");
                            }
                        }
                        e.setCancelled(true);
                    }
                    if (e.getItem().getType() == Material.CARROT_STICK) {
                        e.setCancelled(true);
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
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (e.getItem().getAmount() > 1) {
                                e.getPlayer().sendMessage(MessageUtils.PREFIX_BAD + "§cYou cannot use stacked weapons");
                                return;
                            }
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
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (e.getItem().getAmount() > 1) {
                                e.getPlayer().sendMessage(MessageUtils.PREFIX_BAD + "§cYou cannot use stacked weapons");
                                return;
                            }
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
                                    if (newAmmo == 0) {
                                        reload(e.getPlayer(), "§eMinigun §l» 500 «", 30);
                                        return;
                                    }
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
                                    if (newAmmo == 0) {
                                        reload(e.getPlayer(), "§eMinigun §l» 500 «", 30);
                                        return;
                                    }
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
                                    if (newAmmo == 0) {
                                        reload(e.getPlayer(), "§eMinigun §l» 500 «", 30);
                                        return;
                                    }
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
                                    if (newAmmo == 0) {
                                        reload(e.getPlayer(), "§eMinigun §l» 500 «", 30);
                                        return;
                                    }
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
                        e.setCancelled(true);
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

                    //Begin Tier Weapons

                    if (e.getItem().getType() == Material.WOOD_SPADE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§eTier1 Shotgun §l» 4 «", 10, Material.GOLD_INGOT, 4);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 20);
                                        setName(e.getItem(), e.getPlayer(), "§eTier1 Shotgun §l» " + newAmmo + " «");
                                        fireShotgunShots(e.getPlayer(), "TIER1SHOTTY");
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§eTier1 Shotgun §l» 4 «", 10, Material.GOLD_INGOT, 4);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§eTier1 Shotgun §l» 4 «", 10, Material.GOLD_INGOT, 4);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 20);
                                            setName(e.getItem(), e.getPlayer(), "§eTier1 Shotgun §l» " + newAmmo + " «");
                                            fireShotgunShots(e.getPlayer(), "TIER1SHOTTY");
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§eTier1 Shotgun §l» 4 «", 10, Material.GOLD_INGOT, 4);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§eTier1 Shotgun §l» 4 «");
                            }
                        }
                        e.setCancelled(true);
                    }

                    if (e.getItem().getType() == Material.STONE_SPADE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§6Tier2 Shotgun §l» 2 «", 8, Material.GOLD_INGOT, 2);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 20);
                                        setName(e.getItem(), e.getPlayer(), "§6Tier2 Shotgun §l» " + newAmmo + " «");
                                        fireShotgunShots(e.getPlayer(), "TIER2SHOTTY");
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§6Tier2 Shotgun §l» 2 «", 8, Material.GOLD_INGOT, 2);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§eTier1 Shotgun §l» 4 «", 8, Material.GOLD_INGOT, 2);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 25);
                                            setName(e.getItem(), e.getPlayer(), "§6Tier2 Shotgun §l» " + newAmmo + " «");
                                            fireShotgunShots(e.getPlayer(), "TIER2SHOTTY");
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§6Tier2 Shotgun §l» 2 «", 8, Material.GOLD_INGOT, 2);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§6Tier2 Shotgun §l» 2 «");
                            }
                        }
                        e.setCancelled(true);
                    }
                    if (e.getItem().getType() == Material.GOLD_SPADE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§aTier3 Shotgun §l» 5 «", 12, Material.GOLD_INGOT, 5);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 20);
                                        setName(e.getItem(), e.getPlayer(), "§aTier3 Shotgun §l» " + newAmmo + " «");
                                        fireShotgunShots(e.getPlayer(), "TIER3SHOTTY");
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§aTier3 Shotgun §l» 5 «", 12, Material.GOLD_INGOT, 5);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§aTier3 Shotgun §l» 5 «", 12, Material.GOLD_INGOT, 5);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 25);
                                            setName(e.getItem(), e.getPlayer(), "§aTier3 Shotgun §l» " + newAmmo + " «");
                                            fireShotgunShots(e.getPlayer(), "TIER3SHOTTY");
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§aTier3 Shotgun §l» 5 «", 12, Material.GOLD_INGOT, 5);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§aTier3 Shotgun §l» 5 «");
                            }
                        }
                        e.setCancelled(true);
                    }
                    if (e.getItem().getType() == Material.IRON_SPADE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§dTier4 Shotgun §l» 5 «", 12, Material.GOLD_INGOT, 5);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 20);
                                        setName(e.getItem(), e.getPlayer(), "§dTier4 Shotgun §l» " + newAmmo + " «");
                                        fireShotgunShots(e.getPlayer(), "TIER4SHOTTY");
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§dTier4 Shotgun §l» 5 «", 12, Material.GOLD_INGOT, 5);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§dTier4 Shotgun §l» 5 «", 12, Material.GOLD_INGOT, 5);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 25);
                                            setName(e.getItem(), e.getPlayer(), "§dTier4 Shotgun §l» " + newAmmo + " «");
                                            fireShotgunShots(e.getPlayer(), "TIER4SHOTTY");
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§dTier4 Shotgun §l» 5 «", 12, Material.GOLD_INGOT, 5);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§dTier4 Shotgun §l» 5 «");
                            }
                        }
                        e.setCancelled(true);
                    }
                    if (e.getItem().getType() == Material.DIAMOND_SPADE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§bTier5 Shotgun §l» 6 «", 10, Material.GOLD_INGOT, 6);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 20);
                                        setName(e.getItem(), e.getPlayer(), "§bTier5 Shotgun §l» " + newAmmo + " «");
                                        fireShotgunShots(e.getPlayer(), "TIER5SHOTTY");
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§bTier5 Shotgun §l» 6 «", 10, Material.GOLD_INGOT, 6);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§bTier5 Shotgun §l» 6 «", 10, Material.GOLD_INGOT, 6);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 25);
                                            setName(e.getItem(), e.getPlayer(), "§bTier5 Shotgun §l» " + newAmmo + " «");
                                            fireShotgunShots(e.getPlayer(), "TIER5SHOTTY");
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§bTier5 Shotgun §l» 6 «", 10, Material.GOLD_INGOT, 6);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§bTier5 Shotgun §l» 6 «");
                            }
                        }
                        e.setCancelled(true);
                    }


                    //Assault rifles by tier

                    if (e.getItem().getType() == Material.WOOD_PICKAXE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§eTier1 Assault Rifle §l» 20 «", 10, Material.IRON_INGOT, 20);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 2);
                                        setName(e.getItem(), e.getPlayer(), "§eTier1 Assault Rifle §l» " + newAmmo + " «");
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        s.setVelocity(s.getVelocity().multiply(2));
                                        s.setCustomName("TIER1AR");
                                        s.setCustomNameVisible(false);
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§eTier1 Assault Rifle §l» 20 «", 10, Material.IRON_INGOT, 20);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§eTier1 Assault Rifle §l» 20 «", 10, Material.IRON_INGOT, 20);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 2);
                                            setName(e.getItem(), e.getPlayer(), "§eTier1 Assault Rifle §l» " + newAmmo + " «");
                                            Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                            s.setVelocity(s.getVelocity().multiply(2));
                                            s.setCustomName("TIER1AR");
                                            s.setCustomNameVisible(false);
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§eTier1 Assault Rifle §l» 20 «", 10, Material.IRON_INGOT, 20);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§eTier1 Assault Rifle §l» 20 «");
                            }
                        }
                        e.setCancelled(true);
                    }

                    if (e.getItem().getType() == Material.STONE_PICKAXE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§6Tier2 Assault Rifle §l» 25 «", 11, Material.IRON_INGOT, 25);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 2);
                                        setName(e.getItem(), e.getPlayer(), "§6Tier2 Assault Rifle §l» " + newAmmo + " «");
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        s.setVelocity(s.getVelocity().multiply(2));
                                        s.setCustomName("TIER2AR");
                                        s.setCustomNameVisible(false);
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§6Tier2 Assault Rifle §l» 25 «", 11, Material.IRON_INGOT, 25);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§6Tier2 Assault Rifle §l» 25 «", 11, Material.IRON_INGOT, 25);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 2);
                                            setName(e.getItem(), e.getPlayer(), "§6Tier2 Assault Rifle §l» " + newAmmo + " «");
                                            Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                            s.setVelocity(s.getVelocity().multiply(2));
                                            s.setCustomName("TIER2AR");
                                            s.setCustomNameVisible(false);
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§6Tier2 Assault Rifle §l» 25 «", 11, Material.IRON_INGOT, 25);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§6Tier2 Assault Rifle §l» 25 «");
                            }
                        }
                        e.setCancelled(true);
                    }

                    if (e.getItem().getType() == Material.GOLD_PICKAXE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§aTier3 Assault Rifle §l» 25 «", 11, Material.IRON_INGOT, 25);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 2);
                                        setName(e.getItem(), e.getPlayer(), "§aTier3 Assault Rifle §l» " + newAmmo + " «");
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        s.setVelocity(s.getVelocity().multiply(2));
                                        s.setCustomName("TIER3AR");
                                        s.setCustomNameVisible(false);
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§aTier3 Assault Rifle §l» 25 «", 11, Material.IRON_INGOT, 25);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§aTier3 Assault Rifle §l» 25 «", 11, Material.IRON_INGOT, 25);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 2);
                                            setName(e.getItem(), e.getPlayer(), "§aTier3 Assault Rifle §l» " + newAmmo + " «");
                                            Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                            s.setVelocity(s.getVelocity().multiply(2));
                                            s.setCustomName("TIER3AR");
                                            s.setCustomNameVisible(false);
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§aTier3 Assault Rifle §l» 25 «", 11, Material.IRON_INGOT, 25);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§aTier3 Assault Rifle §l» 25 «");
                            }
                        }
                        e.setCancelled(true);
                    }

                    if (e.getItem().getType() == Material.IRON_PICKAXE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§dTier4 Assault Rifle §l» 30 «", 13, Material.IRON_INGOT, 30);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 2);
                                        setName(e.getItem(), e.getPlayer(), "§dTier4 Assault Rifle §l» " + newAmmo + " «");
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        s.setVelocity(s.getVelocity().multiply(2));
                                        s.setCustomName("TIER4AR");
                                        s.setCustomNameVisible(false);
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§dTier4 Assault Rifle §l» 30 «", 13, Material.IRON_INGOT, 30);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§dTier4 Assault Rifle §l» 30 «", 13, Material.IRON_INGOT, 30);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 2);
                                            setName(e.getItem(), e.getPlayer(), "§dTier4 Assault Rifle §l» " + newAmmo + " «");
                                            Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                            s.setVelocity(s.getVelocity().multiply(2));
                                            s.setCustomName("TIER4AR");
                                            s.setCustomNameVisible(false);
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§dTier4 Assault Rifle §l» 30 «", 13, Material.IRON_INGOT, 30);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§dTier4 Assault Rifle §l» 30 «");
                            }
                        }
                        e.setCancelled(true);
                    }

                    if (e.getItem().getType() == Material.DIAMOND_PICKAXE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§bTier5 Assault Rifle §l» 35 «", 15, Material.IRON_INGOT, 35);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 1);
                                        setName(e.getItem(), e.getPlayer(), "§bTier5 Assault Rifle §l» " + newAmmo + " «");
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        s.setVelocity(s.getVelocity().multiply(2));
                                        s.setCustomName("TIER5AR");
                                        s.setCustomNameVisible(false);
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§bTier5 Assault Rifle §l» 35 «", 15, Material.IRON_INGOT, 35);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§bTier5 Assault Rifle §l» 35 «", 15, Material.IRON_INGOT, 35);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 1);
                                            setName(e.getItem(), e.getPlayer(), "§bTier5 Assault Rifle §l» " + newAmmo + " «");
                                            Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                            s.setVelocity(s.getVelocity().multiply(2));
                                            s.setCustomName("TIER5AR");
                                            s.setCustomNameVisible(false);
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§bTier5 Assault Rifle §l» 35 «", 15, Material.IRON_INGOT, 35);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§bTier5 Assault Rifle §l» 35 «");
                            }
                        }
                        e.setCancelled(true);
                    }

                    //Begin snipers

                    if (e.getItem().getType() == Material.WOOD_HOE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§eTier1 Sniper Rifle §l» 4 «", 10, Material.NETHER_BRICK_ITEM, 4);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 80);
                                        setName(e.getItem(), e.getPlayer(), "§eTier1 Sniper Rifle §l» " + newAmmo + " «");
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        s.setVelocity(s.getVelocity().multiply(5));
                                        s.setCustomName("TIER1SNIPE");
                                        s.setCustomNameVisible(false);
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§eTier1 Sniper Rifle §l» 4 «", 10, Material.NETHER_BRICK_ITEM, 4);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§eTier1 Sniper Rifle §l» 4 «", 10, Material.NETHER_BRICK_ITEM, 4);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 80);
                                            setName(e.getItem(), e.getPlayer(), "§eTier1 Sniper Rifle §l» " + newAmmo + " «");
                                            Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                            s.setVelocity(s.getVelocity().multiply(5));
                                            s.setCustomName("TIER1SNIPE");
                                            s.setCustomNameVisible(false);
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§eTier1 Sniper Rifle §l» 4 «", 10, Material.NETHER_BRICK_ITEM, 4);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§eTier1 Sniper Rifle §l» 4 «");
                            }
                        }
                        e.setCancelled(true);
                    }

                    if (e.getItem().getType() == Material.STONE_HOE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§6Tier2 Sniper Rifle §l» 4 «", 10, Material.NETHER_BRICK_ITEM, 4);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 80);
                                        setName(e.getItem(), e.getPlayer(), "§6Tier2 Sniper Rifle §l» " + newAmmo + " «");
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        s.setVelocity(s.getVelocity().multiply(5));
                                        s.setCustomName("TIER2SNIPE");
                                        s.setCustomNameVisible(false);
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§6Tier2 Sniper Rifle §l» 4 «", 10, Material.NETHER_BRICK_ITEM, 4);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§6Tier2 Sniper Rifle §l» 4 «", 10, Material.NETHER_BRICK_ITEM, 4);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 80);
                                            setName(e.getItem(), e.getPlayer(), "§6Tier2 Sniper Rifle §l» " + newAmmo + " «");
                                            Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                            s.setVelocity(s.getVelocity().multiply(5));
                                            s.setCustomName("TIER2SNIPE");
                                            s.setCustomNameVisible(false);
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§6Tier2 Sniper Rifle §l» 4 «", 10, Material.NETHER_BRICK_ITEM, 4);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§6Tier2 Sniper Rifle §l» 4 «");
                            }
                        }
                        e.setCancelled(true);
                    }

                    if (e.getItem().getType() == Material.GOLD_HOE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§aTier3 Sniper Rifle §l» 6 «", 10, Material.NETHER_BRICK_ITEM, 6);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 80);
                                        setName(e.getItem(), e.getPlayer(), "§aTier3 Sniper Rifle §l» " + newAmmo + " «");
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        s.setVelocity(s.getVelocity().multiply(5));
                                        s.setCustomName("TIER3SNIPE");
                                        s.setCustomNameVisible(false);
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§aTier3 Sniper Rifle §l» 6 «", 10, Material.NETHER_BRICK_ITEM, 6);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§aTier3 Sniper Rifle §l» 6 «", 10, Material.NETHER_BRICK_ITEM, 6);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 80);
                                            setName(e.getItem(), e.getPlayer(), "§aTier3 Sniper Rifle §l» " + newAmmo + " «");
                                            Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                            s.setVelocity(s.getVelocity().multiply(5));
                                            s.setCustomName("TIER3SNIPE");
                                            s.setCustomNameVisible(false);
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§aTier3 Sniper Rifle §l» 6 «", 10, Material.NETHER_BRICK_ITEM, 6);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§aTier3 Sniper Rifle §l» 6 «");
                            }
                        }
                        e.setCancelled(true);
                    }

                    if (e.getItem().getType() == Material.IRON_HOE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§dTier4 Sniper Rifle §l» 5 «", 10, Material.NETHER_BRICK_ITEM, 5);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 80);
                                        setName(e.getItem(), e.getPlayer(), "§dTier4 Sniper Rifle §l» " + newAmmo + " «");
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        s.setVelocity(s.getVelocity().multiply(5));
                                        s.setCustomName("TIER4SNIPE");
                                        s.setCustomNameVisible(false);
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§dTier4 Sniper Rifle §l» 5 «", 10, Material.NETHER_BRICK_ITEM, 5);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§dTier4 Sniper Rifle §l» 5 «", 10, Material.NETHER_BRICK_ITEM, 5);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 80);
                                            setName(e.getItem(), e.getPlayer(), "§dTier4 Sniper Rifle §l» " + newAmmo + " «");
                                            Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                            s.setVelocity(s.getVelocity().multiply(5));
                                            s.setCustomName("TIER4SNIPE");
                                            s.setCustomNameVisible(false);
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§dTier4 Sniper Rifle §l» 5 «", 10, Material.NETHER_BRICK_ITEM, 5);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§dTier4 Sniper Rifle §l» 5 «");
                            }
                        }
                        e.setCancelled(true);
                    }

                    if (e.getItem().getType() == Material.DIAMOND_HOE) {
                        e.setCancelled(true);
                        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                            if (getAmmo(e.getItem()) != -1) {
                                Integer newAmmo = getAmmo(e.getItem()) - 1;
                                if (!shot.containsKey(e.getPlayer())) {
                                    if (newAmmo < 0) {
                                        reload(e.getPlayer(), e.getItem(), "§bTier5 Sniper Rifle §l» 2 «", 10, Material.NETHER_BRICK_ITEM, 2);
                                    } else {
                                        cooldown(e.getPlayer(), e.getItem(), 40);
                                        setName(e.getItem(), e.getPlayer(), "§bTier5 Sniper Rifle §l» " + newAmmo + " «");
                                        Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                        s.setVelocity(s.getVelocity().multiply(8));
                                        s.setCustomName("TIER5SNIPE");
                                        s.setCustomNameVisible(false);
                                        if (newAmmo == 0) {
                                            reload(e.getPlayer(), e.getItem(), "§bTier5 Sniper Rifle §l» 2 «", 10, Material.NETHER_BRICK_ITEM, 2);
                                        }
                                    }
                                } else {
                                    if (!shot.get(e.getPlayer()).isSimilar(e.getItem())) {
                                        if (newAmmo < 0) {
                                            reload(e.getPlayer(), e.getItem(), "§bTier5 Sniper Rifle §l» 2 «", 10, Material.NETHER_BRICK_ITEM, 2);
                                        } else {
                                            cooldown(e.getPlayer(), e.getItem(), 40);
                                            setName(e.getItem(), e.getPlayer(), "§bTier5 Sniper Rifle §l» " + newAmmo + " «");
                                            Snowball s = e.getPlayer().launchProjectile(Snowball.class);
                                            s.setVelocity(s.getVelocity().multiply(8));
                                            s.setCustomName("TIER5SNIPE");
                                            s.setCustomNameVisible(false);
                                            if (newAmmo == 0) {
                                                reload(e.getPlayer(), e.getItem(), "§bTier5 Sniper Rifle §l» 2 «", 10, Material.NETHER_BRICK_ITEM, 2);
                                            }
                                        }
                                    }
                                }

                            } else if (e.getItem().hasItemMeta() && !e.getItem().getItemMeta().hasDisplayName()) {
                                setName(e.getItem(), e.getPlayer(), "§bTier5 Sniper Rifle §l» 2 «");
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
                if(getAmmo(e.getItemDrop().getItemStack()) != 50) {
                    reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eJetpack §l» 50 «", 15);
                    }
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.FEATHER) {
                if(getAmmo(e.getItemDrop().getItemStack()) != 2) {
                    reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eTaser §l» 2 «", 10);
                    }
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.FLINT_AND_STEEL) {
                if(getAmmo(e.getItemDrop().getItemStack()) != 200) {
                    reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eFlamethrower §l» 200 «", 10);
                    }
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.SUGAR_CANE) {
                if(getAmmo(e.getItemDrop().getItemStack()) != 1) {
                    reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eBlow Gun §l» 1 «", 10);
                    }
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.CARROT_STICK) {
                if(getAmmo(e.getItemDrop().getItemStack()) != 10) {
                    reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§5Enderstaff §l» 10 «", 10);
                    }
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.LEASH) {
                if(getAmmo(e.getItemDrop().getItemStack()) != 500) {
                    reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eMinigun §l» 500 «", 30);
                    }
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.IRON_BARDING) {
                if(getAmmo(e.getItemDrop().getItemStack()) != 1) {
                    reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eBazooka §l» 1 «", 20);
                }
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.WOOD_SPADE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eTier1 Shotgun §l» 4 «", 10, Material.GOLD_INGOT, (4 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.STONE_SPADE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§6Tier2 Shotgun §l» 2 «", 8, Material.GOLD_INGOT, (2 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.GOLD_SPADE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§aTier3 Shotgun §l» 5 «", 12, Material.GOLD_INGOT, (5 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.IRON_SPADE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§dTier4 Shotgun §l» 5 «", 12, Material.GOLD_INGOT, (5 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.DIAMOND_SPADE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§bTier5 Shotgun §l» 6 «", 10, Material.GOLD_INGOT, (6 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.WOOD_PICKAXE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eTier1 Assault Rifle §l» 20 «", 10, Material.IRON_INGOT, (20 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.STONE_PICKAXE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§6Tier2 Assault Rifle §l» 25 «", 11, Material.IRON_INGOT, (25 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.GOLD_PICKAXE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§aTier3 Assault Rifle §l» 25 «", 11, Material.IRON_INGOT, (25 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.IRON_PICKAXE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§dTier4 Assault Rifle §l» 30 «", 13, Material.IRON_INGOT, (30 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.DIAMOND_PICKAXE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§bTier5 Assault Rifle §l» 35 «", 15, Material.IRON_INGOT, (35 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.WOOD_HOE) {
                reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§eTier1 Sniper Rifle §l» 4 «", 10, Material.NETHER_BRICK_ITEM, (4 - (getAmmo(e.getItemDrop().getItemStack()))));
                e.setCancelled(true);
            }
        if (e.getItemDrop().getItemStack().getType() == Material.STONE_HOE) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§6Tier2 Sniper Rifle §l» 4 «", 10, Material.NETHER_BRICK_ITEM, (4 - (getAmmo(e.getItemDrop().getItemStack()))));
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.GOLD_HOE) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§aTier3 Sniper Rifle §l» 6 «", 10, Material.NETHER_BRICK_ITEM, (6 - (getAmmo(e.getItemDrop().getItemStack()))));
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.IRON_HOE) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§dTier4 Sniper Rifle §l» 5 «", 10, Material.NETHER_BRICK_ITEM, (5 - (getAmmo(e.getItemDrop().getItemStack()))));
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType() == Material.DIAMOND_HOE) {
            reload(e.getPlayer(), e.getItemDrop().getItemStack(), "§bTier5 Sniper Rifle §l» 2 «", 10, Material.NETHER_BRICK_ITEM, (2 - (getAmmo(e.getItemDrop().getItemStack()))));
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
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            ArrayList<ItemStack> toGive = new ArrayList<>();
            for (ItemStack i : p.getInventory().getContents()) {
                if (i != null) {
                    if(i.getType() == Material.DIAMOND_SPADE ||
                            i.getType() == Material.LEASH ||
                            i.getType() == Material.DOUBLE_PLANT ||
                            i.getType() == Material.FLINT_AND_STEEL ||
                            i.getType() == Material.CARROT_STICK ||
                            i.getType() == Material.IRON_BARDING ||
                            i.getType() == Material.FEATHER ||
                            i.getType() == Material.SUGAR_CANE ||
                            i.getType() == Material.DIAMOND_PICKAXE) {
                        toGive.add(i);
                        e.getDrops().remove(i);
                    }
                }
            }
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
                public void run() {
                    p.spigot().respawn();
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
                        public void run() {
                            for (ItemStack i : toGive) {
                                p.getInventory().addItem(i);
                            }
                            p.updateInventory();
                        }

                    });
                }
            });
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
                    if (s.getCustomName().equals("TIER1AR")) {
                        e.setDamage(1);
                    }
                    if (s.getCustomName().equals("TIER2AR")) {
                        e.setDamage(2);
                    }
                    if (s.getCustomName().equals("TIER3AR")) {
                        e.setDamage(4);
                    }
                    if (s.getCustomName().equals("TIER4AR")) {
                        e.setDamage(4);
                    }
                    if (s.getCustomName().equals("TIER5AR")) {
                        e.setDamage(6);
                    }
                    if (s.getCustomName().equals("TIER1SNIPE")) {
                        e.setDamage(4);
                    }
                    if (s.getCustomName().equals("TIER2SNIPE")) {
                        e.setDamage(6);
                    }
                    if (s.getCustomName().equals("TIER3SNIPE")) {
                        e.setDamage(7);
                    }
                    if (s.getCustomName().equals("TIER4SNIPE")) {
                        e.setDamage(8);
                    }
                    if (s.getCustomName().equals("TIER5SNIPE")) {
                        e.setDamage(12);
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
                    if(s.getCustomName().equals("TIER1SHOTTY")) {
                        e.setDamage(2);
                    }
                    if(s.getCustomName().equals("TIER2SHOTTY")) {
                        e.setDamage(5);
                    }
                    if(s.getCustomName().equals("TIER3SHOTTY")) {
                        e.setDamage(4);
                    }
                    if(s.getCustomName().equals("TIER4SHOTTY")) {
                        e.setDamage(6);
                    }
                    if(s.getCustomName().equals("TIER5SHOTTY")) {
                        if(e.getEntity() instanceof Player) {
                            Player p = (Player) e.getEntity();
                            p.setFireTicks(20);
                        }
                        e.setDamage(10);
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
        if(e.getClickedInventory().getTitle() != null && e.getClickedInventory().getTitle().equals("§c§lGuns")) {
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
                    if (p.getItemInHand() != null && p.getItemInHand().isSimilar(reloading.get(p))) {
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


    private void reload(Player p, ItemStack i, String name, Integer time, Material ammo, int amt) {
        if(!reloading.containsKey(p)) {
            if(amt == 0) {
                return;
            }
            p.sendMessage("§a§lReloading (§7" + time + " seconds§a§l)");
            reloading.put(p, i);
            boolean foundAmmo = false;
                for(ItemStack ii : p.getInventory().getContents()) {
                    if (ii != null) {
                        if (ii.getType().equals(ammo)) {
                            foundAmmo = true;
                            }
                    }
                }
                if(!foundAmmo) {
                    p.sendMessage("§c§lCannot reload (§7No Ammo§c§l)");
                    reloading.remove(p);
                } else {
                    scheduleReload(p, name, time, ammo, amt);
                }
        }
    }

    private void scheduleReload(Player p, String name, Integer time, Material ammo, int amt) {
        final int t = time-1;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (reloading.containsKey(p)) {
                    if (p.getItemInHand().isSimilar(reloading.get(p))) {
                        if (t == 0) {
                            boolean foundAmmo = false;
                            for(ItemStack ii : p.getInventory().getContents()) {
                                if (ii != null) {
                                    if (ii.getType().equals(ammo)) {
                                        foundAmmo = true;
                                        if (ii.getAmount() == amt) {
                                            p.getInventory().removeItem(ii);
                                            p.updateInventory();
                                        } else if (ii.getAmount() > amt) {
                                            ii.setAmount(ii.getAmount() - amt);
                                            p.updateInventory();
                                        } else {
                                            foundAmmo = false;
                                        }
                                    }
                                }
                            }
                            if(!foundAmmo) {
                                p.sendMessage("§c§lCannot reload (§7Not Enough Ammo§c§l)");
                            } else {
                                setName(p.getItemInHand(), p, name);
                            }
                            reloading.remove(p);
                        } else {
                            scheduleReload(p, name, t, ammo, amt);
                        }
                    } else {
                        reloading.remove(p);
                        p.sendMessage("§c§lCancelled Reload (§7Switched item§c§l)");
                    }
                }
            }
        }, 20);
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

    private void fireShotgunShots(Player p, String n) {
        Egg s = p.launchProjectile(Egg.class);
        Egg s1 = p.launchProjectile(Egg.class);
        Egg s2 = p.launchProjectile(Egg.class);
        Egg s3 = p.launchProjectile(Egg.class);
        Egg s4 = p.launchProjectile(Egg.class);
        s.setVelocity(s.getVelocity());
        s1.setVelocity(s.getVelocity().add(new Vector(0, 0.3, 0)));
        s2.setVelocity(s.getVelocity().add(new Vector(0.2, 0.3, 0)));
        s3.setVelocity(s.getVelocity().add(new Vector(0, 0.3, 0.2)));
        s4.setVelocity(s.getVelocity().add(new Vector(0, -0.1, 0)));
        s.setCustomName(n);
        s.setCustomNameVisible(false);
        s1.setCustomName(n);
        s1.setCustomNameVisible(false);
        s2.setCustomName(n);
        s2.setCustomNameVisible(false);
        s3.setCustomName(n);
        s3.setCustomNameVisible(false);
        s4.setCustomName(n);
        s4.setCustomNameVisible(false);
    }
}
