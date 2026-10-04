package anticope.rejects.test;

import anticope.rejects.MeteorRejectsAddon;
import anticope.rejects.gui.screens.InteractionScreen;
import anticope.rejects.gui.themes.rounded.MeteorRoundedGuiTheme;
import anticope.rejects.modules.NoJumpDelay;
import anticope.rejects.modules.SkeletonESP;
import anticope.rejects.utils.RejectsConfig;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.gui.GuiThemes;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.fabricmc.fabric.api.client.gametest.v1.FabricClientGameTest;
import net.fabricmc.fabric.api.client.gametest.v1.context.ClientGameTestContext;
import net.minecraft.nbt.CompoundTag;

/** Exercises real client bootstrap, mixins, registration, commands and both GUI paths. */
public final class PortClientTest implements FabricClientGameTest {
    @Override
    public void runTest(ClientGameTestContext context) {
        context.getInput().resizeWindow(1100, 700);
        org.spongepowered.asm.mixin.MixinEnvironment.getCurrentEnvironment().audit();
        context.runOnClient(client -> {
            long modules = Modules.get().getAll().stream()
                .filter(module -> module.category == MeteorRejectsAddon.CATEGORY).count();
            require(modules == 58, "Expected every upstream module; found " + modules);
            for (String name : new String[] {"center", "clear-chat", "fill", "ghost", "give", "heads", "kick",
                    "locate", "panic", "reconnect", "server", "save-skin", "seed", "setblock", "set-velocity",
                    "teleport", "terrain-export"}) {
                require(Commands.get(name) != null, "Missing command " + name);
            }
            var config = RejectsConfig.get();
            CompoundTag saved = config.toTag();
            config.httpUserAgent = "port-test";
            config.duplicateModuleNames = true;
            CompoundTag changed = config.toTag();
            config.httpUserAgent = "reset";
            config.duplicateModuleNames = false;
            config.fromTag(changed);
            require(config.httpUserAgent.equals("port-test") && config.duplicateModuleNames,
                "Rejects configuration failed NBT roundtrip");
            config.fromTag(saved);
        });
        try (var world = context.worldBuilder().create()) {
            context.waitFor(client -> client.player != null && client.level != null, 400);
            world.getConnection().waitForChunksRender();
            context.runOnClient(client -> {
                try { Commands.dispatch("center middle"); }
                catch (Exception error) { throw new AssertionError("Center command failed", error); }
                require(Math.abs(client.player.getX() - Math.floor(client.player.getX()) - 0.5) < 0.001, "Center command did not move X");
                var noJump = Modules.get().get(NoJumpDelay.class);
                var skeleton = Modules.get().get(SkeletonESP.class);
                if (!noJump.isActive()) noJump.toggle();
                if (!skeleton.isActive()) skeleton.toggle();
                client.options.setCameraType(net.minecraft.client.CameraType.THIRD_PERSON_BACK);
                client.gui.setScreen(new InteractionScreen(client.player));
            });
            context.waitTicks(8);
            context.takeScreenshot("meteor-rejects-interaction-26.2");
            context.runOnClient(client -> {
                client.gui.setScreen(null);
                var rounded = new MeteorRoundedGuiTheme();
                GuiThemes.select(rounded.name);
                client.gui.setScreen(rounded.moduleScreen(Modules.get().get(SkeletonESP.class)));
            });
            context.waitTicks(8);
            context.takeScreenshot("meteor-rejects-rounded-26.2");
            context.runOnClient(client -> {
                client.gui.setScreen(null);
                require(Float.isFinite(client.player.getYRot()) && Float.isFinite(client.player.getXRot()),
                    "Interaction screen produced invalid player rotation");
                client.options.setCameraType(net.minecraft.client.CameraType.FIRST_PERSON);
                Modules.get().get(NoJumpDelay.class).toggle();
                Modules.get().get(SkeletonESP.class).toggle();
            });
            context.waitTicks(4);
        }
        System.out.println("METEOR_REJECTS_26_2_CLIENT_TEST_PASS");
    }
    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
