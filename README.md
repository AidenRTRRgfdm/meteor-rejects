# Meteor Rejects for Minecraft 26.2

A Minecraft 26.2 port of [AntiCope/meteor-rejects](https://github.com/AntiCope/meteor-rejects), based on upstream commit `6a56030`. All 58 registered upstream modules, 17 commands, Radar HUD, and Meteor Rounded theme are retained. Original author credits, feature attribution and GPL-3.0 license are preserved.

## Install

Use Minecraft **26.2**, Fabric Loader **0.19.3 or newer**, Java **25**, and Meteor Client **26.2 build 32**. Download `meteor-rejects-addon-0.3.1+26.2.jar` from this fork's [releases](https://github.com/AidenRTRRgfdm/meteor-rejects/releases) and put it beside Meteor in your profile's `mods` directory. Remove other copies of Meteor Rejects first.

Baritone is optional. Install **Baritone for 26.2** for Baritone-dependent features. Exploit Preventer remains optional. This JAR does not contain Meteor or Baritone.

## Build and validate

With a Java 25 JDK selected:

```sh
./gradlew build
./gradlew -PclientTests runClientGameTest
```

The normal build produces `build/libs/meteor-rejects-addon-0.3.1+26.2.jar`. The client test starts a separate disposable local Minecraft instance and checks mixin application, all module and command registrations, configuration roundtrip, the center command in a local world, and interaction/rounded GUI rendering. Test code and its extra dependencies are excluded from the release JAR.

Meteor is pinned to Maven build `26.2-20261002.183827-32`. Cubiomes 1.22.3 is vendored from its original official release because its Maven host is unavailable; source links, hashes and licenses are in `libs/cubiomes/NOTICE.txt`.

## Known limits

Multiplayer server-specific behavior and custom account authentication have not been validated. Later Meteor snapshots can change private APIs used by this add-on.

OreSim retains the upstream estimation algorithm and the client's vanilla ore feature registry. Exact 26.2 predictions are unverified and can differ with old generated chunks, custom world generation, or modified terrain. The upstream Cubiomes and Seedfinding structure fallback supports older generation versions; it is not a verified 26.2 structure predictor.

# Features
## Modules
- AimAssist (Removed from Meteor in [ee391](https://github.com/MeteorDevelopment/meteor-client/commit/ee391e431f345f253447f425dbc0de8625f88e65))
- AntiBot (Removed from Meteor in [166fc](https://github.com/MeteorDevelopment/meteor-client/commit/166fccc73e53de6cfdbe41ea58dc593a2f5011f6))
- AntiCrash (Ported from [Anti-ClientCrasher](https://github.com/wagyourtail/Anti-ClientCrasher))
- AntiSpawnpoint
- AntiVanish
- ArrowDmg (Ported from [Wurst](https://github.com/Wurst-Imperium/Wurst7/tree))
- AutoBedTrap (Ported from [BleachHack-CupEdition](https://github.com/CUPZYY/BleachHack-CupEdition/blob/master/CupEdition-1.17/src/main/java/bleach/hack/module/mods/AutoBedtrap.java))
- AutoCraft (More generalized version of [AutoBedCraft](https://github.com/Anticope/orion/blob/main/src/main/java/me/ghosttypes/orion/modules/main/AutoBedCraft.java) from orion)
- AutoDrop
- AutoEnchant
- AutoExtinguish
- AutoFarm
- AutoGrind
- AutoLogin
- AutoPot (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/274))
- AutoSoup (Ported from [Wurst](https://github.com/Wurst-Imperium/Wurst7/tree))
- AutoTNT
- AutoWither (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/1070))
- BlockIn
- BoatGlitch & BoatPhase (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/814))
- Boost (Ported from [Cornos](https://github.com/cornos/Cornos/blob/master/src/main/java/me/zeroX150/cornos/features/module/impl/movement/Boost.java))
- BungeeCordSpoof (Ported from [LiquidBounce](https://github.com/CCBlueX/LiquidBounce))
- ChatBot
- ChestAura
- ChorusExploit (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/1727))
- ColorSigns
- Confuse
- Coord Logger (World events from [JexClient](https://github.com/DustinRepo/JexClient-main/blob/main/src/main/java/me/dustin/jex/feature/mod/impl/misc/CoordFinder.java))
- Custom Packets
- DebugRender
- Extra Elytra (Ported from [Wurst](https://github.com/Wurst-Imperium/Wurst7/tree))
- FullFlight (Antikick bypasses by [CCblueX](https://github.com/CCblueX) and [LiveOverflow](https://github.com/LiveOverflow))
- Gamemode notifier
- Ghost Mode (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/1932))
- Glide (Ported from [Wurst](https://github.com/Wurst-Imperium/Wurst7/tree))
- Item generator (Ported from [Wurst](https://github.com/Wurst-Imperium/Wurst7/tree))
- InteractionMenu (Ported from [BleachHack](https://github.com/BleachDrinker420/BleachHack/pull/211))
- Jetpack
- KnockbackPlus
- Lavacast
- LawnBot (Ported from [JexClient](https://github.com/DustinRepo/JexClient/blob/main/src/main/java/me/dustin/jex/feature/mod/impl/world/LawnBot.java))
- MossBot (Ported from [BleachHack](https://github.com/BleachDrinker420/BleachHack/pull/211))
- NewChunks (Ported from [BleachHack](https://github.com/BleachDrinker420/BleachHack/blob/master/BleachHack-Fabric-1.17/src/main/java/bleach/hack/module/mods/NewChunks.java))
- NoJumpDelay
- ObsidianFarm (Taken from [Meteor ObsidianFarm Addon](https://github.com/VoidCyborg/meteor-obsidian-farm))
- Oresim (Ported from [Atomic](https://gitlab.com/0x151/atomic))
- PacketFly (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/813))
- Painter
- Rendering
- RoboWalk ((Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/3015)))
- Shield Bypass
- Silent Disconnect
- SkeletonESP (Ported from [JexClient](https://github.com/DustinRepo/JexClient-main/blob/main/src/main/java/me/dustin/jex/feature/mod/impl/render/Skeletons.java))
- SoundLocator
- Server Finder (Ported from [MeteorAdditions](https://github.com/JFronny/MeteorAdditions))
- TreeAura (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/2138))
- VehicleOneHit (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/3539))

### Modifications
- NoRender
  - `noCommandSuggestions` (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/1347))
  - `disableToasts`
- Flight
  - `stopMomentum`
- AutoSign
  - `Random characters` (Ported from [BleachHack](https://github.com/BleachDrinker420/BleachHack))
- Module
  - `Duplicate names`
- KillAura
  - `Fov and invisible filter`
  - `Random Teleport, Hit Chance, Random Delay` (Removed from Meteor in [8722e](https://github.com/MeteorDevelopment/meteor-client/commit/8722ef565afa02ca4b6d9710a20fc9fcfd97bf05))
- Alts
  -  `Yggdrasil Login`
- ServerSpoof
  -  `Translation Key, Fingerprint, and Local HTTP Request prevention` ([ExploitPreventer](https://github.com/NikOverflow/ExploitPreventer))

## Commands
- `.center`
- `.clear-chat` (Removed from meteor in [9aebf](https://github.com/MeteorDevelopment/meteor-client/commit/9aebf6a0e4ffa739d901c8b8d7f48d07af2fe839))
- `.fill`
- `.ghost` (Ported from [AntiGhost](https://github.com/gbl/AntiGhost/blob/fabric_1_16/src/main/java/de/guntram/mcmod/antighost/AntiGhost.java))
- `.save-skin`
- `.heads`
- `.seed` (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/1300))
- `.setblock`
- `.panic` (Removed from meteor in [dd5f8](https://github.com/MeteorDevelopment/meteor-client/commit/dd5f88a0dbb2753372bf37c58461b886104dc990))
- `.set-velocity`
- `.teleport`
- `.terrain-export` (Ported from [BleachHack](https://github.com/BleachDrinker420/BleachHack/blob/master/BleachHack-Fabric-1.17/src/main/java/bleach/hack/command/commands/CmdTerrain.java))
- `.kick` (Ported from [LiquidBounce](https://github.com/CCBlueX/LiquidBounce/blob/nextgen/src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/exploit/ModuleKick.kt))

### Modifications
- `.server`
  - `ports` (Ported from [Cornos](https://github.com/cornos/Cornos/blob/master/src/main/java/me/zeroX150/cornos/features/command/impl/Scan.java))
- `.locate`
  - rewrite (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/1300))
- `.give`
  - presets (Some presets were taken from [BleachHack](https://github.com/BleachDrinker420/BleachHack/blob/master/BleachHack-Fabric-1.17/src/main/java/bleach/hack/command/commands/CmdGive.java))

## Themes
- "Meteor Rounded" theme (Taken from an [unmerged PR](https://github.com/MeteorDevelopment/meteor-client/pull/619))

## HUD
- Radar HUD

## Config
- `Http Allowed` - Modify what HTTP requests can be made with Meteor's HTTP API
- `Http User Agent` - Modify the HTTP header of Meteor's HTTP API
- `Hidden Modules` - Hide modules from module gui. **requires restart when unhiding**
- `Load System Fonts` - Disabling this for faster launch. You can put font into meteor-client/fonts folder. **requires restart to take effect**
- `Duplicate Module Names` - Allow duplicate module names. Enable it when you have one module overriding another.
