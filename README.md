# GTAMC
Open source GTA plugin for Minecraft, with no dependencies! (Developed on 1.8)

This project is free for all to use! Just don't redistribute, or claim as your own.

This is a WIP and will be updated as made.


To run:

- Download the source
- Import it into a Java IDE
- Include a spigot 1.8 library
- Add in your MySQL database information in the Core.java file (plugin requires MySQL to run)
- Export and enjoy :)


# Quick Help Guide
Ranks:
- Owner (Has all perms)
- Admin (Has all perms)
- Mod (Has upper-staff perms)
- Helper (Has lower-staff perms)
- Mob Boss (Top donator rank)
- Hitman (Upper donator rank)
- Hoodlum (Middle donator rank)
- Crook (Lowest donator rank)
- Default (Starting rank)

Commands:

/setrank ||Player|| ||rank||: Sets the Player's rank to <rank> (Requires: OWNER or ADMIN rank, or can be run from console)
  
/guns: Opens the gun GUI to get guns (Requires: OWNER or ADMIN rank)

/feed: Feeds the sender (Requires: OWNER, ADMIN, MOD, HELPER, MOB BOSS, or HITMAN rank)

/bal: Tells you your balance (No rank required)

/fly: Toggles your flight (Requires: OWNER or ADMIN rank)

/mute ||player||: Mutes ||Player|| (Requires: OWNER, ADMIN, MOD, or HELPER rank)
  
/pay ||Player|| ||amount||: Attempts to pay ||Player|| $||amount|| from your balance (No rank required)
  
/summoncar: Sets you riding a car (WIP) (Requires: OWNER or ADMIN rank)

/spawn: Sends you to spawn after not moving for 5 seconds (OWNER or ADMIN tp instantly)

/summoncop: Spawns a cop to your location (Requires: OWNER or ADMIN rank)

/vanish: Hides you (Requires: OWNER, ADMIN, or MOD rank)

/getammokey: Gives you an ammo key (this is a test command, and is planned on being replaced with a proper one. If putting this plugin on a live server pls disable this command as it has no permissions needed)
